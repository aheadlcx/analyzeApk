package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class pv implements TextWatcher {
    final /* synthetic */ InviteFriendActivity a;

    pv(InviteFriendActivity inviteFriendActivity) {
        this.a = inviteFriendActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.b(editable.toString());
        this.a.d.notifyDataSetChanged();
    }
}
