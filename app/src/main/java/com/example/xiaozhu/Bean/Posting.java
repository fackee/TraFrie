package com.example.xiaozhu.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhu on 2016/7/23.
 */
public class Posting {
    private String username;
    private String txUrl;
    private String category;
    private String content;
    private String time;
    private String replyNum;
    private String praiseNum;
    private ArrayList<String> imgurl;

    public void setPraiseNum(String praiseNum) {
        this.praiseNum = praiseNum;
    }

    public void setReplyNum(String replyNum) {
        this.replyNum = replyNum;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImgurl(ArrayList<String> imgurl) {
        this.imgurl = imgurl;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTxUrl(String txUrl) {
        this.txUrl = txUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<String> getImgurl() {
        return imgurl;
    }

    public String getTime() {
        return time;
    }

    public String getTxUrl() {
        return txUrl;
    }

    public String getPraiseNum() {
        return praiseNum;
    }

    public String getReplyNum() {
        return replyNum;
    }
}
