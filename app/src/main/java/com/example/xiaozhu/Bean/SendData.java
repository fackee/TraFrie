package com.example.xiaozhu.Bean;

import java.io.File;
import java.util.Map;

/**
 * Created by xiaozhu on 2016/7/25.
 */
public class SendData {
    private Map<String,String> map = null;
    private File[] files = null;
    private String[] formName = null;

    public void setFiles(File[] files) {
        this.files = files;
    }

    public void setFormName(String[] formName) {
        this.formName = formName;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public File[] getFiles() {
        return files;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public String[] getFormName() {
        return formName;
    }
}
