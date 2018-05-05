package r92.se.br.breja.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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
import r92.se.br.breja.presenter.CatalogPresenter;
import r92.se.br.breja.util.Util;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogHolder>{

    private CatalogPresenter catalogPresenter;

    public CatalogAdapter(CatalogPresenter catalogPresenter){
        this.catalogPresenter = catalogPresenter;
    }

    @NonNull
    @Override
    public CatalogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CatalogHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_catalog, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CatalogHolder holder, final int position) {
        final Beer beer = catalogPresenter.getBeerList().get(position);
        holder.name.setText(position + " - " + beer.getName());
        holder.tagline.setText(beer.getTagline());
        holder.description.setText(beer.getDescription());

        Picasso.get().load(beer.getImageUrl()).into(holder.img);

        if(catalogPresenter.isBeerFavorite(beer.getId())){
            holder.imgFavorite.setImageResource(Util.getIdImgFavarite());
        }else{
            holder.imgFavorite.setImageResource(Util.getIdImgNotFavarite());
        }

        holder.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catalogPresenter.onItemClick(position);
            }
        });

        holder.cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catalogPresenter.onCardClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catalogPresenter.getBeerList().size();
    }

    public class CatalogHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public ImageView imgFavorite;

        public TextView name;
        public TextView tagline;
        public TextView description;

        public ConstraintLayout cardLayout;

        public CatalogHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            imgFavorite = itemView.findViewById(R.id.imgFavorite);
            name = itemView.findViewById(R.id.name);
            tagline = itemView.findViewById(R.id.tagline);
            description = itemView.findViewById(R.id.description);
            cardLayout = itemView.findViewById(R.id.cardLayout);
        }
    }
}
