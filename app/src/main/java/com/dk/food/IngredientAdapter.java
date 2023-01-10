package com.dk.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dk.food.models.ExtendedIngredient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewholder> {
    Context context;
    List<ExtendedIngredient> extendedIngredients;
    public IngredientAdapter(Context context, List<ExtendedIngredient> extendedIngredients) {
        this.context=context;
        this.extendedIngredients=extendedIngredients;
    }


    @NonNull
    @Override
    public IngredientViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientViewholder(LayoutInflater.from(context).inflate(R.layout.ingredient_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewholder holder, int position) {
        holder.ingreName.setText(extendedIngredients.get(position).name);
        holder.ingrequantity.setText(extendedIngredients.get(position).original);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+extendedIngredients.get(position).image).into(holder.ingreImg);

    }


    @Override
    public int getItemCount() {
        return extendedIngredients.size();
    }

    public class IngredientViewholder extends RecyclerView.ViewHolder {
        TextView ingrequantity,ingreName;
        ImageView ingreImg;
        public IngredientViewholder(@NonNull View itemView) {
            super(itemView);
            this.ingreImg=itemView.findViewById(R.id.ingreImg);
            this.ingrequantity=itemView.findViewById(R.id.ingrequantity);
            this.ingreName=itemView.findViewById(R.id.ingreName);
        }
    }
}
