package ra.appointmentservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.appointmentservice.dto.AppointmentCreateRequest;
import ra.appointmentservice.dto.AppointmentResponse;
import ra.appointmentservice.entity.Appointment;
import ra.appointmentservice.mapper.AppointmentMapper;
import ra.appointmentservice.repository.AppointmentRepository;
import ra.appointmentservice.service.AppointmentReferenceValidator;
import ra.appointmentservice.service.AppointmentService;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final AppointmentReferenceValidator appointmentReferenceValidator;

    @Override
    @Transactional
    public AppointmentResponse create(AppointmentCreateRequest request) {
        appointmentReferenceValidator.ensurePatientExists(request.getPatientId());
        appointmentReferenceValidator.ensureDoctorExists(request.getDoctorId());
        Appointment saved = appointmentRepository.save(appointmentMapper.toNewEntity(request));
        return appointmentMapper.toResponse(saved);
    }
}
