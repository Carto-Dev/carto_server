package com.carto.server.modules.product;

import com.carto.server.dto.product.NewProductDto;
import com.carto.server.dto.product.UpdateProductDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Product;
import com.carto.server.model.ProductCategory;
import com.carto.server.model.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductCategoryService, ProductService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Override
    public void loadCategories(Set<ProductCategory> categories) {

        categories.forEach(category -> {
            ProductCategory checkCategory = productCategoryRepository.findByKey(category.getKey());

            if (checkCategory == null) {
                this.productCategoryRepository.save(category);
            }
        });

    }

    @Override
    public ProductCategory fetchCategory(String categoryKey) throws NotFoundException {
        ProductCategory checkCategory = productCategoryRepository.findByKey(categoryKey);

        if (checkCategory == null) {
            throw new NotFoundException(404, "Category not found");
        } else {
            return checkCategory;
        }
    }

    @Override
    public Product createProduct(CartoUser cartoUser, NewProductDto newProductDto) throws NotFoundException {

        Set<ProductCategory> productCategories = new HashSet<>();

        for (String category : newProductDto.getCategories()) {
            productCategories.add(this.fetchCategory(category));
        }

        Product product = new Product(
                null,
                cartoUser,
                newProductDto.getTitle(),
                newProductDto.getDescription(),
                newProductDto.getCost().doubleValue(),
                new HashSet<>(),
                productCategories,
                null,
                null
        );

        for (String imgLink : newProductDto.getImgLinks()) {
            ProductImage newProductImage = new ProductImage(null, imgLink, null);
            newProductImage.setProduct(product);

            product.getImgLinks().add(newProductImage);
        }

        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(CartoUser cartoUser, UpdateProductDto updateProductDto) throws NotFoundException {
        Product oldProduct = this.productRepository.findProductByIdAndUser(updateProductDto.getId(), cartoUser);

        if (oldProduct == null) {
            throw new NotFoundException(404, "Product not found");
        }

        Set<ProductImage> oldProductImages = this.productImageRepository.findAllByProduct(oldProduct);
        this.productImageRepository.deleteAll(oldProductImages);

        Set<ProductCategory> productCategories = new HashSet<>();
        Set<ProductImage> productImages = new HashSet<>();

        for (String category : updateProductDto.getCategories()) {
            productCategories.add(this.fetchCategory(category));
        }

        for (String imgLink : updateProductDto.getImgLinks()) {
            ProductImage newProductImage = new ProductImage(null, imgLink, null);
            newProductImage.setProduct(oldProduct);

            productImages.add(newProductImage);
        }

        oldProduct.setTitle(updateProductDto.getTitle());
        oldProduct.setDescription(updateProductDto.getDescription());
        oldProduct.setCost(updateProductDto.getCost().doubleValue());
        oldProduct.setImgLinks(productImages);
        oldProduct.setCategories(productCategories);

        return this.productRepository.save(oldProduct);
    }
}
