package com.lhbnt.ticketreservation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthDto {
    @NotEmpty(message = "Missing credentials")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Missing credentials")
    @Min(value = 8, message = "Password length must be more than 8")
    private String password;
}
