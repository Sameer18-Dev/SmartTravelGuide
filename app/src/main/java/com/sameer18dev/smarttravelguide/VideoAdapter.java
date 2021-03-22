package com.sameer18dev.smarttravelguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    List<YoutubeVideo> youtubeVideoList;

    public VideoAdapter(List<YoutubeVideo> youtubeVideoList) {
        this.youtubeVideoList = youtubeVideoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.card_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

        holder.videoWeb.loadData( youtubeVideoList.get(position).getVideoUrl(), "text/html" , "utf-8" );
        holder.desp.setText(youtubeVideoList.get(position).getVdesp());
        holder.title.setText(youtubeVideoList.get(position).getVtitle());
    }

    @Override
    public int getItemCount() {
        return youtubeVideoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        private WebView videoWeb;
        private TextView title, desp;

        public VideoViewHolder(View itemView) {
            super(itemView);

            videoWeb = itemView.findViewById(R.id.webVideoView);

            title = itemView.findViewById(R.id.title);
            desp = itemView.findViewById(R.id.desp);

            videoWeb.getSettings().setJavaScriptEnabled(true);

            videoWeb.setWebChromeClient(new WebChromeClient() {
            } );
        }
    }

    public void setFilter(List<YoutubeVideo> newList){
        youtubeVideoList = new ArrayList<>();
        youtubeVideoList.addAll(newList);
        notifyDataSetChanged();
    }

}
