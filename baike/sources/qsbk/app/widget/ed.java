package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;
import qsbk.app.activity.QiuyouCircleLikedListActivity;
import qsbk.app.im.ChatMsg;
import qsbk.app.utils.UIHelper;

class ed implements OnClickListener {
    final /* synthetic */ QiuyouCircleNotificationItemView a;

    ed(QiuyouCircleNotificationItemView qiuyouCircleNotificationItemView) {
        this.a = qiuyouCircleNotificationItemView;
    }

    public void onClick(View view) {
        QiuyouCircleLikedListActivity.gotoQiuyouquanForward(view.getContext(), QiuyouCircleNotificationItemView.a(this.a));
        QiuyouCircleNotificationItemView.b(this.a).setVisibility(8);
        ((ChatMsg) QiuyouCircleNotificationItemView.d(this.a).get(QiuyouCircleNotificationItemView.c(this.a))).totalLikeNum = 0;
        QiuyouCircleNotificationItemView.a(this.a, QiuyouCircleNotificationItemView.e(this.a), 0);
        QiuyouCircleNotificationItemView.e(this.a).setImageResource(UIHelper.isNightTheme() ? R.drawable.group_info_next_night : R.drawable.group_info_next);
    }
}
