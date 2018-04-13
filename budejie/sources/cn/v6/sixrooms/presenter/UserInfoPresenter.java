package cn.v6.sixrooms.presenter;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.engine.AddFollowEngine;
import cn.v6.sixrooms.engine.AddFollowEngine.CallBack;
import cn.v6.sixrooms.engine.CancelFollowEngine;
import cn.v6.sixrooms.engine.UserInfoMessageEngine;
import cn.v6.sixrooms.presenter.runnable.UserInfoDsplayable;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.view.interfaces.FollowViewable;

public class UserInfoPresenter implements FollowViewable {
    private UserInfoMessageEngine a;
    private AddFollowEngine b;
    private CancelFollowEngine c;
    private UserInfoDsplayable d;
    private UserInfoBean e;

    class a implements CallBack {
        final /* synthetic */ UserInfoPresenter a;

        a(UserInfoPresenter userInfoPresenter) {
            this.a = userInfoPresenter;
        }

        public final void result(boolean z) {
            this.a.e.setIsfollow("1");
            this.a.d.showFollowState(true);
        }

        public final void handleErrorInfo(String str, String str2) {
            this.a.d.showErrorDialog(str, str2);
        }

        public final void error(int i) {
            this.a.d.showErrorToast(i);
        }
    }

    class b implements CancelFollowEngine.CallBack {
        final /* synthetic */ UserInfoPresenter a;

        b(UserInfoPresenter userInfoPresenter) {
            this.a = userInfoPresenter;
        }

        public final void result(boolean z) {
            this.a.e.setIsfollow("0");
            this.a.d.showFollowState(false);
        }

        public final void handleErrorInfo(String str, String str2) {
            this.a.d.showErrorDialog(str, str2);
        }

        public final void error(int i) {
            this.a.d.showErrorToast(i);
        }
    }

    class c implements UserInfoMessageEngine.CallBack {
        final /* synthetic */ UserInfoPresenter a;

        c(UserInfoPresenter userInfoPresenter) {
            this.a = userInfoPresenter;
        }

        public final void handleInfo(UserInfoBean userInfoBean, int i) {
            UserInfoPresenter.a(this.a, userInfoBean);
            this.a.d.setUserinfo(userInfoBean);
            if (UserInfoPresenter.a(this.a.e.getUid())) {
                this.a.d.updateView(userInfoBean);
                if (this.a.e.getUid().equals(this.a.e.getRuid())) {
                    this.a.d.showAnchorYourself(userInfoBean);
                    return;
                } else {
                    this.a.d.showMyself();
                    return;
                }
            }
            this.a.d.updateView(userInfoBean);
            this.a.d.hideLoadingView();
            this.a.d.showContentView();
            if (this.a.e.getUid().equals(this.a.e.getRuid())) {
                this.a.d.showAnchor(userInfoBean);
                return;
            }
            int userIdentity = userInfoBean.getUserIdentity();
            userInfoBean.setAdmin(UserInfoPresenter.a(userIdentity));
            userInfoBean.setManager(UserInfoPresenter.b(userIdentity));
            userInfoBean.setGag(UserInfoPresenter.c(userInfoBean.getSpeakState()));
            switch (i) {
                case 7:
                    this.a.d.showEveryoneByAdmin(userInfoBean);
                    return;
                case 9:
                    if (userIdentity == 10) {
                        this.a.d.showManagerByAnchor(userInfoBean);
                        return;
                    } else {
                        this.a.d.showEveryoneByAnchor(userInfoBean);
                        return;
                    }
                case 10:
                    if (UserInfoPresenter.b(userIdentity)) {
                        this.a.d.showManagerByManager(userInfoBean);
                        return;
                    } else {
                        this.a.d.showEveryoneByManager(userInfoBean);
                        return;
                    }
                default:
                    this.a.d.showEveryoneByGeneral(userInfoBean);
                    return;
            }
        }

        public final void error(int i) {
            this.a.d.dismiss();
            this.a.d.showErrorToast(i);
        }

        public final void handleErrorInfo(String str, String str2) {
            this.a.d.showErrorDialog(str, str2);
        }
    }

    public UserInfoPresenter() {
        if (this.a == null) {
            this.a = new UserInfoMessageEngine(new c(this));
        }
    }

    public void setUserInfoDsplayable(UserInfoDsplayable userInfoDsplayable) {
        this.d = userInfoDsplayable;
    }

    public void requestFollow(boolean z) {
        if (LoginUtils.getLoginUserBean() == null) {
            this.d.showLoginDialog();
            this.d.dismiss();
        } else if (this.e.getUid() == null || !this.e.getUid().equals(this.e.getRuid())) {
            if (this.b == null) {
                this.b = new AddFollowEngine(new a(this));
            }
            if (this.c == null) {
                this.c = new CancelFollowEngine(new b(this));
            }
            if (z) {
                this.b.addFollow(this.e.getUid(), LoginUtils.getLoginUserBean().getId(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
            } else {
                this.c.cancelFollow(this.e.getUid(), LoginUtils.getLoginUserBean().getId(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
            }
        } else {
            FollowPresenter.getInstance().followOrCancel(this.e.getUid(), LoginUtils.getLoginUserBean().getId(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
        }
    }

    public void requestMute() {
        if (LoginUtils.getLoginUserBean() == null) {
            this.d.showLoginDialog();
        } else {
            this.d.requestStopMsg(this.e.getUid());
        }
    }

    public void requestUnMute() {
        if (LoginUtils.getLoginUserBean() == null) {
            this.d.showLoginDialog();
        } else {
            this.d.requestRecoverMsg(this.e.getUid());
        }
    }

    public void requestPopup() {
        if (LoginUtils.getLoginUserBean() == null) {
            this.d.showLoginDialog();
        } else {
            this.d.requestKickRoom(this.e.getUid());
        }
    }

    public void updateBean(String str, String str2, boolean z, int i) {
        if (this.d != null) {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setUid(str);
            userInfoBean.setRuid(str2);
            userInfoBean.setRoomManager(z);
            updateBean(userInfoBean, i);
        }
    }

    public void updateBean(UserInfoBean userInfoBean, int i) {
        if (this.d != null) {
            this.e = userInfoBean;
            String str = "";
            if (!(LoginUtils.getLoginUserBean() == null || LoginUtils.getLoginUserBean().getId() == null)) {
                str = LoginUtils.getLoginUserBean().getId();
            }
            this.d.hideContentView();
            this.d.showLoadingView();
            this.a.getUserInfoMessage(this.e.getUid(), str, this.e.getRuid(), i);
        }
    }

    public void reqeuestAddManager() {
        if (LoginUtils.getLoginUserBean() == null) {
            this.d.showLoginDialog();
        } else {
            this.d.requestAddManager(this.e.getUid());
        }
    }

    public void reqeuestRevokeManager() {
        if (LoginUtils.getLoginUserBean() == null) {
            this.d.showLoginDialog();
        } else {
            this.d.requestRevokeManager(this.e.getUid());
        }
    }

    public void reqeuestAddAdmin() {
        if (LoginUtils.getLoginUserBean() == null) {
            this.d.showLoginDialog();
        } else {
            this.d.requestAddAdmin(this.e.getUid());
        }
    }

    public void reqeuestRevokeAdmin() {
        if (LoginUtils.getLoginUserBean() == null) {
            this.d.showLoginDialog();
        } else {
            this.d.requestRevokeAdmin(this.e.getUid());
        }
    }

    public void updateFollow(boolean z) {
        this.d.showFollowState(z);
    }

    public void initFollow(boolean z) {
        this.d.showFollowState(z);
    }

    public void updateFollowNetError(boolean z, int i) {
        this.d.showErrorToast(i);
    }

    public void updateFollowServerError(boolean z, String str, String str2) {
        this.d.showErrorDialog(str, str2);
    }

    public void initData() {
        FollowPresenter.getInstance().register(this);
    }

    public void relese() {
        FollowPresenter.getInstance().unregister(this);
    }

    static /* synthetic */ UserInfoBean a(UserInfoPresenter userInfoPresenter, UserInfoBean userInfoBean) {
        if (userInfoPresenter.e == null) {
            userInfoPresenter.e = new UserInfoBean();
        }
        userInfoPresenter.e.setUid(userInfoBean.getUid());
        userInfoPresenter.e.setUname(userInfoBean.getUname());
        userInfoPresenter.e.setIsGodPic(userInfoBean.getIsGodPic());
        userInfoPresenter.e.setIsfollow(userInfoBean.getIsfollow());
        userInfoPresenter.e.setUserpic(userInfoBean.getUserpic());
        userInfoPresenter.e.setAnchorLevel(userInfoBean.getAnchorLevel());
        userInfoPresenter.e.setWealthLevel(userInfoBean.getWealthLevel());
        userInfoPresenter.e.setUrid(userInfoBean.getUrid());
        userInfoPresenter.e.setSpeakState(userInfoBean.getSpeakState());
        userInfoPresenter.e.setFriend(userInfoBean.isFriend());
        userInfoPresenter.e.setCoin6late(userInfoBean.getCoin6late());
        userInfoPresenter.e.setWealtlate(userInfoBean.getWealtlate());
        userInfoPresenter.e.setCoinstep(userInfoBean.getCoinstep());
        userInfoPresenter.e.setWealthstep(userInfoBean.getWealthstep());
        return userInfoPresenter.e;
    }

    static /* synthetic */ boolean a(String str) {
        return LoginUtils.getLoginUserBean() != null && LoginUtils.getLoginUserBean().getId().equals(str);
    }

    static /* synthetic */ boolean a(int i) {
        return i == 7;
    }

    static /* synthetic */ boolean b(int i) {
        return i == 10;
    }

    static /* synthetic */ boolean c(int i) {
        return i == 0;
    }
}
