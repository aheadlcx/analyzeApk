package qsbk.app.adapter;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.SingleArticle;
import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;

class ec implements OnClickListener {
    final /* synthetic */ VideoImmersionCell a;

    ec(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void onClick(View view) {
        QsbkApp.currentDataSource = this.a.a.m;
        QsbkApp.currentSelectItem = (int) this.a.a.getItemId(this.a.q);
        Intent intent = new Intent(this.a.a.k, SingleArticle.class);
        intent.putExtra("scroll_to_comment", true);
        this.a.a.k.startActivity(intent);
    }
}
