package lesson5;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.example.api.ProductServices;
import org.example.dto.Product;
import org.example.lesson5.utilits.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PutProduct {
    static ProductServices productServices;
    Product product = null;
    Faker faker = new Faker();
    Faker faker2 = new Faker();


    @BeforeAll
    static void beforeAll() {
        productServices = RetrofitUtils.getRetrofit().create(ProductServices.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withId(faker2.number().numberBetween(1, 10))
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random()) * 10000);
    }

    @SneakyThrows
    @Test
    void putProduct() {
        Response<Product> response = productServices.modifyProduct(product).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
    }

}
