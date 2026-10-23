package com.desafio.accenture.qa.api.client;

import com.desafio.accenture.qa.config.TestConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public final class RestClient {

    private RestClient() {
    }

    public static RequestSpecification spec() {
        return given().spec(new RequestSpecBuilder()
                .setBaseUri(TestConfig.API_BASE_URI)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.URI)
                .build());
    }

    public static RequestSpecification authenticated(String token) {
        return spec().header("Authorization", "Bearer " + token);
    }
}
