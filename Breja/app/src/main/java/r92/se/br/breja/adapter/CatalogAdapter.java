package r92.se.br.breja.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import r92.se.br.breja.R;
import r92.se.br.breja.model.Beer;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogHolder>{

    private List<Beer> beerList;

    public CatalogAdapter(List<Beer> beerList){
        this.beerList = beerList;
    }

    @NonNull
    @Override
    public CatalogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CatalogHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_catalog, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogHolder holder, int position) {
        Beer beer = beerList.get(position);
        holder.name.setText(beer.getName());
        holder.tagline.setText(beer.getTagline());
        holder.description.setText(beer.getDescription());
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }

    public class CatalogHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView name;
        public TextView tagline;
        public TextView description;

        public CatalogHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            tagline = itemView.findViewById(R.id.tagline);
            description = itemView.findViewById(R.id.description);
        }
    }
}
