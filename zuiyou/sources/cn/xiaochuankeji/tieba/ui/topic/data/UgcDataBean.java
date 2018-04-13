package cn.xiaochuankeji.tieba.ui.topic.data;

import cn.xiaochuankeji.tieba.background.data.post.Moment;
import org.json.JSONObject;

public class UgcDataBean extends Moment implements c {
    public UgcDataBean(JSONObject jSONObject) {
        super(jSONObject);
    }

    public UgcDataBean(com.alibaba.fastjson.JSONObject jSONObject) {
        super(jSONObject);
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public int localPostType() {
        return 3;
    }

    public int getShareNum() {
        return (int) this.shareCount;
    }

    public long getId() {
        return this.id;
    }

    public long getMemberId() {
        return this.member.getId();
    }

    public void setFollowStatus(int i) {
        if (this.member != null) {
            this.member.setFollowStatus(i);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof UgcDataBean)) {
            return false;
        }
        if (this.id == ((UgcDataBean) obj).getId()) {
            return true;
        }
        return false;
    }
}
