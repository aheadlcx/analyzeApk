package qsbk.app.fragments;

import android.content.Context;
import android.content.Intent;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.publish.CirclePublishActivity;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

class be extends ProgressDialogCallBack {
    final /* synthetic */ CircleTopicsFragment$OtherItem a;
    final /* synthetic */ CircleTopicsFragment b;

    be(CircleTopicsFragment circleTopicsFragment, Context context, String str, CircleTopicsFragment$OtherItem circleTopicsFragment$OtherItem) {
        this.b = circleTopicsFragment;
        this.a = circleTopicsFragment$OtherItem;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        if (this.b.getActivity() != null) {
            if (jSONObject.optInt("rank") >= 9) {
                CircleTopic circleTopic = new CircleTopic();
                circleTopic.id = "0";
                circleTopic.content = this.a.msg;
                if (!CircleTopicsFragment.n(this.b)) {
                    if (circleTopic.content.contains("打卡")) {
                        circleTopic.status = 6;
                    }
                    CirclePublishActivity.launch(this.b.getActivity(), circleTopic);
                    CircleTopicsFragment.a(this.b, "");
                    CircleTopicsFragment.a(this.b, false);
                    CircleTopicsFragment.e(this.b).setText("");
                    CircleTopicsFragment.d(this.b).setLoadMoreEnable(CircleTopicsFragment.h(this.b));
                    CircleTopicsFragment.i(this.b);
                    return;
                } else if (circleTopic.canPublishArticle()) {
                    Intent intent = new Intent();
                    intent.putExtra("topic", circleTopic);
                    if (circleTopic.content.contains("打卡")) {
                        circleTopic.status = 6;
                        if (CircleTopicsFragment.k(this.b)) {
                            ToastAndDialog.makeNegativeToast(this.b.getActivity(), "不可以添加打卡话题哦～").show();
                            return;
                        }
                    }
                    this.b.getActivity().setResult(-1, intent);
                    this.b.getActivity().finish();
                    return;
                } else {
                    ToastAndDialog.makeNegativeToast(this.b.getActivity(), "该话题暂时不支持发动态哦").show();
                    this.b.getActivity().finish();
                    return;
                }
            }
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "圈等级LV9以上才能创建话题哦", Integer.valueOf(0)).show();
        }
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
