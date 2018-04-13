package cn.v6.sixrooms.view.interfaces;

import cn.v6.sixrooms.bean.UserInfoBean;
import java.util.List;

public interface RoomInputDialogListener {
    void chatChange();

    List<UserInfoBean> initChatListData();

    void refreshChat();

    void showChatLengthy();

    void showOpenGuardPage();

    void showSpeakOverquick();
}
