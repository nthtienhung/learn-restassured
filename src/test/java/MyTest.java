import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MyTest {

    @BeforeSuite
    void setup() {
        // Set base URI for JSONPlaceholder API
        System.out.println("setup");
    }

    @Test
    void getReq(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.basePath = "posts";
        given().when().get().then().log().all().statusCode(200).body("size()", greaterThan(0));
    }

    @AfterSuite
    void tearDown() {
        System.out.println("teardown");
    }
}