package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

class va implements TextWatcher {
    final /* synthetic */ TextView a;
    final /* synthetic */ uz b;

    va(uz uzVar, TextView textView) {
        this.b = uzVar;
        this.a = textView;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.setText(String.valueOf(8 - editable.length()));
    }
}
