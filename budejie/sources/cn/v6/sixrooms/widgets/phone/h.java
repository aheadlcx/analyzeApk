package cn.v6.sixrooms.widgets.phone;

final class h implements Runnable {
    final /* synthetic */ ExpressionKeyboard a;

    h(ExpressionKeyboard expressionKeyboard) {
        this.a = expressionKeyboard;
    }

    public final void run() {
        if (this.a.h != null) {
            this.a.h.deleteSmileyVo(this.a.g);
        }
        this.a.i.postDelayed(this.a.a, 300);
    }
}
