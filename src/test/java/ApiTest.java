import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @BeforeClass
    public void setup() {
        // Set base URI for JSONPlaceholder API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetPosts() {
        given()
            .when()
                .get("/posts")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testGetSpecificPost() {
        given()
            .when()
                .get("/posts/1")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("title", not(emptyString()))
                .body("body", not(emptyString()));
    }

    @Test
    public void testCreatePost() {
        String requestBody = """
                {
                    "title": "Test Post",
                    "body": "This is a test post",
                    "userId": 1
                }""";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("Test Post"))
            .body("body", equalTo("This is a test post"))
            .body("userId", equalTo(1))
            .body("id", notNullValue());
    }
}