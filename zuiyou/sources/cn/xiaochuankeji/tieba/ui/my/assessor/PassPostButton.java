package cn.xiaochuankeji.tieba.ui.my.assessor;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import cn.xiaochuankeji.tieba.R;

public class PassPostButton extends a {
    public PassPostButton(Context context) {
        super(context);
    }

    public PassPostButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PassPostButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void a(Resources resources) {
        this.a = resources.getDrawable(R.drawable.btn_pass_post_bg_normal);
        this.b = resources.getDrawable(R.drawable.btn_pass_post_fg_normal);
        this.d = resources.getDrawable(R.drawable.btn_pass_post_bg_selected);
        this.c = resources.getDrawable(R.drawable.btn_pass_post_fg_selected);
    }
}
