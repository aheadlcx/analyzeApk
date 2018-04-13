package qsbk.app.widget.qbnews.recommend;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.NewsWebActivity;
import qsbk.app.model.News;

class a implements OnClickListener {
    final /* synthetic */ OneImageNewsRecommendCell a;

    a(OneImageNewsRecommendCell oneImageNewsRecommendCell) {
        this.a = oneImageNewsRecommendCell;
    }

    public void onClick(View view) {
        NewsWebActivity.launch(this.a.getContext(), (News) this.a.getItem());
    }
}
