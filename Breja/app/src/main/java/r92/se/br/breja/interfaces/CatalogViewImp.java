package r92.se.br.breja.interfaces;

import android.content.Context;

public interface CatalogViewImp {

    public void updateItemList(int position);
    public void updateBeerList();
    public void updateAllBeerList();
    public Context getContext();
    public void showProgress();
    public void dismissProgress();

}
