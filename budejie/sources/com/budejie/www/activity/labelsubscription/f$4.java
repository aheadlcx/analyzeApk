package com.budejie.www.activity.labelsubscription;

import android.os.AsyncTask;
import android.os.Message;
import android.text.TextUtils;
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

class f$4 extends a<String> {
    final /* synthetic */ RecommendSubscription a;
    final /* synthetic */ f b;

    f$4(f fVar, RecommendSubscription recommendSubscription) {
        this.b = fVar;
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
                    f.a(this.b, an.a(f.a(this.b), f.a(this.b).getString(R.string.operate_fail), -1));
                } else {
                    f.a(this.b, an.a(f.a(this.b), msg, -1));
                }
                if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                    Object obj;
                    if (this.a.getSub_number().matches("[0-9]+")) {
                        this.a.setSub_number((Integer.parseInt(this.a.getSub_number()) - 1) + "");
                    }
                    for (int size = f.b(this.b).size() - 1; size > 0; size--) {
                        if (this.a.getTheme_id().equals(((RecommendSubscription) f.b(this.b).get(size)).getTheme_id())) {
                            if ("r".equals(((RecommendSubscription) f.b(this.b).get(size)).getType())) {
                                this.a.setIs_sub("0");
                                ((RecommendSubscription) f.b(this.b).get(size)).setIs_sub("0");
                            } else {
                                f.b(this.b).remove(size);
                            }
                        }
                    }
                    Iterator it = f.b(this.b).iterator();
                    msg = null;
                    while (it.hasNext()) {
                        RecommendSubscription recommendSubscription = (RecommendSubscription) it.next();
                        if (!"r".equals(recommendSubscription.getType())) {
                            if (!"recomm_tv".equals(recommendSubscription.getType())) {
                                if (!"sub_tv".equals(recommendSubscription.getType())) {
                                    obj = 1;
                                    break;
                                }
                                RecommendSubscription recommendSubscription2 = recommendSubscription;
                            } else {
                                obj = null;
                                break;
                            }
                        }
                        break;
                    }
                    obj = null;
                    if (obj == null && msg != null) {
                        f.b(this.b).remove(msg);
                    }
                    EventBus.getDefault().post(new LabelEvent(LabelAction.UNSUBSCRIBE, this.a));
                    this.b.notifyDataSetChanged();
                    Message message = new Message();
                    message.what = 9976575;
                    message.obj = f.b(this.b);
                    f.c(this.b).sendMessage(message);
                    new AsyncTask(this) {
                        final /* synthetic */ f$4 a;

                        {
                            this.a = r1;
                        }

                        protected Object doInBackground(Object[] objArr) {
                            f.d(this.a.b).a("recommend_Label", this.a.a.getTheme_id(), this.a.a.getSub_number(), "0");
                            f.d(this.a.b).e("subscribe_Label", this.a.a.getTheme_id());
                            return null;
                        }
                    }.execute(new Object[0]);
                }
            } else {
                f.a(this.b, an.a(f.a(this.b), f.a(this.b).getString(R.string.operate_fail), -1));
            }
            if (f.e(this.b) != null) {
                f.e(this.b).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
