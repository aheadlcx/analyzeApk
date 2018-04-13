package cn.v6.sixrooms.view.interfaces;

import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.presenter.runnable.BaseRoomActivityErrorable;
import java.util.List;

public interface SpectatorsViewable extends BaseRoomActivityErrorable {
    void dismiss();

    void pullToRefreshComplete();

    void sendLoadAllRoomList();

    void showOpenGuard();

    void updateError(int i);

    void updateSelectedType(int i);

    void updateSpectatorsView(List<UserInfoBean> list, String str, String str2, String str3);
}
