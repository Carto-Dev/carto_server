package com.carto.server.modules.search;

import com.carto.server.model.Product;
import com.carto.server.modelDtos.AlgoliaProductDto;
import com.carto.server.modules.algolia.AlgoliaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final AlgoliaService algoliaService;

    @Override
    public void addOrUpdateProduct(Product product) {
        AlgoliaProductDto algoliaProductDto = new AlgoliaProductDto()
                .setObjectID(product.getId().toString())
                .setTitle(product.getTitle())
                .setDescription(product.getDescription());

        this.algoliaService.getProductIndex().saveObject(algoliaProductDto);

    }
}
