package qsbk.app.im;

import android.widget.TextView;
import java.util.List;
import qsbk.app.im.ChatMsg.TYPE;

class ef implements Runnable {
    final /* synthetic */ GroupConversationActivity a;

    ef(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void run() {
        int i;
        boolean z;
        if (this.a.aL != 0) {
            List list;
            for (i = 0; i < this.a.g.e.size(); i++) {
                if (((ChatMsg) this.a.g.e.get(i)).dbid == this.a.aL) {
                    z = this.a.d.getFirstVisiblePosition() > i + 1;
                    if (z) {
                        this.a.aL = 0;
                        return;
                    }
                    list = this.a.af.get(this.a.aL);
                    if (list != null && list.size() > 0) {
                        ChatMsg chatMsg = (ChatMsg) list.get(0);
                        if (chatMsg.isAtAll()) {
                            this.a.ak.setText("@全体成员");
                        } else {
                            this.a.ak.setText("有人@我");
                            this.a.g.setIcon(chatMsg.from, chatMsg.fromicon, this.a.aj);
                        }
                        this.a.ai.setVisibility(0);
                        this.a.ai.setOnClickListener(new eg(this));
                        return;
                    }
                }
            }
            z = true;
            if (z) {
                this.a.aL = 0;
                return;
            }
            list = this.a.af.get(this.a.aL);
            if (list != null) {
            }
        } else if (this.a.aJ != 0) {
            z = this.a.aJ > 7 && this.a.d.getFirstVisiblePosition() > this.a.g.e.size() - this.a.aJ;
            if (this.a.g.e.size() >= this.a.aJ && !this.a.aI) {
                i = this.a.g.e.size() - this.a.aJ;
                ChatMsg chatMsg2 = new ChatMsg();
                chatMsg2.type = TYPE.TYPE_MSG_DIVIDER;
                chatMsg2.data = "新消息分割线";
                chatMsg2.msgid = "divider";
                this.a.g.e.add(i, chatMsg2);
                this.a.g.notifyDataSetChanged();
                this.a.aI = true;
            }
            if (z) {
                Object obj;
                this.a.ai.setVisibility(0);
                TextView C = this.a.ak;
                StringBuilder stringBuilder = new StringBuilder();
                if (this.a.aJ > 99) {
                    obj = "99+";
                } else {
                    obj = Integer.valueOf(this.a.aJ);
                }
                C.setText(stringBuilder.append(obj).append("条新消息").toString());
                this.a.ai.setOnClickListener(new eh(this));
                return;
            }
            this.a.aJ = 0;
        }
    }
}
