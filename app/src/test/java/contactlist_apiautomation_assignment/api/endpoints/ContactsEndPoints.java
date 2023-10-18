package contactlist_apiautomation_assignment.api.endpoints;

import contactlist_apiautomation_assignment.api.payload.Contacts;
import contactlist_apiautomation_assignment.api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ContactsEndPoints {
    public static Response addContacts(Contacts Payload, String token){
        Response response = given()
                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Payload)
                .when()
                .post(Routes.addContacts);

        return response;

    }
    public static Response readContactList(String token){
        Response response =given()
                .header("Authorization","Bearer "+token)
                .when()
                .get(Routes.getContactList);

        return response;

    }
    public static Response readContact(String token){
        Response response =given()
                .header("Authorization","Bearer "+token)
                .when()
                .get(Routes.getContactList);

        return response;

    }
    public static Response updateContact(Contacts Payload,String token,String id){
        Response response = given()
                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Payload)
                .when()
                .put(Routes.updateContact+id);

        return response;

    }
    public static Response updateContactFirstName(Contacts Payload,String token,String id){
        Response response = given()
                .header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(Payload)
                .when()
                .patch(Routes.updateContact+id);

        return response;

    }
    public static Response deleteContacts(String token,String id){
        Response response = given()
                .header("Authorization","Bearer "+token)
                .when()
                .delete(Routes.deleteContact+id);
        return response;
    }
}
