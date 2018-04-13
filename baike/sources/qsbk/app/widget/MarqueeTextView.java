package qsbk.app.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.widget.TextView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MarqueeTextView extends TextView {
    private boolean a = false;

    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void a() {
        try {
            Method declaredMethod = TextView.class.getDeclaredMethod("startStopMarquee", new Class[]{Boolean.TYPE});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, new Object[]{Boolean.valueOf(true)});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    public boolean isMarqueeEnable() {
        return this.a;
    }

    public void setMarqueeEnable(boolean z) {
        if (this.a != z) {
            this.a = z;
            if (z) {
                setEllipsize(TruncateAt.MARQUEE);
            } else {
                setEllipsize(TruncateAt.END);
            }
            onFocusChanged(z, 130, null);
            onWindowFocusChanged(z);
            a();
        }
    }

    public boolean isFocused() {
        return this.a;
    }

    public boolean isSelected() {
        return this.a;
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(this.a, i, rect);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(this.a);
    }
}
