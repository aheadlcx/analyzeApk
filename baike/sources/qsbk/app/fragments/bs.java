package qsbk.app.fragments;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.ToastAndDialog;

class bs implements OnClickListener {
    final /* synthetic */ CircleTopic a;
    final /* synthetic */ CircleTopicsFragment$a b;

    bs(CircleTopicsFragment$a circleTopicsFragment$a, CircleTopic circleTopic) {
        this.b = circleTopicsFragment$a;
        this.a = circleTopic;
    }

    public void onClick(View view) {
        if (!CircleTopicsFragment.n(this.b.c)) {
            CircleTopicActivity.launch(this.b.c.getActivity(), this.a, -1, false);
        } else if (this.a.canPublishArticle()) {
            Intent intent = new Intent();
            intent.putExtra("topic", this.a);
            this.b.c.getActivity().setResult(-1, intent);
            this.b.c.getActivity().finish();
        } else {
            ToastAndDialog.makeNegativeToast(this.b.c.getActivity(), "该话题暂时不支持发动态哦").show();
        }
    }
}
