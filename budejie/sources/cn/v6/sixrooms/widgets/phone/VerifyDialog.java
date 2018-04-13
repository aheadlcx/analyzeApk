package cn.v6.sixrooms.widgets.phone;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.view.KeyEvent;

public class VerifyDialog extends Dialog {
    public VerifyDialog(Context context) {
        super(context);
    }

    public VerifyDialog(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public VerifyDialog(Context context, int i) {
        super(context, i);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return i == 4 || super.onKeyDown(i, keyEvent);
    }
}
