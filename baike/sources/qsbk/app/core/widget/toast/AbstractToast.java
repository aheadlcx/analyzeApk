package qsbk.app.core.widget.toast;

import android.support.annotation.StringRes;
import android.view.View;

public abstract class AbstractToast {
    public static final int LENGTH_LONG = 1;
    public static final int LENGTH_SHORT = 0;

    public abstract void cancel();

    public abstract int getDuration();

    public abstract int getGravity();

    public abstract float getHorizontalMargin();

    public abstract float getVerticalMargin();

    public abstract View getView();

    public abstract int getXOffset();

    public abstract int getYOffset();

    public abstract void setDuration(int i);

    public abstract void setGravity(int i, int i2, int i3);

    public abstract void setMargin(float f, float f2);

    public abstract void setText(@StringRes int i);

    public abstract void setText(CharSequence charSequence);

    public abstract void setView(View view);

    public abstract void show();
}
