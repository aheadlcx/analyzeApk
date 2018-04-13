package qsbk.app.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

class abx implements TextWatcher {
    final /* synthetic */ SearchQiuYouActivity a;

    abx(SearchQiuYouActivity searchQiuYouActivity) {
        this.a = searchQiuYouActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (TextUtils.isEmpty(charSequence)) {
            this.a.f.setVisibility(8);
        } else {
            this.a.f.setVisibility(0);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.a(editable.toString(), false);
    }
}
