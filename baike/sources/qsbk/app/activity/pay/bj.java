package qsbk.app.activity.pay;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import qsbk.app.utils.LogUtil;

class bj implements OnGlobalLayoutListener {
    final /* synthetic */ PayPWDUniversalActivity a;

    bj(PayPWDUniversalActivity payPWDUniversalActivity) {
        this.a = payPWDUniversalActivity;
    }

    public void onGlobalLayout() {
        View view = (View) this.a.i.getParent();
        int measureWrapContentHeight = this.a.i.getMeasureWrapContentHeight();
        int height = view.getHeight();
        if (this.a.i.getVisibility() == 0) {
            height = ((height - measureWrapContentHeight) - this.a.g.getHeight()) / 2;
        } else {
            height = (height - this.a.g.getHeight()) / 2;
        }
        this.a.g.animate().y((float) height).start();
        LogUtil.d("key board global layout = " + this.a.i.getVisibility() + " top = " + this.a.i.getTop() + "height = " + this.a.i.getHeight() + "abs top = ");
    }
}
