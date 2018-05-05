package r92.se.br.breja.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import r92.se.br.breja.R;
import r92.se.br.breja.adapter.CatalogAdapter;
import r92.se.br.breja.presenter.CatalogPresenter;

public class CatalogFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private CatalogAdapter catalogAdapter;
    private CatalogPresenter catalogPresenter;

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
        catalogPresenter = new CatalogPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_catalog, container, false);

        recyclerView = rootView.findViewById(R.id.catalogRV);
        progressBar = rootView.findViewById(R.id.progressBar);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        catalogAdapter = new CatalogAdapter(catalogPresenter);
        recyclerView.setAdapter(catalogAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading && (visibleItemCount + pastVisiblesItems) >= totalItemCount){
                        loading = false;
                        catalogPresenter.increasePage();
                    }
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        catalogPresenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        catalogPresenter.onResume();
    }

    public void updateBeerList(){
        loading = true;
        catalogAdapter.notifyItemRangeChanged(catalogAdapter.getItemCount(), catalogPresenter.getBeerList().size());
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
