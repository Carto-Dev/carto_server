package com.carto.server.dto.product;

import com.carto.server.annotation.MinArraySize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewProductDto {

    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title is required")
    @Size(min = 5, message = "Title should have at least 5 characters.")
    private String title;

    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is required")
    @Size(min = 5, message = "Description should have at least 5 characters.")
    private String description;

    @NotNull(message = "Cost is required")
    @Range(min = 0)
    private BigDecimal cost;

    @NotNull(message = "Images is required")
    @NotEmpty(message = "Images is required")
    @MinArraySize(message = "Please upload at least one image.")
    private List<String> imgLinks;

    @NotNull(message = "Categories is required")
    @NotEmpty(message = "Categories is required")
    @MinArraySize(message = "Please select at least one category.")
    private List<String> categories;

}
