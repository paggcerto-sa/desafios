package r92.se.br.breja.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Util {

    private static final String PREFS_NAME = "BREJAPREFS";
    private static final String FAVORITE_KEY = "FAVORITE";

    public static void log(String msg){
        Log.e("App", msg);
    }

    private static SharedPreferences getSharedPreferences(Context context){
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return settings;
    }

    public static List<Integer> getFavoriteList(Context context){
        SharedPreferences settings = getSharedPreferences(context);
        String favoriteGson = settings.getString(FAVORITE_KEY, null);

        if(favoriteGson != null){
            Type listType = new TypeToken<ArrayList<Integer>>(){}.getType();
            return new Gson().fromJson(favoriteGson, listType);
        }else{
            return new ArrayList<>();
        }
    }

    public static void saveFavoriteList(List<Integer> favoriteList, Context context){
        SharedPreferences settings = getSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(FAVORITE_KEY, new Gson().toJson(favoriteList));
        editor.commit();
    }

    public static void addFavarite(Integer idBeer, Context context){
        List<Integer> favoriteList = getFavoriteList(context);
        favoriteList.add(idBeer);
        saveFavoriteList(favoriteList, context);
    }

    public static void removeFavarite(Integer idBeer, Context context){
        List<Integer> favoriteList = getFavoriteList(context);
        favoriteList.remove(idBeer);
        saveFavoriteList(favoriteList, context);
    }
}
