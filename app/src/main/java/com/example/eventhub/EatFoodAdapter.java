package com.example.eventhub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EatFoodAdapter extends RecyclerView.Adapter<EatFoodAdapter.EatFoodViewHolder> {

    private List<EatFood> eatFoodList;
    private Context context;

    public EatFoodAdapter(Context context, List<EatFood> eatFoodList) {
        this.context = context;
        this.eatFoodList = eatFoodList;
    }

    @NonNull
    @Override
    public EatFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_eatfood, parent, false);
        return new EatFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EatFoodViewHolder holder, int position) {
        EatFood eatFood = eatFoodList.get(position);
        holder.title.setText(eatFood.getTitle());
        holder.image.setImageResource(eatFood.getImageResId());

        // Set the click listener for the view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RestaurantActivity.class);
                intent.putExtra("EAT_FOOD_NAME", eatFood.getTitle()); // Pass the name of the eatFood item
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eatFoodList.size();
    }

    public static class EatFoodViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;

        public EatFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.eatfood_image);
            title = itemView.findViewById(R.id.eatfood_title);
        }
    }
}
