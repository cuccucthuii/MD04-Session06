package ra.doctorservice.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/{id}")
    public DoctorListItemResponse get(@PathVariable Long id) {
        return doctorService.findById(id);
    }
}
