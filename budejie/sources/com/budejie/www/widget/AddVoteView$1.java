package com.budejie.www.widget;

import android.text.Editable;
import android.text.TextWatcher;

class AddVoteView$1 implements TextWatcher {
    final /* synthetic */ AddVoteView a;

    AddVoteView$1(AddVoteView addVoteView) {
        this.a = addVoteView;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        AddVoteView.a(this.a);
    }
}
