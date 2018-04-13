package cn.tatagou.sdk.pojo;

public class SendFeedback extends CommonResponseResult {
    private String createTime;
    private String id;

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public String getId() {
        return this.id;
    }
}
