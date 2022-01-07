package com.carto.server.modules.product;

import com.carto.server.modelDtos.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequiredArgsConstructor
@RequestMapping(path = "v1/category")
public class CategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping
    public List<CategoryDto> fetchCategories() {
        return this.productCategoryService.fetchCategories().stream().map(productCategory -> {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.convertToDto(productCategory);

            return categoryDto;
        }).collect(Collectors.toList());
    }

}
