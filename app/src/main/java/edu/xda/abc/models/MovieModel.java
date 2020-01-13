package edu.xda.abc.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieModel {
    @SerializedName("movieid")
    @Expose
    private Integer movieid;
    @SerializedName("moviename")
    @Expose
    private String moviename;
    @SerializedName("movieimage")
    @Expose
    private String movieimage;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("nation")
    @Expose
    private String nation;
    @SerializedName("trailer")
    @Expose
    private String trailer;
    @SerializedName("releasedate")
    @Expose
    private String releasedate;
    @SerializedName("backgroundimage")
    @Expose
    private String backgroundimage;
    @SerializedName("category")
    @Expose
    private String category;

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getMovieimage() {
        return movieimage;
    }

    public void setMovieimage(String movieimage) {
        this.movieimage = movieimage;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getBackgroundimage() {
        return backgroundimage;
    }

    public void setBackgroundimage(String backgroundimage) {
        this.backgroundimage = backgroundimage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
