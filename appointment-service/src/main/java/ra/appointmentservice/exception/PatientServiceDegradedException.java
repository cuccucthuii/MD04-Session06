package ra.appointmentservice.exception;

import lombok.Getter;
import ra.appointmentservice.dto.ApiResponseError;

@Getter
public class PatientServiceDegradedException extends RuntimeException {

    private final ApiResponseError body;

    public PatientServiceDegradedException(ApiResponseError body) {
        super(body != null ? body.getMessage() : "Patient service unavailable");
        this.body = body;
    }
}
