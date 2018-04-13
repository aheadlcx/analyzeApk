package cn.v6.sixrooms.mvp.interfaces;

import cn.v6.sixrooms.bean.UserInfoBean;

public interface PrivateChatrable {
    void setNewMsgViewHide();

    void setNewMsgViewShow();

    void setPrivateChatViewHide();

    void setPrivateChatViewShow();

    void showInputDialog(UserInfoBean userInfoBean);
}
