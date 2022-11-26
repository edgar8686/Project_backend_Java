package lesson3;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.checkerframework.checker.index.qual.LessThan;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpoonApiPostTest extends AbstractClass {
    @Test
    void ClassifyCuisineIngredientList() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pork roast with green beans")
                .formParam("ingredientList", "3 oz pork shoulder")
                .formParam("language", "en")
                .expect()
                .body("cuisine", equalTo("Mediterranean"))
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200)
                .time(lessThan(2500L))
                .contentType(ContentType.JSON);
    }

    @Test
    void ClassifyCuisineBurger() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Falafel Burger")
                .formParam("ingredientList", "1 cup green tea")
                .formParam("language", "en")
                .expect()
                .body("cuisine", equalTo("Middle Eastern"))
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .contentType(ContentType.JSON)
                .time(lessThan(2500L))
                .statusCode(200);
    }

    @Test
    void ClassifyCuisineCauliflowerBrownRiceAndVegetableFriedRice() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Cauliflower, Brown Rice, and Vegetable Fried Rice")
                .formParam("language", "en")
                .expect()
                .body("cuisines[1]", equalTo("Asian"))
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .time(lessThan(2500L))
                .contentType(ContentType.JSON)
                .statusCode(200);
    }

    @Test
    void ClassifyCuisineHomemadeGarlic() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Homemade Garlic and Basil French Fries")
                .formParam("language", "en")
                .expect()
                .body("cuisine", equalTo("American"))
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200)
                .time(lessThan(2500L))
                .contentType(ContentType.JSON);
    }

    @Test
    void ClassifyCuisineNegativeTest() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Dark Chocolate Mousse")
                .formParam("language", "en")
                .expect()
                .body("message", equalTo("You are not authorized. Please read https://spoonacular.com/food-api/docs#Authentication"))
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .contentType(ContentType.JSON)
                .time(lessThan(2500L))
                .statusCode(401);
    }

    @Test
    void addAndDeleteToMealPlan() {
        String response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", "000f62c7eaa04bf4ac04d8bd163dd91544d11cfa")
                .body("{\n"
                        + " \"date\": 1589500800,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"RECIPE\",\n"
                        + " \"value\": {\n"
                        + " \"id\": 296213,\n"
                        + " \"servings\": 2,\n"
                        + " \"title\" : \"Spinach Salad with Roasted Vegetables and Spiced Chickpea\",\n"
                        + " \"imageType\": \"jpg\",\n"
                        + " }\n"
                        + "}")
                .when()
                .request(Method.POST, getBaseUrl() + "mealplanner/your-users-name591/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", "000f62c7eaa04bf4ac04d8bd163dd91544d11cfa")
                .when()
                .request(Method.DELETE, getBaseUrl() + "mealplanner/your-users-name591/items/" + response)
                .then()
                .statusCode(200);
    }
}
