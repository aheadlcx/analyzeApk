package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.QiushiTopicListActivity;

class dt implements OnClickListener {
    final /* synthetic */ QiushiTopicRecommendCell a;

    dt(QiushiTopicRecommendCell qiushiTopicRecommendCell) {
        this.a = qiushiTopicRecommendCell;
    }

    public void onClick(View view) {
        QiushiTopicListActivity.launch(this.a.getContext(), 0);
    }
}
