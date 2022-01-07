package com.carto.server.modules.product;

import com.carto.server.dto.product.NewProductDto;
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
@RequiredArgsConstructor
@Transactional
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
        Set<ProductImage> productImages = new HashSet<>();

        for (String category : newProductDto.getCategories()) {
            productCategories.add(this.fetchCategory(category));
        }

        for (String imgLink : newProductDto.getImgLinks()) {
            ProductImage productImage = this.productImageRepository.save(
                    new ProductImage(null, imgLink, null)
            );

            productImages.add(productImage);
        }

        Product product = new Product(
                null,
                cartoUser,
                newProductDto.getTitle(),
                newProductDto.getDescription(),
                newProductDto.getCost(),
                productImages,
                productCategories,
                null,
                null
        );

        return this.productRepository.save(product);
    }
}
