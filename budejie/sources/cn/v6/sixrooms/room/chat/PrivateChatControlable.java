package cn.v6.sixrooms.room.chat;

import cn.v6.sixrooms.bean.UserInfoBean;

public interface PrivateChatControlable {
    void hidePrivateChatView();

    void showPrivateChatView(UserInfoBean userInfoBean);
}
