package com.carto.server.modules.product;

import com.carto.server.dto.product.NewProductDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Product;

public interface ProductService {

    Product createProduct(CartoUser cartoUser, NewProductDto newProductDto) throws NotFoundException;

}
