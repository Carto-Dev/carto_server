package com.carto.server.modules.search;

import com.carto.server.dto.search.SearchDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.Product;
import com.carto.server.modelDtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path = "v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    List<ProductDto> searchForProducts(@Valid SearchDto searchDto) throws NotFoundException {
        Set<Product> products = this.searchService.searchForProducts(searchDto);
        List<ProductDto> productDtoLinkedList = new LinkedList<>();

        products.forEach(product -> {
            ProductDto productDto = new ProductDto();
            productDto.convertToDto(product);

            productDtoLinkedList.add(productDto);
        });

        return productDtoLinkedList;
    }

}
