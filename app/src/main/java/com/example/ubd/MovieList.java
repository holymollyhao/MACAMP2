package com.example.ubd;

import java.lang.reflect.Array;

public class MovieList {
    private String Title;
    private Array Cast;
    private String Director;
    private String Gross;
    private Number UBD;
    private String Image_URL;
    private String Movie_URL;
    private String Steal_Cut;
    private String Genre;
    private String Runtime;
    private String Tickets;

    public String getTitle() {
        return Title;
    }

    public Array getCast() {
        return Cast;
    }

    public String getDirector() {
        return Director;
    }

    public String getGross() {
        return Gross;
    }

    public Number getUBD() {
        return UBD;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setCast(Array cast) {
        Cast = cast;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public void setGross(String gross) {
        Gross = gross;
    }

    public void setUBD(Number UBD) {
        this.UBD = UBD;
    }

    public String getImage_URL() {
        return Image_URL;
    }
    public String getMovie_URL(){
        return Movie_URL;
    }
    public String getSteal_Cut(){
        return Steal_Cut;
    }

    public String getGenre() {
        return Genre;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getTickets() {
        return Tickets;
    }

    public MovieList(String title, Array cast, String director, String gross, Number ubd, String img_url, String movie_url, String steal_cut, String genre, String runtime, String tickets) {
        Title =title;
        Cast =cast;
        Director=director;
        Gross=gross;
        UBD=ubd;
        Image_URL =img_url;
        Movie_URL = movie_url;
        Steal_Cut=steal_cut;
        Runtime=runtime;
        Genre=genre;
        Tickets=tickets;
    }
}
