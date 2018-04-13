package c.a.i;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import c.a.i.b.a;
import c.a.i.b.f;

public class i extends AppCompatImageView implements u {
    private a a;
    private f b;

    public i(Context context) {
        this(context, null);
    }

    public i(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public i(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new a(this);
        this.a.a(attributeSet, i);
        this.b = new f(this);
        this.b.a(attributeSet, i);
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        if (this.a != null) {
            this.a.a(i);
        }
    }

    public void setImageResource(@DrawableRes int i) {
        if (this.b != null) {
            this.b.a(i);
        }
    }

    public void d() {
        if (this.a != null) {
            this.a.a();
        }
        if (this.b != null) {
            this.b.a();
        }
    }
}
