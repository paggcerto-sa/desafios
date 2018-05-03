package r92.se.br.breja.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import r92.se.br.breja.R;
import r92.se.br.breja.model.Beer;
import r92.se.br.breja.util.Util;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogHolder>{

    private List<Beer> beerList;
    private List<Integer> favoriteList;

    private Context context;

    public CatalogAdapter(Context context){
        this.beerList = new ArrayList<>();
        this.favoriteList = Util.getFavoriteList(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CatalogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CatalogHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_catalog, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CatalogHolder holder, int position) {
        final Beer beer = beerList.get(position);
        holder.name.setText(position + " - " + beer.getName());
        holder.tagline.setText(beer.getTagline());
        holder.description.setText(beer.getDescription());

        Picasso.get().load(beer.getImageUrl()).into(holder.img);

        loadFavoriteForBeer(holder.imgFavorite, beer);
        holder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFavoriteList(beer.getId());
                loadFavoriteForBeer(holder.imgFavorite, beer);
            }
        });
    }

    private void updateFavoriteList(Integer idBeer){
        if(favoriteList.contains(idBeer)){
            Util.removeFavarite(idBeer, context);
        }else{
            Util.addFavarite(idBeer, context);
        }
        this.favoriteList = Util.getFavoriteList(context);
    }

    private void loadFavoriteForBeer(ImageView imgFavorite, Beer beer){
        if(favoriteList.contains(beer.getId())){
            imgFavorite.setImageResource(android.R.drawable.star_big_on);
        }else{
            imgFavorite.setImageResource(android.R.drawable.star_big_off);
        }
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }

    public List<Beer> getBeerList() {
        return beerList;
    }

    public void setBeerList(List<Beer> beerList) {
        this.beerList = beerList;
    }

    public class CatalogHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public ImageView imgFavorite;

        public TextView name;
        public TextView tagline;
        public TextView description;

        public CatalogHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            imgFavorite = itemView.findViewById(R.id.imgFavorite);
            name = itemView.findViewById(R.id.name);
            tagline = itemView.findViewById(R.id.tagline);
            description = itemView.findViewById(R.id.description);
        }
    }
}
