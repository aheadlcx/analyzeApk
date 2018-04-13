package qsbk.app.activity.pay;

import java.util.Iterator;
import qsbk.app.widget.VirtualKeyboardView.Key;
import qsbk.app.widget.VirtualKeyboardView.OnKeyPressListener;

class bh implements OnKeyPressListener {
    final /* synthetic */ PayPWDUniversalActivity a;

    bh(PayPWDUniversalActivity payPWDUniversalActivity) {
        this.a = payPWDUniversalActivity;
    }

    public void onKeyPressed(Key key) {
        if (key.value != -2) {
            if (key.value != -1) {
                this.a.a.add(key);
            } else if (this.a.a.size() > 0) {
                this.a.a.removeLast();
            }
            this.a.a();
            if (this.a.a.size() == 6) {
                synchronized (LaiseePaymentActivity.class) {
                    StringBuilder stringBuilder = new StringBuilder();
                    Iterator it = this.a.a.iterator();
                    while (it.hasNext()) {
                        stringBuilder.append(((Key) it.next()).value);
                    }
                    this.a.a(stringBuilder.toString());
                }
            }
        }
    }
}
