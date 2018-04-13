package com.budejie.www.type;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;

public class MySquareTag {
    @SerializedName("theme_id")
    private int id;
    @SerializedName("theme_name")
    private String name;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        if (!TextUtils.isEmpty(this.name)) {
            if (this.name.endsWith("#") && this.name.startsWith("#")) {
                return this.name;
            }
            if (!this.name.startsWith("#") && this.name.endsWith("#")) {
                this.name = "#" + this.name;
            } else if (!this.name.startsWith("#") || this.name.endsWith("#")) {
                this.name = "#" + this.name + "#";
            } else {
                this.name += "#";
            }
        }
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
