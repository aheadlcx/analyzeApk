package com.budejie.www.activity.labelsubscription;

import android.text.TextUtils;
import com.budejie.www.R;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.busevent.LabelEvent;
import com.budejie.www.busevent.LabelEvent.LabelAction;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import de.greenrobot.event.EventBus;
import net.tsz.afinal.a.a;

class d$4 extends a<String> {
    final /* synthetic */ RecommendSubscription a;
    final /* synthetic */ d b;

    d$4(d dVar, RecommendSubscription recommendSubscription) {
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
                    this.a.setIs_sub("0");
                    if (this.a.getSub_number().matches("[0-9]+")) {
                        this.a.setSub_number((Integer.parseInt(this.a.getSub_number()) - 1) + "");
                    }
                    EventBus.getDefault().post(new LabelEvent(LabelAction.UNSUBSCRIBE, this.a));
                    this.b.notifyDataSetChanged();
                    new Thread(new Runnable(this) {
                        final /* synthetic */ d$4 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            d.b(this.a.b).a("recommend_Label", this.a.a.getTheme_id(), this.a.a.getSub_number(), "0");
                            d.b(this.a.b).e("subscribe_Label", this.a.a.getTheme_id());
                        }
                    }).start();
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
