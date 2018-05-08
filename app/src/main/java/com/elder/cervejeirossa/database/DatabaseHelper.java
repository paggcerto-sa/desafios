package com.elder.cervejeirossa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.elder.cervejeirossa.models.Beer;
import com.elder.cervejeirossa.models.LocalBeer;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "cervejeiros_sa";

    //table name
    private static final String TABLE_FAV = "favorites";

    //table columns
    private static String COL_ID = "id";
    private static String COL_NAME = "name";
    private static String COL_TAGLINE = "tagline";
    private static String COL_FIRST_BREWED = "firstBrewed";
    private static String COL_DESCRIPTION = "description";
    private static String COL_FOOD_PAIRING = "foodPairing";
    private static String COL_BREWERS_TIPS = "brewersTips";
    private static String COL_CONTRIBUTED_BY = "contributedBy";
    private static String COL_INGREDIENTS = "ingredients";
    private static String COL_IMAGE_URL = "imageUrl";

    private static final String CREATE_TABLE_FAVORITES = "CREATE TABLE "
            + TABLE_FAV + "(" + COL_ID + " INTEGER PRIMARY KEY,"
            + COL_NAME + " TEXT,"
            + COL_TAGLINE + " TEXT,"
            + COL_FIRST_BREWED + " TEXT,"
            + COL_DESCRIPTION + " TEXT,"
            + COL_FOOD_PAIRING + " TEXT,"
            + COL_BREWERS_TIPS + " TEXT,"
            + COL_CONTRIBUTED_BY + " TEXT,"
            + COL_INGREDIENTS + " TEXT,"
            + COL_IMAGE_URL + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_FAVORITES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAV);

        // create new tables
        onCreate(db);
    }

    public long createFavorite(LocalBeer beer){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_ID, beer.getId());
        values.put(COL_NAME, beer.getName());
        values.put(COL_TAGLINE, beer.getTagline());
        values.put(COL_FIRST_BREWED, beer.getFirstBrewed());
        values.put(COL_DESCRIPTION, beer.getDescription());
        values.put(COL_FOOD_PAIRING, beer.getFoodPairing());
        values.put(COL_BREWERS_TIPS, beer.getBrewersTips());
        values.put(COL_CONTRIBUTED_BY, beer.getContributedBy());
        values.put(COL_INGREDIENTS, beer.getIngredients());
        values.put(COL_IMAGE_URL, beer.getImageUrl());

        long id = db.insert(TABLE_FAV, null, values);

        return id;
    }

    public long createFavorite(Beer beer){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL_ID, beer.getId());
        values.put(COL_NAME, beer.getName());
        values.put(COL_TAGLINE, beer.getTagline());
        values.put(COL_FIRST_BREWED, beer.getFirstBrewed());
        values.put(COL_DESCRIPTION, beer.getDescription());
        values.put(COL_FOOD_PAIRING, beer.getFoodPairingAsUniqueString());
        values.put(COL_BREWERS_TIPS, beer.getBrewersTips());
        values.put(COL_CONTRIBUTED_BY, beer.getContributedBy());
        values.put(COL_INGREDIENTS, beer.getIngredients().toString());
        values.put(COL_IMAGE_URL, beer.getImageUrl());

        long id = db.insert(TABLE_FAV, null, values);

        return id;
    }

    public LocalBeer getFavorite(int id){

        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_FAV + " WHERE " + COL_ID + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);

        if(c.getCount() > 0){

            if(c != null)
                c.moveToFirst();

            LocalBeer beer = new LocalBeer();

            beer.setId(c.getInt(c.getColumnIndex(COL_ID)));
            beer.setName(c.getString(c.getColumnIndex(COL_NAME)));
            beer.setTagline(c.getString(c.getColumnIndex(COL_TAGLINE)));
            beer.setFirstBrewed(c.getString(c.getColumnIndex(COL_FIRST_BREWED)));
            beer.setDescription(c.getString(c.getColumnIndex(COL_DESCRIPTION)));
            beer.setFoodPairing(c.getString(c.getColumnIndex(COL_FOOD_PAIRING)));
            beer.setBrewersTips(c.getString(c.getColumnIndex(COL_BREWERS_TIPS)));
            beer.setContributedBy(c.getString(c.getColumnIndex(COL_CONTRIBUTED_BY)));
            beer.setIngredients(c.getString(c.getColumnIndex(COL_INGREDIENTS)));
            beer.setImageUrl(c.getString(c.getColumnIndex(COL_IMAGE_URL)));

            return beer;

        }else{
            return null;
        }
    }

    public boolean isFavorite(int id){

        SQLiteDatabase db = getWritableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_FAV + " WHERE " + COL_ID + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);

        return (c.getCount() > 0);
    }

    public int removeFavorite(int id){

        SQLiteDatabase db = getWritableDatabase();

        int res = db.delete(TABLE_FAV, COL_ID + " = ?",
                new String[] { String.valueOf(id) });

        return res;
    }

    public ArrayList<LocalBeer> getFavorites(){

        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_FAV;

        Cursor c = db.rawQuery(selectQuery, null);

        ArrayList<LocalBeer> favoriteBeers = new ArrayList<>();

        if (c.moveToFirst()){

            do{

                LocalBeer beer = new LocalBeer();

                beer.setId(c.getInt(c.getColumnIndex(COL_ID)));
                beer.setName(c.getString(c.getColumnIndex(COL_NAME)));
                beer.setTagline(c.getString(c.getColumnIndex(COL_TAGLINE)));
                beer.setFirstBrewed(c.getString(c.getColumnIndex(COL_FIRST_BREWED)));
                beer.setDescription(c.getString(c.getColumnIndex(COL_DESCRIPTION)));
                beer.setFoodPairing(c.getString(c.getColumnIndex(COL_FOOD_PAIRING)));
                beer.setBrewersTips(c.getString(c.getColumnIndex(COL_BREWERS_TIPS)));
                beer.setContributedBy(c.getString(c.getColumnIndex(COL_CONTRIBUTED_BY)));
                beer.setIngredients(c.getString(c.getColumnIndex(COL_INGREDIENTS)));
                beer.setImageUrl(c.getString(c.getColumnIndex(COL_IMAGE_URL)));

                favoriteBeers.add(beer);

            }while(c.moveToNext());

        }

        return favoriteBeers;
    }
}
