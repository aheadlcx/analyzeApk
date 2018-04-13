package qsbk.app.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ManageQiushiMoreActivity;
import qsbk.app.adapter.ManageMyContentsAdapter;
import qsbk.app.adapter.ManageMyContentsAdapter.ViewHolder;
import qsbk.app.model.Article;

class gd implements OnItemLongClickListener {
    final /* synthetic */ MyHighLightQiushiFragment a;

    gd(MyHighLightQiushiFragment myHighLightQiushiFragment) {
        this.a = myHighLightQiushiFragment;
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!(this.a.m.getAdapter().getItem(i) instanceof Article)) {
            return true;
        }
        Article article = (Article) adapterView.getAdapter().getItem(i);
        QsbkApp.currentDataSource = this.a.j;
        QsbkApp.currentSelectItem = (int) adapterView.getAdapter().getItemId(i);
        this.a.N = view;
        this.a.M = (Article) this.a.m.getAdapter().getItem(i);
        if (!(view == null || view.getTag() == null || !(view.getTag() instanceof ViewHolder))) {
            this.a.O = ((ViewHolder) view.getTag()).collection_icon;
        }
        if (QsbkApp.currentSelectItem < 0 || this.a.j.size() < QsbkApp.currentSelectItem) {
            return true;
        }
        if (article.state == null || QsbkApp.currentSelectItem < 0 || this.a.j.size() < QsbkApp.currentSelectItem) {
            return true;
        }
        if (article.state.equalsIgnoreCase("publish") || article.state.equalsIgnoreCase(ManageMyContentsAdapter.FAKE)) {
            this.a.c();
        } else if (article.state.equals(ManageMyContentsAdapter.NEW_PUBLISH)) {
            ManageQiushiMoreActivity.launch(this.a.B, QsbkApp.currentUser.userId, article, true);
        } else {
            ManageQiushiMoreActivity.launch(this.a.B, QsbkApp.currentUser.userId, article);
        }
        return false;
    }
}
