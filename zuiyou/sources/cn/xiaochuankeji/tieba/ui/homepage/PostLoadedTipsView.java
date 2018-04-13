package cn.xiaochuankeji.tieba.ui.homepage;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;

public class PostLoadedTipsView extends LinearLayout {
    private TextView a;
    private LinearLayout b;

    public PostLoadedTipsView(Context context) {
        super(context);
        a(context);
    }

    public PostLoadedTipsView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public PostLoadedTipsView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_postloaded_tips, this);
        this.a = (TextView) findViewById(R.id.content_label);
        this.b = (LinearLayout) findViewById(R.id.tip_layout);
    }

    public void setText(CharSequence charSequence) {
        this.a.setText(charSequence);
    }

    public void setTipBackground(@DrawableRes int i) {
        if (this.b != null) {
            this.b.setBackgroundResource(i);
        }
    }
}
