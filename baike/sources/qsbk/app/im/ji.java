package qsbk.app.im;

import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.TemporaryNoteUtils;

class ji implements SimpleCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ ChatMsg b;
    final /* synthetic */ UserChatManager c;

    ji(UserChatManager userChatManager, String str, ChatMsg chatMsg) {
        this.c = userChatManager;
        this.a = str;
        this.b = chatMsg;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (TemporaryNoteUtils.getPreferences().getBoolean(this.a, true)) {
            this.c.b(this.b);
        }
    }

    public void onFailure(int i, String str) {
        onSuccess(null);
    }
}
