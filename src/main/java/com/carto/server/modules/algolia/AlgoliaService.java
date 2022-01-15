package com.carto.server.modules.algolia;

import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.carto.server.modelDtos.AlgoliaProductDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Getter
@Setter
@Slf4j
public class AlgoliaService {

    private SearchClient searchClient;
    private SearchIndex<AlgoliaProductDto> productIndex;

    @PostConstruct
    public void initializeAlgolia() {
        String algoliaApplicationId = System.getenv("ALGOLIA_APPLICATION_ID");
        String algoliaAdminKey = System.getenv("ALGOLIA_ADMIN_KEY");

        this.searchClient = DefaultSearchClient.create(algoliaApplicationId, algoliaAdminKey);

        log.info("Algolia Search Client Initialized");

        this.productIndex = this.searchClient.initIndex("products", AlgoliaProductDto.class);

        log.info("Algolia Search Index Initialized");

    }

}
