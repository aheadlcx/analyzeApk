package cn.v6.sixrooms.presenter.runnable;

import cn.v6.sixrooms.view.interfaces.FollowViewable;

public interface FollowPresenterable {
    void followOrCancel(String str, String str2, String str3);

    void getFollowStatus(String str, String str2, String str3);

    boolean getIsChange();

    boolean getLoacaFollowStatus();

    void onDestroy();

    void register(FollowViewable followViewable);

    void unregister(FollowViewable followViewable);
}
