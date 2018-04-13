package qsbk.app.im;

import android.text.TextUtils;
import java.util.List;
import qsbk.app.Constants;
import qsbk.app.http.HttpTask;
import qsbk.app.model.relationship.Relationship;

class cg implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ cf b;

    cg(cf cfVar, List list) {
        this.b = cfVar;
        this.a = list;
    }

    public void run() {
        this.b.a.as = this.a.size() == 20;
        if (!this.b.a.as) {
            this.b.a.d.stopPullToRefresh();
        }
        this.b.a.g.appendItem(this.a);
        if (this.a.size() > 0 && this.b.a.mRelationship != Relationship.FRIEND) {
            int size = this.a.size() - 1;
            while (size >= 0) {
                ChatMsg chatMsg = (ChatMsg) this.a.get(size);
                if (chatMsg.inout != 1) {
                    size--;
                } else if (!TextUtils.isEmpty(chatMsg.msgsrc)) {
                    IMChatMsgSource msgSourceFromChatMsg = IMChatMsgSource.getMsgSourceFromChatMsg(chatMsg.msgsrc);
                    if (msgSourceFromChatMsg.type == 7) {
                        CharSequence presentText = msgSourceFromChatMsg.getPresentText();
                        if (TextUtils.isEmpty(presentText)) {
                            String str = msgSourceFromChatMsg.valueObj.group_id;
                            if (!(msgSourceFromChatMsg.valueObj == null || str == null)) {
                                String str2 = String.format(Constants.URL_GROUP_DETAIL, new Object[]{str}) + "?tid=" + str;
                                new HttpTask(str2, str2, new ch(this)).execute(new Void[0]);
                            }
                        } else {
                            this.b.a.A.setSubTitle(presentText);
                        }
                    }
                }
            }
        }
        this.b.a.g.notifyDataSetChanged();
        if (this.b.a.g.getCount() > 0) {
            this.b.a.d.post(new ci(this));
        }
    }
}
