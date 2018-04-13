package qsbk.app.pay.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

class z extends Dialog {
    final /* synthetic */ WithdrawActivity a;

    z(WithdrawActivity withdrawActivity, Context context, int i) {
        this.a = withdrawActivity;
        super(context, i);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.setSoftInputMode(5);
        }
    }
}
