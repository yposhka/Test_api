package api;

import dto.game.Root;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RequestGame {
    private final static String URL = "http://85.192.34.140:8080";
    private final static int gameId = 1;

    private int size;

    @Test
    public void getAllGame() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        Response response = given()
                .header("Authorization", "Bearer " + new RequestUser().getToken())
                .when()
                .get(URL + "/api/user/games");

        size = response.then().extract().jsonPath().getList("$").size();
    }

    @Test
    public void postAddGame() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification201()
        );

        Response response = given()
                .header("Authorization", "Bearer " + new RequestUser().getToken())
                .contentType("application/json")
                .body(new Root(gameId))
                .when()
                .post(URL + "/api/user/games");
    }

    @Test
    public void getAllGamePlusOne() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL),
                Specifications.responseSpecification200()
        );

        Response response = given()
                .header("Authorization", "Bearer " + new RequestUser().getToken())
                .when()
                .get(URL + "/api/user/games");

        Assertions.assertEquals(size++, response.then().extract().jsonPath().getList("$").size());
    }

    @Test
    public void deleteGame() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        Response response = given()
                .header("Authorization", "Bearer " + new RequestUser().getToken())
                .when()
                .delete(URL + "/api/user/games/" + gameId);

        Assertions.assertNotNull(response.then().extract().jsonPath().get("info.message"));
        Assertions.assertEquals("success", response.then().extract().jsonPath().get("info.status").toString());
    }
}
