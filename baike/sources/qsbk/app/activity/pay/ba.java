package qsbk.app.activity.pay;

import java.util.Iterator;
import qsbk.app.widget.VirtualKeyboardView.Key;
import qsbk.app.widget.VirtualKeyboardView.OnKeyPressListener;

class ba implements OnKeyPressListener {
    final /* synthetic */ LaiseePaymentActivity a;

    ba(LaiseePaymentActivity laiseePaymentActivity) {
        this.a = laiseePaymentActivity;
    }

    public void onKeyPressed(Key key) {
        if (key.value != -2) {
            if (key.value != -1) {
                this.a.c.add(key);
            } else if (this.a.c.size() > 0) {
                this.a.c.removeLast();
            }
            this.a.j();
            if (this.a.c.size() == 6) {
                synchronized (LaiseePaymentActivity.class) {
                    StringBuilder stringBuilder = new StringBuilder();
                    Iterator it = this.a.c.iterator();
                    while (it.hasNext()) {
                        stringBuilder.append(((Key) it.next()).value + "");
                    }
                    this.a.a(stringBuilder.toString());
                }
            }
        }
    }
}
