package qsbk.app.activity.pay;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import qsbk.app.utils.LogUtil;

class bd implements OnGlobalLayoutListener {
    final /* synthetic */ LaiseePaymentActivity a;

    bd(LaiseePaymentActivity laiseePaymentActivity) {
        this.a = laiseePaymentActivity;
    }

    public void onGlobalLayout() {
        View view = (View) this.a.p.getParent();
        int measureWrapContentHeight = this.a.p.getMeasureWrapContentHeight();
        int height = view.getHeight();
        if (this.a.p.getVisibility() == 0) {
            height = ((height - measureWrapContentHeight) - this.a.e.getHeight()) / 2;
        } else {
            height = (height - this.a.e.getHeight()) / 2;
        }
        this.a.e.animate().y((float) height).start();
        LogUtil.d("key board global layout = " + this.a.p.getVisibility() + " top = " + this.a.p.getTop() + "height = " + this.a.p.getHeight() + "abs top = ");
    }
}
