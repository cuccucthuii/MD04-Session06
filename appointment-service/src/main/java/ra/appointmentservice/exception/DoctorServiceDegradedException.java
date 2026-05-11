package ra.appointmentservice.exception;

import lombok.Getter;
import ra.appointmentservice.dto.ApiResponseError;

@Getter
public class DoctorServiceDegradedException extends RuntimeException {

    private final ApiResponseError body;

    public DoctorServiceDegradedException(ApiResponseError body) {
        super(body != null ? body.getMessage() : "Doctor service unavailable");
        this.body = body;
    }
}
