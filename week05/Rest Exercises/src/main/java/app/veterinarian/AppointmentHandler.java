package app.veterinarian;

import app.veterinarian.Appointment;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import java.util.List;

public class AppointmentHandler {
    private List<Appointment> apList;

    public AppointmentHandler(List<Appointment> apList) {

        this.apList = apList;
    }

    public void getAllAppointments(Context ctx) {

        ctx.json(apList);
    }

    public void getAppointmentById(Context ctx) {
        int apId = Integer.parseInt(ctx.pathParam("apId"));
        Appointment appointment = apList.stream()
                .filter(a -> a.getId() == apId)
                .findFirst()
                .orElseThrow(() -> new NotFoundResponse("Appointment not found"));
        ctx.json(appointment);
    }
}
