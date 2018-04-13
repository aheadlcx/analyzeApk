package qsbk.app.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.widget.VirtualKeyboardView.Key;

class fd implements OnItemClickListener {
    final /* synthetic */ VirtualKeyboardView a;

    fd(VirtualKeyboardView virtualKeyboardView) {
        this.a = virtualKeyboardView;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Key key = (Key) this.a.c.get(i);
        if (this.a.e != null) {
            this.a.e.onKeyPressed(key);
        }
    }
}
