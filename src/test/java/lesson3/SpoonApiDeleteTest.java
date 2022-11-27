package lesson3;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import org.example.request.AddMealRequest;
import org.example.request.AddRequest;
import org.example.response.AddResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpoonApiDeleteTest extends AbstractClass {
    @Test
    void addAndDeleteToMealPlan() {
        String response = given()
                .spec(getRequestSpecification())
                .contentType(ContentType.JSON)
                .queryParam("hash", getHash())
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
                .spec(getResponseSpecification())
                .extract()
                .body()
                .as(AddRequest.Value.class)
                .getId()
                .toString();
        given()
                .spec(getRequestSpecification())
                .queryParam("hash", getHash())
                .when()
                .request(Method.DELETE, getBaseUrl() + "mealplanner/your-users-name591/items/" + response)
                .then()
                .spec(getResponseSpecification());
    }

    //Tests POST and DELETE Shopping List
    @Test
    void addAndDeleteShoppingListBaking() {
        AddMealRequest addMealRequest = new AddMealRequest();
        addMealRequest.setItem("1 package baking powder");
        addMealRequest.setAisle("Baking");
        addMealRequest.setParse(true);
        Integer response = given()
                .contentType(ContentType.JSON)
                .spec(getRequestSpecification())
                .queryParam("hash", getHash())
                .body(addMealRequest)
                .when()
                .request(Method.POST, getBaseUrl() + "mealplanner/your-users-name591/shopping-list/items")
                .then()
                .spec(getResponseSpecification())
                .extract()
                .body()
                .as(AddResponse.class)
                .getId();
        given()
                .spec(getRequestSpecification())
                .contentType(ContentType.JSON)
                .queryParam("hash", getHash())
                .when()
                .request(Method.DELETE, getBaseUrl() + "mealplanner/your-users-name591/shopping-list/items/" + response).prettyPeek()
                .then()
                .spec(getResponseSpecification());
    }

    @Test
    void addAndDeleteShoppingListPasta() {
        AddMealRequest addMealRequest = new AddMealRequest();
        addMealRequest.setItem("1 Pasta With Tuna");
        addMealRequest.setAisle("Pasta and Rice");
        addMealRequest.setParse(true);
        Integer response = given()
                .contentType(ContentType.JSON)
                .spec(getRequestSpecification())
                .queryParam("hash", getHash())
                .body(addMealRequest)
                .when()
                .request(Method.POST, getBaseUrl() + "mealplanner/your-users-name591/shopping-list/items")
                .then()
                .spec(getResponseSpecification())
                .extract()
                .body()
                .as(AddResponse.class)
                .getId();
        given()
                .spec(getRequestSpecification())
                .contentType(ContentType.JSON)
                .queryParam("hash", getHash())
                .when()
                .request(Method.DELETE, getBaseUrl() + "mealplanner/your-users-name591/shopping-list/items/" + response)
                .then()
                .spec(getResponseSpecification());
    }
}
