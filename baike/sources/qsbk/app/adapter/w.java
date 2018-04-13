package qsbk.app.adapter;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.SingleArticle;

class w implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ ArticleAdapter b;

    w(ArticleAdapter articleAdapter, int i) {
        this.b = articleAdapter;
        this.a = i;
    }

    public void onClick(View view) {
        QsbkApp.currentDataSource = this.b.m;
        QsbkApp.currentSelectItem = (int) this.b.getItemId(this.a);
        Intent intent = new Intent(this.b.k, SingleArticle.class);
        intent.putExtra("scroll_to_comment", true);
        this.b.k.startActivity(intent);
    }
}
