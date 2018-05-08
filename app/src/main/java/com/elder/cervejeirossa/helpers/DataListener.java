package com.elder.cervejeirossa.helpers;

import com.android.volley.VolleyError;

public interface DataListener {

    public void onSuccess(String response);

    public void onError(VolleyError error);

}
