package com.carto.server.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchProductsByUserDto {

    @NotNull(message = "User ID is required")
    private Long userId;

}
