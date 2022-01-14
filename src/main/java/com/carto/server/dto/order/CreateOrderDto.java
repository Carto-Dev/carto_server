package com.carto.server.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {

    @Valid
    @NotEmpty(message = "Orders cannot be empty")
    Set<CreateOrderItemDto> orders;

}
