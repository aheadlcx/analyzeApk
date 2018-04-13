package cn.xiaochuankeji.tieba.ui.topic.data;

public interface c {
    long getCreateTime();

    long getId();

    long getMemberId();

    int getShareNum();

    int localPostType();

    void setFollowStatus(int i);
}
