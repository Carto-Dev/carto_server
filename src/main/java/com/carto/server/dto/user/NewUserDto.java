package com.carto.server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {

    @NotEmpty(message = "Display name should not be empty.")
    @Size(min = 5, message = "Display name should have at least 5 characters.")
    private String displayName;

    @NotEmpty(message = "Email address should not be empty.")
    @Email(message = "Email address is not valid.")
    private String emailAddress;

    @NotEmpty(message = "Password should not be empty.")
    @Size(min = 8, message = "Password should have at least 8 characters.")
    private String password;
}
