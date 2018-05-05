package r92.se.br.breja.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import r92.se.br.breja.R;
import r92.se.br.breja.adapter.CatalogAdapter;
import r92.se.br.breja.interfaces.CatalogViewImp;
import r92.se.br.breja.presenter.CatalogPresenter;
import r92.se.br.breja.presenter.FavoritePresenter;

public class FavoritesFragment extends Fragment implements CatalogViewImp {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private CatalogAdapter catalogAdapter;

    private FavoritePresenter favoritePresenter;

    private LinearLayoutManager layoutManager;

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    public FavoritesFragment() {
        favoritePresenter = new FavoritePresenter(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getView() != null){
            FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.fab);
            floatingActionButton.setVisibility(View.GONE);
            if (isVisibleToUser) {
                favoritePresenter.isVisibleToUser();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_catalog, container, false);

        recyclerView = rootView.findViewById(R.id.catalogRV);
        progressBar = rootView.findViewById(R.id.progressBar);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        catalogAdapter = new CatalogAdapter(favoritePresenter);
        recyclerView.setAdapter(catalogAdapter);

        return rootView;
    }

    public void updateBeerList(){
        catalogAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateAllBeerList() {

    }

    public void updateItemList(int position){
        catalogAdapter.notifyItemChanged(position);
    }

    public void showProgress(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void dismissProgress(){
        progressBar.setVisibility(View.GONE);
    }
}
