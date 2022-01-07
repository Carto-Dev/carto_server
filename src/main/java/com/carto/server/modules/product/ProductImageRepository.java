package com.carto.server.modules.product;

import com.carto.server.model.Product;
import com.carto.server.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    Set<ProductImage> findAllByProduct(Product product);

}
