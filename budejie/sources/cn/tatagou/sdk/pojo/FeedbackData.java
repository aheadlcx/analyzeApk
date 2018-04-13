package cn.tatagou.sdk.pojo;

public class FeedbackData extends CommonResponseResult {
    private Page<Feedback> feedback;
    private String unRead;

    public Page<Feedback> getFeedback() {
        return this.feedback;
    }

    public void setFeedback(Page<Feedback> page) {
        this.feedback = page;
    }

    public String getUnRead() {
        return this.unRead;
    }

    public void setUnRead(String str) {
        this.unRead = str;
    }
}
