package app.controllers;

import app.Main;
import app.config.HibernateConfig;
import app.dao.UserDAO;
import app.dto.UserDTO;
import app.exceptions.NotAuthorizedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import io.javalin.http.HttpStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import java.text.ParseException;
import java.util.*;


class SecurityControllerTest {

    static EntityManagerFactory emfTest;
    static UserDAO userDAO;
    static UserDTO userDTO;
    static ISecurityController securityController;
    static SecurityController securityController2;
    static ObjectMapper objectMapper;
    static UserDTO newUser;
    static UserDTO createdUser;

    @BeforeAll
    static void setUpAll(){
        emfTest = HibernateConfig.getEntityManagerFactoryForTest();

        userDAO = new UserDAO(emfTest);

        userDTO= new UserDTO();

        securityController= new SecurityController();

        objectMapper= new ObjectMapper();

        securityController2= new SecurityController();


        RestAssured.baseURI= "http://localhost:7007/api/";
        Main.startServer(7007);
    }
    @AfterAll
    static void closeAll(){

        Main.closeServer();
    }

    @BeforeEach
    void setUp() {
        createdUser = new UserDTO("Keld", "1234");
        newUser = new UserDTO("fry", "kkkk", Collections.singleton("USER"));

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void registerUser() {
        UserDTO registerUser = new UserDTO("Barry", "kkkk", Collections.singleton("USER"));

        given().
                contentType(ContentType.JSON)
                .body(registerUser)
                .when()
                .post("/auth/register")
                .then()
                .statusCode(HttpStatus.CREATED.getCode())
                .body("userName", is(registerUser.getUsername()));
    }

    @Test
    void login() {

        given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("userName", is(newUser.getUsername()));
    }

    static String loginToken() {
        return given()
                .contentType(ContentType.JSON)
                .body(createdUser)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract().path("token");
    }
    @Test
    void testProtectedRouteUser() {

        given()
                .header("Authorization", "Bearer " + loginToken())
                .when()
                .get("/protected/user_demo")
                .then()
                .statusCode(200);

    }

    @Test
    void testProtectedRouteAdmin() {

        given()
                .header("Authorization", "Bearer "  + loginToken())
                .when()
                .get("/protected/admin_demo")
                .then()
                .statusCode(200);
    }

    @Test
    void createToken(){
        String token = securityController.createToken(newUser);


        assertNotNull(String.valueOf(token));
        assertEquals(newUser.getUsername(), "fry");
        System.out.println(token);
    }

    @Test
    void authorize() {

        Set<String> allowedRoles = new HashSet<>(Arrays.asList("USER"));

        boolean isAuthorized = securityController.authorize(newUser, allowedRoles);

        assertTrue(isAuthorized);
    }


    @Test
    void verifyToken() {
        String token = securityController.createToken(newUser);

        UserDTO isVerified= securityController.verifyToken(token);
        assertNotNull(String.valueOf(isVerified));
        assertEquals(newUser.getUsername(), isVerified.getUsername(), "fry");
    }

    @Test
    void tokenIsValid() throws ParseException, NotAuthorizedException, JOSEException {

        String secret ="DetteErEnHemmeligNøgleTilAtDanneJWT_Tokensmed";

        String token = securityController.createToken(newUser);

        boolean isValid= securityController2.tokenIsValid(token, secret);

        assertTrue(isValid);
    }

    @Test
    void tokenNotExpired() throws ParseException, NotAuthorizedException {

        String token = securityController.createToken(newUser);

        boolean tokenTime= securityController2.tokenNotExpired(token);

        assertTrue(tokenTime);
    }

    @Test
    void getUserWithRolesFromToken() throws ParseException {

        String token = securityController.createToken(newUser);

        UserDTO userRole= securityController2.getUserWithRolesFromToken(token);

        assertTrue(userRole.getRoles().contains(Main.Role.USER.toString()));
    }

    @Test
    void timeToExpire() throws ParseException, NotAuthorizedException {
        String token = securityController.createToken(newUser);
        int timeLeft= securityController2.timeToExpire(token);

        System.out.println(timeLeft + " milliseconds left");
    }
}