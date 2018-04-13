package qsbk.app.activity.pay;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import qsbk.app.utils.LogUtil;

class m implements OnGlobalLayoutListener {
    final /* synthetic */ ItemSignCardPaymentActivity a;

    m(ItemSignCardPaymentActivity itemSignCardPaymentActivity) {
        this.a = itemSignCardPaymentActivity;
    }

    public void onGlobalLayout() {
        View view = (View) this.a.m.getParent();
        int measureWrapContentHeight = this.a.m.getMeasureWrapContentHeight();
        int height = view.getHeight();
        if (this.a.m.getVisibility() == 0) {
            height = ((height - measureWrapContentHeight) - this.a.c.getHeight()) / 2;
        } else {
            height = (height - this.a.c.getHeight()) / 2;
        }
        this.a.c.animate().y((float) height).start();
        LogUtil.d("key board global layout = " + this.a.m.getVisibility() + " top = " + this.a.m.getTop() + "height = " + this.a.m.getHeight() + "abs top = ");
    }
}
