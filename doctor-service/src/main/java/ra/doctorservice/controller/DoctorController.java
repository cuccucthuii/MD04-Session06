package ra.doctorservice.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ra.doctorservice.dto.DoctorListItemResponse;
import ra.doctorservice.service.DoctorService;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public List<DoctorListItemResponse> list() {
        return doctorService.findAllForList();
    }

    @GetMapping("/search")
    @RateLimiter(name = "searchDoctorLimit")
    public List<DoctorListItemResponse> search(@RequestParam("q") String q) {
        return doctorService.searchByName(q);
    }

    @GetMapping("/{id}")
    public DoctorListItemResponse get(@PathVariable Long id) {
        return doctorService.findById(id);
    }
}
