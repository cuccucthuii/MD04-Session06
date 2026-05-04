package ra.patientservice.mapper;

import org.springframework.stereotype.Component;
import ra.patientservice.dto.PatientRequest;
import ra.patientservice.dto.PatientResponse;
import ra.patientservice.entity.Patient;

@Component
public class PatientMapper {

    public Patient toNewEntity(PatientRequest request) {
        return Patient.builder()
                .fullName(request.getFullName())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .medicalHistory(request.getMedicalHistory())
                .build();
    }

    public void updateEntity(Patient patient, PatientRequest request) {
        patient.setFullName(request.getFullName());
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());
        patient.setPhoneNumber(request.getPhoneNumber());
        patient.setAddress(request.getAddress());
        patient.setMedicalHistory(request.getMedicalHistory());
    }

    public PatientResponse toResponse(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .fullName(patient.getFullName())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .phoneNumber(patient.getPhoneNumber())
                .address(patient.getAddress())
                .medicalHistory(patient.getMedicalHistory())
                .build();
    }
}
