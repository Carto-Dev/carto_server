package com.carto.server.modules.search;

import com.carto.server.model.Product;
import com.carto.server.modelDtos.AlgoliaProductDto;

public interface SearchService {

    AlgoliaProductDto addProduct(Product product);

}
