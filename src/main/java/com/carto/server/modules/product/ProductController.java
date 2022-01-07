package com.carto.server.modules.product;

import com.carto.server.annotation.LoggedInUser;
import com.carto.server.dto.product.DeleteProductDto;
import com.carto.server.dto.product.NewProductDto;
import com.carto.server.dto.product.UpdateProductDto;
import com.carto.server.modelDtos.ProductDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "category")
    Set<ProductDto> fetchProductsByCategory(@Valid @RequestParam(name = "category") String category) throws NotFoundException {
        Set<Product> products = this.productService.fetchProductsByCategory(category);

        return products.stream().map(product -> {
            ProductDto productDto = new ProductDto();
            productDto.convertToDto(product);

            return productDto;
        }).collect(Collectors.toSet());
    }

    @GetMapping(path = "user")
    Set<ProductDto> fetchProductsByUser(@Valid @RequestParam(name = "userId") Long userId) throws NotFoundException {
        Set<Product> products = this.productService.fetchProductsByUser(userId);

        return products.stream().map(product -> {
            ProductDto productDto = new ProductDto();
            productDto.convertToDto(product);

            return productDto;
        }).collect(Collectors.toSet());
    }

    @GetMapping(path = "new")
    Set<ProductDto> fetchNewProducts() {
        Set<Product> products = this.productService.fetchNewProducts();

        return products.stream().map(product -> {
            ProductDto productDto = new ProductDto();
            productDto.convertToDto(product);

            return productDto;
        }).collect(Collectors.toSet());
    }

    @PostMapping
    public ProductDto createProduct(@LoggedInUser CartoUser cartoUser, @Valid @RequestBody NewProductDto newProductDto) throws NotFoundException {
        Product newProduct = this.productService.createProduct(cartoUser, newProductDto);

        ProductDto productDto = new ProductDto();
        productDto.convertToDto(newProduct);

        return productDto;
    }

    @PutMapping
    public ProductDto updateProduct(@LoggedInUser CartoUser cartoUser, @Valid @RequestBody UpdateProductDto updateProductDto) throws NotFoundException {
        Product product = this.productService.updateProduct(cartoUser, updateProductDto);

        ProductDto productDto = new ProductDto();
        productDto.convertToDto(product);

        return productDto;
    }

    @DeleteMapping
    public void deleteProduct(@LoggedInUser CartoUser cartoUser, @Valid @RequestBody DeleteProductDto deleteProductDto) throws NotFoundException {
        this.productService.deleteProduct(cartoUser, deleteProductDto);
    }
}
