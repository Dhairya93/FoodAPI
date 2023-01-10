package com.dk.food;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dk.food.databinding.SimilarListRecipeBinding;
import com.dk.food.models.SimilarRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeAdapter.SimilarRecipeViewHolder> {
    private List<SimilarRecipeResponse> responseList;
    private Context context;

    public SimilarRecipeAdapter(Context context, List<SimilarRecipeResponse> responseList) {
        this.responseList = responseList;
        this.context=context;
    }

    @NonNull
    @Override
    public SimilarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return new SimilarRecipeViewHolder(SimilarListRecipeBinding.inflate(LayoutInflater.from(parent.getContext())));
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        SimilarListRecipeBinding recipeBinding=DataBindingUtil.inflate(inflater,R.layout.similar_list_recipe,parent,false);
        return new SimilarRecipeViewHolder(recipeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipeViewHolder holder, int position) {
        //holder.recipeBinding.setSimRecp(responseList.get(position));
        holder.recipeBinding.serveRece.setText(responseList.get(position).servings+" Persons");
        holder.recipeBinding.readyRece.setText(responseList.get(position).readyInMinutes+" Mins");
        holder.recipeBinding.titleRece.setText(responseList.get(position).title);
        ImageView similarImg = holder.recipeBinding.similarImgview;
        Picasso.get().load("https://spoonacular.com/recipeImages/"+responseList.get(position).id+"-312x231."+responseList.get(position).imageType).into(similarImg);
        holder.recipeBinding.similrCard.startAnimation(AnimationUtils.loadAnimation(context,R.anim.slide_up));
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    public class SimilarRecipeViewHolder extends RecyclerView.ViewHolder {
        SimilarListRecipeBinding recipeBinding;
        public SimilarRecipeViewHolder(@NonNull SimilarListRecipeBinding binding) {
            super(binding.getRoot());
            recipeBinding=binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,RecipeDetails.class);
                    intent.putExtra("id1",responseList.get(getAdapterPosition()).id+"");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                }
            });
        }
    }
}
