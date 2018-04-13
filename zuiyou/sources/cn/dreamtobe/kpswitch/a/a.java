package cn.dreamtobe.kpswitch.a;

import android.view.View;
import cn.dreamtobe.kpswitch.b.c;

public class a {
    private final View a;
    private boolean b;
    private View c;

    public a(View view) {
        this.a = view;
    }

    public void a(boolean z) {
        this.b = z;
        if (!z && this.a.getVisibility() == 4) {
            this.a.setVisibility(8);
        }
        if (z && this.a.getVisibility() == 0) {
            this.a.setVisibility(8);
        }
        if (!z && this.c != null) {
            a();
            this.c = null;
        }
    }

    private void a() {
        this.a.setVisibility(4);
        c.a(this.c);
    }
}
