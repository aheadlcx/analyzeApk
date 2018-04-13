package cn.xiaochuankeji.tieba.ui.my.assessor;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import c.a.d.a.a;
import cn.xiaochuankeji.tieba.R;

public class RejectPostButton extends a {
    public RejectPostButton(Context context) {
        super(context);
    }

    public RejectPostButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RejectPostButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void a(Resources resources) {
        this.a = a.a().b(R.drawable.btn_reject_post_bg_normal);
        this.b = a.a().b(R.drawable.btn_reject_post_fg_normal);
        this.d = a.a().b(R.drawable.btn_reject_post_bg_selected);
        this.c = a.a().b(R.drawable.btn_reject_post_fg_selected);
    }
}
