package r92.se.br.breja.service;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static final String url = "https://api.punkapi.com/v2/";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Call getBeerList(Integer page, String name, String malt){
        return retrofit.create(BeerApi.class).getList(page, name, malt);
    }

    public Call getBeerListByIds(String ids){
        return retrofit.create(BeerApi.class).getByIds(ids);
    }
}