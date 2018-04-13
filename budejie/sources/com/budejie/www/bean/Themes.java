package com.budejie.www.bean;

import java.io.Serializable;

public class Themes implements Serializable {
    private String theme_id;
    private String theme_name;
    private String theme_type;

    public String getTheme_id() {
        return this.theme_id;
    }

    public void setTheme_id(String str) {
        this.theme_id = str;
    }

    public String getTheme_type() {
        return this.theme_type;
    }

    public void setTheme_type(String str) {
        this.theme_type = str;
    }

    public String getTheme_name() {
        return this.theme_name;
    }

    public void setTheme_name(String str) {
        this.theme_name = str;
    }
}
