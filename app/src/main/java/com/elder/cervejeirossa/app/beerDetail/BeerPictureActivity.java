package com.elder.cervejeirossa.app.beerDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.elder.cervejeirossa.R;
import com.elder.cervejeirossa.models.AbstractBeer;

import uk.co.senab.photoview.PhotoView;

public class BeerPictureActivity extends AppCompatActivity {

    protected PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_beer_picture);
        initView();

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getExtras().containsKey("pictureUrl")) {
            loadImage(getIntent().getExtras().getString("pictureUrl"));
        }

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

    private void loadImage(String pictureUrl){

        Glide.with(this).load(pictureUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(photoView);

    }

    private void initView() {
        photoView = (PhotoView) findViewById(R.id.photo_view);
    }
}
