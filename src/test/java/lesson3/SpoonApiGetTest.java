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
                .spec(getRequestSpecification())
                .queryParam("query", "burger")
                .expect()
                .body("results[0].title", equalTo("Falafel Burger"))
                .when()
                .request(Method.GET, getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseSpecification());
    }

    @Test
    void searchRecipesGreek() {
        given()
                .spec(getRequestSpecification())
                .queryParam("excludeCuisine", "greek")
                .expect()
                .body("results[0].title", equalTo("Cauliflower, Brown Rice, and Vegetable Fried Rice"))
                .when()
                .request(Method.GET, getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseSpecification());
    }

    @Test
    void searchRecipesGluten() {
        given()
                .spec(getRequestSpecification())
                .queryParam("intolerances", "gluten")
                .expect()
                .body("results[0].title", equalTo("Cauliflower, Brown Rice, and Vegetable Fried Rice"))
                .when()
                .request(Method.GET, getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseSpecification());
    }

    @Test
    void searchRecipesEggs() {
        given()
                .spec(getRequestSpecification())
                .queryParam("excludeIngredients", "eggs")
                .expect()
                .body("results[4].title", equalTo("African Chicken Peanut Stew"))
                .when()
                .request(Method.GET, getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseSpecification());
    }

    @Test
    void searchRecipesMaxSugar() {
        given()
                .spec(getRequestSpecification())
                .queryParam("maxSugar", "100")
                .expect()
                .body("results[2].title", equalTo("Berry Banana Breakfast Smoothie"))
                .when()
                .request(Method.GET, getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseSpecification());
    }

    // Tests GET Shopping List
    @Test
    void getShoppingList() {
        given()
                .spec(getRequestSpecification())
                .contentType(ContentType.JSON)
                .queryParam("hash", getHash())
                .when()
                .request(Method.GET, getBaseUrl() + "mealplanner/your-users-name591/shopping-list").prettyPeek()
                .then()
                .spec(getResponseSpecification());

    }
}
