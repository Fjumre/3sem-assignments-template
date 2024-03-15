package app.config;

import app.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.HttpStatus;
import app.security.SecurityController;

import java.util.Set;

public class ApplicationConfig {

    ObjectMapper om = new ObjectMapper();

    private Javalin app;
    private static ApplicationConfig instance;
    private ApplicationConfig appConfig;


    private ApplicationConfig(){
    }

    public static ApplicationConfig getInstance(){
        if (instance== null){
            instance= new ApplicationConfig();
        }
        return instance;
    }

    public ApplicationConfig initiateServer(){

       app= Javalin.create(config -> {
            config.http.defaultContentType="application/json";
            config.routing.contextPath="/api";

        });
        return instance;
    }

    public ApplicationConfig startServer(int port){

        app.start(port);
        return appConfig;
    }

    public ApplicationConfig setRoute(EndpointGroup route){

        app.routes(route);
        return instance;
    }

    public ApplicationConfig setExceptionHandling() {
        // Handle any Exception, typically resulting in HTTP 500 Internal Server Error
        app.exception(Exception.class, (e, ctx) -> {
            ObjectNode node = om.createObjectNode();
            node.put("errorMessage", e.getMessage());
            ctx.status(500).json(node);
        });

        // Specific handler for HTTP 404 Not Found
        app.error(404, ctx -> {
            ObjectNode node = om.createObjectNode();
            node.put("message", "Resource not found");
            ctx.status(404).json(node);
        });

        // You could add a specific handler for HTTP 500 errors like this,
        // but generally, the exception handler above will suffice for catching exceptions.
        // Including it here for completeness and to show how it would be done.
        app.error(500, ctx -> {
            ObjectNode node = om.createObjectNode();
            node.put("message", "Internal server error, please try again later.");
            ctx.status(500).json(node);
        });
        return instance;
    }

    public void stopServer(){
        app.close();
    }

    public ApplicationConfig checkSecurityRoles() {
        // Check roles on the user (ctx.attribute("username") and compare with permittedRoles using securityController.authorize()
        app.updateConfig(config -> {

            config.accessManager((handler, ctx, permittedRoles) -> {
                // permitted roles are defined in the last arg to routes: get("/", ctx -> ctx.result("Hello World"), Role.ANYONE);

                Set<String> allowedRoles = permittedRoles.stream().map(role -> role.toString().toUpperCase()).collect(Collectors.toSet());
                if(allowedRoles.contains("ANYONE") || ctx.method().toString().equals("OPTIONS")) {
                    // Allow requests from anyone and OPTIONS requests (preflight in CORS)
                    handler.handle(ctx);
                    return;
                }

                UserDTO user = ctx.attribute("user");
                System.out.println("USER IN CHECK_SEC_ROLES: "+user);
                if(user == null)
                    ctx.status(HttpStatus.FORBIDDEN)
                            .json(om.createObjectNode()
                                    .put("msg","Not authorized. No username were added from the token"));

                if (SecurityController.getInstance().authorize(user, allowedRoles))
                    handler.handle(ctx);
                else
                    throw new ApiException(HttpStatus.FORBIDDEN.getCode(), "Unauthorized with roles: "+allowedRoles);
            });
        });
        return appConfig;
    }
}
