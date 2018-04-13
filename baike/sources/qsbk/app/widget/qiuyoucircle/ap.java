package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.CircleArticleBus;

class ap implements OnClickListener {
    final /* synthetic */ NormalCell a;

    ap(NormalCell normalCell) {
        this.a = normalCell;
    }

    public void onClick(View view) {
        this.a.checkJoinTopic();
        CircleArticle circleArticle = (CircleArticle) this.a.getItem();
        circleArticle.voteInfo.vote(circleArticle.id, 0);
        CircleArticleBus.updateArticle(circleArticle, null);
    }
}
