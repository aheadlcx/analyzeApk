package cn.xiaochuankeji.tieba.ui.recommend.data;

import cn.xiaochuankeji.tieba.background.data.post.Moment;
import cn.xiaochuankeji.tieba.ui.recommend.c;
import com.alibaba.fastjson.annotation.JSONField;

public class RecUgcDataBean extends Moment implements c {
    @JSONField(name = "position")
    private int indexInGroup;

    public int getIndexInGroup() {
        return this.indexInGroup;
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

    public long getCreateTime() {
        return this.createTime;
    }

    public int localPostType() {
        return 1;
    }

    public int getShareNum() {
        return (int) this.shareCount;
    }

    public void setIndexInGroup(int i) {
        this.indexInGroup = i;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof RecUgcDataBean) && ((RecUgcDataBean) obj).id == this.id) {
            return true;
        }
        return false;
    }
}
