package ra.appointmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAppointmentReferenceException extends RuntimeException {

    public InvalidAppointmentReferenceException(String entityLabel, Long id) {
        super(String.format("%s không tồn tại với id: %d", entityLabel, id));
    }
}
