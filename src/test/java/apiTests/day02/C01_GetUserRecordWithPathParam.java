package apiTests.day02;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class C01_GetUserRecordWithPathParam {

    @BeforeClass
    public void beforeClass(){
        baseURI =  "https://www.krafttechexlab.com/sw/api/v1";
    }


    @Test
    public void getMethodWithPathParam(){
        /**
         *  given accept type is json
         *  And path param id is 62
         *  When user send GET request to url: "https://www.krafttechexlab.com/sw/api/v1"
         *  When user send GET request to endpoint: "/allusers/getbyid/62"
         *  Then status code should be 200
         *  And content type should be "application/json; charset=UTF-8"
         *  And response body should contains "Selim Gezer"
         *
         */

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .when()
                .get("/allusers/getbyid/62");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");
        assertTrue(response.asString().contains("Selim Gezer"));
    }


    @Test
    public void getMethodWithPathParam_2(){
        /**
         * USING PATH PARAM WİTH REQUEST
         *  given accept type is json
         *  And path param id is 62
         *  When user send GET request to url: "https://www.krafttechexlab.com/sw/api/v1"
         *  When user send GET request to endpoint: "/allusers/getbyid/62"
         *  Then status code should be 200
         *  And content type should be "application/json; charset=UTF-8"
         *  And response body should contains "Selim Gezer"
         *
         */

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("userId",62)
                .contentType(ContentType.JSON)
                .when()
                .get("/allusers/getbyid/{userId}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");
        assertTrue(response.asString().contains("Selim Gezer"));
    }

    @Test
    public void getMethodWithPathParam_NegativeTest(){

        /** negative get user test
         *  Given accept type is JSON
         *  Ans pathParam id is 55
         *  When the user sends a GET request to "/allusers/getbyid/{id}"
         *  Then status code should be 404
         *  And content-type is "application/json; charset=UTF-8"
         *  And "No User Record Found..." message should be in response payload
         * */

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id",55)
                .contentType(ContentType.JSON)
                .when()
                .get("/allusers/getbyid/{id}");

        assertEquals(response.statusCode(),404);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");
        assertTrue(response.asString().contains("No User Record Found..."));
    }



}
