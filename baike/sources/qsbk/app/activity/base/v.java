package qsbk.app.activity.base;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import qsbk.app.QsbkApp;
import qsbk.app.abtest.ArticleActionStater;
import qsbk.app.adapter.ManageMyContentsAdapter;
import qsbk.app.model.Article;
import qsbk.app.model.RssArticle;
import qsbk.app.slide.SlideActivity;
import qsbk.app.utils.DebugUtil;

class v implements OnItemClickListener {
    final /* synthetic */ BaseArticleListViewFragment a;

    v(BaseArticleListViewFragment baseArticleListViewFragment) {
        this.a = baseArticleListViewFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Object item = adapterView.getAdapter().getItem(i);
        if (item instanceof Article) {
            if (item instanceof RssArticle) {
                ArticleActionStater.getInstance().statAction(ArticleActionStater.ACTION_DETAIL, ((Article) item).getStatType());
            }
            if (!(item instanceof Article) || ((Article) item).state == "pending" || ((Article) item).state == ManageMyContentsAdapter.WAITING) {
                this.a.t = true;
                QsbkApp.currentDataSource = this.a.j;
                QsbkApp.currentSelectItem = (int) adapterView.getAdapter().getItemId(i);
                DebugUtil.debug(BaseArticleListViewFragment.Q, "click item position：" + QsbkApp.currentSelectItem);
            } else {
                this.a.t = true;
                QsbkApp.currentDataSource = this.a.j;
                QsbkApp.currentSelectItem = (int) adapterView.getAdapter().getItemId(i);
                DebugUtil.debug(BaseArticleListViewFragment.Q, "click item position：" + QsbkApp.currentSelectItem);
            }
            if (QsbkApp.currentSelectItem >= 0 && this.a.j.size() >= QsbkApp.currentSelectItem) {
                Intent intent = new Intent(this.a.getActivity(), SlideActivity.class);
                intent.putExtra("url", this.a.u);
                intent.putExtra(ParamKey.PAGE, this.a.o);
                int headerViewsCount = i - this.a.m.getHeaderViewsCount();
                String str = "position";
                if (headerViewsCount <= 0) {
                    headerViewsCount = 0;
                }
                intent.putExtra(str, headerViewsCount);
                intent.putExtra("hasAd", this.a.o());
                this.a.startActivityForResult(intent, 0);
            }
        }
    }
}
