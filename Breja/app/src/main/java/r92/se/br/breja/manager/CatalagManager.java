package r92.se.br.breja.manager;

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
        service = new Service();
    }

    public void getBeerList(){
        service.getBeerList().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()){
                    beerList = (List<Beer>) response.body();
                    catalogFragment.updateBeerList(beerList);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
