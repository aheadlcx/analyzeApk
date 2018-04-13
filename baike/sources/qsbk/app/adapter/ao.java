package qsbk.app.adapter;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.CircleComment;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.ToastAndDialog;

class ao implements OnClickListener {
    final /* synthetic */ CircleComment a;
    final /* synthetic */ CircleCommentAdapter b;

    ao(CircleCommentAdapter circleCommentAdapter, CircleComment circleComment) {
        this.b = circleCommentAdapter;
        this.a = circleComment;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser == null) {
            this.b.k.startActivity(new Intent(this.b.k, ActionBarLoginActivity.class));
            return;
        }
        if (!(this.b.p == null || this.b.p.topic == null)) {
            CircleTopicManager.getInstance().insertTopicToLRU(this.b.p.topic);
        }
        CircleComment circleComment = this.a;
        circleComment.likeCount++;
        this.a.setLiked(true);
        this.b.e.put(this.a.id, Boolean.TRUE);
        view.setEnabled(false);
        ((TextView) view).setText(String.valueOf(this.a.likeCount));
        if (!this.a.id.equals("-1")) {
            ToastAndDialog.scale(view, true);
            String format = String.format(Constants.CIRCLE_COMMENT_LIKE, new Object[]{this.a.id});
            Map hashMap = new HashMap();
            hashMap.put("article_id", Integer.valueOf(Integer.parseInt(this.b.q)));
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new ap(this));
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.execute();
        }
    }
}
