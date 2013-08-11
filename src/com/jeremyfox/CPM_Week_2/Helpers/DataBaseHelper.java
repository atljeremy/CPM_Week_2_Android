package com.jeremyfox.CPM_Week_2.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.jeremyfox.CPM_Week_2.Models.Result;
import com.jeremyfox.CPM_Week_2.Models.Search;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jfox
 * Date: 8/11/13s
 * Time: 9:38 AM
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION               = 2;
    private static String DATABASE_NAME                     = "CPMWeekTwo";

    private static final String SEARCHES_TABLE              = "searches";
    private static final String SEARCHES_ID                 = "id";
    private static final String SEARCHES_SEARCH_ID          = "search_id";

    private static final String RESULTS_TABLE               = "results";
    private static final String RESULTS_ID                  = "id";
    private static final String RESULTS_ARTIST_ID           = "artistId";
    private static final String RESULTS_ARTIST_NAME         = "artistName";
    private static final String RESULTS_ARTIST_VIEW_URL     = "artistViewUrl";
    private static final String RESULTS_ARTWORK_URL_100     = "artworkUrl100";
    private static final String RESULTS_COLLECTION_ID       = "collectionId";
    private static final String RESULTS_COLLECTION_NAME     = "collectionName";
    public  static final String RESULTS_COLLECTION_PRICE    = "collectionPrice";
    private static final String RESULTS_COLLECTION_VIEW_URL = "collectionViewUrl";
    private static final String RESULTS_COUNTRY             = "country";
    private static final String RESULTS_CURRENCY            = "currency";
    private static final String RESULTS_KIND                = "kind";
    public  static final String RESULTS_PRIMARY_GENRE_NAME  = "primaryGenreName";
    private static final String RESULTS_RELEASE_DATE        = "releaseDate";
    private static final String RESULTS_TRACK_COUNT         = "trackCount";
    private static final String RESULTS_TRACK_ID            = "trackId";
    private static final String RESULTS_TRACK_NAME          = "trackName";
    private static final String RESULTS_TRACK_PRICE         = "trackPrice";
    private static final String RESULTS_TRACK_VIEW_URL      = "trackViewUrl";
    private static final String RESULTS_SEARCH_ID           = "searchId";

    private static final String GENRES_TABLE                = "genres";
    private static final String GENRES_ID                   = "id";
    private static final String GENRES_RESULT_ID            = "resultId";
    private static final String GENRES_GENRE                = "genre";

    private static final String GENRE_IDS_TABLE             = "genre_id_strings";
    private static final String GENRE_IDS_ID                = "id";
    private static final String GENRE_IDS_RESULT_ID         = "resultId";
    private static final String GENRE_IDS_GENRE_ID          = "genreId";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String searchSql = "CREATE TABLE " + SEARCHES_TABLE + "("+ SEARCHES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+SEARCHES_SEARCH_ID+" integer)";

        String resultsSql = "CREATE TABLE " + RESULTS_TABLE + " ("+ RESULTS_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                ""+RESULTS_ARTIST_ID+" integer, "+RESULTS_ARTIST_NAME+" varchar(255), "+RESULTS_ARTIST_VIEW_URL+" varchar(255), " +
                ""+RESULTS_ARTWORK_URL_100+" varchar(255), "+RESULTS_COLLECTION_ID+" integer, "+RESULTS_COLLECTION_NAME+" varchar(255), " +
                ""+RESULTS_COLLECTION_PRICE+" integer, "+RESULTS_COLLECTION_VIEW_URL+" varchar(255), "+RESULTS_COUNTRY+" varchar(255), " +
                ""+RESULTS_CURRENCY+" varchar(255), "+RESULTS_KIND+" varchar(255), "+RESULTS_PRIMARY_GENRE_NAME+" varchar(255), " +
                ""+RESULTS_RELEASE_DATE+" datetime, "+RESULTS_TRACK_COUNT+" integer, "+RESULTS_TRACK_ID+" integer, "+RESULTS_TRACK_NAME+" varchar(255), " +
                ""+RESULTS_TRACK_PRICE+" integer, "+RESULTS_TRACK_VIEW_URL+" varchar(255), "+RESULTS_SEARCH_ID+" integer)";

        String genreSql   = "CREATE TABLE "+GENRES_TABLE+" ("+GENRES_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+GENRES_RESULT_ID+" integer, "+GENRES_GENRE+" varchar(255))";
        String genreIdSql = "CREATE TABLE "+GENRE_IDS_TABLE+" ("+GENRE_IDS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+GENRE_IDS_RESULT_ID+" integer, "+GENRE_IDS_GENRE_ID+" varchar(255))";

        db.execSQL(searchSql);
        db.execSQL(resultsSql);
        db.execSQL(genreSql);
        db.execSQL(genreIdSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + SEARCHES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + RESULTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + GENRES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + GENRE_IDS_TABLE);

        // Create tables again
        onCreate(db);
    }

    public Search getSearch(int id, String filter, String filterWHERE) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(SEARCHES_TABLE, new String[] {SEARCHES_ID}, SEARCHES_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Search search = null;
        try {
            search = new Search(Integer.parseInt(cursor.getString(0)), 1, null);
            ArrayList<Result> results = getResults(search.getId(), filter, filterWHERE);
            search.setResults(results);
            search.setResultCount(results.size());
        } catch (CursorIndexOutOfBoundsException e) {
            // Tried to access an index that doesn't exist in the cursor
        }


        return search;
    }

    public List<Search> getAllSearches() {
        List<Search> searchList = new ArrayList<Search>();
        String selectQuery = "SELECT * FROM " + SEARCHES_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Search search = new Search();
                search.setId(Integer.parseInt(cursor.getString(0)));
                ArrayList<Result> results = getResults(search.getId(), null, null);
                search.setResults(results);
                search.setResultCount(results.size());
                searchList.add(search);
            } while (cursor.moveToNext());
        }

        return searchList;
    }

    public void addSearch(Search search) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SEARCHES_SEARCH_ID, search.getSearchId());
        long searchId = db.insert(SEARCHES_TABLE, null, values);
        db.close();
        search.setId(Integer.parseInt(String.valueOf(searchId)));
    }

    public ArrayList<Result> getResults(int searchId, String filter, String filterWHERE) {
        ArrayList<Result> resultList = new ArrayList<Result>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {
                RESULTS_ID,
                RESULTS_ARTIST_ID,
                RESULTS_ARTIST_NAME,
                RESULTS_ARTIST_VIEW_URL,
                RESULTS_ARTWORK_URL_100,
                RESULTS_COLLECTION_ID,
                RESULTS_COLLECTION_NAME,
                RESULTS_COLLECTION_PRICE,
                RESULTS_COLLECTION_VIEW_URL,
                RESULTS_COUNTRY,
                RESULTS_CURRENCY,
                RESULTS_KIND,
                RESULTS_PRIMARY_GENRE_NAME,
                RESULTS_RELEASE_DATE,
                RESULTS_TRACK_COUNT,
                RESULTS_TRACK_ID,
                RESULTS_TRACK_NAME,
                RESULTS_TRACK_PRICE,
                RESULTS_TRACK_VIEW_URL,
                RESULTS_SEARCH_ID
        };
        String[] whereArguments = { String.valueOf(searchId) };
        Cursor cursor = db.query(RESULTS_TABLE, columns, RESULTS_SEARCH_ID + "= ?", whereArguments, null, null, null);
        if (filter != null && filterWHERE != null) {
            whereArguments = new String[] { String.valueOf(searchId), filter };
            cursor = db.query(RESULTS_TABLE, columns, RESULTS_SEARCH_ID + "= ? " + filterWHERE, whereArguments, null, null, null);
        }

        if (cursor.moveToFirst()) {
            do {
                try {
                    Result result = new Result();
                    result.setId(Integer.parseInt(cursor.getString(0)));
                    result.setArtistId(Integer.parseInt(cursor.getString(1)));
                    result.setArtistName(cursor.getString(2));
                    result.setArtistViewUrl(cursor.getString(3));
                    result.setArtworkUrl100(cursor.getString(4));
                    result.setCollectionId(Integer.parseInt(cursor.getString(5)));
                    result.setCollectionName(cursor.getString(6));
                    result.setCollectionPrice(Integer.parseInt(cursor.getString(7)));
                    result.setCollectionViewUrl(cursor.getString(8));
                    result.setCountry(cursor.getString(9));
                    result.setCurrency(cursor.getString(10));
                    result.setKind(cursor.getString(11));
                    result.setPrimaryGenreName(cursor.getString(12));
                    result.setReleaseDate(cursor.getString(13));
                    result.setTrackCount(Integer.parseInt(cursor.getString(14)));
                    result.setTrackId(Integer.parseInt(cursor.getString(15)));
                    result.setTrackName(cursor.getString(16));
                    result.setTrackPrice(Integer.parseInt(cursor.getString(17)));
                    result.setTrackViewUrl(cursor.getString(18));
                    result.setSearchId(searchId);
                    resultList.add(result);
                } catch (CursorIndexOutOfBoundsException e) {
                    // Tried to access an index that doesn't exist in the cursor
                }

            } while (cursor.moveToNext());
        }

        return resultList;
    }

    public void addResult(Search search, Result result) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RESULTS_ARTIST_ID,           result.getArtistId());
        values.put(RESULTS_ARTIST_NAME,         result.getArtistName());
        values.put(RESULTS_ARTIST_VIEW_URL,     result.getArtistViewUrl());
        values.put(RESULTS_ARTWORK_URL_100,     result.getArtworkUrl100());
        values.put(RESULTS_COLLECTION_ID,       result.getCollectionId());
        values.put(RESULTS_COLLECTION_NAME,     result.getCollectionName());
        values.put(RESULTS_COLLECTION_PRICE,    result.getCollectionPrice());
        values.put(RESULTS_COLLECTION_VIEW_URL, result.getCollectionViewUrl());
        values.put(RESULTS_COUNTRY,             result.getCountry());
        values.put(RESULTS_CURRENCY,            result.getCurrency());
        values.put(RESULTS_KIND,                result.getKind());
        values.put(RESULTS_PRIMARY_GENRE_NAME,  result.getPrimaryGenreName());
        values.put(RESULTS_RELEASE_DATE,        result.getReleaseDate());
        values.put(RESULTS_TRACK_COUNT,         result.getTrackCount());
        values.put(RESULTS_TRACK_ID,            result.getTrackId());
        values.put(RESULTS_TRACK_NAME,          result.getTrackName());
        values.put(RESULTS_TRACK_PRICE,         result.getTrackPrice());
        values.put(RESULTS_TRACK_VIEW_URL,      result.getTrackViewUrl());
        values.put(RESULTS_SEARCH_ID,           search.getId());

        long resultId = db.insert(RESULTS_TABLE, null, values);
        Log.d("DataBaseHelper", "New Result ID: " + String.valueOf(resultId));
        db.close();
    }

    public void dropAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + SEARCHES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + RESULTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + GENRES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + GENRE_IDS_TABLE);
        onCreate(db);
    }

}