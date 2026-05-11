package ra.doctorservice.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.doctorservice.dto.DoctorListItemResponse;
import ra.doctorservice.exception.DoctorNotFoundException;
import ra.doctorservice.mapper.DoctorMapper;
import ra.doctorservice.repository.DoctorRepository;
import ra.doctorservice.service.DoctorService;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DoctorListItemResponse> findAllForList() {
        return doctorRepository.findAll().stream().map(doctorMapper::toListItem).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DoctorListItemResponse findById(Long id) {
        return doctorRepository
                .findById(id)
                .map(doctorMapper::toListItem)
                .orElseThrow(() -> new DoctorNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorListItemResponse> searchByName(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }
        return doctorRepository.findByNameContainingIgnoreCase(query).stream()
                .map(doctorMapper::toListItem)
                .toList();
    }
}
