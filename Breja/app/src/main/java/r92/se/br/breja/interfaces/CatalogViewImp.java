package r92.se.br.breja.interfaces;

import android.content.Context;

public interface CatalogViewImp {

    void updateItemList(int position);
    void updateBeerList();
    void updateAllBeerList();
    Context getContext();
    void showProgress();
    void dismissProgress();

}
