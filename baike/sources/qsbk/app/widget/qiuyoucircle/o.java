package qsbk.app.widget.qiuyoucircle;

import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.model.CircleArticle;
import qsbk.app.widget.TouchResponseProgressBar.OnProgressListener;

class o implements OnProgressListener {
    final /* synthetic */ CircleArticle a;
    final /* synthetic */ BaseUserCell b;

    o(BaseUserCell baseUserCell, CircleArticle circleArticle) {
        this.b = baseUserCell;
        this.a = circleArticle;
    }

    public boolean onProgress(int i) {
        if (QsbkApp.currentUser == null) {
            ActionBarLoginActivity.launch(QsbkApp.getInstance());
            return false;
        }
        if (i == this.b.addFriendProgressBar.getMax()) {
            this.b.a(0, 0, this.a.user.userId);
            this.b.addFriendProgressBar.setProgress(0);
            this.b.addFriendProgressBar.setEnabled(false);
        }
        return true;
    }
}
