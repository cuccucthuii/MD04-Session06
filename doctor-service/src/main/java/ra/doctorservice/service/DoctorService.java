package ra.doctorservice.service;

import java.util.List;
import ra.doctorservice.dto.DoctorListItemResponse;

public interface DoctorService {

    List<DoctorListItemResponse> findAllForList();

    DoctorListItemResponse findById(Long id);
}
