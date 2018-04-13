package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.HashMap;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.ToastAndDialog;

class gt implements OnClickListener {
    final /* synthetic */ CircleArticleImageViewer a;

    gt(CircleArticleImageViewer circleArticleImageViewer) {
        this.a = circleArticleImageViewer;
    }

    public void onClick(View view) {
        if (!this.a.i.liked) {
            ToastAndDialog.scale(view, true);
            CircleArticle c = this.a.i;
            c.likeCount++;
            this.a.m.setAnimationDuration((long) this.a.getResources().getInteger(R.integer.vote_duration));
            this.a.m.setText(String.valueOf(this.a.i.likeCount), true);
            this.a.i.liked = true;
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.CIRCLE_ARTICLE_LIKE, new Object[]{this.a.i.id}), new gu(this, view));
            this.a.n.setEnabled(false);
            this.a.m.setEnabled(false);
            simpleHttpTask.setMapParams(new HashMap());
            simpleHttpTask.execute();
            CircleArticleBus.updateArticle(this.a.i, this.a);
        }
    }
}
