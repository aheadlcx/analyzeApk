package com.budejie.www.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.budejie.www.R;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.video.p;
import com.budejie.www.bean.DraftBean;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.ShenHeItem;
import com.budejie.www.bean.TouGaoItem;
import com.budejie.www.c.d;
import com.budejie.www.c.f;
import com.budejie.www.c.g;
import com.budejie.www.c.h;
import com.budejie.www.http.e;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.http.m;
import com.budejie.www.http.n;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.am;
import com.budejie.www.util.an;
import com.budejie.www.util.au;
import com.budejie.www.util.z;
import com.budejie.www.widget.XListView;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.tauth.UiError;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class MyPostsActivity extends OauthWeiboBaseAct implements OnClickListener, com.budejie.www.f.a {
    private Dialog A;
    private h B;
    private g C;
    private d D;
    private RelativeLayout E;
    private RelativeLayout F;
    private int G;
    private a H;
    private f I;
    private ArrayList<TouGaoItem> J;
    private List<TouGaoItem> K;
    private com.budejie.www.c.b L = new com.budejie.www.c.b(this);
    private boolean M = false;
    private boolean N = false;
    private ProgressDialog O;
    private String P = "add";
    private com.budejie.www.http.b Q = null;
    private boolean R;
    private j S;
    private ListItemObject T;
    private com.budejie.www.widget.XListView.a U = new com.budejie.www.widget.XListView.a(this) {
        final /* synthetic */ MyPostsActivity a;
        private Handler b = new Handler();

        {
            this.a = r2;
        }

        public void a() {
            if (an.a(this.a.a)) {
                this.a.a.a();
            } else {
                this.b.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        an.a(this.a.a.a, this.a.a.a.getString(R.string.nonet), -1).show();
                        this.a.a.d.b();
                    }
                }, 200);
            }
        }

        public void b() {
            if (an.a(this.a.a)) {
                this.a.d();
            } else {
                this.b.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        an.a(this.a.a.a, this.a.a.a.getString(R.string.nonet), -1).show();
                        this.a.a.d.c();
                    }
                }, 200);
            }
        }
    };
    MyPostsActivity a;
    LinearLayout b;
    LinearLayout c;
    XListView d;
    Button e;
    TextView f;
    Toast g;
    String h;
    String i;
    String j;
    ShenHeItem k;
    ArrayList<TouGaoItem> l;
    com.budejie.www.adapter.c.a m;
    m n;
    SharedPreferences o;
    com.budejie.www.http.f p;
    Handler q = new Handler(this) {
        final /* synthetic */ MyPostsActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            boolean z = false;
            int i = message.what;
            String str;
            if (i == 937) {
                str = (String) message.obj;
                this.a.b.setVisibility(8);
                this.a.k = com.budejie.www.j.a.d(this.a.a, str);
                this.a.j = this.a.k.getLastId();
                this.a.K = this.a.k.getDataList();
                this.a.l.clear();
                if (this.a.M) {
                    this.a.I.a(1, this.a.K);
                } else {
                    this.a.I.a(1, this.a.K);
                }
                this.a.L.d("1");
                this.a.L.a(this.a.k);
                this.a.f();
                this.a.d.b();
                if (!this.a.R && (this.a.l == null || this.a.l.isEmpty())) {
                    this.a.g = an.a(this.a.a, this.a.a.getString(R.string.not_tougao), -1);
                    this.a.g.show();
                    this.a.d.setPullLoadEnable(z);
                }
                if (this.a.l != null && !this.a.l.isEmpty()) {
                    if (this.a.m == null) {
                        this.a.m = new com.budejie.www.adapter.c.a(this.a.a, this.a.s, 11);
                        this.a.d.setAdapter(this.a.m);
                    }
                    an.a(this.a.l, this.a.B, this.a.D, this.a.C, (int) z);
                    this.a.m.c(this.a.l);
                    this.a.d.setPullLoadEnable(true);
                    k.a(this.a.a, ((TouGaoItem) this.a.l.get(z)).getVideouri());
                }
            } else if (i == 938) {
                this.a.g = an.a(this.a.a, this.a.a.getString(R.string.parse_failed), -1);
                this.a.g.show();
                this.a.b.setVisibility(8);
                this.a.d.b();
            } else if (i == 942) {
                str = (String) message.obj;
                aa.a("MyTougaoActivity", "HANDLER_MYTOUGAO_MORE_SUCCESSED.dataList数据-->" + this.a.l);
                this.a.d.c();
                if ("[]".equals(str)) {
                    this.a.d.setPullLoadEnable(z);
                    this.a.g = an.a(this.a.a, this.a.a.getString(R.string.no_more_data), -1);
                    this.a.g.show();
                    return;
                }
                this.a.k = com.budejie.www.j.a.d(this.a.a, str);
                this.a.j = this.a.k.getLastId();
                Object dataList = this.a.k.getDataList();
                if (dataList == null || dataList.isEmpty()) {
                    this.a.d.setPullLoadEnable(z);
                    this.a.z = an.a(this.a.a, this.a.a.getString(R.string.no_more_data), -1);
                    this.a.z.show();
                    return;
                }
                this.a.l.clear();
                this.a.l.addAll(this.a.J);
                this.a.K.addAll(dataList);
                this.a.l.addAll(this.a.K);
                an.a(this.a.l, this.a.B, this.a.D, this.a.C, (int) z);
                this.a.m.d(dataList);
                this.a.d.setPullLoadEnable(true);
            } else if (i == 943) {
                this.a.d.c();
                this.a.g = an.a(this.a.a, this.a.a.getString(R.string.data_failed), -1);
                this.a.g.show();
            } else if (i == 946) {
                an.a(this.a.a, 1, "mytougao", "mytougao", 124);
            } else if (i == 947) {
                this.a.b.setVisibility(z);
                this.a.d();
            } else if (i == 816) {
                Bundle bundle = (Bundle) message.obj;
                CharSequence string = bundle.getString(com.alipay.sdk.util.j.c);
                i = bundle.getInt("notificationId");
                if (TextUtils.isEmpty(string)) {
                    this.a.y.a(i, z, (int) R.string.forwarfail);
                    this.a.g = an.a(this.a.a, this.a.a.getString(R.string.forwarfail), -1);
                    this.a.g.show();
                } else if (!"0".equals(string)) {
                    this.a.y.a(i, z, (int) R.string.forwarfail);
                    this.a.g = an.a(this.a.a, this.a.a.getString(R.string.forwarfail), -1);
                    this.a.g.show();
                }
                new Thread(this) {
                    final /* synthetic */ AnonymousClass6 b;

                    public void run() {
                        try {
                            AnonymousClass1.sleep(1000);
                            this.b.a.q.sendMessage(this.b.a.q.obtainMessage(817, Integer.valueOf(i)));
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
            } else if (i == 817) {
                this.a.y.a(((Integer) message.obj).intValue());
            } else if (i == 812) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.z = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                    this.a.z.show();
                    MobclickAgent.onEvent(this.a.a, "weibo_bind", "sina_faild");
                } else {
                    try {
                        r1 = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                    }
                    if (r1 < 0) {
                        this.a.z = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                        this.a.z.show();
                        MobclickAgent.onEvent(this.a.a, "weibo_bind", "sina_faild");
                    } else {
                        r2 = z.c(str);
                        if (r2 == null || r2.isEmpty()) {
                            this.a.z = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                            this.a.z.show();
                            MobclickAgent.onEvent(this.a.a, "weibo_bind", "sina_faild");
                        } else {
                            r1 = (String) r2.get("result_msg");
                            if ("0".equals((String) r2.get(com.alipay.sdk.util.j.c))) {
                                MobclickAgent.onEvent(this.a.a, "weibo_bind", "sina_success");
                                this.a.h = (String) r2.get("id");
                                this.a.x.a(this.a.h, r2);
                                ai.a(this.a.a, this.a.h, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                if (OauthWeiboBaseAct.mAccessToken != null) {
                                    this.a.x.a(this.a.h, OauthWeiboBaseAct.mAccessToken.e());
                                }
                                this.a.t = this.a.u.a(this.a.h);
                                this.a.z = an.a(this.a.a, this.a.a.getString(R.string.bind_successed), -1);
                                this.a.z.show();
                                i = this.a.o.getInt("position", -1);
                                if (-1 != i) {
                                    this.a.u.a(this.a.a, (TouGaoItem) this.a.l.get(i), "sina", this.a.h, this.a.t, this.a.y, (Handler) this);
                                }
                                this.a.h();
                            } else {
                                an.a(this.a.a, r1, -1).show();
                            }
                        }
                    }
                }
                this.a.q.sendEmptyMessage(815);
            } else if (i == 813) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.z = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                    this.a.z.show();
                    MobclickAgent.onEvent(this.a.a, "weibo_bind", "tencent_faild");
                } else {
                    try {
                        r1 = Integer.parseInt(str);
                    } catch (NumberFormatException e2) {
                    }
                    if (r1 < 0) {
                        this.a.z = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                        this.a.z.show();
                        MobclickAgent.onEvent(this.a.a, "weibo_bind", "tencent_faild");
                    } else {
                        r2 = z.c(str);
                        if (r2 == null || r2.isEmpty()) {
                            this.a.z = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                            this.a.z.show();
                            MobclickAgent.onEvent(this.a.a, "weibo_bind", "tencent_faild");
                        } else {
                            r1 = (String) r2.get("result_msg");
                            if ("0".equals((String) r2.get(com.alipay.sdk.util.j.c))) {
                                MobclickAgent.onEvent(this.a.a, "weibo_bind", "tencent_success");
                                this.a.h = (String) r2.get("id");
                                this.a.x.a(this.a.h, r2);
                                ai.a(this.a.a, this.a.h, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.t = this.a.u.a(this.a.h);
                                this.a.z = an.a(this.a.a, this.a.a.getString(R.string.bind_successed), -1);
                                this.a.z.show();
                                i = this.a.o.getInt("position", -1);
                                if (-1 != i) {
                                    this.a.u.a(this.a.a, (TouGaoItem) this.a.l.get(i), "qq", this.a.h, this.a.t, this.a.y, (Handler) this);
                                }
                                this.a.h();
                            } else {
                                an.a(this.a.a, r1, -1).show();
                            }
                        }
                    }
                }
                this.a.q.sendEmptyMessage(815);
            } else if (i == 929) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.z = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                    this.a.z.show();
                    MobclickAgent.onEvent(this.a.a, "weibo_bind", "qzone_faild");
                } else {
                    try {
                        r1 = Integer.parseInt(str);
                    } catch (NumberFormatException e3) {
                    }
                    if (r1 < 0) {
                        this.a.z = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                        this.a.z.show();
                        MobclickAgent.onEvent(this.a.a, "weibo_bind", "qzone_faild");
                    } else {
                        r2 = z.c(str);
                        if (r2 == null || r2.isEmpty()) {
                            this.a.z = an.a(this.a.a, this.a.a.getString(R.string.bind_failed), -1);
                            this.a.z.show();
                            MobclickAgent.onEvent(this.a.a, "weibo_bind", "qzone_faild");
                        } else {
                            r1 = (String) r2.get("result_msg");
                            if ("0".equals((String) r2.get(com.alipay.sdk.util.j.c))) {
                                MobclickAgent.onEvent(this.a.a, "weibo_bind", "qzone_success");
                                this.a.h = (String) r2.get("id");
                                this.a.x.a(this.a.h, r2);
                                ai.a(this.a.a, this.a.h, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.t = this.a.u.a(this.a.h);
                                this.a.z = an.a(this.a.a, this.a.a.getString(R.string.bind_successed), -1);
                                this.a.z.show();
                                i = this.a.o.getInt("position", -1);
                                if (-1 != i) {
                                    this.a.u.a(this.a.a, (TouGaoItem) this.a.l.get(i), com.tencent.connect.common.Constants.SOURCE_QZONE, this.a.h, this.a.t, this.a.y, (Handler) this);
                                }
                                this.a.h();
                            } else {
                                an.a(this.a.a, r1, -1).show();
                            }
                        }
                    }
                }
                this.a.q.sendEmptyMessage(815);
            } else if (i == 814) {
                this.a.A.show();
            } else if (i == 815) {
                this.a.A.cancel();
            } else if (i == TbsLog.TBSLOG_CODE_SDK_SELF_MODE) {
                this.a.A.cancel();
                str = (String) message.obj;
                r2 = z.q(str);
                if ("0".equals((String) r2.get(com.alipay.sdk.util.j.c)) || "delDraft".equals(str)) {
                    TouGaoItem touGaoItem = (TouGaoItem) this.a.l.remove(this.a.G);
                    Object videouri = touGaoItem.getVideouri();
                    if (TextUtils.isEmpty(videouri) && touGaoItem.getDraftBean() != null) {
                        videouri = touGaoItem.getDraftBean().video;
                    }
                    if (!TextUtils.isEmpty(videouri) && videouri.equals(k.a(this.a.a).d)) {
                        k.a(this.a.a).h();
                    }
                    this.a.K.remove(touGaoItem);
                    this.a.m.c(this.a.l);
                    an.a(this.a.a, this.a.a.getString(R.string.delete_mytiezi_success), -1).show();
                    return;
                }
                str = (String) r2.get("result_desc");
                if (TextUtils.isEmpty(str)) {
                    str = "删除失败";
                }
                an.a(this.a.a, str, -1).show();
            } else if (i == TbsLog.TBSLOG_CODE_SDK_INVOKE_ERROR) {
                this.a.A.cancel();
                an.a(this.a.a, this.a.a.getString(R.string.delete_mytiezi_faild), -1).show();
            } else if (i == 15) {
                str = (String) message.obj;
                this.a.b.setVisibility(8);
                this.a.k = com.budejie.www.j.a.d(this.a.a, str);
                this.a.j = this.a.k.getLastId();
                this.a.K = this.a.k.getDataList();
                this.a.l.clear();
                this.a.I.a(1, this.a.K);
                this.a.f();
                this.a.d.b();
                if (this.a.l == null || this.a.l.isEmpty()) {
                    this.a.g = an.a(this.a.a, this.a.a.getString(R.string.not_tougao), -1);
                    this.a.g.show();
                }
                if (this.a.l != null && !this.a.l.isEmpty()) {
                    if (this.a.m == null) {
                        this.a.m = new com.budejie.www.adapter.c.a(this.a.a, this.a.s, 11);
                        this.a.d.setAdapter(this.a.m);
                    }
                    this.a.m.c(this.a.l);
                }
            } else if (i == 16) {
                this.a.e();
                this.a.M = z;
                this.a.d.d();
            } else if (i == 17) {
                this.a.d.d();
            }
        }
    };
    final b r = new b(this);
    com.budejie.www.adapter.e.a s = new com.budejie.www.adapter.g.a.b(this) {
        final /* synthetic */ MyPostsActivity a;

        {
            this.a = r1;
        }

        public void e(View view, ListItemObject listItemObject) {
            view.setTag(listItemObject);
            this.a.v.a(3, null).onClick(view);
        }

        public void c(View view, ListItemObject listItemObject) {
            if (listItemObject.getState() == 1 && !TextUtils.isEmpty(listItemObject.getWid())) {
                this.a.a(listItemObject.getWid(), listItemObject.getAddtime());
            } else if (listItemObject == null || listItemObject.getWid() == null) {
                this.a.a(listItemObject);
            } else {
                this.a.a(listItemObject.getWid());
            }
            this.a.G = ((Integer) view.getTag()).intValue();
        }

        public void a(View view, ListItemObject listItemObject) {
            TipPopUp.a(this.a.a, TypeControl.dingtie);
            this.a.p.a("ding", this.a.q, listItemObject);
            this.a.p.a(listItemObject, this.a.q, "ding");
        }

        public void b(View view, ListItemObject listItemObject) {
            this.a.p.a("cai", this.a.q, listItemObject);
            this.a.p.a(listItemObject, this.a.q, "cai");
        }

        public void a(View view, ListItemObject listItemObject, int i) {
            listItemObject.setForwardNoCollect(false);
            Bundle bundle = new Bundle();
            bundle.putInt("position", i);
            bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this.a.a));
            bundle.putSerializable("weiboMap", this.a.t);
            bundle.putSerializable("data", listItemObject);
            view.setTag(listItemObject);
            this.a.r.a(listItemObject);
            this.a.T = listItemObject;
            this.a.v.a(5, bundle, this.a.r, this.a.w, this.a.x, this.a.u, this.a.y, this.a.o, this.a.q).onClick(view);
        }

        public void d(View view, ListItemObject listItemObject) {
            view.setTag(listItemObject);
            this.a.v.a(3, null).onClick(view);
        }

        public void a(ListItemObject listItemObject, int i) {
            Intent intent = new Intent(this.a.a, CommonLabelActivity.class);
            PlateBean plateBean = listItemObject.getPlateBean(i);
            if (plateBean != null) {
                intent.putExtra("theme_name", plateBean.theme_name);
                intent.putExtra("theme_id", plateBean.theme_id);
                this.a.a.startActivity(intent);
            }
        }

        public void a(ListItemObject listItemObject) {
            DraftBean b = an.b(listItemObject);
            Map a = this.a.S.a(this.a.a, new HashMap());
            a.put("format", "json");
            a.put("app", "8");
            a.put(HistoryOpenHelper.COLUMN_UID, String.valueOf(this.a.h));
            a.put("bvoiceid", String.valueOf(b.bvoiceid));
            a.put("voicetime", String.valueOf(b.voicetime));
            a.put("videotime", String.valueOf(b.videotime));
            a.put("content", String.valueOf(b.content));
            if (((BudejieApplication) this.a.getApplication()).b != null) {
                a.put("longitude", ((BudejieApplication) this.a.getApplication()).b[0]);
                a.put("latitude", ((BudejieApplication) this.a.getApplication()).b[1]);
            }
            if (!TextUtils.isEmpty(b.reserve)) {
                a.put("reserve", b.reserve);
            }
            if (b.plateDatas != null && b.plateDatas.size() > 0) {
                PlateBean plateBean = (PlateBean) b.plateDatas.get(0);
                if (!TextUtils.isEmpty(plateBean.theme_id)) {
                    a.put("theme_id", plateBean.theme_id);
                } else if (!TextUtils.isEmpty(plateBean.theme_name)) {
                    a.put("theme_name", plateBean.theme_name);
                }
            }
            a.put("vote", b.voteDataStr);
            a.put("a", "createugc");
            a.put("c", "topic");
            this.a.I.a(0, b.createTime);
            b.state = 0;
            com.budejie.www.util.b.a(this.a.a, e.a("/api/api_open.php", a, b), b.createTime, 1);
        }
    };
    private HashMap<String, String> t;
    private n u;
    private com.budejie.www.g.b v;
    private IWXAPI w;
    private com.budejie.www.c.m x;
    private com.elves.update.a y;
    private Toast z;

    class a extends BroadcastReceiver {
        final /* synthetic */ MyPostsActivity a;

        a(MyPostsActivity myPostsActivity) {
            this.a = myPostsActivity;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.budejie.www.draft.action")) {
                int intExtra = intent.getIntExtra("result_code", -1);
                String stringExtra = intent.getStringExtra("create_time");
                Object stringExtra2 = intent.getStringExtra(com.alipay.sdk.cons.b.c);
                String stringExtra3 = intent.getStringExtra("landuri");
                if (!TextUtils.isEmpty(stringExtra)) {
                    if (intExtra == 0) {
                        DraftBean a = this.a.I.a(stringExtra);
                        this.a.I.a(1, stringExtra);
                        if (!TextUtils.isEmpty(stringExtra2)) {
                            this.a.I.a(stringExtra, stringExtra2, stringExtra3);
                        }
                        if (a == null || a.bvoiceid == 0) {
                            this.a.g();
                        } else {
                            this.a.q.sendEmptyMessageDelayed(16, 2000);
                        }
                        SharedPreferences sharedPreferences = context.getSharedPreferences("tip", 0);
                        Editor edit = sharedPreferences.edit();
                        if (a != null && !a.createTime.equals(sharedPreferences.getString("createTime", "123"))) {
                            edit.putString("createTime", a.createTime);
                            edit.commit();
                            if (an.b(a)) {
                                TipPopUp.a(this.a.a, TypeControl.post, TypeControl.pic);
                                intExtra = R.string.track_action_send_picture;
                            } else if (an.c(a)) {
                                TipPopUp.a(this.a.a, TypeControl.post, TypeControl.voice);
                                intExtra = R.string.track_action_send_voice;
                            } else if (an.d(a)) {
                                TipPopUp.a(this.a.a, TypeControl.post, TypeControl.vedio);
                                intExtra = R.string.track_action_send_video;
                            } else {
                                TipPopUp.a(this.a.a, TypeControl.post, TypeControl.text);
                                intExtra = R.string.track_action_send_text;
                            }
                            i.a(this.a.a.getString(intExtra), this.a.a.getString(R.string.track_event_send_post_success));
                            return;
                        }
                        return;
                    }
                    this.a.I.a(-1, stringExtra);
                    this.a.g();
                }
            }
        }
    }

    public class b extends Handler {
        final /* synthetic */ MyPostsActivity a;
        private ListItemObject b;

        public b(MyPostsActivity myPostsActivity) {
            this.a = myPostsActivity;
        }

        public void a(ListItemObject listItemObject) {
            this.b = listItemObject;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 4) {
                this.b.setLove(this.b.getLove() + 1);
            } else if (i == 5) {
                this.a.O = ProgressDialog.show(this.a.a, "", (String) message.obj, true, true);
            } else if (i == 6) {
                this.a.O.cancel();
            } else if (i == 7) {
                an.a(this.a.a, this.a.a.getString(R.string.already_collected), -1).show();
            } else if (i == 9) {
                this.b.setRepost(String.valueOf(Integer.parseInt(TextUtils.isEmpty(this.b.getRepost()) ? "0" : this.b.getRepost()) + 1));
                com.budejie.www.util.m.a(this.a.a, this.a.r, this.b);
            } else if (i == 91) {
                this.b.setRepost(String.valueOf(Integer.parseInt(TextUtils.isEmpty(this.b.getRepost()) ? "0" : this.b.getRepost()) + 1));
            } else if (i == 10) {
                an.a(this.a.a, this.a.a.getString(R.string.collect_failed), -1).show();
            } else if (i == 11) {
                Object b = ai.b(this.a.a);
                if (an.j(this.a.a) && an.k(this.a.a) && !b.equals("")) {
                    an.a(this.a.a, false);
                    sendEmptyMessage(13);
                } else {
                    an.a(this.a.a, (int) R.string.collected, (int) R.drawable.collect_tip).show();
                }
                if (!TextUtils.isEmpty(b)) {
                    this.a.P = "add";
                    this.a.a(this.a.P, (String) message.obj, (String) b);
                }
            } else if (i == 12) {
                an.a(this.a.a, (int) R.string.collect_fail, (int) R.drawable.collect_tip).show();
            } else if (i == 100001) {
                an.a(this.a.a, this.a.a.getString(R.string.forwardAndCollect_succeed), -1).show();
                if (!TextUtils.isEmpty(ai.b(this.a.a))) {
                    this.a.P = "add";
                    this.a.a(this.a.P, (String) message.obj, ai.b(this.a.a));
                }
            } else if (i == 829) {
                r0 = (String) message.obj;
                this.a.L.a("collectTable", r0);
                an.a(this.a.a, this.a.a.getString(R.string.delete_success), -1).show();
                this.a.P = "delete";
                this.a.a(this.a.P, r0, ai.b(this.a.a));
            } else if (i == 13) {
                an.b(this.a.a, this.a.d);
            } else if (i == 1001) {
                Toast a;
                HashMap k = z.k((String) message.obj);
                if (k != null) {
                    r0 = (String) k.get("result_desc");
                    if (TextUtils.isEmpty(r0)) {
                        a = an.a(this.a.a, this.a.a.getString(R.string.operate_fail), -1);
                    } else {
                        a = an.a(this.a.a, r0, -1);
                    }
                } else {
                    a = an.a(this.a.a, this.a.a.getString(R.string.operate_fail), -1);
                }
                if (a != null) {
                    a.show();
                }
            }
            this.a.h();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.my_posts_activity);
        com.budejie.www.widget.a.a((Activity) this);
        this.a = this;
        this.S = new j();
        this.M = getIntent().getBooleanExtra("local_file", this.M);
        this.N = getIntent().getBooleanExtra("from_tougao_tag", false);
        this.I = new f(this);
        this.B = new h(this);
        this.C = new g(this);
        this.D = new d(this);
        this.p = new com.budejie.www.http.f(this.a);
        this.R = false;
        c();
        e();
        b();
    }

    protected void onResume() {
        super.onResume();
        this.h = this.o.getString("id", "");
        this.t = this.u.a(this.h);
        this.Q = com.budejie.www.http.b.a(this, this);
        this.v = new com.budejie.www.g.b(this.a, this.mSsoHandler, this.mTencent, this);
    }

    private void b() {
        this.H = new a(this);
        registerReceiver(this.H, new IntentFilter("com.budejie.www.draft.action"));
    }

    public void finish() {
        super.finish();
        this.j = "";
        this.R = true;
        d();
        this.I.a();
    }

    private void c() {
        this.l = new ArrayList();
        this.c = (LinearLayout) findViewById(R.id.left_layout);
        p.a(this, this.c);
        this.e = (Button) findViewById(R.id.title_left_btn);
        this.f = (TextView) findViewById(R.id.title_center_txt);
        this.d = (XListView) findViewById(R.id.listview);
        this.b = (LinearLayout) findViewById(R.id.loading_layout);
        this.E = (RelativeLayout) findViewById(R.id.curtain_root_layout);
        this.F = (RelativeLayout) findViewById(R.id.topLayout);
        this.d.setXListViewListener(this.U);
        this.d.setOnScrollListener(new com.nostra13.universalimageloader.core.d.e(com.nostra13.universalimageloader.core.d.a(), false, true, new OnScrollListener(this) {
            final /* synthetic */ MyPostsActivity a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (k.a(this.a.a).c) {
                    k.a(this.a.a).a(absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition(), this.a.d.getHeaderViewsCount());
                }
            }
        }));
        this.c.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setText(R.string.mytougao);
        this.e.setVisibility(0);
        this.n = new m();
        this.u = new n(this);
        this.x = new com.budejie.www.c.m(this.a);
        this.y = new com.elves.update.a(this);
        this.t = new HashMap();
        this.A = new Dialog(this, R.style.dialogTheme);
        this.A.setContentView(R.layout.loaddialog);
        if (this.mSsoHandler == null) {
            this.mSsoHandler = new com.sina.weibo.sdk.auth.a.a(this);
        }
        this.v = new com.budejie.www.g.b(this.a, this.mSsoHandler, this.mTencent, this);
        this.o = getSharedPreferences("weiboprefer", 0);
        this.h = this.o.getString("id", "");
        try {
            this.i = this.x.e(this.h).getName();
        } catch (Exception e) {
            this.i = "";
        }
    }

    private void d() {
        this.n.a(this.j, new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ MyPostsActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void a(String str) {
                if ("-2".equals(str)) {
                    this.a.q.sendEmptyMessage(946);
                    return;
                }
                Message obtainMessage;
                if (TextUtils.isEmpty(this.a.j)) {
                    obtainMessage = this.a.q.obtainMessage(937, str);
                } else {
                    obtainMessage = this.a.q.obtainMessage(942, str);
                }
                this.a.q.sendMessage(obtainMessage);
            }

            public void onFailure(Throwable th, int i, String str) {
                if (TextUtils.isEmpty(this.a.j)) {
                    this.a.q.sendEmptyMessage(938);
                } else {
                    this.a.q.sendEmptyMessage(943);
                }
            }
        });
    }

    private void e() {
        List<ListItemObject> b = this.L.b("1", "created_at desc");
        if (b == null || b.size() == 0) {
            this.j = "";
            this.d.postDelayed(new Runnable(this) {
                final /* synthetic */ MyPostsActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.d.d();
                }
            }, 200);
            return;
        }
        this.K = new ArrayList();
        for (ListItemObject a : b) {
            this.K.add(an.a(a));
        }
        this.j = ((ListItemObject) b.get(0)).getReadid();
        f();
        this.b.setVisibility(4);
        if (this.N) {
            this.q.sendEmptyMessageDelayed(17, 1000);
        }
    }

    private void f() {
        boolean z;
        List<DraftBean> b = this.I.b("state ASC,createTime DESC");
        if (this.J == null) {
            this.J = new ArrayList();
        } else {
            this.J.clear();
        }
        if (!(b == null || b.isEmpty())) {
            for (DraftBean draftBean : b) {
                TouGaoItem touGaoItem = new TouGaoItem();
                touGaoItem.setDraftBean(draftBean);
                this.J.add(touGaoItem);
            }
        }
        this.l.clear();
        this.l.addAll(this.J);
        if (this.K != null) {
            this.l.addAll(this.K);
        }
        if (this.m == null) {
            this.m = new com.budejie.www.adapter.c.a(this.a, this.s, 11);
            this.d.setAdapter(this.m);
        }
        an.a(this.l, this.B, this.D, this.C, 0);
        this.m.c(this.l);
        XListView xListView = this.d;
        if (this.m.isEmpty()) {
            z = false;
        } else {
            z = true;
        }
        xListView.setPullLoadEnable(z);
    }

    private void g() {
        List<DraftBean> b = this.I.b("createTime DESC");
        if (this.J == null) {
            this.J = new ArrayList();
        } else {
            this.J.clear();
        }
        if (!(b == null || b.isEmpty())) {
            for (DraftBean draftBean : b) {
                TouGaoItem touGaoItem = new TouGaoItem();
                touGaoItem.setDraftBean(draftBean);
                this.J.add(touGaoItem);
            }
        }
        this.l.clear();
        if (this.J != null) {
            this.l.addAll(this.J);
        }
        if (this.K != null) {
            this.l.addAll(this.K);
        }
        if (this.m == null) {
            this.m = new com.budejie.www.adapter.c.a(this.a, this.s, 11);
            this.d.setAdapter(this.m);
        }
        this.m.c(this.l);
    }

    private void h() {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ MyPostsActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.m != null) {
                    this.a.m.notifyDataSetChanged();
                }
            }
        });
    }

    public void a(int i, String str) {
        if (i == 1111130) {
            this.q.sendMessage(this.q.obtainMessage(TbsLog.TBSLOG_CODE_SDK_SELF_MODE, str));
        } else if (i != 1111131) {
        } else {
            if ("-2".equals(str)) {
                this.q.sendEmptyMessage(946);
                return;
            }
            this.q.sendMessage(this.q.obtainMessage(15, str));
        }
    }

    public void a(int i) {
        if (i == 1111130) {
            this.q.sendEmptyMessage(TbsLog.TBSLOG_CODE_SDK_INVOKE_ERROR);
        } else if (i == 1111131) {
            this.q.sendEmptyMessage(938);
        }
    }

    public void a() {
        this.j = "";
        d();
        MobclickAgent.onEvent((Context) this, "E05-A06", "我的投稿刷新数");
    }

    public void onClick(View view) {
        if (view == this.e || view == this.c) {
            finish();
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.g != null) {
            this.g.cancel();
        }
        k.a((Context) this).o();
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.H);
        k.a((Context) this).p();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 124) {
            this.q.sendEmptyMessage(947);
        } else if (i2 == 711) {
            this.q.sendEmptyMessage(814);
            bindTencent();
        }
    }

    public void onWbShareSuccess() {
        super.onWbShareSuccess();
        am.a(this.T);
        if (this.m != null) {
            this.m.notifyDataSetChanged();
        }
    }

    private void a(String str, String str2, String str3) {
        if (an.a((Context) this)) {
            this.Q.a(str, str3, str2, 971);
        }
    }

    private void a(final String str) {
        Builder builder = new Builder(this.a);
        builder.setTitle(R.string.sd_title);
        builder.setMessage(R.string.delete_mytiezi_dialog_text);
        builder.setPositiveButton(R.string.delete_mytiezi_sure, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ MyPostsActivity b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.A.show();
                this.b.n.a(this.b.a, str, 1111130, this.b.a);
            }
        });
        builder.setNegativeButton(R.string.delete_mytiezi_cancel, null);
        if (!this.a.isFinishing()) {
            builder.show();
        }
    }

    private void a(final String str, final String str2) {
        Builder builder = new Builder(this.a);
        builder.setTitle(R.string.sd_title);
        builder.setMessage(R.string.delete_mytiezi_dialog_text);
        builder.setPositiveButton(R.string.delete_mytiezi_sure, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ MyPostsActivity c;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.c.A.show();
                this.c.n.a(this.c.a, str, 1111130, this.c.a);
                this.c.I.c(str2);
            }
        });
        builder.setNegativeButton(R.string.delete_mytiezi_cancel, null);
        if (!this.a.isFinishing()) {
            builder.show();
        }
    }

    private void a(final ListItemObject listItemObject) {
        Builder builder = new Builder(this.a);
        builder.setTitle(R.string.sd_title);
        builder.setMessage(R.string.delete_mytiezi_dialog_text);
        builder.setPositiveButton(R.string.delete_mytiezi_sure, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ MyPostsActivity b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.q.sendMessage(this.b.q.obtainMessage(TbsLog.TBSLOG_CODE_SDK_SELF_MODE, "delDraft"));
                this.b.I.c(listItemObject.getAddtime());
            }
        });
        builder.setNegativeButton(R.string.delete_mytiezi_cancel, null);
        if (!this.a.isFinishing()) {
            builder.show();
        }
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        super.onComplete(jSONObject);
        HashMap a = z.a(jSONObject);
        if (a != null && a.size() != 0) {
            this.q.sendEmptyMessage(814);
            this.o.edit().putString("openid", (String) a.get("qzone_uid")).putString("qzone_token", (String) a.get("qzone_token")).putString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN, (System.currentTimeMillis() + (Long.parseLong((String) a.get(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN)) * 1000)) + "").commit();
            this.h = this.o.getString("id", "");
            this.u.a((String) a.get("qzone_uid"), this.h, (String) a.get("qzone_token"), 929, this.q);
        }
    }

    public void onError(UiError uiError) {
        super.onError(uiError);
        this.q.sendEmptyMessage(815);
        Toast.makeText(this.a, "code:" + uiError.errorCode + ", msg:" + uiError.errorMessage + ", detail:" + uiError.errorDetail, 0).show();
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        this.q.sendEmptyMessage(814);
        try {
            this.h = this.o.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this.a, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.u.a(mAccessToken, this.h, 812, this.q);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    public void onrefreshTheme() {
        super.onrefreshTheme();
        this.f.setTextColor(getResources().getColor(com.budejie.www.util.j.b));
        this.E.setBackgroundResource(com.budejie.www.util.j.an);
        this.F.setBackgroundResource(com.budejie.www.util.j.a);
        onRefreshTitleFontTheme(this.e, true);
    }

    public void bindTencent() {
        this.h = this.o.getString("id", "");
        String sharePersistent = Util.getSharePersistent(this.a, "ACCESS_TOKEN");
        this.u.a(Util.getSharePersistent(this.a, "NAME"), sharePersistent, Util.getSharePersistent(this.a, "OPEN_ID"), this.h, 813, this.q);
    }
}
