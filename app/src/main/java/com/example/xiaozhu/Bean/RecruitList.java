package com.example.xiaozhu.Bean;

/**
 * Created by xiaozhu on 2016/8/2.
 */
public class RecruitList {
    private String txUrl;
    private String sexUrl;
    private String name;
    private String content = null;

    public void setTxUrl(String txUrl) {
        this.txUrl = txUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSexUrl(String sexUrl) {
        this.sexUrl = sexUrl;
    }

    public String getTxUrl() {
        return txUrl;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getSexUrl() {
        return sexUrl;
    }
}
