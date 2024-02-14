package apiTests.day01;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C01_SimpleGetRequest {

    String baseURL="https://demoqa.com";
    @Test
    public void simpleGetRequestTest(){
        Response response = RestAssured.get("https://demoqa.com/BookStore/v1/Books");

        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();
    }

    @Test
    public void simpleGetRequestTest_2(){
        Response response = RestAssured.get(baseURL+"/BookStore/v1/Books");

        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();
    }

    @Test
    public void simpleGetRequestWithHeader(){
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(baseURL + "/BookStore/v1/Books");

        //verify status code
        Assert.assertEquals(response.getStatusCode(),200);

        //verify content-type
        Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");

    }

    @Test
    public void validateWithRestAssured(){
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(baseURL + "/BookStore/v1/Books")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType("application/json; charset=utf-8");

    }


    @Test
    public void validateBody(){
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(baseURL + "/BookStore/v1/Books");

        //statsus code ve content type
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");

        //body verify with contains method
        String body = response.body().asString();
        Assert.assertTrue(body.contains("Nicholas C. Zakas"));

        System.out.println("body.charAt(0) = " + body.charAt(0));

        System.out.println("body.length() = " + body.length());  //4514
    }
}
