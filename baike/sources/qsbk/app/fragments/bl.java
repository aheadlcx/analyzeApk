package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.ToastAndDialog;

class bl implements OnClickListener {
    final /* synthetic */ CircleTopicsFragment a;

    bl(CircleTopicsFragment circleTopicsFragment) {
        this.a = circleTopicsFragment;
    }

    public void onClick(View view) {
        if (CircleTopicsFragment.n(this.a)) {
            ToastAndDialog.makeNegativeToast(this.a.getActivity(), "该话题暂时不支持发动态哦").show();
        } else {
            CircleTopicActivity.launch(this.a.getActivity(), CircleTopic.BLACK_ROOM_ID);
        }
    }
}
