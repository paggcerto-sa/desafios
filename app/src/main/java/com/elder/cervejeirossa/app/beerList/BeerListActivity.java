package com.elder.cervejeirossa.app.beerList;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elder.cervejeirossa.R;
import com.elder.cervejeirossa.adapters.BeersAdapter;
import com.elder.cervejeirossa.app.beerDetail.BeerDetailActivity;
import com.elder.cervejeirossa.app.favoriteList.FavoriteListActivity;
import com.elder.cervejeirossa.models.Beer;
import com.elder.cervejeirossa.models.Filter;

import java.util.List;

public class BeerListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BeerListView {

    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipe;
    protected NavigationView navView;
    protected TextView textNoResults;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    private BeerListPresenter presenter;

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_beer_list);
        initView();

        presenter = new BeerListPresenterImpl(this, this);
        recyclerView.setOnScrollListener(presenter.createInfiniteScrollListener(recyclerView));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_beer, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_favorites:
                startActivity(new Intent(this, FavoriteListActivity.class));
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        if (item.isChecked()) {

            presenter.removeFilter(item.getOrder());
            item.setChecked(false);

        } else {

            switch (item.getItemId()) {

                case R.id.actionBeerName:
                    callFilterTextDialog("beer_name", item);
                    break;

                case R.id.actionYeast:
                    callFilterTextDialog("yeast", item);
                    break;

                case R.id.actionFood:
                    callFilterTextDialog("food", item);
                    break;

                case R.id.actionHops:
                    callFilterTextDialog("hops", item);
                    break;

                case R.id.actionMalt:
                    callFilterTextDialog("malt", item);
                    break;
            }

        }

        drawer.closeDrawer(GravityCompat.START);
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

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        textNoResults = (TextView) findViewById(R.id.textNoResults);
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
    public void setItens(List<Beer> beerList) {
        recyclerView.swapAdapter(new BeersAdapter(this, beerList), false);
        if(beerList.size() > 0){
            textNoResults.setVisibility(View.GONE);
        }else{
            textNoResults.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openBeer(Beer beer) {

        Intent intent = new Intent(this, BeerDetailActivity.class);
        intent.putExtra("beer", beer);
        startActivity(intent);

    }

    /* Diálogo para usuário digitar o texto do filtro escolhido  */
    @Override
    public void callFilterTextDialog(final String parameter, final MenuItem item) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Filter:");

        final EditText input = new EditText(this);
        input.setGravity(Gravity.CENTER);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint(parameter);
        builder.setView(input);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String filterText = input.getText().toString();

                if (filterText.length() > 0) {

                    Filter filter = new Filter(parameter, filterText);
                    presenter.addFilter(filter, item.getOrder());
                    item.setChecked(true);
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        builder.show();
    }
}
