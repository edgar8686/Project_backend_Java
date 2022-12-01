package lesson5;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.example.api.ProductServices;
import org.example.dto.Product;
import org.example.lesson5.utilits.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetProductsId {
    static ProductServices productServices;
    Product product = null;
    Faker faker = new Faker();


    @BeforeAll
    static void beforeAll() {
        productServices = RetrofitUtils.getRetrofit().create(ProductServices.class);
    }

    @SneakyThrows
    @Test
    void getProductsIdTest() {
        Response<Product> response = productServices.getProductById(2).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
        assertThat(response.body().getTitle(), equalTo("Bread"));
        assertThat(response.body().getId(), equalTo(2));
    }
}
