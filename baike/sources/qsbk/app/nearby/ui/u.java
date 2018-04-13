package qsbk.app.nearby.ui;

import android.text.Editable;
import android.text.TextWatcher;

class u implements TextWatcher {
    final /* synthetic */ InfoCompleteActivity a;

    u(InfoCompleteActivity infoCompleteActivity) {
        this.a = infoCompleteActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        if (editable != null && !editable.toString().equalsIgnoreCase(this.a.G)) {
            if (this.a.B()) {
                this.a.G = editable.toString();
                this.a.z();
                return;
            }
            this.a.h.setText(this.a.G);
        }
    }
}
