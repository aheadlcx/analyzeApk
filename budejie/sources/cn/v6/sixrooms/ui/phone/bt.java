package cn.v6.sixrooms.ui.phone;

import android.text.Editable;
import android.text.TextWatcher;
import cn.v6.sixrooms.utils.UsernameUtils;

final class bt implements TextWatcher {
    final /* synthetic */ RegisterActivity a;

    bt(RegisterActivity registerActivity) {
        this.a = registerActivity;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!charSequence.toString().equals(UsernameUtils.stringFilter(this.a.c()))) {
            this.a.c.setText(UsernameUtils.stringFilter(this.a.c()));
            this.a.c.setSelection(this.a.c.length());
        }
        RegisterActivity.c(this.a, charSequence);
    }

    public final void afterTextChanged(Editable editable) {
    }
}
