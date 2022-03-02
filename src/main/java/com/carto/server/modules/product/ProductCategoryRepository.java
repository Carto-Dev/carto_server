package com.carto.server.modules.product;

import com.carto.server.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    ProductCategory findByKey(String key);

    Set<ProductCategory> findAllByKeyIn(Set<String> key);

}
