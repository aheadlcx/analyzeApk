package cn.tatagou.sdk.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import cn.tatagou.sdk.pojo.TtgTitleBar;

public class TitleBarIconTextView extends TextView {
    public TitleBarIconTextView(Context context) {
        super(context);
        setTypeface(Typeface.createFromAsset(context.getAssets(), "iconfont.ttf"));
        setTextColor(TtgTitleBar.getInstance().getIconColor());
    }

    public TitleBarIconTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setTypeface(Typeface.createFromAsset(context.getAssets(), "iconfont.ttf"));
        setTextColor(TtgTitleBar.getInstance().getIconColor());
    }

    public TitleBarIconTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
