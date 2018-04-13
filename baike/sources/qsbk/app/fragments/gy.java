package qsbk.app.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.ListUtil;

class gy implements OnItemClickListener {
    final /* synthetic */ MyQiuYouDynamicFragment a;

    gy(MyQiuYouDynamicFragment myQiuYouDynamicFragment) {
        this.a = myQiuYouDynamicFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerCount = ListUtil.getHeaderCount(this.a.e);
        if (i >= headerCount) {
            headerCount = i - headerCount;
            if (headerCount < this.a.f.getCount()) {
                CircleArticleActivity.launch(this.a.getActivity(), (CircleArticle) this.a.f.getItem(headerCount), false);
            }
        }
    }
}
