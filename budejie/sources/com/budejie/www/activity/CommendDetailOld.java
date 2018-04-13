package com.budejie.www.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.internal.view.SupportMenu;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.bdj.picture.edit.EditPictureActivity;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.BudejieApplication.Status;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.activity.image.BitmapCache;
import com.budejie.www.activity.image.SelectImageActivity;
import com.budejie.www.activity.label.CommonLabelActivity;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.video.CommentItemVideoActivity;
import com.budejie.www.activity.video.VideoView;
import com.budejie.www.activity.view.AudioLayout;
import com.budejie.www.activity.view.CustomListView;
import com.budejie.www.ad.AdConfig;
import com.budejie.www.ad.AdManager;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.bean.CommentVoiceToWordsItem;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.SuggestedFollowsListItem;
import com.budejie.www.bean.UserItem;
import com.budejie.www.bean.VoteData;
import com.budejie.www.busevent.DetailAction;
import com.budejie.www.busevent.UpdateCommentAction;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.n;
import com.budejie.www.util.aa;
import com.budejie.www.util.ac;
import com.budejie.www.util.ai;
import com.budejie.www.util.al;
import com.budejie.www.util.an;
import com.budejie.www.util.aq;
import com.budejie.www.util.au;
import com.budejie.www.util.ax;
import com.budejie.www.util.p;
import com.budejie.www.util.v;
import com.budejie.www.util.w;
import com.budejie.www.util.z;
import com.budejie.www.widget.CommentOperateView;
import com.budejie.www.widget.KeyboardListenerRelativeLayout;
import com.budejie.www.widget.RecordVoiceView;
import com.budejie.www.widget.VoteView;
import com.budejie.www.widget.curtain.BarrageStatusManager;
import com.budejie.www.widget.curtain.BarrageStatusManager.BarrageState;
import com.budejie.www.widget.curtain.FloatVideoRootLayout;
import com.budejie.www.widget.parsetagview.NewParseTagEditText;
import com.budejie.www.widget.parsetagview.ParseTagEditText;
import com.budejie.www.widget.parsetagview.ParseTagTextView;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.sprite.ads.banner.BannerADListener;
import com.sprite.ads.banner.BannerAd;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.nati.reporter.Reporter;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.open.SocialConstants;
import com.tencent.smtt.sdk.WebView;
import com.tencent.tauth.UiError;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONObject;
import pl.droidsonroids.gif.GifDrawable;

@SuppressLint({"HandlerLeak", "NewApi"})
public class CommendDetailOld extends OauthWeiboBaseAct implements OnClickListener, com.budejie.www.activity.view.CustomListView.c, com.budejie.www.adapter.e.a, com.budejie.www.f.a, com.budejie.www.g.a {
    public static String b;
    private static boolean bF = true;
    private TextView A;
    private RecordVoiceView B;
    private CommentOperateView C;
    private View D;
    private View E;
    private View F;
    private RelativeLayout G;
    private ImageView H;
    private ImageView I;
    private TextView J;
    private TextView K;
    private TextView L;
    private TextView M;
    private EditText N;
    private RelativeLayout O;
    private Toast P;
    private Intent Q;
    private CommentItem R;
    private int S;
    private int T;
    private int U = 1;
    private int V = -1;
    private int W;
    private int X = R$styleable.Theme_Custom_send_btn_text_color;
    private String Y;
    private String Z;
    PopupWindow a;
    private File aA;
    private File aB;
    private File aC;
    private File aD;
    private int aE;
    private int aF;
    private String aG;
    private boolean aH = false;
    private View aI;
    private View aJ;
    private View aK;
    private com.budejie.www.adapter.a aL;
    private View aM;
    private Dialog aN;
    private LinearLayout aO;
    private boolean aP = true;
    private int aQ;
    private com.budejie.www.http.j aR;
    private String aS;
    private String aT;
    private int aU;
    private ArrayList<String> aV;
    private boolean aW;
    private RelativeLayout aX;
    private RelativeLayout aY;
    private TextView aZ;
    private boolean aa = true;
    private boolean ab = false;
    private n ac;
    private com.budejie.www.http.c ad;
    private com.budejie.www.util.n ae;
    private com.budejie.www.c.e af;
    private com.budejie.www.c.g ag;
    private com.elves.update.a ah;
    private SharedPreferences ai;
    private ArrayList<CommentItem> aj = new ArrayList();
    private boolean ak = false;
    private boolean al = false;
    private boolean am = true;
    private FloatVideoRootLayout an;
    private RelativeLayout ao;
    private RelativeLayout ap;
    private final String aq = "unlike";
    private final String ar = "20";
    private String as;
    private boolean at = false;
    private boolean au = false;
    private Resources av;
    private ListItemObject aw;
    private InputMethodManager ax;
    private String ay = "";
    private String az = "";
    private SharedPreferences bA;
    private IWXAPI bB;
    private String bC = "add";
    private ProgressDialog bD;
    private OnClickListener bE = new OnClickListener(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
        }
    };
    private com.budejie.www.activity.view.CustomListView.b bG = new com.budejie.www.activity.view.CustomListView.b(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public void a() {
            if (!an.a(this.a.o)) {
                this.a.P = an.a(this.a.o, this.a.o.getString(R.string.nonet), -1);
                this.a.P.show();
            } else if (this.a.am && this.a.t.a == 3) {
                this.a.L();
                if (!this.a.aW) {
                    this.a.aW = true;
                    this.a.B();
                }
            }
        }
    };
    private com.budejie.www.activity.view.CustomListView.a bH = new com.budejie.www.activity.view.CustomListView.a(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public void a(int i) {
            if (this.a.a != null && this.a.a.isShowing()) {
                this.a.a.dismiss();
            }
        }
    };
    private OnScrollListener bI = new OnScrollListener(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (com.budejie.www.activity.video.k.a(this.a.o).c) {
                com.budejie.www.activity.video.k.a(this.a.o).b(absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition(), 1);
            }
            Log.d(this.a.n, "onScroll view.getLastVisiblePosition()=" + absListView.getLastVisiblePosition());
            Log.d(this.a.n, "onScroll view.getAdapter().getCount()=" + ((ListAdapter) absListView.getAdapter()).getCount());
            if (absListView.getLastVisiblePosition() == ((ListAdapter) absListView.getAdapter()).getCount() - 1) {
                this.a.bq = true;
                if (this.a.d != null) {
                    this.a.d.a(false);
                    return;
                }
                return;
            }
            this.a.bq = false;
        }
    };
    private net.tsz.afinal.a.a<String> bJ = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.aW = false;
            this.a.t.b();
            this.a.m();
            this.a.n();
            this.a.t.a = 3;
            if (this.a.aj.isEmpty() && this.a.aa) {
                an.a(this.a.o, this.a.getString(R.string.data_failed), -1).show();
            }
        }

        public void a(String str) {
            super.onSuccess(str);
            new AsyncTask<String, String, ArrayList<CommentItem>>(this) {
                final /* synthetic */ AnonymousClass16 a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((ArrayList) obj);
                }

                protected void onPreExecute() {
                    super.onPreExecute();
                    this.a.a.m();
                }

                protected void a(ArrayList<CommentItem> arrayList) {
                    switch (this.a.a.V) {
                        case 0:
                            this.a.a.m();
                            this.a.a.t.a = 3;
                            this.a.a.t.b();
                            this.a.a.t.setOnRefreshListener(this.a.a);
                            if (this.a.a.aj.isEmpty()) {
                                this.a.a.j();
                                this.a.a.aW = false;
                            } else {
                                this.a.a.f();
                                this.a.a.k();
                                this.a.a.a(this.a.a.aj);
                                this.a.a.t.setVisibility(0);
                                this.a.a.u();
                            }
                            if (this.a.a.aT == null || this.a.a.aT.equals("null")) {
                                this.a.a.A();
                                return;
                            }
                            return;
                        case 1:
                            this.a.a.aW = false;
                            this.a.a.a(this.a.a.aj);
                            this.a.a.v.notifyDataSetChanged();
                            return;
                        case 2:
                            this.a.a.aW = false;
                            if (this.a.a.aT == null || this.a.a.aT.equals("null")) {
                                this.a.a.A();
                                return;
                            }
                            this.a.a.a(this.a.a.aj);
                            this.a.a.v.notifyDataSetChanged();
                            return;
                        default:
                            return;
                    }
                }

                protected ArrayList<CommentItem> a(String... strArr) {
                    String str = strArr[0];
                    this.a.a.t.a = 3;
                    try {
                        if (this.a.a.V == 0) {
                            this.a.a.aj = z.a(str, "0", this.a.a.o);
                        } else if (this.a.a.V == 1) {
                            Collection a = z.a(str, "1", this.a.a.o);
                            if (a != null && a.size() > 0) {
                                for (int i = 0; i < this.a.a.aj.size(); i++) {
                                    CommentItem commentItem = (CommentItem) this.a.a.aj.get(i);
                                    if (commentItem.getHotNp() != null) {
                                        commentItem.setHotNp(null);
                                        this.a.a.aj.addAll(i + 1, a);
                                        break;
                                    }
                                }
                            }
                        } else if (this.a.a.V == 2) {
                            this.a.a.aj.addAll(z.a(str, "2", this.a.a.o));
                        }
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject != null) {
                            if (jSONObject.has(StatisticCodeTable.HOT)) {
                                JSONObject jSONObject2 = jSONObject.getJSONObject(StatisticCodeTable.HOT);
                                if (jSONObject2.has("info")) {
                                    this.a.a.aS = jSONObject2.getJSONObject("info").getString("np");
                                }
                            }
                            if (jSONObject.has("normal")) {
                                jSONObject = jSONObject.getJSONObject("normal");
                                if (jSONObject.has("info")) {
                                    this.a.a.aT = jSONObject.getJSONObject("info").getString("np");
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return this.a.a.aj;
                }
            }.execute(new String[]{str});
        }
    };
    private net.tsz.afinal.a.a<String> bK = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
            this.a.aX.setVisibility(0);
            this.a.aY.setVisibility(0);
            this.a.aZ.setVisibility(8);
            this.a.bc.setVisibility(8);
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.aY.setVisibility(8);
            this.a.aZ.setVisibility(8);
            this.a.bc.setVisibility(0);
            this.a.bd.setText("语音转换失败");
        }

        public void a(String str) {
            super.onSuccess(str);
            new AsyncTask<String, String, CommentVoiceToWordsItem>(this) {
                final /* synthetic */ AnonymousClass17 a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((CommentVoiceToWordsItem) obj);
                }

                protected void onPreExecute() {
                    super.onPreExecute();
                }

                protected void a(CommentVoiceToWordsItem commentVoiceToWordsItem) {
                    if (this.a.a.aY != null) {
                        this.a.a.aY.setVisibility(8);
                    }
                    this.a.a.aZ.setVisibility(0);
                    if (commentVoiceToWordsItem == null) {
                        this.a.a.aZ.setVisibility(8);
                        this.a.a.bc.setVisibility(0);
                        this.a.a.bd.setText("语音转换失败");
                    } else if (commentVoiceToWordsItem.getCode() == null || !commentVoiceToWordsItem.getCode().equals("1")) {
                        this.a.a.aZ.setVisibility(8);
                        this.a.a.bc.setVisibility(0);
                        CharSequence charSequence = "语音转换失败";
                        if (commentVoiceToWordsItem.getInfo() != null && commentVoiceToWordsItem.getInfo().length() > 0) {
                            charSequence = commentVoiceToWordsItem.getInfo();
                        }
                        this.a.a.bd.setText(charSequence);
                    } else {
                        this.a.a.aZ.setText(commentVoiceToWordsItem.getMsg());
                    }
                }

                protected CommentVoiceToWordsItem a(String... strArr) {
                    CommentVoiceToWordsItem commentVoiceToWordsItem = null;
                    try {
                        commentVoiceToWordsItem = z.b(strArr[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return commentVoiceToWordsItem;
                }
            }.execute(new String[]{str});
        }
    };
    private net.tsz.afinal.a.a<String> bL = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
        }

        public void a(String str) {
            super.onSuccess(str);
            new AsyncTask<String, String, CommentItem>(this) {
                final /* synthetic */ AnonymousClass18 a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((CommentItem) obj);
                }

                protected void onPreExecute() {
                    super.onPreExecute();
                    this.a.a.m();
                }

                protected void a(CommentItem commentItem) {
                    this.a.a.v.notifyDataSetChanged();
                }

                protected CommentItem a(String... strArr) {
                    String str = strArr[0];
                    CommentItem commentItem = null;
                    if (this.a.a.aU != -1) {
                        commentItem = (CommentItem) this.a.a.aj.get(this.a.a.aU);
                    }
                    if (commentItem != null) {
                        try {
                            commentItem = z.a(str);
                            if (this.a.a.aU == 0) {
                                commentItem.setIsnew(true);
                                commentItem.setTagIsShow(true);
                            }
                            this.a.a.a(commentItem);
                            this.a.a.aj.remove(this.a.a.aU);
                            this.a.a.aj.add(this.a.a.aU, commentItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return commentItem;
                }
            }.execute(new String[]{str});
        }
    };
    private com.budejie.www.widget.KeyboardListenerRelativeLayout.a bM = new com.budejie.www.widget.KeyboardListenerRelativeLayout.a(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public void a(boolean z) {
            if (BarrageStatusManager.a(this.a.bA) != BarrageState.CLOSE && com.budejie.www.activity.video.k.a(this.a).b()) {
                return;
            }
            if (z) {
                this.a.bg = true;
                this.a.bt.sendEmptyMessageDelayed(16, 1);
                aq.a(this.a.o, R.color.record_voice_mask_bar_color);
                return;
            }
            this.a.F.setVisibility(8);
            aq.a(this.a.o, com.budejie.www.h.c.a().b(R.attr.title_background));
            this.a.bg = false;
            if (this.a.bf == BottomStatus.VOICE) {
                aq.a(this.a.o, R.color.record_voice_mask_bar_color);
                this.a.bN.sendEmptyMessage(1);
            } else if (this.a.bf == BottomStatus.OPERATE) {
                aq.a(this.a.o, R.color.record_voice_mask_bar_color);
                this.a.bt.sendEmptyMessage(18);
            } else if (System.currentTimeMillis() - this.a.bi > 1000) {
                this.a.bf = BottomStatus.NORMAL;
                this.a.e();
            }
        }
    };
    private Handler bN = new Handler(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            Log.d(this.a.n, "recordVoiceViewHandler msg=" + message.what);
            switch (message.what) {
                case 1:
                    this.a.g();
                    this.a.B.setVisibility(0);
                    return;
                case 3:
                    this.a.aB = (File) message.obj;
                    this.a.aF = message.arg1;
                    this.a.a(true, false);
                    return;
                case 4:
                    this.a.bf = BottomStatus.NORMAL;
                    this.a.e();
                    return;
                default:
                    return;
            }
        }
    };
    private TextView ba;
    private ScrollView bb;
    private LinearLayout bc;
    private TextView bd;
    private boolean be;
    private BottomStatus bf = BottomStatus.NORMAL;
    private boolean bg;
    private com.budejie.www.widget.f bh;
    private long bi;
    private BitmapCache bj;
    private int bk = 5;
    private String bl;
    private RelativeLayout bm;
    private VideoView bn;
    private RelativeLayout bo;
    private TextView bp;
    private boolean bq;
    private TextView br;
    private boolean bs = false;
    private Handler bt = new Handler(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = 0;
            try {
                String str;
                Map c;
                String str2;
                switch (message.what) {
                    case 1:
                        this.a.m();
                        this.a.t.a = 3;
                        this.a.aj = z.a((String) message.obj, true);
                        this.a.t.b();
                        this.a.t.setOnRefreshListener(this.a);
                        if (this.a.aj.isEmpty()) {
                            this.a.j();
                        } else {
                            this.a.k();
                            this.a.a(this.a.aj);
                            this.a.t.setVisibility(0);
                            this.a.u();
                        }
                        int parseInt = Integer.parseInt(TextUtils.isEmpty(this.a.as) ? "0" : this.a.as);
                        if (this.a.v == null || this.a.v.getCount() < parseInt) {
                            this.a.A();
                            Log.i("CommendDetail", "getCount:" + this.a.v.getCount() + ",comCount:" + parseInt);
                            Log.i("CommendDetail", "1调用暂无评论");
                            return;
                        }
                        return;
                    case 3:
                        this.a.t.b();
                        this.a.m();
                        this.a.n();
                        this.a.t.a = 3;
                        if (this.a.aj.isEmpty() && this.a.aa) {
                            an.a(this.a.o, this.a.getString(R.string.data_failed), -1).show();
                            return;
                        }
                        return;
                    case 4:
                        str = (String) message.obj;
                        if (TextUtils.isEmpty(str)) {
                            an.a(this.a.o, this.a.getString(R.string.bind_failed), -1).show();
                            MobclickAgent.onEvent(this.a.o, "weibo_bind", "sina_faild");
                        } else {
                            try {
                                i = Integer.parseInt(str);
                            } catch (NumberFormatException e) {
                            }
                            if (i < 0) {
                                an.a(this.a.o, this.a.getString(R.string.bind_failed), -1).show();
                                MobclickAgent.onEvent(this.a.o, "weibo_bind", "sina_faild");
                            } else {
                                c = z.c(str);
                                if (c == null || c.isEmpty()) {
                                    MobclickAgent.onEvent(this.a.o, "weibo_bind", "sina_faild");
                                } else {
                                    str2 = (String) c.get("result_msg");
                                    if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                        MobclickAgent.onEvent(this.a.o, "weibo_bind", "sina_success");
                                        this.a.Z = (String) c.get("id");
                                        this.a.by.a(this.a.Z, c);
                                        ai.a(this.a.o, this.a.Z, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                        if (OauthWeiboBaseAct.mAccessToken != null) {
                                            this.a.by.a(this.a.Z, OauthWeiboBaseAct.mAccessToken.e());
                                        }
                                        this.a.bz = this.a.ac.a(this.a.Z);
                                        this.a.ac.a(this.a.o, this.a.aw, "sina", this.a.Z, this.a.bz, this.a.ah, this.a.bt);
                                    } else {
                                        an.a(this.a.o, str2, -1).show();
                                    }
                                }
                            }
                        }
                        this.a.u.dismiss();
                        return;
                    case 5:
                        str = (String) message.obj;
                        if (TextUtils.isEmpty(str)) {
                            an.a(this.a.o, this.a.getString(R.string.bind_failed), -1).show();
                            MobclickAgent.onEvent(this.a.o, "weibo_bind", "tencent_faild");
                        } else {
                            try {
                                i = Integer.parseInt(str);
                            } catch (NumberFormatException e2) {
                            }
                            if (i < 0) {
                                this.a.P = an.a(this.a.o, this.a.o.getString(R.string.bind_failed), -1);
                                this.a.P.show();
                                MobclickAgent.onEvent(this.a.o, "weibo_bind", "tencent_faild");
                            } else {
                                c = z.c(str);
                                if (c == null || c.isEmpty()) {
                                    MobclickAgent.onEvent(this.a.o, "weibo_bind", "tencent_faild");
                                } else {
                                    str2 = (String) c.get("result_msg");
                                    if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                        MobclickAgent.onEvent(this.a.o, "weibo_bind", "tencent_success");
                                        this.a.Z = (String) c.get("id");
                                        this.a.by.a(this.a.Z, c);
                                        ai.a(this.a.o, this.a.Z, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                        this.a.bz = this.a.ac.a(this.a.Z);
                                        this.a.ac.a(this.a.o, this.a.aw, "qq", this.a.Z, this.a.bz, this.a.ah, this.a.bt);
                                    } else {
                                        an.a(this.a.o, str2, -1).show();
                                    }
                                }
                            }
                        }
                        this.a.u.dismiss();
                        return;
                    case 7:
                        List a = z.a((String) message.obj, false);
                        Log.i("CommendDetail", "请求更多数据：" + a.size());
                        if (a.isEmpty()) {
                            this.a.A();
                            this.a.m();
                            return;
                        }
                        this.a.a(a);
                        this.a.aj.addAll(a);
                        this.a.v.notifyDataSetChanged();
                        return;
                    case 8:
                        str = (String) message.obj;
                        if (TextUtils.isEmpty(str)) {
                            this.a.P = an.a(this.a.o, this.a.o.getString(R.string.forwarfail), -1);
                            this.a.P.show();
                            return;
                        } else if (!"0".equals(str)) {
                            return;
                        } else {
                            return;
                        }
                    case 9:
                        str = (String) message.obj;
                        if (TextUtils.isEmpty(str)) {
                            this.a.P = an.a(this.a.o, this.a.o.getString(R.string.forwarfail), -1);
                            this.a.P.show();
                            return;
                        } else if (!"0".equals(str)) {
                            this.a.P = an.a(this.a.o, this.a.o.getString(R.string.forwarfail), -1);
                            this.a.P.show();
                            return;
                        } else {
                            return;
                        }
                    case 10:
                        str = (String) message.obj;
                        if (TextUtils.isEmpty((String) message.obj)) {
                            this.a.P = an.a(this.a.o, this.a.o.getString(R.string.bind_failed), -1);
                            this.a.P.show();
                            MobclickAgent.onEvent(this.a.o, "weibo_bind", "qzone_faild");
                        } else {
                            int parseInt2;
                            try {
                                parseInt2 = Integer.parseInt(str);
                            } catch (NumberFormatException e3) {
                                parseInt2 = i;
                            }
                            if (parseInt2 < 0) {
                                this.a.P = an.a(this.a.o, this.a.o.getString(R.string.bind_failed), -1);
                                this.a.P.show();
                                MobclickAgent.onEvent(this.a.o, "weibo_bind", "qzone_faild");
                            } else {
                                c = z.c(str);
                                if (c == null || c.isEmpty()) {
                                    MobclickAgent.onEvent(this.a.o, "weibo_bind", "qzone_faild");
                                } else {
                                    str2 = (String) c.get("result_msg");
                                    if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                        MobclickAgent.onEvent(this.a.o, "weibo_bind", "qzone_success");
                                        this.a.Z = (String) c.get("id");
                                        this.a.by.a(this.a.Z, c);
                                        ai.a(this.a.o, this.a.Z, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                    } else {
                                        an.a(this.a.o, str2, -1).show();
                                    }
                                }
                            }
                        }
                        this.a.u.dismiss();
                        return;
                    case 11:
                        str = (String) message.obj;
                        if (TextUtils.isEmpty(str)) {
                            this.a.P = an.a(this.a.o, this.a.o.getString(R.string.forwarfail), -1);
                            this.a.P.show();
                            return;
                        } else if (!"0".equals(str)) {
                            this.a.P = an.a(this.a.o, this.a.o.getString(R.string.forwarfail), -1);
                            this.a.P.show();
                            return;
                        } else {
                            return;
                        }
                    case 12:
                        Map u = z.u((String) message.obj);
                        if (u != null) {
                            if ("1".equals((String) u.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY))) {
                                if (this.a.aH) {
                                    RichPostDetail.a++;
                                }
                                if (this.a.bh != null) {
                                    this.a.bh.a(true, (String) u.get(SocialConstants.PARAM_APP_DESC));
                                }
                                this.a.a(true);
                                this.a.t.setSelection(2);
                                return;
                            } else if (this.a.bh != null) {
                                this.a.bh.a(false, (String) u.get(SocialConstants.PARAM_APP_DESC));
                                return;
                            } else {
                                return;
                            }
                        } else if (this.a.bh != null) {
                            this.a.bh.a(false, "");
                            return;
                        } else {
                            return;
                        }
                    case 13:
                        str = (String) message.obj;
                        if (TextUtils.isEmpty(str)) {
                            this.a.P = an.a(this.a.o, this.a.o.getString(R.string.commend_fail), -1);
                            this.a.P.show();
                        } else if ("0".equals(str)) {
                            if ("writeCommend".equals(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)) {
                                this.a.o.finish();
                            }
                            this.a.ah.a(this.a.W, true, (int) R.string.sendsuccess);
                            this.a.b(true);
                            return;
                        } else {
                            this.a.P = an.a(this.a.o, this.a.o.getString(R.string.commend_fail), -1);
                            this.a.P.show();
                        }
                        this.a.b(false);
                        return;
                    case 14:
                        this.a.finish();
                        return;
                    case 15:
                        Log.d(this.a.n, "case 15:");
                        this.a.bf = BottomStatus.KEYBOARD;
                        this.a.e();
                        return;
                    case 16:
                        Log.d(this.a.n, "case 16:");
                        this.a.F.setVisibility(0);
                        return;
                    case 17:
                        aq.a(this.a.o, R.color.record_voice_mask_bar_color);
                        return;
                    case 18:
                        this.a.C.setVisibility(0);
                        return;
                    case 816:
                        CharSequence string = ((Bundle) message.obj).getString(com.alipay.sdk.util.j.c);
                        if (TextUtils.isEmpty(string)) {
                            this.a.P = an.a(this.a.o, this.a.o.getString(R.string.forwarfail), -1);
                            this.a.P.show();
                            return;
                        } else if ("0".equals(string)) {
                            this.a.f.sendEmptyMessage(9);
                            return;
                        } else {
                            this.a.P = an.a(this.a.o, this.a.o.getString(R.string.forwarfail), -1);
                            this.a.P.show();
                            return;
                        }
                    case 817:
                        this.a.ah.a(((Integer) message.obj).intValue());
                        return;
                    case 930:
                        this.a.ah.a(this.a.W, false, (int) R.string.sendfail);
                        this.a.b(false);
                        return;
                    case 931:
                        this.a.ah.a(this.a.W);
                        if (((Boolean) message.obj).booleanValue() && !"writeCommend".equals(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)) {
                            return;
                        }
                        return;
                    case 970:
                        if (this.a.a != null && this.a.a.isShowing()) {
                            this.a.a.dismiss();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            e4.printStackTrace();
        }
    };
    private com.budejie.www.g.b bu;
    private com.budejie.www.http.f bv;
    private com.budejie.www.http.b bw;
    private com.budejie.www.c.b bx;
    private com.budejie.www.c.m by;
    private HashMap<String, String> bz;
    Map<String, View> c;
    al d;
    Reporter e;
    final Handler f = new Handler(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r7) {
            /*
            r6 = this;
            r2 = 13;
            r0 = r7.what;
            r1 = 4;
            if (r0 != r1) goto L_0x001d;
        L_0x0007:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.aw;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.aw;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.getLove();	 Catch:{ Exception -> 0x0034 }
            r1 = r1 + 1;
            r0.setLove(r1);	 Catch:{ Exception -> 0x0034 }
        L_0x001c:
            return;
        L_0x001d:
            r1 = 5;
            if (r0 != r1) goto L_0x0036;
        L_0x0020:
            r0 = r7.obj;	 Catch:{ Exception -> 0x0034 }
            r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r3 = "";
            r4 = 1;
            r5 = 1;
            r0 = android.app.ProgressDialog.show(r2, r3, r0, r4, r5);	 Catch:{ Exception -> 0x0034 }
            r1.bD = r0;	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x0034:
            r0 = move-exception;
            goto L_0x001c;
        L_0x0036:
            r1 = 6;
            if (r0 != r1) goto L_0x0043;
        L_0x0039:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.bD;	 Catch:{ Exception -> 0x0034 }
            r0.cancel();	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x0043:
            r1 = 7;
            if (r0 != r1) goto L_0x005a;
        L_0x0046:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = 2131230857; // 0x7f080089 float:1.8077779E38 double:1.05296795E-314;
            r1 = r1.getString(r2);	 Catch:{ Exception -> 0x0034 }
            r2 = -1;
            r0 = com.budejie.www.util.an.a(r0, r1, r2);	 Catch:{ Exception -> 0x0034 }
            r0.show();	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x005a:
            r1 = 9;
            if (r0 != r1) goto L_0x008f;
        L_0x005e:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.aw;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.getRepost();	 Catch:{ Exception -> 0x0034 }
            r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0034 }
            r0 = r0 + 1;
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.aw;	 Catch:{ Exception -> 0x0034 }
            r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x0034 }
            r1.setRepost(r0);	 Catch:{ Exception -> 0x0034 }
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.o;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.f;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.aw;	 Catch:{ Exception -> 0x0034 }
            com.budejie.www.util.m.a(r0, r1, r2);	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x008f:
            r1 = 91;
            if (r0 != r1) goto L_0x00b2;
        L_0x0093:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.aw;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.getRepost();	 Catch:{ Exception -> 0x0034 }
            r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0034 }
            r0 = r0 + 1;
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.aw;	 Catch:{ Exception -> 0x0034 }
            r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x0034 }
            r1.setRepost(r0);	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x00b2:
            r1 = 10;
            if (r0 != r1) goto L_0x00cb;
        L_0x00b6:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = 2131231067; // 0x7f08015b float:1.8078205E38 double:1.0529680536E-314;
            r1 = r1.getString(r2);	 Catch:{ Exception -> 0x0034 }
            r2 = -1;
            r0 = com.budejie.www.util.an.a(r0, r1, r2);	 Catch:{ Exception -> 0x0034 }
            r0.show();	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x00cb:
            r1 = 11;
            if (r0 != r1) goto L_0x0132;
        L_0x00cf:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = com.budejie.www.util.ai.b(r0);	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = com.budejie.www.util.an.j(r1);	 Catch:{ Exception -> 0x0034 }
            if (r1 == 0) goto L_0x0122;
        L_0x00dd:
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = com.budejie.www.util.an.k(r1);	 Catch:{ Exception -> 0x0034 }
            if (r1 == 0) goto L_0x0122;
        L_0x00e5:
            r1 = "";
            r1 = r0.equals(r1);	 Catch:{ Exception -> 0x0034 }
            if (r1 != 0) goto L_0x0122;
        L_0x00ed:
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = 0;
            com.budejie.www.util.an.a(r1, r2);	 Catch:{ Exception -> 0x0034 }
            r1 = 13;
            r6.sendEmptyMessage(r1);	 Catch:{ Exception -> 0x0034 }
        L_0x00f8:
            r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0034 }
            if (r0 != 0) goto L_0x001c;
        L_0x00fe:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = "add";
            r0.bC = r1;	 Catch:{ Exception -> 0x0034 }
            r0 = r7.obj;	 Catch:{ Exception -> 0x0034 }
            r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.bw;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.bC;	 Catch:{ Exception -> 0x0034 }
            r3 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r3 = com.budejie.www.util.ai.b(r3);	 Catch:{ Exception -> 0x0034 }
            r4 = 971; // 0x3cb float:1.36E-42 double:4.797E-321;
            r1.a(r2, r3, r0, r4);	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x0122:
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = 2131231069; // 0x7f08015d float:1.8078209E38 double:1.0529680545E-314;
            r3 = 2130838208; // 0x7f0202c0 float:1.7281392E38 double:1.0527739554E-314;
            r1 = com.budejie.www.util.an.a(r1, r2, r3);	 Catch:{ Exception -> 0x0034 }
            r1.show();	 Catch:{ Exception -> 0x0034 }
            goto L_0x00f8;
        L_0x0132:
            r1 = 12;
            if (r0 != r1) goto L_0x0147;
        L_0x0136:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = 2131231066; // 0x7f08015a float:1.8078203E38 double:1.052968053E-314;
            r2 = 2130838208; // 0x7f0202c0 float:1.7281392E38 double:1.0527739554E-314;
            r0 = com.budejie.www.util.an.a(r0, r1, r2);	 Catch:{ Exception -> 0x0034 }
            r0.show();	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x0147:
            r1 = 100001; // 0x186a1 float:1.40131E-40 double:4.9407E-319;
            if (r0 != r1) goto L_0x019f;
        L_0x014c:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.o;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.o;	 Catch:{ Exception -> 0x0034 }
            r2 = 2131231283; // 0x7f080233 float:1.8078643E38 double:1.0529681603E-314;
            r1 = r1.getString(r2);	 Catch:{ Exception -> 0x0034 }
            r2 = -1;
            r0 = com.budejie.www.util.an.a(r0, r1, r2);	 Catch:{ Exception -> 0x0034 }
            r0.show();	 Catch:{ Exception -> 0x0034 }
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.o;	 Catch:{ Exception -> 0x0034 }
            r0 = com.budejie.www.util.ai.b(r0);	 Catch:{ Exception -> 0x0034 }
            r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0034 }
            if (r0 != 0) goto L_0x001c;
        L_0x0177:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = "add";
            r0.bC = r1;	 Catch:{ Exception -> 0x0034 }
            r0 = r7.obj;	 Catch:{ Exception -> 0x0034 }
            r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.bw;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.bC;	 Catch:{ Exception -> 0x0034 }
            r3 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r3 = r3.o;	 Catch:{ Exception -> 0x0034 }
            r3 = com.budejie.www.util.ai.b(r3);	 Catch:{ Exception -> 0x0034 }
            r4 = 971; // 0x3cb float:1.36E-42 double:4.797E-321;
            r1.a(r2, r3, r0, r4);	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x019f:
            r1 = 829; // 0x33d float:1.162E-42 double:4.096E-321;
            if (r0 != r1) goto L_0x01ed;
        L_0x01a3:
            r0 = r7.obj;	 Catch:{ Exception -> 0x0034 }
            r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.bx;	 Catch:{ Exception -> 0x0034 }
            if (r1 == 0) goto L_0x01ba;
        L_0x01af:
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.bx;	 Catch:{ Exception -> 0x0034 }
            r2 = "collectTable";
            r1.a(r2, r0);	 Catch:{ Exception -> 0x0034 }
        L_0x01ba:
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r3 = 2131231155; // 0x7f0801b3 float:1.8078383E38 double:1.052968097E-314;
            r2 = r2.getString(r3);	 Catch:{ Exception -> 0x0034 }
            r3 = -1;
            r1 = com.budejie.www.util.an.a(r1, r2, r3);	 Catch:{ Exception -> 0x0034 }
            r1.show();	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = "delete";
            r1.bC = r2;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.bw;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.bC;	 Catch:{ Exception -> 0x0034 }
            r3 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r3 = com.budejie.www.util.ai.b(r3);	 Catch:{ Exception -> 0x0034 }
            r4 = 971; // 0x3cb float:1.36E-42 double:4.797E-321;
            r1.a(r2, r3, r0, r4);	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x01ed:
            if (r0 != r2) goto L_0x01fc;
        L_0x01ef:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.t;	 Catch:{ Exception -> 0x0034 }
            com.budejie.www.util.an.b(r0, r1);	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x01fc:
            r1 = 1004; // 0x3ec float:1.407E-42 double:4.96E-321;
            if (r0 != r1) goto L_0x0277;
        L_0x0200:
            r0 = r7.obj;	 Catch:{ Exception -> 0x0034 }
            r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0034 }
            r0 = com.budejie.www.util.z.k(r0);	 Catch:{ Exception -> 0x0034 }
            if (r0 == 0) goto L_0x0259;
        L_0x020a:
            r1 = "result_desc";
            r0 = r0.get(r1);	 Catch:{ Exception -> 0x0034 }
            r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0034 }
            r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0034 }
            if (r1 != 0) goto L_0x023b;
        L_0x0218:
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.o;	 Catch:{ Exception -> 0x0034 }
            r3 = -1;
            r0 = com.budejie.www.util.an.a(r2, r0, r3);	 Catch:{ Exception -> 0x0034 }
            r1.P = r0;	 Catch:{ Exception -> 0x0034 }
        L_0x0228:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.P;	 Catch:{ Exception -> 0x0034 }
            if (r0 == 0) goto L_0x001c;
        L_0x0230:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.P;	 Catch:{ Exception -> 0x0034 }
            r0.show();	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x023b:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.o;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.o;	 Catch:{ Exception -> 0x0034 }
            r3 = 2131231714; // 0x7f0803e2 float:1.8079517E38 double:1.052968373E-314;
            r2 = r2.getString(r3);	 Catch:{ Exception -> 0x0034 }
            r3 = -1;
            r1 = com.budejie.www.util.an.a(r1, r2, r3);	 Catch:{ Exception -> 0x0034 }
            r0.P = r1;	 Catch:{ Exception -> 0x0034 }
            goto L_0x0228;
        L_0x0259:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.o;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.o;	 Catch:{ Exception -> 0x0034 }
            r3 = 2131231714; // 0x7f0803e2 float:1.8079517E38 double:1.052968373E-314;
            r2 = r2.getString(r3);	 Catch:{ Exception -> 0x0034 }
            r3 = -1;
            r1 = com.budejie.www.util.an.a(r1, r2, r3);	 Catch:{ Exception -> 0x0034 }
            r0.P = r1;	 Catch:{ Exception -> 0x0034 }
            goto L_0x0228;
        L_0x0277:
            r1 = 100002; // 0x186a2 float:1.40133E-40 double:4.94076E-319;
            if (r0 == r1) goto L_0x001c;
        L_0x027c:
            r1 = 1006; // 0x3ee float:1.41E-42 double:4.97E-321;
            if (r0 != r1) goto L_0x029f;
        L_0x0280:
            r2 = new com.budejie.www.bean.SuggestedFollowsListItem;	 Catch:{ Exception -> 0x0034 }
            r2.<init>();	 Catch:{ Exception -> 0x0034 }
            r0 = r7.obj;	 Catch:{ Exception -> 0x0034 }
            r0 = (com.budejie.www.bean.ListItemObject) r0;	 Catch:{ Exception -> 0x0034 }
            r1 = r7.obj;	 Catch:{ Exception -> 0x0034 }
            r1 = (com.budejie.www.bean.ListItemObject) r1;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.getUid();	 Catch:{ Exception -> 0x0034 }
            r2.uid = r1;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r3 = new com.budejie.www.activity.CommendDetailOld$3$1;	 Catch:{ Exception -> 0x0034 }
            r3.<init>(r6, r0, r2);	 Catch:{ Exception -> 0x0034 }
            com.budejie.www.util.u.a(r2, r1, r3);	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x029f:
            r1 = 1003; // 0x3eb float:1.406E-42 double:4.955E-321;
            if (r0 != r1) goto L_0x0329;
        L_0x02a3:
            r0 = r7.obj;	 Catch:{ Exception -> 0x0034 }
            r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0034 }
            r1 = com.budejie.www.util.z.s(r0);	 Catch:{ Exception -> 0x0034 }
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.aN;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.isShowing();	 Catch:{ Exception -> 0x0034 }
            if (r0 == 0) goto L_0x02c0;
        L_0x02b7:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.aN;	 Catch:{ Exception -> 0x0034 }
            r0.dismiss();	 Catch:{ Exception -> 0x0034 }
        L_0x02c0:
            if (r1 == 0) goto L_0x0310;
        L_0x02c2:
            r0 = r1.getMsg();	 Catch:{ Exception -> 0x0034 }
            r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0034 }
            if (r2 != 0) goto L_0x02f7;
        L_0x02cc:
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.o;	 Catch:{ Exception -> 0x0034 }
            r3 = -1;
            r0 = com.budejie.www.util.an.a(r2, r0, r3);	 Catch:{ Exception -> 0x0034 }
        L_0x02d7:
            r2 = "1";
            r1 = r1.getCode();	 Catch:{ Exception -> 0x0034 }
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0034 }
            if (r1 == 0) goto L_0x02e3;
        L_0x02e3:
            if (r0 == 0) goto L_0x02e8;
        L_0x02e5:
            r0.show();	 Catch:{ Exception -> 0x0034 }
        L_0x02e8:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.o;	 Catch:{ Exception -> 0x0034 }
            r1 = "E05-A05";
            r2 = "帖子置顶";
            com.umeng.analytics.MobclickAgent.onEvent(r0, r1, r2);	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x02f7:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.o;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.o;	 Catch:{ Exception -> 0x0034 }
            r3 = 2131231714; // 0x7f0803e2 float:1.8079517E38 double:1.052968373E-314;
            r2 = r2.getString(r3);	 Catch:{ Exception -> 0x0034 }
            r3 = -1;
            r0 = com.budejie.www.util.an.a(r0, r2, r3);	 Catch:{ Exception -> 0x0034 }
            goto L_0x02d7;
        L_0x0310:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.o;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.o;	 Catch:{ Exception -> 0x0034 }
            r2 = 2131231714; // 0x7f0803e2 float:1.8079517E38 double:1.052968373E-314;
            r1 = r1.getString(r2);	 Catch:{ Exception -> 0x0034 }
            r2 = -1;
            r0 = com.budejie.www.util.an.a(r0, r1, r2);	 Catch:{ Exception -> 0x0034 }
            goto L_0x02e3;
        L_0x0329:
            r1 = 1004; // 0x3ec float:1.407E-42 double:4.96E-321;
            if (r0 != r1) goto L_0x001c;
        L_0x032d:
            r0 = r7.obj;	 Catch:{ Exception -> 0x0034 }
            r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0034 }
            r1 = com.budejie.www.util.z.q(r0);	 Catch:{ Exception -> 0x0034 }
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.aN;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.isShowing();	 Catch:{ Exception -> 0x0034 }
            if (r0 == 0) goto L_0x034a;
        L_0x0341:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.aN;	 Catch:{ Exception -> 0x0034 }
            r0.dismiss();	 Catch:{ Exception -> 0x0034 }
        L_0x034a:
            if (r1 == 0) goto L_0x0393;
        L_0x034c:
            r0 = "result_desc";
            r0 = r1.get(r0);	 Catch:{ Exception -> 0x0034 }
            r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0034 }
            r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0034 }
            if (r2 != 0) goto L_0x037a;
        L_0x035a:
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.o;	 Catch:{ Exception -> 0x0034 }
            r3 = -1;
            r0 = com.budejie.www.util.an.a(r2, r0, r3);	 Catch:{ Exception -> 0x0034 }
        L_0x0365:
            r2 = "0";
            r3 = "result";
            r1 = r1.get(r3);	 Catch:{ Exception -> 0x0034 }
            r1 = r2.equals(r1);	 Catch:{ Exception -> 0x0034 }
            if (r1 == 0) goto L_0x0373;
        L_0x0373:
            if (r0 == 0) goto L_0x001c;
        L_0x0375:
            r0.show();	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x037a:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.o;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.o;	 Catch:{ Exception -> 0x0034 }
            r3 = 2131231714; // 0x7f0803e2 float:1.8079517E38 double:1.052968373E-314;
            r2 = r2.getString(r3);	 Catch:{ Exception -> 0x0034 }
            r3 = -1;
            r0 = com.budejie.www.util.an.a(r0, r2, r3);	 Catch:{ Exception -> 0x0034 }
            goto L_0x0365;
        L_0x0393:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.o;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.o;	 Catch:{ Exception -> 0x0034 }
            r2 = 2131231714; // 0x7f0803e2 float:1.8079517E38 double:1.052968373E-314;
            r1 = r1.getString(r2);	 Catch:{ Exception -> 0x0034 }
            r2 = -1;
            r0 = com.budejie.www.util.an.a(r0, r1, r2);	 Catch:{ Exception -> 0x0034 }
            goto L_0x0373;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.activity.CommendDetailOld.3.handleMessage(android.os.Message):void");
        }
    };
    net.tsz.afinal.a.a<String> g = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.P = an.a(this.a.o, this.a.getString(R.string.operate_fail), -1);
            this.a.P.show();
        }

        public void a(String str) {
            try {
                HashMap k = z.k(str);
                if (k != null) {
                    String str2 = (String) k.get("result_desc");
                    if (TextUtils.isEmpty(str2)) {
                        this.a.P = an.a(this.a.o, this.a.getString(R.string.operate_fail), -1);
                    } else {
                        this.a.P = an.a(this.a.o, str2, -1);
                    }
                } else {
                    this.a.P = an.a(this.a.o, this.a.getString(R.string.operate_fail), -1);
                }
                if (this.a.P != null) {
                    this.a.P.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    net.tsz.afinal.a.a<String> h = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.P = an.a(this.a.o, this.a.getString(R.string.operate_fail), -1);
            this.a.P.show();
        }

        public void onStart() {
            super.onStart();
            if (this.a.aN != null) {
                this.a.aN.show();
            }
        }

        public void a(String str) {
            try {
                if (this.a.aN.isShowing()) {
                    this.a.aN.dismiss();
                }
                if (RoomActivity.VIDEOTYPE_UNKNOWN.equals(str)) {
                    this.a.P = an.a(this.a.o, this.a.getString(R.string.operate_fail), -1);
                } else {
                    this.a.P = an.a(this.a.o, this.a.getString(R.string.operate_success), -1);
                    this.a.a(false);
                    this.a.t.setSelection(this.a.aQ);
                }
                if (this.a.P != null) {
                    this.a.P.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    OnItemClickListener i = new OnItemClickListener(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            CommentItem commentItem = (CommentItem) adapterView.getItemAtPosition(i);
            if (commentItem != null && !this.a.ab) {
                this.a.a(i, commentItem, view);
            }
        }
    };
    TextWatcher j = new TextWatcher(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(this.a.N.getText())) {
                this.a.M.setTextColor(-1);
                this.a.M.setText("" + this.a.X);
                return;
            }
            int ak = this.a.X - this.a.N.getText().toString().trim().length();
            if (ak < 0) {
                this.a.M.setTextColor(SupportMenu.CATEGORY_MASK);
            } else {
                this.a.M.setTextColor(-16777216);
            }
            this.a.M.setText("" + ak);
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    };
    TextWatcher k = new TextWatcher(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    };
    com.budejie.www.activity.image.BitmapCache.a l = new com.budejie.www.activity.image.BitmapCache.a(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public void a(ImageView imageView, Bitmap bitmap, Object... objArr) {
            if (imageView == null || bitmap == null) {
                Log.e(this.a.n, "callback, bmp null");
                return;
            }
            String str = (String) objArr[0];
            if (str == null || !str.equals((String) imageView.getTag())) {
                Log.e(this.a.n, "callback, bmp not match");
            } else {
                imageView.setImageBitmap(bitmap);
            }
        }
    };
    OnClickListener m = new OnClickListener(this) {
        final /* synthetic */ CommendDetailOld a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (this.a.aX != null) {
                this.a.aX.setVisibility(8);
            }
        }
    };
    private String n = "CommendDetail";
    private CommendDetailOld o;
    private Button p;
    private TextView q;
    private LinearLayout r;
    private RelativeLayout s;
    private CustomListView t;
    private Dialog u;
    private b v;
    private RelativeLayout w;
    private ImageView x;
    private ImageView y;
    private ParseTagEditText z;

    public enum BottomStatus {
        NORMAL,
        KEYBOARD,
        VOICE,
        OPERATE
    }

    class a implements OnClickListener {
        Dialog a;
        final /* synthetic */ CommendDetailOld b;

        public a(CommendDetailOld commendDetailOld, Dialog dialog) {
            this.b = commendDetailOld;
            this.a = dialog;
        }

        public void onClick(View view) {
            this.a.dismiss();
            this.b.e("1");
        }
    }

    private class b extends BaseAdapter {
        CommentItem a;
        DecimalFormat b = new DecimalFormat("##.0");
        DecimalFormat c = new DecimalFormat("##.00");
        final /* synthetic */ CommendDetailOld d;

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public b(CommendDetailOld commendDetailOld) {
            this.d = commendDetailOld;
        }

        public int getCount() {
            return this.d.aj.size();
        }

        public CommentItem a(int i) {
            return (CommentItem) this.d.aj.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int getItemViewType(int i) {
            CommentItem a = a(i);
            if (a.isTagIsShow() && a.isIsnew()) {
                return 1;
            }
            return 0;
        }

        public int getViewTypeCount() {
            return 2;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            CommendDetailOld$b$b commendDetailOld$b$b;
            String str;
            int i2;
            CharSequence charSequence;
            NumberFormatException e;
            if (view == null) {
                CommendDetailOld$b$b commendDetailOld$b$b2 = new CommendDetailOld$b$b(this, null);
                view = this.d.o.getLayoutInflater().inflate(R.layout.comment_item, null);
                commendDetailOld$b$b2.a = (AsyncImageView) view.findViewById(R.id.thume_img);
                commendDetailOld$b$b2.b = (TextView) view.findViewById(R.id.user_name);
                commendDetailOld$b$b2.c = (AsyncImageView) view.findViewById(R.id.iv_members_mark);
                commendDetailOld$b$b2.e = (ImageView) view.findViewById(R.id.user_sex);
                commendDetailOld$b$b2.d = (NewParseTagEditText) view.findViewById(R.id.cmtContent);
                commendDetailOld$b$b2.q = (LinearLayout) view.findViewById(R.id.comment_title_layout);
                commendDetailOld$b$b2.f = (TextView) view.findViewById(R.id.cmtTagTv);
                commendDetailOld$b$b2.g = (TextView) view.findViewById(R.id.commentLikeCount);
                commendDetailOld$b$b2.h = (FrameLayout) view.findViewById(R.id.commentDingLayout);
                commendDetailOld$b$b2.i = (ImageView) view.findViewById(R.id.commentDingIv);
                commendDetailOld$b$b2.j = (TextView) view.findViewById(R.id.commentCaiCount);
                commendDetailOld$b$b2.k = (FrameLayout) view.findViewById(R.id.commentCaiLayout);
                commendDetailOld$b$b2.l = (ImageView) view.findViewById(R.id.commentCaiIv);
                commendDetailOld$b$b2.m = (ImageView) view.findViewById(R.id.commend_listview_divider);
                commendDetailOld$b$b2.n = (AudioLayout) view.findViewById(R.id.cmtVoice);
                commendDetailOld$b$b2.o = (NewParseTagEditText) view.findViewById(R.id.replyUser);
                commendDetailOld$b$b2.p = (ProgressBar) view.findViewById(R.id.pb_cmtSending);
                commendDetailOld$b$b2.r = (TextView) view.findViewById(R.id.cmtLikeCount);
                commendDetailOld$b$b2.s = (LinearLayout) view.findViewById(R.id.ListCommentLayout);
                commendDetailOld$b$b2.t = (RelativeLayout) view.findViewById(R.id.HotMoreLayout);
                commendDetailOld$b$b2.u = (ImageView) view.findViewById(R.id.HotMoreArrow);
                commendDetailOld$b$b2.v = (ProgressBar) view.findViewById(R.id.HotMoreProgressBar);
                commendDetailOld$b$b2.w = (RelativeLayout) view.findViewById(R.id.ReplyImageLayout);
                commendDetailOld$b$b2.x = (AsyncImageView) view.findViewById(R.id.ItemImageView);
                commendDetailOld$b$b2.y = (ImageView) view.findViewById(R.id.PlayVideoImageView);
                commendDetailOld$b$b2.z = (ImageView) view.findViewById(R.id.GifIconImageView);
                commendDetailOld$b$b2.A = (VoteView) view.findViewById(R.id.comment_vote_view);
                commendDetailOld$b$b2.B = (LinearLayout) view.findViewById(R.id.pub_view);
                commendDetailOld$b$b2.C = (TextView) view.findViewById(R.id.pub_content_view);
                commendDetailOld$b$b2.D = view.findViewById(R.id.author_replay_mark);
                commendDetailOld$b$b2.E = (TextView) view.findViewById(R.id.author_replay_mark_content);
                commendDetailOld$b$b2.F = (LinearLayout) view.findViewById(R.id.hot_author_replay);
                commendDetailOld$b$b2.G = (LinearLayout) view.findViewById(R.id.comment_user_name_layout);
                commendDetailOld$b$b2.H = (ImageView) view.findViewById(R.id.hot_author_replay_divider);
                commendDetailOld$b$b2.I = (RelativeLayout) view.findViewById(R.id.commend_content_layout);
                commendDetailOld$b$b2.J = (LinearLayout) view.findViewById(R.id.ll_middle_content_layout);
                view.setTag(R.layout.comment_item, commendDetailOld$b$b2);
                if (getItemViewType(i) == 1 && this.d.aP && this.d.aO != null) {
                    try {
                        ((LinearLayout) view.findViewById(R.id.ad_layout)).addView(this.d.aO, new LayoutParams(-1, -2));
                    } catch (Exception e2) {
                    }
                    if (!(this.d.e == null || this.d.bs)) {
                        this.d.bs = true;
                        this.d.e.onExposured(this.d.aO);
                    }
                    commendDetailOld$b$b = commendDetailOld$b$b2;
                } else {
                    commendDetailOld$b$b = commendDetailOld$b$b2;
                }
            } else {
                commendDetailOld$b$b = (CommendDetailOld$b$b) view.getTag(R.layout.comment_item);
            }
            commendDetailOld$b$b.q.setFocusable(false);
            commendDetailOld$b$b.m.setVisibility(0);
            this.a = (CommentItem) this.d.aj.get(i);
            commendDetailOld$b$b.w.setVisibility(8);
            commendDetailOld$b$b.y.setVisibility(8);
            commendDetailOld$b$b.z.setVisibility(8);
            commendDetailOld$b$b.n.setVisibility(8);
            commendDetailOld$b$b.B.setVisibility(8);
            String type = this.a.getType();
            if (type == null || (type.equals("") | type.equals("null")) != 0) {
                type = "unknown";
            }
            if (this.a.isPub()) {
                str = "pub";
            } else {
                str = type;
            }
            if (!str.equals("text")) {
                if (str.equals(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY)) {
                    commendDetailOld$b$b.w.setVisibility(0);
                    commendDetailOld$b$b.w.setOnClickListener(new g(this.d, this.a));
                    com.budejie.www.adapter.b.a.c(commendDetailOld$b$b.x, this.a.getImageWidth(), this.a.getImageHeight());
                    commendDetailOld$b$b.x.setAsyncCacheImage(this.a.getImageThumbUrl(), R.drawable.likelist_defaut_bg);
                } else if (str.equals("gif")) {
                    commendDetailOld$b$b.z.setVisibility(0);
                    commendDetailOld$b$b.w.setVisibility(0);
                    commendDetailOld$b$b.w.setOnClickListener(new g(this.d, this.a));
                    com.budejie.www.adapter.b.a.c(commendDetailOld$b$b.x, this.a.getGifWidth(), this.a.getGifHeight());
                    commendDetailOld$b$b.x.setAsyncCacheImage(this.a.getGifThumbUrl(), R.drawable.likelist_defaut_bg);
                } else if (str.equals("audio")) {
                    commendDetailOld$b$b.n.setVisibility(0);
                    commendDetailOld$b$b.n.c();
                    commendDetailOld$b$b.n.setPlayPath(this.a.getAudioUrl());
                    commendDetailOld$b$b.n.setAudioTime("" + this.a.getAudioDuration());
                    if (ac.a(this.d.o).c()) {
                        type = ac.a(this.d.o).m();
                        if (type == null || !type.equals(this.a.getVoiceuri())) {
                            commendDetailOld$b$b.n.c();
                        } else {
                            commendDetailOld$b$b.n.d();
                        }
                    } else {
                        commendDetailOld$b$b.n.c();
                    }
                } else if (str.equals("video")) {
                    commendDetailOld$b$b.w.setVisibility(0);
                    commendDetailOld$b$b.w.setOnClickListener(new g(this.d, this.a));
                    commendDetailOld$b$b.y.setVisibility(0);
                    com.budejie.www.adapter.b.a.c(commendDetailOld$b$b.x, 0, 0);
                    commendDetailOld$b$b.x.setAsyncCacheImage(this.a.getVideoThumbnail(), R.drawable.likelist_defaut_bg);
                } else if (str.equals("pub")) {
                    commendDetailOld$b$b.B.setVisibility(0);
                    commendDetailOld$b$b.C.setText(this.a.getmVideoTime() + "\" 神配音");
                    commendDetailOld$b$b.B.setOnClickListener(new h(this.d, this.a));
                }
            }
            VoteData voteData = this.a.getVoteData();
            if (voteData == null || voteData.votes == null || voteData.votes.size() <= 0) {
                commendDetailOld$b$b.A.setVisibility(8);
            } else {
                commendDetailOld$b$b.A.a();
                commendDetailOld$b$b.A.setVisibility(0);
                commendDetailOld$b$b.A.setVoteData(voteData);
            }
            if (this.a.getHotNp() == null || this.a.getHotNp().equals("null")) {
                commendDetailOld$b$b.t.setVisibility(8);
            } else {
                commendDetailOld$b$b.t.setVisibility(0);
                commendDetailOld$b$b.u.setVisibility(0);
                commendDetailOld$b$b.v.setVisibility(8);
                commendDetailOld$b$b.t.setOnClickListener(new CommendDetailOld$b$1(this, commendDetailOld$b$b));
            }
            if (this.a.getReplyList() == null || this.a.getReplyList().size() <= 0) {
                commendDetailOld$b$b.s.setVisibility(8);
                commendDetailOld$b$b.D.setVisibility(8);
                commendDetailOld$b$b.E.setVisibility(8);
            } else {
                a(commendDetailOld$b$b, this.a.getReplyList(), i);
                if (this.a.getUid().equals(this.a.getAuthorUid())) {
                    commendDetailOld$b$b.D.setVisibility(0);
                    commendDetailOld$b$b.E.setVisibility(0);
                } else {
                    commendDetailOld$b$b.D.setVisibility(8);
                    commendDetailOld$b$b.E.setVisibility(8);
                }
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) commendDetailOld$b$b.I.getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) commendDetailOld$b$b.J.getLayoutParams();
            if (this.a.isHotAuthorReplay()) {
                commendDetailOld$b$b.F.setVisibility(0);
                commendDetailOld$b$b.G.setVisibility(8);
                commendDetailOld$b$b.a.setVisibility(8);
                commendDetailOld$b$b.r.setVisibility(8);
                commendDetailOld$b$b.m.setVisibility(8);
                if (this.a.isHotAuthorReplayFirst()) {
                    commendDetailOld$b$b.H.setVisibility(0);
                    layoutParams.setMargins(layoutParams.leftMargin, an.a(this.d.o, 10), layoutParams.rightMargin, layoutParams.bottomMargin);
                    commendDetailOld$b$b.I.setLayoutParams(layoutParams);
                } else {
                    commendDetailOld$b$b.H.setVisibility(8);
                    layoutParams.setMargins(layoutParams.leftMargin, -an.a(this.d.o, 5), layoutParams.rightMargin, layoutParams.bottomMargin);
                    commendDetailOld$b$b.I.setLayoutParams(layoutParams);
                }
                layoutParams2.setMargins(an.a(this.d.o, 21), layoutParams2.topMargin, layoutParams2.rightMargin, layoutParams2.bottomMargin);
                commendDetailOld$b$b.J.setLayoutParams(layoutParams2);
            } else {
                layoutParams.setMargins(layoutParams.leftMargin, an.a(this.d.o, 10), layoutParams.rightMargin, layoutParams.bottomMargin);
                commendDetailOld$b$b.I.setLayoutParams(layoutParams);
                layoutParams2.setMargins(an.a(this.d.o, 12), layoutParams2.topMargin, layoutParams2.rightMargin, layoutParams2.bottomMargin);
                commendDetailOld$b$b.J.setLayoutParams(layoutParams2);
                commendDetailOld$b$b.F.setVisibility(8);
                commendDetailOld$b$b.G.setVisibility(0);
                commendDetailOld$b$b.a.setVisibility(0);
                commendDetailOld$b$b.H.setVisibility(8);
                commendDetailOld$b$b.r.setVisibility(0);
                commendDetailOld$b$b.m.setVisibility(0);
                commendDetailOld$b$b.b.setText(this.a.getUname());
                if (TextUtils.isEmpty(this.a.getIs_vip()) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equalsIgnoreCase(this.a.getIs_vip())) {
                    commendDetailOld$b$b.b.setTextColor(this.d.getResources().getColor(com.budejie.www.util.j.F));
                    commendDetailOld$b$b.c.setVisibility(8);
                } else {
                    commendDetailOld$b$b.b.setTextColor(this.d.o.getResources().getColor(com.budejie.www.util.j.H));
                    try {
                        commendDetailOld$b$b.c.setVisibility(0);
                        Drawable gifDrawable = new GifDrawable(this.d.o.getResources(), com.budejie.www.util.j.I);
                        commendDetailOld$b$b.c.setImageDrawable(gifDrawable);
                        gifDrawable.start();
                    } catch (NotFoundException e3) {
                        e3.printStackTrace();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                if ("m".equals(this.a.getSex())) {
                    commendDetailOld$b$b.e.setBackgroundResource(com.budejie.www.util.j.bs);
                } else {
                    commendDetailOld$b$b.e.setBackgroundResource(com.budejie.www.util.j.br);
                }
                type = this.a.getProfile();
                if (SensorBaseActivity.portraitShow) {
                    commendDetailOld$b$b.a.setVisibility(8);
                } else {
                    commendDetailOld$b$b.a.setVisibility(0);
                    commendDetailOld$b$b.a.setPostAvatarImage(type);
                }
                Bundle bundle = new Bundle();
                bundle.putString(PersonalProfileActivity.c, this.a.getUid());
                bundle.putBoolean(PersonalProfileActivity.f, true);
                commendDetailOld$b$b.a.setOnClickListener(this.d.bu.a(7, bundle));
                commendDetailOld$b$b.b.setOnClickListener(this.d.bu.a(7, bundle));
                commendDetailOld$b$b.c.setOnClickListener(this.d.bE);
                view.setTag(R.layout.cmt_pop_item, this.a);
                i2 = 0;
                charSequence = "0";
                try {
                    i2 = Integer.parseInt(this.a.getCmtLikeCount());
                } catch (NumberFormatException e5) {
                }
                if (i2 < 1000) {
                    charSequence = "" + i2;
                    commendDetailOld$b$b.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like1_bg));
                } else if (i2 < 10000) {
                    charSequence = "" + this.c.format(((double) i2) / 1000.0d) + "k";
                    commendDetailOld$b$b.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like2_bg));
                } else if (i2 < 100000) {
                    charSequence = "" + this.b.format(((double) i2) / 1000.0d) + "k";
                    commendDetailOld$b$b.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like3_bg));
                } else if (i2 < 1000000) {
                    charSequence = "" + (i2 / 1000) + "k";
                    commendDetailOld$b$b.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
                } else if (1000000 < i2 && i2 < 10000000) {
                    charSequence = "" + this.c.format(((double) i2) / 1000000.0d) + "m";
                    commendDetailOld$b$b.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
                } else if (i2 > 10000000 && i2 < 100000000) {
                    charSequence = "" + this.b.format(((double) i2) / 1000000.0d) + "m";
                    commendDetailOld$b$b.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
                } else if (100000000 < i2) {
                    charSequence = "" + (i2 / 1000000) + "m";
                    commendDetailOld$b$b.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
                }
                commendDetailOld$b$b.r.setText(charSequence);
            }
            if (this.a.isTagIsShow()) {
                commendDetailOld$b$b.q.setVisibility(0);
                commendDetailOld$b$b.f.setBackgroundResource(com.budejie.www.util.j.r);
                commendDetailOld$b$b.m.setVisibility(8);
                commendDetailOld$b$b.H.setVisibility(8);
                if (this.a.isIshot()) {
                    commendDetailOld$b$b.f.setText(R.string.hot_comment);
                } else {
                    commendDetailOld$b$b.f.setText(R.string.new_comment);
                }
                commendDetailOld$b$b.q.setOnClickListener(new CommendDetailOld$b$2(this));
                if (ai.a(this.d.o) == 1) {
                    commendDetailOld$b$b.q.setPadding(0, 0, 0, 0);
                } else {
                    commendDetailOld$b$b.q.setPadding(an.a(this.d.o, 1), 0, an.a(this.d.o, 1), 0);
                }
            } else {
                commendDetailOld$b$b.q.setVisibility(8);
            }
            String dingCount = this.a.getDingCount();
            String caiCount = this.a.getCaiCount();
            int i3 = 0;
            try {
                i2 = Integer.parseInt(dingCount);
                try {
                    i3 = Integer.parseInt(caiCount);
                } catch (NumberFormatException e6) {
                    e = e6;
                    Log.i("Commend-dingCount1", e.toString());
                    commendDetailOld$b$b.g.setText(i2 <= 0 ? i2 + "" : "");
                    commendDetailOld$b$b.j.setText(i3 <= 0 ? i3 + "" : "");
                    type = this.a.getDingOrCai();
                    if (!"like".equals(type)) {
                        commendDetailOld$b$b.i.setSelected(true);
                        commendDetailOld$b$b.g.setSelected(true);
                        commendDetailOld$b$b.l.setSelected(false);
                        commendDetailOld$b$b.j.setSelected(false);
                    } else if ("hate".equals(type)) {
                        commendDetailOld$b$b.i.setSelected(false);
                        commendDetailOld$b$b.g.setSelected(false);
                        commendDetailOld$b$b.l.setSelected(false);
                        commendDetailOld$b$b.j.setSelected(false);
                    } else {
                        commendDetailOld$b$b.i.setSelected(false);
                        commendDetailOld$b$b.g.setSelected(false);
                        commendDetailOld$b$b.l.setSelected(true);
                        commendDetailOld$b$b.j.setSelected(true);
                    }
                    commendDetailOld$b$b.h.setTag(Integer.valueOf(i));
                    commendDetailOld$b$b.h.setOnClickListener(new f(this.d, this.a, view));
                    commendDetailOld$b$b.k.setTag(Integer.valueOf(i));
                    commendDetailOld$b$b.k.setOnClickListener(new e(this.d, this.a, view));
                    charSequence = "";
                    if (this.a.getMpreName().length() > 0) {
                        commendDetailOld$b$b.o.setVisibility(8);
                    } else {
                        charSequence = "//@" + this.a.getMpreName() + ":";
                        if (!TextUtils.isEmpty(this.a.getMpreContent())) {
                            charSequence = charSequence + this.a.getMpreContent();
                        }
                        commendDetailOld$b$b.o.setVisibility(0);
                        ParseTagTextView.a(this.d.getApplicationContext(), commendDetailOld$b$b.o, charSequence);
                    }
                    if (!TextUtils.isEmpty(this.a.getVoiceuri())) {
                        if (TextUtils.isEmpty(this.a.getContent())) {
                            commendDetailOld$b$b.o.setVisibility(0);
                            ParseTagTextView.a(this.d.getApplicationContext(), commendDetailOld$b$b.o, charSequence + " " + this.a.getContent());
                        } else if (TextUtils.isEmpty(charSequence)) {
                            commendDetailOld$b$b.o.setVisibility(8);
                        }
                    }
                    if (this.a.getState() != -1) {
                        commendDetailOld$b$b.p.setVisibility(0);
                        commendDetailOld$b$b.d.setVisibility(8);
                    } else {
                        commendDetailOld$b$b.p.setVisibility(8);
                        charSequence = this.a.getContent();
                        if (charSequence != null) {
                        }
                        commendDetailOld$b$b.d.setVisibility(8);
                    }
                    return view;
                }
            } catch (NumberFormatException e7) {
                NumberFormatException numberFormatException = e7;
                i2 = 0;
                e = numberFormatException;
                Log.i("Commend-dingCount1", e.toString());
                if (i2 <= 0) {
                }
                commendDetailOld$b$b.g.setText(i2 <= 0 ? i2 + "" : "");
                if (i3 <= 0) {
                }
                commendDetailOld$b$b.j.setText(i3 <= 0 ? i3 + "" : "");
                type = this.a.getDingOrCai();
                if (!"like".equals(type)) {
                    commendDetailOld$b$b.i.setSelected(true);
                    commendDetailOld$b$b.g.setSelected(true);
                    commendDetailOld$b$b.l.setSelected(false);
                    commendDetailOld$b$b.j.setSelected(false);
                } else if ("hate".equals(type)) {
                    commendDetailOld$b$b.i.setSelected(false);
                    commendDetailOld$b$b.g.setSelected(false);
                    commendDetailOld$b$b.l.setSelected(true);
                    commendDetailOld$b$b.j.setSelected(true);
                } else {
                    commendDetailOld$b$b.i.setSelected(false);
                    commendDetailOld$b$b.g.setSelected(false);
                    commendDetailOld$b$b.l.setSelected(false);
                    commendDetailOld$b$b.j.setSelected(false);
                }
                commendDetailOld$b$b.h.setTag(Integer.valueOf(i));
                commendDetailOld$b$b.h.setOnClickListener(new f(this.d, this.a, view));
                commendDetailOld$b$b.k.setTag(Integer.valueOf(i));
                commendDetailOld$b$b.k.setOnClickListener(new e(this.d, this.a, view));
                charSequence = "";
                if (this.a.getMpreName().length() > 0) {
                    charSequence = "//@" + this.a.getMpreName() + ":";
                    if (TextUtils.isEmpty(this.a.getMpreContent())) {
                        charSequence = charSequence + this.a.getMpreContent();
                    }
                    commendDetailOld$b$b.o.setVisibility(0);
                    ParseTagTextView.a(this.d.getApplicationContext(), commendDetailOld$b$b.o, charSequence);
                } else {
                    commendDetailOld$b$b.o.setVisibility(8);
                }
                if (TextUtils.isEmpty(this.a.getVoiceuri())) {
                    if (TextUtils.isEmpty(this.a.getContent())) {
                        commendDetailOld$b$b.o.setVisibility(0);
                        ParseTagTextView.a(this.d.getApplicationContext(), commendDetailOld$b$b.o, charSequence + " " + this.a.getContent());
                    } else if (TextUtils.isEmpty(charSequence)) {
                        commendDetailOld$b$b.o.setVisibility(8);
                    }
                }
                if (this.a.getState() != -1) {
                    commendDetailOld$b$b.p.setVisibility(8);
                    charSequence = this.a.getContent();
                    if (charSequence != null) {
                    }
                    commendDetailOld$b$b.d.setVisibility(8);
                } else {
                    commendDetailOld$b$b.p.setVisibility(0);
                    commendDetailOld$b$b.d.setVisibility(8);
                }
                return view;
            }
            if (i2 <= 0) {
            }
            commendDetailOld$b$b.g.setText(i2 <= 0 ? i2 + "" : "");
            if (i3 <= 0) {
            }
            commendDetailOld$b$b.j.setText(i3 <= 0 ? i3 + "" : "");
            type = this.a.getDingOrCai();
            if (!"like".equals(type)) {
                commendDetailOld$b$b.i.setSelected(true);
                commendDetailOld$b$b.g.setSelected(true);
                commendDetailOld$b$b.l.setSelected(false);
                commendDetailOld$b$b.j.setSelected(false);
            } else if ("hate".equals(type)) {
                commendDetailOld$b$b.i.setSelected(false);
                commendDetailOld$b$b.g.setSelected(false);
                commendDetailOld$b$b.l.setSelected(true);
                commendDetailOld$b$b.j.setSelected(true);
            } else {
                commendDetailOld$b$b.i.setSelected(false);
                commendDetailOld$b$b.g.setSelected(false);
                commendDetailOld$b$b.l.setSelected(false);
                commendDetailOld$b$b.j.setSelected(false);
            }
            commendDetailOld$b$b.h.setTag(Integer.valueOf(i));
            commendDetailOld$b$b.h.setOnClickListener(new f(this.d, this.a, view));
            commendDetailOld$b$b.k.setTag(Integer.valueOf(i));
            commendDetailOld$b$b.k.setOnClickListener(new e(this.d, this.a, view));
            charSequence = "";
            if (this.a.getMpreName().length() > 0) {
                charSequence = "//@" + this.a.getMpreName() + ":";
                if (TextUtils.isEmpty(this.a.getMpreContent())) {
                    charSequence = charSequence + this.a.getMpreContent();
                }
                commendDetailOld$b$b.o.setVisibility(0);
                ParseTagTextView.a(this.d.getApplicationContext(), commendDetailOld$b$b.o, charSequence);
            } else {
                commendDetailOld$b$b.o.setVisibility(8);
            }
            if (TextUtils.isEmpty(this.a.getVoiceuri())) {
                if (TextUtils.isEmpty(this.a.getContent())) {
                    commendDetailOld$b$b.o.setVisibility(0);
                    ParseTagTextView.a(this.d.getApplicationContext(), commendDetailOld$b$b.o, charSequence + " " + this.a.getContent());
                } else if (TextUtils.isEmpty(charSequence)) {
                    commendDetailOld$b$b.o.setVisibility(8);
                }
            }
            if (this.a.getState() != -1) {
                commendDetailOld$b$b.p.setVisibility(8);
                charSequence = this.a.getContent();
                if (charSequence != null || "".equals(charSequence) || str.equals("audio")) {
                    commendDetailOld$b$b.d.setVisibility(8);
                } else {
                    commendDetailOld$b$b.d.setVisibility(0);
                    commendDetailOld$b$b.d.setText(charSequence);
                    commendDetailOld$b$b.d.setOnClickListener(new CommendDetailOld$b$3(this, i, view));
                }
            } else {
                commendDetailOld$b$b.p.setVisibility(0);
                commendDetailOld$b$b.d.setVisibility(8);
            }
            return view;
        }

        private void a(CommendDetailOld$b$b commendDetailOld$b$b, ArrayList<CommentItem> arrayList, int i) {
            NumberFormatException e;
            View view = null;
            commendDetailOld$b$b.s.setVisibility(0);
            commendDetailOld$b$b.s.removeAllViews();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                View view2;
                Object obj;
                CommentItem commentItem = (CommentItem) arrayList.get(i2);
                CommendDetailOld$b$a commendDetailOld$b$a = null;
                if (this.d.c.containsKey(commentItem.getId() + this.a.getId())) {
                    view2 = (View) this.d.c.get(commentItem.getId() + this.a.getId());
                    commendDetailOld$b$a = (CommendDetailOld$b$a) view2.getTag(R.layout.comment_item_reply);
                    try {
                        commendDetailOld$b$b.s.removeView(view2);
                        commendDetailOld$b$b.s.addView(view2);
                        obj = null;
                    } catch (Exception e2) {
                        obj = 1;
                    }
                } else {
                    int i3 = 1;
                    view2 = view;
                }
                if (obj != null) {
                    CommendDetailOld$b$a commendDetailOld$b$a2 = new CommendDetailOld$b$a(this, null);
                    View inflate = this.d.o.getLayoutInflater().inflate(R.layout.comment_item_reply, null);
                    commendDetailOld$b$a2.d = (LinearLayout) inflate.findViewById(R.id.ReplayLayout);
                    commendDetailOld$b$a2.e = (RelativeLayout) inflate.findViewById(R.id.HideLayout);
                    commendDetailOld$b$a2.f = (ImageView) inflate.findViewById(R.id.LappedArrow);
                    commendDetailOld$b$a2.g = (ProgressBar) inflate.findViewById(R.id.LappedProgressBar);
                    commendDetailOld$b$a2.a = (TextView) inflate.findViewById(R.id.UserNameTextView);
                    commendDetailOld$b$a2.t = (ImageView) inflate.findViewById(R.id.iv_members_mark_reply);
                    commendDetailOld$b$a2.b = (TextView) inflate.findViewById(R.id.ContentTextView);
                    commendDetailOld$b$a2.c = (TextView) inflate.findViewById(R.id.NumberTextView);
                    commendDetailOld$b$a2.h = (FrameLayout) inflate.findViewById(R.id.commentDingLayout);
                    commendDetailOld$b$a2.i = (TextView) inflate.findViewById(R.id.commentLikeCount);
                    commendDetailOld$b$a2.j = (ImageView) inflate.findViewById(R.id.commentDingIv);
                    commendDetailOld$b$a2.k = (FrameLayout) inflate.findViewById(R.id.commentCaiLayout);
                    commendDetailOld$b$a2.l = (TextView) inflate.findViewById(R.id.commentCaiCount);
                    commendDetailOld$b$a2.m = (ImageView) inflate.findViewById(R.id.commentCaiIv);
                    commendDetailOld$b$a2.n = (RelativeLayout) inflate.findViewById(R.id.ReplyImageLayout);
                    commendDetailOld$b$a2.o = (ImageView) inflate.findViewById(R.id.PlayVideoImageView);
                    commendDetailOld$b$a2.p = (AsyncImageView) inflate.findViewById(R.id.ItemImageView);
                    commendDetailOld$b$a2.q = (ImageView) inflate.findViewById(R.id.GifIconImageView);
                    commendDetailOld$b$a2.r = (AudioLayout) inflate.findViewById(R.id.cmtVoice);
                    commendDetailOld$b$a2.s = inflate.findViewById(R.id.line_view);
                    commendDetailOld$b$a2.u = (TextView) inflate.findViewById(R.id.comment_reply_vote);
                    commendDetailOld$b$a2.v = (LinearLayout) inflate.findViewById(R.id.pub_view);
                    commendDetailOld$b$a2.w = (TextView) inflate.findViewById(R.id.pub_content_view);
                    try {
                        commendDetailOld$b$b.s.addView(inflate);
                    } catch (Exception e3) {
                    }
                    inflate.setTag(R.layout.comment_item_reply, commendDetailOld$b$a2);
                    this.d.c.put(commentItem.getId() + this.a.getId(), inflate);
                    commendDetailOld$b$a = commendDetailOld$b$a2;
                    view = inflate;
                } else {
                    view = view2;
                }
                this.d.aU = -1;
                if (commentItem.getLapped() == null || !commentItem.getLapped().equals(Constants.SERVICE_SCOPE_FLAG_VALUE)) {
                    commendDetailOld$b$a.c.setVisibility(0);
                    commendDetailOld$b$a.d.setVisibility(0);
                    commendDetailOld$b$a.e.setVisibility(8);
                    commendDetailOld$b$a.n.setVisibility(8);
                    commendDetailOld$b$a.o.setVisibility(8);
                    commendDetailOld$b$a.q.setVisibility(8);
                    commendDetailOld$b$a.r.setVisibility(8);
                    commendDetailOld$b$a.v.setVisibility(8);
                    commendDetailOld$b$a.b.setVisibility(0);
                    commendDetailOld$b$a.s.setVisibility(0);
                    if (i2 == arrayList.size() - 1) {
                        commendDetailOld$b$a.s.setVisibility(8);
                    }
                    String type = commentItem.getType();
                    if (type == null || (type.equals("") | type.equals("null")) != 0) {
                        type = "unknown";
                    }
                    if (commentItem.isPub()) {
                        type = "pub";
                    }
                    if (!type.equals("text")) {
                        if (type.equals(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY)) {
                            if (commentItem.getContent().equals("")) {
                                commendDetailOld$b$a.b.setVisibility(8);
                            }
                            commendDetailOld$b$a.n.setVisibility(0);
                            commendDetailOld$b$a.n.setOnClickListener(new g(this.d, commentItem));
                            com.budejie.www.adapter.b.a.c(commendDetailOld$b$a.p, commentItem.getImageWidth(), commentItem.getImageHeight());
                            commendDetailOld$b$a.p.setAsyncCacheImage(commentItem.getImageThumbUrl(), R.drawable.likelist_defaut_bg);
                        } else if (type.equals("audio")) {
                            commendDetailOld$b$a.r.setVisibility(0);
                            commendDetailOld$b$a.b.setVisibility(8);
                            commendDetailOld$b$a.r.c();
                            commendDetailOld$b$a.r.setPlayPath(commentItem.getAudioUrl());
                            commendDetailOld$b$a.r.setAudioTime("" + commentItem.getAudioDuration());
                            if (ac.a(this.d.o).c()) {
                                type = ac.a(this.d.o).m();
                                if (type == null || !type.equals(commentItem.getVoiceuri())) {
                                    commendDetailOld$b$a.r.c();
                                } else {
                                    commendDetailOld$b$a.r.d();
                                }
                            } else {
                                commendDetailOld$b$a.r.c();
                            }
                        } else if (type.equals("video")) {
                            if (commentItem.getContent().equals("")) {
                                commendDetailOld$b$a.b.setVisibility(8);
                            }
                            commendDetailOld$b$a.n.setVisibility(0);
                            commendDetailOld$b$a.o.setVisibility(0);
                            commendDetailOld$b$a.n.setOnClickListener(new g(this.d, commentItem));
                            com.budejie.www.adapter.b.a.c(commendDetailOld$b$a.p, 0, 0);
                            commendDetailOld$b$a.p.setAsyncCacheImage(commentItem.getVideoThumbnail(), R.drawable.likelist_defaut_bg);
                        } else if (type.equals("gif")) {
                            if (commentItem.getContent().equals("")) {
                                commendDetailOld$b$a.b.setVisibility(8);
                            }
                            commendDetailOld$b$a.q.setVisibility(0);
                            commendDetailOld$b$a.n.setVisibility(0);
                            commendDetailOld$b$a.n.setOnClickListener(new g(this.d, commentItem));
                            com.budejie.www.adapter.b.a.c(commendDetailOld$b$a.p, commentItem.getGifWidth(), commentItem.getGifHeight());
                            commendDetailOld$b$a.p.setAsyncCacheImage(commentItem.getGifThumbUrl(), R.drawable.likelist_defaut_bg);
                        } else if (type.equals("pub")) {
                            commendDetailOld$b$a.v.setVisibility(0);
                            commendDetailOld$b$a.w.setText(commentItem.getmVideoTime() + "\" 神配音");
                            commendDetailOld$b$a.v.setOnClickListener(new h(this.d, commentItem));
                        } else {
                            commendDetailOld$b$a.b.setText("当前版本暂不支持查看此评论，请升级至最新版本。");
                        }
                    }
                    VoteData voteData = commentItem.getVoteData();
                    if (voteData == null || voteData.votes == null || voteData.votes.size() <= 0) {
                        commendDetailOld$b$a.u.setVisibility(8);
                    } else {
                        commendDetailOld$b$a.u.setVisibility(0);
                        commendDetailOld$b$a.u.setOnClickListener(new m(this.d, voteData));
                    }
                    commendDetailOld$b$a.a.setText(commentItem.getUname().replace("\n", ""));
                    if (TextUtils.isEmpty(commentItem.getIs_vip()) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equalsIgnoreCase(commentItem.getIs_vip())) {
                        commendDetailOld$b$a.a.setTextColor(this.d.getResources().getColor(R.color.comment_item_reply_name_color));
                        commendDetailOld$b$a.t.setVisibility(8);
                    } else {
                        commendDetailOld$b$a.a.setTextColor(this.d.o.getResources().getColor(com.budejie.www.util.j.H));
                        try {
                            commendDetailOld$b$a.t.setVisibility(0);
                            Drawable gifDrawable = new GifDrawable(this.d.o.getResources(), com.budejie.www.util.j.I);
                            commendDetailOld$b$a.t.setImageDrawable(gifDrawable);
                            gifDrawable.start();
                        } catch (NotFoundException e4) {
                            e4.printStackTrace();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString(PersonalProfileActivity.c, commentItem.getUid());
                    bundle.putBoolean(PersonalProfileActivity.f, true);
                    commendDetailOld$b$a.a.setOnClickListener(this.d.bu.a(7, bundle));
                    commendDetailOld$b$a.b.setText(commentItem.getContent());
                    commendDetailOld$b$a.c.setText(commentItem.getFloorNum());
                    if (commentItem.getStatus() == null || !(commentItem.getStatus().equals("1") || commentItem.getStatus().equals("4"))) {
                        commendDetailOld$b$a.b.setTextColor(com.budejie.www.h.c.a().c(R.attr.comment_reply_item_content_color));
                        String dingCount = commentItem.getDingCount();
                        String caiCount = commentItem.getCaiCount();
                        int i4 = 0;
                        try {
                            i3 = Integer.parseInt(dingCount);
                            try {
                                i4 = Integer.parseInt(caiCount);
                            } catch (NumberFormatException e6) {
                                e = e6;
                                Log.i("Commend-dingCount1", e.toString());
                                commendDetailOld$b$a.i.setText(i3 <= 0 ? "" : i3 + "");
                                commendDetailOld$b$a.l.setText(i4 <= 0 ? "" : i4 + "");
                                type = commentItem.getDingOrCai();
                                if (!"like".equals(type)) {
                                    commendDetailOld$b$a.j.setSelected(true);
                                    commendDetailOld$b$a.i.setSelected(true);
                                    commendDetailOld$b$a.m.setSelected(false);
                                    commendDetailOld$b$a.l.setSelected(false);
                                } else if ("hate".equals(type)) {
                                    commendDetailOld$b$a.j.setSelected(false);
                                    commendDetailOld$b$a.i.setSelected(false);
                                    commendDetailOld$b$a.m.setSelected(true);
                                    commendDetailOld$b$a.l.setSelected(true);
                                } else {
                                    commendDetailOld$b$a.j.setSelected(false);
                                    commendDetailOld$b$a.i.setSelected(false);
                                    commendDetailOld$b$a.m.setSelected(false);
                                    commendDetailOld$b$a.l.setSelected(false);
                                }
                                commendDetailOld$b$a.h.setOnClickListener(new CommendDetailOld$b$5(this, commentItem, view));
                                commendDetailOld$b$a.k.setOnClickListener(new CommendDetailOld$b$6(this, commentItem, view));
                                commendDetailOld$b$a.d.setTag(commentItem.getId());
                                commendDetailOld$b$a.d.setOnClickListener(new j(this.d, i, commentItem, view));
                            }
                        } catch (NumberFormatException e7) {
                            NumberFormatException numberFormatException = e7;
                            i3 = 0;
                            e = numberFormatException;
                            Log.i("Commend-dingCount1", e.toString());
                            if (i3 <= 0) {
                            }
                            commendDetailOld$b$a.i.setText(i3 <= 0 ? "" : i3 + "");
                            if (i4 <= 0) {
                            }
                            commendDetailOld$b$a.l.setText(i4 <= 0 ? "" : i4 + "");
                            type = commentItem.getDingOrCai();
                            if (!"like".equals(type)) {
                                commendDetailOld$b$a.j.setSelected(true);
                                commendDetailOld$b$a.i.setSelected(true);
                                commendDetailOld$b$a.m.setSelected(false);
                                commendDetailOld$b$a.l.setSelected(false);
                            } else if ("hate".equals(type)) {
                                commendDetailOld$b$a.j.setSelected(false);
                                commendDetailOld$b$a.i.setSelected(false);
                                commendDetailOld$b$a.m.setSelected(false);
                                commendDetailOld$b$a.l.setSelected(false);
                            } else {
                                commendDetailOld$b$a.j.setSelected(false);
                                commendDetailOld$b$a.i.setSelected(false);
                                commendDetailOld$b$a.m.setSelected(true);
                                commendDetailOld$b$a.l.setSelected(true);
                            }
                            commendDetailOld$b$a.h.setOnClickListener(new CommendDetailOld$b$5(this, commentItem, view));
                            commendDetailOld$b$a.k.setOnClickListener(new CommendDetailOld$b$6(this, commentItem, view));
                            commendDetailOld$b$a.d.setTag(commentItem.getId());
                            commendDetailOld$b$a.d.setOnClickListener(new j(this.d, i, commentItem, view));
                        }
                        if (i3 <= 0) {
                        }
                        commendDetailOld$b$a.i.setText(i3 <= 0 ? "" : i3 + "");
                        if (i4 <= 0) {
                        }
                        commendDetailOld$b$a.l.setText(i4 <= 0 ? "" : i4 + "");
                        type = commentItem.getDingOrCai();
                        if (!"like".equals(type)) {
                            commendDetailOld$b$a.j.setSelected(true);
                            commendDetailOld$b$a.i.setSelected(true);
                            commendDetailOld$b$a.m.setSelected(false);
                            commendDetailOld$b$a.l.setSelected(false);
                        } else if ("hate".equals(type)) {
                            commendDetailOld$b$a.j.setSelected(false);
                            commendDetailOld$b$a.i.setSelected(false);
                            commendDetailOld$b$a.m.setSelected(true);
                            commendDetailOld$b$a.l.setSelected(true);
                        } else {
                            commendDetailOld$b$a.j.setSelected(false);
                            commendDetailOld$b$a.i.setSelected(false);
                            commendDetailOld$b$a.m.setSelected(false);
                            commendDetailOld$b$a.l.setSelected(false);
                        }
                        commendDetailOld$b$a.h.setOnClickListener(new CommendDetailOld$b$5(this, commentItem, view));
                        commendDetailOld$b$a.k.setOnClickListener(new CommendDetailOld$b$6(this, commentItem, view));
                    } else {
                        commendDetailOld$b$a.h.setVisibility(8);
                        commendDetailOld$b$a.k.setVisibility(8);
                        commendDetailOld$b$a.b.setTextColor(com.budejie.www.h.c.a().c(R.attr.comment_reply_item_delete_text_color));
                    }
                    commendDetailOld$b$a.d.setTag(commentItem.getId());
                    commendDetailOld$b$a.d.setOnClickListener(new j(this.d, i, commentItem, view));
                } else {
                    commendDetailOld$b$a.c.setVisibility(8);
                    commendDetailOld$b$a.d.setVisibility(8);
                    commendDetailOld$b$a.e.setVisibility(0);
                    commendDetailOld$b$a.f.setVisibility(0);
                    commendDetailOld$b$a.g.setVisibility(8);
                    commendDetailOld$b$a.e.setOnClickListener(new CommendDetailOld$b$4(this, commendDetailOld$b$a.f, commendDetailOld$b$a.g, i, commentItem));
                }
            }
        }
    }

    class c implements OnClickListener {
        Dialog a;
        final /* synthetic */ CommendDetailOld b;

        public c(CommendDetailOld commendDetailOld, Dialog dialog) {
            this.b = commendDetailOld;
            this.a = dialog;
        }

        public void onClick(View view) {
            this.a.dismiss();
            this.b.e("0");
        }
    }

    class d implements OnClickListener {
        Dialog a;
        final /* synthetic */ CommendDetailOld b;

        public d(CommendDetailOld commendDetailOld, Dialog dialog) {
            this.b = commendDetailOld;
            this.a = dialog;
        }

        public void onClick(View view) {
            this.a.dismiss();
            this.b.f(this.b.aG);
        }
    }

    class e implements OnClickListener {
        CommentItem a;
        View b;
        final /* synthetic */ CommendDetailOld c;

        public e(CommendDetailOld commendDetailOld, CommentItem commentItem, View view) {
            this.c = commendDetailOld;
            this.a = commentItem;
            this.b = view;
        }

        public void onClick(View view) {
            if (!an.b()) {
                this.c.b(this.a, this.b);
            }
        }
    }

    class f implements OnClickListener {
        CommentItem a;
        View b;
        final /* synthetic */ CommendDetailOld c;

        public f(CommendDetailOld commendDetailOld, CommentItem commentItem, View view) {
            this.c = commendDetailOld;
            this.a = commentItem;
            this.b = view;
        }

        public void onClick(View view) {
            if (!an.b()) {
                this.c.a(this.a, this.b);
            }
        }
    }

    class g implements OnClickListener {
        CommentItem a;
        final /* synthetic */ CommendDetailOld b;

        public g(CommendDetailOld commendDetailOld, CommentItem commentItem) {
            this.b = commendDetailOld;
            this.a = commentItem;
        }

        public void onClick(View view) {
            if (this.a != null) {
                String type = this.a.getType();
                Intent intent;
                if (type.equals(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY)) {
                    intent = new Intent(this.b.o, CommentShowBigPicture.class);
                    intent.putExtra("imgPath", this.a.getImageShowUrl());
                    intent.putExtra("isgif", "0");
                    intent.putExtra(IndexEntry.COLUMN_NAME_WIDTH, this.a.getImageWidth());
                    intent.putExtra(IndexEntry.COLUMN_NAME_HEIGHT, this.a.getImageHeight());
                    intent.putExtra("download_uri", this.a.getImageDownloadUrls());
                    this.b.o.startActivity(intent);
                } else if (type.equals("video")) {
                    CommentItemVideoActivity.a(this.b.o, this.a);
                } else if (type.equals("gif")) {
                    intent = new Intent(this.b.o, CommentShowBigPicture.class);
                    intent.putExtra("isgif", "1");
                    intent.putExtra("GifShowUrls", this.a.getGifShowUrl());
                    intent.putExtra(IndexEntry.COLUMN_NAME_WIDTH, this.a.getGifWidth());
                    intent.putExtra(IndexEntry.COLUMN_NAME_HEIGHT, this.a.getGifHeight());
                    intent.putExtra("download_uri", this.a.getGifDownLoadUrls());
                    this.b.o.startActivity(intent);
                }
            }
        }
    }

    class h implements OnClickListener {
        CommentItem a;
        final /* synthetic */ CommendDetailOld b;

        public h(CommendDetailOld commendDetailOld, CommentItem commentItem) {
            this.b = commendDetailOld;
            this.a = commentItem;
        }

        public void onClick(View view) {
            if (!TextUtils.isEmpty(this.a.getVideoPlayUrl())) {
                CommentItemVideoActivity.a(this.b.o, this.a);
            }
        }
    }

    class i implements com.budejie.www.widget.CommentOperateView.a {
        final /* synthetic */ CommendDetailOld a;

        i(CommendDetailOld commendDetailOld) {
            this.a = commendDetailOld;
        }

        public void a() {
            this.a.Z = this.a.ai.getString("id", "");
            if (TextUtils.isEmpty(this.a.Z)) {
                an.a(this.a.o, 0, null, null, 0);
                return;
            }
            UserItem e = this.a.by.e(this.a.Z);
            if (TextUtils.isEmpty(e.getLevel()) || Integer.parseInt(e.getLevel()) < this.a.bk) {
                p.a(this.a.o, this.a.o.getString(R.string.send_media_comment_level_message, new Object[]{Integer.valueOf(this.a.bk)}), this.a.getString(R.string.send_media_comment_level_ok), null);
                return;
            }
            Intent intent = new Intent(this.a.o, SelectImageActivity.class);
            intent.putExtra("source", "CommendDetail");
            this.a.startActivityForResult(intent, 100);
        }

        public void b() {
            Intent intent = new Intent(this.a.o, AddVoteActivity.class);
            intent.putExtra("vote_data_key", this.a.bl);
            this.a.startActivityForResult(intent, 10);
        }

        public void c() {
            this.a.Z = this.a.ai.getString("id", "");
            if (TextUtils.isEmpty(this.a.Z)) {
                an.a(this.a.o, 0, null, null, 0);
                return;
            }
            Intent intent = new Intent(this.a.o, GodDubbingActivity.class);
            intent.putExtra("dubbing_key", this.a.aw);
            this.a.startActivityForResult(intent, 20);
        }
    }

    class j implements OnClickListener {
        int a;
        CommentItem b;
        View c;
        final /* synthetic */ CommendDetailOld d;

        public j(CommendDetailOld commendDetailOld, int i, CommentItem commentItem, View view) {
            this.d = commendDetailOld;
            this.a = i;
            this.b = commentItem;
            this.c = view;
        }

        public void onClick(View view) {
            if (this.b.getStatus() != null && !this.b.getStatus().equals("1") && !this.b.getStatus().equals("4")) {
                this.d.a(this.a, this.b, this.c);
            }
        }
    }

    class k implements OnClickListener {
        Dialog a;
        CommentItem b;
        int c;
        final /* synthetic */ CommendDetailOld d;

        public k(CommendDetailOld commendDetailOld, Dialog dialog, CommentItem commentItem) {
            this.d = commendDetailOld;
            this.a = dialog;
            this.b = commentItem;
            this.c = 0;
        }

        public k(CommendDetailOld commendDetailOld, Dialog dialog, CommentItem commentItem, int i) {
            this.d = commendDetailOld;
            this.a = dialog;
            this.b = commentItem;
            this.c = i;
        }

        public void onClick(View view) {
            this.a.dismiss();
            this.d.R = this.b;
            if (this.c == 0) {
                this.d.z();
            } else if (this.c == 1 && this.d.R != null) {
                this.d.y.performClick();
                this.d.br.setText("回复：" + this.d.R.getUname());
            }
        }
    }

    class l implements OnClickListener {
        Dialog a;
        final /* synthetic */ CommendDetailOld b;

        public l(CommendDetailOld commendDetailOld, Dialog dialog) {
            this.b = commendDetailOld;
            this.a = dialog;
        }

        public void onClick(View view) {
            this.a.dismiss();
            this.b.d(this.b.aG);
        }
    }

    class m implements OnClickListener {
        VoteData a;
        final /* synthetic */ CommendDetailOld b;

        public m(CommendDetailOld commendDetailOld, VoteData voteData) {
            this.b = commendDetailOld;
            this.a = voteData;
        }

        public void onClick(View view) {
            new com.budejie.www.widget.g(this.b.o, R.style.dialog, this.a).show();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        aq.a(this, com.budejie.www.h.c.a().b(R.attr.title_background));
        setContentView(R.layout.commentdetail_old);
        this.aR = new com.budejie.www.http.j(this.o);
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this.o, "send_comment_level");
        if (!TextUtils.isEmpty(configParams)) {
            this.bk = Integer.parseInt(configParams);
        }
        r();
        F();
        E();
        if (!this.aH) {
            i();
        }
        q();
        h();
        com.budejie.www.activity.video.k.a(this.o).h();
        EventBus.getDefault().register(this);
    }

    private void h() {
        this.aN = new Dialog(this, R.style.dialogTheme);
        this.aN.setContentView(R.layout.loaddialog);
        this.aN.setCanceledOnTouchOutside(true);
        this.aN.setCancelable(true);
    }

    private void i() {
        try {
            H();
            u();
        } catch (Exception e) {
            aa.e(this.n, "e.toString()" + e.toString());
        }
    }

    private void j() {
        if (this.aI == null) {
            this.aI = this.o.getLayoutInflater().inflate(R.layout.comment_item_footer_none_data, null);
        }
        try {
            if (this.t.getFooterViewsCount() > 0) {
                this.t.removeFooterView(this.aI);
            }
            this.t.addFooterView(this.aI);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void k() {
        if (this.aI == null) {
            this.aI = this.o.getLayoutInflater().inflate(R.layout.comment_item_footer_none_data, null);
        }
        try {
            if (this.t.getFooterViewsCount() > 0) {
                this.t.removeFooterView(this.aI);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void l() {
        if (this.aJ == null) {
            this.aJ = this.o.getLayoutInflater().inflate(R.layout.comment_item_footer_view, null);
        }
        try {
            if (this.t.getFooterViewsCount() > 0) {
                this.t.removeFooterView(this.aJ);
            }
            this.t.addFooterView(this.aJ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m() {
        if (this.aJ == null) {
            this.aJ = this.o.getLayoutInflater().inflate(R.layout.comment_item_footer_view, null);
        }
        try {
            if (this.t.getFooterViewsCount() > 0) {
                this.t.removeFooterView(this.aJ);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void n() {
        if (this.aK == null) {
            this.aK = this.o.getLayoutInflater().inflate(R.layout.comment_item_footer_view_failed, null);
        }
        this.aK.findViewById(R.id.iv_comment_footer).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommendDetailOld a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.o();
                this.a.a(false);
            }
        });
        try {
            if (this.t.getFooterViewsCount() > 0) {
                this.t.removeFooterView(this.aK);
            }
            this.t.addFooterView(this.aK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void o() {
        if (this.aK == null) {
            this.aK = this.o.getLayoutInflater().inflate(R.layout.comment_item_footer_view_failed, null);
        }
        try {
            if (this.t.getFooterViewsCount() > 0) {
                this.t.removeFooterView(this.aK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void p() {
        if (this.t.getFootView() != null) {
            try {
                if (this.t.getFooterViewsCount() > 0) {
                    this.t.removeFooterView(this.t.getFootView());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void q() {
        this.be = false;
        if ("writeCommend".equals(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)) {
            this.R = null;
            c(false);
            this.O.setVisibility(0);
        } else if (this.aw == null) {
            x();
        } else {
            e();
            a(false);
        }
    }

    private void r() {
        this.bj = new BitmapCache();
        this.aV = new ArrayList();
        this.c = new HashMap();
        this.T = getWindowManager().getDefaultDisplay().getHeight();
        this.S = getWindowManager().getDefaultDisplay().getWidth();
        this.av = getResources();
        this.o = this;
        this.Z = ai.b(this);
        this.ai = getSharedPreferences("weiboprefer", 0);
        this.ac = new n(this);
        this.ag = new com.budejie.www.c.g(this);
        this.ae = new com.budejie.www.util.n();
        this.af = new com.budejie.www.c.e(this);
        this.ad = new com.budejie.www.http.c(this, this);
        this.u = new Dialog(this, R.style.dialogTheme);
        this.u.setContentView(R.layout.loaddialog);
        this.u.setCanceledOnTouchOutside(true);
        this.ax = (InputMethodManager) getSystemService("input_method");
        this.bu = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
        this.bv = new com.budejie.www.http.f(this);
        this.bw = com.budejie.www.http.b.a(this.o, this.o);
        this.bx = new com.budejie.www.c.b(this);
        this.by = new com.budejie.www.c.m(this);
        this.bz = this.ac.a(this.Z);
        this.bA = getSharedPreferences("weiboprefer", 0);
        t();
        this.as = OnlineConfigAgent.getInstance().getConfigParams(this, "评论列表-请求条数");
        if (TextUtils.isEmpty(this.as)) {
            this.as = "20";
        }
        s();
    }

    private void s() {
        if (!AdManager.isAdClosed()) {
            this.aO = (LinearLayout) this.o.getLayoutInflater().inflate(R.layout.ad_banner_layout, null);
            BannerAd bannerAd = new BannerAd(AdConfig.banner, this, (RelativeLayout) this.aO.findViewById(R.id.ad_banner_container), new BannerADListener(this) {
                final /* synthetic */ CommendDetailOld a;

                {
                    this.a = r1;
                }

                public void onADReceive(Reporter reporter, boolean z) {
                    this.a.e = reporter;
                    if (!(this.a.e == null || this.a.bs)) {
                        this.a.bs = true;
                        this.a.e.onExposured(this.a.aO);
                    }
                    this.a.aO.setVisibility(0);
                    if (z) {
                        this.a.aO.addView(this.a.o.getLayoutInflater().inflate(R.layout.item_divide_view, null));
                    }
                }

                public void onNoAD(int i) {
                    this.a.aP = false;
                    this.a.aO.setVisibility(8);
                }

                public void onADSkip(AdItem adItem) {
                    w.a(this.a.o, false).a(adItem.getUrl());
                }
            });
        }
    }

    private void t() {
        this.bB = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.bB.registerApp("wx592fdc48acfbe290");
    }

    private void u() {
        if (this.v == null) {
            this.v = new b(this);
            this.t.setAdapter(this.v);
        } else {
            this.v.notifyDataSetChanged();
        }
        v();
    }

    private void v() {
    }

    private void w() {
        com.budejie.www.http.i.a(getString(R.string.track_event_replay_post), com.budejie.www.http.j.a(this.aw), com.budejie.www.http.j.b((Context) this, this.aw));
    }

    private void a(boolean z) {
        this.t.getFootView().setVisibility(8);
        this.t.a = 4;
        this.am = true;
        this.t.getFootView().findViewById(R.id.message_list_bottom_btn).setVisibility(8);
        this.U = 1;
        this.V = 0;
        this.aS = "0";
        this.aT = "0";
        if (z) {
            this.ad.b(this.o, this.Y, "0", "0", this.bJ);
        } else {
            this.ad.a(this.o, this.Y, "0", "0", this.bJ);
        }
        L();
    }

    private void x() {
        this.ad.a(this.Y, "", new net.tsz.afinal.a.a(this) {
            final /* synthetic */ CommendDetailOld a;

            {
                this.a = r1;
            }

            public void onFailure(Throwable th, int i, String str) {
                super.onFailure(th, i, str);
                Log.i("CommendDetail", "帖子详情失败-->errorNo:" + i + ",strMsg:" + str);
            }

            public void onSuccess(Object obj) {
                super.onSuccess(obj);
                String str = (String) obj;
                Log.i("CommendDetail", "帖子详情:" + str);
                ArrayList a = com.budejie.www.j.a.a(this.a, str);
                if (a != null && a.size() > 0) {
                    this.a.aw = (ListItemObject) a.get(0);
                }
                this.a.b(this.a.aw);
                this.a.z.setTextChangedListener(this.a.k);
                if (this.a.aH || this.a.aw == null || this.a.aw.getRichObject() == null) {
                    if (this.a.aw != null) {
                        this.a.C.setPostType(this.a.a(this.a.aw));
                        this.a.Y = this.a.aw.getWid();
                        this.a.i();
                        this.a.e();
                    }
                    this.a.a(false);
                    return;
                }
                Intent intent = new Intent(this.a, RichPostDetail.class);
                intent.putExtra("listitem_object", this.a.aw);
                this.a.startActivity(intent);
                this.a.finish();
            }
        });
    }

    private boolean a(ListItemObject listItemObject) {
        if (listItemObject == null) {
            return false;
        }
        String type = listItemObject.getType();
        if ("41".equals(type)) {
            try {
                if (Integer.parseInt(listItemObject.getVideotime()) < 60) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                if (!TextUtils.isEmpty(e.getMessage())) {
                    aa.e(this.n, e.getMessage());
                }
                return false;
            }
        } else if (!com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(type) || listItemObject.getHeight() / listItemObject.getWidth() >= 2) {
            return false;
        } else {
            return true;
        }
    }

    private void b(ListItemObject listItemObject) {
        if (listItemObject != null && listItemObject.getStatus() != 11 && listItemObject.getStatus() != 12) {
            int parseInt;
            Map hashMap;
            Map hashMap2 = new HashMap();
            Map hashMap3 = new HashMap();
            Object string = this.ai.getString("id", "");
            if (TextUtils.isEmpty(string)) {
                string = an.e(this.o);
            }
            hashMap2.put(HistoryOpenHelper.COLUMN_UID, string);
            hashMap3.put(HistoryOpenHelper.COLUMN_UID, string);
            String str = "";
            if (listItemObject.getRichObject() != null) {
                string = listItemObject.getRichObject().getTitle();
            } else {
                string = listItemObject.getContent();
            }
            if (!TextUtils.isEmpty(string) && string.length() > 40) {
                string = string.substring(0, 40);
            }
            hashMap2.put("title", string);
            hashMap2.put("url", listItemObject.getWeixin_url());
            hashMap3.put("url", listItemObject.getWeixin_url());
            hashMap2.put("author", listItemObject.getName());
            CharSequence charSequence = "";
            Object obj = "";
            string = "";
            String type = listItemObject.getType();
            if ("29".equals(type)) {
                string = "段子";
            } else if (com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(type)) {
                string = "图片";
                charSequence = listItemObject.getImgUrl();
            } else if ("31".equals(type)) {
                string = "声音";
                charSequence = listItemObject.getImgUrl();
                obj = listItemObject.getVoiceUri();
            } else if ("41".equals(type)) {
                string = "视频";
                charSequence = listItemObject.getImgUrl();
                obj = listItemObject.getVideouri();
            } else if ("51".equals(type)) {
                string = "长文";
                charSequence = listItemObject.getRichObject().getImgUrl();
                obj = listItemObject.getRichObject().getSourceUrl();
            } else if ("61".equals(type)) {
                string = "转载贴";
            }
            if (!TextUtils.isEmpty(charSequence)) {
                hashMap2.put("pic", charSequence);
            }
            hashMap2.put("abs", obj);
            hashMap2.put("cat", string);
            hashMap2.put("iid", listItemObject.getWid());
            hashMap3.put("iid", listItemObject.getWid());
            hashMap2.put("keywords", listItemObject.getPlateNames());
            long j = 0;
            try {
                j = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(listItemObject.getPasstime()).getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }
            hashMap2.put("ptime", Long.valueOf(j));
            if (!TextUtils.isEmpty(listItemObject.getComment())) {
                try {
                    parseInt = Integer.parseInt(listItemObject.getComment());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                hashMap2.put("ccnt", Integer.valueOf(parseInt));
                hashMap2.put("typ", Constants.SERVICE_SCOPE_FLAG_VALUE);
                hashMap = new HashMap();
                hashMap.put("type", string);
                hashMap.put("label", listItemObject.getPlateNames());
                an.a(this.o, hashMap, "E01_A02");
            }
            parseInt = 0;
            hashMap2.put("ccnt", Integer.valueOf(parseInt));
            hashMap2.put("typ", Constants.SERVICE_SCOPE_FLAG_VALUE);
            hashMap = new HashMap();
            hashMap.put("type", string);
            hashMap.put("label", listItemObject.getPlateNames());
            an.a(this.o, hashMap, "E01_A02");
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        try {
            if (keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 4) {
                if (com.budejie.www.activity.video.k.a((Context) this).e == this && com.budejie.www.activity.video.k.a((Context) this).f != null && com.budejie.www.activity.video.k.a((Context) this).f.b(4)) {
                    return true;
                }
                if (this.O != null && this.ab) {
                    an.b(this.o);
                    Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) this.T);
                    translateAnimation.setDuration(600);
                    this.O.setAnimation(translateAnimation);
                    translateAnimation.start();
                    this.O.setVisibility(8);
                    this.ab = false;
                }
                if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0 && this.aX != null && this.aX.isShown()) {
                    this.aX.setVisibility(8);
                    return true;
                } else if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0 && this.E != null && this.E.isShown()) {
                    this.bf = BottomStatus.NORMAL;
                    aq.a(this.o, com.budejie.www.h.c.a().b(R.attr.title_background));
                    e();
                    return true;
                } else if (this.bn != null && this.bn.getVisibility() == 0) {
                    y();
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    private void y() {
        if (this.bn != null) {
            this.bn.b();
            this.bn.g();
            this.bn.setVisibility(8);
            this.bm.setVisibility(8);
        }
    }

    private void a(List<CommentItem> list) {
        List a = this.af.a();
        if (a.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                CommentItem commentItem = (CommentItem) list.get(i);
                if (!(commentItem == null || commentItem.getId() == null)) {
                    for (int i2 = 0; i2 < a.size(); i2++) {
                        String str = (String) a.get(i2);
                        if (!TextUtils.isEmpty(str)) {
                            String[] split = str.split("#");
                            String str2 = split[0];
                            str = split[1];
                            if (str2 != null && str2.equals(commentItem.getId())) {
                                commentItem.setDingOrCai(str);
                            }
                        }
                    }
                }
                if (!(commentItem == null || commentItem.getReplyList() == null || commentItem.getReplyList().size() <= 0)) {
                    for (int i3 = 0; i3 < commentItem.getReplyList().size(); i3++) {
                        CommentItem commentItem2 = (CommentItem) commentItem.getReplyList().get(i3);
                        for (int i4 = 0; i4 < a.size(); i4++) {
                            String str3 = (String) a.get(i4);
                            if (!TextUtils.isEmpty(str3)) {
                                String[] split2 = str3.split("#");
                                String str4 = split2[0];
                                str3 = split2[1];
                                if (str4 != null && str4.equals(commentItem2.getId())) {
                                    commentItem2.setDingOrCai(str3);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(CommentItem commentItem) {
        List a = this.af.a();
        if (commentItem != null && commentItem.getReplyList() != null && commentItem.getReplyList().size() > 0) {
            for (int i = 0; i < commentItem.getReplyList().size(); i++) {
                CommentItem commentItem2 = (CommentItem) commentItem.getReplyList().get(i);
                for (int i2 = 0; i2 < a.size(); i2++) {
                    String str = (String) a.get(i2);
                    if (!TextUtils.isEmpty(str)) {
                        String[] split = str.split("#");
                        String str2 = split[0];
                        str = split[1];
                        if (str2 != null && str2.equals(commentItem2.getId())) {
                            commentItem2.setDingOrCai(str);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void d() {
        this.ax.hideSoftInputFromWindow(this.z.getWindowToken(), 0);
        this.bt.sendEmptyMessageDelayed(14, 200);
    }

    public void onClick(View view) {
        if (view == this.p || view == this.r) {
            if (!this.ab) {
                com.budejie.www.util.i.a().a = false;
                d();
            }
        } else if (view == this.s) {
            a(this.s);
            MobclickAgent.onEvent(this.o, "E02-A05", "顶部评论条转发");
            Log.i("CommendDetail", "顶部评论条转发");
        } else if (view == this.J) {
            an.b(this.o);
            if (this.au) {
                finish();
            } else if ("writeCommend".equals(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)) {
                finish();
            } else {
                if (this.ab) {
                    an.b(this.o);
                    Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) this.T);
                    translateAnimation.setDuration(600);
                    this.O.setAnimation(translateAnimation);
                    translateAnimation.start();
                    this.O.setVisibility(8);
                }
                this.ab = false;
            }
        } else if (view == this.K) {
            if (an.a((Context) this)) {
                this.ay = this.N.getText().toString().trim();
                int length = this.ay.length();
                Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "comment_size");
                int i = 2;
                if (!TextUtils.isEmpty(configParams)) {
                    i = Integer.parseInt(configParams);
                }
                if (length < i) {
                    this.P = an.a((Activity) this, getString(R.string.commend_limit, new Object[]{Integer.valueOf(i)}), -1);
                    this.P.show();
                    return;
                } else if (length > R$styleable.Theme_Custom_send_btn_text_color) {
                    this.P = an.a((Activity) this, getString(R.string.text_beyond) + (length - 140) + getString(R.string.again_input), -1);
                    this.P.show();
                    return;
                } else {
                    this.Z = this.ai.getString("id", "");
                    if (TextUtils.isEmpty(this.Z)) {
                        an.a(this.o, 0, null, null, 0);
                        return;
                    } else if (this.af.b(this.Y, this.ay)) {
                        this.P = an.a((Activity) this, getString(R.string.commentExist), -1);
                        this.P.show();
                        return;
                    } else {
                        G();
                        StringBuffer stringBuffer = new StringBuffer();
                        if (this.ac.a(this.o) && this.ak) {
                            stringBuffer.append("sina,");
                            this.ai.edit().putBoolean("sharesina", true).commit();
                        } else {
                            this.ai.edit().putBoolean("sharesina", false).commit();
                        }
                        if (this.ac.b(this.o) && this.al) {
                            stringBuffer.append("qq,");
                            this.ai.edit().putBoolean("sharetenct", true).commit();
                        } else {
                            this.ai.edit().putBoolean("sharetenct", false).commit();
                        }
                        String stringBuffer2 = stringBuffer.toString();
                        if (stringBuffer2.endsWith(",")) {
                            stringBuffer2 = stringBuffer2.substring(0, stringBuffer.length() - 1);
                        }
                        if (TextUtils.isEmpty(stringBuffer2)) {
                            stringBuffer2 = "";
                        }
                        a(stringBuffer2, this.ay);
                        if (this.au) {
                            finish();
                            return;
                        }
                        return;
                    }
                }
            }
            Toast.makeText(this, R.string.nonet, 0).show();
        } else if (view == this.x) {
            if (this.bf == BottomStatus.OPERATE) {
                this.bf = BottomStatus.NORMAL;
            } else {
                this.bf = BottomStatus.OPERATE;
            }
            e();
        } else if (view == this.br) {
            this.R = null;
            z();
        } else if (view == this.y) {
            this.Z = this.ai.getString("id", "");
            if (TextUtils.isEmpty(this.Z)) {
                an.a(this.o, 0, null, null, 0);
                return;
            }
            UserItem e = this.by.e(this.Z);
            if (TextUtils.isEmpty(e.getLevel()) || Integer.parseInt(e.getLevel()) < this.bk) {
                p.a(this.o, this.o.getString(R.string.send_media_comment_level_message, new Object[]{Integer.valueOf(this.bk)}), getString(R.string.send_media_comment_level_ok), null);
                return;
            }
            if (this.bf == BottomStatus.VOICE) {
                this.bf = BottomStatus.NORMAL;
            } else {
                this.bf = BottomStatus.VOICE;
            }
            e();
        } else if (view == this.A) {
            this.Z = this.ai.getString("id", "");
            if (TextUtils.isEmpty(this.Z)) {
                an.a(this.o, 0, null, null, 0);
            } else {
                a(false, false);
            }
        } else if (view == this.I) {
            this.aA = null;
            this.aC = null;
            this.aD = null;
            this.I.setVisibility(8);
            this.G.setVisibility(8);
        } else if (view == this.bp && this.d != null) {
            this.d.a(false);
        }
    }

    private void z() {
        this.Z = this.ai.getString("id", "");
        if (TextUtils.isEmpty(this.Z)) {
            an.a(this.o, 0, null, null, 0);
        } else {
            com.budejie.www.util.a.a(this.o, this.aw, this.R);
        }
    }

    public void onEventMainThread(UpdateCommentAction updateCommentAction) {
        a(true);
    }

    private void a(View view) {
        this.bf = BottomStatus.NORMAL;
        e();
        if (this.aw != null) {
            view.setTag(this.aw);
            Bundle bundle = new Bundle();
            bundle.putInt("position", 0);
            bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this));
            bundle.putSerializable("weiboMap", this.bz);
            bundle.putSerializable("data", this.aw);
            bundle.putBoolean("has_screen_shoot", true);
            this.bu.a(5, bundle, this.f, this.bB, this.by, this.ac, this.ah, this.bA, this.bt).onClick(view);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 711) {
            bindTencent();
        } else if (i2 == 0) {
            if (this.u.isShowing()) {
                this.u.dismiss();
            }
        } else if (i2 == -1) {
            StringBuilder append;
            if (i == 435 || i == 436) {
                r0 = intent.getStringExtra(getString(R.string.RESPONE_RESULT_CONTACT_NAME));
                if (!TextUtils.isEmpty(r0)) {
                    if (i == 436) {
                        append = new StringBuilder("").append(r0).append(" ");
                    } else {
                        append = new StringBuilder("@").append(r0).append(" ");
                    }
                    a(append);
                }
            } else if (i == 475 || i == 476) {
                r0 = intent.getStringExtra(getString(R.string.RESPONE_RESULT_TOPIC_NAME));
                if (!TextUtils.isEmpty(r0)) {
                    if (i == 476) {
                        append = new StringBuilder("").append(r0).append("#");
                    } else {
                        append = new StringBuilder("#").append(r0).append("#");
                    }
                    a(append);
                }
            } else if (i == 100) {
                String stringExtra = intent.getStringExtra("type");
                String stringExtra2 = intent.getStringExtra("imgPath");
                String stringExtra3 = intent.getStringExtra("thumbnail");
                ArrayList arrayList = (ArrayList) intent.getSerializableExtra("MultipleImgPath");
                Log.d(this.n, "onactivityResult type=" + stringExtra);
                Log.d(this.n, "onactivityResult path=" + stringExtra2);
                Log.d(this.n, "onactivityResult thumbnail=" + stringExtra3);
                if ("video".equals(stringExtra)) {
                    this.aC = new File(stringExtra2);
                    b(stringExtra3, stringExtra2);
                    if (this.bf == BottomStatus.KEYBOARD) {
                        this.ax.hideSoftInputFromWindow(this.z.getWindowToken(), 0);
                        this.B.setVisibility(8);
                    }
                    this.bt.sendEmptyMessageDelayed(15, 100);
                } else if (!CheckCodeDO.CHECKCODE_IMAGE_URL_KEY.equals(stringExtra)) {
                } else {
                    if (stringExtra2.endsWith("gif")) {
                        this.aA = new File(stringExtra2);
                        b(stringExtra3, stringExtra2);
                        if (this.bf == BottomStatus.KEYBOARD) {
                            this.ax.hideSoftInputFromWindow(this.z.getWindowToken(), 0);
                            this.B.setVisibility(8);
                        }
                        this.bt.sendEmptyMessageDelayed(15, 100);
                        return;
                    }
                    a(stringExtra2, arrayList);
                }
            } else if (i == 7201) {
                b(intent.getStringExtra("imagePath"));
            }
        } else if (i2 == 7203 && i == 7201) {
            a(intent.getStringExtra("imagePath"), (ArrayList) intent.getSerializableExtra("MultipleImgPath"));
        } else if (i2 == 10) {
            this.bl = intent.getStringExtra("vote_data_key");
            a(false, true);
        } else if (i2 == 3) {
            r0 = intent.getStringExtra("VideoPathTag");
            this.aE = intent.getIntExtra("VideoTimeTag", 0);
            if (!TextUtils.isEmpty(r0)) {
                this.aC = null;
                this.aD = new File(r0);
                b("", r0);
                this.bt.sendEmptyMessageDelayed(15, 100);
            }
        }
    }

    private void a(String str, ArrayList<String> arrayList) {
        Intent intent = new Intent(this.o, EditPictureActivity.class);
        intent.putExtra("picture_path_key", str);
        intent.putExtra("source", "TougaoActivity");
        if (arrayList != null) {
            intent.putExtra("MultipleImgPath", arrayList);
        }
        startActivityForResult(intent, 7201);
    }

    public void b(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.aA = new File(str);
                b("", str);
                if (this.bf == BottomStatus.KEYBOARD) {
                    this.ax.hideSoftInputFromWindow(this.z.getWindowToken(), 0);
                    this.B.setVisibility(8);
                }
                this.bt.sendEmptyMessageDelayed(15, 100);
            }
        } catch (OutOfMemoryError e) {
            this.P = an.a((Activity) this, getString(R.string.tougao_pic_too_big), -1);
            this.P.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(StringBuilder stringBuilder) {
        int selectionStart = this.z.getSelectionStart();
        if (selectionStart < 0) {
            selectionStart = 0;
        }
        this.z.getEditableText().insert(selectionStart, stringBuilder);
    }

    private void A() {
        View footView = this.t.getFootView();
        if (footView != null) {
            ((ProgressBar) footView.findViewById(R.id.message_list_more_progressbar)).setVisibility(8);
            footView.setVisibility(8);
            this.am = false;
        }
    }

    private void B() {
        this.ai.getString("id", "");
        int i = this.U + 1;
        this.V = 2;
        this.ad.a(this.o, this.Y, this.aT, "2", this.bJ);
    }

    private void a(String str, String str2) {
        this.W = ((int) System.currentTimeMillis()) / 100;
        this.ah.a(this.W, getString(R.string.commend_sending));
        String str3 = "";
        if (this.R != null) {
            str3 = this.R.getId();
        }
        this.ac.a(this.Y, str2, str, this.Z, this.bt, 12, str3, false, sendCommendActivity.a(this.aw));
        if (!"writeCommend".equals(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)) {
            C();
        }
        w();
    }

    public void a(net.tsz.afinal.a.b bVar) {
        net.tsz.afinal.b bVar2 = new net.tsz.afinal.b(this.o.getApplicationContext(), new v(this.o));
        bVar2.a("User-Agent", new WebView(this.o).getSettings().getUserAgentString() + NetWorkUtil.a());
        bVar2.a("cookie", NetWorkUtil.b(this.o));
        bVar2.a(NetWorkUtil.a(getApplicationContext()));
        StringBuilder append = new StringBuilder().append("http://d.api.budejie.com");
        com.budejie.www.http.j jVar = this.aR;
        bVar2.b(com.lt.a.a(this.o).a(append.append(com.budejie.www.http.j.d(this.aw.getWid())).toString()), bVar, new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ CommendDetailOld a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void a(String str) {
                this.a.aA = null;
                this.a.aB = null;
                this.a.aF = 0;
                this.a.aE = 0;
                this.a.aC = null;
                this.a.aD = null;
                this.a.bl = "";
                if (!TextUtils.isEmpty(str)) {
                    Log.i("CommendDetail", "-->" + str);
                    Message obtainMessage = this.a.bt.obtainMessage(12, str);
                    this.a.be = true;
                    this.a.bt.sendMessage(obtainMessage);
                } else if (this.a.bh != null) {
                    this.a.bh.a(false, "");
                }
            }

            public void onFailure(Throwable th, int i, String str) {
                Log.i("CommendDetail", "onCommitVoice error : " + str);
                this.a.aA = null;
                this.a.aB = null;
                this.a.aF = 0;
                this.a.aE = 0;
                this.a.aC = null;
                this.a.aD = null;
                this.a.bl = "";
                this.a.be = false;
                if (this.a.bh != null) {
                    this.a.bh.a(false, "");
                }
            }
        });
    }

    private void a(boolean z, boolean z2) {
        String str;
        if (z) {
            str = "31";
            this.aA = null;
            this.aC = null;
            this.aD = null;
            this.az = "";
            this.bl = "";
            this.aE = 0;
        } else if (this.aC != null) {
            str = "41";
            this.aA = null;
            this.aB = null;
            this.aD = null;
            this.aF = 0;
            this.aE = 0;
            this.az = this.z.getText().toString().trim();
        } else if (this.aD != null) {
            str = "71";
            this.aA = null;
            this.aB = null;
            this.aF = 0;
            this.aC = this.aD;
            this.az = this.z.getText().toString().trim();
        } else if (this.aA != null) {
            str = com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
            this.aB = null;
            this.aF = 0;
            this.aE = 0;
            this.aC = null;
            this.aD = null;
            this.az = this.z.getText().toString().trim();
        } else {
            str = "29";
            this.az = this.z.getText().toString().trim();
            if (!z2) {
                if (TextUtils.isEmpty(this.az)) {
                    Toast.makeText(this.o, R.string.none_comment_content, 0).show();
                    return;
                }
                int length = this.az.length();
                Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "comment_size");
                int i = 2;
                if (!TextUtils.isEmpty(configParams)) {
                    i = Integer.parseInt(configParams);
                }
                if (length < i) {
                    this.P = an.a((Activity) this, getString(R.string.commend_limit, new Object[]{Integer.valueOf(i)}), -1);
                    this.P.show();
                    return;
                } else if (length > R$styleable.Theme_Custom_send_btn_text_color) {
                    this.P = an.a((Activity) this, getString(R.string.text_beyond) + (length - 140) + getString(R.string.again_input), -1);
                    this.P.show();
                    return;
                } else if (this.af.b(this.Y, this.az)) {
                    this.P = an.a((Activity) this, getString(R.string.commentExist), -1);
                    this.P.show();
                    return;
                }
            }
            this.aB = null;
            this.aF = 0;
            this.aE = 0;
        }
        if (this.bh != null) {
            this.bh.show();
        }
        M();
        String str2 = "";
        if (this.R != null) {
            str2 = this.R.getId();
        }
        a(this.aR.a(this.o, this.aw.getWid(), this.Z, str2, this.az, str, this.aF, this.aA, this.aB, this.aC, this.bl, this.aE));
        w();
        this.z.setText("");
        this.bf = BottomStatus.NORMAL;
        e();
    }

    private void C() {
        this.t.setSelection(0);
        an.b(this.o);
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) this.T);
        translateAnimation.setDuration(600);
        this.O.setAnimation(translateAnimation);
        translateAnimation.start();
        this.N.setText("");
        this.O.setVisibility(8);
        this.ab = false;
    }

    private void b(final boolean z) {
        new Thread(this) {
            final /* synthetic */ CommendDetailOld b;

            public void run() {
                try {
                    Thread.sleep(3000);
                    this.b.bt.sendMessage(this.b.bt.obtainMessage(931, Boolean.valueOf(z)));
                } catch (InterruptedException e) {
                }
            }
        }.start();
    }

    public void onResume() {
        super.onResume();
        this.aa = true;
        this.bi = System.currentTimeMillis();
        Log.d(this.n, "onResume");
        e();
        this.bu = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
        D();
    }

    private void D() {
        Object obj = (this.aw == null || !"31".equals(this.aw.getType())) ? null : 1;
        if (obj != null && this.aM != null && (this.aM.getTag() instanceof com.budejie.www.adapter.b)) {
            Object<com.budejie.www.adapter.g.a> obj2 = ((com.budejie.www.adapter.b) this.aM.getTag()).F;
            if (!com.budejie.www.goddubbing.c.a.a(obj2)) {
                for (com.budejie.www.adapter.g.a aVar : obj2) {
                    if (aVar instanceof com.budejie.www.adapter.g.b.k) {
                        aVar.c();
                        return;
                    }
                }
            }
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.d != null) {
            this.d.a(true);
        }
        f();
        this.ao.setFocusable(true);
        this.ao.setFocusableInTouchMode(true);
        this.ao.requestFocus();
        this.ax.hideSoftInputFromWindow(this.z.getWindowToken(), 0);
        try {
            this.aa = false;
            if (this.P != null) {
                this.P.cancel();
            }
            com.budejie.www.activity.video.k.a((Context) this).o();
        } catch (Exception e) {
            e.printStackTrace();
        }
        y();
        this.bf = BottomStatus.NORMAL;
        e();
        if (this.B != null) {
            this.B.b();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.c != null) {
            this.c.clear();
        }
        com.budejie.www.activity.video.k.a((Context) this).p();
        EventBus.getDefault().unregister(this);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    private void E() {
        this.bh = new com.budejie.www.widget.f(this.o, R.style.dialogTheme);
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        ((KeyboardListenerRelativeLayout) findViewById(R.id.root_layout)).setOnKeyboardChangeListener(this.bM);
        this.p = (Button) findViewById(R.id.title_left_btn);
        this.p.setVisibility(0);
        this.q = (TextView) findViewById(R.id.title_center_txt);
        this.r = (LinearLayout) findViewById(R.id.left_layout);
        this.s = (RelativeLayout) findViewById(R.id.title_refresh_layout);
        this.s.findViewById(R.id.title_right_imgbtn).setVisibility(0);
        this.bm = (RelativeLayout) findViewById(R.id.pub_video_view_container);
        this.bn = (VideoView) findViewById(R.id.pub_video_view);
        this.bo = (RelativeLayout) findViewById(R.id.screen_shot_mask);
        this.bp = (TextView) findViewById(R.id.screen_shot_stop);
        this.bo.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ CommendDetailOld a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.bp.setOnClickListener(this);
        this.t = (CustomListView) findViewById(R.id.listview);
        this.an = (FloatVideoRootLayout) findViewById(R.id.curtain_root_layout);
        this.ao = (RelativeLayout) findViewById(R.id.title);
        this.q.setText(R.string.comments_detail);
        this.r.setVisibility(0);
        this.s.setVisibility(0);
        this.p.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.t.setonLoadListener(this.bG);
        this.t.setOnItemClickListener(this.i);
        this.t.a((com.budejie.www.g.a) this);
        this.t.setonFlingListener(this.bH);
        this.t.setOnScrollListener(new com.nostra13.universalimageloader.core.d.e(com.nostra13.universalimageloader.core.d.a(), false, true, this.bI));
        this.aj = new ArrayList();
        this.z = (ParseTagEditText) findViewById(R.id.recordBottomEditText);
        this.z.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ CommendDetailOld a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (1 == motionEvent.getAction() && this.a.bf != BottomStatus.KEYBOARD) {
                    this.a.bf = BottomStatus.KEYBOARD;
                    this.a.B.setVisibility(8);
                    this.a.C.setVisibility(8);
                }
                return false;
            }
        });
        this.br = (TextView) findViewById(R.id.write_comment_tv);
        this.br.setOnClickListener(this);
        if (this.aw != null) {
            this.z.setTextChangedListener(this.k);
        } else {
            this.z.setTextChangedListener(null);
        }
        this.z.setListener(new com.budejie.www.widget.parsetagview.a(this));
        this.w = (RelativeLayout) findViewById(R.id.recordBottomFrame);
        com.budejie.www.activity.video.p.a(this, this.w);
        this.y = (ImageView) findViewById(R.id.recordOrKeyboard);
        this.y.setOnClickListener(this);
        this.x = (ImageView) findViewById(R.id.comment_bottom_more);
        this.x.setOnClickListener(this);
        this.A = (TextView) findViewById(R.id.voiceSendBtn);
        this.A.setOnClickListener(this);
        this.G = (RelativeLayout) findViewById(R.id.select_image_video_mark);
        this.H = (ImageView) findViewById(R.id.select_image_video_mark_iv);
        this.I = (ImageView) findViewById(R.id.select_image_video_mark_cancel);
        this.I.setOnClickListener(this);
        this.B = (RecordVoiceView) findViewById(R.id.record_voice_view);
        this.D = findViewById(R.id.record_voice_mask);
        this.E = findViewById(R.id.record_send_voice_mask);
        this.F = findViewById(R.id.keyword_mask);
        this.C = (CommentOperateView) findViewById(R.id.opetate_view);
        this.C.setOperateListenr(new i(this));
        this.C.setPostType(a(this.aw));
        this.B.a(this.D, this.E);
        this.B.setRecordVoiceHandler(this.bN);
        this.aX = (RelativeLayout) findViewById(R.id.VoiceToWordsLayout);
        this.aY = (RelativeLayout) findViewById(R.id.VoiceToWordsLoadingLayout);
        this.aZ = (TextView) findViewById(R.id.VoiceToWordsTextView);
        this.ba = (TextView) findViewById(R.id.VoiceToWordsCancelTextView);
        this.bb = (ScrollView) findViewById(R.id.VoiceToWordsScrollView);
        this.bc = (LinearLayout) findViewById(R.id.VoiceToWordsFailsLayout);
        this.bd = (TextView) findViewById(R.id.VoiceToWordsFailsTextView);
        this.aX.setVisibility(8);
        this.aX.setOnClickListener(this.m);
        this.bb.setOnClickListener(this.m);
        this.aZ.setOnClickListener(this.m);
        this.ba.setOnClickListener(this.m);
        this.ah = new com.elves.update.a(this);
        this.F.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommendDetailOld a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.F.setVisibility(8);
                this.a.ax.hideSoftInputFromWindow(this.a.z.getWindowToken(), 0);
            }
        });
        this.E.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommendDetailOld a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.bf = BottomStatus.NORMAL;
                aq.a(this.a.o, com.budejie.www.h.c.a().b(R.attr.title_background));
                this.a.e();
            }
        });
    }

    private void F() {
        this.Q = getIntent();
        this.aH = this.Q.getBooleanExtra("is_rich_ommend", false);
        this.aw = (ListItemObject) this.Q.getSerializableExtra("listitem_object");
        if (this.aw != null) {
            VoteData voteData = this.aw.getVoteData();
            if (!(voteData == null || voteData.isVoted())) {
                ax.a(this.aw, this.o);
            }
            if (!this.aH) {
                b(this.aw);
            }
            Log.i("CommendDetail", "帖子评论数:" + this.aw.getComment());
            if ("0".equals(this.aw.getComment())) {
                com.budejie.www.activity.video.k.a(this.o).h();
            }
            this.Y = this.aw.getWid();
            b = this.aw.getImgUrl();
            com.budejie.www.http.i.a(getString(R.string.track_event_see_comment), com.budejie.www.http.j.a(this.aw), com.budejie.www.http.j.b((Context) this, this.aw));
        } else {
            this.Y = this.Q.getStringExtra("msg_wid");
            Log.i("zhangxitao", "dataid:" + this.Y);
        }
        if (!this.aH && this.aw != null && this.aw.getRichObject() != null) {
            Intent intent = new Intent(this, RichPostDetail.class);
            if (!this.aH) {
                intent.putExtra("rich_post_open_type", "rich_post_comment");
            }
            intent.putExtra("listitem_object", this.aw);
            startActivity(intent);
            finish();
        }
    }

    private void c(boolean z) {
        this.O = (RelativeLayout) findViewById(R.id.comment_write_layout);
        this.ap = (RelativeLayout) this.O.findViewById(R.id.title);
        this.L = (TextView) findViewById(R.id.write_comment_txt);
        this.J = (TextView) findViewById(R.id.write_left_btn);
        this.K = (TextView) findViewById(R.id.comment_send);
        this.N = (EditText) findViewById(R.id.commend);
        this.M = (TextView) findViewById(R.id.textview);
        this.N.setText("");
        this.M.setTextColor(-16777216);
        this.J.setText(R.string.cancel);
        onrefreshTheme();
        this.J.setOnClickListener(this);
        this.K.setOnClickListener(this);
        this.N.addTextChangedListener(this.j);
        this.N.setFocusableInTouchMode(true);
        this.N.requestFocus();
        this.ah = new com.elves.update.a(this);
    }

    private void G() {
        this.ak = this.ai.getBoolean("sharesina", true);
        this.al = this.ai.getBoolean("sharetenct", true);
    }

    private void H() {
        this.aL = (com.budejie.www.adapter.a) new com.budejie.www.adapter.c.a((Activity) this, (com.budejie.www.adapter.e.a) this, 14).a(this.aw, 0);
        this.aM = this.aL.b();
        this.aL.a((com.budejie.www.adapter.b) this.aM.getTag());
        J();
        this.t.addHeaderView(this.aM);
        I();
    }

    private void I() {
        this.t.addHeaderView(LayoutInflater.from(this).inflate(R.layout.old_detail_content_divide_view, null));
    }

    private void J() {
        if (this.aM != null) {
            int color;
            View view = this.aM;
            if (ai.a(this) == 0) {
                color = getResources().getColor(R.color.detail_content_day_background);
            } else {
                color = getResources().getColor(R.color.detail_content_night_background);
            }
            view.setBackgroundColor(color);
        }
    }

    private void a(CommentItem commentItem, View view) {
        if (!commentItem.isAlreadyDingCai()) {
            ImageView imageView = (ImageView) view.findViewById(R.id.commentDingIv);
            TextView textView = (TextView) view.findViewById(R.id.commentLikeCount);
            String id = commentItem.getId();
            com.budejie.www.util.d.a(this.o, (View) imageView.getParent(), "1");
            imageView.setSelected(true);
            textView.setSelected(true);
            int i = 0;
            try {
                i = Integer.parseInt(commentItem.getDingCount());
            } catch (Exception e) {
                Log.i("Commend-Ding", e.toString());
            }
            i++;
            textView.setText(String.valueOf(i));
            commentItem.setDingOrCai("like");
            commentItem.setDingCount(i + "");
            this.ad.a(id, "like");
            this.af.a(id, "like");
        }
    }

    private void b(CommentItem commentItem, View view) {
        if (!commentItem.isAlreadyDingCai()) {
            ImageView imageView = (ImageView) view.findViewById(R.id.commentCaiIv);
            TextView textView = (TextView) view.findViewById(R.id.commentCaiCount);
            String id = commentItem.getId();
            com.budejie.www.util.d.a(this.o, (View) imageView.getParent(), "1");
            imageView.setSelected(true);
            textView.setSelected(true);
            int i = 0;
            try {
                i = Integer.parseInt(commentItem.getCaiCount());
            } catch (Exception e) {
                Log.i("Commend-Ding", e.toString());
            }
            i++;
            textView.setText(String.valueOf(i));
            commentItem.setDingOrCai("hate");
            commentItem.setCaiCount(i + "");
            this.ad.a(id, "hate");
            this.af.a(id, "hate");
        }
    }

    private void d(String str) {
        BudejieApplication.a.a(RequstMethod.GET, com.budejie.www.http.j.j(str), this.aR, this.bK);
    }

    private void e(String str) {
        BudejieApplication.a.a(RequstMethod.GET, "https://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().k(this, str, this.aG), this.g);
        this.bt.sendEmptyMessage(970);
    }

    private void K() {
        BudejieApplication.a.a(RequstMethod.GET, "https://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().p(this, this.aG), this.h);
        this.bt.sendEmptyMessage(970);
    }

    private void a(int i, final CommentItem commentItem, View view) {
        int parseInt;
        final Dialog dialog = new Dialog(this.o, R.style.DialogTheme_CreateUgc);
        LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) this.o.getSystemService("layout_inflater")).inflate(R.layout.alert_item_latout, null);
        linearLayout.setMinimumWidth(10000);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.content_list);
        linearLayout2.setBackgroundResource(com.budejie.www.util.j.aC);
        Button button = (Button) linearLayout.findViewById(R.id.btn_cancel);
        button.setBackgroundResource(com.budejie.www.util.j.aC);
        button.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommendDetailOld b;

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelOffset(R.dimen.list_item_writer_profile));
        this.aV.clear();
        if (commentItem.getFloorNum() != null) {
            try {
                parseInt = Integer.parseInt(commentItem.getFloorNum());
            } catch (Exception e) {
                parseInt = 0;
            }
        } else if (commentItem.getReplyList() == null || commentItem.getReplyList().size() <= 0) {
            parseInt = 0;
        } else {
            try {
                parseInt = Integer.parseInt(((CommentItem) commentItem.getReplyList().get(commentItem.getReplyList().size() - 1)).getFloorNum()) + 1;
            } catch (Exception e2) {
                parseInt = 0;
            }
        }
        if (parseInt < 50) {
            if (this.aw == null || this.aw.getUid() == null) {
                this.aV.add("回复");
            } else if (!commentItem.getUid().equals(this.Z)) {
                this.aV.add("回复");
            }
            this.aV.add("语音回复");
        }
        if (this.aw != null && this.aw.getUid() != null && commentItem.getUid().equals(this.Z)) {
            this.aQ = i;
            this.aV.add("删除");
        } else if (this.aw == null || this.aw.getUid() == null || !this.aw.getUid().equals(this.Z)) {
            this.aV.add("举报");
        } else {
            this.aQ = i;
            this.aV.add("删除");
        }
        if (an.v(this.o)) {
            this.aV.add("删除");
            this.aV.add("删除并拉黑");
        }
        if (commentItem.getType().equals("audio")) {
            this.aV.add("转文字");
        }
        for (int i2 = 0; i2 < this.aV.size(); i2++) {
            String str = (String) this.aV.get(i2);
            View textView = new TextView(this.o);
            textView.setGravity(17);
            textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.item_title_name_color));
            textView.setText(str);
            textView.setTextSize(2, 17.0f);
            textView.setBackgroundResource(com.budejie.www.util.j.aC);
            textView.setTag(Integer.valueOf(i2));
            if (str.equals("删除")) {
                this.aG = commentItem.getId();
                textView.setOnClickListener(new d(this, dialog));
            } else if (str.equals("删除并拉黑")) {
                this.aG = commentItem.getId();
                textView.setOnClickListener(new c(this, dialog));
            } else if (str.equals("拉黑")) {
                this.aG = commentItem.getId();
                textView.setOnClickListener(new a(this, dialog));
            } else if (str.equals("转文字")) {
                this.aG = commentItem.getId();
                textView.setOnClickListener(new l(this, dialog));
            } else if (str.equals("回复")) {
                this.aG = commentItem.getId();
                textView.setOnClickListener(new k(this, dialog, commentItem));
            } else if (str.equals("语音回复")) {
                this.aG = commentItem.getId();
                textView.setOnClickListener(new k(this, dialog, commentItem, 1));
            } else {
                textView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ CommendDetailOld c;

                    public void onClick(View view) {
                        dialog.dismiss();
                        if (((String) this.c.aV.get(Integer.parseInt("" + view.getTag()))).equals("举报")) {
                            String string = this.c.ai.getString("id", "");
                            String id = commentItem.getId();
                            this.c.ad.a(this.c.getApplicationContext(), this.c.Y, id, string);
                        }
                    }
                });
            }
            textView.setLayoutParams(layoutParams);
            if (i2 == 0) {
                linearLayout2.addView(textView);
            } else {
                View imageView = new ImageView(this.o);
                imageView.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.divider_horizontal_bg));
                imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelOffset(R.dimen.divide_line_height)));
                linearLayout2.addView(imageView);
                linearLayout2.addView(textView);
            }
        }
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = -1000;
        attributes.gravity = 80;
        dialog.onWindowAttributesChanged(attributes);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(linearLayout);
        dialog.show();
    }

    public void a(int i, String str) {
        if (i == 1111113) {
            if (!this.aH) {
                this.aL.a((com.budejie.www.adapter.b) this.aM.getTag());
            }
            this.bt.sendMessage(this.bt.obtainMessage(1, str));
        } else if (i == 1111114) {
            this.U++;
            this.bt.sendMessage(this.bt.obtainMessage(7, str));
        }
    }

    public void a(int i) {
        if (i == 1111113) {
            this.bt.sendEmptyMessage(3);
        } else if (i == 1111114) {
            this.bt.sendEmptyMessage(3);
        }
    }

    public void c() {
        a(true);
    }

    private void L() {
        p();
        l();
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        super.onComplete(jSONObject);
        HashMap a = z.a(jSONObject);
        if (a != null && a.size() != 0) {
            this.ai.edit().putString("openid", (String) a.get("qzone_uid")).putString("qzone_token", (String) a.get("qzone_token")).putString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN, (System.currentTimeMillis() + (Long.parseLong((String) a.get(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN)) * 1000)) + "").commit();
            this.Z = this.ai.getString("id", "");
            this.ac.a((String) a.get("qzone_uid"), this.Z, (String) a.get("qzone_token"), 10, this.bt);
        }
    }

    public void onError(UiError uiError) {
        super.onError(uiError);
        Toast.makeText(this.o, "code:" + uiError.errorCode + ", msg:" + uiError.errorMessage + ", detail:" + uiError.errorDetail, 0).show();
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        super.onSuccess(bVar);
        try {
            this.Z = this.ai.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this.o, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.ac.a(mAccessToken, this.Z, 4, this.bt);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    public void onrefreshTheme() {
        super.onrefreshTheme();
        this.ao.setBackgroundResource(com.budejie.www.util.j.a);
        this.q.setTextColor(this.av.getColor(com.budejie.www.util.j.b));
        this.an.setBackgroundResource(com.budejie.www.util.j.Y);
        if (this.O != null) {
            this.O.setBackgroundResource(com.budejie.www.util.j.u);
            this.ap.setBackgroundResource(com.budejie.www.util.j.a);
            this.L.setTextColor(this.av.getColor(com.budejie.www.util.j.b));
            this.M.setTextColor(this.av.getColor(com.budejie.www.util.j.aj));
            onRefreshTitleFontTheme(this.J, false);
            onRefreshTitleFontTheme(this.K, false);
        }
        if (this.v != null) {
            this.v.notifyDataSetChanged();
        }
        onRefreshTitleFontTheme(this.p, true);
    }

    public void a(int i, Object obj, boolean z) {
        switch (i) {
            case 0:
                if (z) {
                    this.at = false;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void bindTencent() {
        this.Z = this.ai.getString("id", "");
        String sharePersistent = Util.getSharePersistent(this.o, "ACCESS_TOKEN");
        this.ac.a(Util.getSharePersistent(this.o, "NAME"), sharePersistent, Util.getSharePersistent(this.o, "OPEN_ID"), this.Z, 5, this.bt);
    }

    public void e(View view, ListItemObject listItemObject) {
        if (listItemObject != null && !TextUtils.isEmpty(listItemObject.getWid()) && !listItemObject.getWid().equals(this.aw.getWid())) {
            view.setTag(listItemObject);
            this.bu.a(3, null).onClick(view);
        }
    }

    public void a(View view, ListItemObject listItemObject, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(PersonalProfileActivity.c, listItemObject.getUid());
        bundle.putString(PersonalProfileActivity.d, str);
        this.bu.a(7, bundle).onClick(view);
    }

    public void c(View view, ListItemObject listItemObject) {
        p.a(this, listItemObject, this.f, c(listItemObject.getUid()), this.aN);
    }

    public boolean c(String str) {
        if (TextUtils.isEmpty(str) || !str.equals(this.Z)) {
            return false;
        }
        return true;
    }

    public void a(View view, ListItemObject listItemObject) {
        TipPopUp.a((Context) this, TypeControl.dingtie);
        this.bv.a("ding", this.f, listItemObject);
        this.bv.a(listItemObject, this.f, "ding");
    }

    public void b(View view, ListItemObject listItemObject) {
        this.bv.a("cai", this.f, listItemObject);
        this.bv.a(listItemObject, this.f, "cai");
    }

    public void a(View view, ListItemObject listItemObject, int i) {
        listItemObject.setForwardNoCollect(false);
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this));
        bundle.putSerializable("weiboMap", this.bz);
        bundle.putSerializable("data", listItemObject);
        view.setTag(listItemObject);
        this.bu.a(5, bundle, this.f, this.bB, this.by, this.ac, this.ah, this.bA, this.bt).onClick(view);
    }

    public void d(View view, ListItemObject listItemObject) {
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
        Intent intent = new Intent(this, CommonLabelActivity.class);
        PlateBean plateBean = listItemObject.getPlateBean(i);
        if (plateBean != null) {
            intent.putExtra("theme_name", plateBean.theme_name);
            intent.putExtra("theme_id", plateBean.theme_id);
            this.o.startActivity(intent);
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_exit_left_in, R.anim.push_right_out);
    }

    public void e() {
        switch (this.bf) {
            case NORMAL:
                this.ax.hideSoftInputFromWindow(this.z.getWindowToken(), 0);
                this.B.setVisibility(8);
                this.C.setVisibility(8);
                this.E.setVisibility(8);
                this.R = null;
                this.br.setText("写评论...");
                aq.a(this.o, com.budejie.www.h.c.a().b(R.attr.title_background));
                return;
            case KEYBOARD:
                this.z.setFocusable(true);
                this.z.requestFocus();
                this.ax.showSoftInput(this.z, 2);
                this.B.setVisibility(8);
                this.C.setVisibility(8);
                return;
            case VOICE:
                this.C.setVisibility(8);
                com.budejie.www.activity.video.k.a(this.o).h();
                this.ax.hideSoftInputFromWindow(this.z.getWindowToken(), 0);
                if (!this.bg) {
                    g();
                    this.B.setVisibility(0);
                }
                this.E.setVisibility(0);
                aq.a(this.o, R.color.record_voice_mask_bar_color);
                return;
            case OPERATE:
                this.B.setVisibility(8);
                com.budejie.www.activity.video.k.a(this.o).h();
                this.ax.hideSoftInputFromWindow(this.z.getWindowToken(), 0);
                if (!this.bg) {
                    this.C.setVisibility(0);
                }
                this.E.setVisibility(0);
                aq.a(this.o, R.color.record_voice_mask_bar_color);
                return;
            default:
                return;
        }
    }

    private void b(String str, String str2) {
        this.G.setVisibility(0);
        this.I.setVisibility(0);
        this.H.setTag(str2);
        this.bj.a(this.H, str, str2, this.l);
    }

    private void M() {
        if (this.H != null) {
            this.H.setImageBitmap(null);
            this.G.setVisibility(8);
        }
        if (this.I != null) {
            this.I.setVisibility(8);
        }
    }

    private void f(String str) {
        final Dialog dialog = new Dialog(this.o, R.style.dialogTheme);
        View inflate = this.o.getLayoutInflater().inflate(R.layout.mycomment_delete_dialog, null);
        Button button = (Button) inflate.findViewById(R.id.mycomment_delete_cancelBtn);
        Button button2 = (Button) inflate.findViewById(R.id.mycomment_delete_sureBtn);
        button2.setTag(str);
        button.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommendDetailOld b;

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        button2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommendDetailOld b;

            public void onClick(View view) {
                this.b.K();
                dialog.dismiss();
            }
        });
        dialog.setContentView(inflate);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = an.a(this.o, 300);
        window.setAttributes(attributes);
        dialog.show();
    }

    public void onEventMainThread(DetailAction detailAction) {
        if (DetailAction.PUB_VIDEO_CLOSE == detailAction) {
            y();
        } else if (DetailAction.SCREEN_SHOT == detailAction) {
            N();
        }
    }

    private void N() {
        this.d = new al(this.o, findViewById(R.id.listview_container), an.y(this.o) * 4);
        this.d.a((int) R.drawable.screen_shoot_bottom);
        this.d.a(this.bq, new com.budejie.www.util.al.b(this) {
            final /* synthetic */ CommendDetailOld a;

            {
                this.a = r1;
            }

            public void a(String str) {
                this.a.d = null;
                if (this.a.bo != null) {
                    this.a.bo.setVisibility(8);
                    aq.a(this.a.o, com.budejie.www.h.c.a().b(R.attr.title_background));
                }
                if (!TextUtils.isEmpty(str)) {
                    Intent intent = new Intent(this.a.o, HotCommentShareActivity.class);
                    intent.putExtra("hot_comment_share_post", this.a.aw);
                    intent.putExtra("hot_comment_share_image", str);
                    this.a.o.startActivity(intent);
                }
            }

            public void a() {
                if (this.a.bp != null) {
                    this.a.bp.setText(R.string.screen_shot_processing);
                    this.a.bp.setClickable(false);
                }
            }

            public void b() {
                if (this.a.bo != null) {
                    this.a.bo.setVisibility(0);
                    if (this.a.bp != null) {
                        this.a.bp.setText(R.string.screen_shot_stop);
                        this.a.bp.setClickable(true);
                    }
                    aq.a(this.a.o, R.color.barrage_full_screen_bg);
                }
            }
        });
    }

    public void f() {
        Log.d(this.n, "stopCommentVoice");
        BudejieApplication budejieApplication = (BudejieApplication) this.o.getApplication();
        if (budejieApplication.a() == null || budejieApplication.a() == Status.end) {
            ac a = ac.a(this.o);
            if (a.c()) {
                a.i();
            }
        }
    }

    public void g() {
        f();
        BudejieApplication budejieApplication = (BudejieApplication) this.o.getApplication();
        com.budejie.www.service.MediaPlayerServer.a f = budejieApplication.f();
        if (f == null) {
            return;
        }
        if (f.a() || f.n()) {
            f.d();
            budejieApplication.a(Status.end);
        }
    }
}
