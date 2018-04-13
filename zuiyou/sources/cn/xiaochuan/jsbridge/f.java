package cn.xiaochuan.jsbridge;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnScrollChangeListener;
import com.a.a.a.c;

public class f extends c {
    private a d;

    public interface a {
        void a(View view, int i, int i2, int i3, int i4);
    }

    public f(Context context) {
        super(context);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.d != null) {
            this.d.a(this, i, i2, i3, i4);
        }
    }

    public a getOnScrollChangeListener() {
        return this.d;
    }

    public void a(final a aVar) {
        if (VERSION.SDK_INT >= 23) {
            setOnScrollChangeListener(new OnScrollChangeListener(this) {
                final /* synthetic */ f b;

                public void onScrollChange(View view, int i, int i2, int i3, int i4) {
                    aVar.a(view, i, i2, i3, i4);
                }
            });
        } else {
            this.d = aVar;
        }
    }

    public void b(a aVar) {
        this.d = null;
    }
}
