package ra.appointmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.appointmentservice.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
