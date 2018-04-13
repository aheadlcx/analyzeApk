package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.widgets.phone.ExpressionGroup.DeleteSmileyListener;

final class e implements DeleteSmileyListener {
    final /* synthetic */ ExpressionKeyboard a;

    e(ExpressionKeyboard expressionKeyboard) {
        this.a = expressionKeyboard;
    }

    public final void onTouchAction(int i) {
        switch (i) {
            case 0:
                if (this.a.h != null) {
                    this.a.h.deleteSmileyVo(this.a.g);
                }
                this.a.i.postDelayed(this.a.a, 1000);
                return;
            case 1:
                this.a.i.removeCallbacks(this.a.a);
                return;
            case 2:
                if (this.a.h != null) {
                    this.a.h.deleteSmileyVo(this.a.g);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
