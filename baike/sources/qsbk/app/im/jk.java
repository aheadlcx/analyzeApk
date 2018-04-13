package qsbk.app.im;

import org.json.JSONArray;

class jk implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ JSONArray b;
    final /* synthetic */ String c;
    final /* synthetic */ UserChatManager d;

    jk(UserChatManager userChatManager, String str, JSONArray jSONArray, String str2) {
        this.d = userChatManager;
        this.a = str;
        this.b = jSONArray;
        this.c = str2;
    }

    public void run() {
        this.d.sendReadedMsg(this.a, this.b, this.c);
    }
}
