package cn.v6.sixrooms.room.game;

public class GameWinnerBean {
    private String name;
    private String outer;
    private String uid;

    public GameWinnerBean(String str, String str2, String str3) {
        this.uid = str;
        this.outer = str2;
        this.name = str3;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getOuter() {
        return this.outer;
    }

    public void setOuter(String str) {
        this.outer = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
