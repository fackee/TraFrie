package com.example.xiaozhu.Bean;

/**
 * Created by xiaozhu on 2016/7/30.
 */
public class ReplyTopic {
    private String txSrc;
    private String name;
    private String title;
    private String Content;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTxSrc(String txSrc) {
        this.txSrc = txSrc;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return Content;
    }

    public String getName() {
        return name;
    }

    public String getTxSrc() {
        return txSrc;
    }
}
