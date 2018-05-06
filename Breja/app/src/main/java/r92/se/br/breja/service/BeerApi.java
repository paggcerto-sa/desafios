package r92.se.br.breja.service;

import java.util.List;

import r92.se.br.breja.model.Beer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface BeerApi {

    @GET("beers")
    Call<List<Beer>> getList(@Query("page") Integer page, @Query("beer_name") String name, @Query("malt") String malt);

    @GET("beers")
    Call<List<Beer>> getByIds(@Query("ids") String ids);
}