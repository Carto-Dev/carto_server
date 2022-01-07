package com.carto.server.modules.product;

import com.carto.server.dto.product.DeleteProductDto;
import com.carto.server.dto.product.NewProductDto;
import com.carto.server.dto.product.UpdateProductDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Product;

import java.util.Set;

public interface ProductService {

    Set<Product> fetchNewProducts();

    Product createProduct(CartoUser cartoUser, NewProductDto newProductDto) throws NotFoundException;

    Product updateProduct(CartoUser cartoUser, UpdateProductDto updateProductDto) throws NotFoundException;

    void deleteProduct(CartoUser cartoUser, DeleteProductDto deleteProductDto) throws NotFoundException;

}
