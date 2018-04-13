package android.support.v7.widget;

import android.text.Editable;
import android.text.TextWatcher;

class ca implements TextWatcher {
    final /* synthetic */ SearchView a;

    ca(SearchView searchView) {
        this.a = searchView;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.a.b(charSequence);
    }

    public void afterTextChanged(Editable editable) {
    }
}
