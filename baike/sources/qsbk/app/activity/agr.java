package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class agr implements OnClickListener {
    final /* synthetic */ WithdrawActivity a;

    agr(WithdrawActivity withdrawActivity) {
        this.a = withdrawActivity;
    }

    public void onClick(View view) {
        this.a.b.setText("");
    }
}
