package com.carto.server.modules.product;

import com.carto.server.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    ProductCategory findByKey(String key);

}
