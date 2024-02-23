package apiTests.day07;

import apiPOJOTemplates.devEx.LoginBody;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class AuthorizationDevEx {
    @BeforeClass
    public void setUpClass(){
        baseURI = "http://92.205.106.232/api";
    }

    public static Map<String,String> getToken(String email,String password){
        String body = """
                {
                  "email": "%s",
                  "password": "%s"
                }
                """.formatted(email,password);

        String body2="{\n" +
                "  \"email\": \""+email+"\",\n" +
                "  \"password\": \""+password+"\"\n" +
                "}";

        Map<String,String> mapBody = new HashMap<>();
        mapBody.put("email",email);
        mapBody.put("password",password);

        LoginBody loginBody = new LoginBody(email, password);

        Response response = given().contentType(ContentType.JSON)
                .body(body)
                .when().log().all()
                .post("/auth")
                .prettyPeek();

        String token = response.path("token");

        Map<String ,String> map = new HashMap<>();
        map.put("x-auth-token",token);

        ConfigurationReader.set("x-auth-token",token);

        return map;
    }


    @Test
    public void test1(){
        getToken("emiko.morissette@hotmail.com","Test1234");
    }


}
