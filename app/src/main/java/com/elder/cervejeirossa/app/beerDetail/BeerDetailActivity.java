package com.elder.cervejeirossa.app.beerDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.elder.cervejeirossa.R;
import com.elder.cervejeirossa.models.AbstractBeer;
import com.elder.cervejeirossa.models.Beer;
import com.elder.cervejeirossa.models.LocalBeer;

public class BeerDetailActivity extends AppCompatActivity implements BeerDetailView {

    protected ImageView imagePicture;
    protected FloatingActionButton fab;
    protected Toolbar toolbar;
    protected TextView textName;
    protected TextView textTagline;
    protected TextView textFirstBrewed;
    protected TextView textDescription;
    protected TextView textTitleIngredients;
    protected TextView textIngredients;
    protected TextView textTitleFoodPairing;
    protected TextView textFoodPairing;
    protected TextView textTitleTips;
    protected TextView textTips;
    protected TextView textContributedBy;

    private BeerDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_beer_detail);
        initView();
        setSupportActionBar(toolbar);


        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getExtras().containsKey("beer")) {

            AbstractBeer beer = (AbstractBeer) getIntent().getExtras().get("beer");
            presenter = new BeerDetailPresenterImpl(this, this, beer);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.changeFavoriteState();
                }
            });

        } else {
            finish();
        }


    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        imagePicture = (ImageView) findViewById(R.id.imagePicture);
        textName = (TextView) findViewById(R.id.textName);
        textTagline = (TextView) findViewById(R.id.textTagline);
        textFirstBrewed = (TextView) findViewById(R.id.textFirstBrewed);
        textDescription = (TextView) findViewById(R.id.textDescription);
        textTitleIngredients = (TextView) findViewById(R.id.textTitleIngredients);
        textIngredients = (TextView) findViewById(R.id.textIngredients);
        textTitleFoodPairing = (TextView) findViewById(R.id.textTitleFoodPairing);
        textFoodPairing = (TextView) findViewById(R.id.textFoodPairing);
        textTitleTips = (TextView) findViewById(R.id.textTitleTips);
        textTips = (TextView) findViewById(R.id.textTips);
        textContributedBy = (TextView) findViewById(R.id.textContributedBy);
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

    @Override
    public void loadFavoriteButtonState(boolean isFavorite) {

        if(isFavorite){
            fab.setImageDrawable(getResources().getDrawable(android.R.drawable.star_on));
        }else{
            fab.setImageDrawable(getResources().getDrawable(android.R.drawable.star_off));
        }

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void configureView(final Beer beer) {

        Glide.with(this).load(beer.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imagePicture);

        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(beer.getName());

        textName.setText(beer.getName());
        textTagline.setText(beer.getTagline());
        textFirstBrewed.setText(beer.getFirstBrewed());
        textDescription.setText(beer.getDescription());
        textIngredients.setText(beer.getIngredients().toString());
        textFoodPairing.setText(beer.getFoodPairingAsUniqueString());
        textTips.setText(beer.getBrewersTips());
        textContributedBy.setText(String.format("%s %s", getResources().getString(R.string.by), beer.getContributedBy()));

        imagePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new  Intent(BeerDetailActivity.this, BeerPictureActivity.class);
                intent.putExtra("pictureUrl", beer.getImageUrl());
                startActivity(intent);
            }
        });

    }

    @Override
    public void configureView(final LocalBeer beer) {

        Glide.with(this).load(beer.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imagePicture);

        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(beer.getName());

        textName.setText(beer.getName());
        textTagline.setText(beer.getTagline());
        textFirstBrewed.setText(beer.getFirstBrewed());
        textDescription.setText(beer.getDescription());
        textIngredients.setText(beer.getIngredients());
        textFoodPairing.setText(beer.getFoodPairing());
        textTips.setText(beer.getBrewersTips());
        textContributedBy.setText(String.format("%s %s", getResources().getString(R.string.by), beer.getContributedBy()));

        imagePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new  Intent(BeerDetailActivity.this, BeerPictureActivity.class);
                intent.putExtra("pictureUrl", beer.getImageUrl());
                startActivity(intent);
            }
        });
    }

}
