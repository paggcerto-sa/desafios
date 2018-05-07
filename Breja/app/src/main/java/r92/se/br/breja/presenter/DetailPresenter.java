package r92.se.br.breja.presenter;

import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import r92.se.br.breja.bean.ItemDetail;
import r92.se.br.breja.constants.MyConstants;
import r92.se.br.breja.interfaces.DetailPresenterImp;
import r92.se.br.breja.interfaces.DetailViewImp;
import r92.se.br.breja.model.Beer;
import r92.se.br.breja.util.Util;

public class DetailPresenter implements DetailPresenterImp{

    private DetailViewImp detailView;
    private Beer beer;
    private List<ItemDetail> listItem;

    public DetailPresenter(DetailViewImp detailView, Intent intent){
        this.detailView = detailView;

        String beerGson = intent.getExtras().getString(MyConstants.DETAIL_KEY);
        beer = new Gson().fromJson(beerGson, Util.getBeerType());

        Boolean showFab = intent.getExtras().getBoolean(MyConstants.DETAIL_KEY_SHOW_FLOAT);
        if(showFab){
            detailView.updateVisibilityFab(View.VISIBLE);
        }else{
            detailView.updateVisibilityFab(View.GONE);
        }

        detailView.updateImage(beer.getImageUrl());
        detailView.updateName(beer.getName());
        updateFabIcon();

        this.listItem = beer.getListForDetail();
    }

    public void floatClick(){
        Util.updateFavoriteList(beer.getId(), detailView.getContext());
        updateFabIcon();
    }

    private void updateFabIcon(){
        if(Util.isBeerFavorite(beer.getId(), detailView.getContext())){
            detailView.updateFabIcon(Util.getIdImgFavarite());
        }else{
            detailView.updateFabIcon(Util.getIdImgNotFavarite());
        }
    }

    public List<ItemDetail> getListItem() {
        return listItem;
    }
}
