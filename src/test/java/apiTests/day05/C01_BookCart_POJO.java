package apiTests.day05;

import apiPOJOTemplates.bookCart.BookCartGetBookTemp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C01_BookCart_POJO {

    @Test
    public void simplePojoTest(){
        /**
         *  Given accept type json
         *  When user sends a get request to https://bookcart.azurewebsites.net/api
         *  And endPoint:/Book/{bookId} ---> bookId =2
         *  Then status code should be 200
         *  And content type should be application/json; charset=utf-8
         *
         *  verify that bookId is 2
         *  and verify that title is "HP2"
         *  verify that price is 235.00
         *  verify that coverFileName is "9d8f4978-0ef8-42d0-873a-4eb583439237HP2.jpg"

         Note : Use POJO for verification of response body

         {
         "bookId": 2,
         "title": "HP2",
         "author": "JKR",
         "category": "Mystery",
         "price": 235.00,
         "coverFileName": "9d8f4978-0ef8-42d0-873a-4eb583439237HP2.jpg"
         }

         */

        String baseURL = "https://bookcart.azurewebsites.net/api";

        Response response = given().accept(ContentType.JSON)
                .pathParam("bookId", 2)
                .when()
                .get(baseURL + "/Book/{bookId}");

        BookCartGetBookTemp dataOfBook = response.as(BookCartGetBookTemp.class);
        int bookId = dataOfBook.getBookId();
        assertEquals(bookId,2);

        String title = dataOfBook.getTitle();
        assertEquals(title,"HP2");

        double price = dataOfBook.getPrice();
        assertEquals(price,235.00);

        String coverFileName = dataOfBook.getCoverFileName();
        assertEquals(coverFileName,"9d8f4978-0ef8-42d0-873a-4eb583439237HP2.jpg");
    }
}
