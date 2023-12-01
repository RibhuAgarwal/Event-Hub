package com.example.eventhub;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AccessAdapter extends RecyclerView.Adapter<AccessAdapter.ViewHolder> {

    private Context context;
    private List<Access> accessList;

    public AccessAdapter(Context context, List<Access> accessList) {
        this.context = context;
        this.accessList = accessList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.access_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Access currentAccess = accessList.get(position);
        holder.imageView.setImageResource(currentAccess.getImageResId());
        holder.titleTextView.setText(currentAccess.getTitle());
        holder.locationTextView.setText(currentAccess.getLocation());
    }

    @Override
    public int getItemCount() {
        return accessList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView, locationTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    // Check if the position is valid
                    if (position != RecyclerView.NO_POSITION) {
                        // Get the Access object for the clicked item
                        Access clickedAccess = accessList.get(position);

                        // Create an Intent to start your target activity
                        Intent intent = new Intent(context, TransitActivity.class);

                        // Add the title to the Intent
                        intent.putExtra("title", clickedAccess.getTitle());


                        // Start the new activity
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}