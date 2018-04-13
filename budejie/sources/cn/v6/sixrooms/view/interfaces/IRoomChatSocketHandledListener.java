package cn.v6.sixrooms.view.interfaces;

import cn.v6.sixrooms.bean.UserInfoBean;

public interface IRoomChatSocketHandledListener {
    void sendSocketMessage(Object obj, int i);

    void setChatClickable(UserInfoBean userInfoBean);
}
