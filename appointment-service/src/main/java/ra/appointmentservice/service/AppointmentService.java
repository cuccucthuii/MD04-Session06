package ra.appointmentservice.service;

import ra.appointmentservice.dto.AppointmentCreateRequest;
import ra.appointmentservice.dto.AppointmentResponse;

public interface AppointmentService {

    AppointmentResponse create(AppointmentCreateRequest request);
}
