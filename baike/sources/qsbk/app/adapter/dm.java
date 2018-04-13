package qsbk.app.adapter;

import android.text.Layout;

class dm implements Runnable {
    final /* synthetic */ b a;

    dm(b bVar) {
        this.a = bVar;
    }

    public void run() {
        Layout layout = this.a.l.getLayout();
        if (layout != null) {
            this.a.m.setVisibility(8);
            if (layout.getLineCount() >= 4 && layout.getLineEnd(3) < this.a.l.getText().length()) {
                this.a.m.setVisibility(0);
            }
        }
    }
}
