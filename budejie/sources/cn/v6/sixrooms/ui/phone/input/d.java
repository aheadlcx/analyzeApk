package cn.v6.sixrooms.ui.phone.input;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

final class d implements TextWatcher {
    final /* synthetic */ BaseRoomInputDialog a;

    d(BaseRoomInputDialog baseRoomInputDialog) {
        this.a = baseRoomInputDialog;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.a.mActivity.mSpeakState == 1 && !TextUtils.isEmpty(charSequence.toString())) {
            this.a.mInputEditText.setText("");
        }
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
    }
}
