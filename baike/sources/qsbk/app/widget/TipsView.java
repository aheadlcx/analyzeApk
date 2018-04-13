package qsbk.app.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.DebugUtil;

public class TipsView extends RelativeLayout {
    private static final String a = TipsView.class.getSimpleName();
    private RelativeLayout b;
    private TextView c;
    private String d;
    private int e;

    public TipsView(Context context) {
        super(context);
    }

    public TipsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.widget_tips_view, this, true);
        a(context);
        a(context, attributeSet);
    }

    public TipsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @SuppressLint({"NewApi"})
    public TipsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    private void a(Context context) {
        this.b = (RelativeLayout) findViewById(R.id.rl_tips);
        this.c = (TextView) findViewById(R.id.tv_tips);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TipsView);
        this.d = obtainStyledAttributes.getString(0);
        this.e = obtainStyledAttributes.getColor(3, -1);
        obtainStyledAttributes.recycle();
    }

    public void setTipsViewTextContent(String str) {
        DebugUtil.debug(a, "setTipsViewTextContent, " + str);
        this.d = str;
        this.c.setText(this.d);
    }

    public void setTipsViewBgColor(int i) {
        this.e = i;
        setBackgroundColor(this.e);
        invalidate();
    }
}
