package cn.v6.sixrooms.room.chat;

import cn.v6.sixrooms.bean.UserInfoBean;

public interface PublicChatControlable {
    void hidePublicChatView();

    void showPublicChatView(UserInfoBean userInfoBean);
}
