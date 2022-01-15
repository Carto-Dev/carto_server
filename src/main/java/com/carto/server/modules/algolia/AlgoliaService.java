package com.carto.server.modules.algolia;

import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class AlgoliaService {

    SearchClient searchClient;

    @PostConstruct
    public void initializeAlgolia() {
        String algoliaApplicationId = System.getenv("ALGOLIA_APPLICATION_ID");
        String algoliaAdminKey = System.getenv("ALGOLIA_ADMIN_KEY");

        this.searchClient = DefaultSearchClient.create(algoliaApplicationId, algoliaAdminKey);

        log.info("Algolia Search Client Initialized");
    }

}
