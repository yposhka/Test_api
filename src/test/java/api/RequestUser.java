package api;

import dto.User;
import dto.UserReg;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class RequestUser {
    private final static String URL = "http://85.192.34.140:8080";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new Random();

    private String login;
    private String newPassword = "twsqss";

    public static String generateRandomString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

    @Test
    public void postCreateUser() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification201()
        );

        login = generateRandomString();
        UserReg userReg = new UserReg(login, "root");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(userReg)
                .when()
                .post(URL + "/api/signup");

        Assertions.assertEquals(
                userReg.getLogin(), response.then().extract().jsonPath().getString("register_data.login")
        );
        Assertions.assertEquals(
                userReg.getPass(), response.then().extract().jsonPath().getString("register_data.pass")
        );

        Assertions.assertNotNull(response.then().extract().jsonPath().get("register_data.id"));
        Assertions.assertNotNull(response.then().extract().jsonPath().get("info.message"));
        Assertions.assertNotNull(response.then().extract().jsonPath().get("info.status"));
    }

    @Test
    public String getToken() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        User user = new User("root", login);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(URL + "/api/login");

        return response.then().extract().jsonPath().get("token").toString();
    }

    @Test
    public void putPassword() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        String requestBody = "{\"password\": \""+newPassword+"\"}";

        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put(URL + "/api/user");

        Assertions.assertNotNull(response.then().extract().jsonPath().get("info.message"));
        Assertions.assertEquals("success", response.then().extract().jsonPath().get("info.status").toString());
    }

    @Test
    public void getUserInfo() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .when()
                .get(URL + "/api/user");

        Assertions.assertEquals(newPassword, response.then().extract().jsonPath().get("pass").toString());
    }

    @Test
    public void deleteUser() {
        Specifications.installSpecification(
                Specifications.requestSpecification(URL), Specifications.responseSpecification200()
        );

        Response response = given()
                .header("Authorization", "Bearer " + getToken())
                .when()
                .delete(URL + "/api/user");

        Assertions.assertNotNull(response.then().extract().jsonPath().get("info.message"));
        Assertions.assertEquals("success", response.then().extract().jsonPath().get("info.status").toString());
    }
}
