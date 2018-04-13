package qsbk.app.pay.ui;

class ak implements Runnable {
    final /* synthetic */ WithdrawRecordFragment a;

    ak(WithdrawRecordFragment withdrawRecordFragment) {
        this.a = withdrawRecordFragment;
    }

    public void run() {
        this.a.b.setRefreshing(true);
        this.a.b.setVisibility(0);
        this.a.b.setEnabled(true);
        this.a.h = 1;
        this.a.b();
    }
}
