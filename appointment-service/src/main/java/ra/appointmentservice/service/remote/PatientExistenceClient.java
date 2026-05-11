package ra.appointmentservice.service.remote;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ra.appointmentservice.dto.ApiResponseError;
import ra.appointmentservice.exception.InvalidAppointmentReferenceException;
import ra.appointmentservice.exception.PatientServiceDegradedException;

@Service
@RequiredArgsConstructor
public class PatientExistenceClient {

    private static final String PATIENT_BY_ID_URL = "http://patient-service/api/patients/{id}";

    private final RestTemplate loadBalancedRestTemplate;

    @Retry(name = "patientRetry", fallbackMethod = "getPatientFallback")
    public void verifyPatientExists(Long patientId) {
        try {
            loadBalancedRestTemplate.getForEntity(PATIENT_BY_ID_URL, String.class, patientId);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new InvalidAppointmentReferenceException("Bệnh nhân", patientId);
        }
    }

    @SuppressWarnings("unused")
    private void getPatientFallback(Long patientId, Throwable throwable) {
        if (throwable instanceof InvalidAppointmentReferenceException ex) {
            throw ex;
        }
        throw new PatientServiceDegradedException(
                ApiResponseError.builder()
                        .status(503)
                        .message(
                                "Không thể liên hệ patient-service sau nhiều lần thử. Vui lòng thử lại sau.")
                        .build());
    }
}
