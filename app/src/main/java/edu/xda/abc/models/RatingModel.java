package edu.xda.abc.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingModel {
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("star")
    @Expose
    private Integer star;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("movieid")
    @Expose
    private Integer movieid;
    @SerializedName("userid")
    @Expose
    private Integer userid;

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
