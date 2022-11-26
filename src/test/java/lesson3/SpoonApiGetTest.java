package lesson3;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.checkerframework.checker.index.qual.LessThan;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class SpoonApiGetTest extends AbstractClass {
    @Test
    void searchRecipesBurger() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "burger")
                .expect()
                .body("results[0].title", equalTo("Falafel Burger"))
                .when()
                .request(Method.GET,getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .time(lessThan(2500L));
    }
    @Test
    void searchRecipesGreek() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("excludeCuisine", "greek")
                .expect()
                .body("results[0].title",equalTo("Cauliflower, Brown Rice, and Vegetable Fried Rice"))
                .when()
                .request(Method.GET,getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .time(lessThan(2500L));
    }
    @Test
    void searchRecipesGluten() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("intolerances", "gluten")
                .expect()
                .body("results[0].title", equalTo("Cauliflower, Brown Rice, and Vegetable Fried Rice"))
                .when()
                .request(Method.GET,getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .time(lessThan(2500L));
    }
    @Test
    void searchRecipesEggs() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("excludeIngredients", "eggs")
                .expect()
                .body("results[4].title", equalTo("African Chicken Peanut Stew"))
                .when()
                .request(Method.GET, getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .time(lessThan(2500L));
    }
    @Test
    void searchRecipesMaxSugar() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("maxSugar", "100")
                .expect()
                .body("results[2].title", equalTo("Berry Banana Breakfast Smoothie"))
                .when()
                .request(Method.GET,getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200)
                .time(lessThan(2500L))
                .contentType(ContentType.JSON);
    }
}
