package ra.doctorservice.mapper;

import org.springframework.stereotype.Component;
import ra.doctorservice.dto.DoctorListItemResponse;
import ra.doctorservice.entity.Doctor;

@Component
public class DoctorMapper {

    public DoctorListItemResponse toListItem(Doctor doctor) {
        return DoctorListItemResponse.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .specialization(doctor.getSpecialization())
                .build();
    }
}
