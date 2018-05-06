package r92.se.br.breja.presenter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import r92.se.br.breja.R;
import r92.se.br.breja.activity.DetailActivity;
import r92.se.br.breja.constants.MyConstants;
import r92.se.br.breja.fragments.CatalogFragment;
import r92.se.br.breja.interfaces.CatalogPresenterImp;
import r92.se.br.breja.interfaces.CatalogViewImp;
import r92.se.br.breja.model.Beer;
import r92.se.br.breja.service.Service;
import r92.se.br.breja.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogPresenter implements CatalogPresenterImp{

    private CatalogViewImp catalogView;
    private Service service;

    private int page = 1;
    private String name = null;
    private String malt = null;

    private Integer position;
    private List<Beer> beerList;

    public CatalogPresenter(CatalogViewImp catalogView){
        this.catalogView = catalogView;
        this.beerList = new ArrayList<>();
        this.service = new Service();
    }

    public void increasePage(){
        page++;
        getBeerList(page, name, malt);
    }

    public void onStart(){
        getBeerList(page, name, malt);
    }

    public void onResume(){
        if(position != null){
            catalogView.updateItemList(position);
        }
    }

    public void isVisibleToUser(){
        catalogView.updateAllBeerList();
    }

    @Override
    public void fabClick() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(catalogView.getContext());
        LayoutInflater inflater = LayoutInflater.from(catalogView.getContext());

        final View dialogView = inflater.inflate(R.layout.dialog_search, null);
        final EditText inputName = dialogView.findViewById(R.id.inputName);
        final EditText inputMalt = dialogView.findViewById(R.id.inputMalt);

        dialog.setView(dialogView);
        dialog.setTitle(catalogView.getContext().getString(R.string.search));
        dialog.setCancelable(false);

        dialog.setNegativeButton(catalogView.getContext().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        dialog.setPositiveButton(catalogView.getContext().getString(R.string.search), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                page = 1;
                beerList = new ArrayList<>();
                String name = inputName.getText().toString();
                String malt = inputMalt.getText().toString();
                getBeerList(page, name.isEmpty() ? null : name.replace(" ", "_"), malt.isEmpty() ? null : malt.replace(" ", "_"));
            }
        });

        dialog.show();
    }

    public void updateBeerList(List<Beer> beerList){
        this.beerList.addAll(beerList);
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
        Util.updateFavoriteList(beerList.get(position).getId(), catalogView.getContext());
        catalogView.updateItemList(position);
    }

    public void onCardClick(int position){
        this.position = position;

        Intent intent = new Intent(catalogView.getContext(), DetailActivity.class);
        intent.putExtra(MyConstants.DETAIL_KEY, new Gson().toJson(beerList.get(position), Util.getBeerType()));
        intent.putExtra(MyConstants.DETAIL_KEY_SHOW_FLOAT, true);
        catalogView.getContext().startActivity(intent);
    }

    public boolean isBeerFavorite(Integer id){
        return Util.isBeerFavorite(id,catalogView.getContext());
    }

    public void getBeerList(int page, String name, String malt) {
        this.name = name;
        this.malt = malt;
        showProgress();
        service.getBeerList(page, name, malt).enqueue(new Callback() {
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
                Util.log("Fail");
            }
        });
    }
}
