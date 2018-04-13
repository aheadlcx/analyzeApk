package com.androidex.util;

class ClickControlledSpinner$1 extends Thread {
    final /* synthetic */ ClickControlledSpinner this$0;

    ClickControlledSpinner$1(ClickControlledSpinner clickControlledSpinner) {
        this.this$0 = clickControlledSpinner;
    }

    public void run() {
        ClickControlledSpinner.access$100(this.this$0).onSpinnerClick(ClickControlledSpinner.access$000(this.this$0));
    }
}
