package com.dk.food;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.dk.food.models.SimilarRecipeResponse;
import com.dk.food.viewmodel.SimilarRecipeViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class SimilarRecipe extends BottomSheetDialogFragment {
    RecyclerView recyclerView;
    int recipeId;
    public SimilarRecipe(int recipeId) {
        this.recipeId=recipeId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.similar_recipe, container, false);

        recyclerView=view.findViewById(R.id.similarRecyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        SimilarRecipeViewModel viewModel=new SimilarRecipeViewModel(getContext(),recipeId);
        viewModel.getSimilarRecipes().observe(this, new Observer<List<SimilarRecipeResponse>>() {
            @Override
            public void onChanged(List<SimilarRecipeResponse> similarRecipeResponses) {
                if(similarRecipeResponses!=null && !similarRecipeResponses.isEmpty()){
                    RecyclerView.Adapter adapter= new SimilarRecipeAdapter(getContext(), similarRecipeResponses);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        return view;
    }
}