package com.jeremyfox.CPM_Week_2.Models;

/**
 * Created with IntelliJ IDEA.
 * User: jfox
 * Date: 8/11/13
 * Time: 10:12 AM
 */
public class Genre {

    private int id;
    private int resultId;
    private String genre;

    public Genre() {
    }

    public Genre(int id, int resultId, String genre) {
        this.id = id;
        this.resultId = resultId;
        this.genre = genre;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
