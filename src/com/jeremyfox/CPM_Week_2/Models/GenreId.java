package com.jeremyfox.CPM_Week_2.Models;

/**
 * Created with IntelliJ IDEA.
 * User: jfox
 * Date: 8/11/13
 * Time: 10:12 AM
 */
public class GenreId {

    private int id;
    private int resultId;
    private String genreId;

    public GenreId() {
    }

    public GenreId(int id, int resultId, String genreId) {
        this.id = id;
        this.resultId = resultId;
        this.genreId = genreId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

}
