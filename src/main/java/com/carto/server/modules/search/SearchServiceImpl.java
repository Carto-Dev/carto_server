package com.carto.server.modules.search;

import com.algolia.search.models.indexing.Query;
import com.algolia.search.models.indexing.SearchResult;
import com.carto.server.model.Product;
import com.carto.server.modelDtos.AlgoliaProductDto;
import com.carto.server.modules.algolia.AlgoliaService;
import com.carto.server.modules.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SearchServiceImpl implements SearchService {

    private final AlgoliaService algoliaService;
    private final ProductRepository productRepository;

    @Override
    public Set<Product> searchForProducts(String query) {
        log.info("Searching For Products: " + query + " in Algolia");

        SearchResult<AlgoliaProductDto> searchResult = this
                .algoliaService
                .getProductIndex()
                .search(new Query(query));

        Set<AlgoliaProductDto> algoliaProductDtos = new HashSet<>(searchResult.getHits());

        Set<Long> productIds = algoliaProductDtos
                .stream()
                .map(algoliaProductDto -> Long.parseLong(algoliaProductDto.getObjectID()))
                .collect(Collectors.toSet());

        return this.productRepository.findProductsByIdIn(productIds);
    }

    @Override
    public void addOrUpdateProduct(com.carto.server.model.Product product) {
        AlgoliaProductDto algoliaProductDto = new AlgoliaProductDto()
                .setObjectID(product.getId().toString())
                .setTitle(product.getTitle())
                .setDescription(product.getDescription());

        this.algoliaService.getProductIndex().saveObject(algoliaProductDto);

        log.info("Added/Updated Product:" + product.getId().toString() + " to Algolia");

    }

    @Override
    public void deleteProduct(com.carto.server.model.Product product) {
        this.algoliaService.getProductIndex().deleteObject(product.getId().toString());
        log.info("Deleted Product:" + product.getId().toString() + " from Algolia");

    }
}
