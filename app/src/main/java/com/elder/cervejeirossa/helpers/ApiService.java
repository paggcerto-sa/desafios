package com.elder.cervejeirossa.helpers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.elder.cervejeirossa.models.Filter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ApiService {

    private Context context;
    private RequestQueue mRequestQueue;

    private final String API_URL = "https://api.punkapi.com/v2/";
    private final String BEERS_URL = API_URL + "beers";

    private String getBeersURL(int page, int perPage, ArrayList<Filter> filters){

        StringBuilder sb = new StringBuilder();

        sb.append(API_URL);
        sb.append("beers?page=");
        sb.append(String.valueOf(page));
        sb.append("&per_page=");
        sb.append(String.valueOf(perPage));
        if(filters.size() > 0){

            for (Filter filter: filters) {
                sb.append("&").append(filter.getParameter()).append("=").append(filter.getValue());
            }

        }

        return sb.toString().replaceAll(" ", "%20");

    }


    public ApiService(Context context){

        this.context = context;
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public void getBeers(final int page, final int perPage, ArrayList<Filter> filters, final DataListener dataListener) {

        StringRequest request = new StringRequest(Request.Method.GET, getBeersURL(page, perPage, filters),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        dataListener.onSuccess(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ApiService", "Error: " + error.getMessage());
                dataListener.onError(error);
            }

        });

        request.setRetryPolicy(new DefaultRetryPolicy(
                3600,
                3,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(request);
    }

}
