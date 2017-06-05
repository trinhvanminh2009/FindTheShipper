package com.minh.findtheshipper.models;

/**
 * Created by Minh on 5/31/2017.
 */

public class ListControl {
    private int idIcon;
    private String content;

    public ListControl(int idIcon, String content) {
        this.idIcon = idIcon;
        this.content = content;
    }

    public int getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(int idIcon) {
        this.idIcon = idIcon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
