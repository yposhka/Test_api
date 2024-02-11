package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class RequestFiles {
    private final static String URL = "http://85.192.34.140:8080";

    @Test
    public void getImage() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        Response response = given()
                .when()
                .get("/api/files/download");

        try {
            response.getBody().asInputStream().transferTo(new FileOutputStream("test.jpg"));
        } catch (IOException e) {
            System.err.println("Ошибка сохранения" + e.getMessage());
        }
    }

    @Test
    public void postImage() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        Response response = given()
                .contentType("multipart/form-data")
                .multiPart("file", new File("MyText.txt"))
                .when()
                .post(URL + "/api/files/upload");

        Assertions.assertNotNull(response.then().extract().jsonPath().get("info.message"));
        Assertions.assertEquals("success", response.then().extract().jsonPath().get("info.status").toString());
    }
}
