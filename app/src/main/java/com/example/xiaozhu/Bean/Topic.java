package com.example.xiaozhu.Bean;

/**
 * Created by xiaozhu on 2016/7/28.
 */
public class Topic {
    private String viewnum;
    private String concern;
    private String imgSrc;
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setConcern(String concern) {
        this.concern = concern;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setViewnum(String viewnum) {
        this.viewnum = viewnum;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getConcern() {
        return concern;
    }

    public String getViewnum() {
        return viewnum;
    }

    public String getTitle() {
        return title;
    }
}
