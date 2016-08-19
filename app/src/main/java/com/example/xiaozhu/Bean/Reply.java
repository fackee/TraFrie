package com.example.xiaozhu.Bean;

/**
 * Created by xiaozhu on 2016/8/1.
 */
public class Reply {
    private String txUrl;
    private String time;
    private String name;
    private String content;
    public int menuId;
    public void setContent(String content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTxUrl(String txUrl) {
        this.txUrl = txUrl;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getTxUrl() {
        return txUrl;
    }

    public int getMenuId() {
        return menuId;
    }
}
