package qsbk.app.im;

import java.util.List;
import qsbk.app.im.ChatMsg.TYPE;

class dt implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ ds b;

    dt(ds dsVar, List list) {
        this.b = dsVar;
        this.a = list;
    }

    public void run() {
        ChatMsg chatMsg;
        boolean z = true;
        if (this.a.size() > 0) {
            int size = this.b.a.a.g.e.size();
            this.b.a.a.g.insertMsg(this.a);
            if (!(this.b.a.a.aJ == 0 || this.b.a.a.aI || this.b.a.a.g.e.size() < this.b.a.a.aJ)) {
                int size2 = this.b.a.a.g.e.size() - this.b.a.a.aJ;
                ChatMsg chatMsg2 = new ChatMsg();
                chatMsg2.type = TYPE.TYPE_MSG_DIVIDER;
                chatMsg2.data = "新消息分割线";
                chatMsg2.msgid = "divider";
                this.b.a.a.g.e.add(size2, chatMsg2);
                this.b.a.a.aI = true;
            }
            if (this.b.a.a.ay > 0) {
                this.b.a.a.ay = (this.b.a.a.g.e.size() - size) + this.b.a.a.ay;
            }
            this.b.a.a.g.notifyDataSetChanged();
            this.b.a.a.a(this.a);
        }
        if (this.a.size() < 20) {
            this.b.a.a.aP = false;
        }
        this.b.a.a.d.setListSelection(this.a.size());
        if (this.b.a.a.aM != 0) {
            boolean z2 = this.a.size() == 0;
            for (int i = 0; i < this.a.size(); i++) {
                chatMsg = (ChatMsg) this.b.a.a.g.e.get(i);
                if (chatMsg.dbid == this.b.a.a.aM) {
                    this.b.a.a.d.post(new du(this, i));
                    this.b.a.a.aM = 0;
                    this.b.a.a.g._toFlash = chatMsg;
                    z2 = true;
                    break;
                }
            }
            if (!z2) {
                this.b.a.a.d.smoothScrollToPosition(0);
            }
        }
        if (this.b.a.a.aK != 0) {
            boolean z3 = this.a.size() == 0;
            if (this.b.a.a.g.e.size() > this.b.a.a.aK) {
                size2 = this.b.a.a.g.e.size() - this.b.a.a.aK;
                chatMsg = (ChatMsg) this.b.a.a.g.e.get(size2);
                this.b.a.a.d.post(new dv(this, size2));
                this.b.a.a.aK = 0;
                this.b.a.a.g._toFlash = chatMsg;
            } else {
                z = z3;
            }
            if (!z) {
                this.b.a.a.d.smoothScrollToPosition(0);
            }
        }
    }
}
