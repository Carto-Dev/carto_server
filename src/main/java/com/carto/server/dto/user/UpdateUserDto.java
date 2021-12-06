package com.carto.server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

    @NotEmpty(message = "Display name should not be empty.")
    @Size(min = 5, message = "Display name should have at least 5 characters.")
    private String displayName;

}
