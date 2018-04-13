package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import qsbk.app.R;

public class ActionBarTabPanel extends TabPanel {
    private int a = -1;
    private int b = -1;

    public ActionBarTabPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public ActionBarTabPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public ActionBarTabPanel(Context context) {
        super(context);
        a();
    }

    public LayoutParams getTabLayoutParams() {
        return new LinearLayout.LayoutParams(this.b, this.a);
    }

    private void a() {
        this.a = getResources().getDimensionPixelSize(R.dimen.actionbar_height);
        this.b = getResources().getDimensionPixelSize(R.dimen.actionbar_navigation_item_width);
    }
}
