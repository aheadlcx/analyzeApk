package qsbk.app.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import qsbk.app.QsbkApp;
import qsbk.app.adapter.ManageMyContentsAdapter;
import qsbk.app.model.Article;
import qsbk.app.slide.SlideActivity;

class gc implements OnItemClickListener {
    final /* synthetic */ MyHighLightQiushiFragment a;

    gc(MyHighLightQiushiFragment myHighLightQiushiFragment) {
        this.a = myHighLightQiushiFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView.getAdapter().getItem(i) instanceof Article) {
            Article article = (Article) adapterView.getAdapter().getItem(i);
            if (article != null && article.state != null) {
                if (article.state.equalsIgnoreCase("publish") || article.state.equalsIgnoreCase(ManageMyContentsAdapter.FAKE)) {
                    this.a.t = true;
                    QsbkApp.currentDataSource = this.a.j;
                    QsbkApp.currentSelectItem = (int) adapterView.getAdapter().getItemId(i);
                    if (QsbkApp.currentSelectItem >= 0 && this.a.j.size() >= QsbkApp.currentSelectItem) {
                        if (!article.anonymous) {
                            article.user_icon = this.a.Q.userIcon;
                            article.user_id = this.a.Q.userId;
                            article.login = this.a.Q.userName;
                        }
                        Intent intent = new Intent(this.a.getContext(), SlideActivity.class);
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
    }
}
