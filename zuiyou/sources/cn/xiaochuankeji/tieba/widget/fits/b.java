package cn.xiaochuankeji.tieba.widget.fits;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

public class b extends ViewPager {
    private WindowInsetsCompat a;

    public b(Context context) {
        this(context, null);
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (VERSION.SDK_INT >= 21 && ViewCompat.getFitsSystemWindows(this)) {
            setSystemUiVisibility(1280);
            ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                    this.a.a = windowInsetsCompat;
                    this.a.requestLayout();
                    if (VERSION.SDK_INT >= 20) {
                        return windowInsetsCompat.consumeSystemWindowInsets();
                    }
                    return windowInsetsCompat;
                }
            });
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Object obj = (this.a == null || !ViewCompat.getFitsSystemWindows(this)) ? null : 1;
        if (obj != null) {
            int childCount = getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() != 8) {
                    WindowInsetsCompat windowInsetsCompat = this.a;
                    if (ViewCompat.getFitsSystemWindows(childAt)) {
                        if (VERSION.SDK_INT >= 20) {
                            ViewCompat.dispatchApplyWindowInsets(childAt, windowInsetsCompat);
                        }
                    } else if (childAt.getLayoutParams() instanceof MarginLayoutParams) {
                        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();
                        if (VERSION.SDK_INT >= 20) {
                            marginLayoutParams.leftMargin = windowInsetsCompat.getSystemWindowInsetLeft();
                            marginLayoutParams.topMargin = windowInsetsCompat.getSystemWindowInsetTop();
                            marginLayoutParams.rightMargin = windowInsetsCompat.getSystemWindowInsetRight();
                            marginLayoutParams.bottomMargin = windowInsetsCompat.getSystemWindowInsetBottom();
                        }
                    }
                }
            }
        }
    }
}
