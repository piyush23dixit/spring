package com.coding.PracticeSpring.DTO;

import com.coding.PracticeSpring.Annotation.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private  Long id;

    @NotBlank(message="Name cannot be blank or null")
    @Size(min=1,max=10, message ="Name should be between 1 to 10")
    private  String name;

    @NotBlank(message="Email cannot be blank or null")
    @Email(message="Enter Valid email")
    private  String email;

    @Max(value=50, message ="Age should not be greater than 50")
    private  Integer age;

    @NotBlank(message="Role cannot be blank or null")
//@Pattern(regexp = "^(ADMIN|USER)$",message = "Employee can be Admin or User")
    @EmployeeRoleValidation
    private String role;


    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private Boolean isActive;


}


