package com.elder.cervejeirossa.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.elder.cervejeirossa.R;

import com.elder.cervejeirossa.app.beerList.BeerListActivity;
import com.elder.cervejeirossa.models.Beer;

import java.util.List;

public class BeersAdapter extends RecyclerView.Adapter<BeersAdapter.ViewHolder>{

    private Context context;
    private List<Beer> beerList;

    public BeersAdapter(Context context, List<Beer> beerList) {
        this.context = context;
        this.beerList = beerList;
    }

    @Override
    public BeersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.beer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BeersAdapter.ViewHolder holder, int position) {

        holder.textName.setText(beerList.get(position).getName());
        holder.textTagline.setText(beerList.get(position).getTagline());
        holder.textFirstBrewed.setText(beerList.get(position).getFirstBrewed());

        holder.loadPicture(beerList.get(position).getImageUrl());

        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textName;
        private TextView textTagline;
        private TextView textFirstBrewed;
        private ImageView imagePicture;
        private int position;

        private ViewHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.textName);
            textTagline = (TextView) itemView.findViewById(R.id.textTagline);
            textFirstBrewed = (TextView) itemView.findViewById(R.id.textFirstBrewed);
            imagePicture = (ImageView) itemView.findViewById(R.id.imagePicture);


            itemView.setOnClickListener(this);
        }

        private void loadPicture(String imageUrl){
            Glide.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(imagePicture);
        }


        @Override
        public void onClick(View view) {
            ((BeerListActivity)context).openBeer(beerList.get(position));
        }
    }
}
