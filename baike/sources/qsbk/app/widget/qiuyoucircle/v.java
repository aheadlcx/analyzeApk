package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.model.CircleArticle;

class v implements OnClickListener {
    final /* synthetic */ BaseUserCell a;

    v(BaseUserCell baseUserCell) {
        this.a = baseUserCell;
    }

    public void onClick(View view) {
        CircleArticle circleArticle = (CircleArticle) this.a.getItem();
        if (!circleArticle.user.isAnonymous()) {
            if (QsbkApp.currentUser == null) {
                MyInfoActivity.launch(view.getContext(), circleArticle.user.userId, true);
            } else if (QsbkApp.currentUser.userId.equals(circleArticle.user.userId)) {
                MyInfoActivity.launch(view.getContext());
            } else {
                MyInfoActivity.launch(view.getContext(), circleArticle.user.userId, MyInfoActivity.FANS_ORIGINS[1], new IMChatMsgSource(8, circleArticle.user.userId, "来自糗友圈"));
            }
        }
    }
}
