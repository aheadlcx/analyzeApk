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
import com.budejie.www.busevent.SimpleVideoClickAction;
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
import com.budejie.www.util.q;
import com.budejie.www.util.v;
import com.budejie.www.util.w;
import com.budejie.www.util.z;
import com.budejie.www.widget.CommentOperateView;
import com.budejie.www.widget.KeyboardListenerRelativeLayout;
import com.budejie.www.widget.NewTitleView;
import com.budejie.www.widget.RecordVoiceView;
import com.budejie.www.widget.VoteView;
import com.budejie.www.widget.curtain.BarrageStatusManager;
import com.budejie.www.widget.curtain.BarrageStatusManager.BarrageState;
import com.budejie.www.widget.curtain.FloatVideoRootLayout;
import com.budejie.www.widget.parsetagview.NewParseTagEditText;
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
public class CommendDetail extends OauthWeiboBaseAct implements OnClickListener, com.budejie.www.activity.view.CustomListView.c, com.budejie.www.adapter.e.a, com.budejie.www.f.a, com.budejie.www.g.a {
    public static String b;
    private static boolean bG = true;
    private View A;
    private View B;
    private RelativeLayout C;
    private ImageView D;
    private ImageView E;
    private TextView F;
    private TextView G;
    private TextView H;
    private TextView I;
    private EditText J;
    private RelativeLayout K;
    private Toast L;
    private Intent M;
    private CommentItem N;
    private int O;
    private int P;
    private int Q = 1;
    private int R = -1;
    private int S;
    private int T = R$styleable.Theme_Custom_send_btn_text_color;
    private String U;
    private String V;
    private boolean W = true;
    private boolean X = false;
    private n Y;
    private com.budejie.www.http.c Z;
    PopupWindow a;
    private String aA;
    private boolean aB = false;
    private boolean aC = false;
    private View aD;
    private View aE;
    private View aF;
    private com.budejie.www.adapter.d.d aG;
    private com.budejie.www.adapter.d.b aH;
    private com.budejie.www.adapter.d.c aI;
    private View aJ;
    private View aK;
    private View aL;
    private Dialog aM;
    private LinearLayout aN;
    private boolean aO = true;
    private int aP;
    private com.budejie.www.http.j aQ;
    private String aR;
    private String aS;
    private int aT;
    private ArrayList<String> aU;
    private boolean aV;
    private RelativeLayout aW;
    private RelativeLayout aX;
    private TextView aY;
    private TextView aZ;
    private com.budejie.www.util.n aa;
    private com.budejie.www.c.e ab;
    private com.budejie.www.c.g ac;
    private com.elves.update.a ad;
    private SharedPreferences ae;
    private ArrayList<CommentItem> af = new ArrayList();
    private boolean ag = false;
    private boolean ah = false;
    private boolean ai = true;
    private FloatVideoRootLayout aj;
    private final String ak = "unlike";
    private final String al = "20";
    private String am;
    private boolean an = false;
    private boolean ao = false;
    private Resources ap;
    private ListItemObject aq;
    private InputMethodManager ar;
    private String as = "";
    private String at = "";
    private File au;
    private File av;
    private File aw;
    private File ax;
    private int ay;
    private int az;
    private HashMap<String, String> bA;
    private SharedPreferences bB;
    private IWXAPI bC;
    private String bD = "add";
    private ProgressDialog bE;
    private OnClickListener bF = new OnClickListener(this) {
        final /* synthetic */ CommendDetail a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
        }
    };
    private com.budejie.www.activity.view.CustomListView.b bH = new com.budejie.www.activity.view.CustomListView.b(this) {
        final /* synthetic */ CommendDetail a;

        {
            this.a = r1;
        }

        public void a() {
            if (!an.a(this.a.q)) {
                this.a.L = an.a(this.a.q, this.a.q.getString(R.string.nonet), -1);
                this.a.L.show();
            } else if (this.a.ai && this.a.r.a == 3) {
                this.a.p();
                this.a.H();
                if (!this.a.aV) {
                    this.a.aV = true;
                    this.a.A();
                }
            }
        }
    };
    private com.budejie.www.activity.view.CustomListView.a bI = new com.budejie.www.activity.view.CustomListView.a(this) {
        final /* synthetic */ CommendDetail a;

        {
            this.a = r1;
        }

        public void a(int i) {
            Log.d(this.a.p, "onScrollStateChanged onFling");
            if (this.a.a != null && this.a.a.isShowing()) {
                this.a.a.dismiss();
            }
        }
    };
    private OnScrollListener bJ = new OnScrollListener(this) {
        final /* synthetic */ CommendDetail a;

        {
            this.a = r1;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            Log.d(this.a.p, "onScrollStateChanged scrollState" + i);
            if (i == 1) {
                this.a.bp = true;
            } else if (i == 0) {
                this.a.bp = false;
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (com.budejie.www.activity.video.k.a(this.a.q).c) {
                com.budejie.www.activity.video.k.a(this.a.q).b(absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition(), 1);
            }
            if (this.a.q.getRequestedOrientation() != 0) {
                boolean z;
                NewTitleView ap;
                if (i == 0) {
                    View childAt = this.a.r.getChildAt(0);
                    if (childAt != null && childAt.getTop() == 0) {
                        Log.d("ListView", "##### 滚动到顶部 #####");
                        z = true;
                        if (this.a.bo != null) {
                            ap = this.a.bo;
                            z = z && this.a.m;
                            ap.a(z);
                        }
                    }
                }
                z = false;
                if (this.a.bo != null) {
                    ap = this.a.bo;
                    if (!z) {
                    }
                    ap.a(z);
                }
            }
            if (this.a.be != BottomStatus.VOICE) {
                if (absListView.getLastVisiblePosition() >= 3) {
                    if (!this.a.u.isShown()) {
                        this.a.u.setVisibility(0);
                    }
                    float ar = (float) (((this.a.P - this.a.getResources().getDimensionPixelSize(R.dimen.navigation_height)) - an.t(this.a.q)) + 2);
                    float ar2 = (float) ((this.a.P - an.t(this.a.q)) + 2);
                    Log.d(this.a.p, "minY=" + ar);
                    Log.d(this.a.p, "maxY=" + ar2);
                    if (absListView.getLastVisiblePosition() == 3) {
                        int[] iArr = new int[2];
                        absListView.getChildAt(i2 - 1).getLocationOnScreen(iArr);
                        float t = (float) ((iArr[1] - an.t(this.a.q)) + 2);
                        Log.d(this.a.p, "location[1]=" + iArr[1]);
                        if (t < ar2) {
                            ar2 = t <= ar ? ar : t;
                        }
                    } else {
                        ar2 = ar;
                    }
                    Log.d(this.a.p, "Y=" + ar2);
                    Log.d(this.a.p, "recordBottomFrame.getY()=" + this.a.u.getY());
                    if (ar2 != this.a.u.getY()) {
                        this.a.u.setY(ar2);
                    }
                } else if (this.a.u.isShown()) {
                    this.a.u.setVisibility(8);
                }
            }
            if (absListView.getLastVisiblePosition() == ((ListAdapter) absListView.getAdapter()).getCount() - 1) {
                this.a.bn = true;
                if (this.a.e != null) {
                    this.a.e.a(false);
                    return;
                }
                return;
            }
            this.a.bn = false;
        }
    };
    private net.tsz.afinal.a.a<String> bK = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CommendDetail a;

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
            this.a.aV = false;
            this.a.r.b();
            this.a.n();
            this.a.o();
            this.a.r.a = 3;
            if (this.a.af.isEmpty() && this.a.W) {
                an.a(this.a.q, this.a.getString(R.string.data_failed), -1).show();
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
                    this.a.a.n();
                }

                protected void a(ArrayList<CommentItem> arrayList) {
                    switch (this.a.a.R) {
                        case 0:
                            this.a.a.n();
                            this.a.a.r.a = 3;
                            this.a.a.r.b();
                            this.a.a.r.setOnRefreshListener(this.a.a);
                            if (this.a.a.af.isEmpty()) {
                                this.a.a.k();
                                this.a.a.aV = false;
                            } else {
                                this.a.a.e();
                                this.a.a.l();
                                this.a.a.a(this.a.a.af);
                                this.a.a.r.setVisibility(0);
                                this.a.a.v();
                            }
                            if (this.a.a.aS == null || this.a.a.aS.equals("null")) {
                                this.a.a.z();
                            }
                            if (!this.a.a.aC) {
                                return;
                            }
                            if (this.a.a.m) {
                                this.a.a.r.setSelection(2);
                                this.a.a.r.smoothScrollToPositionFromTop(3, this.a.a.getResources().getDimensionPixelSize(R.dimen.comment_detail_title_height), 10);
                                return;
                            }
                            this.a.a.r.setSelection(3);
                            return;
                        case 1:
                            this.a.a.aV = false;
                            this.a.a.a(this.a.a.af);
                            this.a.a.t.notifyDataSetChanged();
                            return;
                        case 2:
                            this.a.a.aV = false;
                            this.a.a.a(this.a.a.af);
                            this.a.a.t.notifyDataSetChanged();
                            if (this.a.a.aS == null || this.a.a.aS.equals("null")) {
                                this.a.a.z();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }

                protected ArrayList<CommentItem> a(String... strArr) {
                    String str = strArr[0];
                    this.a.a.r.a = 3;
                    try {
                        if (this.a.a.R == 0) {
                            this.a.a.af = z.a(str, "0", this.a.a.q);
                        } else if (this.a.a.R == 1) {
                            Collection a = z.a(str, "1", this.a.a.q);
                            if (a != null && a.size() > 0) {
                                for (int i = 0; i < this.a.a.af.size(); i++) {
                                    CommentItem commentItem = (CommentItem) this.a.a.af.get(i);
                                    if (commentItem.getHotNp() != null) {
                                        commentItem.setHotNp(null);
                                        this.a.a.af.addAll(i + 1, a);
                                        break;
                                    }
                                }
                            }
                        } else if (this.a.a.R == 2) {
                            this.a.a.af.addAll(z.a(str, "2", this.a.a.q));
                        }
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject != null) {
                            if (jSONObject.has(StatisticCodeTable.HOT)) {
                                JSONObject jSONObject2 = jSONObject.getJSONObject(StatisticCodeTable.HOT);
                                if (jSONObject2.has("info")) {
                                    this.a.a.aR = jSONObject2.getJSONObject("info").getString("np");
                                }
                            }
                            if (jSONObject.has("normal")) {
                                jSONObject = jSONObject.getJSONObject("normal");
                                if (jSONObject.has("info")) {
                                    this.a.a.aS = jSONObject.getJSONObject("info").getString("np");
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return this.a.a.af;
                }
            }.execute(new String[]{str});
        }
    };
    private net.tsz.afinal.a.a<String> bL = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CommendDetail a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
            this.a.aW.setVisibility(0);
            this.a.aX.setVisibility(0);
            this.a.aY.setVisibility(8);
            this.a.bb.setVisibility(8);
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            this.a.aX.setVisibility(8);
            this.a.aY.setVisibility(8);
            this.a.bb.setVisibility(0);
            this.a.bc.setText("语音转换失败");
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
                    if (this.a.a.aX != null) {
                        this.a.a.aX.setVisibility(8);
                    }
                    this.a.a.aY.setVisibility(0);
                    if (commentVoiceToWordsItem == null) {
                        this.a.a.aY.setVisibility(8);
                        this.a.a.bb.setVisibility(0);
                        this.a.a.bc.setText("语音转换失败");
                    } else if (commentVoiceToWordsItem.getCode() == null || !commentVoiceToWordsItem.getCode().equals("1")) {
                        this.a.a.aY.setVisibility(8);
                        this.a.a.bb.setVisibility(0);
                        CharSequence charSequence = "语音转换失败";
                        if (commentVoiceToWordsItem.getInfo() != null && commentVoiceToWordsItem.getInfo().length() > 0) {
                            charSequence = commentVoiceToWordsItem.getInfo();
                        }
                        this.a.a.bc.setText(charSequence);
                    } else {
                        this.a.a.aY.setText(commentVoiceToWordsItem.getMsg());
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
    private net.tsz.afinal.a.a<String> bM = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CommendDetail a;

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
                    this.a.a.n();
                }

                protected void a(CommentItem commentItem) {
                    this.a.a.t.notifyDataSetChanged();
                }

                protected CommentItem a(String... strArr) {
                    String str = strArr[0];
                    CommentItem commentItem = null;
                    if (this.a.a.aT != -1) {
                        commentItem = (CommentItem) this.a.a.af.get(this.a.a.aT);
                    }
                    if (commentItem != null) {
                        try {
                            commentItem = z.a(str);
                            if (this.a.a.aT == 0) {
                                commentItem.setIsnew(true);
                                commentItem.setTagIsShow(true);
                            }
                            this.a.a.a(commentItem);
                            this.a.a.af.remove(this.a.a.aT);
                            this.a.a.af.add(this.a.a.aT, commentItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return commentItem;
                }
            }.execute(new String[]{str});
        }
    };
    private com.budejie.www.widget.KeyboardListenerRelativeLayout.a bN = new com.budejie.www.widget.KeyboardListenerRelativeLayout.a(this) {
        final /* synthetic */ CommendDetail a;

        {
            this.a = r1;
        }

        public void a(boolean z) {
            if (BarrageStatusManager.a(this.a.bB) != BarrageState.CLOSE && com.budejie.www.activity.video.k.a(this.a).b()) {
                return;
            }
            if (z) {
                this.a.bf = true;
                this.a.bt.sendEmptyMessageDelayed(16, 1);
                return;
            }
            this.a.B.setVisibility(8);
            this.a.bf = false;
            if (this.a.be == BottomStatus.VOICE) {
                this.a.bO.sendEmptyMessage(1);
            } else if (this.a.be == BottomStatus.OPERATE) {
                this.a.bt.sendEmptyMessage(18);
            } else if (System.currentTimeMillis() - this.a.bh > 1000) {
                this.a.be = BottomStatus.NORMAL;
                this.a.d();
            }
        }
    };
    private Handler bO = new Handler(this) {
        final /* synthetic */ CommendDetail a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            Log.d(this.a.p, "recordVoiceViewHandler msg=" + message.what);
            switch (message.what) {
                case 1:
                    this.a.f();
                    this.a.x.setVisibility(0);
                    return;
                case 3:
                    this.a.av = (File) message.obj;
                    this.a.az = message.arg1;
                    this.a.a(true, false);
                    return;
                case 4:
                    this.a.be = BottomStatus.NORMAL;
                    this.a.d();
                    return;
                default:
                    return;
            }
        }
    };
    private ScrollView ba;
    private LinearLayout bb;
    private TextView bc;
    private boolean bd;
    private BottomStatus be = BottomStatus.NORMAL;
    private boolean bf;
    private com.budejie.www.widget.f bg;
    private long bh;
    private BitmapCache bi;
    private int bj = 5;
    private String bk;
    private RelativeLayout bl;
    private TextView bm;
    private boolean bn;
    private NewTitleView bo;
    private boolean bp = false;
    private String bq;
    private boolean br;
    private boolean bs = false;
    private Handler bt = new Handler(this) {
        final /* synthetic */ CommendDetail a;

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
                        this.a.n();
                        this.a.r.a = 3;
                        this.a.af = z.a((String) message.obj, true);
                        this.a.r.b();
                        this.a.r.setOnRefreshListener(this.a);
                        if (this.a.af.isEmpty()) {
                            this.a.k();
                        } else {
                            this.a.l();
                            this.a.a(this.a.af);
                            this.a.r.setVisibility(0);
                            this.a.v();
                        }
                        int parseInt = Integer.parseInt(TextUtils.isEmpty(this.a.am) ? "0" : this.a.am);
                        if (this.a.t == null || this.a.t.getCount() < parseInt) {
                            this.a.z();
                            Log.i("CommendDetail", "getCount:" + this.a.t.getCount() + ",comCount:" + parseInt);
                            Log.i("CommendDetail", "1调用暂无评论");
                            return;
                        }
                        return;
                    case 3:
                        this.a.r.b();
                        this.a.n();
                        this.a.o();
                        this.a.r.a = 3;
                        if (this.a.af.isEmpty() && this.a.W) {
                            an.a(this.a.q, this.a.getString(R.string.data_failed), -1).show();
                            return;
                        }
                        return;
                    case 4:
                        str = (String) message.obj;
                        if (TextUtils.isEmpty(str)) {
                            an.a(this.a.q, this.a.getString(R.string.bind_failed), -1).show();
                            MobclickAgent.onEvent(this.a.q, "weibo_bind", "sina_faild");
                        } else {
                            try {
                                i = Integer.parseInt(str);
                            } catch (NumberFormatException e) {
                            }
                            if (i < 0) {
                                an.a(this.a.q, this.a.getString(R.string.bind_failed), -1).show();
                                MobclickAgent.onEvent(this.a.q, "weibo_bind", "sina_faild");
                            } else {
                                c = z.c(str);
                                if (c == null || c.isEmpty()) {
                                    MobclickAgent.onEvent(this.a.q, "weibo_bind", "sina_faild");
                                } else {
                                    str2 = (String) c.get("result_msg");
                                    if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                        MobclickAgent.onEvent(this.a.q, "weibo_bind", "sina_success");
                                        this.a.V = (String) c.get("id");
                                        this.a.bz.a(this.a.V, c);
                                        ai.a(this.a.q, this.a.V, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                        if (OauthWeiboBaseAct.mAccessToken != null) {
                                            this.a.bz.a(this.a.V, OauthWeiboBaseAct.mAccessToken.e());
                                        }
                                        this.a.bA = this.a.Y.a(this.a.V);
                                        this.a.Y.a(this.a.q, this.a.aq, "sina", this.a.V, this.a.bA, this.a.ad, this.a.bt);
                                    } else {
                                        an.a(this.a.q, str2, -1).show();
                                    }
                                }
                            }
                        }
                        this.a.s.dismiss();
                        return;
                    case 5:
                        str = (String) message.obj;
                        if (TextUtils.isEmpty(str)) {
                            an.a(this.a.q, this.a.getString(R.string.bind_failed), -1).show();
                            MobclickAgent.onEvent(this.a.q, "weibo_bind", "tencent_faild");
                        } else {
                            try {
                                i = Integer.parseInt(str);
                            } catch (NumberFormatException e2) {
                            }
                            if (i < 0) {
                                this.a.L = an.a(this.a.q, this.a.q.getString(R.string.bind_failed), -1);
                                this.a.L.show();
                                MobclickAgent.onEvent(this.a.q, "weibo_bind", "tencent_faild");
                            } else {
                                c = z.c(str);
                                if (c == null || c.isEmpty()) {
                                    MobclickAgent.onEvent(this.a.q, "weibo_bind", "tencent_faild");
                                } else {
                                    str2 = (String) c.get("result_msg");
                                    if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                        MobclickAgent.onEvent(this.a.q, "weibo_bind", "tencent_success");
                                        this.a.V = (String) c.get("id");
                                        this.a.bz.a(this.a.V, c);
                                        ai.a(this.a.q, this.a.V, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                        this.a.bA = this.a.Y.a(this.a.V);
                                        this.a.Y.a(this.a.q, this.a.aq, "qq", this.a.V, this.a.bA, this.a.ad, this.a.bt);
                                    } else {
                                        an.a(this.a.q, str2, -1).show();
                                    }
                                }
                            }
                        }
                        this.a.s.dismiss();
                        return;
                    case 7:
                        List a = z.a((String) message.obj, false);
                        Log.i("CommendDetail", "请求更多数据：" + a.size());
                        if (a.isEmpty()) {
                            this.a.z();
                            this.a.n();
                            return;
                        }
                        this.a.a(a);
                        this.a.af.addAll(a);
                        this.a.t.notifyDataSetChanged();
                        return;
                    case 8:
                        str = (String) message.obj;
                        if (TextUtils.isEmpty(str)) {
                            this.a.L = an.a(this.a.q, this.a.q.getString(R.string.forwarfail), -1);
                            this.a.L.show();
                            return;
                        } else if (!"0".equals(str)) {
                            return;
                        } else {
                            return;
                        }
                    case 9:
                        str = (String) message.obj;
                        if (TextUtils.isEmpty(str)) {
                            this.a.L = an.a(this.a.q, this.a.q.getString(R.string.forwarfail), -1);
                            this.a.L.show();
                            return;
                        } else if (!"0".equals(str)) {
                            this.a.L = an.a(this.a.q, this.a.q.getString(R.string.forwarfail), -1);
                            this.a.L.show();
                            return;
                        } else {
                            return;
                        }
                    case 10:
                        str = (String) message.obj;
                        if (TextUtils.isEmpty((String) message.obj)) {
                            this.a.L = an.a(this.a.q, this.a.q.getString(R.string.bind_failed), -1);
                            this.a.L.show();
                            MobclickAgent.onEvent(this.a.q, "weibo_bind", "qzone_faild");
                        } else {
                            int parseInt2;
                            try {
                                parseInt2 = Integer.parseInt(str);
                            } catch (NumberFormatException e3) {
                                parseInt2 = i;
                            }
                            if (parseInt2 < 0) {
                                this.a.L = an.a(this.a.q, this.a.q.getString(R.string.bind_failed), -1);
                                this.a.L.show();
                                MobclickAgent.onEvent(this.a.q, "weibo_bind", "qzone_faild");
                            } else {
                                c = z.c(str);
                                if (c == null || c.isEmpty()) {
                                    MobclickAgent.onEvent(this.a.q, "weibo_bind", "qzone_faild");
                                } else {
                                    str2 = (String) c.get("result_msg");
                                    if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                                        MobclickAgent.onEvent(this.a.q, "weibo_bind", "qzone_success");
                                        this.a.V = (String) c.get("id");
                                        this.a.bz.a(this.a.V, c);
                                        ai.a(this.a.q, this.a.V, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                    } else {
                                        an.a(this.a.q, str2, -1).show();
                                    }
                                }
                            }
                        }
                        this.a.s.dismiss();
                        return;
                    case 11:
                        str = (String) message.obj;
                        if (TextUtils.isEmpty(str)) {
                            this.a.L = an.a(this.a.q, this.a.q.getString(R.string.forwarfail), -1);
                            this.a.L.show();
                            return;
                        } else if (!"0".equals(str)) {
                            this.a.L = an.a(this.a.q, this.a.q.getString(R.string.forwarfail), -1);
                            this.a.L.show();
                            return;
                        } else {
                            return;
                        }
                    case 12:
                        Map u = z.u((String) message.obj);
                        if (u != null) {
                            if ("1".equals((String) u.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY))) {
                                if (this.a.aB) {
                                    RichPostDetail.a++;
                                }
                                if (this.a.bg != null) {
                                    this.a.bg.a(true, (String) u.get(SocialConstants.PARAM_APP_DESC));
                                }
                                this.a.b(true);
                                this.a.r.setSelection(2);
                                return;
                            } else if (this.a.bg != null) {
                                this.a.bg.a(false, (String) u.get(SocialConstants.PARAM_APP_DESC));
                                return;
                            } else {
                                return;
                            }
                        } else if (this.a.bg != null) {
                            this.a.bg.a(false, "");
                            return;
                        } else {
                            return;
                        }
                    case 13:
                        str = (String) message.obj;
                        if (TextUtils.isEmpty(str)) {
                            this.a.L = an.a(this.a.q, this.a.q.getString(R.string.commend_fail), -1);
                            this.a.L.show();
                        } else if ("0".equals(str)) {
                            if ("writeCommend".equals(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)) {
                                this.a.q.finish();
                            }
                            this.a.ad.a(this.a.S, true, (int) R.string.sendsuccess);
                            this.a.c(true);
                            return;
                        } else {
                            this.a.L = an.a(this.a.q, this.a.q.getString(R.string.commend_fail), -1);
                            this.a.L.show();
                        }
                        this.a.c(false);
                        return;
                    case 14:
                        this.a.finish();
                        return;
                    case 15:
                        Log.d(this.a.p, "case 15:");
                        this.a.be = BottomStatus.KEYBOARD;
                        this.a.d();
                        return;
                    case 16:
                        Log.d(this.a.p, "case 16:");
                        this.a.B.setVisibility(0);
                        return;
                    case 18:
                        this.a.y.setVisibility(0);
                        return;
                    case 19:
                        this.a.r.smoothScrollBy(1, 10);
                        this.a.r.smoothScrollBy(-1, 10);
                        this.a.u.requestLayout();
                        return;
                    case 816:
                        CharSequence string = ((Bundle) message.obj).getString(com.alipay.sdk.util.j.c);
                        if (TextUtils.isEmpty(string)) {
                            this.a.L = an.a(this.a.q, this.a.q.getString(R.string.forwarfail), -1);
                            this.a.L.show();
                            return;
                        } else if ("0".equals(string)) {
                            this.a.g.sendEmptyMessage(9);
                            return;
                        } else {
                            this.a.L = an.a(this.a.q, this.a.q.getString(R.string.forwarfail), -1);
                            this.a.L.show();
                            return;
                        }
                    case 817:
                        this.a.ad.a(((Integer) message.obj).intValue());
                        return;
                    case 930:
                        this.a.ad.a(this.a.S, false, (int) R.string.sendfail);
                        this.a.c(false);
                        return;
                    case 931:
                        this.a.ad.a(this.a.S);
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
    private com.budejie.www.widget.NewTitleView.a bu = new com.budejie.www.widget.NewTitleView.a(this) {
        final /* synthetic */ CommendDetail a;

        {
            this.a = r1;
        }

        public void a(View view, ListItemObject listItemObject) {
            this.a.q.a(view, this.a.aq);
        }

        public void b(View view, ListItemObject listItemObject) {
            this.a.b(view, this.a.aq);
        }

        public void a(View view) {
            this.a.a(view);
        }

        public void a(BarrageState barrageState) {
            if (com.budejie.www.activity.video.k.a(this.a.q).f != null) {
                com.budejie.www.activity.video.k.a(this.a.q).f.a(barrageState);
            }
        }

        public void c(View view, ListItemObject listItemObject) {
            this.a.a(view, this.a.aq, "");
        }
    };
    private com.budejie.www.g.b bv;
    private com.budejie.www.http.f bw;
    private com.budejie.www.http.b bx;
    private com.budejie.www.c.b by;
    private com.budejie.www.c.m bz;
    public boolean c = false;
    Map<String, View> d;
    al e;
    Reporter f;
    final Handler g = new Handler(this) {
        final /* synthetic */ CommendDetail a;

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
            r0 = r0.aq;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.aq;	 Catch:{ Exception -> 0x0034 }
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
            r1.bE = r0;	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x0034:
            r0 = move-exception;
            goto L_0x001c;
        L_0x0036:
            r1 = 6;
            if (r0 != r1) goto L_0x0043;
        L_0x0039:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.bE;	 Catch:{ Exception -> 0x0034 }
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
            r0 = r0.aq;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.getRepost();	 Catch:{ Exception -> 0x0034 }
            r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0034 }
            r0 = r0 + 1;
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.aq;	 Catch:{ Exception -> 0x0034 }
            r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x0034 }
            r1.setRepost(r0);	 Catch:{ Exception -> 0x0034 }
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.q;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.g;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.aq;	 Catch:{ Exception -> 0x0034 }
            com.budejie.www.util.m.a(r0, r1, r2);	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x008f:
            r1 = 91;
            if (r0 != r1) goto L_0x00b2;
        L_0x0093:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.aq;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.getRepost();	 Catch:{ Exception -> 0x0034 }
            r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0034 }
            r0 = r0 + 1;
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.aq;	 Catch:{ Exception -> 0x0034 }
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
            r0.bD = r1;	 Catch:{ Exception -> 0x0034 }
            r0 = r7.obj;	 Catch:{ Exception -> 0x0034 }
            r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.bx;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.bD;	 Catch:{ Exception -> 0x0034 }
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
            r0 = r0.q;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.q;	 Catch:{ Exception -> 0x0034 }
            r2 = 2131231283; // 0x7f080233 float:1.8078643E38 double:1.0529681603E-314;
            r1 = r1.getString(r2);	 Catch:{ Exception -> 0x0034 }
            r2 = -1;
            r0 = com.budejie.www.util.an.a(r0, r1, r2);	 Catch:{ Exception -> 0x0034 }
            r0.show();	 Catch:{ Exception -> 0x0034 }
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.q;	 Catch:{ Exception -> 0x0034 }
            r0 = com.budejie.www.util.ai.b(r0);	 Catch:{ Exception -> 0x0034 }
            r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0034 }
            if (r0 != 0) goto L_0x001c;
        L_0x0177:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = "add";
            r0.bD = r1;	 Catch:{ Exception -> 0x0034 }
            r0 = r7.obj;	 Catch:{ Exception -> 0x0034 }
            r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.bx;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.bD;	 Catch:{ Exception -> 0x0034 }
            r3 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r3 = r3.q;	 Catch:{ Exception -> 0x0034 }
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
            r1 = r1.by;	 Catch:{ Exception -> 0x0034 }
            if (r1 == 0) goto L_0x01ba;
        L_0x01af:
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.by;	 Catch:{ Exception -> 0x0034 }
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
            r1.bD = r2;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.bx;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.bD;	 Catch:{ Exception -> 0x0034 }
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
            r1 = r1.r;	 Catch:{ Exception -> 0x0034 }
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
            r2 = r2.q;	 Catch:{ Exception -> 0x0034 }
            r3 = -1;
            r0 = com.budejie.www.util.an.a(r2, r0, r3);	 Catch:{ Exception -> 0x0034 }
            r1.L = r0;	 Catch:{ Exception -> 0x0034 }
        L_0x0228:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.L;	 Catch:{ Exception -> 0x0034 }
            if (r0 == 0) goto L_0x001c;
        L_0x0230:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.L;	 Catch:{ Exception -> 0x0034 }
            r0.show();	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x023b:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.q;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.q;	 Catch:{ Exception -> 0x0034 }
            r3 = 2131231714; // 0x7f0803e2 float:1.8079517E38 double:1.052968373E-314;
            r2 = r2.getString(r3);	 Catch:{ Exception -> 0x0034 }
            r3 = -1;
            r1 = com.budejie.www.util.an.a(r1, r2, r3);	 Catch:{ Exception -> 0x0034 }
            r0.L = r1;	 Catch:{ Exception -> 0x0034 }
            goto L_0x0228;
        L_0x0259:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.q;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.q;	 Catch:{ Exception -> 0x0034 }
            r3 = 2131231714; // 0x7f0803e2 float:1.8079517E38 double:1.052968373E-314;
            r2 = r2.getString(r3);	 Catch:{ Exception -> 0x0034 }
            r3 = -1;
            r1 = com.budejie.www.util.an.a(r1, r2, r3);	 Catch:{ Exception -> 0x0034 }
            r0.L = r1;	 Catch:{ Exception -> 0x0034 }
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
            r3 = new com.budejie.www.activity.CommendDetail$3$1;	 Catch:{ Exception -> 0x0034 }
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
            r0 = r0.aM;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.isShowing();	 Catch:{ Exception -> 0x0034 }
            if (r0 == 0) goto L_0x02c0;
        L_0x02b7:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.aM;	 Catch:{ Exception -> 0x0034 }
            r0.dismiss();	 Catch:{ Exception -> 0x0034 }
        L_0x02c0:
            if (r1 == 0) goto L_0x0310;
        L_0x02c2:
            r0 = r1.getMsg();	 Catch:{ Exception -> 0x0034 }
            r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0034 }
            if (r2 != 0) goto L_0x02f7;
        L_0x02cc:
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.q;	 Catch:{ Exception -> 0x0034 }
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
            r0 = r0.q;	 Catch:{ Exception -> 0x0034 }
            r1 = "E05-A05";
            r2 = "帖子置顶";
            com.umeng.analytics.MobclickAgent.onEvent(r0, r1, r2);	 Catch:{ Exception -> 0x0034 }
            goto L_0x001c;
        L_0x02f7:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.q;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.q;	 Catch:{ Exception -> 0x0034 }
            r3 = 2131231714; // 0x7f0803e2 float:1.8079517E38 double:1.052968373E-314;
            r2 = r2.getString(r3);	 Catch:{ Exception -> 0x0034 }
            r3 = -1;
            r0 = com.budejie.www.util.an.a(r0, r2, r3);	 Catch:{ Exception -> 0x0034 }
            goto L_0x02d7;
        L_0x0310:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.q;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.q;	 Catch:{ Exception -> 0x0034 }
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
            r0 = r0.aM;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.isShowing();	 Catch:{ Exception -> 0x0034 }
            if (r0 == 0) goto L_0x034a;
        L_0x0341:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.aM;	 Catch:{ Exception -> 0x0034 }
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
            r2 = r2.q;	 Catch:{ Exception -> 0x0034 }
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
            r0 = r0.q;	 Catch:{ Exception -> 0x0034 }
            r2 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r2 = r2.q;	 Catch:{ Exception -> 0x0034 }
            r3 = 2131231714; // 0x7f0803e2 float:1.8079517E38 double:1.052968373E-314;
            r2 = r2.getString(r3);	 Catch:{ Exception -> 0x0034 }
            r3 = -1;
            r0 = com.budejie.www.util.an.a(r0, r2, r3);	 Catch:{ Exception -> 0x0034 }
            goto L_0x0365;
        L_0x0393:
            r0 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r0 = r0.q;	 Catch:{ Exception -> 0x0034 }
            r1 = r6.a;	 Catch:{ Exception -> 0x0034 }
            r1 = r1.q;	 Catch:{ Exception -> 0x0034 }
            r2 = 2131231714; // 0x7f0803e2 float:1.8079517E38 double:1.052968373E-314;
            r1 = r1.getString(r2);	 Catch:{ Exception -> 0x0034 }
            r2 = -1;
            r0 = com.budejie.www.util.an.a(r0, r1, r2);	 Catch:{ Exception -> 0x0034 }
            goto L_0x0373;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.activity.CommendDetail.3.handleMessage(android.os.Message):void");
        }
    };
    net.tsz.afinal.a.a<String> h = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CommendDetail a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.L = an.a(this.a.q, this.a.getString(R.string.operate_fail), -1);
            this.a.L.show();
        }

        public void a(String str) {
            try {
                HashMap k = z.k(str);
                if (k != null) {
                    String str2 = (String) k.get("result_desc");
                    if (TextUtils.isEmpty(str2)) {
                        this.a.L = an.a(this.a.q, this.a.getString(R.string.operate_fail), -1);
                    } else {
                        this.a.L = an.a(this.a.q, str2, -1);
                    }
                } else {
                    this.a.L = an.a(this.a.q, this.a.getString(R.string.operate_fail), -1);
                }
                if (this.a.L != null) {
                    this.a.L.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    net.tsz.afinal.a.a<String> i = new net.tsz.afinal.a.a<String>(this) {
        final /* synthetic */ CommendDetail a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onFailure(Throwable th, int i, String str) {
            this.a.L = an.a(this.a.q, this.a.getString(R.string.operate_fail), -1);
            this.a.L.show();
        }

        public void onStart() {
            super.onStart();
            if (this.a.aM != null) {
                this.a.aM.show();
            }
        }

        public void a(String str) {
            try {
                if (this.a.aM.isShowing()) {
                    this.a.aM.dismiss();
                }
                if (RoomActivity.VIDEOTYPE_UNKNOWN.equals(str)) {
                    this.a.L = an.a(this.a.q, this.a.getString(R.string.operate_fail), -1);
                } else {
                    this.a.L = an.a(this.a.q, this.a.getString(R.string.operate_success), -1);
                    this.a.b(false);
                    this.a.r.setSelection(this.a.aP);
                }
                if (this.a.L != null) {
                    this.a.L.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    OnItemClickListener j = new OnItemClickListener(this) {
        final /* synthetic */ CommendDetail a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            CommentItem commentItem = (CommentItem) adapterView.getItemAtPosition(i);
            if (commentItem != null && !this.a.X) {
                this.a.a(i, commentItem, view);
            }
        }
    };
    TextWatcher k = new TextWatcher(this) {
        final /* synthetic */ CommendDetail a;

        {
            this.a = r1;
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(this.a.J.getText())) {
                this.a.I.setTextColor(-1);
                this.a.I.setText("" + this.a.T);
                return;
            }
            int aj = this.a.T - this.a.J.getText().toString().trim().length();
            if (aj < 0) {
                this.a.I.setTextColor(SupportMenu.CATEGORY_MASK);
            } else {
                this.a.I.setTextColor(-16777216);
            }
            this.a.I.setText("" + aj);
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    };
    TextWatcher l = new TextWatcher(this) {
        final /* synthetic */ CommendDetail a;

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
    public boolean m = false;
    com.budejie.www.activity.image.BitmapCache.a n = new com.budejie.www.activity.image.BitmapCache.a(this) {
        final /* synthetic */ CommendDetail a;

        {
            this.a = r1;
        }

        public void a(ImageView imageView, Bitmap bitmap, Object... objArr) {
            if (imageView == null || bitmap == null) {
                Log.e(this.a.p, "callback, bmp null");
                return;
            }
            String str = (String) objArr[0];
            if (str == null || !str.equals((String) imageView.getTag())) {
                Log.e(this.a.p, "callback, bmp not match");
            } else {
                imageView.setImageBitmap(bitmap);
            }
        }
    };
    OnClickListener o = new OnClickListener(this) {
        final /* synthetic */ CommendDetail a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (this.a.aW != null) {
                this.a.aW.setVisibility(8);
            }
        }
    };
    private String p = "CommendDetail";
    private CommendDetail q;
    private CustomListView r;
    private Dialog s;
    private b t;
    private RelativeLayout u;
    private ImageView v;
    private TextView w;
    private RecordVoiceView x;
    private CommentOperateView y;
    private View z;

    public enum BottomStatus {
        NORMAL,
        KEYBOARD,
        VOICE,
        OPERATE
    }

    class a implements OnClickListener {
        Dialog a;
        final /* synthetic */ CommendDetail b;

        public a(CommendDetail commendDetail, Dialog dialog) {
            this.b = commendDetail;
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
        final /* synthetic */ CommendDetail d;

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public b(CommendDetail commendDetail) {
            this.d = commendDetail;
        }

        public int getCount() {
            return this.d.af.size();
        }

        public CommentItem a(int i) {
            return (CommentItem) this.d.af.get(i);
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
            CommendDetail$b$b commendDetail$b$b;
            String str;
            int i2;
            CharSequence charSequence;
            NumberFormatException e;
            if (view == null) {
                CommendDetail$b$b commendDetail$b$b2 = new CommendDetail$b$b(this, null);
                view = this.d.q.getLayoutInflater().inflate(R.layout.comment_item, null);
                commendDetail$b$b2.a = (AsyncImageView) view.findViewById(R.id.thume_img);
                commendDetail$b$b2.b = (TextView) view.findViewById(R.id.user_name);
                commendDetail$b$b2.c = (AsyncImageView) view.findViewById(R.id.iv_members_mark);
                commendDetail$b$b2.e = (ImageView) view.findViewById(R.id.user_sex);
                commendDetail$b$b2.d = (NewParseTagEditText) view.findViewById(R.id.cmtContent);
                commendDetail$b$b2.q = (LinearLayout) view.findViewById(R.id.comment_title_layout);
                commendDetail$b$b2.f = (TextView) view.findViewById(R.id.cmtTagTv);
                commendDetail$b$b2.g = (TextView) view.findViewById(R.id.commentLikeCount);
                commendDetail$b$b2.h = (FrameLayout) view.findViewById(R.id.commentDingLayout);
                commendDetail$b$b2.i = (ImageView) view.findViewById(R.id.commentDingIv);
                commendDetail$b$b2.j = (TextView) view.findViewById(R.id.commentCaiCount);
                commendDetail$b$b2.k = (FrameLayout) view.findViewById(R.id.commentCaiLayout);
                commendDetail$b$b2.l = (ImageView) view.findViewById(R.id.commentCaiIv);
                commendDetail$b$b2.m = (ImageView) view.findViewById(R.id.commend_listview_divider);
                commendDetail$b$b2.n = (AudioLayout) view.findViewById(R.id.cmtVoice);
                commendDetail$b$b2.o = (NewParseTagEditText) view.findViewById(R.id.replyUser);
                commendDetail$b$b2.p = (ProgressBar) view.findViewById(R.id.pb_cmtSending);
                commendDetail$b$b2.r = (TextView) view.findViewById(R.id.cmtLikeCount);
                commendDetail$b$b2.s = (LinearLayout) view.findViewById(R.id.ListCommentLayout);
                commendDetail$b$b2.t = (RelativeLayout) view.findViewById(R.id.HotMoreLayout);
                commendDetail$b$b2.u = (ImageView) view.findViewById(R.id.HotMoreArrow);
                commendDetail$b$b2.v = (ProgressBar) view.findViewById(R.id.HotMoreProgressBar);
                commendDetail$b$b2.w = (RelativeLayout) view.findViewById(R.id.ReplyImageLayout);
                commendDetail$b$b2.x = (AsyncImageView) view.findViewById(R.id.ItemImageView);
                commendDetail$b$b2.y = (ImageView) view.findViewById(R.id.PlayVideoImageView);
                commendDetail$b$b2.z = (ImageView) view.findViewById(R.id.GifIconImageView);
                commendDetail$b$b2.A = (VoteView) view.findViewById(R.id.comment_vote_view);
                commendDetail$b$b2.B = (LinearLayout) view.findViewById(R.id.pub_view);
                commendDetail$b$b2.C = (TextView) view.findViewById(R.id.pub_content_view);
                commendDetail$b$b2.D = view.findViewById(R.id.author_replay_mark);
                commendDetail$b$b2.E = (TextView) view.findViewById(R.id.author_replay_mark_content);
                commendDetail$b$b2.F = (LinearLayout) view.findViewById(R.id.hot_author_replay);
                commendDetail$b$b2.G = (LinearLayout) view.findViewById(R.id.comment_user_name_layout);
                commendDetail$b$b2.H = (ImageView) view.findViewById(R.id.hot_author_replay_divider);
                commendDetail$b$b2.I = (RelativeLayout) view.findViewById(R.id.commend_content_layout);
                commendDetail$b$b2.J = (LinearLayout) view.findViewById(R.id.ll_middle_content_layout);
                view.setTag(R.layout.comment_item, commendDetail$b$b2);
                if (getItemViewType(i) == 1 && this.d.aO && this.d.aN != null) {
                    try {
                        ((LinearLayout) view.findViewById(R.id.ad_layout)).addView(this.d.aN, new LayoutParams(-1, -2));
                    } catch (Exception e2) {
                    }
                    if (!(this.d.f == null || this.d.bs)) {
                        this.d.bs = true;
                        this.d.f.onExposured(this.d.aN);
                    }
                    commendDetail$b$b = commendDetail$b$b2;
                } else {
                    commendDetail$b$b = commendDetail$b$b2;
                }
            } else {
                commendDetail$b$b = (CommendDetail$b$b) view.getTag(R.layout.comment_item);
            }
            commendDetail$b$b.q.setFocusable(false);
            commendDetail$b$b.m.setVisibility(0);
            this.a = (CommentItem) this.d.af.get(i);
            commendDetail$b$b.w.setVisibility(8);
            commendDetail$b$b.y.setVisibility(8);
            commendDetail$b$b.z.setVisibility(8);
            commendDetail$b$b.n.setVisibility(8);
            commendDetail$b$b.B.setVisibility(8);
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
                    commendDetail$b$b.w.setVisibility(0);
                    commendDetail$b$b.w.setOnClickListener(new g(this.d, this.a));
                    com.budejie.www.adapter.b.a.c(commendDetail$b$b.x, this.a.getImageWidth(), this.a.getImageHeight());
                    commendDetail$b$b.x.setAsyncCacheImage(this.a.getImageThumbUrl(), R.drawable.likelist_defaut_bg);
                } else if (str.equals("gif")) {
                    commendDetail$b$b.z.setVisibility(0);
                    commendDetail$b$b.w.setVisibility(0);
                    commendDetail$b$b.w.setOnClickListener(new g(this.d, this.a));
                    com.budejie.www.adapter.b.a.c(commendDetail$b$b.x, this.a.getGifWidth(), this.a.getGifHeight());
                    commendDetail$b$b.x.setAsyncCacheImage(this.a.getGifThumbUrl(), R.drawable.likelist_defaut_bg);
                } else if (str.equals("audio")) {
                    commendDetail$b$b.n.setVisibility(0);
                    commendDetail$b$b.n.c();
                    commendDetail$b$b.n.setPlayPath(this.a.getAudioUrl());
                    commendDetail$b$b.n.setAudioTime("" + this.a.getAudioDuration());
                    if (ac.a(this.d.q).c()) {
                        type = ac.a(this.d.q).m();
                        if (type == null || !type.equals(this.a.getVoiceuri())) {
                            commendDetail$b$b.n.c();
                        } else {
                            commendDetail$b$b.n.d();
                        }
                    } else {
                        commendDetail$b$b.n.c();
                    }
                } else if (str.equals("video")) {
                    commendDetail$b$b.w.setVisibility(0);
                    commendDetail$b$b.w.setOnClickListener(new g(this.d, this.a));
                    commendDetail$b$b.y.setVisibility(0);
                    com.budejie.www.adapter.b.a.c(commendDetail$b$b.x, 0, 0);
                    commendDetail$b$b.x.setAsyncCacheImage(this.a.getVideoThumbnail(), R.drawable.likelist_defaut_bg);
                } else if (str.equals("pub")) {
                    commendDetail$b$b.B.setVisibility(0);
                    commendDetail$b$b.C.setText(this.a.getmVideoTime() + "\" 神配音");
                    commendDetail$b$b.B.setOnClickListener(new h(this.d, this.a));
                }
            }
            VoteData voteData = this.a.getVoteData();
            if (voteData == null || voteData.votes == null || voteData.votes.size() <= 0) {
                commendDetail$b$b.A.setVisibility(8);
            } else {
                commendDetail$b$b.A.a();
                commendDetail$b$b.A.setVisibility(0);
                commendDetail$b$b.A.setVoteData(voteData);
            }
            if (this.a.getHotNp() == null || this.a.getHotNp().equals("null")) {
                commendDetail$b$b.t.setVisibility(8);
            } else {
                commendDetail$b$b.t.setVisibility(0);
                commendDetail$b$b.u.setVisibility(0);
                commendDetail$b$b.v.setVisibility(8);
                commendDetail$b$b.t.setOnClickListener(new CommendDetail$b$1(this, commendDetail$b$b));
            }
            if (this.a.getReplyList() == null || this.a.getReplyList().size() <= 0) {
                commendDetail$b$b.s.setVisibility(8);
                commendDetail$b$b.D.setVisibility(8);
                commendDetail$b$b.E.setVisibility(8);
            } else {
                a(commendDetail$b$b, this.a.getReplyList(), i);
                if (this.a.getUid().equals(this.a.getAuthorUid())) {
                    commendDetail$b$b.D.setVisibility(0);
                    commendDetail$b$b.E.setVisibility(0);
                } else {
                    commendDetail$b$b.D.setVisibility(8);
                    commendDetail$b$b.E.setVisibility(8);
                }
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) commendDetail$b$b.I.getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) commendDetail$b$b.J.getLayoutParams();
            if (this.a.isHotAuthorReplay()) {
                commendDetail$b$b.F.setVisibility(0);
                commendDetail$b$b.G.setVisibility(8);
                commendDetail$b$b.a.setVisibility(8);
                commendDetail$b$b.r.setVisibility(8);
                commendDetail$b$b.m.setVisibility(8);
                if (this.a.isHotAuthorReplayFirst()) {
                    commendDetail$b$b.H.setVisibility(0);
                    layoutParams.setMargins(layoutParams.leftMargin, an.a(this.d.q, 10), layoutParams.rightMargin, layoutParams.bottomMargin);
                    commendDetail$b$b.I.setLayoutParams(layoutParams);
                } else {
                    commendDetail$b$b.H.setVisibility(8);
                    layoutParams.setMargins(layoutParams.leftMargin, -an.a(this.d.q, 5), layoutParams.rightMargin, layoutParams.bottomMargin);
                    commendDetail$b$b.I.setLayoutParams(layoutParams);
                }
                layoutParams2.setMargins(an.a(this.d.q, 21), layoutParams2.topMargin, layoutParams2.rightMargin, layoutParams2.bottomMargin);
                commendDetail$b$b.J.setLayoutParams(layoutParams2);
            } else {
                layoutParams.setMargins(layoutParams.leftMargin, an.a(this.d.q, 10), layoutParams.rightMargin, layoutParams.bottomMargin);
                commendDetail$b$b.I.setLayoutParams(layoutParams);
                layoutParams2.setMargins(an.a(this.d.q, 12), layoutParams2.topMargin, layoutParams2.rightMargin, layoutParams2.bottomMargin);
                commendDetail$b$b.J.setLayoutParams(layoutParams2);
                commendDetail$b$b.F.setVisibility(8);
                commendDetail$b$b.G.setVisibility(0);
                commendDetail$b$b.a.setVisibility(0);
                commendDetail$b$b.H.setVisibility(8);
                commendDetail$b$b.r.setVisibility(0);
                commendDetail$b$b.m.setVisibility(0);
                commendDetail$b$b.b.setText(this.a.getUname());
                if (TextUtils.isEmpty(this.a.getIs_vip()) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equalsIgnoreCase(this.a.getIs_vip())) {
                    commendDetail$b$b.b.setTextColor(this.d.getResources().getColor(com.budejie.www.util.j.F));
                    commendDetail$b$b.c.setVisibility(8);
                } else {
                    commendDetail$b$b.b.setTextColor(this.d.q.getResources().getColor(com.budejie.www.util.j.H));
                    try {
                        commendDetail$b$b.c.setVisibility(0);
                        Drawable gifDrawable = new GifDrawable(this.d.q.getResources(), com.budejie.www.util.j.I);
                        commendDetail$b$b.c.setImageDrawable(gifDrawable);
                        gifDrawable.start();
                    } catch (NotFoundException e3) {
                        e3.printStackTrace();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                if ("m".equals(this.a.getSex())) {
                    commendDetail$b$b.e.setBackgroundResource(com.budejie.www.util.j.bs);
                } else {
                    commendDetail$b$b.e.setBackgroundResource(com.budejie.www.util.j.br);
                }
                type = this.a.getProfile();
                if (SensorBaseActivity.portraitShow) {
                    commendDetail$b$b.a.setVisibility(8);
                } else {
                    commendDetail$b$b.a.setVisibility(0);
                    commendDetail$b$b.a.setPostAvatarImage(type);
                }
                Bundle bundle = new Bundle();
                bundle.putString(PersonalProfileActivity.c, this.a.getUid());
                bundle.putBoolean(PersonalProfileActivity.f, true);
                commendDetail$b$b.a.setOnClickListener(this.d.bv.a(7, bundle));
                commendDetail$b$b.b.setOnClickListener(this.d.bv.a(7, bundle));
                commendDetail$b$b.c.setOnClickListener(this.d.bF);
                view.setTag(R.layout.cmt_pop_item, this.a);
                i2 = 0;
                charSequence = "0";
                try {
                    i2 = Integer.parseInt(this.a.getCmtLikeCount());
                } catch (NumberFormatException e5) {
                }
                if (i2 < 1000) {
                    charSequence = "" + i2;
                    commendDetail$b$b.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like1_bg));
                } else if (i2 < 10000) {
                    charSequence = "" + this.c.format(((double) i2) / 1000.0d) + "k";
                    commendDetail$b$b.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like2_bg));
                } else if (i2 < 100000) {
                    charSequence = "" + this.b.format(((double) i2) / 1000.0d) + "k";
                    commendDetail$b$b.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like3_bg));
                } else if (i2 < 1000000) {
                    charSequence = "" + (i2 / 1000) + "k";
                    commendDetail$b$b.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
                } else if (1000000 < i2 && i2 < 10000000) {
                    charSequence = "" + this.c.format(((double) i2) / 1000000.0d) + "m";
                    commendDetail$b$b.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
                } else if (i2 > 10000000 && i2 < 100000000) {
                    charSequence = "" + this.b.format(((double) i2) / 1000000.0d) + "m";
                    commendDetail$b$b.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
                } else if (100000000 < i2) {
                    charSequence = "" + (i2 / 1000000) + "m";
                    commendDetail$b$b.r.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.shape_cmt_like4_bg));
                }
                commendDetail$b$b.r.setText(charSequence);
            }
            if (this.a.isTagIsShow()) {
                commendDetail$b$b.q.setVisibility(0);
                commendDetail$b$b.f.setBackgroundResource(com.budejie.www.util.j.r);
                commendDetail$b$b.m.setVisibility(8);
                commendDetail$b$b.H.setVisibility(8);
                if (this.a.isIshot()) {
                    commendDetail$b$b.f.setText(R.string.hot_comment);
                } else {
                    commendDetail$b$b.f.setText(R.string.new_comment);
                }
                commendDetail$b$b.q.setOnClickListener(new CommendDetail$b$2(this));
                if (ai.a(this.d.q) == 1) {
                    commendDetail$b$b.q.setPadding(0, 0, 0, 0);
                } else {
                    commendDetail$b$b.q.setPadding(an.a(this.d.q, 1), 0, an.a(this.d.q, 1), 0);
                }
            } else {
                commendDetail$b$b.q.setVisibility(8);
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
                    commendDetail$b$b.g.setText(i2 <= 0 ? i2 + "" : "");
                    commendDetail$b$b.j.setText(i3 <= 0 ? i3 + "" : "");
                    type = this.a.getDingOrCai();
                    if (!"like".equals(type)) {
                        commendDetail$b$b.i.setSelected(true);
                        commendDetail$b$b.g.setSelected(true);
                        commendDetail$b$b.l.setSelected(false);
                        commendDetail$b$b.j.setSelected(false);
                    } else if ("hate".equals(type)) {
                        commendDetail$b$b.i.setSelected(false);
                        commendDetail$b$b.g.setSelected(false);
                        commendDetail$b$b.l.setSelected(false);
                        commendDetail$b$b.j.setSelected(false);
                    } else {
                        commendDetail$b$b.i.setSelected(false);
                        commendDetail$b$b.g.setSelected(false);
                        commendDetail$b$b.l.setSelected(true);
                        commendDetail$b$b.j.setSelected(true);
                    }
                    commendDetail$b$b.h.setTag(Integer.valueOf(i));
                    commendDetail$b$b.h.setOnClickListener(new f(this.d, this.a, view));
                    commendDetail$b$b.k.setTag(Integer.valueOf(i));
                    commendDetail$b$b.k.setOnClickListener(new e(this.d, this.a, view));
                    charSequence = "";
                    if (this.a.getMpreName().length() > 0) {
                        commendDetail$b$b.o.setVisibility(8);
                    } else {
                        charSequence = "//@" + this.a.getMpreName() + ":";
                        if (!TextUtils.isEmpty(this.a.getMpreContent())) {
                            charSequence = charSequence + this.a.getMpreContent();
                        }
                        commendDetail$b$b.o.setVisibility(0);
                        ParseTagTextView.a(this.d.getApplicationContext(), commendDetail$b$b.o, charSequence);
                    }
                    if (!TextUtils.isEmpty(this.a.getVoiceuri())) {
                        if (TextUtils.isEmpty(this.a.getContent())) {
                            commendDetail$b$b.o.setVisibility(0);
                            ParseTagTextView.a(this.d.getApplicationContext(), commendDetail$b$b.o, charSequence + " " + this.a.getContent());
                        } else if (TextUtils.isEmpty(charSequence)) {
                            commendDetail$b$b.o.setVisibility(8);
                        }
                    }
                    if (this.a.getState() != -1) {
                        commendDetail$b$b.p.setVisibility(0);
                        commendDetail$b$b.d.setVisibility(8);
                    } else {
                        commendDetail$b$b.p.setVisibility(8);
                        charSequence = this.a.getContent();
                        if (charSequence != null) {
                        }
                        commendDetail$b$b.d.setVisibility(8);
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
                commendDetail$b$b.g.setText(i2 <= 0 ? i2 + "" : "");
                if (i3 <= 0) {
                }
                commendDetail$b$b.j.setText(i3 <= 0 ? i3 + "" : "");
                type = this.a.getDingOrCai();
                if (!"like".equals(type)) {
                    commendDetail$b$b.i.setSelected(true);
                    commendDetail$b$b.g.setSelected(true);
                    commendDetail$b$b.l.setSelected(false);
                    commendDetail$b$b.j.setSelected(false);
                } else if ("hate".equals(type)) {
                    commendDetail$b$b.i.setSelected(false);
                    commendDetail$b$b.g.setSelected(false);
                    commendDetail$b$b.l.setSelected(true);
                    commendDetail$b$b.j.setSelected(true);
                } else {
                    commendDetail$b$b.i.setSelected(false);
                    commendDetail$b$b.g.setSelected(false);
                    commendDetail$b$b.l.setSelected(false);
                    commendDetail$b$b.j.setSelected(false);
                }
                commendDetail$b$b.h.setTag(Integer.valueOf(i));
                commendDetail$b$b.h.setOnClickListener(new f(this.d, this.a, view));
                commendDetail$b$b.k.setTag(Integer.valueOf(i));
                commendDetail$b$b.k.setOnClickListener(new e(this.d, this.a, view));
                charSequence = "";
                if (this.a.getMpreName().length() > 0) {
                    charSequence = "//@" + this.a.getMpreName() + ":";
                    if (TextUtils.isEmpty(this.a.getMpreContent())) {
                        charSequence = charSequence + this.a.getMpreContent();
                    }
                    commendDetail$b$b.o.setVisibility(0);
                    ParseTagTextView.a(this.d.getApplicationContext(), commendDetail$b$b.o, charSequence);
                } else {
                    commendDetail$b$b.o.setVisibility(8);
                }
                if (TextUtils.isEmpty(this.a.getVoiceuri())) {
                    if (TextUtils.isEmpty(this.a.getContent())) {
                        commendDetail$b$b.o.setVisibility(0);
                        ParseTagTextView.a(this.d.getApplicationContext(), commendDetail$b$b.o, charSequence + " " + this.a.getContent());
                    } else if (TextUtils.isEmpty(charSequence)) {
                        commendDetail$b$b.o.setVisibility(8);
                    }
                }
                if (this.a.getState() != -1) {
                    commendDetail$b$b.p.setVisibility(8);
                    charSequence = this.a.getContent();
                    if (charSequence != null) {
                    }
                    commendDetail$b$b.d.setVisibility(8);
                } else {
                    commendDetail$b$b.p.setVisibility(0);
                    commendDetail$b$b.d.setVisibility(8);
                }
                return view;
            }
            if (i2 <= 0) {
            }
            commendDetail$b$b.g.setText(i2 <= 0 ? i2 + "" : "");
            if (i3 <= 0) {
            }
            commendDetail$b$b.j.setText(i3 <= 0 ? i3 + "" : "");
            type = this.a.getDingOrCai();
            if (!"like".equals(type)) {
                commendDetail$b$b.i.setSelected(true);
                commendDetail$b$b.g.setSelected(true);
                commendDetail$b$b.l.setSelected(false);
                commendDetail$b$b.j.setSelected(false);
            } else if ("hate".equals(type)) {
                commendDetail$b$b.i.setSelected(false);
                commendDetail$b$b.g.setSelected(false);
                commendDetail$b$b.l.setSelected(true);
                commendDetail$b$b.j.setSelected(true);
            } else {
                commendDetail$b$b.i.setSelected(false);
                commendDetail$b$b.g.setSelected(false);
                commendDetail$b$b.l.setSelected(false);
                commendDetail$b$b.j.setSelected(false);
            }
            commendDetail$b$b.h.setTag(Integer.valueOf(i));
            commendDetail$b$b.h.setOnClickListener(new f(this.d, this.a, view));
            commendDetail$b$b.k.setTag(Integer.valueOf(i));
            commendDetail$b$b.k.setOnClickListener(new e(this.d, this.a, view));
            charSequence = "";
            if (this.a.getMpreName().length() > 0) {
                charSequence = "//@" + this.a.getMpreName() + ":";
                if (TextUtils.isEmpty(this.a.getMpreContent())) {
                    charSequence = charSequence + this.a.getMpreContent();
                }
                commendDetail$b$b.o.setVisibility(0);
                ParseTagTextView.a(this.d.getApplicationContext(), commendDetail$b$b.o, charSequence);
            } else {
                commendDetail$b$b.o.setVisibility(8);
            }
            if (TextUtils.isEmpty(this.a.getVoiceuri())) {
                if (TextUtils.isEmpty(this.a.getContent())) {
                    commendDetail$b$b.o.setVisibility(0);
                    ParseTagTextView.a(this.d.getApplicationContext(), commendDetail$b$b.o, charSequence + " " + this.a.getContent());
                } else if (TextUtils.isEmpty(charSequence)) {
                    commendDetail$b$b.o.setVisibility(8);
                }
            }
            if (this.a.getState() != -1) {
                commendDetail$b$b.p.setVisibility(8);
                charSequence = this.a.getContent();
                if (charSequence != null || "".equals(charSequence) || str.equals("audio")) {
                    commendDetail$b$b.d.setVisibility(8);
                } else {
                    commendDetail$b$b.d.setVisibility(0);
                    commendDetail$b$b.d.setText(charSequence);
                    commendDetail$b$b.d.setOnClickListener(new CommendDetail$b$3(this, i, view));
                }
            } else {
                commendDetail$b$b.p.setVisibility(0);
                commendDetail$b$b.d.setVisibility(8);
            }
            return view;
        }

        private void a(CommendDetail$b$b commendDetail$b$b, ArrayList<CommentItem> arrayList, int i) {
            NumberFormatException e;
            View view = null;
            commendDetail$b$b.s.setVisibility(0);
            commendDetail$b$b.s.removeAllViews();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                View view2;
                Object obj;
                CommentItem commentItem = (CommentItem) arrayList.get(i2);
                CommendDetail$b$a commendDetail$b$a = null;
                if (this.d.d.containsKey(commentItem.getId() + this.a.getId())) {
                    view2 = (View) this.d.d.get(commentItem.getId() + this.a.getId());
                    commendDetail$b$a = (CommendDetail$b$a) view2.getTag(R.layout.comment_item_reply);
                    try {
                        commendDetail$b$b.s.removeView(view2);
                        commendDetail$b$b.s.addView(view2);
                        obj = null;
                    } catch (Exception e2) {
                        obj = 1;
                    }
                } else {
                    int i3 = 1;
                    view2 = view;
                }
                if (obj != null) {
                    CommendDetail$b$a commendDetail$b$a2 = new CommendDetail$b$a(this, null);
                    View inflate = this.d.q.getLayoutInflater().inflate(R.layout.comment_item_reply, null);
                    commendDetail$b$a2.d = (LinearLayout) inflate.findViewById(R.id.ReplayLayout);
                    commendDetail$b$a2.e = (RelativeLayout) inflate.findViewById(R.id.HideLayout);
                    commendDetail$b$a2.f = (ImageView) inflate.findViewById(R.id.LappedArrow);
                    commendDetail$b$a2.g = (ProgressBar) inflate.findViewById(R.id.LappedProgressBar);
                    commendDetail$b$a2.a = (TextView) inflate.findViewById(R.id.UserNameTextView);
                    commendDetail$b$a2.t = (ImageView) inflate.findViewById(R.id.iv_members_mark_reply);
                    commendDetail$b$a2.b = (TextView) inflate.findViewById(R.id.ContentTextView);
                    commendDetail$b$a2.c = (TextView) inflate.findViewById(R.id.NumberTextView);
                    commendDetail$b$a2.h = (FrameLayout) inflate.findViewById(R.id.commentDingLayout);
                    commendDetail$b$a2.i = (TextView) inflate.findViewById(R.id.commentLikeCount);
                    commendDetail$b$a2.j = (ImageView) inflate.findViewById(R.id.commentDingIv);
                    commendDetail$b$a2.k = (FrameLayout) inflate.findViewById(R.id.commentCaiLayout);
                    commendDetail$b$a2.l = (TextView) inflate.findViewById(R.id.commentCaiCount);
                    commendDetail$b$a2.m = (ImageView) inflate.findViewById(R.id.commentCaiIv);
                    commendDetail$b$a2.n = (RelativeLayout) inflate.findViewById(R.id.ReplyImageLayout);
                    commendDetail$b$a2.o = (ImageView) inflate.findViewById(R.id.PlayVideoImageView);
                    commendDetail$b$a2.p = (AsyncImageView) inflate.findViewById(R.id.ItemImageView);
                    commendDetail$b$a2.q = (ImageView) inflate.findViewById(R.id.GifIconImageView);
                    commendDetail$b$a2.r = (AudioLayout) inflate.findViewById(R.id.cmtVoice);
                    commendDetail$b$a2.s = inflate.findViewById(R.id.line_view);
                    commendDetail$b$a2.u = (TextView) inflate.findViewById(R.id.comment_reply_vote);
                    commendDetail$b$a2.v = (LinearLayout) inflate.findViewById(R.id.pub_view);
                    commendDetail$b$a2.w = (TextView) inflate.findViewById(R.id.pub_content_view);
                    try {
                        commendDetail$b$b.s.addView(inflate);
                    } catch (Exception e3) {
                    }
                    inflate.setTag(R.layout.comment_item_reply, commendDetail$b$a2);
                    this.d.d.put(commentItem.getId() + this.a.getId(), inflate);
                    commendDetail$b$a = commendDetail$b$a2;
                    view = inflate;
                } else {
                    view = view2;
                }
                this.d.aT = -1;
                if (commentItem.getLapped() == null || !commentItem.getLapped().equals(Constants.SERVICE_SCOPE_FLAG_VALUE)) {
                    commendDetail$b$a.c.setVisibility(0);
                    commendDetail$b$a.d.setVisibility(0);
                    commendDetail$b$a.e.setVisibility(8);
                    commendDetail$b$a.n.setVisibility(8);
                    commendDetail$b$a.o.setVisibility(8);
                    commendDetail$b$a.q.setVisibility(8);
                    commendDetail$b$a.r.setVisibility(8);
                    commendDetail$b$a.v.setVisibility(8);
                    commendDetail$b$a.b.setVisibility(0);
                    commendDetail$b$a.s.setVisibility(0);
                    if (i2 == arrayList.size() - 1) {
                        commendDetail$b$a.s.setVisibility(8);
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
                                commendDetail$b$a.b.setVisibility(8);
                            }
                            commendDetail$b$a.n.setVisibility(0);
                            commendDetail$b$a.n.setOnClickListener(new g(this.d, commentItem));
                            com.budejie.www.adapter.b.a.c(commendDetail$b$a.p, commentItem.getImageWidth(), commentItem.getImageHeight());
                            commendDetail$b$a.p.setAsyncCacheImage(commentItem.getImageThumbUrl(), R.drawable.likelist_defaut_bg);
                        } else if (type.equals("audio")) {
                            commendDetail$b$a.r.setVisibility(0);
                            commendDetail$b$a.b.setVisibility(8);
                            commendDetail$b$a.r.c();
                            commendDetail$b$a.r.setPlayPath(commentItem.getAudioUrl());
                            commendDetail$b$a.r.setAudioTime("" + commentItem.getAudioDuration());
                            if (ac.a(this.d.q).c()) {
                                type = ac.a(this.d.q).m();
                                if (type == null || !type.equals(commentItem.getVoiceuri())) {
                                    commendDetail$b$a.r.c();
                                } else {
                                    commendDetail$b$a.r.d();
                                }
                            } else {
                                commendDetail$b$a.r.c();
                            }
                        } else if (type.equals("video")) {
                            if (commentItem.getContent().equals("")) {
                                commendDetail$b$a.b.setVisibility(8);
                            }
                            commendDetail$b$a.n.setVisibility(0);
                            commendDetail$b$a.o.setVisibility(0);
                            commendDetail$b$a.n.setOnClickListener(new g(this.d, commentItem));
                            com.budejie.www.adapter.b.a.c(commendDetail$b$a.p, 0, 0);
                            commendDetail$b$a.p.setAsyncCacheImage(commentItem.getVideoThumbnail(), R.drawable.likelist_defaut_bg);
                        } else if (type.equals("gif")) {
                            if (commentItem.getContent().equals("")) {
                                commendDetail$b$a.b.setVisibility(8);
                            }
                            commendDetail$b$a.q.setVisibility(0);
                            commendDetail$b$a.n.setVisibility(0);
                            commendDetail$b$a.n.setOnClickListener(new g(this.d, commentItem));
                            com.budejie.www.adapter.b.a.c(commendDetail$b$a.p, commentItem.getGifWidth(), commentItem.getGifHeight());
                            commendDetail$b$a.p.setAsyncCacheImage(commentItem.getGifThumbUrl(), R.drawable.likelist_defaut_bg);
                        } else if (type.equals("pub")) {
                            commendDetail$b$a.v.setVisibility(0);
                            commendDetail$b$a.w.setText(commentItem.getmVideoTime() + "\" 神配音");
                            commendDetail$b$a.v.setOnClickListener(new h(this.d, commentItem));
                        } else {
                            commendDetail$b$a.b.setText("当前版本暂不支持查看此评论，请升级至最新版本。");
                        }
                    }
                    VoteData voteData = commentItem.getVoteData();
                    if (voteData == null || voteData.votes == null || voteData.votes.size() <= 0) {
                        commendDetail$b$a.u.setVisibility(8);
                    } else {
                        commendDetail$b$a.u.setVisibility(0);
                        commendDetail$b$a.u.setOnClickListener(new m(this.d, voteData));
                    }
                    commendDetail$b$a.a.setText(commentItem.getUname().replace("\n", ""));
                    if (TextUtils.isEmpty(commentItem.getIs_vip()) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equalsIgnoreCase(commentItem.getIs_vip())) {
                        commendDetail$b$a.a.setTextColor(this.d.getResources().getColor(R.color.comment_item_reply_name_color));
                        commendDetail$b$a.t.setVisibility(8);
                    } else {
                        commendDetail$b$a.a.setTextColor(this.d.q.getResources().getColor(com.budejie.www.util.j.H));
                        try {
                            commendDetail$b$a.t.setVisibility(0);
                            Drawable gifDrawable = new GifDrawable(this.d.q.getResources(), com.budejie.www.util.j.I);
                            commendDetail$b$a.t.setImageDrawable(gifDrawable);
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
                    commendDetail$b$a.a.setOnClickListener(this.d.bv.a(7, bundle));
                    commendDetail$b$a.b.setText(commentItem.getContent());
                    commendDetail$b$a.c.setText(commentItem.getFloorNum());
                    if (commentItem.getStatus() == null || !(commentItem.getStatus().equals("1") || commentItem.getStatus().equals("4"))) {
                        commendDetail$b$a.b.setTextColor(com.budejie.www.h.c.a().c(R.attr.comment_reply_item_content_color));
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
                                commendDetail$b$a.i.setText(i3 <= 0 ? "" : i3 + "");
                                commendDetail$b$a.l.setText(i4 <= 0 ? "" : i4 + "");
                                type = commentItem.getDingOrCai();
                                if (!"like".equals(type)) {
                                    commendDetail$b$a.j.setSelected(true);
                                    commendDetail$b$a.i.setSelected(true);
                                    commendDetail$b$a.m.setSelected(false);
                                    commendDetail$b$a.l.setSelected(false);
                                } else if ("hate".equals(type)) {
                                    commendDetail$b$a.j.setSelected(false);
                                    commendDetail$b$a.i.setSelected(false);
                                    commendDetail$b$a.m.setSelected(true);
                                    commendDetail$b$a.l.setSelected(true);
                                } else {
                                    commendDetail$b$a.j.setSelected(false);
                                    commendDetail$b$a.i.setSelected(false);
                                    commendDetail$b$a.m.setSelected(false);
                                    commendDetail$b$a.l.setSelected(false);
                                }
                                commendDetail$b$a.h.setOnClickListener(new CommendDetail$b$5(this, commentItem, view));
                                commendDetail$b$a.k.setOnClickListener(new CommendDetail$b$6(this, commentItem, view));
                                commendDetail$b$a.d.setTag(commentItem.getId());
                                commendDetail$b$a.d.setOnClickListener(new j(this.d, i, commentItem, view));
                            }
                        } catch (NumberFormatException e7) {
                            NumberFormatException numberFormatException = e7;
                            i3 = 0;
                            e = numberFormatException;
                            Log.i("Commend-dingCount1", e.toString());
                            if (i3 <= 0) {
                            }
                            commendDetail$b$a.i.setText(i3 <= 0 ? "" : i3 + "");
                            if (i4 <= 0) {
                            }
                            commendDetail$b$a.l.setText(i4 <= 0 ? "" : i4 + "");
                            type = commentItem.getDingOrCai();
                            if (!"like".equals(type)) {
                                commendDetail$b$a.j.setSelected(true);
                                commendDetail$b$a.i.setSelected(true);
                                commendDetail$b$a.m.setSelected(false);
                                commendDetail$b$a.l.setSelected(false);
                            } else if ("hate".equals(type)) {
                                commendDetail$b$a.j.setSelected(false);
                                commendDetail$b$a.i.setSelected(false);
                                commendDetail$b$a.m.setSelected(false);
                                commendDetail$b$a.l.setSelected(false);
                            } else {
                                commendDetail$b$a.j.setSelected(false);
                                commendDetail$b$a.i.setSelected(false);
                                commendDetail$b$a.m.setSelected(true);
                                commendDetail$b$a.l.setSelected(true);
                            }
                            commendDetail$b$a.h.setOnClickListener(new CommendDetail$b$5(this, commentItem, view));
                            commendDetail$b$a.k.setOnClickListener(new CommendDetail$b$6(this, commentItem, view));
                            commendDetail$b$a.d.setTag(commentItem.getId());
                            commendDetail$b$a.d.setOnClickListener(new j(this.d, i, commentItem, view));
                        }
                        if (i3 <= 0) {
                        }
                        commendDetail$b$a.i.setText(i3 <= 0 ? "" : i3 + "");
                        if (i4 <= 0) {
                        }
                        commendDetail$b$a.l.setText(i4 <= 0 ? "" : i4 + "");
                        type = commentItem.getDingOrCai();
                        if (!"like".equals(type)) {
                            commendDetail$b$a.j.setSelected(true);
                            commendDetail$b$a.i.setSelected(true);
                            commendDetail$b$a.m.setSelected(false);
                            commendDetail$b$a.l.setSelected(false);
                        } else if ("hate".equals(type)) {
                            commendDetail$b$a.j.setSelected(false);
                            commendDetail$b$a.i.setSelected(false);
                            commendDetail$b$a.m.setSelected(true);
                            commendDetail$b$a.l.setSelected(true);
                        } else {
                            commendDetail$b$a.j.setSelected(false);
                            commendDetail$b$a.i.setSelected(false);
                            commendDetail$b$a.m.setSelected(false);
                            commendDetail$b$a.l.setSelected(false);
                        }
                        commendDetail$b$a.h.setOnClickListener(new CommendDetail$b$5(this, commentItem, view));
                        commendDetail$b$a.k.setOnClickListener(new CommendDetail$b$6(this, commentItem, view));
                    } else {
                        commendDetail$b$a.h.setVisibility(8);
                        commendDetail$b$a.k.setVisibility(8);
                        commendDetail$b$a.b.setTextColor(com.budejie.www.h.c.a().c(R.attr.comment_reply_item_delete_text_color));
                    }
                    commendDetail$b$a.d.setTag(commentItem.getId());
                    commendDetail$b$a.d.setOnClickListener(new j(this.d, i, commentItem, view));
                } else {
                    commendDetail$b$a.c.setVisibility(8);
                    commendDetail$b$a.d.setVisibility(8);
                    commendDetail$b$a.e.setVisibility(0);
                    commendDetail$b$a.f.setVisibility(0);
                    commendDetail$b$a.g.setVisibility(8);
                    commendDetail$b$a.e.setOnClickListener(new CommendDetail$b$4(this, commendDetail$b$a.f, commendDetail$b$a.g, i, commentItem));
                }
            }
        }
    }

    class c implements OnClickListener {
        Dialog a;
        final /* synthetic */ CommendDetail b;

        public c(CommendDetail commendDetail, Dialog dialog) {
            this.b = commendDetail;
            this.a = dialog;
        }

        public void onClick(View view) {
            this.a.dismiss();
            this.b.e("1");
        }
    }

    class d implements OnClickListener {
        Dialog a;
        final /* synthetic */ CommendDetail b;

        public d(CommendDetail commendDetail, Dialog dialog) {
            this.b = commendDetail;
            this.a = dialog;
        }

        public void onClick(View view) {
            this.a.dismiss();
            this.b.f(this.b.aA);
        }
    }

    class e implements OnClickListener {
        CommentItem a;
        View b;
        final /* synthetic */ CommendDetail c;

        public e(CommendDetail commendDetail, CommentItem commentItem, View view) {
            this.c = commendDetail;
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
        final /* synthetic */ CommendDetail c;

        public f(CommendDetail commendDetail, CommentItem commentItem, View view) {
            this.c = commendDetail;
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
        final /* synthetic */ CommendDetail b;

        public g(CommendDetail commendDetail, CommentItem commentItem) {
            this.b = commendDetail;
            this.a = commentItem;
        }

        public void onClick(View view) {
            if (this.a != null) {
                String type = this.a.getType();
                Intent intent;
                if (type.equals(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY)) {
                    intent = new Intent(this.b.q, CommentShowBigPicture.class);
                    intent.putExtra("imgPath", this.a.getImageShowUrl());
                    intent.putExtra("isgif", "0");
                    intent.putExtra(IndexEntry.COLUMN_NAME_WIDTH, this.a.getImageWidth());
                    intent.putExtra(IndexEntry.COLUMN_NAME_HEIGHT, this.a.getImageHeight());
                    intent.putExtra("download_uri", this.a.getImageDownloadUrls());
                    this.b.q.startActivity(intent);
                } else if (type.equals("video")) {
                    CommentItemVideoActivity.a(this.b.q, this.a);
                } else if (type.equals("gif")) {
                    intent = new Intent(this.b.q, CommentShowBigPicture.class);
                    intent.putExtra("isgif", "1");
                    intent.putExtra("GifShowUrls", this.a.getGifShowUrl());
                    intent.putExtra(IndexEntry.COLUMN_NAME_WIDTH, this.a.getGifWidth());
                    intent.putExtra(IndexEntry.COLUMN_NAME_HEIGHT, this.a.getGifHeight());
                    intent.putExtra("download_uri", this.a.getGifDownLoadUrls());
                    this.b.q.startActivity(intent);
                }
            }
        }
    }

    class h implements OnClickListener {
        CommentItem a;
        final /* synthetic */ CommendDetail b;

        public h(CommendDetail commendDetail, CommentItem commentItem) {
            this.b = commendDetail;
            this.a = commentItem;
        }

        public void onClick(View view) {
            if (!TextUtils.isEmpty(this.a.getVideoPlayUrl())) {
                CommentItemVideoActivity.a(this.b.q, this.a);
            }
        }
    }

    class i implements com.budejie.www.widget.CommentOperateView.a {
        final /* synthetic */ CommendDetail a;

        i(CommendDetail commendDetail) {
            this.a = commendDetail;
        }

        public void a() {
            this.a.V = this.a.ae.getString("id", "");
            if (TextUtils.isEmpty(this.a.V)) {
                an.a(this.a.q, 0, null, null, 0);
                return;
            }
            UserItem e = this.a.bz.e(this.a.V);
            if (TextUtils.isEmpty(e.getLevel()) || Integer.parseInt(e.getLevel()) < this.a.bj) {
                p.a(this.a.q, this.a.q.getString(R.string.send_media_comment_level_message, new Object[]{Integer.valueOf(this.a.bj)}), this.a.getString(R.string.send_media_comment_level_ok), null);
                return;
            }
            Intent intent = new Intent(this.a.q, SelectImageActivity.class);
            intent.putExtra("source", "CommendDetail");
            this.a.startActivityForResult(intent, 100);
        }

        public void b() {
            Intent intent = new Intent(this.a.q, AddVoteActivity.class);
            intent.putExtra("vote_data_key", this.a.bk);
            this.a.startActivityForResult(intent, 10);
        }

        public void c() {
            this.a.V = this.a.ae.getString("id", "");
            if (TextUtils.isEmpty(this.a.V)) {
                an.a(this.a.q, 0, null, null, 0);
                return;
            }
            Intent intent = new Intent(this.a.q, GodDubbingActivity.class);
            intent.putExtra("dubbing_key", this.a.aq);
            this.a.startActivityForResult(intent, 20);
        }
    }

    class j implements OnClickListener {
        int a;
        CommentItem b;
        View c;
        final /* synthetic */ CommendDetail d;

        public j(CommendDetail commendDetail, int i, CommentItem commentItem, View view) {
            this.d = commendDetail;
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
        final /* synthetic */ CommendDetail d;

        public k(CommendDetail commendDetail, Dialog dialog, CommentItem commentItem, int i) {
            this.d = commendDetail;
            this.a = dialog;
            this.b = commentItem;
            this.c = i;
        }

        public void onClick(View view) {
            this.a.dismiss();
            this.d.N = this.b;
            if (this.c == 0) {
                this.d.K();
            } else if (this.c == 1) {
                this.d.v.performClick();
                this.d.w.setText("回复：" + this.d.N.getUname());
            }
        }
    }

    class l implements OnClickListener {
        Dialog a;
        final /* synthetic */ CommendDetail b;

        public l(CommendDetail commendDetail, Dialog dialog) {
            this.b = commendDetail;
            this.a = dialog;
        }

        public void onClick(View view) {
            this.a.dismiss();
            com.budejie.www.activity.video.k.a(this.b.q).k();
            this.b.d(this.b.aA);
        }
    }

    class m implements OnClickListener {
        VoteData a;
        final /* synthetic */ CommendDetail b;

        public m(CommendDetail commendDetail, VoteData voteData) {
            this.b = commendDetail;
            this.a = voteData;
        }

        public void onClick(View view) {
            new com.budejie.www.widget.g(this.b.q, R.style.dialog, this.a).show();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.commentdetail);
        aq.a(this, R.color.black);
        this.aQ = new com.budejie.www.http.j(this.q);
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this.q, "send_comment_level");
        if (!TextUtils.isEmpty(configParams)) {
            this.bj = Integer.parseInt(configParams);
        }
        s();
        D();
        C();
        if (!this.aB) {
            i();
        }
        r();
        h();
        com.budejie.www.activity.video.k.a(this.q).h();
        EventBus.getDefault().register(this);
    }

    private void h() {
        this.aM = new Dialog(this, R.style.dialogTheme);
        this.aM.setContentView(R.layout.loaddialog);
        this.aM.setCanceledOnTouchOutside(true);
        this.aM.setCancelable(true);
    }

    private void i() {
        try {
            j();
            F();
            v();
        } catch (Exception e) {
            aa.e(this.p, "e.toString()" + e.toString());
        }
    }

    private void j() {
        this.bo = (NewTitleView) findViewById(R.id.new_title_view);
        if (this.aq != null) {
            this.bo.setmTitleLayoutClick(this.bu);
            this.bo.setmListItemObject(this.aq);
            if ((this.aq.getHeight() * this.O) / this.aq.getWidth() > (this.P - an.t(this.q)) - getResources().getDimensionPixelSize(R.dimen.navigation_height)) {
                I();
                if (this.bo != null) {
                    this.bo.a(true);
                }
            }
            this.bo.a(BarrageStatusManager.a(this.bB));
        }
    }

    private void k() {
        if (this.aD == null) {
            this.aD = this.q.getLayoutInflater().inflate(R.layout.comment_item_footer_none_data, null);
        }
        try {
            if (this.r.getFooterViewsCount() > 0) {
                this.r.removeFooterView(this.aD);
            }
            this.r.addFooterView(this.aD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void l() {
        if (this.aD == null) {
            this.aD = this.q.getLayoutInflater().inflate(R.layout.comment_item_footer_none_data, null);
        }
        try {
            if (this.r.getFooterViewsCount() > 0) {
                this.r.removeFooterView(this.aD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m() {
        if (this.aE == null) {
            this.aE = this.q.getLayoutInflater().inflate(R.layout.comment_item_footer_view, null);
        }
        try {
            if (this.r.getFooterViewsCount() > 0) {
                this.r.removeFooterView(this.aE);
            }
            this.r.addFooterView(this.aE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void n() {
        if (this.aE == null) {
            this.aE = this.q.getLayoutInflater().inflate(R.layout.comment_item_footer_view, null);
        }
        try {
            if (this.r.getFooterViewsCount() > 0) {
                this.r.removeFooterView(this.aE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void o() {
        if (this.aF == null) {
            this.aF = this.q.getLayoutInflater().inflate(R.layout.comment_item_footer_view_failed, null);
        }
        this.aF.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommendDetail a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.p();
                this.a.b(false);
            }
        });
        try {
            if (this.r.getFooterViewsCount() > 0) {
                this.r.removeFooterView(this.aF);
            }
            this.r.addFooterView(this.aF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void p() {
        if (this.aF == null) {
            this.aF = this.q.getLayoutInflater().inflate(R.layout.comment_item_footer_view_failed, null);
        }
        try {
            if (this.r.getFooterViewsCount() > 0) {
                this.r.removeFooterView(this.aF);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void q() {
        if (this.r.getFootView() != null) {
            try {
                if (this.r.getFooterViewsCount() > 0) {
                    this.r.removeFooterView(this.r.getFootView());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void r() {
        this.bd = false;
        if ("writeCommend".equals(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)) {
            this.N = null;
            d(false);
            this.K.setVisibility(0);
        } else if (this.aq == null) {
            y();
        } else {
            d();
            b(false);
        }
    }

    private void s() {
        this.bi = new BitmapCache();
        this.aU = new ArrayList();
        this.d = new HashMap();
        this.P = getWindowManager().getDefaultDisplay().getHeight();
        this.O = getWindowManager().getDefaultDisplay().getWidth();
        this.ap = getResources();
        this.q = this;
        this.V = ai.b(this);
        this.ae = getSharedPreferences("weiboprefer", 0);
        this.Y = new n(this);
        this.ac = new com.budejie.www.c.g(this);
        this.aa = new com.budejie.www.util.n();
        this.ab = new com.budejie.www.c.e(this);
        this.Z = new com.budejie.www.http.c(this, this);
        this.s = new Dialog(this, R.style.dialogTheme);
        this.s.setContentView(R.layout.loaddialog);
        this.s.setCanceledOnTouchOutside(true);
        this.ar = (InputMethodManager) getSystemService("input_method");
        this.bv = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
        this.bw = new com.budejie.www.http.f(this);
        this.bx = com.budejie.www.http.b.a(this.q, this.q);
        this.by = new com.budejie.www.c.b(this);
        this.bz = new com.budejie.www.c.m(this);
        this.bA = this.Y.a(this.V);
        this.bB = getSharedPreferences("weiboprefer", 0);
        u();
        this.am = OnlineConfigAgent.getInstance().getConfigParams(this, "评论列表-请求条数");
        if (TextUtils.isEmpty(this.am)) {
            this.am = "20";
        }
        t();
    }

    private void t() {
        if (!AdManager.isAdClosed()) {
            this.aN = (LinearLayout) this.q.getLayoutInflater().inflate(R.layout.ad_banner_layout, null);
            BannerAd bannerAd = new BannerAd(AdConfig.banner, this, (RelativeLayout) this.aN.findViewById(R.id.ad_banner_container), new BannerADListener(this) {
                final /* synthetic */ CommendDetail a;

                {
                    this.a = r1;
                }

                public void onADReceive(Reporter reporter, boolean z) {
                    this.a.f = reporter;
                    if (!(this.a.f == null || this.a.bs)) {
                        this.a.bs = true;
                        this.a.f.onExposured(this.a.aN);
                    }
                    this.a.aN.setVisibility(0);
                    if (z) {
                        this.a.aN.addView(this.a.q.getLayoutInflater().inflate(R.layout.item_divide_view, null));
                    }
                }

                public void onNoAD(int i) {
                    this.a.aO = false;
                    this.a.aN.setVisibility(8);
                }

                public void onADSkip(AdItem adItem) {
                    w.a(this.a.q, false).a(adItem.getUrl());
                }
            });
        }
    }

    private void u() {
        this.bC = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.bC.registerApp("wx592fdc48acfbe290");
    }

    private void v() {
        if (this.t == null) {
            this.t = new b(this);
            this.r.setAdapter(this.t);
        } else {
            this.t.notifyDataSetChanged();
        }
        w();
        if (this.br) {
            this.br = false;
            if (com.budejie.www.activity.video.k.a(this.q).b()) {
                com.budejie.www.activity.video.k.a(this.q).k();
            }
        }
    }

    private void w() {
    }

    private void x() {
        com.budejie.www.http.i.a(getString(R.string.track_event_replay_post), com.budejie.www.http.j.a(this.aq), com.budejie.www.http.j.b((Context) this, this.aq));
    }

    private void b(boolean z) {
        this.r.getFootView().setVisibility(8);
        this.r.a = 4;
        this.ai = true;
        this.r.getFootView().findViewById(R.id.message_list_bottom_btn).setVisibility(8);
        this.Q = 1;
        this.R = 0;
        this.aR = "0";
        this.aS = "0";
        if (z) {
            this.Z.b(this.q, this.U, "0", "0", this.bK);
        } else {
            this.Z.a(this.q, this.U, "0", "0", this.bK);
        }
        H();
    }

    private void y() {
        this.Z.a(this.U, this.bq, new net.tsz.afinal.a.a(this) {
            final /* synthetic */ CommendDetail a;

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
                    this.a.aq = (ListItemObject) a.get(0);
                }
                this.a.b(this.a.aq);
                if (this.a.aB || this.a.aq == null || this.a.aq.getRichObject() == null) {
                    if (this.a.aq != null) {
                        this.a.y.setPostType(this.a.a(this.a.aq));
                        this.a.U = this.a.aq.getWid();
                        this.a.i();
                        this.a.d();
                    }
                    this.a.b(false);
                    return;
                }
                Intent intent = new Intent(this.a, RichPostDetail.class);
                intent.putExtra("listitem_object", this.a.aq);
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
                    aa.e(this.p, e.getMessage());
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
            Object string = this.ae.getString("id", "");
            if (TextUtils.isEmpty(string)) {
                string = an.e(this.q);
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
            String passtime = listItemObject.getPasstime();
            try {
                if (passtime.length() > 19) {
                    passtime = passtime.substring(passtime.length() - 19);
                }
                j = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(passtime).getTime();
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
                an.a(this.q, hashMap, "E01_A02");
            }
            parseInt = 0;
            hashMap2.put("ccnt", Integer.valueOf(parseInt));
            hashMap2.put("typ", Constants.SERVICE_SCOPE_FLAG_VALUE);
            hashMap = new HashMap();
            hashMap.put("type", string);
            hashMap.put("label", listItemObject.getPlateNames());
            an.a(this.q, hashMap, "E01_A02");
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        try {
            if (keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 4) {
                if (com.budejie.www.activity.video.k.a((Context) this).e == this && com.budejie.www.activity.video.k.a((Context) this).f != null && com.budejie.www.activity.video.k.a((Context) this).f.b(4)) {
                    return true;
                }
                if (this.K != null && this.X) {
                    an.b(this.q);
                    Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) this.P);
                    translateAnimation.setDuration(600);
                    this.K.setAnimation(translateAnimation);
                    translateAnimation.start();
                    this.K.setVisibility(8);
                    this.X = false;
                }
                if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0 && this.aW != null && this.aW.isShown()) {
                    this.aW.setVisibility(8);
                    return true;
                } else if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0 && this.A != null && this.A.isShown()) {
                    this.be = BottomStatus.NORMAL;
                    d();
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    private void a(List<CommentItem> list) {
        List a = this.ab.a();
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
        List a = this.ab.a();
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

    public void onClick(View view) {
        if (view == this.F) {
            an.b(this.q);
            if (this.ao) {
                finish();
            } else if ("writeCommend".equals(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)) {
                finish();
            } else {
                if (this.X) {
                    an.b(this.q);
                    Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) this.P);
                    translateAnimation.setDuration(600);
                    this.K.setAnimation(translateAnimation);
                    translateAnimation.start();
                    this.K.setVisibility(8);
                }
                this.X = false;
            }
        } else if (view == this.G) {
            if (an.a((Context) this)) {
                this.as = this.J.getText().toString().trim();
                int length = this.as.length();
                Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "comment_size");
                int i = 2;
                if (!TextUtils.isEmpty(configParams)) {
                    i = Integer.parseInt(configParams);
                }
                if (length < i) {
                    this.L = an.a((Activity) this, getString(R.string.commend_limit, new Object[]{Integer.valueOf(i)}), -1);
                    this.L.show();
                    return;
                } else if (length > R$styleable.Theme_Custom_send_btn_text_color) {
                    this.L = an.a((Activity) this, getString(R.string.text_beyond) + (length - 140) + getString(R.string.again_input), -1);
                    this.L.show();
                    return;
                } else {
                    this.V = this.ae.getString("id", "");
                    if (TextUtils.isEmpty(this.V)) {
                        an.a(this.q, 0, null, null, 0);
                        return;
                    } else if (this.ab.b(this.U, this.as)) {
                        this.L = an.a((Activity) this, getString(R.string.commentExist), -1);
                        this.L.show();
                        return;
                    } else {
                        E();
                        StringBuffer stringBuffer = new StringBuffer();
                        if (this.Y.a(this.q) && this.ag) {
                            stringBuffer.append("sina,");
                            this.ae.edit().putBoolean("sharesina", true).commit();
                        } else {
                            this.ae.edit().putBoolean("sharesina", false).commit();
                        }
                        if (this.Y.b(this.q) && this.ah) {
                            stringBuffer.append("qq,");
                            this.ae.edit().putBoolean("sharetenct", true).commit();
                        } else {
                            this.ae.edit().putBoolean("sharetenct", false).commit();
                        }
                        String stringBuffer2 = stringBuffer.toString();
                        if (stringBuffer2.endsWith(",")) {
                            stringBuffer2 = stringBuffer2.substring(0, stringBuffer.length() - 1);
                        }
                        if (TextUtils.isEmpty(stringBuffer2)) {
                            stringBuffer2 = "";
                        }
                        a(stringBuffer2, this.as);
                        if (this.ao) {
                            finish();
                            return;
                        }
                        return;
                    }
                }
            }
            Toast.makeText(this, R.string.nonet, 0).show();
        } else if (view == this.w) {
            this.N = null;
            K();
        } else if (view == this.v) {
            if (!this.bp) {
                this.V = this.ae.getString("id", "");
                if (TextUtils.isEmpty(this.V)) {
                    an.a(this.q, 0, null, null, 0);
                    return;
                }
                UserItem e = this.bz.e(this.V);
                if (TextUtils.isEmpty(e.getLevel()) || Integer.parseInt(e.getLevel()) < this.bj) {
                    p.a(this.q, this.q.getString(R.string.send_media_comment_level_message, new Object[]{Integer.valueOf(this.bj)}), getString(R.string.send_media_comment_level_ok), null);
                    return;
                }
                if (this.be == BottomStatus.VOICE) {
                    this.be = BottomStatus.NORMAL;
                } else {
                    this.be = BottomStatus.VOICE;
                }
                d();
            }
        } else if (view == this.E) {
            this.au = null;
            this.aw = null;
            this.ax = null;
            this.E.setVisibility(8);
            this.C.setVisibility(8);
        } else if (view == this.bm && this.e != null) {
            this.e.a(false);
        }
    }

    private void a(View view) {
        this.be = BottomStatus.NORMAL;
        d();
        if (this.aq != null) {
            view.setTag(this.aq);
            Bundle bundle = new Bundle();
            bundle.putInt("position", 0);
            bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this));
            bundle.putSerializable("weiboMap", this.bA);
            bundle.putSerializable("data", this.aq);
            bundle.putBoolean("has_screen_shoot", true);
            this.bv.a(5, bundle, this.g, this.bC, this.bz, this.Y, this.ad, this.bB, this.bt).onClick(view);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 711) {
            bindTencent();
        } else if (i2 == 0) {
            if (this.s.isShowing()) {
                this.s.dismiss();
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
                Log.d(this.p, "onactivityResult type=" + stringExtra);
                Log.d(this.p, "onactivityResult path=" + stringExtra2);
                Log.d(this.p, "onactivityResult thumbnail=" + stringExtra3);
                if ("video".equals(stringExtra)) {
                    this.aw = new File(stringExtra2);
                    b(stringExtra3, stringExtra2);
                    if (this.be == BottomStatus.KEYBOARD) {
                        this.x.setVisibility(8);
                    }
                    this.bt.sendEmptyMessageDelayed(15, 100);
                } else if (!CheckCodeDO.CHECKCODE_IMAGE_URL_KEY.equals(stringExtra)) {
                } else {
                    if (stringExtra2.endsWith("gif")) {
                        this.au = new File(stringExtra2);
                        b(stringExtra3, stringExtra2);
                        if (this.be == BottomStatus.KEYBOARD) {
                            this.x.setVisibility(8);
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
            this.bk = intent.getStringExtra("vote_data_key");
            a(false, true);
        } else if (i2 == 3) {
            r0 = intent.getStringExtra("VideoPathTag");
            this.ay = intent.getIntExtra("VideoTimeTag", 0);
            if (!TextUtils.isEmpty(r0)) {
                this.aw = null;
                this.ax = new File(r0);
                b("", r0);
                this.bt.sendEmptyMessageDelayed(15, 100);
            }
        }
    }

    private void a(String str, ArrayList<String> arrayList) {
        Intent intent = new Intent(this.q, EditPictureActivity.class);
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
                this.au = new File(str);
                b("", str);
                if (this.be == BottomStatus.KEYBOARD) {
                    this.x.setVisibility(8);
                }
                this.bt.sendEmptyMessageDelayed(15, 100);
            }
        } catch (OutOfMemoryError e) {
            this.L = an.a((Activity) this, getString(R.string.tougao_pic_too_big), -1);
            this.L.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(StringBuilder stringBuilder) {
    }

    private void z() {
        View footView = this.r.getFootView();
        if (footView != null) {
            ((ProgressBar) footView.findViewById(R.id.message_list_more_progressbar)).setVisibility(8);
            TextView textView = (TextView) footView.findViewById(R.id.message_list_more_tv);
            textView.setLayoutParams(new LinearLayout.LayoutParams(-1, an.a(this.q, 80)));
            textView.setVisibility(0);
            textView.setText("");
            footView.setVisibility(0);
            this.ai = false;
        }
    }

    private void A() {
        this.ae.getString("id", "");
        int i = this.Q + 1;
        this.R = 2;
        this.Z.a(this.q, this.U, this.aS, "2", this.bK);
    }

    private void a(String str, String str2) {
        this.S = ((int) System.currentTimeMillis()) / 100;
        this.ad.a(this.S, getString(R.string.commend_sending));
        String str3 = "";
        if (this.N != null) {
            str3 = this.N.getId();
        }
        this.Y.a(this.U, str2, str, this.V, this.bt, 12, str3, false, sendCommendActivity.a(this.aq));
        if (!"writeCommend".equals(com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)) {
            B();
        }
        x();
    }

    public void a(net.tsz.afinal.a.b bVar) {
        net.tsz.afinal.b bVar2 = new net.tsz.afinal.b(this.q.getApplication(), new v(this.q));
        bVar2.a("User-Agent", new WebView(this.q).getSettings().getUserAgentString() + NetWorkUtil.a());
        bVar2.a("cookie", NetWorkUtil.b(this.q));
        bVar2.a(NetWorkUtil.a(getApplicationContext()));
        StringBuilder append = new StringBuilder().append("http://d.api.budejie.com");
        com.budejie.www.http.j jVar = this.aQ;
        bVar2.b(com.lt.a.a(this.q).a(append.append(com.budejie.www.http.j.d(this.aq.getWid())).toString()), bVar, new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ CommendDetail a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void a(String str) {
                this.a.au = null;
                this.a.av = null;
                this.a.az = 0;
                this.a.ay = 0;
                this.a.aw = null;
                this.a.ax = null;
                this.a.bk = "";
                if (!TextUtils.isEmpty(str)) {
                    Log.i("CommendDetail", "-->" + str);
                    Message obtainMessage = this.a.bt.obtainMessage(12, str);
                    this.a.bd = true;
                    this.a.bt.sendMessage(obtainMessage);
                } else if (this.a.bg != null) {
                    this.a.bg.a(false, "");
                }
            }

            public void onFailure(Throwable th, int i, String str) {
                Log.i("CommendDetail", "onCommitVoice error : " + str);
                this.a.au = null;
                this.a.av = null;
                this.a.az = 0;
                this.a.ay = 0;
                this.a.aw = null;
                this.a.ax = null;
                this.a.bk = "";
                this.a.bd = false;
                if (this.a.bg != null) {
                    this.a.bg.a(false, "");
                }
            }
        });
    }

    private void a(boolean z, boolean z2) {
        String str;
        if (z) {
            str = "31";
            this.au = null;
            this.aw = null;
            this.ax = null;
            this.at = "";
            this.bk = "";
            this.ay = 0;
        } else if (this.aw != null) {
            str = "41";
            this.au = null;
            this.av = null;
            this.ax = null;
            this.az = 0;
            this.ay = 0;
        } else if (this.ax != null) {
            str = "71";
            this.au = null;
            this.av = null;
            this.az = 0;
            this.aw = this.ax;
        } else if (this.au != null) {
            str = com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
            this.av = null;
            this.az = 0;
            this.ay = 0;
            this.aw = null;
            this.ax = null;
        } else {
            str = "29";
            if (!z2) {
                if (TextUtils.isEmpty(this.at)) {
                    Toast.makeText(this.q, R.string.none_comment_content, 0).show();
                    return;
                }
                int length = this.at.length();
                Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "comment_size");
                int i = 2;
                if (!TextUtils.isEmpty(configParams)) {
                    i = Integer.parseInt(configParams);
                }
                if (length < i) {
                    this.L = an.a((Activity) this, getString(R.string.commend_limit, new Object[]{Integer.valueOf(i)}), -1);
                    this.L.show();
                    return;
                } else if (length > R$styleable.Theme_Custom_send_btn_text_color) {
                    this.L = an.a((Activity) this, getString(R.string.text_beyond) + (length - 140) + getString(R.string.again_input), -1);
                    this.L.show();
                    return;
                } else if (this.ab.b(this.U, this.at)) {
                    this.L = an.a((Activity) this, getString(R.string.commentExist), -1);
                    this.L.show();
                    return;
                }
            }
            this.av = null;
            this.az = 0;
            this.ay = 0;
        }
        if (this.bg != null) {
            this.bg.show();
        }
        J();
        String str2 = "";
        if (this.N != null) {
            str2 = this.N.getId();
        }
        a(this.aQ.a(this.q, this.aq.getWid(), this.V, str2, this.at, str, this.az, this.au, this.av, this.aw, this.bk, this.ay));
        x();
        this.be = BottomStatus.NORMAL;
        d();
    }

    private void B() {
        this.r.setSelection(0);
        an.b(this.q);
        Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) this.P);
        translateAnimation.setDuration(600);
        this.K.setAnimation(translateAnimation);
        translateAnimation.start();
        this.J.setText("");
        this.K.setVisibility(8);
        this.X = false;
    }

    private void c(final boolean z) {
        new Thread(this) {
            final /* synthetic */ CommendDetail b;

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
        this.W = true;
        this.bh = System.currentTimeMillis();
        Log.d(this.p, "onResume");
        d();
        this.bv = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
    }

    protected void onPause() {
        super.onPause();
        if (this.e != null) {
            this.e.a(true);
        }
        e();
        try {
            this.W = false;
            if (this.L != null) {
                this.L.cancel();
            }
            com.budejie.www.activity.video.k.a((Context) this).o();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.be = BottomStatus.NORMAL;
        d();
        if (this.x != null) {
            this.x.b();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.d != null) {
            this.d.clear();
        }
        com.budejie.www.activity.video.k.a((Context) this).p();
        EventBus.getDefault().unregister(this);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    private void C() {
        this.bg = new com.budejie.www.widget.f(this.q, R.style.dialogTheme);
        ((KeyboardListenerRelativeLayout) findViewById(R.id.root_layout)).setOnKeyboardChangeListener(this.bN);
        this.bl = (RelativeLayout) findViewById(R.id.screen_shot_mask);
        this.bm = (TextView) findViewById(R.id.screen_shot_stop);
        this.bl.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ CommendDetail a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.bm.setOnClickListener(this);
        this.r = (CustomListView) findViewById(R.id.listview);
        this.r.setmEnablePullToRefresh(false);
        this.aj = (FloatVideoRootLayout) findViewById(R.id.curtain_root_layout);
        this.r.setonLoadListener(this.bH);
        this.r.setOnItemClickListener(this.j);
        this.r.a((com.budejie.www.g.a) this);
        this.r.setonFlingListener(this.bI);
        this.r.setOnScrollListener(new com.nostra13.universalimageloader.core.d.e(com.nostra13.universalimageloader.core.d.a(), false, true, this.bJ));
        this.af = new ArrayList();
        this.u = (RelativeLayout) findViewById(R.id.recordBottomFrame);
        com.budejie.www.activity.video.p.a(this, this.u);
        this.v = (ImageView) findViewById(R.id.recordOrKeyboard);
        this.v.setOnClickListener(this);
        this.w = (TextView) findViewById(R.id.write_comment_tv);
        this.w.setOnClickListener(this);
        this.C = (RelativeLayout) findViewById(R.id.select_image_video_mark);
        this.D = (ImageView) findViewById(R.id.select_image_video_mark_iv);
        this.E = (ImageView) findViewById(R.id.select_image_video_mark_cancel);
        this.E.setOnClickListener(this);
        this.x = (RecordVoiceView) findViewById(R.id.record_voice_view);
        this.z = findViewById(R.id.record_voice_mask);
        this.A = findViewById(R.id.record_send_voice_mask);
        this.B = findViewById(R.id.keyword_mask);
        this.y = (CommentOperateView) findViewById(R.id.opetate_view);
        this.y.setOperateListenr(new i(this));
        this.y.setPostType(a(this.aq));
        this.x.a(this.z, this.A);
        this.x.setRecordVoiceHandler(this.bO);
        this.aW = (RelativeLayout) findViewById(R.id.VoiceToWordsLayout);
        this.aX = (RelativeLayout) findViewById(R.id.VoiceToWordsLoadingLayout);
        this.aY = (TextView) findViewById(R.id.VoiceToWordsTextView);
        this.aZ = (TextView) findViewById(R.id.VoiceToWordsCancelTextView);
        this.ba = (ScrollView) findViewById(R.id.VoiceToWordsScrollView);
        this.bb = (LinearLayout) findViewById(R.id.VoiceToWordsFailsLayout);
        this.bc = (TextView) findViewById(R.id.VoiceToWordsFailsTextView);
        this.aW.setVisibility(8);
        this.aW.setOnClickListener(this.o);
        this.ba.setOnClickListener(this.o);
        this.aY.setOnClickListener(this.o);
        this.aZ.setOnClickListener(this.o);
        this.ad = new com.elves.update.a(this);
        this.B.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommendDetail a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.B.setVisibility(8);
            }
        });
        this.A.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommendDetail a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.be = BottomStatus.NORMAL;
                this.a.d();
            }
        });
    }

    private void D() {
        this.M = getIntent();
        this.c = this.M.getBooleanExtra("is_from_commonlabel", false);
        this.aB = this.M.getBooleanExtra("is_rich_ommend", false);
        this.aq = (ListItemObject) this.M.getSerializableExtra("listitem_object");
        this.bq = this.M.getStringExtra("request_detail_url");
        if (this.aq != null) {
            this.aC = this.M.getBooleanExtra("to_comment", false);
            VoteData voteData = this.aq.getVoteData();
            if (!(voteData == null || voteData.isVoted())) {
                ax.a(this.aq, this.q);
            }
            if (!this.aB) {
                b(this.aq);
            }
            Log.i("CommendDetail", "帖子评论数:" + this.aq.getComment());
            if ("0".equals(this.aq.getComment())) {
                com.budejie.www.activity.video.k.a(this.q).h();
            }
            this.U = this.aq.getWid();
            b = this.aq.getImgUrl();
            com.budejie.www.http.i.a(getString(R.string.track_event_see_comment), com.budejie.www.http.j.a(this.aq), com.budejie.www.http.j.b((Context) this, this.aq));
        } else {
            this.U = this.M.getStringExtra("msg_wid");
            Log.i("zhangxitao", "dataid:" + this.U);
        }
        if (!this.aB && this.aq != null && this.aq.getRichObject() != null) {
            Intent intent = new Intent(this, RichPostDetail.class);
            if (!this.aB) {
                intent.putExtra("rich_post_open_type", "rich_post_comment");
            }
            intent.putExtra("listitem_object", this.aq);
            startActivity(intent);
            finish();
        }
    }

    private void d(boolean z) {
        this.K = (RelativeLayout) findViewById(R.id.comment_write_layout);
        this.H = (TextView) findViewById(R.id.write_comment_txt);
        this.F = (TextView) findViewById(R.id.write_left_btn);
        this.G = (TextView) findViewById(R.id.comment_send);
        this.J = (EditText) findViewById(R.id.commend);
        this.I = (TextView) findViewById(R.id.textview);
        this.J.setText("");
        this.I.setTextColor(-16777216);
        this.F.setText(R.string.cancel);
        onrefreshTheme();
        this.F.setOnClickListener(this);
        this.G.setOnClickListener(this);
        this.J.addTextChangedListener(this.k);
        this.J.setFocusableInTouchMode(true);
        this.J.requestFocus();
        this.ad = new com.elves.update.a(this);
    }

    private void E() {
        this.ag = this.ae.getBoolean("sharesina", true);
        this.ah = this.ae.getBoolean("sharetenct", true);
    }

    private void F() {
        this.aG = new com.budejie.www.adapter.d.d(this.q, this, this.aq, 0, !this.aC);
        this.aJ = this.aG.b();
        this.aG.a((com.budejie.www.adapter.b) this.aJ.getTag());
        this.aH = new com.budejie.www.adapter.d.b(this.q, null, this.aq, 0);
        this.aK = this.aH.b();
        this.aH.a((com.budejie.www.adapter.b) this.aK.getTag());
        this.r.addHeaderView(this.aJ);
        this.r.addHeaderView(this.aK);
        if (!this.c) {
            this.aI = new com.budejie.www.adapter.d.c(this.q, null, this.aq, 0);
            this.aL = this.aI.b();
            this.aI.a((com.budejie.www.adapter.b) this.aL.getTag());
            this.r.addHeaderView(this.aL);
        }
    }

    private void a(CommentItem commentItem, View view) {
        if (!commentItem.isAlreadyDingCai()) {
            ImageView imageView = (ImageView) view.findViewById(R.id.commentDingIv);
            TextView textView = (TextView) view.findViewById(R.id.commentLikeCount);
            String id = commentItem.getId();
            com.budejie.www.util.d.a(this.q, (View) imageView.getParent(), "1");
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
            this.Z.a(id, "like");
            this.ab.a(id, "like");
        }
    }

    private void b(CommentItem commentItem, View view) {
        if (!commentItem.isAlreadyDingCai()) {
            ImageView imageView = (ImageView) view.findViewById(R.id.commentCaiIv);
            TextView textView = (TextView) view.findViewById(R.id.commentCaiCount);
            String id = commentItem.getId();
            com.budejie.www.util.d.a(this.q, (View) imageView.getParent(), "1");
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
            this.Z.a(id, "hate");
            this.ab.a(id, "hate");
        }
    }

    private void d(String str) {
        BudejieApplication.a.a(RequstMethod.GET, com.budejie.www.http.j.j(str), this.aQ, this.bL);
    }

    private void e(String str) {
        BudejieApplication.a.a(RequstMethod.GET, "https://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().k(this, str, this.aA), this.h);
        this.bt.sendEmptyMessage(970);
    }

    private void G() {
        BudejieApplication.a.a(RequstMethod.GET, "https://api.budejie.com/api/api_open.php", new com.budejie.www.http.j().p(this, this.aA), this.i);
        this.bt.sendEmptyMessage(970);
    }

    private void a(int i, final CommentItem commentItem, View view) {
        int parseInt;
        final Dialog dialog = new Dialog(this.q, R.style.DialogTheme_CreateUgc);
        LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) this.q.getSystemService("layout_inflater")).inflate(R.layout.alert_item_latout, null);
        linearLayout.setMinimumWidth(10000);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.content_list);
        linearLayout2.setBackgroundResource(com.budejie.www.util.j.aC);
        Button button = (Button) linearLayout.findViewById(R.id.btn_cancel);
        button.setBackgroundResource(com.budejie.www.util.j.aC);
        button.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommendDetail b;

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelOffset(R.dimen.list_item_writer_profile));
        this.aU.clear();
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
            if (this.aq == null || this.aq.getUid() == null) {
                this.aU.add("回复");
            } else if (!commentItem.getUid().equals(this.V)) {
                this.aU.add("回复");
            }
            this.aU.add("语音回复");
        }
        if (this.aq != null && this.aq.getUid() != null && commentItem.getUid().equals(this.V)) {
            this.aP = i;
            this.aU.add("删除");
        } else if (this.aq == null || this.aq.getUid() == null || !this.aq.getUid().equals(this.V)) {
            this.aU.add("举报");
        } else {
            this.aP = i;
            this.aU.add("删除");
        }
        if (an.v(this.q)) {
            this.aU.add("删除");
            this.aU.add("删除并拉黑");
        }
        if (commentItem.getType().equals("audio")) {
            this.aU.add("转文字");
        }
        for (int i2 = 0; i2 < this.aU.size(); i2++) {
            String str = (String) this.aU.get(i2);
            View textView = new TextView(this.q);
            textView.setGravity(17);
            textView.setTextColor(com.budejie.www.h.c.a().c(R.attr.item_title_name_color));
            textView.setText(str);
            textView.setTextSize(2, 17.0f);
            textView.setBackgroundResource(com.budejie.www.util.j.aC);
            textView.setTag(Integer.valueOf(i2));
            if (str.equals("删除")) {
                this.aA = commentItem.getId();
                textView.setOnClickListener(new d(this, dialog));
            } else if (str.equals("删除并拉黑")) {
                this.aA = commentItem.getId();
                textView.setOnClickListener(new c(this, dialog));
            } else if (str.equals("拉黑")) {
                this.aA = commentItem.getId();
                textView.setOnClickListener(new a(this, dialog));
            } else if (str.equals("转文字")) {
                this.aA = commentItem.getId();
                textView.setOnClickListener(new l(this, dialog));
            } else if (str.equals("回复")) {
                this.aA = commentItem.getId();
                textView.setOnClickListener(new k(this, dialog, commentItem, 0));
            } else if (str.equals("语音回复")) {
                this.aA = commentItem.getId();
                textView.setOnClickListener(new k(this, dialog, commentItem, 1));
            } else {
                textView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ CommendDetail c;

                    public void onClick(View view) {
                        dialog.dismiss();
                        if (((String) this.c.aU.get(Integer.parseInt("" + view.getTag()))).equals("举报")) {
                            String string = this.c.ae.getString("id", "");
                            String id = commentItem.getId();
                            this.c.Z.a(this.c.getApplicationContext(), this.c.U, id, string);
                        }
                    }
                });
            }
            textView.setLayoutParams(layoutParams);
            if (i2 == 0) {
                linearLayout2.addView(textView);
            } else {
                View imageView = new ImageView(this.q);
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
            if (!this.aB) {
                this.aG.a((com.budejie.www.adapter.b) this.aJ.getTag());
                this.aH.a((com.budejie.www.adapter.b) this.aK.getTag());
            }
            this.bt.sendMessage(this.bt.obtainMessage(1, str));
        } else if (i == 1111114) {
            this.Q++;
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
        b(true);
    }

    private void H() {
        q();
        m();
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        super.onComplete(jSONObject);
        HashMap a = z.a(jSONObject);
        if (a != null && a.size() != 0) {
            this.ae.edit().putString("openid", (String) a.get("qzone_uid")).putString("qzone_token", (String) a.get("qzone_token")).putString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN, (System.currentTimeMillis() + (Long.parseLong((String) a.get(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN)) * 1000)) + "").commit();
            this.V = this.ae.getString("id", "");
            this.Y.a((String) a.get("qzone_uid"), this.V, (String) a.get("qzone_token"), 10, this.bt);
        }
    }

    public void onError(UiError uiError) {
        super.onError(uiError);
        Toast.makeText(this.q, "code:" + uiError.errorCode + ", msg:" + uiError.errorMessage + ", detail:" + uiError.errorDetail, 0).show();
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        super.onSuccess(bVar);
        try {
            this.V = this.ae.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this.q, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.Y.a(mAccessToken, this.V, 4, this.bt);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    public void onrefreshTheme() {
        super.onrefreshTheme();
        this.aj.setBackgroundResource(com.budejie.www.util.j.Y);
        if (this.K != null) {
            this.K.setBackgroundResource(com.budejie.www.util.j.u);
            this.H.setTextColor(this.ap.getColor(com.budejie.www.util.j.b));
            this.I.setTextColor(this.ap.getColor(com.budejie.www.util.j.aj));
            onRefreshTitleFontTheme(this.F, false);
            onRefreshTitleFontTheme(this.G, false);
        }
        if (this.t != null) {
            this.t.notifyDataSetChanged();
        }
    }

    public void a(int i, Object obj, boolean z) {
        switch (i) {
            case 0:
                if (z) {
                    this.an = false;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void bindTencent() {
        this.V = this.ae.getString("id", "");
        String sharePersistent = Util.getSharePersistent(this.q, "ACCESS_TOKEN");
        this.Y.a(Util.getSharePersistent(this.q, "NAME"), sharePersistent, Util.getSharePersistent(this.q, "OPEN_ID"), this.V, 5, this.bt);
    }

    private void I() {
        if (!this.m) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.listview_container);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            linearLayout.setLayoutParams(layoutParams);
            this.aj.setmMinTopHeight(0);
            this.m = true;
        }
    }

    public void a(boolean z) {
        if (this.bo != null) {
            this.bo.a(z);
        }
    }

    public void e(View view, ListItemObject listItemObject) {
        if (listItemObject != null && !TextUtils.isEmpty(listItemObject.getWid()) && !listItemObject.getWid().equals(this.aq.getWid())) {
            view.setTag(listItemObject);
            this.bv.a(3, null).onClick(view);
        }
    }

    public void a(View view, ListItemObject listItemObject, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(PersonalProfileActivity.c, listItemObject.getUid());
        bundle.putString(PersonalProfileActivity.d, str);
        this.bv.a(7, bundle).onClick(view);
    }

    public void c(View view, ListItemObject listItemObject) {
        p.a(this, listItemObject, this.g, c(listItemObject.getUid()), this.aM);
    }

    public boolean c(String str) {
        if (TextUtils.isEmpty(str) || !str.equals(this.V)) {
            return false;
        }
        return true;
    }

    public void a(View view, ListItemObject listItemObject) {
        if (this.bo.a()) {
            TipPopUp.a((Context) this, TypeControl.dingtie);
            this.bw.a("ding", this.g, listItemObject);
            this.bw.a(listItemObject, this.g, "ding");
            Object tag = view.getTag(R.id.DOUBLE_CLICK_KEY);
            if (view != null && tag != null && ((Boolean) tag).booleanValue()) {
                try {
                    q.a(com.budejie.www.activity.video.k.a(this.q).b.b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void b(View view, ListItemObject listItemObject) {
        this.bw.a("cai", this.g, listItemObject);
        this.bw.a(listItemObject, this.g, "cai");
    }

    public void a(View view, ListItemObject listItemObject, int i) {
        listItemObject.setForwardNoCollect(false);
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this));
        bundle.putSerializable("weiboMap", this.bA);
        bundle.putSerializable("data", listItemObject);
        view.setTag(listItemObject);
        this.bv.a(5, bundle, this.g, this.bC, this.bz, this.Y, this.ad, this.bB, this.bt).onClick(view);
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
            this.q.startActivity(intent);
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_exit_left_in, R.anim.push_right_out);
    }

    public void d() {
        Log.d(this.p, "changeBottomStatus currentBottomStatus=" + this.be);
        switch (this.be) {
            case NORMAL:
                this.x.setVisibility(8);
                this.y.setVisibility(8);
                this.A.setVisibility(8);
                this.N = null;
                this.w.setText("写评论...");
                return;
            case KEYBOARD:
                this.x.setVisibility(8);
                this.y.setVisibility(8);
                return;
            case VOICE:
                if (!this.bp) {
                    this.y.setVisibility(8);
                    com.budejie.www.activity.video.k.a(this.q).h();
                    if (!this.bf) {
                        f();
                        this.x.setVisibility(0);
                    }
                    this.A.setVisibility(0);
                    return;
                }
                return;
            case OPERATE:
                this.x.setVisibility(8);
                com.budejie.www.activity.video.k.a(this.q).h();
                if (!this.bf) {
                    this.y.setVisibility(0);
                }
                this.A.setVisibility(0);
                return;
            default:
                return;
        }
    }

    private void b(String str, String str2) {
        this.C.setVisibility(0);
        this.E.setVisibility(0);
        this.D.setTag(str2);
        this.bi.a(this.D, str, str2, this.n);
    }

    private void J() {
        if (this.D != null) {
            this.D.setImageBitmap(null);
            this.C.setVisibility(8);
        }
        if (this.E != null) {
            this.E.setVisibility(8);
        }
    }

    private void f(String str) {
        final Dialog dialog = new Dialog(this.q, R.style.dialogTheme);
        View inflate = this.q.getLayoutInflater().inflate(R.layout.mycomment_delete_dialog, null);
        Button button = (Button) inflate.findViewById(R.id.mycomment_delete_cancelBtn);
        Button button2 = (Button) inflate.findViewById(R.id.mycomment_delete_sureBtn);
        button2.setTag(str);
        button.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommendDetail b;

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        button2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommendDetail b;

            public void onClick(View view) {
                this.b.G();
                dialog.dismiss();
            }
        });
        dialog.setContentView(inflate);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = an.a(this.q, 300);
        window.setAttributes(attributes);
        dialog.show();
    }

    private void K() {
        this.V = this.ae.getString("id", "");
        if (TextUtils.isEmpty(this.V)) {
            an.a(this.q, 0, null, null, 0);
        } else {
            com.budejie.www.util.a.a(this.q, this.aq, this.N);
        }
    }

    public void onEventMainThread(UpdateCommentAction updateCommentAction) {
        this.br = true;
        b(true);
    }

    public void onEventMainThread(DetailAction detailAction) {
        if (!this.W) {
            return;
        }
        if (DetailAction.SCREEN_SHOT == detailAction) {
            L();
        } else if (DetailAction.FINISH_PAGE == detailAction) {
            finish();
        }
    }

    public void onEventMainThread(SimpleVideoClickAction simpleVideoClickAction) {
        if (SimpleVideoClickAction.TO_NORMAL == simpleVideoClickAction && this.r != null) {
            this.r.setSelection(1);
        }
    }

    private void L() {
        this.e = new al(this.q, findViewById(R.id.listview_container), an.y(this.q) * 4);
        this.e.a((int) R.drawable.screen_shoot_bottom);
        this.e.a(this.bn, new com.budejie.www.util.al.b(this) {
            final /* synthetic */ CommendDetail a;

            {
                this.a = r1;
            }

            public void a(String str) {
                this.a.e = null;
                if (this.a.bl != null) {
                    this.a.bl.setVisibility(8);
                }
                if (!TextUtils.isEmpty(str)) {
                    Intent intent = new Intent(this.a.q, HotCommentShareActivity.class);
                    intent.putExtra("hot_comment_share_post", this.a.aq);
                    intent.putExtra("hot_comment_share_image", str);
                    this.a.q.startActivity(intent);
                }
            }

            public void a() {
                if (this.a.bm != null) {
                    this.a.bm.setText(R.string.screen_shot_processing);
                    this.a.bm.setClickable(false);
                }
            }

            public void b() {
                if (this.a.bl != null) {
                    this.a.bl.setVisibility(0);
                    if (this.a.bm != null) {
                        this.a.bm.setText(R.string.screen_shot_stop);
                        this.a.bm.setClickable(true);
                    }
                }
            }
        });
    }

    public void e() {
        Log.d(this.p, "stopCommentVoice");
        BudejieApplication budejieApplication = (BudejieApplication) this.q.getApplication();
        if (budejieApplication.a() == null || budejieApplication.a() == Status.end) {
            ac a = ac.a(this.q);
            if (a.c()) {
                a.i();
            }
        }
    }

    public void f() {
        e();
        BudejieApplication budejieApplication = (BudejieApplication) this.q.getApplication();
        com.budejie.www.service.MediaPlayerServer.a f = budejieApplication.f();
        if (f == null) {
            return;
        }
        if (f.a() || f.n()) {
            f.d();
            budejieApplication.a(Status.end);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Log.d(this.p, "onWindowFocusChanged hasFocus=" + z);
        if (z && this.e == null) {
            g();
        }
    }

    public void g() {
        this.bt.sendEmptyMessageDelayed(19, 500);
    }
}
