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

class e$3 extends a<String> {
    final /* synthetic */ RecommendSubscription a;
    final /* synthetic */ e b;

    e$3(e eVar, RecommendSubscription recommendSubscription) {
        this.b = eVar;
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
                    e.a(this.b, an.a(e.a(this.b), e.a(this.b).getString(R.string.operate_fail), -1));
                } else {
                    e.a(this.b, an.a(e.a(this.b), msg, -1));
                }
                if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                    this.a.setIs_sub("1");
                    if (this.a.getSub_number().matches("[0-9]+")) {
                        this.a.setSub_number((Integer.parseInt(this.a.getSub_number()) + 1) + "");
                    }
                    EventBus.getDefault().post(new LabelEvent(LabelAction.SUBSCRIBE, this.a));
                    this.b.notifyDataSetChanged();
                    new Thread(new Runnable(this) {
                        final /* synthetic */ e$3 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            e.b(this.a.b).a("recommend_Label", this.a.a.getTheme_id(), this.a.a.getSub_number(), "1");
                            e.b(this.a.b).a("subscribe_Label", this.a.a);
                        }
                    }).start();
                }
            } else {
                e.a(this.b, an.a(e.a(this.b), e.a(this.b).getString(R.string.operate_fail), -1));
            }
            if (e.c(this.b) != null) {
                e.c(this.b).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
