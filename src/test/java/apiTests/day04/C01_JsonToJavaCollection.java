package apiTests.day04;

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

public class C01_JsonToJavaCollection {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void jsonToJava_List() {
        /**
         *
         *          given accept type is json
         *          And query param pagesize is 30
         *          And query param page is 1
         *          And take the request logs
         *          When user sends a get request to /allusers/alluser
         *          Then status code should be 200
         *          And content type should be application/json; charset=UTF-8
         *
         *          ilk kullanıcını email.nin "afmercan@gmail.com" olduğunu verify edelim
         *          ilk kullanıcını name.nin "MercanS" olduğunu verify edelim
         *          ilk kullanıcının skillerinden ilkinin "PHP" olduğunu verify edelim.
         *          ilk kullanıcının experience.lerinden üçüncüsünün company.sinin "Kraft Techex" olduğunu assert edelim
         *
         *          10. user'ın adını assert edelim "Selim Gezer"
         *          10.user'ın skillerinin ikincisinin Selenium olduğunu verify edelim
         *          10.user'ın educationın 3.sünün school adının Ankara University olduğunu verify edelim
         */

        Response response = given().accept(ContentType.JSON)
                .queryParam("pagesize", 30)
                .queryParam("page", 1)
                .when()
                //.log().all()   // request logları için
                .get("/allusers/alluser");
        //  .prettyPeek();  response status code, headers ve body logları için

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json; charset=UTF-8");

        //josn bady'i java collectina'a çevirmeye de-serailization denir
        // tersi de olur onun adı da serialization'dır.

        List<Map<String, Object>> allUsersData = response.body().as(List.class);

        // System.out.println("allUsersData = " + allUsersData);

        String name = (String) allUsersData.get(0).get("name");
        assertEquals(name, "MercanS");

        String email = (String) allUsersData.get(0).get("email");
        assertEquals(email, "afmercan@gmail.com");

        List<String> firstUserSkills = (List<String>) allUsersData.get(0).get("skills");
        assertEquals(firstUserSkills.get(0), "PHP");

        List<Map<String, Object>> firstUserExperience = (List<Map<String, Object>>) allUsersData.get(0).get(
                "experience");
        String company = (String) firstUserExperience.get(2).get("company");
        assertEquals(company, "Kraft Techex");

        // 10. user'ın adını assert edelim "Selim Gezer"
        // 10.user'ın skillerinin ikincisinin Selenium olduğunu verify edelim
        // 10.user'ın educationın 3.sünün school adının Ankara University olduğunu verify edelim
    }

    @Test
    public void jsonToJava_Map() {
        /**
         Given accept type json
         When user sends a get request to https://bookcart.azurewebsites.net/api
         And endPoint:/Book/{bookId} ---> bookId =3
         Then status code should be 200
         And content type should be application/json; charset=utf-8
         And the book id should be 1
         And the title should be HP3
         And the category should be Romance
         And the price should be 213.00

         */
        String baseURL = "https://bookcart.azurewebsites.net/api";

        Response response = given().accept(ContentType.JSON)
                .pathParam("bookId", 3)
                .when()
                .get(baseURL + "/Book/{bookId}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json; charset=utf-8");

        Map<String, Object> dataMap = response.as(Map.class);

        double id = (double) dataMap.get("bookId");

        assertEquals(id, 3);

        String title = (String) dataMap.get("title");
        assertEquals(title, "HP3");


        String category = (String) dataMap.get("category");
        assertEquals(category, "Romance");

        double price = (double) dataMap.get("price");
        assertEquals(price, 213.00);
    }
}
