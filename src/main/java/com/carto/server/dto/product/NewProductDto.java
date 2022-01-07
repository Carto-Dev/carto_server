package com.carto.server.dto.product;

import com.carto.server.annotation.MinArraySize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewProductDto {

    @NotEmpty(message = "Title is required")
    @Size(min = 5, message = "Title should have at least 5 characters.")
    private String title;

    @NotEmpty(message = "Description is required")
    @Size(min = 5, message = "Description should have at least 5 characters.")
    private String description;

    @NotEmpty(message = "Cost is required")
    private double cost;

    @NotEmpty(message = "Images is required")
    @MinArraySize(message = "Please upload at least one image.")
    private List<String> imgLinks;

    @NotEmpty(message = "Categories is required")
    @MinArraySize(message = "Please select at least one category.")
    private List<String> categories;

}
