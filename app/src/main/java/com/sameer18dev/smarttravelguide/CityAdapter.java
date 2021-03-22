package com.sameer18dev.smarttravelguide;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {

    private List<city> cityList;
    private Context context;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        city cty = cityList.get(position);

        if(Integer.parseInt(cty.getTemprature()) >= 35){
            holder.img.setBackgroundResource(R.drawable.partlysunny);

        }else if(Integer.parseInt(cty.getTemprature()) <= 30){
            holder.img.setBackgroundResource(R.drawable.cloudy);

        }else if(Integer.parseInt(cty.getTemprature()) <= 25){
            holder.img.setBackgroundResource(R.drawable.rainy);

        }else if(Integer.parseInt(cty.getTemprature()) <= 20){
            holder.img.setBackgroundResource(R.drawable.rainy);
        }
        holder.name.setText("City : "+cty.getName());
        holder.temprature.setText("Temprature : "+cty.getTemprature());
        holder.humidity.setText("Humidity : "+cty.getHumidity());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, temprature, humidity;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.tempimg);
            name =  view.findViewById(R.id.name);
            temprature =  view.findViewById(R.id.temprature);
            humidity =  view.findViewById(R.id.humidity);
        }
    }



    public CityAdapter(List<city> ctyList, Context context) {
        this.cityList = ctyList;
        this.context = context;
    }













}
