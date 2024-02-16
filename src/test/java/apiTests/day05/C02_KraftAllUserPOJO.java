package apiTests.day05;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import apiPOJOTemplates.kraft.Education;
import apiPOJOTemplates.kraft.KraftAllUser;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class C02_KraftAllUserPOJO {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }
    @Test
    public void kraftAllUserPojoTest(){
        /**
         * Class Task
         * Given accept type JSON
         * and Query parameter value pagesize 30
         * and Query parameter value page 1
         * When user send GET request to /allusers/alluser
         * Then response status code is 200
         */

        Response response = given().accept(ContentType.JSON)
                .queryParam("page", 1)
                .queryParam("pagesize", 30)
                .when()
                .get("/allusers/alluser");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(), "application/json; charset=UTF-8");

        KraftAllUser[] kraftAllUsers = response.as(KraftAllUser[].class);

        System.out.println("kraftAllUsers[0].getName() = " + kraftAllUsers[0].getName());
        System.out.println("kraftAllUsers[29].getName() = " + kraftAllUsers[29].getName());
        System.out.println(kraftAllUsers[kraftAllUsers.length - 1].getName());

        List<Education> education =kraftAllUsers[5].getEducation();
        System.out.println(education);

        Education education1 = education.get(0);
        System.out.println("education1.getSchool() = " + education1.getSchool());

        System.out.println(kraftAllUsers[29].getEducation().get(1).getSchool());


    }
}
