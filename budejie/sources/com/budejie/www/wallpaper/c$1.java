package com.budejie.www.wallpaper;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.wallpaper.a.a;

class c$1 implements OnClickListener {
    final /* synthetic */ c$a a;
    final /* synthetic */ c b;

    c$1(c cVar, c$a c_a) {
        this.b = cVar;
        this.a = c_a;
    }

    public void onClick(View view) {
        int layoutPosition = this.a.getLayoutPosition();
        if (layoutPosition >= 0 && layoutPosition <= c.a(this.b).size()) {
            a aVar = (a) c.a(this.b).get(layoutPosition);
            if (aVar != null) {
                com.budejie.www.util.a.a(c.b(this.b), aVar.a(), aVar.b());
            }
        }
    }
}
