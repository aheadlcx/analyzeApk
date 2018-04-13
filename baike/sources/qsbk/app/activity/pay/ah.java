package qsbk.app.activity.pay;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import qsbk.app.utils.LogUtil;

class ah implements OnGlobalLayoutListener {
    final /* synthetic */ ItemUnivasualBuyActivity a;

    ah(ItemUnivasualBuyActivity itemUnivasualBuyActivity) {
        this.a = itemUnivasualBuyActivity;
    }

    public void onGlobalLayout() {
        View view = (View) this.a.n.getParent();
        int measureWrapContentHeight = this.a.n.getMeasureWrapContentHeight();
        int height = view.getHeight();
        if (this.a.n.getVisibility() == 0) {
            height = ((height - measureWrapContentHeight) - this.a.c.getHeight()) / 2;
        } else {
            height = (height - this.a.c.getHeight()) / 2;
        }
        this.a.c.animate().y((float) height).start();
        LogUtil.d("key board global layout = " + this.a.n.getVisibility() + " top = " + this.a.n.getTop() + "height = " + this.a.n.getHeight() + "abs top = ");
    }
}
