package edu.xda.abc.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActorModel {
    @SerializedName("actorid")
    @Expose
    private Integer actorid;
    @SerializedName("actorname")
    @Expose
    private String actorname;
    @SerializedName("actorimage")
    @Expose
    private String actorimage;
    @SerializedName("infomation")
    @Expose
    private String infomation;

    public Integer getActorid() {
        return actorid;
    }

    public void setActorid(Integer actorid) {
        this.actorid = actorid;
    }

    public String getActorname() {
        return actorname;
    }

    public void setActorname(String actorname) {
        this.actorname = actorname;
    }

    public String getActorimage() {
        return actorimage;
    }

    public void setActorimage(String actorimage) {
        this.actorimage = actorimage;
    }

    public String getInfomation() {
        return infomation;
    }

    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }
}
