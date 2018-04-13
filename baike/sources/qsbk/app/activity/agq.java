package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class agq implements OnClickListener {
    final /* synthetic */ WithdrawActivity a;

    agq(WithdrawActivity withdrawActivity) {
        this.a = withdrawActivity;
    }

    public void onClick(View view) {
        this.a.b.setText(this.a.m + "");
        this.a.b.setSelection(this.a.b.getText().length());
    }
}
