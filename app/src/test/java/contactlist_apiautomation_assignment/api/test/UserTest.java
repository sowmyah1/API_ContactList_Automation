package contactlist_apiautomation_assignment.api.test;

import com.github.javafaker.Faker;
import contactlist_apiautomation_assignment.api.endpoints.UserEndPoints;
import contactlist_apiautomation_assignment.api.payload.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {
        Faker faker;
        User userPayload;
        String token;
        String id;
    @BeforeClass
    public void setUpData(){
        faker = new Faker();
        userPayload = new User();

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password());
    }
    @Test(priority = 1)
    public void testPostUser(){
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        token =response.jsonPath().getString("token");
        id = response.jsonPath().getString("user.firstName");
        System.out.println(token);
        System.out.println(id);
        Assert.assertEquals(response.getStatusCode(),201,"User not created");
    }
    @Test(priority = 2)
    public void testGetUser(){
        Response response= UserEndPoints.readUser(token);
        response.then().log().all();
        String id1 = response.jsonPath().getString("firstName");
        Assert.assertEquals(response.getStatusCode(),200,"Cannot access user details");
        Assert.assertEquals(id1,id,"user did not match");
    }
}
