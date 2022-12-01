package org.example.api;

import org.example.dto.GetCategoryResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface CategoryServices {
    @GET("categories/{id}")
    Call<GetCategoryResponse> getCategory(@Path("id") int id);
}