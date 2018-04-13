package cn.tatagou.sdk.fragment;

import android.util.Log;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.pojo.CommListPojo;
import cn.tatagou.sdk.pojo.FeedbackType;
import java.util.Collection;

class FeedbackFragment$3 extends a<CommListPojo<FeedbackType>> {
    final /* synthetic */ FeedbackFragment a;

    FeedbackFragment$3(FeedbackFragment feedbackFragment) {
        this.a = feedbackFragment;
    }

    public void onApiDataResult(CommListPojo<FeedbackType> commListPojo, int i) {
        super.onApiDataResult(commListPojo, i);
        if (this.a.isAdded() && commListPojo != null) {
            Log.d(FeedbackFragment.e(), "onApiDataResult: feedbackTypeApiCallback");
            Collection data = commListPojo.getData();
            if (data != null && data.size() > 0) {
                this.a.a(data);
                if (FeedbackFragment.a(this.a).size() > 0) {
                    FeedbackFragment.a(this.a).clear();
                }
                FeedbackFragment.a(this.a).addAll(data);
                if (FeedbackFragment.b(this.a) && FeedbackFragment.a(this.a).size() > 0) {
                    FeedbackFragment.a(this.a, false);
                    FeedbackFragment.c(this.a);
                }
            }
        }
    }
}
