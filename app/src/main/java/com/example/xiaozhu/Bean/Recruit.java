package com.example.xiaozhu.Bean;

/**
 * Created by xiaozhu on 2016/7/23.
 */
public class Recruit {
    private String username;
    private String starttime;
    private String endtime;
    private String title;
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public String getEndtime() {
        return endtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }
}
