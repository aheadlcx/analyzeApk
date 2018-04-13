package cn.v6.sixrooms.widgets.phone;

import android.view.View;
import android.widget.RelativeLayout.LayoutParams;

public class ViewWrapper {
    private View a;

    public ViewWrapper(View view) {
        this.a = view;
    }

    public int getMarginLeft() {
        return ((LayoutParams) this.a.getLayoutParams()).leftMargin;
    }

    public void setMarginLeft(int i) {
        ((LayoutParams) this.a.getLayoutParams()).leftMargin = i;
        this.a.requestLayout();
    }
}
