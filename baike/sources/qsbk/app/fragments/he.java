package qsbk.app.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.ListUtil;
import qsbk.app.utils.LoginPermissionClickDelegate;

class he implements OnItemLongClickListener {
    final /* synthetic */ NearbyCircleFragment a;

    he(NearbyCircleFragment nearbyCircleFragment) {
        this.a = nearbyCircleFragment;
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerCount = ListUtil.getHeaderCount(this.a.o);
        if (i >= headerCount) {
            i -= headerCount;
        }
        Object item = this.a.r.getItem(i);
        if (item instanceof CircleArticle) {
            if (QsbkApp.currentUser != null) {
                this.a.onCircleShareStart((CircleArticle) item);
            } else {
                LoginPermissionClickDelegate.startLoginActivity(this.a.getActivity());
            }
        }
        return true;
    }
}
