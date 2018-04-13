package qsbk.app.im;

import java.util.ArrayList;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.GroupMemberManager.CallBack;

class df implements CallBack {
    final /* synthetic */ GroupConversationActivity a;

    df(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onSuccess(ArrayList<BaseUserInfo> arrayList, int i) {
        this.a.a((ArrayList) arrayList);
    }

    public void onFailure(int i, String str) {
    }
}
