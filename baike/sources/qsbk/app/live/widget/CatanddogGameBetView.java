package qsbk.app.live.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout.LayoutParams;
import qsbk.app.core.utils.WindowUtils;

public class CatanddogGameBetView extends GameBetView {
    public CatanddogGameBetView(Context context) {
        super(context);
    }

    public CatanddogGameBetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CatanddogGameBetView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        this.b.setTextColor(Color.parseColor("#ff7272"));
        this.c.setTextColor(Color.parseColor("#ff7272"));
    }

    public void setText(String str) {
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        layoutParams.topMargin = WindowUtils.dp2Px(5);
        this.a.setLayoutParams(layoutParams);
        super.setText(str);
    }
}
