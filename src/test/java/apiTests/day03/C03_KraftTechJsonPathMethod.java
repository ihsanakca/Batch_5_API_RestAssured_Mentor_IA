package apiTests.day03;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class C03_KraftTechJsonPathMethod {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void kraftJsonPathMethod() {
        /**
         *     TASK
         *     Given accept type is json
         *     When user sends a GET request to /allusers/alluser
         *     query params pagesize=5, page=1
         *     Then the status Code should be 200
         *     And Content type json should be "application/json; charset=UTF-8"
         *
         */

        Response response = given().accept(ContentType.JSON)
                .queryParam("pagesize", 5)
                .queryParam("page", 1)
                .when()
                .get("/allusers/alluser");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=UTF-8");

     //   response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.getInt("id[0]"),1);

        //son elemanıın id.si
        assertEquals(jsonPath.getInt("id[-1]"),33);
        assertEquals(jsonPath.getInt("id[4]"),33);
        assertEquals(jsonPath.getInt("[4].id"),33);
        assertEquals(jsonPath.getInt("[-1].id"),33);

        //beşinci elemanın skillslerinden ikincisi cucumber
        assertEquals(jsonPath.getString("skills[4][1]"),"Cucumber");
        assertEquals(jsonPath.getString("[4].skills[1]"),"Cucumber");

        //ilk elemanın eğitimlerinin ilkinin "School or Bootcamp" olduğunu assert edelim
        assertEquals(jsonPath.get("education[0][0].school"),"School or Bootcamp");
        assertEquals(jsonPath.get("[0].education[0].school"),"School or Bootcamp");
        assertEquals(jsonPath.get("[0].education.school[0]"),"School or Bootcamp");
        assertEquals(jsonPath.get("education[0].school[0]"),"School or Bootcamp");

        //  [0]['education'][0]['school']
        //  [0]["education"][0]["school"]

        System.out.println(jsonPath.getString("[0]['education'][0]['school']"));
        System.out.println(jsonPath.getString("[0][\"education\"][0][\"school\"]"));


        List<List<Map<String,Object>>>  allEducations = jsonPath.getList("education");
        System.out.println("allEducations.get(0).get(0).get(\"school\") = " + allEducations.get(0).get(0).get("school"));
    }
}
