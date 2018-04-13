package qsbk.app.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import qsbk.app.im.ContactListItem;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.GroupInfo$MemberInfo;
import qsbk.app.utils.GroupMemberManager.CallBack;

class qd implements CallBack {
    final /* synthetic */ List a;
    final /* synthetic */ InviteQiuYouActivity b;

    qd(InviteQiuYouActivity inviteQiuYouActivity, List list) {
        this.b = inviteQiuYouActivity;
        this.a = list;
    }

    public void onSuccess(ArrayList<BaseUserInfo> arrayList, int i) {
        this.b.hideLoading();
        this.b.a(this.a, (ArrayList) arrayList);
    }

    public void onFailure(int i, String str) {
        this.b.hideLoading();
        for (ContactListItem contactListItem : this.a) {
            if (contactListItem.type == 0) {
                BaseUserInfo baseUserInfo = new BaseUserInfo();
                baseUserInfo.userName = contactListItem.name;
                baseUserInfo.userIcon = contactListItem.icon;
                baseUserInfo.userId = contactListItem.id;
                Iterator it = this.b.f.memberList.iterator();
                while (it.hasNext()) {
                    if (baseUserInfo.userId.equals(String.valueOf(((GroupInfo$MemberInfo) it.next()).uid))) {
                        baseUserInfo.alreadyInGroup = true;
                        break;
                    }
                }
                this.b.d.add(baseUserInfo);
            }
        }
        this.b.c.notifyDataSetChanged();
    }
}
