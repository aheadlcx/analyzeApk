package qsbk.app.fragments;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.ToastAndDialog;

class lg implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ TopicsTopFragment$CircleTopicAdapter b;

    lg(TopicsTopFragment$CircleTopicAdapter topicsTopFragment$CircleTopicAdapter, a aVar) {
        this.b = topicsTopFragment$CircleTopicAdapter;
        this.a = aVar;
    }

    public void onClick(View view) {
        int itemViewType = this.b.getItemViewType(this.a.a);
        if (itemViewType == 0) {
            CircleTopic circleTopic = (CircleTopic) this.b.getItem(this.a.a);
            if (!this.b.c) {
                CircleTopicActivity.launch(this.b.k, circleTopic, -1, false);
            } else if (circleTopic.canPublishArticle()) {
                Intent intent = new Intent();
                intent.putExtra("topic", circleTopic);
                this.b.k.setResult(-1, intent);
                this.b.k.finish();
            } else {
                ToastAndDialog.makeNegativeToast(this.b.a.getActivity(), "该话题暂时不支持发动态哦").show();
                this.b.k.finish();
            }
        } else if (itemViewType != 2) {
        } else {
            if (QsbkApp.currentUser == null) {
                LoginPermissionClickDelegate.startLoginActivity(this.b.a.getActivity());
                return;
            }
            TopicsTopFragment.a(this.b.a, (TopicsTopFragment$OtherItem) this.b.getItem(this.a.a));
        }
    }
}
