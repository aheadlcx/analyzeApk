package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayout.Alignment;
import android.view.View;

final class au extends Alignment {
    final /* synthetic */ Alignment a;
    final /* synthetic */ Alignment b;

    au(Alignment alignment, Alignment alignment2) {
        this.a = alignment;
        this.b = alignment2;
    }

    int a(View view, int i) {
        Object obj = 1;
        if (ViewCompat.getLayoutDirection(view) != 1) {
            obj = null;
        }
        return (obj == null ? this.a : this.b).a(view, i);
    }

    public int getAlignmentValue(View view, int i, int i2) {
        Object obj = 1;
        if (ViewCompat.getLayoutDirection(view) != 1) {
            obj = null;
        }
        return (obj == null ? this.a : this.b).getAlignmentValue(view, i, i2);
    }

    String a() {
        return "SWITCHING[L:" + this.a.a() + ", R:" + this.b.a() + "]";
    }
}
