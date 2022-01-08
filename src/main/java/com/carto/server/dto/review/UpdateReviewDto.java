package com.carto.server.dto.review;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateReviewDto {

    @NotNull(message = "Review ID is required")
    private Long id;

    @NotNull(message = "Review is required")
    @NotEmpty(message = "Review is required")
    @Size(min = 5, message = "Review should have at least 5 characters.")
    private String text;

    @NotNull(message = "Stars is required")
    @Range(min = 0, max = 5)
    private Long stars;


}
