package c.a.i;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import c.a.b.a.b;
import c.a.d.a.a;
import c.a.i.b.e;

public class c extends CardView implements u {
    private static final int[] a = new int[]{16842801};
    private int b;
    private int c;

    public c(Context context) {
        this(context, null);
    }

    public c(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 0;
        this.c = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.a.b.a.c.CardView, i, b.CardView);
        if (obtainStyledAttributes.hasValue(c.a.b.a.c.CardView_cardBackgroundColor)) {
            this.c = obtainStyledAttributes.getResourceId(c.a.b.a.c.CardView_cardBackgroundColor, 0);
        } else {
            obtainStyledAttributes = getContext().obtainStyledAttributes(a);
            this.b = obtainStyledAttributes.getResourceId(0, 0);
            obtainStyledAttributes.recycle();
        }
        a();
    }

    private void a() {
        this.c = e.b(this.c);
        this.b = e.b(this.b);
        if (this.c != 0) {
            setCardBackgroundColor(a.a().c(this.c));
        } else if (this.b != 0) {
            int color;
            float[] fArr = new float[3];
            Color.colorToHSV(a.a().a(this.b), fArr);
            if (fArr[2] > 0.5f) {
                color = getResources().getColor(c.a.b.a.a.cardview_light_background);
            } else {
                color = getResources().getColor(c.a.b.a.a.cardview_dark_background);
            }
            setCardBackgroundColor(ColorStateList.valueOf(color));
        }
    }

    public void d() {
        a();
    }
}
