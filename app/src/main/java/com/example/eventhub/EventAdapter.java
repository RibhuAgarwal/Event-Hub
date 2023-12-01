package com.example.eventhub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<Event> events; // The list of events

    public interface OnItemClickListener {
        void onItemClick(Event event);
    }

    // Corrected constructor
    public EventAdapter(List<Event> eventList) {
        this.events = eventList; // Assign the passed list to your member variable
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event event = events.get(position);
        holder.tvTitle.setText(event.getName());
        holder.tvLocation.setText(event.getLocation());
        holder.tvDate.setText(event.getDate());
        holder.tvTime.setText(event.getStartTime());
        holder.tvEndTime.setText(event.getEndTime());
        // You can also set the image for locationIcon here if needed




    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvTitle, tvLocation, tvTime, tvEndTime;
        ImageView locationIcon;

        ViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvEndTime = itemView.findViewById(R.id.enndtime); // Ensure this ID matches your layout
            locationIcon = itemView.findViewById(R.id.location_icon); // Set up this ImageView if needed
        }
    }
}
