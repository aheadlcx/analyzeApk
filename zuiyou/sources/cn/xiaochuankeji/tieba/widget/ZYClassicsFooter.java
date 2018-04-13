package cn.xiaochuankeji.tieba.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

public class ZYClassicsFooter extends ClassicsFooter {
    public static String a = "没有更多内容了";

    public ZYClassicsFooter(Context context) {
        super(context);
    }

    public ZYClassicsFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ZYClassicsFooter(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean a(boolean z) {
        if (this.r != z) {
            this.r = z;
            if (z) {
                this.i.setText(a);
                this.j.setVisibility(8);
            } else {
                this.i.setText(b);
                this.j.setVisibility(0);
            }
            if (this.m != null) {
                this.m.stop();
            } else {
                this.k.animate().rotation(0.0f).setDuration(300);
            }
            this.k.setVisibility(8);
        }
        return true;
    }
}
