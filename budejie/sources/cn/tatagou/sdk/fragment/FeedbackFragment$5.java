package cn.tatagou.sdk.fragment;

import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.a.a;
import cn.tatagou.sdk.adapter.b;
import cn.tatagou.sdk.pojo.CommPojo;
import cn.tatagou.sdk.pojo.Feedback;
import cn.tatagou.sdk.pojo.SendFeedback;
import cn.tatagou.sdk.util.f;
import cn.tatagou.sdk.util.l;
import cn.tatagou.sdk.util.p;
import com.alibaba.fastjson.JSON;

class FeedbackFragment$5 extends a<CommPojo<SendFeedback>> {
    final /* synthetic */ FeedbackFragment a;

    FeedbackFragment$5(FeedbackFragment feedbackFragment) {
        this.a = feedbackFragment;
    }

    public void onApiDataResult(CommPojo<SendFeedback> commPojo, int i) {
        super.onApiDataResult(commPojo, i);
        if (this.a.isAdded()) {
            Log.d(FeedbackFragment.e(), "onApiDataResult: sendFeedbackApiCallback");
            FeedbackFragment.d(this.a).setEnabled(true);
            if (commPojo == null) {
                l.showToastCenter(this.a.getActivity(), this.a.getString(R.string.commit_fail));
            } else if (i == 200 && commPojo.getData() != null) {
                Log.d(FeedbackFragment.e(), "toJSONString : " + JSON.toJSONString(commPojo));
                p.hideKeyboard(this.a.getActivity(), this.a.getView(), (InputMethodManager) this.a.getActivity().getSystemService("input_method"), FeedbackFragment.e(this.a));
                SendFeedback sendFeedback = (SendFeedback) commPojo.getData();
                Feedback feedback = new Feedback();
                feedback.setContent(FeedbackFragment.e(this.a).getText().toString());
                feedback.setType(FeedbackFragment.f(this.a));
                feedback.setCreateTime(p.isEmpty(sendFeedback.getCreateTime()) ? f.getNowTimeYMD("yyyy-MM-dd HH:mm:ss") : sendFeedback.getCreateTime());
                feedback.setId(p.isEmpty(sendFeedback.getId()) ? "0" : sendFeedback.getId());
                FeedbackFragment.g(this.a).add(0, feedback);
                FeedbackFragment.e(this.a).setText("");
                FeedbackFragment.h(this.a).setText("");
                if (FeedbackFragment.i(this.a) == null) {
                    FeedbackFragment.a(this.a, new b(this.a.getActivity(), FeedbackFragment.g(this.a)));
                    FeedbackFragment.j(this.a).setAdapter(FeedbackFragment.i(this.a));
                } else {
                    FeedbackFragment.i(this.a).setItems(FeedbackFragment.g(this.a));
                }
                FeedbackFragment.j(this.a).finishLoading(0);
                FeedbackFragment.j(this.a).setLoadDataFlag(true);
            } else if (p.isEmpty(commPojo.getCode()) && p.isEmpty(commPojo.getMessage())) {
                l.showToastCenter(this.a.getActivity(), commPojo.getCode() + " - " + commPojo.getMessage());
            } else {
                l.showToastCenter(this.a.getActivity(), this.a.getString(R.string.unkonw_error));
            }
        }
    }
}
