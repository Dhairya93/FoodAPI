package com.dk.food;

import com.dk.food.models.ApiResponse;
import com.dk.food.models.RecipeDetailResponse;
import com.dk.food.models.SimilarRecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Apis {
    @GET("recipes/random")
    Call<ApiResponse> apiresponse(
            @Query("apiKey") String apiKey,
            @Query("number") String number,
            @Query("tags") List<String> tags
    );

    @GET("recipes/{id}/information")
    Call<RecipeDetailResponse> recipedetailresponse(
        @Path("id") int id,
        @Query("apiKey") String apiKey
    );

    @GET("recipes/{id}/similar")
    Call<List<SimilarRecipeResponse>> SIMILAR_RECIPE_RESPONSE_CALL(
            @Path("id") int id,
            @Query("number") String number,
            @Query("apiKey") String apiKey
    );
}
