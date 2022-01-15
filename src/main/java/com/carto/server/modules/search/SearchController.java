package com.carto.server.modules.search;

import com.carto.server.model.Product;
import com.carto.server.modelDtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    Set<ProductDto> searchForProducts(@Valid @RequestParam(name = "query") String query) {
        Set<Product> products = this.searchService.searchForProducts(query);

        return products.stream().map(product -> {
            ProductDto productDto = new ProductDto();
            productDto.convertToDto(product);

            return productDto;
        }).collect(Collectors.toSet());
    }

}
