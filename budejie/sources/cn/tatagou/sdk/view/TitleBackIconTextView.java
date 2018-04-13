package cn.tatagou.sdk.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import cn.tatagou.sdk.pojo.TtgTitleBar;

public class TitleBackIconTextView extends TextView {
    public TitleBackIconTextView(Context context) {
        super(context);
        setTextStyle(context);
    }

    public TitleBackIconTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setTextStyle(context);
    }

    public TitleBackIconTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setTextStyle(context);
    }

    private void setTextStyle(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), "iconfont.ttf"));
        setTextColor(TtgTitleBar.getInstance().getBackIconColor());
    }
}
