package lesson5;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.apache.http.entity.ContentType;
import org.example.api.ProductServices;
import org.example.lesson5.utilits.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetProducts {
    static ProductServices productServices;

    @BeforeAll
    static void beforeAll() {
        productServices = RetrofitUtils.getRetrofit().create(ProductServices.class);
    }

    @SneakyThrows
    @Test
    void getProductsPositiveTest() {
        Response<ResponseBody> response = productServices.getProducts().execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
    }
}
