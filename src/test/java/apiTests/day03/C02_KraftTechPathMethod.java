package apiTests.day03;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class C02_KraftTechPathMethod {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void kraftPathMethod() {
        /*
        [
    {
        "id": 62,
        "name": "Selim Gezer",
        "email": "sgezer@gmail.com",
        "password": "sg12345678",
        "about": "This is great workSome words are here...Some words are here...",
        "terms": "13",
        "date": "2022-11-17 19:37:10",
        "job": "QA Automation Engineer",
        "company": "KraftTech",
        "website": "https://www.krafttechnologie.com",
        "location": "İstanbul",
        "skills": [
            "Java",
            "Selenium",
            "TestNG",
            "Cucumber"
        ],
        "github": "",
        "twitter": "",
        "facebook": "",
        "youtube": "",
        "linkedin": "",
        "instagram": "",
        "avatar": "1",
        "admin": "0",
        "education": [
            {
                "id": 493,
                "school": "Kraft Tech Bootcamp",
                "degree": "Master Degree",
         */

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 62)
                .when()
                .get("/allusers/getbyid/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json; charset=UTF-8");

        //id.yi alalım
        int id = response.path("id[0]");
        assertEquals(id, 62, 0);

        //emaili alalım
        System.out.println("response.path(\"email\") = " + response.path("email"));
        String email = response.path("email[0]");
        String email_1 = response.path("[0].email");
        assertEquals(email, email_1);

        //skillerini alalım
        System.out.println("response.path(\"skills\") = " + response.path("skills"));
        System.out.println("response.path(\"skills[0][0]\") = " + response.path("skills[0][0]"));
        System.out.println("response.path(\"[0].skills[0]\") = " + response.path("[0].skills[0]"));
    }
}
