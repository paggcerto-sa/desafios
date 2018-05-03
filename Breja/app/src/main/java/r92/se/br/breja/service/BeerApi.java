package r92.se.br.breja.service;

import java.util.List;

import r92.se.br.breja.model.Beer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface BeerApi {

    @GET("beers")
    Call<List<Beer>> list(@Query("page") int page);

    @GET("beers/random")
    Call<Beer> getRandom();
}
