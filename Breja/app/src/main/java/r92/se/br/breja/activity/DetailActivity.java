package r92.se.br.breja.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import r92.se.br.breja.R;
import r92.se.br.breja.adapter.DetailAdapter;
import r92.se.br.breja.presenter.DetailPresenter;

public class DetailActivity extends AppCompatActivity {

    private DetailPresenter detailPresenter;

    private TextView name;
    private ImageView beerIv;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private LinearLayoutManager layoutManager;
    private DetailAdapter detailAdapter;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        loadViews();

        detailPresenter = new DetailPresenter(this, getIntent());

        layoutManager = new LinearLayoutManager(this);
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
        this.progressBar = findViewById(R.id.progressBar);
        this.fab = findViewById(R.id.fab);
    }

    public void updateName(String name){
        this.name.setText(name);
    }

    public void updateImage(String url){
        Picasso.get().load(url).into(this.beerIv);
    }

    public void updateFabIcon(int idResource){
        fab.setImageResource(idResource);
    }

    public void updateVisibilityFab(Integer id){
        this.fab.setVisibility(id);
    }
}
