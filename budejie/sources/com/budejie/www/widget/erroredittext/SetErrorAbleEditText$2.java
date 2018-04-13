package com.budejie.www.widget.erroredittext;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

class SetErrorAbleEditText$2 implements OnKeyListener {
    final /* synthetic */ SetErrorAbleEditText a;

    SetErrorAbleEditText$2(SetErrorAbleEditText setErrorAbleEditText) {
        this.a = setErrorAbleEditText;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (SetErrorAbleEditText.a(this.a).a != null) {
            SetErrorAbleEditText.a(this.a).a(null, null);
        }
        return false;
    }
}
