package cn.tatagou.sdk.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import cn.tatagou.sdk.android.TtgConfig;

public class ThemeIconFontTextView extends TextView {
    public ThemeIconFontTextView(Context context) {
        super(context);
        setTypeface(Typeface.createFromAsset(context.getAssets(), "iconfont.ttf"));
        setTextColor(TtgConfig.getInstance().getThemeColor());
    }

    public ThemeIconFontTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setTypeface(Typeface.createFromAsset(context.getAssets(), "iconfont.ttf"));
        setTextColor(TtgConfig.getInstance().getThemeColor());
    }

    public ThemeIconFontTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
