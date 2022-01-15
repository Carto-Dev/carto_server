package com.carto.server.modules.search;

import com.carto.server.model.Product;

import java.util.Set;

public interface SearchService {

    Set<Product> searchForProducts(String query);

    void addOrUpdateProduct(com.carto.server.model.Product product);

    void deleteProduct(com.carto.server.model.Product product);

}
