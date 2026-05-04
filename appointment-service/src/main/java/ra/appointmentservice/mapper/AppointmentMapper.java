package ra.appointmentservice.mapper;

import org.springframework.stereotype.Component;
import ra.appointmentservice.dto.AppointmentCreateRequest;
import ra.appointmentservice.dto.AppointmentResponse;
import ra.appointmentservice.entity.Appointment;

@Component
public class AppointmentMapper {

    public Appointment toNewEntity(AppointmentCreateRequest request) {
        String status = request.getStatus() != null ? request.getStatus() : "PENDING";
        return Appointment.builder()
                .patientId(request.getPatientId())
                .doctorId(request.getDoctorId())
                .appointmentDate(request.getAppointmentDate())
                .reason(request.getReason())
                .status(status)
                .build();
    }

    public AppointmentResponse toResponse(Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatientId())
                .doctorId(appointment.getDoctorId())
                .appointmentDate(appointment.getAppointmentDate())
                .reason(appointment.getReason())
                .status(appointment.getStatus())
                .build();
    }
}
