package com.carto.server.modules.search;

import com.carto.server.dto.search.SearchDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.Product;

import java.util.Set;

public interface SearchService {

    Set<Product> searchForProducts(SearchDto searchDto) throws NotFoundException;

    void addOrUpdateProduct(Product product);

    void deleteProduct(Product product);

}
