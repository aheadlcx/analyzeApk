package cn.tatagou.sdk.pojo;

public class Feedback extends CommonResponseResult {
    private String content;
    private String createTime;
    private String id;
    private String pusher;
    private String read;
    private String replyContent;
    private FeedbackType type;

    public FeedbackType getType() {
        return this.type;
    }

    public void setType(FeedbackType feedbackType) {
        this.type = feedbackType;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getPusher() {
        return this.pusher;
    }

    public void setPusher(String str) {
        this.pusher = str;
    }

    public String getRead() {
        return this.read;
    }

    public void setRead(String str) {
        this.read = str;
    }

    public String getReplyContent() {
        return this.replyContent;
    }

    public void setReplyContent(String str) {
        this.replyContent = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.id.equals(((Feedback) obj).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }
}
