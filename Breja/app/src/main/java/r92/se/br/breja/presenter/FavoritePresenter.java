package r92.se.br.breja.presenter;

import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import r92.se.br.breja.activity.DetailActivity;
import r92.se.br.breja.constants.MyConstants;
import r92.se.br.breja.interfaces.CatalogPresenterImp;
import r92.se.br.breja.interfaces.CatalogViewImp;
import r92.se.br.breja.model.Beer;
import r92.se.br.breja.service.Service;
import r92.se.br.breja.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritePresenter implements CatalogPresenterImp{

    private CatalogViewImp catalogView;
    private Service service;

    private List<Beer> beerList;

    public FavoritePresenter(CatalogViewImp catalogView){
        this.catalogView = catalogView;
        this.beerList = new ArrayList<>();
        this.service = new Service();
    }

    @Override
    public void isVisibleToUser() {
        getBeerFavoriteList();
    }

    @Override
    public void fabClick() {

    }

    public void updateBeerList(List<Beer> beerList){
        this.beerList = beerList;
        catalogView.updateBeerList();
    }

    public void showProgress(){
        catalogView.showProgress();
    }

    public void dismissProgress(){
        catalogView.dismissProgress();
    }

    public List<Beer> getBeerList() {
        return beerList;
    }

    public void onItemClick(int position){
        Util.removeFavarite(beerList.get(position).getId(), catalogView.getContext());
        beerList.remove(position);
        catalogView.updateBeerList();
    }

    public void onCardClick(int position){
        Intent intent = new Intent(catalogView.getContext(), DetailActivity.class);
        intent.putExtra(MyConstants.DETAIL_KEY, new Gson().toJson(beerList.get(position), Util.getBeerType()));
        intent.putExtra(MyConstants.DETAIL_KEY_SHOW_FLOAT, false);
        catalogView.getContext().startActivity(intent);
    }

    @Override
    public void increasePage() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    public boolean isBeerFavorite(Integer id){
        return Util.isBeerFavorite(id,catalogView.getContext());
    }

    public void getBeerFavoriteList(){
        String ids = getStringIds();
        Util.log(ids);
        if(ids == null || ids.isEmpty()){
            return;
        }

        showProgress();
        service.getBeerListByIds(ids).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()){
                    List<Beer> beerList = (List<Beer>) response.body();
                    updateBeerList(beerList);
                    dismissProgress();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Util.log(t.getMessage());
            }
        });
    }

    public String getStringIds(){
        return TextUtils.join("|", Util.getFavoriteList(catalogView.getContext()));
    }
}
