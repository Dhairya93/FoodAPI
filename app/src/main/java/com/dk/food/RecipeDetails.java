package com.dk.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.animation.LayoutTransition;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.dk.food.databinding.RecipeDetailsActivityBinding;
import com.dk.food.databinding.RecipelistBinding;
import com.dk.food.models.ExtendedIngredient;
import com.dk.food.models.Recipe;
import com.dk.food.models.RecipeDetailResponse;
import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeDetails extends AppCompatActivity {

    String id1=null;
    List<ExtendedIngredient> extendedIngredients;
    RecipeDetailsActivityBinding binding;
    ProgressDialog progressDialog;
    ScrollView mainLayout;
    boolean summary_collapsed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=RecipeDetailsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        animateSummaryView(binding);
        mainLayout=binding.animBack;
        summary_collapsed=true;
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Lodaing...");
        progressDialog.show();


        //RecipeDetailsActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.recipe_details_activity);
        id1 = getIntent().getStringExtra("id1");
       // Toast.makeText(this, "extraaa:"+id1, Toast.LENGTH_SHORT).show();
        int id=Integer.parseInt(id1);
       // Toast.makeText(this, "integer...:"+id, Toast.LENGTH_SHORT).show();
        Call<RecipeDetailResponse> responseCall=ReqestManager.getInstance().getApi().recipedetailresponse(id,this.getString(R.string.apikey));
        responseCall.enqueue(new Callback<RecipeDetailResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailResponse> call, Response<RecipeDetailResponse> response) {
                if(response.isSuccessful()){

                binding.recipeTitle.setText(response.body().title);
                binding.recipeLikes.setText(response.body().aggregateLikes+" Likes");
                binding.recipePrice.setText("$"+response.body().pricePerServing);
                binding.recipeTime.setText(response.body().readyInMinutes+" Mins");
                binding.recipeServing.setText(response.body().servings+" Person");
                    ImageView recipeImg = binding.recipeImg;
//                    int devcieWidth= Resources.getSystem().getDisplayMetrics().widthPixels;
//                    recipeImg.getLayoutParams().width=devcieWidth-80;
//                    recipeImg.requestLayout();
//                    recipeImg.setScaleType(ImageView.ScaleType.MATRIX);
                    Picasso.get().load(response.body().image).into(recipeImg);
                binding.recipeSummary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(summary_collapsed){binding.recipeSummary.setMaxLines(Integer.MAX_VALUE);}
                        else{binding.recipeSummary.setMaxLines(2);}
                        summary_collapsed=false;
                    }
                });
                binding.recipeSummary.setText(Html.fromHtml(response.body().summary));

                    progressDialog.dismiss();
                extendedIngredients = response.body().extendedIngredients;
                binding.ingredList.setLayoutManager(new LinearLayoutManager(RecipeDetails.this,LinearLayoutManager.HORIZONTAL,false));
                binding.ingredList.setAdapter(new IngredientAdapter(RecipeDetails.this,extendedIngredients));
                }
                else{
                    Toast.makeText(RecipeDetails.this, "Error Loading...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RecipeDetailResponse> call, Throwable t) {

            }
        });

        binding.similarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimilarRecipe similarRecipe=new SimilarRecipe(id);
                similarRecipe.show(getSupportFragmentManager(), similarRecipe.getTag());

            }
        });
    }

    private void animateSummaryView(RecipeDetailsActivityBinding binding) {
        LayoutTransition transition=new LayoutTransition();
        transition.setDuration(400);
        transition.enableTransitionType(LayoutTransition.CHANGING);
        binding.summaryLayout.setLayoutTransition(transition);
    }
}