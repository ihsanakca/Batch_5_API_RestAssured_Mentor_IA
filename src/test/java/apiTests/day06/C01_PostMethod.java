package apiTests.day06;
import apiPOJOTemplates.kraft.KraftNewUser;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.error.Mark;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class C01_PostMethod {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postWithString_1(){
        /**
         * //TASK
         * post method
         * baseUrl = https://www.krafttechexlab.com/sw/api/v1
         * endpoint = /allusers/register
         * Given accept type and Content type is JSON
         * And request json body is:
         {
         "name": "Mark Ruffalo",
         "email": "mark@mark.com",
         "password": "Test1234",
         "about": "from USA",
         "terms": "3"
         }
         * When user sends POST request
         * Then status code 200
         * And content type should be application/json; charset=UTF-8
         * And json payload/response/body should contain:
         * a new generated id that is special for user
         * verify name and email
         * verify that response body contains token
         * get the token and assign it to a variable and print it
         */

        Faker faker = new Faker();
        String fullName = faker.name().fullName();
        String emailAddress = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16, true, true);

        String newUserBody = "{\n" +
                "         \"name\": \"" +fullName+"\",\n" +
                "         \"email\": \""+emailAddress+"\",\n" +
                "         \"password\": \""+password+"\",\n" +
                "         \"about\": \"from USA\",\n" +
                "         \"terms\": \"3\"\n" +
                "         }";

        Response response = given().accept(ContentType.JSON)
                .body(newUserBody)
                .when().log().all()
                .post("/allusers/register")
                .prettyPeek();


        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");
        assertNotNull(response.path("id"));
        assertEquals(response.body().path("name"),fullName);
        assertEquals(response.body().path("email"),emailAddress);
        assertNotNull(response.path("token"));

        String token = response.path("token");
        System.out.println("token = " + token);
    }

    @Test
    public void postWithString_2(){
        /**
         * //TASK
         * post method
         * baseUrl = https://www.krafttechexlab.com/sw/api/v1
         * endpoint = /allusers/register
         * Given accept type and Content type is JSON
         * And request json body is:
         {
         "name": "Mark Ruffalo",
         "email": "mark@mark.com",
         "password": "Test1234",
         "about": "from USA",
         "terms": "3"
         }
         * When user sends POST request
         * Then status code 200
         * And content type should be application/json; charset=UTF-8
         * And json payload/response/body should contain:
         * a new generated id that is special for user
         * verify name and email
         * verify that response body contains token
         * get the token and assign it to a variable and print it
         */

        Faker faker = new Faker();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16, true, true);

        String newUserBody = """
                {
                         "name": "%s",
                         "email": "%s",
                         "password": "%s",
                         "about": "from USA",
                         "terms": "3"
                         }
                """.formatted(name,email,password);

        Response response = given().accept(ContentType.JSON)
                .body(newUserBody)
                .when().log().all()
                .post("/allusers/register")
                .prettyPeek();


        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");
        assertNotNull(response.path("id"));
        assertEquals(response.body().path("name"),name);
        assertEquals(response.body().path("email"),email);
        assertNotNull(response.path("token"));

        String token = response.path("token");
        System.out.println("token = " + token);
    }

    @Test
    public void postWithMap(){
        /**
         * //TASK
         * post method
         * baseUrl = https://www.krafttechexlab.com/sw/api/v1
         * endpoint = /allusers/register
         * Given accept type and Content type is JSON
         * And request json body is:
         {
         "name": "Mark Ruffalo",
         "email": "mark@mark.com",
         "password": "Test1234",
         "about": "from USA",
         "terms": "3"
         }
         * When user sends POST request
         * Then status code 200
         * And content type should be application/json; charset=UTF-8
         * And json payload/response/body should contain:
         * a new generated id that is special for user
         * verify name and email
         * verify that response body contains token
         * get the token and assign it to a variable and print it
         */

        Faker faker = new Faker();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16, true, true);

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("name", name);
        jsonBody.put("email", email);
        jsonBody.put("password", password);
        jsonBody.put("about", "About something");
        jsonBody.put("terms", "10");


        Response response = given().accept(ContentType.JSON)
                .body(jsonBody)
                .when().log().all()
                .post("/allusers/register")
                .prettyPeek();


        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");
        assertNotNull(response.path("id"));
        assertEquals(response.body().path("name"),name);
        assertEquals(response.body().path("email"),jsonBody.get("email"));
        assertNotNull(response.path("token"));

        String token = response.path("token");
        System.out.println("token = " + token);
    }

    @Test
    public void postWithPOJO(){
        /**
         * //TASK
         * post method
         * baseUrl = https://www.krafttechexlab.com/sw/api/v1
         * endpoint = /allusers/register
         * Given accept type and Content type is JSON
         * And request json body is:
         {
         "name": "Mark Ruffalo",
         "email": "mark@mark.com",
         "password": "Test1234",
         "about": "from USA",
         "terms": "3"
         }
         * When user sends POST request
         * Then status code 200
         * And content type should be application/json; charset=UTF-8
         * And json payload/response/body should contain:
         * a new generated id that is special for user
         * verify name and email
         * verify that response body contains token
         * get the token and assign it to a variable and print it
         */

        Faker faker = new Faker();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16, true, true);

        KraftNewUser kraftNewUser = new KraftNewUser(name,email,password,"From Bursa","5");

        Response response = given().accept(ContentType.JSON)
                .body(kraftNewUser)
                .when().log().all()
                .post("/allusers/register")
                .prettyPeek();


        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");
        assertNotNull(response.path("id"));
        assertEquals(response.body().path("name"),name);
        assertEquals(response.body().path("email"),kraftNewUser.getEmail());
        assertNotNull(response.path("token"));

        String token = response.path("token");
        System.out.println("token = " + token);
    }



}
