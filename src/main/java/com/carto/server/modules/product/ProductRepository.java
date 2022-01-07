package com.carto.server.modules.product;

import com.carto.server.model.CartoUser;
import com.carto.server.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductByIdAndUser(Long id, CartoUser cartoUser);

}
