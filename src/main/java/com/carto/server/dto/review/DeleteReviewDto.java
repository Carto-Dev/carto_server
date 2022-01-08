package com.carto.server.dto.review;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteReviewDto {
    @NotNull(message = "Review ID is required")
    private Long id;
}
