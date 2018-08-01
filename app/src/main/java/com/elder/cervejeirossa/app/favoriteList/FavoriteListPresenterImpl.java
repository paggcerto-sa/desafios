package com.elder.cervejeirossa.app.favoriteList;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

import com.android.volley.VolleyError;
import com.elder.cervejeirossa.app.beerList.BeerListPresenter;
import com.elder.cervejeirossa.app.beerList.BeerListView;
import com.elder.cervejeirossa.database.DatabaseHelper;
import com.elder.cervejeirossa.helpers.ApiService;
import com.elder.cervejeirossa.helpers.DataListener;
import com.elder.cervejeirossa.models.Beer;
import com.elder.cervejeirossa.models.LocalBeer;
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/* Classe que implementa a Interface Presenter da Tela de Lista de Beer  */

public class FavoriteListPresenterImpl extends OnScrollListener implements FavoriteListPresenter{

    private FavoriteListView favoriteListView;
    private DatabaseHelper databaseHelper;

    private List<LocalBeer> beerList = new ArrayList<LocalBeer>();

    FavoriteListPresenterImpl(FavoriteListView favoriteListView, Context context) {
        this.favoriteListView = favoriteListView;
        this.databaseHelper = new DatabaseHelper(context);
    }

    private void getLocalBeers(){
        favoriteListView.startLoading();
        favoriteListView.setItens(databaseHelper.getFavorites());
        favoriteListView.stopLoading();
    }

    /* Métodos da Interface View */
    @Override
    public void onResume() {
        getLocalBeers();
    }

    @Override
    public void onDestroy() {
        favoriteListView = null;
    }

}
