package qsbk.app.im;

import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

class cd implements Runnable {
    final /* synthetic */ cc a;

    cd(cc ccVar) {
        this.a = ccVar;
    }

    public void run() {
        if (this.a.a.as) {
            List list = this.a.a.aj.get(this.a.a.getToId(), this.a.a.ar);
            if (list.size() > 0) {
                ChatMsg chatMsg = (ChatMsg) list.get(0);
                this.a.a.ar = chatMsg.dbid;
                if (chatMsg.msgsrc != null) {
                    try {
                        IMChatMsgSource iMChatMsgSource = new IMChatMsgSource();
                        iMChatMsgSource.parseFromJSONObject(new JSONObject(chatMsg.msgsrc));
                        if (iMChatMsgSource.type == 7) {
                            this.a.a.isTemporary = true;
                            iMChatMsgSource.valueObj.group_name = null;
                            this.a.a.s = iMChatMsgSource;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            this.a.a.y.post(new ce(this, list));
        }
    }
}
