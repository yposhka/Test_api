package api;

import dto.User;
import dto.UserReg;
import dto.game.Root;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class CheckRequest {
    private final static String URL = "http://85.192.34.140:8080";

    private int size;
    private final int gameId = 1;
    private String token;

    @Test
    public void checkGetRequestSuccess() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        Response response = given()
                .when()
                .get(URL + "/api/users");
    }

    @Test
    public void checkGetRequestUnSuccess() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification400()
        );

        Response response = given()
                .when()
                .get(URL + "/api/bad-request");
    }

    @Test
    public void checkPostCreateUser() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification201()
        );

        UserReg userReg = new UserReg("login", "root");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(userReg)
                .when()
                .post(URL + "/api/signup");

        Assertions.assertEquals(userReg.getLogin(), response.then().extract().jsonPath().getString("register_data.login"));
        Assertions.assertEquals(userReg.getPass(), response.then().extract().jsonPath().getString("register_data.pass"));

        Assertions.assertNotNull(response.then().extract().jsonPath().get("register_data.id"));
        Assertions.assertNotNull(response.then().extract().jsonPath().get("info.message"));
        Assertions.assertNotNull(response.then().extract().jsonPath().get("info.status"));
    }


    @Test
    public void checkGetToken() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        User user = new User("root", "login");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(URL + "/api/login");

        token = response.then().extract().jsonPath().get("token").toString();
    }

    @Test
    public void checkGetAllGame() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL + "/api/user/games");

        size = response.then().extract().jsonPath().getList("$").size();
    }

    @Test
    public void checkPostAddGame() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification201()
        );

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(new Root(gameId))
                .when()
                .post(URL + "/api/user/games");
    }

    @Test
    public void checkGetAllGamePlusOne() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL),
                Specifications.responseSpecification200()
        );

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL + "/api/user/games");

        Assertions.assertEquals(size++, response.then().extract().jsonPath().getList("$").size());
    }

    @Test
    public void checkDeleteGame() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(URL + "/api/user/games/" + gameId);

        Assertions.assertNotNull(response.then().extract().jsonPath().get("info.message"));
        Assertions.assertEquals("success", response.then().extract().jsonPath().get("info.status").toString());
    }

    @Test
    public void checkPutPassword() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        String requestBody = "{\"password\": \"twsqss\"}";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put(URL + "/api/user");

        Assertions.assertNotNull(response.then().extract().jsonPath().get("info.message"));
        Assertions.assertEquals("success", response.then().extract().jsonPath().get("info.status").toString());
    }

    @Test
    public void checkUserInfo() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        String password = "twsqss";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(URL + "/api/user");

        Assertions.assertEquals(password, response.then().extract().jsonPath().get("pass").toString());
    }

    @Test
    public void checkDeleteUser() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(URL + "/api/user");

        Assertions.assertNotNull(response.then().extract().jsonPath().get("info.message"));
        Assertions.assertEquals("success", response.then().extract().jsonPath().get("info.status").toString());
    }

    @Test
    public void checkGetImage() {
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
    public void checkPostImage() {
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
