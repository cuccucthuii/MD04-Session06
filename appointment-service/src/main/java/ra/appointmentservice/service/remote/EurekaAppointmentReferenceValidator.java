package ra.appointmentservice.service.remote;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.appointmentservice.dto.ApiResponseError;
import ra.appointmentservice.exception.DoctorServiceDegradedException;
import ra.appointmentservice.service.AppointmentReferenceValidator;

@Service
@RequiredArgsConstructor
public class EurekaAppointmentReferenceValidator implements AppointmentReferenceValidator {

    private final PatientExistenceClient patientExistenceClient;
    private final DoctorExistenceClient doctorExistenceClient;

    @Override
    public void ensurePatientExists(Long patientId) {
        patientExistenceClient.verifyPatientExists(patientId);
    }

    @Override
    public void ensureDoctorExists(Long doctorId) {
        ApiResponseError degraded = doctorExistenceClient.verifyDoctorExists(doctorId);
        if (degraded != null) {
            throw new DoctorServiceDegradedException(degraded);
        }
    }
}
