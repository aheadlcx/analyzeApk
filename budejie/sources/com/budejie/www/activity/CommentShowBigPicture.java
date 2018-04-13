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
import android.provider.MediaStore.Images.Media;
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
import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.alipay.sdk.util.j;
import com.androidex.util.ImageUtil;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.posts.PostsActivity;
import com.budejie.www.ad.AdConfig;
import com.budejie.www.ad.AdManager;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.c.b;
import com.budejie.www.c.d;
import com.budejie.www.c.m;
import com.budejie.www.f.a;
import com.budejie.www.http.f;
import com.budejie.www.http.n;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.au;
import com.budejie.www.util.p;
import com.budejie.www.util.w;
import com.budejie.www.util.z;
import com.budejie.www.widget.PhotoViewContainerScrollView;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.c;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.exception.ErrorCode;
import com.sprite.ads.nati.NativeAd;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.NativeAdRef;
import com.sprite.ads.nati.NativeSingleAdListener;
import com.sprite.ads.nati.view.NativeAdView;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.WebView;
import com.tencent.tauth.UiError;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UpdateConfig;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import org.json.JSONObject;
import pl.droidsonroids.gif.GifDrawable;

public class CommentShowBigPicture extends OauthWeiboBaseAct implements MediaScannerConnectionClient, OnClickListener, a {
    private RelativeLayout A;
    private Button B;
    private RelativeLayout C;
    private LinearLayout D;
    private RelativeLayout E;
    private Button F;
    private TextView G;
    private Button H;
    private TextView I;
    private Button J;
    private Button K;
    private ImageView L;
    private String M;
    private n N;
    private m O;
    private HashMap<String, String> P;
    private d Q;
    private b R;
    private Dialog S;
    private com.budejie.www.http.b T = null;
    private com.budejie.www.g.b U;
    private IWXAPI V;
    private com.elves.update.a W;
    private ListItemObject X;
    private ArrayList<String> Y;
    private Stack<SoftReference<WebView>> Z = new Stack();
    String a = "ShowBigPicture";
    private List<String> aa = new ArrayList();
    private f ab;
    private String ac = "";
    private String ad = (Environment.getExternalStorageDirectory().toString() + "/budejie/");
    private String ae = "";
    private boolean af;
    private SubsamplingScaleImageView ag;
    private boolean ah = false;
    private NativeAdData ai = null;
    private SubsamplingScaleImageView.f aj = new SubsamplingScaleImageView.d(this) {
        final /* synthetic */ CommentShowBigPicture a;

        {
            this.a = r1;
        }

        public void a() {
            aa.b("SubsamplingScaleImageView", "onImageLoaded");
            this.a.ah = true;
            this.a.e();
        }
    };
    private c ak = com.budejie.www.e.d.a(new com.nostra13.universalimageloader.core.b.d());
    private com.nostra13.universalimageloader.core.d.d al = new com.nostra13.universalimageloader.core.d.d(this) {
        final /* synthetic */ CommentShowBigPicture a;

        {
            this.a = r1;
        }

        public void onProgressUpdate(String str, View view, int i, int i2) {
            this.a.v.setMax(i2);
            this.a.v.setProgress(i);
        }
    };
    private com.nostra13.universalimageloader.core.d.b am = new com.nostra13.universalimageloader.core.d.b(this) {
        final /* synthetic */ CommentShowBigPicture a;

        {
            this.a = r1;
        }

        public void onLoadingStarted(String str, View view) {
            this.a.w.setVisibility(0);
        }

        public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
            this.a.q.setVisibility(0);
            this.a.w.setVisibility(8);
            this.a.r.setVisibility(0);
        }

        public void onLoadingFailed(String str, View view, FailReason failReason) {
            super.onLoadingFailed(str, view, failReason);
            if (this.a.Y.size() > 0) {
                com.nostra13.universalimageloader.core.d.a().a(com.lt.a.a(this.a.b).a((String) this.a.Y.remove(0)), this.a.q, this.a.ak, this.a.am, this.a.al);
                return;
            }
            Toast.makeText(this.a.b, R.string.load_picture_failed, 0).show();
        }
    };
    private Handler an = new Handler(this) {
        final /* synthetic */ CommentShowBigPicture a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            boolean z = false;
            int i = message.what;
            if (i == 1) {
                Toast.makeText(this.a.b, R.string.load_picture_failed, z).show();
            } else if (i == 814) {
                this.a.t.show();
            } else if (i == 815) {
                this.a.t.cancel();
            } else if (i == 0) {
                this.a.af = z;
                r0 = (String) message.obj;
                this.a.t.dismiss();
                r1 = new Builder(this.a);
                r1.setTitle(R.string.system_tip);
                r1.setMessage(r0);
                r1.setPositiveButton(R.string.sure, null);
                if (!this.a.b.isFinishing()) {
                    r1.create().show();
                }
                this.a.n();
            } else if (i == 3) {
                if (!this.a.j()) {
                    this.a.af = z;
                    r0 = (String) message.obj;
                    this.a.t.dismiss();
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
                    this.a.T.a("add", ai.b(this.a.b), (String) message.obj, 971);
                }
            } else if (i == 12) {
                an.a(this.a.b, (int) R.string.collect_fail, (int) R.drawable.collect_tip).show();
            } else if (i == 100001) {
                an.a(this.a.b, this.a.b.getString(R.string.forwardAndCollect_succeed), -1).show();
                if (!TextUtils.isEmpty(ai.b(this.a.b))) {
                    this.a.T.a("add", ai.b(this.a.b), (String) message.obj, 971);
                }
            } else if (i == 829) {
                r0 = (String) message.obj;
                this.a.R.a("collectTable", r0);
                an.a(this.a.b, this.a.b.getString(R.string.delete_success), -1).show();
                this.a.T.a("delete", ai.b(this.a.b), r0, 971);
            } else if (i == 812) {
                r0 = (String) message.obj;
                if (TextUtils.isEmpty(r0)) {
                    this.a.x = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                    this.a.x.show();
                    MobclickAgent.onEvent(this.a.b, "weibo_bind", "sina_faild");
                } else {
                    try {
                        r1 = Integer.parseInt(r0);
                    } catch (NumberFormatException e) {
                    }
                    if (r1 < 0) {
                        this.a.x = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                        this.a.x.show();
                        MobclickAgent.onEvent(this.a.b, "weibo_bind", "sina_faild");
                    } else {
                        r2 = z.c(r0);
                        if (r2 == null || r2.isEmpty()) {
                            this.a.x = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                            this.a.x.show();
                            MobclickAgent.onEvent(this.a.b, "weibo_bind", "sina_faild");
                        } else {
                            r1 = (String) r2.get("result_msg");
                            if ("0".equals((String) r2.get(j.c))) {
                                MobclickAgent.onEvent(this.a.b, "weibo_bind", "sina_success");
                                this.a.M = (String) r2.get("id");
                                this.a.O.a(this.a.M, r2);
                                ai.a(this.a.b, this.a.M, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                if (OauthWeiboBaseAct.mAccessToken != null) {
                                    this.a.O.a(this.a.M, OauthWeiboBaseAct.mAccessToken.e());
                                }
                                this.a.P = this.a.N.a(this.a.M);
                                this.a.x = an.a(this.a.b, this.a.b.getString(R.string.bind_successed), -1);
                                this.a.x.show();
                                this.a.N.a(this.a.b, this.a.X, "sina", this.a.M, this.a.P, this.a.W, (Handler) this);
                                if (this.a.l()) {
                                    this.a.S.show();
                                }
                            } else {
                                an.a(this.a.b, r1, -1).show();
                            }
                        }
                    }
                }
                this.a.u.cancel();
            } else if (i == 929) {
                r0 = (String) message.obj;
                if (TextUtils.isEmpty(r0)) {
                    this.a.x = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                    this.a.x.show();
                    MobclickAgent.onEvent(this.a.b, "weibo_bind", "qzone_faild");
                } else {
                    try {
                        r1 = Integer.parseInt(r0);
                    } catch (NumberFormatException e2) {
                    }
                    if (r1 < 0) {
                        this.a.x = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                        this.a.x.show();
                        MobclickAgent.onEvent(this.a.b, "weibo_bind", "qzone_faild");
                    } else {
                        r2 = z.c(r0);
                        if (r2 == null || r2.isEmpty()) {
                            this.a.x = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                            this.a.x.show();
                            MobclickAgent.onEvent(this.a.b, "weibo_bind", "qzone_faild");
                        } else {
                            r1 = (String) r2.get("result_msg");
                            if ("0".equals((String) r2.get(j.c))) {
                                MobclickAgent.onEvent(this.a.b, "weibo_bind", "qzone_success");
                                this.a.M = (String) r2.get("id");
                                this.a.O.a(this.a.M, r2);
                                ai.a(this.a.b, this.a.M, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.P = this.a.N.a(this.a.M);
                                this.a.x = an.a(this.a.b, this.a.b.getString(R.string.bind_successed), -1);
                                this.a.x.show();
                                this.a.N.a(this.a.b, this.a.X, com.tencent.connect.common.Constants.SOURCE_QZONE, this.a.M, this.a.P, this.a.W, (Handler) this);
                                if (this.a.l()) {
                                    this.a.S.show();
                                }
                            } else {
                                an.a(this.a.b, r1, -1).show();
                            }
                        }
                    }
                }
                this.a.u.cancel();
            } else if (i == 813) {
                r0 = (String) message.obj;
                if (TextUtils.isEmpty(r0)) {
                    this.a.x = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                    this.a.x.show();
                    MobclickAgent.onEvent(this.a.b, "weibo_bind", "tencent_faild");
                } else {
                    try {
                        r1 = Integer.parseInt(r0);
                    } catch (NumberFormatException e3) {
                    }
                    if (r1 < 0) {
                        this.a.x = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                        this.a.x.show();
                        MobclickAgent.onEvent(this.a.b, "weibo_bind", "tencent_faild");
                    } else {
                        r2 = z.c(r0);
                        if (r2 == null || r2.isEmpty()) {
                            this.a.x = an.a(this.a.b, this.a.b.getString(R.string.bind_failed), -1);
                            this.a.x.show();
                            MobclickAgent.onEvent(this.a.b, "weibo_bind", "tencent_faild");
                        } else {
                            r1 = (String) r2.get("result_msg");
                            if ("0".equals((String) r2.get(j.c))) {
                                MobclickAgent.onEvent(this.a.b, "weibo_bind", "tencent_success");
                                this.a.M = (String) r2.get("id");
                                this.a.O.a(this.a.M, r2);
                                ai.a(this.a.b, this.a.M, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                this.a.P = this.a.N.a(this.a.M);
                                this.a.x = an.a(this.a.b, this.a.b.getString(R.string.bind_successed), -1);
                                this.a.x.show();
                                this.a.N.a(this.a.b, this.a.X, "qq", this.a.M, this.a.P, this.a.W, (Handler) this);
                                if (this.a.l()) {
                                    this.a.S.show();
                                }
                            } else {
                                an.a(this.a.b, r1, -1).show();
                            }
                        }
                    }
                }
                this.a.u.cancel();
            } else if (i == 816) {
                Bundle bundle = (Bundle) message.obj;
                CharSequence string = bundle.getString(j.c);
                i = bundle.getInt("notificationId");
                if (TextUtils.isEmpty(string)) {
                    this.a.W.a(i, z, (int) R.string.forwarfail);
                } else if ("0".equals(string)) {
                    this.a.W.a(i, true, (int) R.string.forwardsuccess);
                } else {
                    this.a.W.a(i, z, (int) R.string.forwarfail);
                }
                new Thread(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void run() {
                        try {
                            Thread.sleep(1000);
                            this.b.a.an.sendMessage(this.b.a.an.obtainMessage(817, Integer.valueOf(i)));
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
            } else if (i == 817) {
                this.a.W.a(((Integer) message.obj).intValue());
            }
        }
    };
    private OnClickListener ao = new OnClickListener(this) {
        final /* synthetic */ CommentShowBigPicture a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.mycomment_delete_cancelBtn) {
                this.a.S.dismiss();
            } else if (view.getId() == R.id.mycomment_delete_sureBtn) {
                this.a.S.dismiss();
                ArrayList a = this.a.Q.a();
                if (a != null && !a.isEmpty()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < a.size(); i++) {
                        if (i == a.size() - 1) {
                            stringBuffer.append((String) a.get(i));
                        } else {
                            stringBuffer.append((String) a.get(i)).append(",");
                        }
                    }
                    this.a.T.a("add", this.a.M, stringBuffer.toString(), 971);
                }
            }
        }
    };
    private MediaScannerConnection ap;
    CommentShowBigPicture b;
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
        final /* synthetic */ CommentShowBigPicture a;

        {
            this.a = r1;
        }

        public void a(View view, float f, float f2) {
            this.a.b.finish();
        }
    };
    OnClickListener o = new OnClickListener(this) {
        final /* synthetic */ CommentShowBigPicture a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.b.finish();
        }
    };
    private PhotoViewContainerScrollView p;
    private PhotoView q;
    private NativeAdView r;
    private View s;
    private Dialog t;
    private Dialog u;
    private ProgressBar v;
    private LinearLayout w;
    private Toast x;
    private String[] y;
    private List<String> z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WindowManager windowManager = (WindowManager) getSystemService("window");
        this.i = windowManager.getDefaultDisplay().getWidth();
        this.j = windowManager.getDefaultDisplay().getHeight();
        setContentView(R.layout.show_comment_big_img);
        this.b = this;
        this.ab = new f(this.b);
        a();
        g();
        overridePendingTransition(R.anim.show_picture_enter, R.anim.show_picture_excessive);
        if (an.a((Context) this)) {
            d();
        } else if (!this.f) {
            this.x = an.a((Activity) this, getString(R.string.nonet), -1);
            this.x.show();
        }
        f();
    }

    protected void onResume() {
        super.onResume();
        this.U = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
    }

    private void d() {
        this.V = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.V.registerApp("wx592fdc48acfbe290");
    }

    @TargetApi(11)
    public void a() {
        try {
            an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
            this.A = (RelativeLayout) findViewById(R.id.showbigimg_layout);
            this.w = (LinearLayout) findViewById(R.id.progress_layout);
            this.v = (ProgressBar) findViewById(R.id.gif_progress);
            this.q = (PhotoView) findViewById(R.id.main_img);
            this.ag = (SubsamplingScaleImageView) findViewById(R.id.subsamplingScaleImageView);
            this.ag.setOnClickListener(this.o);
            this.ag.setOnImageEventListener(this.aj);
            this.ag.setMaxScale(20.0f);
            this.ag.setDoubleTapZoomScale(4.0f);
            this.ag.setDoubleTapZoomStyle(2);
            this.ag.setMinimumScaleType(2);
            LayoutParams layoutParams = this.q.getLayoutParams();
            layoutParams.height = (com.bdj.picture.edit.util.a.b(this.b) - an.t(this.b)) - 10;
            this.q.setLayoutParams(layoutParams);
            this.ag.setLayoutParams(layoutParams);
            this.p = (PhotoViewContainerScrollView) findViewById(R.id.container_scroll_view);
            this.p.setScrollBottomListener(new PhotoViewContainerScrollView.a(this) {
                final /* synthetic */ CommentShowBigPicture a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    this.a.q.setIsTop(z);
                    this.a.ag.setPanEnabled(z);
                    this.a.ag.setZoomEnabled(z);
                }

                public void b(boolean z) {
                }
            });
            this.r = (NativeAdView) findViewById(R.id.big_picture_ad_view);
            a(this.r);
            this.q.setAllowParentInterceptOnEdge(false);
            this.q.setOnPhotoTapListener(this.n);
            this.s = findViewById(R.id.blank_img_layout);
            this.B = (Button) findViewById(R.id.showbig_down_btn);
            this.C = (RelativeLayout) findViewById(R.id.showbig_title_layout);
            this.D = (LinearLayout) findViewById(R.id.left_layout);
            this.F = (Button) findViewById(R.id.title_left_btn);
            this.I = (TextView) findViewById(R.id.title_center_txt);
            this.I.setText(getString(R.string.seeimg));
            this.I.setVisibility(0);
            this.G = (TextView) findViewById(R.id.title_right_btn);
            this.E = (RelativeLayout) findViewById(R.id.title_refresh_layout);
            this.H = (Button) findViewById(R.id.refresh_btn);
            this.H.setVisibility(8);
            this.J = (Button) findViewById(R.id.showbig_bottom_title_share);
            this.G.setVisibility(0);
            this.G.setText(this.b.getResources().getString(R.string.forward));
            this.K = (Button) findViewById(R.id.showbig_bottom_title_comment);
            this.L = (ImageView) findViewById(R.id.back_button);
            com.bdj.picture.edit.util.d.b(this.b, 30.0f);
            this.L.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ CommentShowBigPicture a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.b.finish();
                }
            });
            Intent intent = getIntent();
            this.m = getSharedPreferences("weiboprefer", 0);
            Rect rect = (Rect) intent.getParcelableExtra("rect");
            this.c = intent.getStringExtra("imgPath");
            if (this.c != null) {
                this.c = com.lt.a.a(this.b).a(this.c);
            }
            this.y = intent.getStringArrayExtra("download_uri");
            this.X = (ListItemObject) intent.getSerializableExtra("listItemObject");
            this.Y = (ArrayList) intent.getSerializableExtra("GifShowUrls");
            if ((this.X == null || !TextUtils.isEmpty(this.X.getName())) && this.X != null) {
                int parseInt;
                try {
                    parseInt = Integer.parseInt(this.X.getRepost());
                } catch (NumberFormatException e) {
                    parseInt = 0;
                }
                if (parseInt > CommonInts.USER_MANAGER_REQUEST_CODE) {
                    this.J.setText("9999+");
                } else if (parseInt != 0) {
                    this.J.setText(String.valueOf(parseInt));
                } else {
                    this.J.setText(R.string.forward_share);
                }
                CharSequence comment = this.X.getComment();
                if ("0".equals(comment)) {
                    this.K.setText(R.string.commend);
                } else {
                    this.K.setText(comment);
                }
            } else {
                this.J.setVisibility(8);
                this.K.setVisibility(8);
                this.r.setVisibility(8);
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
            if (this.g == 0 && this.X != null) {
                this.g = this.X.getWidth();
            }
            if (this.h == 0 && this.X != null) {
                this.h = this.X.getHeight();
            }
            if ((((double) this.h) * 1.0d) / ((double) this.g) > (((double) this.j) * 1.0d) / ((double) this.i)) {
                this.q.setScaleType(ScaleType.CENTER_CROP);
            }
            this.q.setMaxScale(10.0f);
            this.A.setOnClickListener(this.b);
            this.B.setOnClickListener(this.b);
            this.K.setOnClickListener(this.b);
            this.F.setOnClickListener(this.b);
            this.G.setOnClickListener(this.b);
            this.t = new Dialog(this, R.style.dialogTheme);
            this.t.setContentView(R.layout.loaddialog_mp);
            this.u = new Dialog(this, R.style.dialogTheme);
            this.u.setContentView(R.layout.loaddialog);
            this.N = new n(this);
            this.O = new m(this);
            this.Q = new d(this);
            this.R = new b(this);
            this.l = new com.budejie.www.util.f();
            this.U = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
            this.T = com.budejie.www.http.b.a(this, this);
            this.W = new com.elves.update.a(this);
            m();
            this.d = "";
            if (!TextUtils.isEmpty(this.c)) {
                this.d = this.c.substring(7).replace("/", "-");
            }
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private void a(final NativeAdView nativeAdView) {
        if (!AdManager.isAdClosed()) {
            new NativeAd(this).loadAd(AdConfig.image, new NativeSingleAdListener(this) {
                final /* synthetic */ CommentShowBigPicture b;

                public void loadAd(NativeAdRef nativeAdRef) {
                    nativeAdView.loadAd(nativeAdRef, new CommentShowBigPicture$8$1(this));
                }

                public void loadFailure(ErrorCode errorCode) {
                }

                public void onADSkip(AdItem adItem) {
                    w.a(this.b.b, false).a(adItem.getUrl());
                }
            });
        }
    }

    private void e() {
        if (this.ai == null) {
            return;
        }
        if (this.ah || "1".equals(this.e)) {
            this.r.setVisibility(0);
            AsyncImageView asyncImageView = (AsyncImageView) this.r.findViewById(R.id.adImg);
            asyncImageView.setAdjustViewBounds(true);
            asyncImageView.setMaxWidth(com.bdj.picture.edit.util.a.a(this.b));
            View findViewById = this.r.findViewById(R.id.tub);
            asyncImageView.setPostImage(this.ai.getPic());
            findViewById.setVisibility(0);
        }
    }

    private void f() {
        try {
            this.M = this.m.getString("id", "");
            this.P = this.N.a(this.M);
            Bundle bundle = new Bundle();
            bundle.putString(HistoryOpenHelper.COLUMN_UID, this.M);
            bundle.putSerializable("weiboMap", this.P);
            bundle.putSerializable("data", this.X);
            this.J.setTag(this.X);
            this.J.setOnClickListener(this.U.a(5, bundle, this.an, this.V, this.O, this.N, this.W, this.m, this.an));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void g() {
        this.C.setVisibility(8);
        if ("1".equals(this.e)) {
            this.q.setVisibility(8);
            this.ag.setVisibility(8);
            this.r.setVisibility(8);
            c();
            return;
        }
        this.ag.setVisibility(0);
        this.q.setVisibility(8);
        h();
    }

    private void h() {
        File file;
        if (this.f) {
            file = new File(this.c);
        } else {
            file = ImageUtil.getImageFile(this.c);
        }
        if (file == null || !file.exists()) {
            Drawable a = this.l.a(this.b, this.c, new com.budejie.www.util.f.b(this) {
                final /* synthetic */ CommentShowBigPicture a;

                {
                    this.a = r1;
                }

                public void a(Drawable drawable, String str) {
                    if (this.a.ag == null || drawable == null) {
                        this.a.an.sendEmptyMessage(1);
                        this.a.w.setVisibility(8);
                        this.a.v.setProgress(0);
                        return;
                    }
                    this.a.d = str.substring(7).replace("/", "-");
                    this.a.ag.setImage(com.davemorrissey.labs.subscaleview.a.b(this.a.b.getCacheDir() + "/" + this.a.d));
                    if (this.a.g == 0 || this.a.g <= this.a.i) {
                        this.a.ag.a(0.0f, new PointF(0.0f, 0.0f));
                    } else {
                        this.a.ag.a((float) (this.a.i / this.a.g), new PointF(0.0f, 0.0f));
                    }
                    this.a.w.setVisibility(8);
                    this.a.v.setProgress(0);
                }

                public void a(int i, String str) {
                    this.a.v.setProgress(i);
                }

                public void b(int i, String str) {
                    this.a.v.setMax(i);
                }
            });
            if (a != null) {
                if ((a instanceof BitmapDrawable) && ((BitmapDrawable) a).getBitmap() != null) {
                    ((BitmapDrawable) a).getBitmap().recycle();
                }
                this.w.setVisibility(8);
                this.ag.setImage(com.davemorrissey.labs.subscaleview.a.b(file.getPath()));
                if (this.g == 0 || this.g <= this.i) {
                    this.ag.a(0.0f, new PointF(0.0f, 0.0f));
                    return;
                } else {
                    this.ag.a((float) (this.i / this.g), new PointF(0.0f, 0.0f));
                    return;
                }
            }
            this.w.setVisibility(0);
            return;
        }
        aa.a("CommentShowBigPicture", "获取列表的缓存图片");
        this.ag.setImage(com.davemorrissey.labs.subscaleview.a.b(file.getPath()));
        if (this.g == 0 || this.g <= this.i) {
            this.ag.a(0.0f, new PointF(0.0f, 0.0f));
        } else {
            this.ag.a((float) (this.i / this.g), new PointF(0.0f, 0.0f));
        }
        this.w.setVisibility(8);
        this.v.setProgress(0);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.show_picture_excessive, R.anim.show_picture_exit);
    }

    public void b() {
        if (this.X != null) {
            com.nostra13.universalimageloader.core.d.a().a(com.lt.a.a(this.b).a(this.X.getImgUrl()), this.q, com.budejie.www.e.d.a(new com.nostra13.universalimageloader.core.b.d()), new com.nostra13.universalimageloader.core.d.b(this) {
                final /* synthetic */ CommentShowBigPicture a;

                {
                    this.a = r1;
                }

                public void onLoadingStarted(String str, View view) {
                    this.a.w.setVisibility(0);
                }

                public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
                    this.a.q.setVisibility(0);
                    this.a.w.setVisibility(8);
                    this.a.r.setVisibility(0);
                }

                public void onLoadingFailed(String str, View view, FailReason failReason) {
                    super.onLoadingFailed(str, view, failReason);
                    Toast.makeText(this.a.b, R.string.load_picture_failed, 0).show();
                }
            }, new com.nostra13.universalimageloader.core.d.d(this) {
                final /* synthetic */ CommentShowBigPicture a;

                {
                    this.a = r1;
                }

                public void onProgressUpdate(String str, View view, int i, int i2) {
                    this.a.v.setMax(i2);
                    this.a.v.setProgress(i);
                }
            });
        } else if (this.Y != null && this.Y.size() > 0) {
            com.nostra13.universalimageloader.core.d.a().a(com.lt.a.a(this.b).a((String) this.Y.remove(0)), this.q, this.ak, this.am, this.al);
        } else if (this.c != null && this.c.endsWith(".gif")) {
            com.nostra13.universalimageloader.core.d.a().a(this.c, this.q, this.ak, this.am, this.al);
        }
    }

    public void c() {
        if (this.X != null) {
            b();
        } else if (this.Y != null && this.Y.size() > 0) {
            b();
        } else if (this.c != null && this.c.length() > 0 && this.c.endsWith(".gif")) {
            b();
        }
    }

    private void i() {
        int i = 0;
        if (this.af) {
            Toast.makeText(this, "正在保存中", 0).show();
            return;
        }
        this.af = true;
        if (this.y == null) {
            BudejieApplication.a.a((Context) this, this.c, (a) this, 100);
            return;
        }
        this.z = new ArrayList();
        String[] strArr = this.y;
        int length = strArr.length;
        while (i < length) {
            CharSequence charSequence = strArr[i];
            if (!TextUtils.isEmpty(charSequence)) {
                this.z.add(charSequence);
            }
            i++;
        }
        this.z.add(this.c);
        j();
    }

    private boolean j() {
        if (this.z == null || this.z.size() == 0) {
            return false;
        }
        String str = (String) this.z.remove(0);
        if (TextUtils.isEmpty(str)) {
            try {
                this.an.sendMessage(this.an.obtainMessage(3, this.b.getString(R.string.save_failed)));
            } catch (Exception e) {
                this.an.sendMessage(this.an.obtainMessage(3, this.b.getString(R.string.save_failed)));
            }
            return false;
        }
        this.d = str.substring(7).replace("/", "-");
        this.ae = this.ad + this.d;
        BudejieApplication.a.a((Context) this, str, (a) this, 100);
        return true;
    }

    @Deprecated
    private void k() {
        try {
            this.d = "test" + System.currentTimeMillis() + ".jpg";
            if (!TextUtils.isEmpty(this.c) && this.c.length() > 7) {
                this.d = this.c.substring(7).replace("/", "-");
            }
            String str = "";
            if (this.y != null && this.y.length > 0) {
                str = this.y[0];
                this.c = str;
            }
            if (!TextUtils.isEmpty(str) && str.length() > 7) {
                this.d = str.substring(7).replace("/", "-");
            }
            if (this.f) {
                this.d = this.c.substring(this.c.lastIndexOf("/")).replace("-", "");
                if (!this.d.endsWith(".gif")) {
                    this.d += ".jpg";
                }
            }
            this.ae = this.ad + this.d;
            if (new File(this.ae).exists()) {
                aa.a("CommentShowBigPicture", "下载过图片，不用再次下载：" + this.d);
                this.ac = this.b.getString(R.string.save_successed);
            } else if (!TextUtils.isEmpty(this.d) && this.d.endsWith(".gif")) {
                r0 = an.a(this.b, this.c);
                if (this.f) {
                    r0 = new File(this.c);
                }
                if (r0 == null || !r0.exists()) {
                    r0 = ImageUtil.getImageFile(this.c);
                    if (r0 != null && r0.exists()) {
                        aa.a("CommentShowBigPicture", "获取列表的缓存图片：" + r0.getPath());
                        an.a(r0.getPath(), this.ae);
                        this.ac = this.b.getString(R.string.save_successed);
                    }
                } else {
                    aa.a("CommentShowBigPicture", "播放过gif，不用再次下载：" + this.d);
                    an.a(r0.getPath(), this.ae);
                    this.ac = this.b.getString(R.string.save_successed);
                }
            } else if (!(TextUtils.isEmpty(this.d) || this.d.endsWith(".gif"))) {
                r0 = ImageUtil.getImageFile(this.c);
                if (this.f) {
                    r0 = new File(this.c);
                }
                if (r0 != null && r0.exists()) {
                    aa.a("ShowBigPicture", "获取列表的缓存图片：" + r0.getPath());
                    an.a(r0.getPath(), this.ae);
                    this.ac = this.b.getString(R.string.save_successed);
                }
            }
            if (TextUtils.isEmpty(this.ac)) {
                aa.a("CommentShowBigPicture", "下载路径：" + this.c);
                BudejieApplication.a.a((Context) this, this.c, (a) this, 100);
                return;
            }
            aa.a("CommentShowBigPicture", "message:" + this.ac);
            this.an.sendMessage(this.an.obtainMessage(0, this.ac));
        } catch (Exception e) {
            this.an.sendMessage(this.an.obtainMessage(0, this.b.getString(R.string.save_failed)));
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.showbig_down_btn) {
            if (!an.i(UpdateConfig.f)) {
                p.a((Context) this);
            } else if ((an.a((Context) this) || this.f) && "1".equals(this.e)) {
                if (this.X != null) {
                    r0 = new HashMap();
                    r0.put("type", "gif图片");
                    r0.put("title", this.X.getContent());
                    r0.put("label", this.X.getPlateNames());
                    an.a(this.b, r0, "E01_A05");
                }
                k();
            } else if (an.a((Context) this)) {
                if (this.X != null) {
                    r0 = new HashMap();
                    r0.put("type", "静态图片");
                    r0.put("title", this.X.getContent());
                    r0.put("label", this.X.getPlateNames());
                    an.a(this.b, r0, "E01_A05");
                }
                this.t.show();
                i();
                MobclickAgent.onEvent((Context) this, "E05-A07", "图片保存");
            } else {
                this.x = an.a((Activity) this, getString(R.string.nonet), -1);
                this.x.show();
            }
        } else if (id == R.id.showbig_bottom_title_comment && this.X != null && this.U != null) {
            view.setTag(this.X);
            this.U.a(3, null).onClick(view);
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.x != null) {
            this.x.cancel();
        }
    }

    protected void onDestroy() {
        if (this.t != null && this.t.isShowing()) {
            this.t.dismiss();
        }
        super.onDestroy();
    }

    public void onrefreshTheme() {
        this.C.setBackgroundResource(com.budejie.www.util.j.a);
        this.I.setTextColor(getResources().getColor(com.budejie.www.util.j.b));
        onRefreshTitleFontTheme(this.F, true);
        onRefreshTitleFontTheme(this.G, false);
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        super.onComplete(jSONObject);
        HashMap a = z.a(jSONObject);
        if (a != null && a.size() != 0) {
            this.u.show();
            this.m.edit().putString("openid", (String) a.get("qzone_uid")).putString("qzone_token", (String) a.get("qzone_token")).putString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN, (System.currentTimeMillis() + (Long.parseLong((String) a.get(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN)) * 1000)) + "").commit();
            this.M = this.m.getString("id", "");
            this.N.a((String) a.get("qzone_uid"), this.M, (String) a.get("qzone_token"), 929, this.an);
        }
    }

    public void onError(UiError uiError) {
        super.onError(uiError);
        Toast.makeText(this.b, "code:" + uiError.errorCode + ", msg:" + uiError.errorMessage + ", detail:" + uiError.errorDetail, 0).show();
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        try {
            this.M = this.m.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this.b, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.N.a(mAccessToken, this.M, 812, this.an);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 711) {
            this.u.show();
            bindTencent();
        }
    }

    private boolean l() {
        ArrayList a = this.Q.a();
        if (a == null || a.isEmpty()) {
            return false;
        }
        return true;
    }

    private void m() {
        this.S = new Dialog(this.b, R.style.dialogTheme);
        View inflate = this.b.getLayoutInflater().inflate(R.layout.mycomment_delete_dialog, null);
        ((TextView) inflate.findViewById(R.id.mycomment_delete_text)).setText(getString(R.string.mycollect_sync_text));
        Button button = (Button) inflate.findViewById(R.id.mycomment_delete_sureBtn);
        ((Button) inflate.findViewById(R.id.mycomment_delete_cancelBtn)).setOnClickListener(this.ao);
        button.setOnClickListener(this.ao);
        this.S.setContentView(inflate);
        Window window = this.S.getWindow();
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
                this.an.sendMessage(this.an.obtainMessage(0, string));
            } catch (Exception e) {
                this.an.sendMessage(this.an.obtainMessage(0, this.b.getString(R.string.save_failed)));
            }
        }
    }

    public void a(int i) {
        if (i == 100) {
            try {
                this.an.sendMessage(this.an.obtainMessage(3, this.b.getString(R.string.save_failed)));
            } catch (Exception e) {
                this.an.sendMessage(this.an.obtainMessage(3, this.b.getString(R.string.save_failed)));
            }
        }
    }

    public void bindTencent() {
        this.M = this.m.getString("id", "");
        String sharePersistent = Util.getSharePersistent(this.b, "ACCESS_TOKEN");
        this.N.a(Util.getSharePersistent(this.b, "NAME"), sharePersistent, Util.getSharePersistent(this.b, "OPEN_ID"), this.M, 813, this.an);
    }

    private void n() {
        try {
            Media.insertImage(getContentResolver(), this.ae, this.d, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MediaScannerConnection.scanFile(this, new String[]{Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath()}, null, null);
        aa.a("CommentShowBigPicture", "startScan");
        try {
            Uri fromFile = Uri.fromFile(new File(this.ae));
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(fromFile);
            sendBroadcast(intent);
        } catch (Exception e2) {
            if (this.ap != null) {
                this.ap.disconnect();
            }
            this.ap = new MediaScannerConnection(this, this);
            this.ap.connect();
        }
    }

    public void onMediaScannerConnected() {
        aa.a("CommentShowBigPicture", "onMediaScannerConnected:" + this.ae);
        try {
            this.ap.scanFile(this.ae, "image/*");
        } catch (IllegalStateException e) {
        }
    }

    public void onScanCompleted(String str, Uri uri) {
        aa.a("CommentShowBigPicture", "onScanCompleted");
        Log.e("AAA", "onScanCompleted:" + str + "uri" + uri);
        this.ap.disconnect();
    }
}
