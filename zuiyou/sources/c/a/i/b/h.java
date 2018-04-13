package c.a.i.b;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.SeekBar;
import c.a.a.d;
import c.a.d.a.a;

public class h extends g {
    private final SeekBar a;
    private int b = 0;

    public h(SeekBar seekBar) {
        super(seekBar);
        this.a = seekBar;
    }

    @SuppressLint({"RestrictedApi"})
    public void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        TypedArray obtainStyledAttributes = this.a.getContext().obtainStyledAttributes(attributeSet, d.AppCompatSeekBar, i, 0);
        this.b = obtainStyledAttributes.getResourceId(d.AppCompatSeekBar_android_thumb, 0);
        obtainStyledAttributes.recycle();
        a();
    }

    public void a() {
        super.a();
        this.b = e.b(this.b);
        if (this.b != 0) {
            this.a.setThumb(a.a().b(this.b));
        }
    }
}
