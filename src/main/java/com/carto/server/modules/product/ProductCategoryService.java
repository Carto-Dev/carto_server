package com.carto.server.modules.product;

import com.carto.server.model.ProductCategory;

import java.util.Set;

public interface ProductCategoryService {

    void loadCategories(Set<ProductCategory> categories);

}