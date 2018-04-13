package qsbk.app.im;

import java.util.List;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.JoinedGroupGetter.CallBack;

class bn implements CallBack {
    final /* synthetic */ ContactListItem a;
    final /* synthetic */ a b;
    final /* synthetic */ ContactListAdapter c;

    bn(ContactListAdapter contactListAdapter, ContactListItem contactListItem, a aVar) {
        this.c = contactListAdapter;
        this.a = contactListItem;
        this.b = aVar;
    }

    public void onSuccess(List<GroupInfo> list) {
        this.a.name = ((GroupInfo) list.get(0)).name;
        this.a.icon = ((GroupInfo) list.get(0)).icon;
        this.b.b.setText(this.a.name);
        this.c.a(this.b.a, this.a.icon, this.a.id);
    }

    public void onFail(int i, String str) {
    }
}
