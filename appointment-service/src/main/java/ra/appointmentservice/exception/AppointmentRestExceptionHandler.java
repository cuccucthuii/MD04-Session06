package ra.appointmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.appointmentservice.dto.ApiResponseError;

@RestControllerAdvice
public class AppointmentRestExceptionHandler {

    @ExceptionHandler(DoctorServiceDegradedException.class)
    public ResponseEntity<ApiResponseError> handleDoctorDegraded(DoctorServiceDegradedException ex) {
        return toApiResponse(ex.getBody());
    }

    @ExceptionHandler(PatientServiceDegradedException.class)
    public ResponseEntity<ApiResponseError> handlePatientDegraded(PatientServiceDegradedException ex) {
        return toApiResponse(ex.getBody());
    }

    private static ResponseEntity<ApiResponseError> toApiResponse(ApiResponseError body) {
        int status = body != null ? body.getStatus() : HttpStatus.SERVICE_UNAVAILABLE.value();
        return ResponseEntity.status(status).body(body);
    }
}
