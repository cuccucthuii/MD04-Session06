package ra.patientservice.service;

import java.util.List;
import ra.patientservice.dto.PatientRequest;
import ra.patientservice.dto.PatientResponse;

public interface PatientService {

    List<PatientResponse> findAll();

    PatientResponse findById(Long id);

    PatientResponse create(PatientRequest request);

    PatientResponse update(Long id, PatientRequest request);

    void deleteById(Long id);
}
