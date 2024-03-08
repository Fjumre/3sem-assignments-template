package app.veterinarian;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import java.util.List;

public class PatientHandler {
    private List<Patient> paList;

    public PatientHandler(List<Patient> paList) {

        this.paList = paList;
    }

    public void getAllPatients(Context ctx) {

        ctx.json(paList);
    }

    public void getPatientById(Context ctx) {
        int paId = Integer.parseInt(ctx.pathParam("paId"));
        Patient patient = paList.stream()
                .filter(p -> p.getId() == paId)
                .findFirst()
                .orElseThrow(() -> new NotFoundResponse("Patient not found"));
        ctx.json(patient);
    }
}
