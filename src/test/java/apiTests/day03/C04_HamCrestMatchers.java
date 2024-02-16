package apiTests.day03;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
public class C04_HamCrestMatchers {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void kraftHamCrestMatchersMethod() {
        /**
         *         given accept type is json
         *         And path param id is 62
         *         When user sends a get request to /allusers/getbyid/{id}
         *         Then status code should be 200
         *         And content type should be "application/json; charset=UTF-8"
         *         user's id should be "62"
         *         user's name should be "Selim Gezer"
         *         user's job should be "QA Automation Engineer"
         *         User's second skill should be "Selenium"
         *         User's third education school name should be "Ankara University"
         *         User's email should be "sgezer@gmail.com"
         *         User's company should be "KraftTech"
         *
         *         The response header Content-Lenght should be 756
         *         Response headers should have "Date" header
         *
         *         User's skills should contain Selenium
         *         User's skills should contain Selenium and Java
         */

       given().accept(ContentType.JSON)
                .pathParam("userId", 62)
                .when()
                .get("/allusers/getbyid/{userId}")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=UTF-8")
               .and()
               .body("[0].id", equalTo(62),
                       "name[0]", equalTo("Selim Gezer"),
                       "[0].job",equalTo("QA Automation Engineer"),
                       "skills[0][1]",equalTo("Selenium"),
                       "[0].education.school[2]",equalTo("Ankara University"),
                       "email[0]",equalTo("sgezer@gmail.com"),"[0].company",equalTo("KraftTech"))
               .header("Content-Length","756")
               .header("Date",notNullValue())
               .headers("Content-Length",equalTo("756"),"Server",equalTo("Apache/2"))
               .body("skills[0]",hasItem("Selenium"),
                       "[0].skills",hasItems("Selenium","Java"),
                       "location[0]",equalTo("İstanbul"));


    }

}
