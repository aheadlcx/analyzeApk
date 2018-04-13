package cn.tatagou.sdk.pojo;

public class HomeAd extends CommonResponseResult {
    private String id;
    private String img;
    private String ttgUrl;
    private String type;

    public String getTtgUrl() {
        return this.ttgUrl;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setTtgUrl(String str) {
        this.ttgUrl = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String str) {
        this.img = str;
    }
}
