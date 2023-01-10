package com.dk.food;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dk.food.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    Context context;
    List<Recipe> recipeList;

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.recipelist,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.textview_title.setText(recipeList.get(position).title);
        holder.textview_title.setSelected(true);
        holder.textview_like.setText(recipeList.get(position).aggregateLikes+" Likes");
        holder.textview_time.setText(recipeList.get(position).readyInMinutes+" Minutes");
        holder.textview_serving.setText(recipeList.get(position).servings+" Servings");
        Picasso.get().load(recipeList.get(position).image).into(holder.foodImg);
        holder.recipe_container.startAnimation(AnimationUtils.loadAnimation(context,R.anim.down_top));
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView recipe_container;
        TextView textview_title,textview_serving,textview_time,textview_like;
        ImageView foodImg;
        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            recipe_container=itemView.findViewById(R.id.recipe_container);
            textview_like=itemView.findViewById(R.id.textview_like);
            textview_serving=itemView.findViewById(R.id.textview_serving);
            textview_time=itemView.findViewById(R.id.textview_time);
            textview_title=itemView.findViewById(R.id.textview_title);
            foodImg=itemView.findViewById(R.id.foodImg);

        }

        @Override
        public void onClick(View v) {
            Recipe recipe=recipeList.get(getAdapterPosition());
            int id=recipe.id;
            Toast.makeText(context, "id is:"+id, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context,RecipeDetails.class);
            intent.putExtra("id1",recipe.id+"");
            context.startActivity(intent);

        }
    }
}


