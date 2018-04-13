package qsbk.app.activity.base;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import qsbk.app.R;
import qsbk.app.adapter.ArticleAdapter.ViewHolder;
import qsbk.app.model.Article;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.UIHelper;

class w implements OnItemLongClickListener {
    final /* synthetic */ BaseArticleListViewFragment a;

    w(BaseArticleListViewFragment baseArticleListViewFragment) {
        this.a = baseArticleListViewFragment;
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a.m.getAdapter().getItem(i) instanceof Article) {
            View findViewById = view.findViewById(R.id.layerMask);
            if (!UIHelper.isNightTheme()) {
                DebugUtil.debug(BaseArticleListViewFragment.Q, "open layerMask...");
                if (findViewById != null) {
                    findViewById.setVisibility(0);
                }
            }
            this.a.N = view;
            this.a.M = (Article) this.a.m.getAdapter().getItem(i);
            this.a.O = ((ViewHolder) view.getTag()).collection_icon;
            this.a.G();
        }
        return true;
    }
}
