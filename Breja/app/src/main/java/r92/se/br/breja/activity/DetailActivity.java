package r92.se.br.breja.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import r92.se.br.breja.R;
import r92.se.br.breja.adapter.DetailAdapter;
import r92.se.br.breja.interfaces.DetailPresenterImp;
import r92.se.br.breja.interfaces.DetailViewImp;
import r92.se.br.breja.presenter.DetailPresenter;

public class DetailActivity extends AppCompatActivity implements DetailViewImp{

    private DetailPresenterImp detailPresenter;

    private TextView name;
    private ImageView beerIv;

    private RecyclerView recyclerView;

    private DetailAdapter detailAdapter;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        loadViews();

        detailPresenter = new DetailPresenter(this, getIntent());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        detailAdapter = new DetailAdapter(detailPresenter);
        recyclerView.setAdapter(detailAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailPresenter.floatClick();
            }
        });
    }

    private void loadViews(){
        this.name = findViewById(R.id.name);
        this.beerIv = findViewById(R.id.beerIv);
        this.recyclerView = findViewById(R.id.detailRV);
        this.fab = findViewById(R.id.fab);
    }

    @Override
    public void updateName(String name){
        this.name.setText(name);
    }

    @Override
    public void updateImage(String url){
        Picasso.get().load(url).into(this.beerIv);
    }

    @Override
    public void updateFabIcon(int idResource){
        fab.setImageResource(idResource);
    }

    @Override
    public void updateVisibilityFab(Integer id){
        this.fab.setVisibility(id);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
