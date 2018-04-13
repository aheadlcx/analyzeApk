package cn.tatagou.sdk.fragment;

import cn.tatagou.sdk.fragment.DialogFeedbackFragment.b;
import cn.tatagou.sdk.pojo.FeedbackType;

class FeedbackFragment$9 implements b {
    final /* synthetic */ FeedbackFragment a;

    FeedbackFragment$9(FeedbackFragment feedbackFragment) {
        this.a = feedbackFragment;
    }

    public void onClick(FeedbackType feedbackType) {
        if (feedbackType != null) {
            FeedbackFragment.k(this.a).setText(feedbackType.getType());
            FeedbackFragment.a(this.a, feedbackType);
        }
    }
}
