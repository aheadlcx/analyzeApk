package com.budejie.www.activity.labelsubscription;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.busevent.LabelEvent;
import com.budejie.www.busevent.LabelEvent.LabelAction;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.EventBus;
import net.tsz.afinal.a.a;

class d$3 extends a<String> {
    final /* synthetic */ RecommendSubscription a;
    final /* synthetic */ d b;

    d$3(d dVar, RecommendSubscription recommendSubscription) {
        this.b = dVar;
        this.a = recommendSubscription;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        try {
            ResultBean s = z.s(str);
            if (s != null) {
                Object msg = s.getMsg();
                CharSequence code = s.getCode();
                if (TextUtils.isEmpty(msg)) {
                    d.a(this.b, an.a(d.a(this.b), d.a(this.b).getString(R.string.operate_fail), -1));
                } else {
                    d.a(this.b, an.a(d.a(this.b), msg, -1));
                }
                if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                    this.a.setIs_sub("1");
                    if (this.a.getSub_number().matches("[0-9]+")) {
                        this.a.setSub_number((Integer.parseInt(this.a.getSub_number()) + 1) + "");
                    }
                    MobclickAgent.onEvent(d.a(this.b), "推荐列表订阅按钮点击", "推荐列表订阅按钮点击");
                    EventBus.getDefault().post(new LabelEvent(LabelAction.SUBSCRIBE, this.a));
                    final RecommendSubscription recommendSubscription = (RecommendSubscription) this.a.clone();
                    recommendSubscription.setType("s");
                    this.b.notifyDataSetChanged();
                    new AsyncTask(this) {
                        final /* synthetic */ d$3 b;

                        protected Object doInBackground(Object[] objArr) {
                            d.b(this.b.b).e("recommend_Label", this.b.a.getTheme_id());
                            d.b(this.b.b).a("subscribe_Label", recommendSubscription);
                            return null;
                        }
                    }.execute(new Object[0]);
                }
            } else {
                d.a(this.b, an.a(d.a(this.b), d.a(this.b).getString(R.string.operate_fail), -1));
            }
            if (d.c(this.b) != null) {
                d.c(this.b).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
