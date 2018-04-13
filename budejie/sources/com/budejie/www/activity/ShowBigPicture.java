package com.budejie.www.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import bdj.uk.co.senab.photoview.PhotoView;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.alipay.sdk.util.j;
import com.androidex.util.ImageUtil;
import com.budejie.www.R;
import com.budejie.www.activity.posts.PostsActivity;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.busevent.DetailAction;
import com.budejie.www.c.b;
import com.budejie.www.c.d;
import com.budejie.www.c.m;
import com.budejie.www.f.a;
import com.budejie.www.http.n;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.au;
import com.budejie.www.util.p;
import com.budejie.www.util.z;
import com.budejie.www.widget.PhotoViewContainerScrollView;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.f;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.c;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.sprite.ads.nati.NativeAdData;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.UiError;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UpdateConfig;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import pl.droidsonroids.gif.GifDrawable;

public class ShowBigPicture extends OauthWeiboBaseAct implements MediaScannerConnectionClient, OnClickListener, a {
    private RelativeLayout A;
    private LinearLayout B;
    private RelativeLayout C;
    private Button D;
    private TextView E;
    private Button F;
    private TextView G;
    private Button H;
    private Button I;
    private ImageView J;
    private String K;
    private n L;
    private m M;
    private HashMap<String, String> N;
    private d O;
    private b P;
    private Dialog Q;
    private com.budejie.www.http.b R = null;
    private com.budejie.www.g.b S;
    private IWXAPI T;
    private com.elves.update.a U;
    private ListItemObject V;
    private ArrayList<String> W;
    private String X = "";
    private String Y = (Environment.getExternalStorageDirectory().toString() + "/budejie/");
    private String Z = "";
    String a = "ShowBigPicture";
    private boolean aa;
    private SubsamplingScaleImageView ab;
    private boolean ac = false;
    private NativeAdData ad = null;
    private boolean ae;
    private f af = new SubsamplingScaleImageView.d(this) {
        final /* synthetic */ ShowBigPicture a;

        {
            this.a = r1;
        }

        public void a() {
            aa.b("SubsamplingScaleImageView", "onImageLoaded");
            this.a.ac = true;
        }
    };
    private c ag = com.budejie.www.e.d.a(new com.nostra13.universalimageloader.core.b.d());
    private com.nostra13.universalimageloader.core.d.d ah = new com.nostra13.universalimageloader.core.d.d(this) {
        final /* synthetic */ ShowBigPicture a;

        {
            this.a = r1;
        }

        public void onProgressUpdate(String str, View view, int i, int i2) {
            this.a.t.setMax(i2);
            this.a.t.setProgress(i);
        }
    };
    private com.nostra13.universalimageloader.core.d.b ai = new com.nostra13.universalimageloader.core.d.b(this) {
        final /* synthetic */ ShowBigPicture a;

        {
            this.a = r1;
        }

        public void onLoadingStarted(String str, View view) {
            this.a.u.setVisibility(0);
        }

        public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
            this.a.q.setVisibility(0);
            this.a.u.setVisibility(8);
        }

        public void onLoadingFailed(String str, View view, FailReason failReason) {
            super.onLoadingFailed(str, view, failReason);
            if (this.a.W.size() > 0) {
                com.nostra13.universalimageloader.core.d.a().a(com.lt.a.a(this.a.b).a((String) this.a.W.remove(0)), this.a.q, this.a.ag, this.a.ai, this.a.ah);
                return;
            }
            Toast.makeText(this.a.b, R.string.load_picture_failed, 0).show();
        }
    };
    private Handler aj = new Handler(this) {
        final /* synthetic */ ShowBigPicture a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            boolean z = false;
            int i = message.what;
            if (i == 1) {
                Toast.makeText(this.a.b, R.string.load_picture_failed, z).show();
            } else if (i == 814) {
                this.a.r.show();
            } else if (i == 815) {
                this.a.r.cancel();
            } else if (i == 0) {
                this.a.aa = z;
                r0 = (String) message.obj;
                this.a.r.dismiss();
                r1 = new Builder(this.a);
                r1.setTitle(R.string.system_tip);
                r1.setMessage(r0);
                r1.setPositiveButton(R.string.sure, null);
                if (!this.a.b.isFinishing()) {
                    r1.create().show();
                }
                this.a.m();
            } else if (i == 3) {
                if (!this.a.i()) {
                    this.a.aa = z;
                    r0 = (String) message.obj;
                    this.a.r.dismiss();
                    r1 = new Builder(this.a);
                    r1.setTitle(R.string.system_tip);
                    r1.setMessage(r0);
                    r1.setPositiveButton(R.string.sure, null);
                    if (!this.a.b.isFinishing()) {
                        r1.create().show();
                    }
                }
            } else if (i == 11) {
                CharSequence b = ai.b(this.a.b);
                if (an.j(this.a.b) && an.k(this.a.b) && !b.equals("")) {
                    an.a(this.a.b, z);
                    sendEmptyMessage(13);
                } else {
                    an.a(this.a.b, (int) R.string.collected, (int) R.drawable.collect_tip).show();
                }
                if (!TextUtils.isEmpty(b)) {
                    this.a.R.a("add", ai.b(this.a.b), (String) message.obj, 971);
                }
            } else if (i == 12) {
                an.a(this.a.b, (int) R.string.collect_fail, (int) R.drawable.collect_tip).show();
            } else if (i == 100001) {
                an.a(this.a.b, this.a.b.getString(R.string.forwardAndCollect_succeed), -1).show();
                if (!TextUtils.isEmpty(ai.b(this.a.b))) {
                    this.a.R.a("add", ai.b(this.a.b), (String) message.obj, 971);
                }
            } else if (i == 829) {
                r0 = (String) message.obj;
                this.a.P.a("collectTable", r0);
                an.a(this.a.b, this.a.b.getString(R.string.delete_success), -1).show();
                this.a.R.a("delete", ai.b(this.a.b), r0, 971);
            } else if (i == 812) {
                r0 = (String) message.obj;
                if (TextUtils.isEmpty(r0)) {
                    this.a.v = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                    this.a.v.show();
                    MobclickAgent.onEvent(this.a.b, "weibo_bind", "sina_faild");
                } else {
                    try {
                        r1 = Integer.parseInt(r0);
                    } catch (NumberFormatException e) {
                    }
                    if (r1 < 0) {
                        this.a.v = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                        this.a.v.show();
                        MobclickAgent.onEvent(this.a.b, "weibo_bind", "sina_faild");
                    } else {
                        r2 = z.c(r0);
                        if (r2 == null || r2.isEmpty()) {
                            this.a.v = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                            this.a.v.show();
                            MobclickAgent.onEvent(this.a.b, "weibo_bind", "sina_faild");
                        } else {
                            r1 = (String) r2.get("result_msg");
                            if ("0".equals((String) r2.get(j.c))) {
                                MobclickAgent.onEvent(this.a.b, "weibo_bind", "sina_success");
                                this.a.K = (String) r2.get("id");
                                this.a.M.a(this.a.K, r2);
                                ai.a(this.a.b, this.a.K, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                if (OauthWeiboBaseAct.mAccessToken != null) {
                                    this.a.M.a(this.a.K, OauthWeiboBaseAct.mAccessToken.e());
                                }
                                this.a.N = this.a.L.a(this.a.K);
                                this.a.v = an.a(this.a.b, this.a.b.getString(R.string.bind_successed), -1);
                                this.a.v.show();
                                this.a.L.a(this.a.b, this.a.V, "sina", this.a.K, this.a.N, this.a.U, (Handler) this);
                                if (this.a.k()) {
                                    this.a.Q.show();
                                }
                            } else {
                                an.a(this.a.b, r1, -1).show();
                            }
                        }
                    }
                }
                this.a.s.cancel();
            } else if (i == 929) {
                r0 = (String) message.obj;
                if (TextUtils.isEmpty(r0)) {
                    this.a.v = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                    this.a.v.show();
                    MobclickAgent.onEvent(this.a.b, "weibo_bind", "qzone_faild");
                } else {
                    try {
                        r1 = Integer.parseInt(r0);
                    } catch (NumberFormatException e2) {
                    }
                    if (r1 < 0) {
                        this.a.v = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                        this.a.v.show();
                        MobclickAgent.onEvent(this.a.b, "weibo_bind", "qzone_faild");
                    } else {
                        r2 = z.c(r0);
                        if (r2 == null || r2.isEmpty()) {
                            this.a.v = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                            this.a.v.show();
                            MobclickAgent.onEvent(this.a.b, "weibo_bind", "qzone_faild");
                        } else {
                            r1 = (String) r2.get("result_msg");
                            if ("0".equals((String) r2.get(j.c))) {
                                MobclickAgent.onEvent(this.a.b, "weibo_bind", "qzone_success");
                                this.a.K = (String) r2.get("id");
                                this.a.M.a(this.a.K, r2);
                                ai.a(this.a.b, this.a.K, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.N = this.a.L.a(this.a.K);
                                this.a.v = an.a(this.a.b, this.a.b.getString(R.string.bind_successed), -1);
                                this.a.v.show();
                                this.a.L.a(this.a.b, this.a.V, com.tencent.connect.common.Constants.SOURCE_QZONE, this.a.K, this.a.N, this.a.U, (Handler) this);
                                if (this.a.k()) {
                                    this.a.Q.show();
                                }
                            } else {
                                an.a(this.a.b, r1, -1).show();
                            }
                        }
                    }
                }
                this.a.s.cancel();
            } else if (i == 813) {
                r0 = (String) message.obj;
                if (TextUtils.isEmpty(r0)) {
                    this.a.v = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                    this.a.v.show();
                    MobclickAgent.onEvent(this.a.b, "weibo_bind", "tencent_faild");
                } else {
                    try {
                        r1 = Integer.parseInt(r0);
                    } catch (NumberFormatException e3) {
                    }
                    if (r1 < 0) {
                        this.a.v = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                        this.a.v.show();
                        MobclickAgent.onEvent(this.a.b, "weibo_bind", "tencent_faild");
                    } else {
                        r2 = z.c(r0);
                        if (r2 == null || r2.isEmpty()) {
                            this.a.v = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                            this.a.v.show();
                            MobclickAgent.onEvent(this.a.b, "weibo_bind", "tencent_faild");
                        } else {
                            r1 = (String) r2.get("result_msg");
                            if ("0".equals((String) r2.get(j.c))) {
                                MobclickAgent.onEvent(this.a.b, "weibo_bind", "tencent_success");
                                this.a.K = (String) r2.get("id");
                                this.a.M.a(this.a.K, r2);
                                ai.a(this.a.b, this.a.K, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.N = this.a.L.a(this.a.K);
                                this.a.v = an.a(this.a.b, this.a.b.getString(R.string.bind_successed), -1);
                                this.a.v.show();
                                this.a.L.a(this.a.b, this.a.V, "qq", this.a.K, this.a.N, this.a.U, (Handler) this);
                                if (this.a.k()) {
                                    this.a.Q.show();
                                }
                            } else {
                                an.a(this.a.b, r1, -1).show();
                            }
                        }
                    }
                }
                this.a.s.cancel();
            } else if (i == 816) {
                Bundle bundle = (Bundle) message.obj;
                CharSequence string = bundle.getString(j.c);
                i = bundle.getInt("notificationId");
                if (TextUtils.isEmpty(string)) {
                    this.a.U.a(i, z, (int) R.string.forwarfail);
                } else if ("0".equals(string)) {
                    this.a.U.a(i, true, (int) R.string.forwardsuccess);
                } else {
                    this.a.U.a(i, z, (int) R.string.forwarfail);
                }
                new Thread(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void run() {
                        try {
                            Thread.sleep(1000);
                            this.b.a.aj.sendMessage(this.b.a.aj.obtainMessage(817, Integer.valueOf(i)));
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
            } else if (i == 817) {
                this.a.U.a(((Integer) message.obj).intValue());
            }
        }
    };
    private OnClickListener ak = new OnClickListener(this) {
        final /* synthetic */ ShowBigPicture a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.mycomment_delete_cancelBtn) {
                this.a.Q.dismiss();
            } else if (view.getId() == R.id.mycomment_delete_sureBtn) {
                this.a.Q.dismiss();
                ArrayList a = this.a.O.a();
                if (a != null && !a.isEmpty()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < a.size(); i++) {
                        if (i == a.size() - 1) {
                            stringBuffer.append((String) a.get(i));
                        } else {
                            stringBuffer.append((String) a.get(i)).append(",");
                        }
                    }
                    this.a.R.a("add", this.a.K, stringBuffer.toString(), 971);
                }
            }
        }
    };
    private MediaScannerConnection al;
    ShowBigPicture b;
    String c;
    String d;
    String e;
    boolean f;
    int g;
    int h;
    int i;
    int j;
    int k;
    com.budejie.www.util.f l;
    SharedPreferences m;
    bdj.uk.co.senab.photoview.d.d n = new bdj.uk.co.senab.photoview.d.d(this) {
        final /* synthetic */ ShowBigPicture a;

        {
            this.a = r1;
        }

        public void a(View view, float f, float f2) {
            this.a.b.finish();
        }
    };
    OnClickListener o = new OnClickListener(this) {
        final /* synthetic */ ShowBigPicture a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.b.finish();
        }
    };
    private PhotoViewContainerScrollView p;
    private PhotoView q;
    private Dialog r;
    private Dialog s;
    private ProgressBar t;
    private LinearLayout u;
    private Toast v;
    private String[] w;
    private List<String> x;
    private RelativeLayout y;
    private Button z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WindowManager windowManager = (WindowManager) getSystemService("window");
        this.i = windowManager.getDefaultDisplay().getWidth();
        this.j = windowManager.getDefaultDisplay().getHeight();
        setContentView(R.layout.showbigimg);
        this.b = this;
        n();
        a();
        f();
        overridePendingTransition(R.anim.show_picture_enter, R.anim.show_picture_excessive);
        if (an.a((Context) this)) {
            d();
        } else if (!this.f) {
            this.v = an.a((Activity) this, getString(R.string.nonet), -1);
            this.v.show();
        }
        e();
    }

    protected void onResume() {
        super.onResume();
        this.S = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && !this.ae) {
            this.ae = true;
            LayoutParams layoutParams = this.q.getLayoutParams();
            int[] iArr = new int[2];
            this.q.getLocationOnScreen(iArr);
            layoutParams.height = this.y.getHeight() - iArr[1];
            aa.b(this.a, "onWindowFocusChanged params.height=" + layoutParams.height);
            this.q.setLayoutParams(layoutParams);
            this.ab.setLayoutParams(layoutParams);
        }
    }

    private void d() {
        this.T = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.T.registerApp("wx592fdc48acfbe290");
    }

    @TargetApi(11)
    public void a() {
        try {
            an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
            this.y = (RelativeLayout) findViewById(R.id.showbigimg_layout);
            this.u = (LinearLayout) findViewById(R.id.progress_layout);
            this.t = (ProgressBar) findViewById(R.id.gif_progress);
            this.q = (PhotoView) findViewById(R.id.main_img);
            this.ab = (SubsamplingScaleImageView) findViewById(R.id.subsamplingScaleImageView);
            this.ab.setOnClickListener(this.o);
            this.ab.setOnImageEventListener(this.af);
            this.ab.setMaxScale(20.0f);
            this.ab.setDoubleTapZoomScale(4.0f);
            this.ab.setDoubleTapZoomStyle(2);
            this.ab.setMinimumScaleType(2);
            this.p = (PhotoViewContainerScrollView) findViewById(R.id.container_scroll_view);
            this.p.setScrollBottomListener(new PhotoViewContainerScrollView.a(this) {
                final /* synthetic */ ShowBigPicture a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    this.a.q.setIsTop(z);
                    this.a.ab.setPanEnabled(z);
                    this.a.ab.setZoomEnabled(z);
                }

                public void b(boolean z) {
                }
            });
            this.q.setAllowParentInterceptOnEdge(false);
            this.q.setOnPhotoTapListener(this.n);
            this.z = (Button) findViewById(R.id.showbig_down_btn);
            this.A = (RelativeLayout) findViewById(R.id.showbig_title_layout);
            this.B = (LinearLayout) findViewById(R.id.left_layout);
            this.D = (Button) findViewById(R.id.title_left_btn);
            this.G = (TextView) findViewById(R.id.title_center_txt);
            this.G.setText(getString(R.string.seeimg));
            this.G.setVisibility(0);
            this.E = (TextView) findViewById(R.id.title_right_btn);
            this.C = (RelativeLayout) findViewById(R.id.title_refresh_layout);
            this.F = (Button) findViewById(R.id.refresh_btn);
            this.F.setVisibility(8);
            this.H = (Button) findViewById(R.id.showbig_bottom_title_share);
            this.E.setVisibility(0);
            this.E.setText(this.b.getResources().getString(R.string.forward));
            this.I = (Button) findViewById(R.id.showbig_bottom_title_comment);
            this.J = (ImageView) findViewById(R.id.back_button);
            this.J.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ShowBigPicture a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    EventBus.getDefault().post(DetailAction.FINISH_PAGE);
                    this.a.b.finish();
                }
            });
            Intent intent = getIntent();
            this.m = getSharedPreferences("weiboprefer", 0);
            Rect rect = (Rect) intent.getParcelableExtra("rect");
            this.c = intent.getStringExtra("imgPath");
            this.c = com.lt.a.a(this.b).a(this.c);
            this.w = intent.getStringArrayExtra("download_uri");
            this.V = (ListItemObject) intent.getSerializableExtra("listItemObject");
            this.W = (ArrayList) intent.getSerializableExtra("GifShowUrls");
            if ((this.V != null && TextUtils.isEmpty(this.V.getName())) || this.V == null) {
                this.H.setVisibility(8);
                this.I.setVisibility(8);
            }
            this.e = intent.getStringExtra("isgif");
            this.f = intent.getBooleanExtra("isLocal", false);
            if (this.c != null && this.c.endsWith(".gif")) {
                this.e = "1";
            }
            if (Scheme.ofUri(this.c) == Scheme.FILE) {
                this.c = Scheme.FILE.crop(this.c);
                this.f = true;
                if (this.c.endsWith(".gif")) {
                    this.e = "1";
                }
            }
            this.g = intent.getIntExtra(IndexEntry.COLUMN_NAME_WIDTH, 0);
            this.h = intent.getIntExtra(IndexEntry.COLUMN_NAME_HEIGHT, 0);
            this.k = PostsActivity.k;
            if (this.k == 0) {
                this.k = an.d(this.b);
            }
            if (this.g == 0 && this.V != null) {
                this.g = this.V.getWidth();
            }
            if (this.h == 0 && this.V != null) {
                this.h = this.V.getHeight();
            }
            if ((((double) this.h) * 1.0d) / ((double) this.g) > (((double) this.j) * 1.0d) / ((double) this.i)) {
                this.q.setScaleType(ScaleType.CENTER_CROP);
            }
            this.q.setMaxScale(10.0f);
            this.y.setOnClickListener(this.b);
            this.z.setOnClickListener(this.b);
            this.I.setOnClickListener(this.b);
            this.D.setOnClickListener(this.b);
            this.E.setOnClickListener(this.b);
            this.r = new Dialog(this, R.style.dialogTheme);
            this.r.setContentView(R.layout.loaddialog_mp);
            this.s = new Dialog(this, R.style.dialogTheme);
            this.s.setContentView(R.layout.loaddialog);
            this.L = new n(this);
            this.M = new m(this);
            this.O = new d(this);
            this.P = new b(this);
            this.l = new com.budejie.www.util.f();
            this.S = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
            this.R = com.budejie.www.http.b.a(this, this);
            this.U = new com.elves.update.a(this);
            l();
            this.d = "";
            if (!TextUtils.isEmpty(this.c)) {
                this.d = this.c.substring(7).replace("/", "-");
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void e() {
        try {
            this.K = this.m.getString("id", "");
            this.N = this.L.a(this.K);
            final Bundle bundle = new Bundle();
            bundle.putString(HistoryOpenHelper.COLUMN_UID, this.K);
            bundle.putSerializable("weiboMap", this.N);
            bundle.putSerializable("data", this.V);
            this.H.setTag(this.V);
            this.H.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ShowBigPicture b;

                public void onClick(View view) {
                    this.b.S.a(5, bundle, this.b.aj, this.b.T, this.b.M, this.b.L, this.b.U, this.b.m, this.b.aj).onClick(this.b.H);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        EventBus.getDefault().post(DetailAction.FINISH_PAGE);
        super.onBackPressed();
    }

    private void f() {
        this.A.setVisibility(8);
        if ("1".equals(this.e)) {
            this.q.setVisibility(8);
            this.ab.setVisibility(8);
            c();
            return;
        }
        this.ab.setVisibility(0);
        this.q.setVisibility(8);
        g();
    }

    private void g() {
        File file;
        if (this.f) {
            file = new File(this.c);
        } else {
            file = ImageUtil.getImageFile(this.c);
        }
        if (file == null || !file.exists()) {
            Drawable a = this.l.a(this.b, this.c, new com.budejie.www.util.f.b(this) {
                final /* synthetic */ ShowBigPicture a;

                {
                    this.a = r1;
                }

                public void a(Drawable drawable, String str) {
                    if (this.a.ab == null || drawable == null) {
                        this.a.aj.sendEmptyMessage(1);
                        this.a.u.setVisibility(8);
                        this.a.t.setProgress(0);
                        return;
                    }
                    this.a.d = str.substring(7).replace("/", "-");
                    this.a.ab.setImage(com.davemorrissey.labs.subscaleview.a.b(this.a.b.getCacheDir() + "/" + this.a.d));
                    if (this.a.g == 0 || this.a.g <= this.a.i) {
                        this.a.ab.a(0.0f, new PointF(0.0f, 0.0f));
                    } else {
                        this.a.ab.a((float) (this.a.i / this.a.g), new PointF(0.0f, 0.0f));
                    }
                    this.a.u.setVisibility(8);
                    this.a.t.setProgress(0);
                }

                public void a(int i, String str) {
                    this.a.t.setProgress(i);
                }

                public void b(int i, String str) {
                    this.a.t.setMax(i);
                }
            });
            if (a != null) {
                if ((a instanceof BitmapDrawable) && ((BitmapDrawable) a).getBitmap() != null) {
                    ((BitmapDrawable) a).getBitmap().recycle();
                }
                this.u.setVisibility(8);
                this.ab.setImage(com.davemorrissey.labs.subscaleview.a.b(file.getPath()));
                if (this.g == 0 || this.g <= this.i) {
                    this.ab.a(0.0f, new PointF(0.0f, 0.0f));
                    return;
                } else {
                    this.ab.a((float) (this.i / this.g), new PointF(0.0f, 0.0f));
                    return;
                }
            }
            this.u.setVisibility(0);
            return;
        }
        aa.a("ShowBigPicture", "获取列表的缓存图片");
        this.ab.setImage(com.davemorrissey.labs.subscaleview.a.b(file.getPath()));
        if (this.g == 0 || this.g <= this.i) {
            this.ab.a(0.0f, new PointF(0.0f, 0.0f));
        } else {
            this.ab.a((float) (this.i / this.g), new PointF(0.0f, 0.0f));
        }
        this.u.setVisibility(8);
        this.t.setProgress(0);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.show_picture_excessive, R.anim.show_picture_exit);
    }

    public void b() {
        if (this.V != null) {
            com.nostra13.universalimageloader.core.d.a().a(com.lt.a.a(this.b).a(this.V.getImgUrl()), this.q, com.budejie.www.e.d.a(new com.nostra13.universalimageloader.core.b.d()), new com.nostra13.universalimageloader.core.d.b(this) {
                final /* synthetic */ ShowBigPicture a;

                {
                    this.a = r1;
                }

                public void onLoadingStarted(String str, View view) {
                    this.a.u.setVisibility(0);
                }

                public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
                    this.a.q.setVisibility(0);
                    this.a.u.setVisibility(8);
                }

                public void onLoadingFailed(String str, View view, FailReason failReason) {
                    super.onLoadingFailed(str, view, failReason);
                    Toast.makeText(this.a.b, R.string.load_picture_failed, 0).show();
                }
            }, new com.nostra13.universalimageloader.core.d.d(this) {
                final /* synthetic */ ShowBigPicture a;

                {
                    this.a = r1;
                }

                public void onProgressUpdate(String str, View view, int i, int i2) {
                    this.a.t.setMax(i2);
                    this.a.t.setProgress(i);
                }
            });
        } else if (this.W != null && this.W.size() > 0) {
            com.nostra13.universalimageloader.core.d.a().a(com.lt.a.a(this.b).a((String) this.W.remove(0)), this.q, this.ag, this.ai, this.ah);
        } else if (this.c != null && this.c.endsWith(".gif")) {
            com.nostra13.universalimageloader.core.d.a().a(this.c, this.q, this.ag, this.ai, this.ah);
        }
    }

    public void c() {
        if (this.V != null) {
            b();
        } else if (this.W != null && this.W.size() > 0) {
            b();
        } else if (this.c != null && this.c.length() > 0 && this.c.endsWith(".gif")) {
            b();
        }
    }

    private void h() {
        int i = 0;
        if (this.aa) {
            Toast.makeText(this, "正在保存中", 0).show();
            return;
        }
        this.aa = true;
        if (this.w == null) {
            BudejieApplication.a.a((Context) this, this.c, (a) this, 100);
            return;
        }
        this.x = new ArrayList();
        String[] strArr = this.w;
        int length = strArr.length;
        while (i < length) {
            CharSequence charSequence = strArr[i];
            if (!TextUtils.isEmpty(charSequence)) {
                this.x.add(charSequence);
            }
            i++;
        }
        this.x.add(this.c);
        i();
    }

    private boolean i() {
        if (this.x == null || this.x.size() == 0) {
            return false;
        }
        String str = (String) this.x.remove(0);
        if (TextUtils.isEmpty(str)) {
            try {
                this.aj.sendMessage(this.aj.obtainMessage(3, this.b.getString(R.string.save_failed)));
            } catch (Exception e) {
                this.aj.sendMessage(this.aj.obtainMessage(3, this.b.getString(R.string.save_failed)));
            }
            return false;
        }
        this.d = str.substring(7).replace("/", "-");
        this.Z = this.Y + this.d;
        BudejieApplication.a.a((Context) this, str, (a) this, 100);
        return true;
    }

    @Deprecated
    private void j() {
        try {
            this.d = "test" + System.currentTimeMillis() + ".jpg";
            if (!TextUtils.isEmpty(this.c) && this.c.length() > 7) {
                this.d = this.c.substring(7).replace("/", "-");
            }
            if (this.f) {
                this.d = this.c.substring(this.c.lastIndexOf("/")).replace("-", "");
                if (!this.d.endsWith(".gif")) {
                    this.d += ".jpg";
                }
            }
            this.Z = this.Y + this.d;
            if (new File(this.Z).exists()) {
                aa.a("ShowBigPicture", "下载过图片，不用再次下载：" + this.d);
                this.X = this.b.getString(R.string.save_successed);
            } else if (!TextUtils.isEmpty(this.d) && this.d.endsWith(".gif")) {
                r0 = an.a(this.b, this.c);
                if (this.f) {
                    r0 = new File(this.c);
                }
                if (r0 == null || !r0.exists()) {
                    r0 = ImageUtil.getImageFile(this.c);
                    if (r0 != null && r0.exists()) {
                        aa.a("ShowBigPicture", "获取列表的缓存图片：" + r0.getPath());
                        an.a(r0.getPath(), this.Z);
                        this.X = this.b.getString(R.string.save_successed);
                    }
                } else {
                    aa.a("ShowBigPicture", "播放过gif，不用再次下载：" + this.d);
                    an.a(r0.getPath(), this.Z);
                    this.X = this.b.getString(R.string.save_successed);
                }
            } else if (!(TextUtils.isEmpty(this.d) || this.d.endsWith(".gif"))) {
                r0 = ImageUtil.getImageFile(this.c);
                if (this.f) {
                    r0 = new File(this.c);
                }
                if (r0 != null && r0.exists()) {
                    aa.a("ShowBigPicture", "获取列表的缓存图片：" + r0.getPath());
                    an.a(r0.getPath(), this.Z);
                    this.X = this.b.getString(R.string.save_successed);
                }
            }
            if (TextUtils.isEmpty(this.X)) {
                aa.a("ShowBigPicture", "下载路径：" + this.c);
                BudejieApplication.a.a((Context) this, this.c, (a) this, 100);
                return;
            }
            aa.a("ShowBigPicture", "message:" + this.X);
            this.aj.sendMessage(this.aj.obtainMessage(0, this.X));
        } catch (Exception e) {
            this.aj.sendMessage(this.aj.obtainMessage(0, this.b.getString(R.string.save_failed)));
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.showbig_down_btn) {
            if (!an.i(UpdateConfig.f)) {
                p.a((Context) this);
            } else if ((an.a((Context) this) || this.f) && "1".equals(this.e)) {
                if (this.V != null) {
                    r0 = new HashMap();
                    r0.put("type", "gif图片");
                    r0.put("title", this.V.getContent());
                    r0.put("label", this.V.getPlateNames());
                    an.a(this.b, r0, "E01_A05");
                }
                j();
            } else if (an.a((Context) this)) {
                if (this.V != null) {
                    r0 = new HashMap();
                    r0.put("type", "静态图片");
                    r0.put("title", this.V.getContent());
                    r0.put("label", this.V.getPlateNames());
                    an.a(this.b, r0, "E01_A05");
                }
                this.r.show();
                h();
                MobclickAgent.onEvent((Context) this, "E05-A07", "图片保存");
            } else {
                this.v = an.a((Activity) this, getString(R.string.nonet), -1);
                this.v.show();
            }
        } else if (id == R.id.showbig_bottom_title_comment && this.V != null && this.S != null) {
            this.K = this.m.getString("id", "");
            if (TextUtils.isEmpty(this.K)) {
                an.a(this.b, 0, null, null, 0);
            } else {
                com.budejie.www.util.a.a(this.b, this.V);
            }
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.v != null) {
            this.v.cancel();
        }
    }

    protected void onDestroy() {
        if (this.r != null && this.r.isShowing()) {
            this.r.dismiss();
        }
        super.onDestroy();
    }

    public void onrefreshTheme() {
        this.A.setBackgroundResource(com.budejie.www.util.j.a);
        this.G.setTextColor(getResources().getColor(com.budejie.www.util.j.b));
        onRefreshTitleFontTheme(this.D, true);
        onRefreshTitleFontTheme(this.E, false);
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        super.onComplete(jSONObject);
        HashMap a = z.a(jSONObject);
        if (a != null && a.size() != 0) {
            this.s.show();
            this.m.edit().putString("openid", (String) a.get("qzone_uid")).putString("qzone_token", (String) a.get("qzone_token")).putString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN, (System.currentTimeMillis() + (Long.parseLong((String) a.get(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN)) * 1000)) + "").commit();
            this.K = this.m.getString("id", "");
            this.L.a((String) a.get("qzone_uid"), this.K, (String) a.get("qzone_token"), 929, this.aj);
        }
    }

    public void onError(UiError uiError) {
        super.onError(uiError);
        Toast.makeText(this.b, "code:" + uiError.errorCode + ", msg:" + uiError.errorMessage + ", detail:" + uiError.errorDetail, 0).show();
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        try {
            this.K = this.m.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this.b, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.L.a(mAccessToken, this.K, 812, this.aj);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 711) {
            this.s.show();
            bindTencent();
        }
    }

    private boolean k() {
        ArrayList a = this.O.a();
        if (a == null || a.isEmpty()) {
            return false;
        }
        return true;
    }

    private void l() {
        this.Q = new Dialog(this.b, R.style.dialogTheme);
        View inflate = this.b.getLayoutInflater().inflate(R.layout.mycomment_delete_dialog, null);
        ((TextView) inflate.findViewById(R.id.mycomment_delete_text)).setText(getString(R.string.mycollect_sync_text));
        Button button = (Button) inflate.findViewById(R.id.mycomment_delete_sureBtn);
        ((Button) inflate.findViewById(R.id.mycomment_delete_cancelBtn)).setOnClickListener(this.ak);
        button.setOnClickListener(this.ak);
        this.Q.setContentView(inflate);
        Window window = this.Q.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = an.a(this.b, 300);
        window.setAttributes(attributes);
    }

    public void a(int i, String str) {
        if (i == 100) {
            String str2 = "";
            try {
                Object string;
                if (Constants.SERVICE_SCOPE_FLAG_VALUE.equals(str)) {
                    string = this.b.getString(R.string.save_successed);
                } else {
                    string = this.b.getString(R.string.save_failed);
                }
                this.aj.sendMessage(this.aj.obtainMessage(0, string));
            } catch (Exception e) {
                this.aj.sendMessage(this.aj.obtainMessage(0, this.b.getString(R.string.save_failed)));
            }
        }
    }

    public void a(int i) {
        if (i == 100) {
            try {
                this.aj.sendMessage(this.aj.obtainMessage(3, this.b.getString(R.string.save_failed)));
            } catch (Exception e) {
                this.aj.sendMessage(this.aj.obtainMessage(3, this.b.getString(R.string.save_failed)));
            }
        }
    }

    public void bindTencent() {
        this.K = this.m.getString("id", "");
        String sharePersistent = Util.getSharePersistent(this.b, "ACCESS_TOKEN");
        this.L.a(Util.getSharePersistent(this.b, "NAME"), sharePersistent, Util.getSharePersistent(this.b, "OPEN_ID"), this.K, 813, this.aj);
    }

    private void m() {
        aa.a("ShowBigPicture", "startScan");
        try {
            Uri fromFile = Uri.fromFile(new File(this.Z));
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(fromFile);
            sendBroadcast(intent);
        } catch (Exception e) {
            if (this.al != null) {
                this.al.disconnect();
            }
            this.al = new MediaScannerConnection(this, this);
            this.al.connect();
        }
    }

    public void onMediaScannerConnected() {
        aa.a("ShowBigPicture", "onMediaScannerConnected:" + this.Z);
        try {
            this.al.scanFile(this.Z, "image/*");
        } catch (IllegalStateException e) {
        }
    }

    public void onScanCompleted(String str, Uri uri) {
        aa.a("ShowBigPicture", "onScanCompleted");
        Log.e("AAA", "onScanCompleted:" + str + "uri" + uri);
        this.al.disconnect();
    }

    private void n() {
    }
}
