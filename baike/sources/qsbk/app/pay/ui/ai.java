package qsbk.app.pay.ui;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

class ai extends OnScrollListener {
    final /* synthetic */ WithdrawRecordFragment a;

    ai(WithdrawRecordFragment withdrawRecordFragment) {
        this.a = withdrawRecordFragment;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        if (!this.a.i && this.a.j && i2 > 0) {
            this.a.c();
        }
    }
}
