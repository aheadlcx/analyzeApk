package cn.v6.sixrooms.room.game;

import cn.v6.sixrooms.hall.BaseBean;

public class MiniGameBean extends BaseBean {
    private String gameid;
    private String icon;
    private String intro;
    private String title;
    private String url;

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String str) {
        this.intro = str;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public String getGameid() {
        return this.gameid;
    }

    public void setGameid(String str) {
        this.gameid = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
