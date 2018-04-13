package qsbk.app.live.ui.family;

import android.text.Editable;
import android.text.TextWatcher;

class e implements TextWatcher {
    final /* synthetic */ FamilyCreateActivity a;

    e(FamilyCreateActivity familyCreateActivity) {
        this.a = familyCreateActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.a.j.setLevelAndName(1, this.a.f.getText().toString());
    }

    public void afterTextChanged(Editable editable) {
    }
}
