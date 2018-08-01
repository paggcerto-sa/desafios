package com.elder.cervejeirossa.app.favoriteList;

import com.elder.cervejeirossa.models.Beer;
import com.elder.cervejeirossa.models.LocalBeer;

import java.util.List;

public interface FavoriteListView {

    void startLoading();

    void stopLoading();

    void setItens(List<LocalBeer> beerList);

    void showErrorMessage(String message);

    void openBeer(LocalBeer beer);
}
