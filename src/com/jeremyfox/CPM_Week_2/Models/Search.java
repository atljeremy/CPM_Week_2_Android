package com.jeremyfox.CPM_Week_2.Models;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: jfox
 * Date: 8/11/13
 * Time: 10:11 AM
 */
public class Search {

    private int id;
    private int resultCount;
    private int searchId;
    private ArrayList<Result> results;

    public Search() {
    }

    public Search(int id, int resultCount, ArrayList<Result> results) {
        this.id = id;
        this.resultCount = resultCount;
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public int getSearchId() {
        return searchId;
    }

    public void setSearchId(int searchId) {
        this.searchId = searchId;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

}
