package apiTests.day07;

import static io.restassured.RestAssured.*;

import apiPOJOTemplates.devEx.RegisterNewUser;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class C01_PostNewUserDevEx {

    @BeforeClass
    public void setUpClass(){
        baseURI = "http://92.205.106.232/api";
    }

    @Test
    public void registerNewUser(){
        /**
         * Class Task
         * register devEx new user
         * post new user info to the related api
         * <p>
         * use as base url: "http://92.205.106.232/api"
         * registerUserEndPoint = "/users";
         * <p>
         * make assertion with statusCode
         */

        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        RegisterNewUser devExUserBody = new RegisterNewUser(email,"github",
                "facebook","google","Hasan Kaya","Test1234" );

        Response response = given().contentType(ContentType.JSON)
                .body(devExUserBody)
                .when().log().all()
                .post("/users")
                .prettyPeek();

        Assert.assertEquals(response.statusCode(),200);
        String token = response.path("token");
        System.out.println("token = " + token);

    }

}
