package cn.xiaochuankeji.tieba.widget.fits;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import c.a.i.q;

public class FitsRelativeLayout extends q implements a {
    private Rect a;

    public FitsRelativeLayout(Context context) {
        this(context, null);
    }

    public FitsRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FitsRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Rect();
        a();
    }

    private void a() {
        ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener(this) {
            final /* synthetic */ FitsRelativeLayout a;

            {
                this.a = r1;
            }

            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                if (VERSION.SDK_INT < 20) {
                    return windowInsetsCompat.consumeSystemWindowInsets();
                }
                this.a.a.left = windowInsetsCompat.getSystemWindowInsetLeft();
                this.a.a.top = windowInsetsCompat.getSystemWindowInsetTop();
                this.a.a.right = windowInsetsCompat.getSystemWindowInsetRight();
                this.a.a.bottom = windowInsetsCompat.getSystemWindowInsetBottom();
                int childCount = this.a.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    this.a.a(this.a.getChildAt(i), this.a.a);
                }
                return windowInsetsCompat.replaceSystemWindowInsets(0, 0, 0, this.a.a.bottom);
            }
        });
    }

    public Rect getWindowInsets() {
        return this.a;
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        a(view, this.a);
    }

    protected void a(View view, Rect rect) {
        int i = 0;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup instanceof a) {
                if (VERSION.SDK_INT > 19) {
                    fitSystemWindows(rect);
                } else {
                    super.fitSystemWindows(rect);
                }
            } else if (!ViewCompat.getFitsSystemWindows(viewGroup)) {
                int childCount = viewGroup.getChildCount();
                while (i < childCount) {
                    a(viewGroup.getChildAt(i), rect);
                    i++;
                }
            } else if (view instanceof Toolbar) {
                viewGroup.setPadding(rect.left, rect.top, rect.right, 0);
            } else {
                viewGroup.setPadding(rect.left, rect.top, rect.right, rect.bottom);
            }
        }
    }

    protected boolean fitSystemWindows(Rect rect) {
        if (VERSION.SDK_INT != 19) {
            return super.fitSystemWindows(rect);
        }
        this.a.set(rect);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            a(getChildAt(i), this.a);
        }
        rect.top = 0;
        return super.fitSystemWindows(rect);
    }
}
