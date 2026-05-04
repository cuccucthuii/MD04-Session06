package ra.patientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.patientservice.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
