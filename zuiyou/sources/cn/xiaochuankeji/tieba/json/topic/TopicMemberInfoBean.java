package cn.xiaochuankeji.tieba.json.topic;

import cn.xiaochuankeji.tieba.json.MemberInfoBean;
import com.alibaba.fastjson.annotation.JSONField;

public class TopicMemberInfoBean extends MemberInfoBean {
    public boolean applyAgreed = false;
    public boolean applyDenied = false;
    @JSONField(name = "apply_reason")
    private String applyReason;
    @JSONField(name = "delete_des")
    private String escortDescribe;

    public String getEscortDescribe() {
        return this.escortDescribe;
    }

    public void setEscortDescribe(String str) {
        this.escortDescribe = str;
    }

    public String getApplyReason() {
        return this.applyReason;
    }

    public void setApplyReason(String str) {
        this.applyReason = str;
    }
}
