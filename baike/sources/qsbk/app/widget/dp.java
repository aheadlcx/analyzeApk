package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;
import qsbk.app.activity.QiuyouCircleLikedListActivity;
import qsbk.app.im.ChatMsg;
import qsbk.app.utils.UIHelper;

class dp implements OnClickListener {
    final /* synthetic */ QiushiNotificationItemView a;

    dp(QiushiNotificationItemView qiushiNotificationItemView) {
        this.a = qiushiNotificationItemView;
    }

    public void onClick(View view) {
        QiuyouCircleLikedListActivity.gotoQiushi(view.getContext(), QiushiNotificationItemView.a(this.a), QiushiNotificationItemView.b(this.a));
        QiushiNotificationItemView.c(this.a).setVisibility(8);
        ((ChatMsg) QiushiNotificationItemView.e(this.a).get(QiushiNotificationItemView.d(this.a))).totalLikeNum = 0;
        QiushiNotificationItemView.a(this.a, QiushiNotificationItemView.f(this.a), 0);
        QiushiNotificationItemView.f(this.a).setImageResource(UIHelper.isNightTheme() ? R.drawable.group_info_next_night : R.drawable.group_info_next);
    }
}
