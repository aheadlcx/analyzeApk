package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.widget.GlobalBarrageLayout.BarrageItem;

class ee implements OnClickListener {
    final /* synthetic */ BarrageItem a;

    ee(BarrageItem barrageItem) {
        this.a = barrageItem;
    }

    public void onClick(View view) {
        if (this.a.a.d != null && this.a.c.getUserId() > 0) {
            this.a.a.d.onGlobalBarrageClicked(this.a.c);
        }
    }
}
