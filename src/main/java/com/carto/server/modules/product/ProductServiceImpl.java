package com.carto.server.modules.product;

import com.carto.server.model.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public void loadCategories(Set<ProductCategory> categories) {

        categories.forEach(category -> {
            ProductCategory checkCategory = productCategoryRepository.findByKey(category.getKey());

            if (checkCategory == null) {
                this.productCategoryRepository.save(category);
            }
        });

    }
}
