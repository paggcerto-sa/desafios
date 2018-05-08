package com.elder.cervejeirossa.app.favoriteList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.elder.cervejeirossa.R;
import com.elder.cervejeirossa.adapters.BeersAdapter;
import com.elder.cervejeirossa.adapters.FavoriteBeersAdapter;
import com.elder.cervejeirossa.app.beerDetail.BeerDetailActivity;
import com.elder.cervejeirossa.app.beerList.BeerListPresenter;
import com.elder.cervejeirossa.app.beerList.BeerListPresenterImpl;
import com.elder.cervejeirossa.app.beerList.BeerListView;
import com.elder.cervejeirossa.models.Beer;
import com.elder.cervejeirossa.models.LocalBeer;

import java.util.List;

public class FavoriteListActivity extends AppCompatActivity implements FavoriteListView {

    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipe;

    private FavoriteListPresenter presenter;

    @Override protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_favorite_list);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initView();

        presenter = new FavoriteListPresenterImpl(this, this);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }

    /* Inicia as views da tela  */
    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onResume();
            }
        });
    }

    /* Implementação dos Métodos da Interface BeerListView  */

    @Override
    public void startLoading() {
        swipe.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        swipe.setRefreshing(false);
    }

    @Override
    public void setItens(List<LocalBeer> beerList) {
        recyclerView.swapAdapter(new FavoriteBeersAdapter(this, beerList), false);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openBeer(LocalBeer beer) {

        Intent intent = new Intent(this, BeerDetailActivity.class);
        intent.putExtra("beer", beer);
        startActivity(intent);

    }
}
