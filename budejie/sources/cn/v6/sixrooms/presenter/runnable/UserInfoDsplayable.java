package cn.v6.sixrooms.presenter.runnable;

import cn.v6.sixrooms.bean.UserInfoBean;

public interface UserInfoDsplayable extends BaseRoomActivityErrorable, SocketMemuRequestable {
    void dismiss();

    void hideBottomView();

    void hideBottomView3();

    void hideCoinLevelView();

    void hideContentView();

    void hideLoadingView();

    void hideStarLevelView();

    void setUserinfo(UserInfoBean userInfoBean);

    void showAnchor(UserInfoBean userInfoBean);

    void showAnchorYourself(UserInfoBean userInfoBean);

    void showBottomView();

    void showBottomView3();

    void showCoinLevelView();

    void showContentView();

    void showEveryoneByAdmin(UserInfoBean userInfoBean);

    void showEveryoneByAnchor(UserInfoBean userInfoBean);

    void showEveryoneByGeneral(UserInfoBean userInfoBean);

    void showEveryoneByManager(UserInfoBean userInfoBean);

    void showFollowState(boolean z);

    void showLoadingView();

    void showManagerByAnchor(UserInfoBean userInfoBean);

    void showManagerByManager(UserInfoBean userInfoBean);

    void showMyself();

    void showOperateBtn(boolean z);

    void showStarLevelView();

    void updateView(UserInfoBean userInfoBean);
}
