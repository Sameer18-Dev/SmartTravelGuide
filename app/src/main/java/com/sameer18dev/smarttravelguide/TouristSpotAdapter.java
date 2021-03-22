package com.sameer18dev.smarttravelguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TouristSpotAdapter extends RecyclerView.Adapter<TouristSpotAdapter.VideoViewHolder> {

    List<touristspot> touristspotList;

    public TouristSpotAdapter(List<touristspot> youtubeVideoList) {
        this.touristspotList = youtubeVideoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.tourist_spot_row, parent, false);

        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

        holder.desp.setText(touristspotList.get(position).getTsdesp());
        holder.spot.setText(touristspotList.get(position).getTsname());
        holder.city.setText("City : "+touristspotList.get(position).getScity());
        holder.ratings.setText("Ratings : "+touristspotList.get(position).getTsratings());
    }

    @Override
    public int getItemCount() {
        return touristspotList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        private TextView spot, desp, city, ratings;

        public VideoViewHolder(View itemView) {
            super(itemView);

            spot = itemView.findViewById(R.id.spot);
            desp = itemView.findViewById(R.id.desp);
            city = itemView.findViewById(R.id.city);
            ratings = itemView.findViewById(R.id.ratings);

        }
    }

}
