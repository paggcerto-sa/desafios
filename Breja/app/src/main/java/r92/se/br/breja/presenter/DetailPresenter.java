package r92.se.br.breja.presenter;

import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import r92.se.br.breja.activity.DetailActivity;
import r92.se.br.breja.bean.ItemDetail;
import r92.se.br.breja.constants.MyConstants;
import r92.se.br.breja.model.Beer;
import r92.se.br.breja.util.Util;

public class DetailPresenter {

    private DetailActivity detailActivity;
    private Beer beer;
    private List<ItemDetail> listItem;

    public DetailPresenter(DetailActivity detailActivity, Intent intent){
        this.detailActivity = detailActivity;

        String beerGson = intent.getExtras().getString(MyConstants.DETAIL_KEY);
        beer = new Gson().fromJson(beerGson, Util.getBeerType());

        Boolean showFab = intent.getExtras().getBoolean(MyConstants.DETAIL_KEY_SHOW_FLOAT);
        if(showFab){
            detailActivity.updateVisibilityFab(View.VISIBLE);
        }else{
            detailActivity.updateVisibilityFab(View.GONE);
        }

        detailActivity.updateImage(beer.getImageUrl());
        detailActivity.updateName(beer.getName());
        updateFabIcon();

        this.listItem = beer.getListForDetail();
    }

    public void floatClick(){
        Util.updateFavoriteList(beer.getId(), detailActivity);
        updateFabIcon();
    }

    private void updateFabIcon(){
        if(Util.isBeerFavorite(beer.getId(), detailActivity)){
            detailActivity.updateFabIcon(Util.getIdImgFavarite());
        }else{
            detailActivity.updateFabIcon(Util.getIdImgNotFavarite());
        }
    }

    public List<ItemDetail> getListItem() {
        return listItem;
    }

    public void setListItem(List<ItemDetail> listItem) {
        this.listItem = listItem;
    }
}
