package com.dk.food.models;

import java.util.ArrayList;

public class RecipeDetailResponse {
    public int id;
    public String title;    //
    public String image;    //
    public int servings;    //
    public int readyInMinutes;  //
    public int aggregateLikes;  //
    public double pricePerServing;  //
    public String instructions;     //
    public String summary;          //

    public String imageType;
    public String license;
    public String sourceName;
    public String sourceUrl;
    public String spoonacularSourceUrl;
    public double healthScore;
    public double spoonacularScore;
    public ArrayList<Object> analyzedInstructions;
    public boolean cheap;
    public String creditsText;
    public ArrayList<Object> cuisines;
    public boolean dairyFree;
    public ArrayList<Object> diets;
    public String gaps;
    public boolean glutenFree;
    public boolean ketogenic;
    public boolean lowFodmap;
    public ArrayList<Object> occasions;
    public boolean sustainable;
    public boolean vegan;
    public boolean vegetarian;
    public boolean veryHealthy;
    public boolean veryPopular;
    public boolean whole30;
    public int weightWatcherSmartPoints;
    public ArrayList<String> dishTypes;
    public ArrayList<ExtendedIngredient> extendedIngredients;
    //public WinePairing winePairing;
}
