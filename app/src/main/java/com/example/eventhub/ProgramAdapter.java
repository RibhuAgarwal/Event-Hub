package com.example.eventhub;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder> {

    private Context context;
    private List<Program> programList;

    public ProgramAdapter(Context context, List<Program> programList) {
        this.context = context;
        this.programList = programList;
    }

    @NonNull
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_program, parent, false);
        return new ProgramViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramViewHolder holder, int position) {
        Program program = programList.get(position);

        holder.textViewName.setText(program.getName());
        holder.textViewLocation.setText(program.getLocation());
        holder.textViewDate.setText(program.getDate());
        holder.textViewStartTime.setText(program.getStartTime());
        holder.textViewEndTime.setText(program.getEndTime());

        Glide.with(context)
                .load(program.getImageUrl())
                .centerCrop()
                .into(holder.imageViewEvent);

        // Set the click listener for the button if needed
        holder.buttonBuyTicket.setOnClickListener(view -> {
            Intent intent = new Intent(context, BuyActivity.class);
            String eventId = program.getId();
            if (eventId != null) {
              //  Toast.makeText(view.getContext(), eventId, Toast.LENGTH_LONG).show();
                intent.putExtra("EVENT_ID", eventId);
                context.startActivity(intent);
            } else {
                Toast.makeText(view.getContext(), "Event ID is null", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return programList.size();
    }

    static class ProgramViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewLocation,  textViewDate, textViewStartTime, textViewEndTime;
        ImageView imageViewEvent;
        Button buttonBuyTicket;

        ProgramViewHolder(View itemView) {
            super(itemView);
            imageViewEvent = itemView.findViewById(R.id.imageViewEvent);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
            textViewStartTime = itemView.findViewById(R.id.textViewTime);
            textViewEndTime = itemView.findViewById(R.id.textViewEndTime);
            buttonBuyTicket = itemView.findViewById(R.id.buttonBuyTicket);
        }
    }
}
