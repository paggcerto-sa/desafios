package r92.se.br.breja.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import r92.se.br.breja.R;
import r92.se.br.breja.adapter.CatalogAdapter;
import r92.se.br.breja.manager.CatalagManager;
import r92.se.br.breja.model.Beer;
import r92.se.br.breja.service.Service;

public class CatalogFragment extends Fragment {

    private RecyclerView recyclerView;
    private CatalagManager catalagManager;

    public static CatalogFragment newInstance() {
        CatalogFragment fragment = new CatalogFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    public CatalogFragment() {
        catalagManager = new CatalagManager(this);
        catalagManager.getBeerList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_catalog, container, false);

        recyclerView = rootView.findViewById(R.id.catalogRV);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return rootView;
    }

    public void updateBeerList(List<Beer> beerList){
        CatalogAdapter catalogAdapter = new CatalogAdapter(beerList);
        recyclerView.setAdapter(catalogAdapter);
    }
}
