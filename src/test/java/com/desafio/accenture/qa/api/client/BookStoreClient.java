package com.desafio.accenture.qa.api.client;

import com.desafio.accenture.qa.api.models.AddBooksRequest;
import io.restassured.response.Response;

public class BookStoreClient {

    public Response listBooks() {
        return RestClient.spec()
                .when()
                .get("/BookStore/v1/Books");
    }

    public Response addBooks(AddBooksRequest request, String token) {
        return RestClient.authenticated(token)
                .body(request)
                .when()
                .post("/BookStore/v1/Books");
    }
}
