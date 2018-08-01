package com.elder.cervejeirossa.app.beerDetail;

import android.content.Context;

import com.elder.cervejeirossa.R;
import com.elder.cervejeirossa.database.DatabaseHelper;
import com.elder.cervejeirossa.models.AbstractBeer;
import com.elder.cervejeirossa.models.Beer;
import com.elder.cervejeirossa.models.LocalBeer;

public class BeerDetailPresenterImpl implements BeerDetailPresenter {

    private Context context;
    private DatabaseHelper databaseHelper;
    BeerDetailView beerDetailView;
    private AbstractBeer beer;

    public BeerDetailPresenterImpl(Context context, BeerDetailView beerDetailView, AbstractBeer beer) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
        this.beerDetailView = beerDetailView;
        this.beer = beer;

        verifyIsFavorite();
        if(beer.getClass().equals(Beer.class)){
            beerDetailView.configureView((Beer) this.beer);
        }else{
            beerDetailView.configureView((LocalBeer) this.beer);
        }
    }


    @Override
    public boolean verifyIsFavorite() {

        boolean state;
        if(beer.getClass().equals(Beer.class)) {
            state = databaseHelper.isFavorite(((Beer) this.beer).getId());
        }else{
            state = databaseHelper.isFavorite(((LocalBeer) this.beer).getId());
        }

        beerDetailView.loadFavoriteButtonState(state);

        return state;
    }

    @Override
    public void changeFavoriteState() {

        if(verifyIsFavorite()){
            removeFromFavorite();
        }else{
            saveAsFavorite();
        }

    }

    @Override
    public void saveAsFavorite() {

        long res;
        if(beer.getClass().equals(Beer.class)) {
            res = databaseHelper.createFavorite((Beer) this.beer);
        }else{
            res = databaseHelper.createFavorite((LocalBeer) this.beer);
        }

        if(res > 0){
            beerDetailView.showMessage(context.getResources().getString(R.string.success_adding_favorite));
            verifyIsFavorite();
        }

    }

    @Override
    public void removeFromFavorite() {

        long res;
        if(beer.getClass().equals(Beer.class)) {
            res = databaseHelper.removeFavorite(((Beer) this.beer).getId());
        }else{
            res = databaseHelper.removeFavorite(((LocalBeer) this.beer).getId());
        }

        if(res > 0){
            beerDetailView.showMessage(context.getResources().getString(R.string.success_removing_favorite));
            verifyIsFavorite();
        }
    }
}
