package apiTests.day07;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class C02_PostPutPatchDelete_Flow_DevEx {

    String email = "emiko.morissette@hotmail.com";
    String password = "Test1234";

    Response response;
    int experienceId;
    int userId;
    int profileId;


    @BeforeClass
    public void setUpClass(){
        baseURI = "http://92.205.106.232/api";
    }

    @Test (priority = 1)
    public void updateUserProfile(){
        String profileBody = """
                {
                  "company": "ABC System",
                  "website": "www.abc.com",
                  "location": "Ankara",
                  "status": "Intern",
                  "skills": "HTML,CSS,Javascript,Java, Selenium",
                  "githubusername": "abc666",
                  "youtube": "abc666",
                  "twitter": "abc666",
                  "facebook": "abc666",
                  "linkedin": "abc666",
                  "instagram": "abc666"
                }
                """;

        response = given().contentType(ContentType.JSON)
            //    .header("x-auth-token",AuthorizationDevEx.getToken(email,password).get("x-auth-token"))
                .headers(AuthorizationDevEx.getToken(email, password))
                .body(profileBody)
                .when().log().all()
                .post("/profile")
                .prettyPeek();

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test (priority = 2)
    public void addExperience(){
        String experienceBody = "{\n" +
                "  \"title\": \"QA\",\n" +
                "  \"company\": \"Google\",\n" +
                "  \"location\": \"NY\",\n" +
                "  \"from\": \"2012-12-10\",\n" +
                "  \"to\": \"2015-11-11\",\n" +
                "  \"current\": false,\n" +
                "  \"description\": \"First Job\"\n" +
                "}";

         response = given().contentType(ContentType.JSON)
                .headers(AuthorizationDevEx.getToken(email, password))
                .body(experienceBody)
                .when().log().all()
                .post("/profile/experience")
                .prettyPeek();

         Assert.assertEquals(response.statusCode(),201);

          profileId = response.path("id");
          userId = response.path("userId");
          experienceId = response.path("experience[0].id");

        System.out.println("profileId = " + profileId);
        System.out.println("userId = " + userId);
        System.out.println("experienceId = " + experienceId);

    }

    @Test (priority = 3)
    public void updateExperienceWithPut(){
        String putBody = """
                {
                  "title": "DevOPs",
                  "company": "MicroSoft",
                  "location": "LA",
                  "from": "2016-11-11",
                  "to": "2018-12-12",
                  "current": false,
                  "description": "Good job"
                }
                """;
     response =  given().contentType(ContentType.JSON)
                .body(putBody)
                .headers(AuthorizationDevEx.getToken(email,password))
                .pathParam("ex_Id",experienceId)
                .when().log().all()
                .put("/profile/experience/{ex_Id}")
                .prettyPeek();

        Assert.assertEquals(response.statusCode(),204);
    }

    @Test (priority = 4)
    public void deleteExperience(){
       response = given().contentType(ContentType.JSON)
                .pathParam("experienceId",experienceId)
                .headers(AuthorizationDevEx.getToken(email,password))
                .when().log().all()
                .delete("/profile/experience/{experienceId}")
                .prettyPeek();

        Assert.assertEquals(response.statusCode(),200);
    }

}
