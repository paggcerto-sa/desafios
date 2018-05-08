package com.elder.cervejeirossa.app.beerDetail;

import com.elder.cervejeirossa.models.Beer;
import com.elder.cervejeirossa.models.LocalBeer;

public interface BeerDetailView {

    void loadFavoriteButtonState(boolean isFavorite);

    void showMessage(String message);

    void configureView(Beer beer);

    void configureView(LocalBeer beer);
}
