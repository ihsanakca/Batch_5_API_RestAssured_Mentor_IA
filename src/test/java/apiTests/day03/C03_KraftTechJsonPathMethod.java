package apiTests.day03;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class C03_KraftTechJsonPathMethod {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void kraftJsonPathMethod() {}
}
