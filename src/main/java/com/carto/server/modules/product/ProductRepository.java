package com.carto.server.modules.product;

import com.carto.server.model.CartoUser;
import com.carto.server.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductByIdAndUser(Long id, CartoUser cartoUser);

    Set<Product> findByOrderByCreatedAtDesc();

    Set<Product> findProductsByUser(CartoUser cartoUser);

    Set<Product> findProductsByIdIn(Set<Long> ids);

}
