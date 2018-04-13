package com.prolificinteractive.materialcalendarview;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import android.util.TypedValue;
import android.widget.ImageView;

class h extends ImageView {
    public h(Context context) {
        super(context);
        setBackgroundResource(a(context));
    }

    private static int a(Context context) {
        int identifier = context.getResources().getIdentifier("selectableItemBackgroundBorderless", "attr", context.getPackageName());
        if (identifier == 0) {
            if (VERSION.SDK_INT >= 21) {
                identifier = 16843868;
            } else {
                identifier = 16843534;
            }
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(identifier, typedValue, true);
        return typedValue.resourceId;
    }

    public void setColor(int i) {
        setColorFilter(i, Mode.SRC_ATOP);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(z ? 1.0f : 0.1f);
    }
}
