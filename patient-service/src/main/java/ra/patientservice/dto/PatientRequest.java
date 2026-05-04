package ra.patientservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequest {

    @NotBlank
    @Size(max = 255)
    private String fullName;

    @NotNull
    private LocalDate dateOfBirth;

    @NotBlank
    @Size(max = 50)
    private String gender;

    @NotBlank
    @Size(max = 30)
    private String phoneNumber;

    @NotBlank
    @Size(max = 500)
    private String address;

    @Size(max = 10_000)
    private String medicalHistory;
}
