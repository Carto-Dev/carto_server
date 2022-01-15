package com.carto.server.modelDtos;

public class AlgoliaProductDto {

    private String objectID;
    private String title;
    private String description;

    public String getObjectID() {
        return objectID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public AlgoliaProductDto setObjectID(String objectID) {
        this.objectID = objectID;
        return this;
    }

    public AlgoliaProductDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public AlgoliaProductDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
