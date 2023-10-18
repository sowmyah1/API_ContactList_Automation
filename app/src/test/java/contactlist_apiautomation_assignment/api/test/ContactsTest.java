package contactlist_apiautomation_assignment.api.test;

import contactlist_apiautomation_assignment.api.endpoints.ContactsEndPoints;
import contactlist_apiautomation_assignment.api.endpoints.UserEndPoints;
import contactlist_apiautomation_assignment.api.payload.Contacts;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import contactlist_apiautomation_assignment.api.test.UserTest;


public class ContactsTest {
    Faker faker;
    Contacts contactsPayload;
    String userToken;
    String contactId;
    @BeforeClass
    public void setup(){
        UserTest userTest = new UserTest();

        faker = new Faker();
        contactsPayload= new Contacts();
        userTest.setUpData();
        userTest.testPostUser();
        userToken= userTest.getToken();

        contactsPayload.setFirstName(faker.name().firstName());
        contactsPayload.setLastName(faker.name().lastName());
        contactsPayload.setCity(faker.address().cityName());
        contactsPayload.setCountry(faker.address().country());
        contactsPayload.setEmail(faker.internet().emailAddress());

    }
    @Test(priority = 1,groups = "smoke")
    public void testAddContacts(){

            Response response = ContactsEndPoints.addContacts(contactsPayload,userToken);
            contactId = response.jsonPath().getString("_id");
            response.then().log().all();

            Assert.assertEquals(response.getStatusCode(),201,"User not created");

    }
    @Test(priority = 2,groups = "sanity")
    public void testReadContactList(){
        Response response = ContactsEndPoints.readContactList(userToken);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200,"Cannot retrieve contact List");
    }
    @Test(priority = 3,groups = "sanity")
    public void testReadContacts(){
        Response response = ContactsEndPoints.readContact(userToken);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200,"Cannot get contact details");
    }
    @Test(priority = 4,groups = "regression")
    public void testUpdateContact(){

        contactsPayload.setFirstName(faker.name().firstName());
        contactsPayload.setLastName(faker.name().lastName());
        contactsPayload.setCity(faker.address().cityName());
        contactsPayload.getBirthdate();
        contactsPayload.setCountry(faker.address().country());
        contactsPayload.setEmail(faker.internet().emailAddress());
        contactsPayload.getPhone();
        contactsPayload.getPostalCode();
        contactsPayload.getStreet1();
        contactsPayload.getStreet2();
        contactsPayload.getStateProvince();
        Response response = ContactsEndPoints.updateContact(contactsPayload,userToken,contactId);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200," user details Cannot be update");

        Response responseAfterUpdate = ContactsEndPoints.readContact(userToken);

        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200,"User details are not updated");
    }

    @Test(priority = 5,groups = "regression")
    public void testUpdateContactFirstName(){
        contactsPayload.setFirstName(faker.name().firstName());
        Response response = ContactsEndPoints.updateContactFirstName(contactsPayload,userToken,contactId);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200,"FirstName cannot be updated");

        Response responseAfterUpdateFirstName = ContactsEndPoints.readContact(userToken);
        responseAfterUpdateFirstName.then().log().all();

        Assert.assertEquals(responseAfterUpdateFirstName.getStatusCode(),200,"First name cannot be updated");
    }
    @Test(priority = 6,groups = "smoke")
    public void testdeleteContacts(){
        Response response = ContactsEndPoints.deleteContacts(userToken,contactId);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200,"Contact details cannot be deleted");
    }




}
