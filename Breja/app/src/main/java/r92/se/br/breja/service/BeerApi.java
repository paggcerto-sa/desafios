package r92.se.br.breja.service;

import java.util.List;

import r92.se.br.breja.model.Beer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface BeerApi {

    @GET("beers")
    Call<List<Beer>> getList(@Query("page") int page);

    @GET("beers/{id}")
    Call<Beer> getById(@Path("id") int page);
}
