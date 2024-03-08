package app.veterinarian;

import app.ApplicationConfig;
import app.dog.Dog;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.NotFoundResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class Main {

    public static void main(String[] args) {

        List<Appointment> apList = new ArrayList<>();

        apList.add(new Appointment(1, LocalDateTime.of(2024, 3, 15, 14, 0), "Charlie"));
        apList.add(new Appointment(2, LocalDateTime.of(2024, 3, 16, 9, 30), "Bella"));
        apList.add(new Appointment(3, LocalDateTime.of(2024, 3, 17, 11, 0), "Max"));


        List<Patient> paList = new ArrayList<>();

        paList.add(new Patient(1, "Fluffy", Arrays.asList("Penicillin"), Arrays.asList("Ibuprofen"), Arrays.asList("Spay")));
        paList.add(new Patient(2, "Whiskers", Arrays.asList("Nuts"), Arrays.asList("Paracetamol"), Arrays.asList("Dental cleaning")));
        paList.add(new Patient(3, "Rover", Arrays.asList("Grass"), Arrays.asList("Insulin"), Arrays.asList("Neutering")));


        AppointmentHandler appointmentHandler = new AppointmentHandler(apList);
        PatientHandler patientHandler = new PatientHandler(paList);
        ObjectMapper om = new ObjectMapper();

        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();
        applicationConfig
                .initiateServer()
                .startServer(7007)
                .setExceptionHandling()
                .setRoute(() -> {

                })
                .setRoute(() -> {
                    path("demo", () -> {
                        get("test", ctx -> ctx.json("{\"msg\":\"Hello from server\"}"));

                        get("error", ctx -> {
                            throw new Exception("Testing");
                        });
                    });
                    path("/vet", () -> {
                        path("/appointments", () -> {

                            get("/",appointmentHandler::getAllAppointments);
                            get("/{apId}", appointmentHandler::getAppointmentById);
                            get("/error", ctx ->{
                             throw new Exception(String.valueOf(ApplicationConfig.getInstance().setExceptionHandling()));
                            });
                            });



                        path("/patients", () -> {

                            get(patientHandler::getAllPatients);
                            get("/{paId}", patientHandler::getPatientById);
                            get("/error", ctx ->{
                                throw new Exception(String.valueOf(ApplicationConfig.getInstance().setExceptionHandling()));
                                });
                            });
                    });
                });

    }

}