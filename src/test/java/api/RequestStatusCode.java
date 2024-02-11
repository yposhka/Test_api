package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RequestStatusCode {
    private final static String URL = "http://85.192.34.140:8080";

    @Test
    public void getRequestSuccess() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        Response response = given()
                .when()
                .get(URL + "/api/users");
    }

    @Test
    public void getRequestUnSuccess400() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification400()
        );

        Response response = given()
                .when()
                .get(URL + "/api/bad-request");
    }
}
