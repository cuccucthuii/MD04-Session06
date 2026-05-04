package ra.appointmentservice.service;

public interface AppointmentReferenceValidator {

    void ensurePatientExists(Long patientId);

    void ensureDoctorExists(Long doctorId);
}
