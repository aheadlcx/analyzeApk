package qsbk.app.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import qsbk.app.utils.UIHelper;

public class NightModeMaskView extends View {
    public NightModeMaskView(Context context) {
        super(context);
        a();
    }

    public NightModeMaskView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public NightModeMaskView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    @TargetApi(21)
    public NightModeMaskView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }

    private void a() {
        setBackgroundColor(Color.parseColor("#8f000000"));
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (UIHelper.isNightTheme()) {
            setVisibility(4);
        } else {
            setVisibility(4);
        }
    }
}
