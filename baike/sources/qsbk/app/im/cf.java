package qsbk.app.im;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.model.relationship.Relationship;

class cf implements Runnable {
    final /* synthetic */ ConversationActivity a;

    cf(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void run() {
        List list = this.a.aj.get(this.a.getToId());
        if (TextUtils.equals(this.a.getToId(), ChatMsg.UID_LIVE)) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((ChatMsg) it.next()).type == 26) {
                    it.remove();
                }
            }
        }
        if (list.size() > 0 && this.a.mRelationship != Relationship.FRIEND) {
            ChatMsg chatMsg = (ChatMsg) list.get(list.size() - 1);
            this.a.ar = chatMsg.dbid;
            if (chatMsg.msgsrc != null) {
                try {
                    IMChatMsgSource iMChatMsgSource = new IMChatMsgSource();
                    iMChatMsgSource.parseFromJSONObject(new JSONObject(chatMsg.msgsrc));
                    if (iMChatMsgSource.type == 7) {
                        this.a.isTemporary = true;
                        iMChatMsgSource.valueObj.group_name = null;
                        this.a.s = iMChatMsgSource;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        this.a.y.post(new cg(this, list));
    }
}
