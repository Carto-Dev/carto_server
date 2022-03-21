package com.carto.server.modules.product;

import com.carto.server.model.CartoUser;
import com.carto.server.model.Product;
import com.carto.server.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductByIdAndUser(Long id, CartoUser cartoUser);

    Set<Product> findByOrderByCreatedAtDesc();

    Set<Product> findProductsByUser(CartoUser cartoUser);

    Set<Product> findProductsByIdInOrderByCostDesc(Set<Long> ids);

    Set<Product> findProductsByIdInOrderByCostAsc(Set<Long> ids);

    Set<Product> findProductsByCategoriesInAndOrderByCostAsc(Set<ProductCategory> categories);

    Set<Product> findProductsByCategoriesInAndOrderByCostDesc(Set<ProductCategory> categories);

    Set<Product> findProductsByIdInAndCategoriesInOrderByCostDesc(Set<Long> id, Set<ProductCategory> categories);

    Set<Product> findProductsByIdInAndCategoriesInOrderByCostAsc(Collection<Long> id, Set<ProductCategory> categories);

}
