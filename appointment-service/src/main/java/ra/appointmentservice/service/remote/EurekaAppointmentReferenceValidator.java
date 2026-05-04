package ra.appointmentservice.service.remote;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ra.appointmentservice.exception.InvalidAppointmentReferenceException;
import ra.appointmentservice.service.AppointmentReferenceValidator;

@Service
@RequiredArgsConstructor
public class EurekaAppointmentReferenceValidator implements AppointmentReferenceValidator {

    private static final String PATIENT_BY_ID_URL = "http://patient-service/api/patients/{id}";
    private static final String DOCTOR_BY_ID_URL = "http://doctor-service/api/v1/doctors/{id}";

    private final RestTemplate loadBalancedRestTemplate;

    @Override
    public void ensurePatientExists(Long patientId) {
        getExpectingSuccess(patientId, PATIENT_BY_ID_URL, "Bệnh nhân");
    }

    @Override
    public void ensureDoctorExists(Long doctorId) {
        getExpectingSuccess(doctorId, DOCTOR_BY_ID_URL, "Bác sĩ");
    }

    private void getExpectingSuccess(Long id, String urlTemplate, String entityLabel) {
        try {
            loadBalancedRestTemplate.getForEntity(urlTemplate, String.class, id);
        } catch (HttpClientErrorException.NotFound e) {
            throw new InvalidAppointmentReferenceException(entityLabel, id);
        }
    }
}
