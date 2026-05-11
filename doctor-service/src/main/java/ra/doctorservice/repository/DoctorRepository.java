package ra.doctorservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.doctorservice.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByNameContainingIgnoreCase(String namePart);
}
