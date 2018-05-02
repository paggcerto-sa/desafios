package r92.se.br.breja.service;

import java.util.List;

import r92.se.br.breja.model.Beer;
import retrofit2.Call;
import retrofit2.http.GET;

interface BeerApi {

    @GET("beers")
    Call<List<Beer>> list();

    @GET("beers/random")
    Call<Beer> getRandom();
}
