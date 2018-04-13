package qsbk.app.widget.qiuyoucircle;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.HashMap;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.CircleArticle;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.ToastAndDialog;

class y implements OnClickListener {
    final /* synthetic */ BaseUserCell a;

    y(BaseUserCell baseUserCell) {
        this.a = baseUserCell;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser != null) {
            LocalBroadcastManager.getInstance(QsbkApp.getInstance()).sendBroadcast(new Intent(Constants.ACTION_CIRCLE_LIKE));
        }
        ToastAndDialog.scale(this.a.likeCountBt, true);
        CircleArticle circleArticle = (CircleArticle) this.a.getItem();
        circleArticle.likeCount++;
        this.a.likeCountView.setAnimationDuration((long) this.a.getContext().getResources().getInteger(R.integer.vote_duration));
        this.a.likeCountView.setText(String.valueOf(circleArticle.likeCount), true);
        circleArticle.liked = true;
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.CIRCLE_ARTICLE_LIKE, new Object[]{circleArticle.id}), new z(this, circleArticle, view));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.execute();
        CircleArticleBus.updateArticle(circleArticle, null);
    }
}
