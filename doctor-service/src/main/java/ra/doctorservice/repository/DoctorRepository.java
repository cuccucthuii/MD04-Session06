package ra.doctorservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.doctorservice.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
