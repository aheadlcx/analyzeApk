package com.budejie.www.activity.labelsubscription;

import android.os.AsyncTask;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.budejie.www.R;
import com.budejie.www.bean.RecommendSubscription;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.busevent.LabelEvent;
import com.budejie.www.busevent.LabelEvent.LabelAction;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import de.greenrobot.event.EventBus;
import java.util.Iterator;
import net.tsz.afinal.a.a;

class f$3 extends a<String> {
    final /* synthetic */ RecommendSubscription a;
    final /* synthetic */ View b;
    final /* synthetic */ f c;

    f$3(f fVar, RecommendSubscription recommendSubscription, View view) {
        this.c = fVar;
        this.a = recommendSubscription;
        this.b = view;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        int i = 0;
        try {
            ResultBean s = z.s(str);
            if (s != null) {
                Object msg = s.getMsg();
                CharSequence code = s.getCode();
                if (TextUtils.isEmpty(msg)) {
                    f.a(this.c, an.a(f.a(this.c), f.a(this.c).getString(R.string.operate_fail), -1));
                } else {
                    f.a(this.c, an.a(f.a(this.c), msg, -1));
                }
                if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                    int i2;
                    if (this.a.getSub_number().matches("[0-9]+")) {
                        this.a.setSub_number((Integer.parseInt(this.a.getSub_number()) + 1) + "");
                    }
                    final RecommendSubscription recommendSubscription = (RecommendSubscription) this.a.clone();
                    Iterator it = f.b(this.c).iterator();
                    while (it.hasNext()) {
                        if ("sub_tv".equals(((RecommendSubscription) it.next()).getType())) {
                            i2 = 1;
                            break;
                        }
                    }
                    i2 = 0;
                    if (i2 == 0) {
                        RecommendSubscription recommendSubscription2 = new RecommendSubscription();
                        recommendSubscription2.setType("sub_tv");
                        f.b(this.c).add(0, recommendSubscription2);
                    }
                    while (i < f.b(this.c).size()) {
                        if ("sub_tv".equals(((RecommendSubscription) f.b(this.c).get(i)).getType())) {
                            recommendSubscription.setType("s");
                            f.b(this.c).add(i + 1, recommendSubscription);
                        } else if (this.a.getTheme_id().equals(((RecommendSubscription) f.b(this.c).get(i)).getTheme_id())) {
                            this.a.setIs_sub("1");
                            recommendSubscription.setIs_sub("1");
                        }
                        i++;
                    }
                    f.b(this.c).remove(this.a);
                    EventBus.getDefault().post(new LabelEvent(LabelAction.SUBSCRIBE, this.a, this.b));
                    this.c.notifyDataSetChanged();
                    Message message = new Message();
                    message.what = 434534545;
                    message.obj = f.b(this.c);
                    f.c(this.c).sendMessage(message);
                    new AsyncTask(this) {
                        final /* synthetic */ f$3 b;

                        protected Object doInBackground(Object[] objArr) {
                            f.d(this.b.c).e("recommend_Label", this.b.a.getTheme_id());
                            f.d(this.b.c).a("subscribe_Label", recommendSubscription);
                            return null;
                        }
                    }.execute(new Object[0]);
                }
            } else {
                f.a(this.c, an.a(f.a(this.c), f.a(this.c).getString(R.string.operate_fail), -1));
            }
            if (f.e(this.c) != null) {
                f.e(this.c).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
