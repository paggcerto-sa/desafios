package r92.se.br.breja.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import r92.se.br.breja.R;
import r92.se.br.breja.adapter.CatalogAdapter;
import r92.se.br.breja.manager.CatalagManager;
import r92.se.br.breja.model.Beer;
import r92.se.br.breja.util.Util;

public class CatalogFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private CatalagManager catalagManager;
    private CatalogAdapter catalogAdapter;

    private int page = 1;
    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;

    private LinearLayoutManager layoutManager;

    public static CatalogFragment newInstance() {
        CatalogFragment fragment = new CatalogFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    public CatalogFragment() {
        catalagManager = new CatalagManager(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_catalog, container, false);

        recyclerView = rootView.findViewById(R.id.catalogRV);
        progressBar = rootView.findViewById(R.id.progressBar);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading){
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount){
                            loading = false;
                            Util.log("Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                            increasePage();
                        }
                    }
                }
            }
        });

        return rootView;
    }

    private void increasePage(){
        page++;
        catalagManager.getBeerList(page);
        loading = true;
    }

    @Override
    public void onStart() {
        super.onStart();
        catalagManager.getBeerList(page);
    }

    public void updateBeerList(List<Beer> beerList){
        if(catalogAdapter == null){
            catalogAdapter = new CatalogAdapter(getContext());
            recyclerView.setAdapter(catalogAdapter);
        }
        catalogAdapter.setBeerList(beerList);

        int beerListSize = catalogAdapter.getItemCount();
        catalogAdapter.notifyItemRangeChanged(beerListSize, beerList.size());
    }

    public void showProgress(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void dismissProgress(){
        progressBar.setVisibility(View.GONE);
    }
}
