package qsbk.app.adapter;

import android.text.Layout;

class ar implements Runnable {
    final /* synthetic */ b a;

    ar(b bVar) {
        this.a = bVar;
    }

    public void run() {
        Layout layout = this.a.k.getLayout();
        if (layout != null) {
            this.a.l.setVisibility(8);
            if (layout.getLineCount() >= 4 && layout.getLineEnd(3) < this.a.k.getText().length()) {
                this.a.l.setVisibility(0);
            }
        }
    }
}
