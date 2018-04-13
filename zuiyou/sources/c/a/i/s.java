package c.a.i;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import c.a.a.a;
import c.a.i.b.h;

public class s extends AppCompatSeekBar implements u {
    private h a;

    public s(Context context) {
        this(context, null);
    }

    public s(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.seekBarStyle);
    }

    public s(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new h(this);
        this.a.a(attributeSet, i);
    }

    public void d() {
        if (this.a != null) {
            this.a.a();
        }
    }
}
