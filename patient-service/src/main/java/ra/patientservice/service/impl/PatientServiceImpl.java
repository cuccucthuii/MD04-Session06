package ra.patientservice.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.patientservice.dto.PatientRequest;
import ra.patientservice.dto.PatientResponse;
import ra.patientservice.entity.Patient;
import ra.patientservice.exception.PatientNotFoundException;
import ra.patientservice.mapper.PatientMapper;
import ra.patientservice.repository.PatientRepository;
import ra.patientservice.service.PatientService;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PatientResponse> findAll() {
        return patientRepository.findAll().stream().map(patientMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponse findById(Long id) {
        return patientMapper.toResponse(getPatientOrThrow(id));
    }

    @Override
    @Transactional
    public PatientResponse create(PatientRequest request) {
        Patient saved = patientRepository.save(patientMapper.toNewEntity(request));
        return patientMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public PatientResponse update(Long id, PatientRequest request) {
        Patient patient = getPatientOrThrow(id);
        patientMapper.updateEntity(patient, request);
        return patientMapper.toResponse(patient);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException(id);
        }
        patientRepository.deleteById(id);
    }

    private Patient getPatientOrThrow(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(id));
    }
}
