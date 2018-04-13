package cn.tatagou.sdk.fragment;

import android.util.Log;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.pojo.CommPojo;
import cn.tatagou.sdk.pojo.FeedbackData;

class FeedbackFragment$6 extends a<CommPojo<FeedbackData>> {
    final /* synthetic */ FeedbackFragment a;

    FeedbackFragment$6(FeedbackFragment feedbackFragment) {
        this.a = feedbackFragment;
    }

    public void onApiDataResult(CommPojo<FeedbackData> commPojo, int i) {
        super.onApiDataResult(commPojo, i);
        if (this.a.isAdded()) {
            Log.d(FeedbackFragment.e(), "onApiDataResult: feedbackApiCallback");
            if (commPojo != null) {
                FeedbackFragment.a(this.a, (FeedbackData) commPojo.getData());
            } else {
                FeedbackFragment.a(this.a, this.a.onDataError(i, true), true);
            }
        }
    }
}
