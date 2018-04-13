package qsbk.app.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import qsbk.app.model.CircleArticle;

class afr implements OnItemClickListener {
    final /* synthetic */ VideoImmersionCircleActivity a;

    afr(VideoImmersionCircleActivity videoImmersionCircleActivity) {
        this.a = videoImmersionCircleActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Object item = adapterView.getAdapter().getItem(i);
        if (item != null && (item instanceof CircleArticle)) {
            CircleArticleActivity.launch(this.a, (CircleArticle) item, false);
        }
    }
}
