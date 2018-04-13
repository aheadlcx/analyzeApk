package c.a.i;

import android.content.Context;
import android.support.v7.widget.AppCompatRatingBar;
import android.util.AttributeSet;
import c.a.a.a;
import c.a.i.b.g;

public class o extends AppCompatRatingBar implements u {
    private g a;

    public o(Context context) {
        this(context, null);
    }

    public o(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.ratingBarStyle);
    }

    public o(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new g(this);
        this.a.a(attributeSet, i);
    }

    public void d() {
        if (this.a != null) {
            this.a.a();
        }
    }
}
