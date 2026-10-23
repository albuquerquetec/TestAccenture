package com.desafio.accenture.qa.api.models;

import java.util.List;

public class AddBooksRequest {

    private String userId;
    private List<IsbnItem> collectionOfIsbns;

    public AddBooksRequest() {
    }

    public AddBooksRequest(String userId, List<IsbnItem> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<IsbnItem> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

    public void setCollectionOfIsbns(List<IsbnItem> collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }
}
