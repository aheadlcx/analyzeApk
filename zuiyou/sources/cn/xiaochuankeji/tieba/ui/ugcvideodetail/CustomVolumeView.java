package cn.xiaochuankeji.tieba.ui.ugcvideodetail;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import cn.htjyb.c.a;

public class CustomVolumeView extends ProgressBar {
    private Context a;
    private int b;
    private Runnable c = new Runnable(this) {
        final /* synthetic */ CustomVolumeView a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.setVisibility(8);
        }
    };

    public CustomVolumeView(Context context) {
        super(context);
        a(context);
    }

    public CustomVolumeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public CustomVolumeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.a = context;
        this.b = a.j(this.a);
        setMax(this.b);
    }

    public void a(int i) {
        int i2 = 1;
        int i3 = 0;
        int i4 = i == 25 ? 1 : 0;
        if (i != 24) {
            i2 = 0;
        }
        if (i4 != 0 || r1 != 0) {
            if (getVisibility() != 0) {
                setVisibility(0);
            }
            removeCallbacks(this.c);
            i2 = a.k(this.a);
            if (i4 != 0) {
                i2--;
                if (i2 >= 0) {
                    i3 = i2;
                }
            } else {
                i3 = i2 + 1;
                if (i3 > this.b) {
                    i3 = this.b;
                }
            }
            setProgress(i3);
            a.a(this.a, i3);
        }
    }

    public void a() {
        postDelayed(this.c, 2000);
    }
}
