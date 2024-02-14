package apiTests.day02;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class C02_GetUsersRecordsWithQueryParam {
    @BeforeClass
    public void beforeClass(){
        baseURI =  "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void queryParamTest_1(){
        /**
         *  Given accept type json
         *  And query  parameter value pagesize 50
         *  And query  parameter value page 1
         *  When user sends GET request to /allusers/alluser
         *  Then response status code should be 200
         *  And response content-type application/json; charset=UTF-8
         *  And response body contains "Developer" message
         */

        Response response = given().contentType(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .when()
                .get("/allusers/alluser");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");
        assertTrue(response.asString().contains("Developer"));

    }

    @Test
    public void queryParamTest_WithMap(){
        /**
         *  Given accept type json
         *  And query  parameter value name Thomas Eduson
         *  And query  parameter value skills Cypress
         *  And query  parameter value pagesize 50
         *  And query  parameter value page 1
         *  When user sends GET request to /allusers/alluser
         *  Then response status code should be 200
         *  And response content-type application/json; charset=UTF-8
         *  And response body contains "Developer" message
         */

        Map<String ,Object> map = new HashMap<>();
        map.put("name","Thomas Eduson");
        map.put("pagesize",50);
        map.put("page",1);
        map.put("skills","Cypress");

        Response response = given().contentType(ContentType.JSON)
                .queryParams(map)
                .when()
                .get("/allusers/alluser");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");
        assertTrue(response.asString().contains("Developer"));

    }
}
