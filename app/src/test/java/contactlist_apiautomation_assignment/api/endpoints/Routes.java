package contactlist_apiautomation_assignment.api.endpoints;

public class Routes {
    public static String baseUrl ="https://thinking-tester-contact-list.herokuapp.com";

    //Contacts
    public static String addContacts = baseUrl+"/contacts";
    public static String getContactList = baseUrl+"/contacts";
    public static String getContact = baseUrl+"/contacts/";
    public static String updateContact = baseUrl+"/contacts/";
    public static String deleteContact = baseUrl+"/contacts/";


    //User
    public static String addUser = baseUrl+"/users";
    public static String getUserProfile = baseUrl+"/users/me";
    public static String updateUser = baseUrl+"/users/me";
    public static String logOutUser = baseUrl+"/users/logout";
    public static String loginUser = baseUrl+"/users/login";
    public static String deleteUser = baseUrl+"/users/me";




}
