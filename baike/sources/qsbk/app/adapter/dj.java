package qsbk.app.adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.Comment;
import qsbk.app.utils.ToastAndDialog;

class dj implements OnClickListener {
    final /* synthetic */ Comment a;
    final /* synthetic */ SingleArticleAdapter b;

    dj(SingleArticleAdapter singleArticleAdapter, Comment comment) {
        this.b = singleArticleAdapter;
        this.a = comment;
    }

    public void onClick(View view) {
        if (QsbkApp.currentUser == null) {
            this.b.k.startActivity(new Intent(this.b.k, ActionBarLoginActivity.class));
            return;
        }
        Comment comment = this.a;
        comment.likeCount++;
        this.a.setLiked(true);
        this.b.d.put(this.a.id, Boolean.TRUE);
        view.setEnabled(false);
        ((TextView) view).setText(String.valueOf(this.a.likeCount));
        if (!TextUtils.equals("-1", this.a.id)) {
            ToastAndDialog.scale(view, true);
            String format = String.format(Constants.LIKE_COMMENT, new Object[]{this.b.j});
            Map hashMap = new HashMap();
            hashMap.put("comment_id", this.a.id);
            SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new dk(this));
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.execute();
        }
    }
}
