package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import qsbk.app.model.CircleArticle;
import qsbk.app.share.ShareUtils;

class afs implements OnItemLongClickListener {
    final /* synthetic */ VideoImmersionCircleActivity a;

    afs(VideoImmersionCircleActivity videoImmersionCircleActivity) {
        this.a = videoImmersionCircleActivity;
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        Object item = adapterView.getAdapter().getItem(i);
        if (item != null && (item instanceof CircleArticle)) {
            this.a.p = (CircleArticle) item;
            ShareUtils.openShareDialog(this.a, 1, this.a.p);
        }
        return false;
    }
}
