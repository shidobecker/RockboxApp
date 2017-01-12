package br.com.rockbox.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Classe que representa uma musica do usuário - para que as letras sejam salvas - mesma que será salva.
 */

public class Song extends RealmObject {
    @PrimaryKey
    private long id;

    private String title;

    private String artist;

    private String urlAlbumCover;

    private String uri;

    private String lyrics;

    private String translatedLyrics;

    private long albumID;


    //Song duration
    private double startTime = 0;
    private double finalTime = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrlAlbumCover() {
        return urlAlbumCover;
    }

    public void setUrlAlbumCover(String urlAlbumCover) {
        this.urlAlbumCover = urlAlbumCover;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getTranslatedLyrics() {
        return translatedLyrics;
    }

    public void setTranslatedLyrics(String translatedLyrics) {
        this.translatedLyrics = translatedLyrics;
    }

    public long getAlbumID() {
        return albumID;
    }

    public void setAlbumID(long albumID) {
        this.albumID = albumID;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(double finalTime) {
        this.finalTime = finalTime;
    }
}
