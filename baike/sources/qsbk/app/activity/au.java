package qsbk.app.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

class au implements TextWatcher {
    final /* synthetic */ AddQiuYouActivity a;

    au(AddQiuYouActivity addQiuYouActivity) {
        this.a = addQiuYouActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (TextUtils.isEmpty(charSequence)) {
            this.a.d.setVisibility(8);
        } else {
            this.a.d.setVisibility(0);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
    }
}
