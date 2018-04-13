package qsbk.app.live.widget;

import android.text.Editable;
import android.text.TextWatcher;

class ir implements TextWatcher {
    final /* synthetic */ SendRedEnvelopesDialog a;

    ir(SendRedEnvelopesDialog sendRedEnvelopesDialog) {
        this.a = sendRedEnvelopesDialog;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.f.setEnabled(editable.length() > 0);
    }
}
