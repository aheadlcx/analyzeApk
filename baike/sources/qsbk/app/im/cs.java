package qsbk.app.im;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import qsbk.app.utils.LogUtil;

class cs implements TextWatcher {
    final /* synthetic */ ConversationActivity a;

    cs(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        LogUtil.d("sending on text change");
        this.a.sendTypingStatus();
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.a.sendTypingStatus();
        this.a.O();
    }

    public void afterTextChanged(Editable editable) {
        if (editable == null || TextUtils.isEmpty(editable.toString())) {
            this.a.f.setVisibility(8);
            if (this.a.getUserType() == 1 && this.a.getToId().equals("32879940")) {
                this.a.x.setSendVoiceButtonVisibility(false);
                return;
            } else {
                this.a.x.setSendVoiceButtonVisibility(true);
                return;
            }
        }
        this.a.f.setVisibility(0);
        this.a.x.setSendVoiceButtonVisibility(false);
    }
}
