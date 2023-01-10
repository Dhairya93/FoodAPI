package com.dk.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.dk.food.models.ApiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    RecipeAdapter recipeAdapter;
    RecyclerView recyclerView;
    Spinner spinner;
    List<String> tags=new ArrayList<>();
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();

        searchView=findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                getRecipe();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        spinner=findViewById(R.id.spinner_tag);
        ArrayAdapter arrayAdapter=ArrayAdapter.createFromResource(this,R.array.tags, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        recyclerView=findViewById(R.id.recipeView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
        tags.add("main course");//DEFAULT RECIPE VIEW
        getRecipe();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tags.clear();
                tags.add(parent.getSelectedItem().toString());
                //Toast.makeText(MainActivity.this, "PARENT.."+parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                getRecipe();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void getRecipe(){
        Call<ApiResponse> call=ReqestManager.getInstance().getApi().apiresponse(this.getString(R.string.apikey),"15",tags);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    recipeAdapter=new RecipeAdapter(MainActivity.this,response.body().recipes);
                    recyclerView.setAdapter(recipeAdapter);
                    Toast.makeText(MainActivity.this, "Success...", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this, "Failed to reach API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}