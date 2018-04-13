package cn.v6.sixrooms.view.interfaces;

public interface FollowViewable {
    void initFollow(boolean z);

    void updateFollow(boolean z);

    void updateFollowNetError(boolean z, int i);

    void updateFollowServerError(boolean z, String str, String str2);
}
