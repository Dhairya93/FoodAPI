package com.dk.food.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.dk.food.R;
import com.dk.food.ReqestManager;
import com.dk.food.models.SimilarRecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimilarRecipeRepo {
    int recipeId;
    Context context;

    public SimilarRecipeRepo(int recipeId, Context context) {
        this.recipeId = recipeId;
        this.context = context;
    }

    public MutableLiveData<List<SimilarRecipeResponse>> requestSimilarRecipe() {

        final MutableLiveData<List<SimilarRecipeResponse>> mutableLiveData=new MutableLiveData<>();

        Call<List<SimilarRecipeResponse>> responseCall= ReqestManager.getInstance().getApi().SIMILAR_RECIPE_RESPONSE_CALL(recipeId,"10",context.getString(R.string.apikey));
        responseCall.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if (response.isSuccessful() && response.body()!=null){
                    mutableLiveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
