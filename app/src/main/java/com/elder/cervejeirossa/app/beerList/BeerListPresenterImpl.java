package com.elder.cervejeirossa.app.beerList;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

import com.android.volley.VolleyError;
import com.elder.cervejeirossa.helpers.ApiService;
import com.elder.cervejeirossa.helpers.DataListener;
import com.elder.cervejeirossa.models.Beer;
import com.elder.cervejeirossa.models.Filter;
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* Classe que implementa a Interface Presenter da Tela de Lista de Beer  */

public class BeerListPresenterImpl extends OnScrollListener implements BeerListPresenter, DataListener {

    private BeerListView beerListView;
    private ApiService apiService;

    private int page = 1;
    private final int ITENS_PER_PAGE = 25;
    private boolean loading = false;
    private List<Beer> beerList = new ArrayList<Beer>();

    private HashMap<Integer, Filter> filters = new HashMap<>();

    BeerListPresenterImpl(BeerListView beerListView, Context context) {
        this.beerListView = beerListView;
        this.apiService = new ApiService(context);
    }

    private void getBeers(int page){
        loading = true;
        beerListView.startLoading();
        apiService.getBeers(page, ITENS_PER_PAGE, new ArrayList<Filter>(filters.values()), this);
    }

    /* Métodos da Interface View */
    @Override
    public void onResume() {
        page = 1;
        getBeers(page);
    }

    @Override
    public void onDestroy() {
        beerListView = null;
    }

    @Override
    public void addFilter(Filter filter, int index) {
        filters.put(index, filter);
        page = 1;
        getBeers(page);
    }

    @Override
    public void removeFilter(int index) {
        filters.remove(index);
        page = 1;
        getBeers(page);
    }


    /* Callbacks da Requisição de Beer via Volley  */
    @Override
    public void onSuccess(String response) {

        loading = false;

        try{
            List<Beer> newBeerList = new Gson().fromJson(response, new TypeToken<List<Beer>>(){}.getType());

            if(page == 1){
                beerList.clear();
            }

            beerList.addAll(newBeerList);
            beerListView.setItens(beerList);

        }catch (Exception e){
            e.printStackTrace();
            beerListView.showErrorMessage("Error fetching beer list. Try again later...");
        }finally {
            beerListView.stopLoading();
        }

    }

    @Override
    public void onError(VolleyError error) {
        loading = false;

        beerListView.showErrorMessage("Connection error. Try again.");
        beerListView.stopLoading();

    }

    /* Método que cuida do Infinite Scroll do Recycler View  */

    public InfiniteScrollListener createInfiniteScrollListener(RecyclerView recyclerView) {
        return new InfiniteScrollListener(ITENS_PER_PAGE-5, (LinearLayoutManager)recyclerView.getLayoutManager()) {
            @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {

                if(!loading){
                    page++;
                    getBeers(page);
                }
            }
        };
    }

}
