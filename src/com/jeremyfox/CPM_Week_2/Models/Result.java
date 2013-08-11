package com.jeremyfox.CPM_Week_2.Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: jfox
 * Date: 8/11/13
 * Time: 10:12 AM
 */
public class Result {

    private int         id;
    private int         artistId;
    private String      artistName;
    private String      artistViewUrl;
    private String      artworkUrl100;
    private int         collectionId;
    private String      collectionName;
    private int         collectionPrice;
    private String      collectionViewUrl;
    private String      country;
    private String      currency;
    private String      kind;
    private String      primaryGenreName;
    private String      releaseDate;
    private int         trackCount;
    private int         trackId;
    private String      trackName;
    private int         trackPrice;
    private String      trackViewUrl;
    private int         searchId;
    private ArrayList<Genre> genres;
    private ArrayList<GenreId> genreIds;

    public Result() {
    }

    public Result(
            int         id,
            int         artistId,
            String      artistName,
            String      artistViewUrl,
            String      artworkUrl100,
            int         collectionId,
            String      collectionName,
            int         collectionPrice,
            String      collectionViewUrl,
            String      country,
            String      curreny,
            String      kind,
            String      primaryGenreName,
            String      releaseDate,
            int         trackCount,
            int         trackId,
            String      trackName,
            int         trackPrice,
            String      trackViewUrl,
            int         searchId,
            ArrayList<Genre> genres,
            ArrayList<GenreId> genreIds
        ) {
        this.id = id;
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistViewUrl = artistViewUrl;
        this.artworkUrl100 = artworkUrl100;
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.collectionPrice = collectionPrice;
        this.collectionViewUrl = collectionViewUrl;
        this.country = country;
        this.currency = curreny;
        this.kind = kind;
        this.primaryGenreName = primaryGenreName;
        this.releaseDate = releaseDate;
        this.trackCount = trackCount;
        this.trackId = trackId;
        this.trackName = trackName;
        this.trackPrice = trackPrice;
        this.trackViewUrl = trackViewUrl;
        this.searchId = searchId;
        this.genres = genres;
        this.genreIds = genreIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public int getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(int sollectionPrice) {
        this.collectionPrice = sollectionPrice;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public int getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(int trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    public int getSearchId() {
        return searchId;
    }

    public void setSearchId(int searchId) {
        this.searchId = searchId;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public ArrayList<GenreId> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(ArrayList<GenreId> genreIds) {
        this.genreIds = genreIds;
    }

}
