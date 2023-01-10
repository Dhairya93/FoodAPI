package com.dk.food.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dk.food.models.SimilarRecipeResponse;
import com.dk.food.repository.SimilarRecipeRepo;

import java.util.List;


public class SimilarRecipeViewModel extends ViewModel {

    private SimilarRecipeRepo recipeRepo;
    private MutableLiveData<List<SimilarRecipeResponse>> liveData;

    public SimilarRecipeViewModel(Context applicationContext, int recipeId) {
        recipeRepo =new SimilarRecipeRepo(recipeId,applicationContext);
    }

    public LiveData<List<SimilarRecipeResponse>> getSimilarRecipes(){
        if(liveData==null){
            liveData=recipeRepo.requestSimilarRecipe();
        }
        return liveData;
    }
}
