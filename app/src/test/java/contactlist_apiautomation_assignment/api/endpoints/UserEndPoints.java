package contactlist_apiautomation_assignment.api.endpoints;

import contactlist_apiautomation_assignment.api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UserEndPoints {
    public static Response createUser(User Payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Payload)
                .when()
                .post(Routes.addUser);

                return response;

    }
    public static Response readUser(String token){
        Response response =given()
                .header("Authorization","Bearer "+token)
                .when()
                .get(Routes.getUserProfile);

        return response;

    }
    public static Response updateUser(User Payload,String token){
        Response response = given()
                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Payload)
                .when()
                .patch(Routes.updateUser);

        return response;

    }
    public static Response loginUser(User Payload,String token){
        Response response = given()
                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Payload)
                .when()
                .post(Routes.loginUser);

        return response;

    }

    public static Response logoutUser(String token){
        Response response =given()
                .header("Authorization","Bearer "+token)
                                  .contentType(ContentType.JSON)
                          .when()
                                  .post(Routes.logOutUser);

        return response;

    }
    public static Response deleteUser(String token){
        Response response =given()
                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .when()
                .delete(Routes.deleteUser);

        return response;

    }

}
