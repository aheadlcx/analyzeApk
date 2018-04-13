package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class tq implements TextWatcher {
    final /* synthetic */ MemberManagerActivity a;

    tq(MemberManagerActivity memberManagerActivity) {
        this.a = memberManagerActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.c(editable.toString());
    }
}
