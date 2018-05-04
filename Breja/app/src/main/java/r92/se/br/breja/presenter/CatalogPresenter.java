package r92.se.br.breja.presenter;

import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import r92.se.br.breja.activity.DetailActivity;
import r92.se.br.breja.constants.MyConstants;
import r92.se.br.breja.fragments.CatalogFragment;
import r92.se.br.breja.model.Beer;
import r92.se.br.breja.service.Service;
import r92.se.br.breja.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogPresenter {

    private CatalogFragment catalogFragment;
    private Service service;

    private int page = 1;

    private List<Beer> beerList;

    public CatalogPresenter(CatalogFragment catalogFragment){
        this.catalogFragment = catalogFragment;
        this.beerList = new ArrayList<>();
        this.service = new Service();
    }

    public void increasePage(){
        page++;
        getBeerList(page);
    }

    public void onStart(){
        getBeerList(page);
    }

    public void updateBeerList(List<Beer> beerList){
        this.beerList.addAll(beerList);
        catalogFragment.updateBeerList();
    }

    public void showProgress(){
        catalogFragment.showProgress();
    }

    public void dismissProgress(){
        catalogFragment.dismissProgress();
    }

    public List<Beer> getBeerList() {
        return beerList;
    }

    public void onItemClick(int position){
        updateFavoriteList(beerList.get(position).getId());
        catalogFragment.updateItemList(position);
    }

    public void onCardClick(int position){
        Intent intent = new Intent(catalogFragment.getContext(), DetailActivity.class);

        intent.putExtra(MyConstants.DETAIL_KEY, new Gson().toJson(beerList.get(position), Util.getBeerType()));

        catalogFragment.getContext().startActivity(intent);
    }

    private void updateFavoriteList(Integer idBeer){
        if(isBeerFavorite(idBeer)){
            Util.removeFavarite(idBeer, catalogFragment.getContext());
        }else{
            Util.addFavarite(idBeer, catalogFragment.getContext());
        }
    }

    public boolean isBeerFavorite(int idBeer){
        if(Util.getFavoriteList(catalogFragment.getContext()).contains(idBeer)){
            return true;
        }else{
            return false;
        }
    }

    public void getBeerList(int page){
        Util.log("inicio");
        showProgress();
        service.getBeerList(page).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Util.log("inicio2");
                if(response.isSuccessful()){
                    List<Beer> beerList = (List<Beer>) response.body();
                    updateBeerList(beerList);
                    dismissProgress();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Util.log("Fail");
            }
        });
    }
}
