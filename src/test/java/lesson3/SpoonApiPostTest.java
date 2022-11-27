package lesson3;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.example.request.AddMealRequest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import static io.restassured.RestAssured.given;

public class SpoonApiPostTest extends AbstractClass {
    @Test
    void ClassifyCuisineIngredientList() {
        given()
                .spec(getRequestSpecification())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pork roast with green beans")
                .formParam("ingredientList", "3 oz pork shoulder")
                .formParam("language", "en")
                .expect()
                .body("cuisine", equalTo("Mediterranean"))
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(getResponseSpecification());
    }

    @Test
    void ClassifyCuisineBurger() {
        given()
                .spec(getRequestSpecification())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Falafel Burger")
                .formParam("ingredientList", "1 cup green tea")
                .formParam("language", "en")
                .expect()
                .body("cuisine", equalTo("Middle Eastern"))
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(getResponseSpecification());
    }

    @Test
    void ClassifyCuisineCauliflowerBrownRiceAndVegetableFriedRice() {
        given()
                .spec(getRequestSpecification())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Cauliflower, Brown Rice, and Vegetable Fried Rice")
                .formParam("language", "en")
                .expect()
                .body("cuisines[1]", equalTo("Asian"))
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(getResponseSpecification());
    }

    @Test
    void ClassifyCuisineHomemadeGarlic() {
        given()
                .spec(getRequestSpecification())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Homemade Garlic and Basil French Fries")
                .formParam("language", "en")
                .expect()
                .body("cuisine", equalTo("American"))
                .when()
                .request(Method.POST, getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(getResponseSpecification());
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

}
