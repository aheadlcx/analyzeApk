package qsbk.app.activity.pay;

import java.util.Iterator;
import qsbk.app.widget.VirtualKeyboardView.Key;
import qsbk.app.widget.VirtualKeyboardView.OnKeyPressListener;

class j implements OnKeyPressListener {
    final /* synthetic */ ItemSignCardPaymentActivity a;

    j(ItemSignCardPaymentActivity itemSignCardPaymentActivity) {
        this.a = itemSignCardPaymentActivity;
    }

    public void onKeyPressed(Key key) {
        if (key.value != -2) {
            if (key.value != -1) {
                this.a.a.add(key);
            } else if (this.a.a.size() > 0) {
                this.a.a.removeLast();
            }
            this.a.d();
            if (this.a.a.size() == 6) {
                synchronized (ItemSignCardPaymentActivity.class) {
                    StringBuilder stringBuilder = new StringBuilder();
                    Iterator it = this.a.a.iterator();
                    while (it.hasNext()) {
                        stringBuilder.append(((Key) it.next()).value + "");
                    }
                    this.a.a(stringBuilder.toString());
                }
            }
        }
    }
}
