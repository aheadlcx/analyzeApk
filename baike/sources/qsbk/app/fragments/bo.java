package qsbk.app.fragments;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.ToastAndDialog;

class bo implements OnClickListener {
    final /* synthetic */ b a;
    final /* synthetic */ CircleTopicsFragment$CircleTopicAdapter b;

    bo(CircleTopicsFragment$CircleTopicAdapter circleTopicsFragment$CircleTopicAdapter, b bVar) {
        this.b = circleTopicsFragment$CircleTopicAdapter;
        this.a = bVar;
    }

    public void onClick(View view) {
        int itemViewType = this.b.getItemViewType(this.a.a);
        if (itemViewType == 0) {
            CircleTopic circleTopic = (CircleTopic) this.b.getItem(this.a.a);
            if (!CircleTopicsFragment.n(this.b.a)) {
                CircleTopicActivity.launch(this.b.k, circleTopic, -1, false);
            } else if (!circleTopic.canPublishArticle()) {
                ToastAndDialog.makeNegativeToast(this.b.a.getActivity(), "该话题暂时不支持发动态哦").show();
            } else if (TextUtils.equals(circleTopic.id, CircleTopic.BLACK_ROOM_ID)) {
                ToastAndDialog.makeNegativeToast(this.b.a.getActivity(), "该话题暂时不支持发动态哦").show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("topic", circleTopic);
                this.b.k.setResult(-1, intent);
                this.b.k.finish();
            }
        } else if (itemViewType != 2) {
        } else {
            if (QsbkApp.currentUser == null) {
                LoginPermissionClickDelegate.startLoginActivity(this.b.a.getActivity());
                return;
            }
            CircleTopicsFragment.a(this.b.a, (CircleTopicsFragment$OtherItem) this.b.getItem(this.a.a));
        }
    }
}
