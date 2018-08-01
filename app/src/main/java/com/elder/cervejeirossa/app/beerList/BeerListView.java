package com.elder.cervejeirossa.app.beerList;

import android.view.MenuItem;

import com.elder.cervejeirossa.models.Beer;

import java.util.List;

public interface BeerListView {

    void startLoading();

    void stopLoading();

    void setItens(List<Beer> beerList);

    void showErrorMessage(String message);

    void openBeer(Beer beer);

    void callFilterTextDialog(String parameter, MenuItem item);
}
