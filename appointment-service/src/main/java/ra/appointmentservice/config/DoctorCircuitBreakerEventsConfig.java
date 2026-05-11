package ra.appointmentservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DoctorCircuitBreakerEventsConfig {

    private static final String DOCTOR_CB = "doctorServiceCB";

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @PostConstruct
    public void registerStateTransitionLogging() {
        circuitBreakerRegistry.circuitBreaker(DOCTOR_CB).getEventPublisher().onStateTransition(event -> {
            var transition = event.getStateTransition();
            log.info(
                    "[{}] CircuitBreaker chuyển trạng thái: {} -> {}",
                    DOCTOR_CB,
                    transition.getFromState(),
                    transition.getToState());
        });
    }
}
