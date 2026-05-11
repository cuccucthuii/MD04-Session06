package ra.appointmentservice.service.remote;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ra.appointmentservice.dto.ApiResponseError;
import ra.appointmentservice.exception.InvalidAppointmentReferenceException;

@Component
@RequiredArgsConstructor
public class DoctorExistenceClient {

    private static final String DOCTOR_BY_ID_URL = "http://doctor-service/api/v1/doctors/{id}";

    private final RestTemplate loadBalancedRestTemplate;

    /**
     * @return {@code null} nếu bác sĩ tồn tại; {@link ApiResponseError} khi fallback (CB mở hoặc lỗi gọi service).
     */
    @CircuitBreaker(name = "doctorServiceCB", fallbackMethod = "getDoctorFallback")
    public ApiResponseError verifyDoctorExists(Long doctorId) {
        fetchDoctor(doctorId);
        return null;
    }

    @SuppressWarnings("unused")
    private ApiResponseError getDoctorFallback(Long doctorId, Exception e) {
        return ApiResponseError.builder()
                .status(503)
                .message("Hiện tại không thể kiểm tra thông tin bác sĩ, vui lòng thử lại sau vài giây")
                .build();
    }

    private void fetchDoctor(Long doctorId) {
        try {
            loadBalancedRestTemplate.getForEntity(DOCTOR_BY_ID_URL, String.class, doctorId);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new InvalidAppointmentReferenceException("Bác sĩ", doctorId);
        }
    }
}
