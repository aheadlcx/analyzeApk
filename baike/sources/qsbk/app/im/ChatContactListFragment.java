package qsbk.app.im;

import qsbk.app.fragments.BaseFragment;

public class ChatContactListFragment extends BaseFragment implements IChatMsgListener {
    public static final String TO_ID = "to_id";
    public static final String USER_ID = "user_id";

    public void onMessageReceived(ChatMsg chatMsg) {
    }

    public void onGroupMessageReceived(ChatMsg chatMsg) {
    }

    public void onChatMsgStatusChanged(long j, int i) {
    }

    public void onDuplicateConnect(ChatMsg chatMsg) {
    }

    public void onConnectStatusChange(int i) {
    }
}
