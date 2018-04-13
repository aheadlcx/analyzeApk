package qsbk.app.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.ListUtil;
import qsbk.app.utils.LoginPermissionClickDelegate;

class cu implements OnItemLongClickListener {
    final /* synthetic */ HotCommentCircleFragment a;

    cu(HotCommentCircleFragment hotCommentCircleFragment) {
        this.a = hotCommentCircleFragment;
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerCount = ListUtil.getHeaderCount(this.a.g);
        if (i >= headerCount) {
            i -= headerCount;
        }
        Object item = this.a.j.getItem(i);
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
