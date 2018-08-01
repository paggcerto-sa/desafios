package com.elder.cervejeirossa.app.beerDetail;

public interface BeerDetailPresenter {

    boolean verifyIsFavorite();

    void changeFavoriteState();

    void saveAsFavorite();

    void removeFromFavorite();
}
