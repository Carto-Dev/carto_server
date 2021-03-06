package com.carto.server.modules.product;

import com.carto.server.dto.product.DeleteProductDto;
import com.carto.server.dto.product.NewProductDto;
import com.carto.server.dto.product.UpdateProductDto;
import com.carto.server.exception.NotFoundException;
import com.carto.server.model.CartoUser;
import com.carto.server.model.Product;
import com.carto.server.model.ProductCategory;
import com.carto.server.model.ProductImage;
import com.carto.server.modules.search.SearchService;
import com.carto.server.modules.user.CartoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductCategoryService, ProductService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CartoUserRepository cartoUserRepository;

    private final SearchService searchService;

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
    public List<ProductCategory> fetchCategories() {
        return this.productCategoryRepository.findAll();
    }

    @Override
    public Product fetchProductById(Long id) throws NotFoundException {
        Optional<Product> productOptional = this.productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new NotFoundException(404, "Order Not Found");
        } else {
            return productOptional.get();
        }
    }

    @Override
    public Set<Product> fetchNewProducts() {
        return this.productRepository.findByOrderByCreatedAtDesc();
    }

    @Override
    public Set<Product> fetchProductsByUser(Long userId) throws NotFoundException {
        Optional<CartoUser> cartoUserOptional = this.cartoUserRepository.findById(userId);

        if (cartoUserOptional.isEmpty()) {
            throw new NotFoundException(404, "User not found");
        } else {
            return this.productRepository.findProductsByUser(cartoUserOptional.get());
        }
    }

    @Override
    public Set<Product> fetchProductsByCategory(String category) throws NotFoundException {
        ProductCategory productCategory = this.fetchCategory(category);

        return productCategory.getProducts();
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
                new HashSet<>(),
                null,
                productCategories,
                null,
                null
        );

        for (String imgLink : newProductDto.getImgLinks()) {
            ProductImage newProductImage = new ProductImage(null, imgLink, null);
            newProductImage.setProduct(product);

            product.getImgLinks().add(newProductImage);
        }

        Product savedProduct = this.productRepository.save(product);

        this.searchService.addOrUpdateProduct(savedProduct);

        return savedProduct;
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

        Product savedProduct = this.productRepository.save(oldProduct);

        this.searchService.addOrUpdateProduct(savedProduct);

        return savedProduct;
    }

    @Override
    public void deleteProduct(CartoUser cartoUser, DeleteProductDto deleteProductDto) throws NotFoundException {
        Product oldProduct = this.productRepository.findProductByIdAndUser(deleteProductDto.getId(), cartoUser);

        if (oldProduct == null) {
            throw new NotFoundException(404, "Product not found");
        }

        this.searchService.deleteProduct(oldProduct);

        this.productRepository.delete(oldProduct);
    }
}
