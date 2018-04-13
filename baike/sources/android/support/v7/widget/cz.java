package android.support.v7.widget;

import android.support.v7.view.menu.ActionMenuItem;
import android.view.View;
import android.view.View.OnClickListener;

class cz implements OnClickListener {
    final ActionMenuItem a = new ActionMenuItem(this.b.a.getContext(), 0, 16908332, 0, 0, this.b.b);
    final /* synthetic */ ToolbarWidgetWrapper b;

    cz(ToolbarWidgetWrapper toolbarWidgetWrapper) {
        this.b = toolbarWidgetWrapper;
    }

    public void onClick(View view) {
        if (this.b.c != null && this.b.d) {
            this.b.c.onMenuItemSelected(0, this.a);
        }
    }
}
