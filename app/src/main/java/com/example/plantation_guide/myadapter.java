package com.example.plantation_guide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model)
    {
        holder.title.setText(model.getTitle());
        holder.details.setText(model.getDetails());
        Glide.with(holder.img.getContext()).load(model.getImage()).into(holder.img);
        holder.sunlight.setText(model.getSunlight());
        holder.water.setText(model.getWater());
        holder.humidity.setText(model.getHumidity());
        holder.temperature.setText(model.getTemperature());



    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.plants,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView title,details,sunlight,water,humidity,temperature;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.rimageview);
            title=(TextView)itemView.findViewById(R.id.rTitleView);
            details=(TextView)itemView.findViewById(R.id.details);

           sunlight=(TextView)itemView.findViewById(R.id.details1);

            water=(TextView)itemView.findViewById(R.id.details2);

           humidity=(TextView)itemView.findViewById(R.id.details3);

            temperature=(TextView)itemView.findViewById(R.id.details4);




        }


    }

}