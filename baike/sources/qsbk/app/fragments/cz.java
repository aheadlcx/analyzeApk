package qsbk.app.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.activity.SingleArticle;
import qsbk.app.model.Article;

class cz implements OnItemClickListener {
    final /* synthetic */ HotCommentQiuShiFragment a;

    cz(HotCommentQiuShiFragment hotCommentQiuShiFragment) {
        this.a = hotCommentQiuShiFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Object item = adapterView.getAdapter().getItem(i);
        if (item instanceof Article) {
            SingleArticle.launch(this.a.getActivity(), ((Article) item).id);
        }
    }
}
