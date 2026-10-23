package com.desafio.accenture.qa.api.client;

import com.desafio.accenture.qa.api.models.UserRequest;
import io.restassured.response.Response;

public class AccountClient {

    public Response createUser(UserRequest request) {
        return RestClient.spec()
                .body(request)
                .when()
                .post("/Account/v1/User");
    }

    public Response generateToken(UserRequest request) {
        return RestClient.spec()
                .body(request)
                .when()
                .post("/Account/v1/GenerateToken");
    }

    public Response isAuthorized(UserRequest request) {
        return RestClient.spec()
                .body(request)
                .when()
                .post("/Account/v1/Authorized");
    }

    public Response getUser(String userId, String token) {
        return RestClient.authenticated(token)
                .when()
                .get("/Account/v1/User/{userId}", userId);
    }
}
