package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class AppUpdateBean implements Serializable {
    private static final long serialVersionUID = 1;
    String appCode = "";
    String appURL = "";
    String description = "";
    String isForce = "";
    String title = "";
    String version = "";

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getAppCode() {
        return this.appCode;
    }

    public void setAppCode(String str) {
        this.appCode = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getIsForce() {
        return this.isForce;
    }

    public void setIsForce(String str) {
        this.isForce = str;
    }

    public String getAppURL() {
        return this.appURL;
    }

    public void setAppURL(String str) {
        this.appURL = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String toString() {
        return "AppUpdateBean [version=" + this.version + ", title=" + this.title + ", description=" + this.description + ", appURL=" + this.appURL + ", isForce=" + this.isForce + "]";
    }
}
