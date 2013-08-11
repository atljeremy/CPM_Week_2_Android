package com.jeremyfox.CPM_Week_2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.jeremyfox.CPM_Week_2.Helpers.DataBaseHelper;
import com.jeremyfox.CPM_Week_2.Models.Result;
import com.jeremyfox.CPM_Week_2.Models.Search;

import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private DataBaseHelper dbHelper;
    private ListView listView;
    private Spinner filterSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ListView) findViewById(R.id.listView);
        filterSpinner = (Spinner) findViewById(R.id.filterSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.filter_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);
        filterSpinner.setOnItemSelectedListener(this);

        dbHelper = new DataBaseHelper(this);

        /**
         * PLEASE NOTE: This is to ensure that on every launch we start fresh with a an empty database.
         */
        dbHelper.dropAllData();

        /**
         * First create a new Search object and set a generic searchID.
         * Pass this search into the addSearch method to be stored in the databse.
         */
        Search search = new Search();
        search.setSearchId(111);
        dbHelper.addSearch(search);

        /**
         * Next we create and save 3 Result objects and set the searchId field of each to the id of the search object created above.
         */
        createFirstResult(search);
        createSecondResult(search);
        createThirdResult(search);

        /**
         * We can now query the database for the previously stored Search.
         * We know there is only going to be 1 Search in the database with an id of 1 so we can hard code that here.
         */
        search = dbHelper.getSearch(1, null, null);

        if (search != null) {
            listView.setAdapter(new ListViewAdapter(this, 0, search.getResults()));
        }
    }

    /**
     * This is the callback from the filterSpinner. Every time a new filter options is chosen, this method will be called.
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String filterChosen = (String) filterSpinner.getAdapter().getItem(pos);
        Log.d("MainActivity", filterChosen);

        String filter = null;
        String filterWHERE = null;
        switch (pos) {
            case 1: // Genre: Literature
                filter = "Literature";
                filterWHERE = "AND "+DataBaseHelper.RESULTS_PRIMARY_GENRE_NAME+" = ?";
                break;
            case 2: // Genre: Music
                filter = "Music";
                filterWHERE = "AND "+DataBaseHelper.RESULTS_PRIMARY_GENRE_NAME+" = ?";
                break;
            case 3: // Genre: Natural Sciences
                filter = "Natural Sciences";
                filterWHERE = "AND "+DataBaseHelper.RESULTS_PRIMARY_GENRE_NAME+" = ?";
                break;
            case 4: // Paid
                filter = "0";
                filterWHERE = "AND "+DataBaseHelper.RESULTS_COLLECTION_PRICE+" > ?";
                break;
            case 5: // Free
                filter = "0";
                filterWHERE = "AND "+DataBaseHelper.RESULTS_COLLECTION_PRICE+" = ?";
                break;
            case 0:
            default:
                // No filters
                break;
        }

        Search search = dbHelper.getSearch(1, filter, filterWHERE);
        if (search != null) {
            listView.setAdapter(new ListViewAdapter(this, 0, search.getResults()));
        }
    }

    /**
     * This is also a callback method of the filterSpinner. It will be called when nothing was selected.
     */
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d("MainActivity", "Nothing Selected");
    }

    private void createFirstResult(Search search) {
        Result result = new Result();
        result.setArtistId(155862268);
        result.setArtistName("Abigail Hilton");
        result.setArtistViewUrl("https://itunes.apple.com/us/artist/podiobooks.com/id155862268?mt=2&uo=4");
        result.setArtworkUrl100("http://a5.mzstatic.com/us/r30/Podcasts/v4/40/83/b0/4083b046-0b6a-d938-d12e-88ef5c0893fa/mza_6274906439174656865.100x100-75.jpg");
        result.setCollectionId(427113696);
        result.setCollectionName("The Guild of the Cowry Catchers, Book 2 Flames");
        result.setCollectionPrice(0);
        result.setCollectionViewUrl("https://itunes.apple.com/us/podcast/guild-cowry-catchers-book/id427113696?mt=2&uo=4");
        result.setCountry("USA");
        result.setCurrency("USD");
        result.setKind("podcast");
        result.setPrimaryGenreName("Literature");
        result.setReleaseDate("2011-03-18T05:50:00Z");
        result.setTrackCount(16);
        result.setTrackId(427113696);
        result.setTrackName("The Guild of the Cowry Catchers, Book 2 Flames");
        result.setTrackPrice(0);
        result.setTrackViewUrl("https://itunes.apple.com/us/podcast/guild-cowry-catchers-book/id427113696?mt=2&uo=4");
        result.setSearchId(search.getId());

        dbHelper.addResult(search, result);
    }

    private void createSecondResult(Search search) {
        Result result = new Result();
        result.setArtistId(325211830);
        result.setArtistName("Spinner.com");
        result.setArtistViewUrl("https://itunes.apple.com/us/artist/aol-media/id325211830?mt=2&uo=4");
        result.setArtworkUrl100("http://a1.mzstatic.com/us/r30/Podcasts/v4/66/44/17/664417b4-532b-4f6a-7a0e-147f6414d523/mza_4525175260731493267.100x100-75.jpg");
        result.setCollectionId(309328973);
        result.setCollectionName("MP3 of the Day: A free Spinner-approved MP3 download of artists you need to know.");
        result.setCollectionPrice(0);
        result.setCollectionViewUrl("https://itunes.apple.com/us/podcast/mp3-day-free-spinner-approved/id309328973?mt=2&uo=4");
        result.setCountry("USA");
        result.setCurrency("USD");
        result.setKind("podcast");
        result.setPrimaryGenreName("Music");
        result.setReleaseDate("2013-04-26T03:50:00Z");
        result.setTrackCount(20);
        result.setTrackId(309328973);
        result.setTrackName("MP3 of the Day: A free Spinner-approved MP3 download of artists you need to know.");
        result.setTrackPrice(5);
        result.setTrackViewUrl("https://itunes.apple.com/us/podcast/mp3-day-free-spinner-approved/id309328973?mt=2&uo=4");
        result.setSearchId(search.getId());

        dbHelper.addResult(search, result);
    }

    private void createThirdResult(Search search) {
        Result result = new Result();
        result.setArtistId(256201037);
        result.setArtistName("Fraser Cain & Dr. Pamela Gay");
        result.setArtistViewUrl("https://itunes.apple.com/us/artist/wizzard-media/id256201037?mt=2&uo=4");
        result.setArtworkUrl100("http://a5.mzstatic.com/us/r30/Features/v4/dd/dd/ad/ddddad15-13df-6813-7d05-d81e45d88d9e/mza_5442136597615139731.100x100-75.jpg");
        result.setCollectionId(191636169);
        result.setCollectionName("Astronomy Cast");
        result.setCollectionPrice(99);
        result.setCollectionViewUrl("https://itunes.apple.com/us/podcast/astronomy-cast/id191636169?mt=2&uo=4");
        result.setCountry("USA");
        result.setCurrency("USD");
        result.setKind("podcast");
        result.setPrimaryGenreName("Natural Sciences");
        result.setReleaseDate("2013-06-24T12:00:00Z");
        result.setTrackCount(300);
        result.setTrackId(191636169);
        result.setTrackName("Astronomy Cast");
        result.setTrackPrice(0);
        result.setTrackViewUrl("https://itunes.apple.com/us/podcast/astronomy-cast/id191636169?mt=2&uo=4");
        result.setSearchId(search.getId());

        dbHelper.addResult(search, result);
    }
}

class ListViewAdapter extends ArrayAdapter<Result> {

    private List<Result> items;
    private Context context;

    public ListViewAdapter(Context context, int resource, List<Result> items) {
        super(context, resource, items);
        this.items = items;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (null == view) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(android.R.layout.simple_list_item_1, null);
        }

        Result item = items.get(position);

        if (null != item) {
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            if (null != textView) {
                textView.setText(item.getArtistName());
            }
        }

        return view;
    }
}
