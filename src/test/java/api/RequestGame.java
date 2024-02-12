package api;

import dto.game.Game;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RequestGame {
    private final static String URL = "http://85.192.34.140:8080";
    private final static int gameId = 1;

    private RequestUser requestUser = new RequestUser();

    @Test
    public void getAllGame() {

        Response response = given()
                .header("Authorization", "Bearer " + requestUser.getToken("login","root"))
                .when()
                .get(URL + "/api/user/games");

        Response response1 = given()
                .header("Authorization", "Bearer " + requestUser.getToken("login","root"))
                .contentType("application/json")
                .body(new Game().getGame())
                .when()
                .post(URL + "/api/user/games");

        Response response2 = given()
                .header("Authorization", "Bearer " + requestUser.getToken("login","root"))
                .when()
                .get(URL + "/api/user/games");

        int size = response.then().extract().jsonPath().getList("$").size();
        size++;
        Assertions.assertEquals(size, response2.then().extract().jsonPath().getList("$").size());
    }

    @Test
    public void deleteGame() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        Response response = given()
                .header("Authorization", "Bearer " + requestUser.getToken("login","root"))
                .when()
                .delete(URL + "/api/user/games/" + gameId);

        Assertions.assertNotNull(response.then().extract().jsonPath().get("info.message"));
        Assertions.assertEquals("success", response.then().extract().jsonPath().get("info.status").toString());
    }
}
