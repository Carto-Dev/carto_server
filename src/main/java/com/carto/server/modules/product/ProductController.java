package com.carto.server.modules.product;

import com.carto.server.annotation.LoggedInUser;
import com.carto.server.dto.product.NewProductDto;
import com.carto.server.modelDtos.ProductDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDto createProduct(@LoggedInUser CartoUser cartoUser, @Valid @RequestBody NewProductDto newProductDto) throws NotFoundException {
        Product newProduct = this.productService.createProduct(cartoUser, newProductDto);

        ProductDto productDto = new ProductDto();
        productDto.convertToDto(newProduct);

        return productDto;
    }

}
