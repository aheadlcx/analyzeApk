package cn.tatagou.sdk.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class IconTextView extends TextView {
    public IconTextView(Context context) {
        super(context);
        setTypeface(Typeface.createFromAsset(context.getAssets(), "iconfont.ttf"));
    }

    public IconTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setTypeface(Typeface.createFromAsset(context.getAssets(), "iconfont.ttf"));
    }

    public IconTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
