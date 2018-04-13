package com.budejie.www.type;

import java.io.Serializable;

public class MySquareIcon implements Serializable {
    private static final long serialVersionUID = 1239239200;
    private int defaultIcon = -1;
    private String icon;
    private int id;
    private String name;
    private String url;

    public int getDefaultIcon() {
        return this.defaultIcon;
    }

    public void setDefaultIcon(int i) {
        this.defaultIcon = i;
    }

    public int getId() {
        return this.id;
    }

    public MySquareIcon(int i, String str, String str2, String str3) {
        this.id = i;
        this.name = str;
        this.icon = str2;
        this.url = str3;
    }

    public MySquareIcon(int i, String str, String str2, int i2) {
        this.id = i;
        this.name = str;
        this.url = str2;
        this.defaultIcon = i2;
    }

    public MySquareIcon(String str) {
        this.name = str;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof MySquareIcon) && ((MySquareIcon) obj).getName().equals(this.name)) {
            return true;
        }
        return super.equals(obj);
    }
}
