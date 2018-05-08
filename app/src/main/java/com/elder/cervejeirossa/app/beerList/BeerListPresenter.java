package com.elder.cervejeirossa.app.beerList;

import android.support.v7.widget.RecyclerView;

import com.elder.cervejeirossa.models.Filter;
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;

public interface BeerListPresenter {

    void onResume();

    //void onItemClicked(int position);

    void onDestroy();

    void addFilter(Filter filter, int index);

    void removeFilter(int index);

    InfiniteScrollListener createInfiniteScrollListener(RecyclerView recyclerView);
}
