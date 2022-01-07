package com.carto.server.modules.product;

import com.carto.server.exception.NotFoundException;
import com.carto.server.model.ProductCategory;

import java.util.List;
import java.util.Set;

public interface ProductCategoryService {

    void loadCategories(Set<ProductCategory> categories);

    ProductCategory fetchCategory(String categoryKey) throws NotFoundException;

    List<ProductCategory> fetchCategories();

}
