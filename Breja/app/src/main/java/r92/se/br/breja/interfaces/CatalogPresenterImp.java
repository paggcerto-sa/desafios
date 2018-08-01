package r92.se.br.breja.interfaces;

import java.util.List;

import r92.se.br.breja.model.Beer;

public interface CatalogPresenterImp {

    List<Beer> getBeerList();
    boolean isBeerFavorite(Integer id);
    void onItemClick(int position);
    void onCardClick(int position);
    void increasePage();
    void onStart();
    void onResume();
    void isVisibleToUser();
    void fabClick();
}
