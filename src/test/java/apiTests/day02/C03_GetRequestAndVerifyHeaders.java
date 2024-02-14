package apiTests.day02;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class C03_GetRequestAndVerifyHeaders {
    @BeforeClass
    public void beforeClass(){
        baseURI =  "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void headerTest(){
        /** Headers test
         *  Given accept type is JSON
         *  Ans pathParam id is 111
         *  When the user sends a GET request to "/allusers/getbyid/{id}"
         *  And status code should be 200
         *  And content-type is "application/json; charset=UTF-8"
         *  And response headers should have followings:
         *  Content-Length =606
         *  Content-Type = application/json; charset=UTF-8
         *  Date ---> should be a date header
         */

        Response response = given().contentType(ContentType.JSON)
                .pathParam("id", 111)
                .when().log().all()
                .get("/allusers/getbyid/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");

        //verify headers
        assertEquals(response.header("Content-Length"),"606");
        assertEquals(response.header("Content-Type"),"application/json; charset=UTF-8");

        //verify that date header is existing
        assertTrue(response.headers().hasHeaderWithName("Date"));

        //Date'in değerini alalım
        Headers headers = response.headers();
        System.out.println("-------------");
        System.out.println("headers = " + headers);
        System.out.println("-------------");
        System.out.println("headers.getValue(\"Date\") = " + headers.getValue("Date"));

        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));

    }
}
