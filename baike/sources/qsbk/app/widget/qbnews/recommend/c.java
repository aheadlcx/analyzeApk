package qsbk.app.widget.qbnews.recommend;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.NewsWebActivity;
import qsbk.app.model.News;

class c implements OnClickListener {
    final /* synthetic */ ThreeImageNewsRecommendCell a;

    c(ThreeImageNewsRecommendCell threeImageNewsRecommendCell) {
        this.a = threeImageNewsRecommendCell;
    }

    public void onClick(View view) {
        NewsWebActivity.launch(this.a.getContext(), (News) this.a.getItem());
    }
}
