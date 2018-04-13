package com.c.c;

import android.view.View;
import android.view.View.OnClickListener;

final class b implements OnClickListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        if (this.a.b != null) {
            this.a.b.onFooterError();
        }
    }
}
