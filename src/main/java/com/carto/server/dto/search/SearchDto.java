package com.carto.server.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    @NotNull(message = "Query is required")
    @NotEmpty(message = "Query is required")
    private String query;

    @NotNull(message = "SortBy is required")
    @NotEmpty(message = "SortBy is required")
    private String sortBy;

    @NotNull(message = "Categories is required")
    @NotEmpty(message = "Categories is required")
    private List<String> categories;
}
