package cn.tatagou.sdk.pojo;

public class FeedbackType extends CommonResponseResult {
    private String id;
    private String type;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }
}
