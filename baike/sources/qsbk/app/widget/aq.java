package qsbk.app.widget;

import android.text.Editable;
import android.text.TextWatcher;
import java.util.Iterator;
import qsbk.app.widget.CircleEditText.CircleText;

class aq implements TextWatcher {
    final /* synthetic */ CircleEditText a;
    private boolean b = false;
    private int c;
    private int d;
    private int e;

    aq(CircleEditText circleEditText) {
        this.a = circleEditText;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!this.b) {
            this.c = i;
            this.d = i2;
            this.e = i3;
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        if (!this.b) {
            this.b = true;
            Iterator it = CircleEditText.a(this.a).iterator();
            while (it.hasNext()) {
                CircleText circleText = (CircleText) it.next();
                int i = circleText.index;
                int length = circleText.index + circleText.text.length();
                int i2 = this.c + this.d;
                if (this.c < length && i2 > i) {
                    int i3;
                    if (i2 < length) {
                        editable.delete(i2, length);
                        this.d = length - this.c;
                        i3 = length;
                    } else {
                        i3 = i2;
                    }
                    if (this.c > i) {
                        editable.delete(i, this.c);
                        this.c = i;
                        this.d = i3 - this.c;
                    }
                    it.remove();
                } else if (i2 <= i) {
                    circleText.index += this.e - this.d;
                }
            }
            this.b = false;
        }
    }
}
