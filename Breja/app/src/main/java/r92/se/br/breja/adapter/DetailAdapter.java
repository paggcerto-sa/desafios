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
import r92.se.br.breja.bean.ItemDetail;
import r92.se.br.breja.model.Beer;
import r92.se.br.breja.presenter.CatalogPresenter;
import r92.se.br.breja.presenter.DetailPresenter;
import r92.se.br.breja.util.Util;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailHolder>{

    private DetailPresenter detailPresenter;

    public DetailAdapter(DetailPresenter detailPresenter){
        this.detailPresenter = detailPresenter;
    }

    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final DetailHolder holder, final int position) {
        final ItemDetail itemDetail = detailPresenter.getListItem().get(position);

        holder.label.setText(itemDetail.getLabel());
        holder.value.setText(itemDetail.getValue());
    }

    @Override
    public int getItemCount() {
        return detailPresenter.getListItem().size();
    }

    public class DetailHolder extends RecyclerView.ViewHolder{

        public TextView label;
        public TextView value;

        public DetailHolder(View itemView) {
            super(itemView);

            label = itemView.findViewById(R.id.label);
            value = itemView.findViewById(R.id.value);
        }
    }
}