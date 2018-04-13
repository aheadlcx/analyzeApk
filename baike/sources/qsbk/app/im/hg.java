package qsbk.app.im;

import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.UIHelper;

class hg implements OnClickListener {
    final /* synthetic */ IMMessageListFragment a;

    hg(IMMessageListFragment iMMessageListFragment) {
        this.a = iMMessageListFragment;
    }

    public void onClick(View view) {
        if (this.a.c == null) {
            this.a.c = new ListPopupWindow(this.a.getActivity());
            this.a.c.setWidth(UIHelper.dip2px(this.a.getActivity(), 200.0f));
            this.a.c.setAdapter(new a(this.a));
            this.a.c.setAnchorView(this.a.L);
            this.a.c.setHorizontalOffset(-100);
            this.a.c.setForceIgnoreOutsideTouch(true);
            this.a.c.setModal(true);
        }
        if (this.a.c.isShowing()) {
            this.a.c.dismiss();
        } else {
            this.a.c.show();
        }
    }
}
