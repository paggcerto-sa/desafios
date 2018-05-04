package r92.se.br.breja.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import r92.se.br.breja.R;
import r92.se.br.breja.constants.MyConstants;
import r92.se.br.breja.model.Beer;
import r92.se.br.breja.util.Util;

public class DetailActivity extends AppCompatActivity {

    private Beer beer;

    private TextView name;
    private ImageView beerIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String beerGson = getIntent().getExtras().getString(MyConstants.DETAIL_KEY);
        this.beer = new Gson().fromJson(beerGson, Util.getBeerType());

        Util.log(this.beer.getName());

        loadViews();
    }

    private void loadViews(){
        this.name = findViewById(R.id.name);
        this.beerIv = findViewById(R.id.beerIv);

        this.name.setText(this.beer.getName());

        Picasso.get().load(this.beer.getImageUrl()).into(this.beerIv);
    }
}
