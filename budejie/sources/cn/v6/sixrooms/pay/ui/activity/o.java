package cn.v6.sixrooms.pay.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;

final class o implements OnClickListener {
    final /* synthetic */ BanklineActivity a;

    o(BanklineActivity banklineActivity) {
        this.a = banklineActivity;
    }

    public final void onClick(View view) {
        this.a.showMenu();
    }
}
