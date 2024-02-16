package apiTests.day03;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class C01_BooksStorePathMethod {

    @BeforeClass
    public void setUoClass(){
       baseURI ="https://bookstore.toolsqa.com";
    }

    @Test
    public void bookStoreGetTest(){
        /**
         *
         *     TASK
         *     Given accept type json
         *     When user sends a get request to https://bookstore.toolsqa.com/BookStore/v1/Books
         *     end point:/BookStore/v1/Books
         *     Then status code should be 200
         *     And content type should be application/json; charset=utf-8
         *
         *     And the first book isbn should be 9781449325862
         *     And the first book publisher should be O'Reilly Media
         *
         *     And the last book isbn should be "9781593277574"
         *     And the last book author's name should be "Nicholas C. Zakas"
         *
         *     And the third book isbn should be "9781449337711"
         *     And the third book title should be "Designing Evolvable Web APIs with ASP.NET"
         *
         *     How to take all isbn numbers
         *     And the isbn numbers should have "9781491904244"
         *     And the all isbn numbers should have 13 digits
         */

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/BookStore/v1/Books");

       assertEquals(response.statusCode(),200);
       assertEquals(response.contentType(),"application/json; charset=utf-8");

       // And the first book isbn should be 9781449325862

        String isbn1 = response.body().path("books.isbn[0]");
        String  isbn_1 = response.path("books[0].isbn");
        assertEquals(isbn_1,"9781449325862");
        assertEquals(isbn1,"9781449325862");

        //['books'][0]['isbn']
        //["books"][0]["isbn"]

       // String  isbn_1_1 = response.path("['books[0]']['isbn']");
       // System.out.println("isbn_1_1 = " + isbn_1_1);

        // And the first book publisher should be O'Reilly Media
        String firstPublisher = response.path("books[0].publisher");
        assertEquals(firstPublisher,"O'Reilly Media");

       // And the last book isbn should be "9781593277574"
        String lastBook = response.path("books[-1].isbn");
        String lastBook_1= response.path("books.isbn[-1]");

        assertEquals(lastBook_1,"9781593277574");
        assertEquals(lastBook,"9781593277574");

        // And the last book author's name should be "Nicholas C. Zakas"
        assertEquals(response.path("books.author[-1]"),"Nicholas C. Zakas");

        //And the third book isbn should be "9781449337711"
        String thirdBook = response.path("books[2].isbn");
        assertEquals(thirdBook,"9781449337711");

        //And the third book title should be "Designing Evolvable Web APIs with ASP.NET"
        String title = response.path("books.title[2]");
        assertEquals(title,"Designing Evolvable Web APIs with ASP.NET");

        // *     How to take all isbn numbers
        //         *     And the isbn numbers should have "9781491904244"
        //         *     And the all isbn numbers should have 13 digits

       List<String> allISBNs = response.path("books.isbn");
       assertTrue(allISBNs.contains("9781491904244"));
       assertTrue(allISBNs.stream().anyMatch(x->x.equals("9781491904244")));

        // And the all isbn numbers should have 13 digits
        assertTrue(allISBNs.stream().allMatch(x->x.length()==13));
    }

}
