package cn.v6.sixrooms.room.fragment;

import android.support.v4.app.Fragment;
import cn.v6.sixrooms.bean.AuthKeyBean;
import cn.v6.sixrooms.bean.LiveStateBean;
import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.room.OnChatMsgSocketCallBack;
import cn.v6.sixrooms.room.chat.PrivateChatControlable;
import cn.v6.sixrooms.room.chat.PublicChatControlable;
import cn.v6.sixrooms.room.interfaces.OnRoomIMListener;
import cn.v6.sixrooms.view.interfaces.IOnBackPressedListener;
import cn.v6.sixrooms.view.interfaces.IOnKeyDownListener;
import cn.v6.sixrooms.view.interfaces.IOnRestartListener;
import cn.v6.sixrooms.view.interfaces.IRoomChatSocketHandledListener;
import cn.v6.sixrooms.view.interfaces.IRoomHistoryContral;
import cn.v6.sixrooms.view.interfaces.IRoomResetDataListener;
import cn.v6.sixrooms.view.interfaces.OnRoomActivityResultListener;
import cn.v6.sixrooms.view.interfaces.RoomInputDialogListener;

public abstract class RoomBaseFragment extends Fragment implements OnChatMsgSocketCallBack, PrivateChatControlable, PublicChatControlable, OnRoomIMListener, IOnBackPressedListener, IOnKeyDownListener, IOnRestartListener, IRoomChatSocketHandledListener, IRoomHistoryContral, IRoomResetDataListener, OnRoomActivityResultListener, RoomInputDialogListener {
    public static final String FRAGMENT_TYPE_KEY = "fragmentType";
    public static final String RID_KEY = "rid";
    public static final String RUID_KEY = "ruid";
    protected static final String SUPERGIRL_GROUP_MEMBER_URL = "http://m.v.6.cn/supergirl-group/member/";
    public String mLiveType;
    public int mRoomType;

    public abstract void clearGiftList();

    public abstract void openGiftBox(String str);

    public boolean addHistory() {
        return true;
    }

    public boolean getGiftVisibility() {
        return true;
    }

    public void onLiveStateChange(LiveStateBean liveStateBean) {
    }

    public void showPrivateChatView(UserInfoBean userInfoBean) {
    }

    public void hidePrivateChatView() {
    }

    public void showPublicChatView(UserInfoBean userInfoBean) {
    }

    public void hidePublicChatView() {
    }

    public boolean isRoomManager(AuthKeyBean authKeyBean) {
        return authKeyBean.getUserIdentity() == 7 || authKeyBean.getUserIdentity() == 10 || authKeyBean.getUserIdentity() == 9;
    }
}
