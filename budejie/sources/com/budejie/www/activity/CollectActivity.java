package com.budejie.www.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.alipay.sdk.util.j;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.BudejieApplication.b;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.view.CustomListView;
import com.budejie.www.bean.ListInfo;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.ResultBean;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.bean.SyncCollectItem;
import com.budejie.www.c.d;
import com.budejie.www.c.g;
import com.budejie.www.c.h;
import com.budejie.www.c.m;
import com.budejie.www.f.a;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.f;
import com.budejie.www.http.n;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.am;
import com.budejie.www.util.an;
import com.budejie.www.util.as;
import com.budejie.www.util.au;
import com.budejie.www.util.ay;
import com.budejie.www.util.p;
import com.budejie.www.util.u;
import com.budejie.www.util.z;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.UiError;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.MobclickAgent;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

public class CollectActivity extends OauthWeiboBaseAct implements OnClickListener, b, a {
    private Toast A;
    private Toast B;
    private View C;
    private boolean D = false;
    private g E;
    private RelativeLayout F;
    private RelativeLayout G;
    private com.budejie.www.http.b H;
    private boolean I = true;
    private Dialog J;
    private ImageView K;
    private AnimationDrawable L = null;
    private BudejieApplication M;
    private ListItemObject N;
    private f O;
    private long P = 0;
    private List<ListItemObject> Q;
    private Handler R = new Handler(this) {
        final /* synthetic */ CollectActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 4) {
                this.a.N.setLove(this.a.N.getLove() + 1);
                this.a.r.cancel();
            } else if (i == 5) {
                r0 = (String) message.obj;
                this.a.r = ProgressDialog.show(this.a.b, "", r0, true, true);
            } else if (i == 8) {
                this.a.A = an.a(this.a.b, this.a.b.getString(R.string.already_ding), -1);
                this.a.A.show();
            } else if (i == 9) {
                r0 = (Integer.parseInt(this.a.N.getRepost()) + 1) + "";
                this.a.N.setRepost(r0);
                this.a.j.a(this.a.N.getWid(), r0);
            } else if (i == 11) {
                CharSequence b = ai.b(this.a);
                if (an.j(this.a) && an.k(this.a) && !b.equals("")) {
                    an.a(this.a, false);
                    sendEmptyMessage(13);
                } else {
                    an.a(this.a, (int) R.string.collected, (int) R.drawable.collect_tip).show();
                }
                if (!TextUtils.isEmpty(b)) {
                    this.a.H.a("add", ai.b(this.a), (String) message.obj, 971);
                }
            } else if (i == 13) {
                this.a.B = an.a(this.a.b, (int) R.string.liked, (int) R.drawable.love_tip);
                this.a.B.show();
            } else if (i == 829) {
                r0 = (String) message.obj;
                this.a.g.a("collectTable", r0);
                this.a.a(r0);
                this.a.A = an.a(this.a.b, this.a.b.getString(R.string.delete_success), -1);
                this.a.A.show();
            } else if (i == 1001) {
                HashMap k = z.k((String) message.obj);
                if (k != null) {
                    r0 = (String) k.get("result_desc");
                    if (TextUtils.isEmpty(r0)) {
                        this.a.A = an.a(this.a.b, this.a.b.getString(R.string.operate_fail), -1);
                    } else {
                        this.a.A = an.a(this.a.b, r0, -1);
                    }
                } else {
                    this.a.A = an.a(this.a.b, this.a.b.getString(R.string.operate_fail), -1);
                }
                if (this.a.A != null) {
                    this.a.A.show();
                }
            } else if (i == 1006) {
                aa.b(this.a.a, "ch 取消关注");
                final SuggestedFollowsListItem suggestedFollowsListItem = new SuggestedFollowsListItem();
                final ListItemObject listItemObject = (ListItemObject) message.obj;
                suggestedFollowsListItem.uid = ((ListItemObject) message.obj).getUid();
                u.a(suggestedFollowsListItem, this.a, new net.tsz.afinal.a.a<String>(this) {
                    final /* synthetic */ AnonymousClass2 c;

                    public /* synthetic */ void onSuccess(Object obj) {
                        a((String) obj);
                    }

                    public void a(String str) {
                        try {
                            Toast a;
                            ResultBean s = z.s(str);
                            if (s != null) {
                                String msg = s.getMsg();
                                CharSequence code = s.getCode();
                                if (TextUtils.isEmpty(msg)) {
                                    a = an.a(this.c.a, this.c.a.getString(R.string.operate_fail), -1);
                                } else {
                                    a = an.a(this.c.a, msg, -1);
                                }
                                if (!TextUtils.isEmpty(code) && "0".equals(code)) {
                                    if (listItemObject != null) {
                                        listItemObject.setShwFollow(true);
                                    }
                                    suggestedFollowsListItem.is_follow = 0;
                                    this.c.a.E.a(suggestedFollowsListItem.uid);
                                    as.b().a(suggestedFollowsListItem.uid, Integer.valueOf(0));
                                    this.c.a.z.notifyDataSetChanged();
                                    a = an.a(this.c.a, this.c.a.getString(R.string.had_cancel_attention), -1);
                                }
                            } else {
                                a = an.a(this.c.a, this.c.a.getString(R.string.operate_fail), -1);
                            }
                            if (a != null) {
                                a.show();
                            }
                        } catch (Exception e) {
                            if (!TextUtils.isEmpty(e.getMessage())) {
                                aa.e(this.c.a.a, e.getMessage());
                            }
                        }
                    }
                });
            }
        }
    };
    private com.budejie.www.adapter.e.a S = new com.budejie.www.adapter.e.a(this) {
        final /* synthetic */ CollectActivity a;

        {
            this.a = r1;
        }

        public void a(View view, ListItemObject listItemObject) {
            TipPopUp.a(this.a, TypeControl.dingtie);
            this.a.O.a("ding", this.a.s, listItemObject);
            this.a.O.a(listItemObject, this.a.s, "ding");
        }

        public void b(View view, ListItemObject listItemObject) {
            this.a.O.a("cai", this.a.s, listItemObject);
            this.a.O.a(listItemObject, this.a.s, "cai");
        }

        public void a(View view, ListItemObject listItemObject, String str) {
            Bundle bundle = new Bundle();
            bundle.putString(PersonalProfileActivity.c, listItemObject.getUid());
            bundle.putString(PersonalProfileActivity.d, str);
            this.a.p.a(7, bundle).onClick(view);
        }

        public void c(View view, ListItemObject listItemObject) {
            p.a(this.a, listItemObject, this.a.R);
        }

        public void a(View view, ListItemObject listItemObject, int i) {
            this.a.N = listItemObject;
            Bundle bundle = new Bundle();
            bundle.putInt("position", i);
            bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this.a));
            bundle.putSerializable("weiboMap", this.a.d);
            bundle.putSerializable("data", listItemObject);
            view.setTag(listItemObject);
            this.a.p.a(5, bundle, this.a.R, this.a.l, this.a.i, this.a.k, this.a.o, this.a.f, this.a.s).onClick(view);
        }

        public void d(View view, ListItemObject listItemObject) {
            view.setTag(listItemObject);
            this.a.p.a(3, null).onClick(view);
        }

        public void e(View view, ListItemObject listItemObject) {
            view.setTag(listItemObject);
            this.a.p.a(3, null).onClick(view);
        }

        public void a() {
        }

        public void a_(String str) {
        }

        public OnClickListener b() {
            return null;
        }

        public void a(SuggestedFollowsListItem suggestedFollowsListItem) {
        }

        public void a(ListItemObject listItemObject, int i) {
            Intent intent = new Intent(this.a.b, CommonLabelActivity.class);
            PlateBean plateBean = listItemObject.getPlateBean(i);
            if (plateBean != null) {
                intent.putExtra("theme_name", plateBean.theme_name);
                intent.putExtra("theme_id", plateBean.theme_id);
                this.a.b.startActivity(intent);
            }
        }
    };
    private CustomListView.b T = new CustomListView.b(this) {
        final /* synthetic */ CollectActivity a;

        {
            this.a = r1;
        }

        public void a() {
            if (an.a(this.a.b) && !this.a.D) {
                if (this.a.C != null) {
                    ((TextView) this.a.C.findViewById(R.id.message_list_more_tv)).setText(R.string.more_info);
                    this.a.C.setVisibility(0);
                }
                this.a.e();
            }
        }
    };
    private OnClickListener U = new OnClickListener(this) {
        final /* synthetic */ CollectActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.mycomment_delete_cancelBtn) {
                this.a.J.dismiss();
            } else if (view.getId() == R.id.mycomment_delete_sureBtn) {
                this.a.J.dismiss();
                ArrayList a = this.a.j.a();
                if (a != null && !a.isEmpty()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < a.size(); i++) {
                        if (i == a.size() - 1) {
                            stringBuffer.append((String) a.get(i));
                        } else {
                            stringBuffer.append((String) a.get(i)).append(",");
                        }
                    }
                    this.a.H.a("add", this.a.c, stringBuffer.toString(), 971);
                }
            }
        }
    };
    String a = "CollectActivity";
    CollectActivity b;
    String c;
    HashMap<String, String> d;
    boolean e = false;
    SharedPreferences f;
    com.budejie.www.c.b g;
    h h;
    m i;
    d j;
    n k;
    IWXAPI l;
    ay m;
    Dialog n;
    com.elves.update.a o;
    com.budejie.www.g.b p;
    Bundle q = new Bundle();
    ProgressDialog r;
    Handler s = new Handler(this) {
        final /* synthetic */ CollectActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (!this.a.isFinishing()) {
                int parseInt;
                int i = message.what;
                if (i == 814) {
                    this.a.n.show();
                } else if (i == 815) {
                    this.a.n.cancel();
                } else if (i == 813) {
                    r0 = (String) message.obj;
                    if (TextUtils.isEmpty(r0)) {
                        this.a.A = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                        this.a.A.show();
                        MobclickAgent.onEvent(this.a.b, "weibo_bind", "tencent_faild");
                    } else {
                        try {
                            parseInt = Integer.parseInt(r0);
                        } catch (NumberFormatException e) {
                            parseInt = 0;
                        }
                        if (parseInt < 0) {
                            this.a.A = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                            this.a.A.show();
                            MobclickAgent.onEvent(this.a.b, "weibo_bind", "tencent_faild");
                        } else {
                            r2 = z.c(r0);
                            if (r2 == null || r2.isEmpty()) {
                                this.a.A = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                                this.a.A.show();
                                MobclickAgent.onEvent(this.a.b, "weibo_bind", "tencent_faild");
                            } else {
                                r1 = (String) r2.get("result_msg");
                                if ("0".equals((String) r2.get(j.c))) {
                                    MobclickAgent.onEvent(this.a.b, "weibo_bind", "tencent_success");
                                    this.a.c = (String) r2.get("id");
                                    this.a.i.a(this.a.c, r2);
                                    ai.a(this.a.b, this.a.c, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                    this.a.d = this.a.k.a(this.a.c);
                                    this.a.A = an.a(this.a.b, this.a.b.getString(R.string.bind_successed), -1);
                                    this.a.A.show();
                                    r0 = this.a.f.getInt("position", -1);
                                    if (-1 != r0) {
                                        this.a.k.a(this.a.b, (ListItemObject) this.a.Q.get(r0), "qq", this.a.c, this.a.d, this.a.o, (Handler) this);
                                    }
                                    this.a.c();
                                    if (this.a.g()) {
                                        this.a.J.show();
                                    }
                                } else {
                                    an.a(this.a.b, r1, -1).show();
                                }
                            }
                        }
                    }
                    this.a.s.sendEmptyMessage(815);
                } else if (i == 812) {
                    r0 = (String) message.obj;
                    if (TextUtils.isEmpty(r0)) {
                        this.a.A = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                        this.a.A.show();
                        MobclickAgent.onEvent(this.a.b, "weibo_bind", "sina_faild");
                    } else {
                        try {
                            parseInt = Integer.parseInt(r0);
                        } catch (NumberFormatException e2) {
                            parseInt = 0;
                        }
                        if (parseInt < 0) {
                            this.a.A = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                            this.a.A.show();
                            MobclickAgent.onEvent(this.a.b, "weibo_bind", "sina_faild");
                        } else {
                            r2 = z.c(r0);
                            if (r2 == null || r2.isEmpty()) {
                                this.a.A = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                                this.a.A.show();
                                MobclickAgent.onEvent(this.a.b, "weibo_bind", "sina_faild");
                            } else {
                                r1 = (String) r2.get("result_msg");
                                if ("0".equals((String) r2.get(j.c))) {
                                    MobclickAgent.onEvent(this.a.b, "weibo_bind", "sina_success");
                                    this.a.c = (String) r2.get("id");
                                    this.a.i.a(this.a.c, r2);
                                    ai.a(this.a.b, this.a.c, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                    if (OauthWeiboBaseAct.mAccessToken != null) {
                                        this.a.i.a(this.a.c, OauthWeiboBaseAct.mAccessToken.e());
                                    }
                                    this.a.d = this.a.k.a(this.a.c);
                                    this.a.A = an.a(this.a.b, this.a.b.getString(R.string.bind_successed), -1);
                                    this.a.A.show();
                                    r0 = this.a.f.getInt("position", -1);
                                    if (-1 != r0) {
                                        this.a.k.a(this.a.b, (ListItemObject) this.a.Q.get(r0), "sina", this.a.c, this.a.d, this.a.o, (Handler) this);
                                    }
                                    this.a.c();
                                    if (this.a.g()) {
                                        this.a.J.show();
                                    }
                                } else {
                                    an.a(this.a.b, r1, -1).show();
                                }
                            }
                        }
                    }
                    this.a.s.sendEmptyMessage(815);
                } else if (i == 929) {
                    r0 = (String) message.obj;
                    if (TextUtils.isEmpty(r0)) {
                        this.a.A = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                        this.a.A.show();
                        MobclickAgent.onEvent(this.a.b, "weibo_bind", "qzone_faild");
                    } else {
                        try {
                            parseInt = Integer.parseInt(r0);
                        } catch (NumberFormatException e3) {
                            parseInt = 0;
                        }
                        if (parseInt < 0) {
                            this.a.A = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                            this.a.A.show();
                            MobclickAgent.onEvent(this.a.b, "weibo_bind", "qzone_faild");
                        } else {
                            r2 = z.c(r0);
                            if (r2 == null || r2.isEmpty()) {
                                this.a.A = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                                this.a.A.show();
                                MobclickAgent.onEvent(this.a.b, "weibo_bind", "qzone_faild");
                            } else {
                                r1 = (String) r2.get("result_msg");
                                if ("0".equals((String) r2.get(j.c))) {
                                    MobclickAgent.onEvent(this.a.b, "weibo_bind", "qzone_success");
                                    this.a.c = (String) r2.get("id");
                                    this.a.i.a(this.a.c, r2);
                                    ai.a(this.a.b, this.a.c, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                    this.a.d = this.a.k.a(this.a.c);
                                    this.a.A = an.a(this.a.b, this.a.b.getString(R.string.bind_successed), -1);
                                    this.a.A.show();
                                    r0 = this.a.f.getInt("position", -1);
                                    if (-1 != r0) {
                                        this.a.k.a(this.a.b, (ListItemObject) this.a.Q.get(r0), com.tencent.connect.common.Constants.SOURCE_QZONE, this.a.c, this.a.d, this.a.o, (Handler) this);
                                    }
                                    this.a.c();
                                    if (this.a.g()) {
                                        this.a.J.show();
                                    }
                                } else {
                                    an.a(this.a.b, r1, -1).show();
                                }
                            }
                        }
                    }
                    this.a.s.sendEmptyMessage(815);
                } else if (i == 816) {
                    Bundle bundle = (Bundle) message.obj;
                    CharSequence string = bundle.getString(j.c);
                    r0 = bundle.getInt("notificationId");
                    if (TextUtils.isEmpty(string)) {
                        this.a.o.a(r0, false, (int) R.string.forwarfail);
                    } else if ("0".equals(string)) {
                        this.a.o.a(r0, true, (int) R.string.forwardsuccess);
                    } else {
                        this.a.o.a(r0, false, (int) R.string.forwarfail);
                    }
                    new Thread(this) {
                        final /* synthetic */ AnonymousClass4 b;

                        public void run() {
                            try {
                                AnonymousClass1.sleep(1000);
                                this.b.a.s.sendMessage(this.b.a.s.obtainMessage(817, Integer.valueOf(r0)));
                            } catch (InterruptedException e) {
                            }
                        }
                    }.start();
                } else if (i == 817) {
                    this.a.o.a(((Integer) message.obj).intValue());
                }
                if (i == 979) {
                    if (this.a.I) {
                        this.a.A = an.a(this.a.b, this.a.b.getString(R.string.sync_collect_time_out), -1);
                        this.a.A.show();
                    }
                } else if (i == 972) {
                    if (this.a.r != null && this.a.r.isShowing()) {
                        this.a.r.cancel();
                    }
                    SyncCollectItem n = z.n((String) message.obj);
                    if ("1".equals(n.getResult())) {
                        ArrayList successIds = n.getSuccessIds();
                        if (!successIds.isEmpty()) {
                            for (parseInt = 0; parseInt < successIds.size(); parseInt++) {
                                this.a.j.b(n.getUid(), (String) successIds.get(parseInt));
                            }
                            this.a.P = 0;
                            this.a.d();
                        }
                    }
                }
            }
        }
    };
    net.tsz.afinal.a.a<String> t = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CollectActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void a(String str) {
            Observable.just(str).map(new Function<String, List<ListItemObject>>(this) {
                final /* synthetic */ AnonymousClass7 a;

                {
                    this.a = r1;
                }

                public /* synthetic */ Object apply(Object obj) throws Exception {
                    return a((String) obj);
                }

                public List<ListItemObject> a(String str) {
                    ListInfo a = com.budejie.www.j.a.a(str);
                    if (a == null) {
                        return null;
                    }
                    List<ListItemObject> a2 = com.budejie.www.j.a.a(this.a.a.b, str);
                    if (this.a.a.Q == null) {
                        this.a.a.Q = new ArrayList();
                    }
                    if (this.a.a.P == 0) {
                        this.a.a.Q.clear();
                        this.a.a.Q.addAll(a2);
                    } else {
                        this.a.a.Q.addAll(a2);
                    }
                    this.a.a.P = a.np;
                    return a2;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<ListItemObject>>(this) {
                final /* synthetic */ AnonymousClass7 a;
                private Disposable b;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((List) obj);
                }

                public void onSubscribe(@NonNull Disposable disposable) {
                    this.b = disposable;
                }

                public void onComplete() {
                    this.a.a.D = false;
                    this.b.dispose();
                    this.a.a.n.dismiss();
                }

                public void onError(Throwable th) {
                    this.a.a.D = false;
                    this.a.a.n.dismiss();
                    this.b.dispose();
                    if (!TextUtils.isEmpty(th.getMessage())) {
                        aa.e(this.a.a.a, th.getMessage());
                    }
                }

                public void a(List<ListItemObject> list) {
                    boolean z = true;
                    if (!this.a.a.isFinishing()) {
                        int i;
                        an.c(this.a.a.Q, this.a.a.h, this.a.a.j);
                        boolean z2 = !com.budejie.www.goddubbing.c.a.a(this.a.a.Q);
                        CustomListView a = this.a.a.y;
                        if (z2) {
                            i = 0;
                        } else {
                            i = 8;
                        }
                        a.setVisibility(i);
                        TextView t = this.a.a.u;
                        if (z2) {
                            i = 8;
                        } else {
                            i = 0;
                        }
                        t.setVisibility(i);
                        if (z2) {
                            an.a(this.a.a.Q, this.a.a.h, this.a.a.j, this.a.a.E);
                        }
                        if (this.a.a.z != null) {
                            this.a.a.z.a(this.a.a.Q);
                        } else {
                            this.a.a.z = new com.budejie.www.adapter.c.a(this.a.a, this.a.a.S);
                            this.a.a.z.a(this.a.a.Q);
                            this.a.a.y.setAdapter(this.a.a.z);
                        }
                        CustomListView a2 = this.a.a.y;
                        if (this.a.a.P != 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        a2.setmEnablePullLoad(z2);
                        CollectActivity collectActivity = this.a.a;
                        if (this.a.a.P == 0) {
                            z = false;
                        }
                        collectActivity.e = z;
                        if (this.a.a.C != null) {
                            this.a.a.C.setVisibility(8);
                        }
                        if (this.a.a.P == 0 && !com.budejie.www.goddubbing.c.a.a(this.a.a.Q)) {
                            this.a.a.A = an.a(this.a.a.b, this.a.a.b.getString(R.string.no_more_data), -1);
                            this.a.a.A.show();
                        }
                    }
                }
            });
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.n.cancel();
            if (this.a.I) {
                this.a.A = an.a(this.a.b, this.a.b.getString(R.string.time_out), -1);
                this.a.A.show();
            }
        }
    };
    private TextView u;
    private TextView v;
    private Button w;
    private LinearLayout x;
    private CustomListView y;
    private com.budejie.www.adapter.c.a z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.M = BudejieApplication.b();
        setContentView(R.layout.collectsister);
        com.budejie.www.widget.a.a((Activity) this);
        this.b = this;
        this.H = com.budejie.www.http.b.a(this, this);
        a();
        if (an.a((Context) this)) {
            b();
        }
        if (an.a((Context) this) && !TextUtils.isEmpty(ai.b(this.b))) {
            d();
        }
    }

    public void a() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.G = (RelativeLayout) findViewById(R.id.curtain_root_layout);
        this.u = (TextView) findViewById(R.id.empty_tips);
        this.w = (Button) findViewById(R.id.title_left_btn);
        this.x = (LinearLayout) findViewById(R.id.left_layout);
        this.v = (TextView) findViewById(R.id.title_center_txt);
        this.K = (ImageView) findViewById(R.id.melodyview);
        this.K.setOnClickListener(this);
        this.L = (AnimationDrawable) this.K.getBackground();
        this.y = (CustomListView) findViewById(R.id.listview_collect);
        this.y.setonLoadListener(this.T);
        this.y.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ CollectActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                k.a(this.a.b).a(absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition(), this.a.y.getHeaderViewsCount());
            }
        });
        this.C = this.y.getFootView();
        if (this.C != null) {
            this.C.setVisibility(8);
        }
        this.F = (RelativeLayout) findViewById(R.id.title);
        this.w.setVisibility(0);
        this.w.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.x.setVisibility(0);
        this.y.setVisibility(0);
        this.v.setText(R.string.collect_txt);
        this.O = new f(this);
        this.E = new g(this);
        this.h = new h(this);
        this.i = new m(this);
        this.j = new d(this);
        this.g = new com.budejie.www.c.b(this);
        this.k = new n(this);
        this.m = new ay();
        this.o = new com.elves.update.a(this);
        if (this.mSsoHandler == null) {
            this.mSsoHandler = new com.sina.weibo.sdk.auth.a.a(this);
        }
        this.p = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
        this.d = new HashMap();
        this.f = getSharedPreferences("weiboprefer", 0);
        this.n = new Dialog(this, R.style.dialogTheme);
        this.n.setContentView(R.layout.loaddialog);
        f();
    }

    private void b() {
        this.l = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.l.registerApp("wx592fdc48acfbe290");
    }

    public void onWbShareSuccess() {
        super.onWbShareSuccess();
        int a = am.a(this.N);
        if (!(this.N == null || this.j == null)) {
            this.j.a(this.N.getWid(), String.valueOf(a));
        }
        if (this.z != null) {
            this.z.notifyDataSetChanged();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 711) {
            this.s.sendEmptyMessage(814);
            bindTencent();
        }
    }

    private void c() {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ CollectActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.z != null) {
                    this.a.z.a(this.a.Q);
                    return;
                }
                this.a.z = new com.budejie.www.adapter.c.a(this.a, this.a.S);
                this.a.z.a(this.a.Q);
                this.a.y.setAdapter(this.a.z);
            }
        });
    }

    public void onClick(View view) {
        if (view == this.x || view == this.w) {
            finish();
        }
        if (view.getId() == R.id.melodyview) {
            view.setTag(this.M.d());
            this.p.a(3, null).onClick(view);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onPause() {
        super.onPause();
        if (this.A != null) {
            this.A.cancel();
        }
        if (this.B != null) {
            this.B.cancel();
        }
        this.I = false;
        k.a(this.b).o();
    }

    protected void onDestroy() {
        super.onDestroy();
        k.a((Context) this).p();
    }

    protected void onResume() {
        super.onResume();
        this.M.a((b) this);
        if (this.z != null) {
            this.z.a(this.Q);
        }
        this.I = true;
        Status a = this.M.a();
        if (a != null) {
            a(a);
        }
        this.p = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        super.onComplete(jSONObject);
        HashMap a = z.a(jSONObject);
        if (a != null && a.size() != 0) {
            this.f.edit().putString("openid", (String) a.get("qzone_uid")).putString("qzone_token", (String) a.get("qzone_token")).putString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN, (System.currentTimeMillis() + (Long.parseLong((String) a.get(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN)) * 1000)) + "").commit();
            this.c = this.f.getString("id", "");
            this.k.a((String) a.get("qzone_uid"), this.c, (String) a.get("qzone_token"), 929, this.s);
        }
    }

    public void onError(UiError uiError) {
        super.onError(uiError);
        Toast.makeText(this.b, "code:" + uiError.errorCode + ", msg:" + uiError.errorMessage + ", detail:" + uiError.errorDetail, 0).show();
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        super.onSuccess(bVar);
        try {
            this.c = this.f.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this.b, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.k.a(mAccessToken, this.c, 812, this.s);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    public void onrefreshTheme() {
        super.onrefreshTheme();
        this.G.setBackgroundResource(com.budejie.www.util.j.m);
        this.v.setTextColor(getResources().getColor(com.budejie.www.util.j.b));
        this.F.setBackgroundResource(com.budejie.www.util.j.a);
        this.y.setBackgroundResource(com.budejie.www.util.j.s);
        if (this.z != null) {
            this.z.a(this.Q);
        }
        onRefreshTitleFontTheme(this.w, true);
    }

    private void d() {
        if (!this.b.isFinishing()) {
            this.n.show();
        }
        this.c = this.f.getString("id", "");
        e();
    }

    private void e() {
        this.D = this.P != 0;
        BudejieApplication.a.a(RequstMethod.GET, com.budejie.www.http.j.c(this.c, String.valueOf(this.P)), new com.budejie.www.http.j(this), this.t);
    }

    private void a(String str) {
        if (an.a((Context) this)) {
            this.H.a("delete", this.c, str, 971);
        }
    }

    public void a(int i, String str) {
        if (!isFinishing() && i == 971) {
            this.s.sendMessage(this.s.obtainMessage(972, str));
        }
    }

    public void a(int i) {
        if (!isFinishing() && i == 971) {
            this.s.sendEmptyMessage(979);
        }
    }

    private void f() {
        this.J = new Dialog(this.b, R.style.dialogTheme);
        View inflate = this.b.getLayoutInflater().inflate(R.layout.mycomment_delete_dialog, null);
        ((TextView) inflate.findViewById(R.id.mycomment_delete_text)).setText(getString(R.string.mycollect_sync_text));
        Button button = (Button) inflate.findViewById(R.id.mycomment_delete_sureBtn);
        ((Button) inflate.findViewById(R.id.mycomment_delete_cancelBtn)).setOnClickListener(this.U);
        button.setOnClickListener(this.U);
        this.J.setContentView(inflate);
        Window window = this.J.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = an.a(this.b, 300);
        window.setAttributes(attributes);
    }

    private boolean g() {
        ArrayList a = this.j.a();
        if (a == null || a.isEmpty()) {
            return false;
        }
        return true;
    }

    public void bindTencent() {
        this.c = this.f.getString("id", "");
        String sharePersistent = Util.getSharePersistent(this.b, "ACCESS_TOKEN");
        this.k.a(Util.getSharePersistent(this.b, "NAME"), sharePersistent, Util.getSharePersistent(this.b, "OPEN_ID"), this.c, 813, this.s);
    }

    public void a(Status status) {
        int i = 0;
        switch (status) {
            case start:
                this.L.stop();
                this.L.start();
                this.K.setVisibility(0);
                return;
            case stop:
                this.L.stop();
                ImageView imageView = this.K;
                if (!an.b) {
                    i = 8;
                }
                imageView.setVisibility(i);
                return;
            case end:
                this.L.stop();
                this.K.setVisibility(8);
                return;
            default:
                return;
        }
    }
}
