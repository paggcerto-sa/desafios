package r92.se.br.breja.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import r92.se.br.breja.fragments.CatalogFragment;
import r92.se.br.breja.model.Beer;
import r92.se.br.breja.service.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalagManager {

    private List<Beer> beerList;
    private Service service;

    private CatalogFragment catalogFragment;

    public CatalagManager(CatalogFragment catalogFragment){
        this.catalogFragment = catalogFragment;
        this.beerList = new ArrayList<>();
        service = new Service();
    }

    public void getBeerList(int page){
        catalogFragment.showProgress();
        service.getBeerList(page).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()){
                    beerList.addAll((Collection<? extends Beer>) response.body());
                    catalogFragment.updateBeerList(beerList);
                    catalogFragment.dismissProgress();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
