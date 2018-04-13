package cn.xiaochuankeji.tieba.ui.videomaker;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.opengl.EGLContext;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.download.DownloadApi;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.common.medialib.i;
import cn.xiaochuankeji.tieba.common.medialib.k;
import cn.xiaochuankeji.tieba.common.medialib.m;
import cn.xiaochuankeji.tieba.json.UgcTypefaceJson;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicJson;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.videomaker.CameraSurfaceView.c;
import cn.xiaochuankeji.tieba.ui.videomaker.edittext.OverlayEditTextContainer;
import cn.xiaochuankeji.tieba.ui.videomaker.music.SelectVideoMusicActivity;
import cn.xiaochuankeji.tieba.ui.videomaker.roundedvideo.RoundedVideoSurfaceView;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.TouchProcessStickerLayout;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.TemplatedTextStickerDrawable;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.TextStickerDrawable;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment.VideoStickerFragment;
import cn.xiaochuankeji.tieba.ui.widget.f;
import com.airbnb.lottie.LottieAnimationView;
import com.sensetime.stmobile.STCommon;
import com.sensetime.stmobile.STHumanAction;
import com.sensetime.stmobile.STMobileHumanActionNative;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import okhttp3.ab;
import org.json.JSONException;
import org.json.JSONObject;
import rx.d;
import rx.j;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;

@cn.xiaochuankeji.aop.permission.b(a = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"}, b = "开启以下权限才能正常拍摄", d = "确认", f = "开启以下权限才能正常拍摄", h = "拒绝", j = "设置权限", l = true, m = true)
public class VideoRecordActivity extends cn.xiaochuankeji.tieba.ui.base.a implements PreviewCallback, cn.xiaochuankeji.tieba.common.medialib.m.a, cn.xiaochuankeji.tieba.common.medialib.m.b, cn.xiaochuankeji.tieba.ui.videomaker.CameraSurfaceView.a, c, cn.xiaochuankeji.tieba.ui.videomaker.d.a, cn.xiaochuankeji.tieba.ui.videomaker.edittext.OverlayEditTextContainer.a, cn.xiaochuankeji.tieba.ui.videomaker.roundedvideo.RoundedVideoSurfaceView.a, cn.xiaochuankeji.tieba.ui.videomaker.sticker.b.a {
    private volatile boolean A;
    private volatile long B;
    private m C;
    private cn.xiaochuankeji.tieba.common.medialib.b D;
    private volatile boolean E;
    private boolean F;
    private k G;
    private ArrayList<k> H = new ArrayList();
    private UgcVideoMusicJson I;
    private e J;
    private cn.xiaochuankeji.tieba.common.d.a K;
    private volatile int L;
    private cn.xiaochuankeji.tieba.common.d.a M;
    private cn.xiaochuankeji.tieba.common.d.a N;
    private boolean O;
    private a P;
    private View Q;
    private FrameLayout R;
    private TouchProcessStickerLayout S;
    private View T;
    private View U;
    private View V;
    private ImageView W;
    private View X;
    private View Y;
    private View Z;
    private long a;
    private Runnable aA = new Runnable(this) {
        final /* synthetic */ VideoRecordActivity a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.af <= 0) {
                this.a.ad.setVisibility(8);
                this.a.Q.setVisibility(0);
                this.a.ai.a(false);
                return;
            }
            if (this.a.af == this.a.ae) {
                this.a.Q.setVisibility(4);
                this.a.ad.setVisibility(0);
            }
            this.a.ad.setText(String.valueOf(this.a.af));
            VideoRecordActivity.E(this.a);
            this.a.P.postDelayed(this.a.aA, 1000);
        }
    };
    private final ErrorCallback aB = new ErrorCallback(this) {
        final /* synthetic */ VideoRecordActivity a;

        {
            this.a = r1;
        }

        public void onError(int i, Camera camera) {
            String str;
            if (i == 100) {
                str = "Camera server died!";
            } else {
                str = "Camera error: " + i;
            }
            Log.e("VideoRecordActivity", str);
            this.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ AnonymousClass16 b;

                public void run() {
                    g.a(str);
                }
            });
        }
    };
    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.a aC = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.a<cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a>(this) {
        final /* synthetic */ VideoRecordActivity a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.Q.setVisibility(8);
        }

        public void b() {
            this.a.Q.setVisibility(0);
        }

        public void a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar) {
            if (aVar != null) {
                this.a.Q.setVisibility(0);
                this.a.au.setVisibility(4);
                int intrinsicWidth = aVar.getIntrinsicWidth();
                int intrinsicHeight = aVar.getIntrinsicHeight();
                int width = (this.a.at.getWidth() - intrinsicWidth) / 2;
                int height = (this.a.at.getHeight() - intrinsicHeight) / 2;
                aVar.setBounds(width, height, intrinsicWidth + width, intrinsicHeight + height);
                this.a.at.a(aVar);
            }
            this.a.O();
        }
    };
    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.a aD = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.a<b>(this) {
        final /* synthetic */ VideoRecordActivity a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.Q.setVisibility(8);
        }

        public void b() {
            this.a.Q.setVisibility(0);
        }

        public void a(b bVar) {
            this.a.az = bVar.a;
            this.a.a(this.a.az);
            this.a.P.removeCallbacks(this.a.aE);
            if (TextUtils.isEmpty(bVar.b)) {
                this.a.ac.setVisibility(8);
                return;
            }
            this.a.ac.setText(bVar.b);
            this.a.ac.setVisibility(0);
            this.a.P.postDelayed(this.a.aE, 2000);
        }
    };
    private Runnable aE = new Runnable(this) {
        final /* synthetic */ VideoRecordActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.ac.setVisibility(8);
        }
    };
    private View aa;
    private View ab;
    private TextView ac;
    private TextSwitcher ad;
    private int ae;
    private int af;
    private View ag;
    private View ah;
    private ShutterButton ai;
    private View aj;
    private VideoRecordProgressView ak;
    private cn.xiaochuankeji.tieba.ui.videomaker.VideoRecordProgressView.a al;
    private RoundedVideoSurfaceView am;
    private VideoRecordOptionPanel an;
    private f ao;
    private CustomFakeViewPager ap;
    private TextView aq;
    private int ar;
    private d as;
    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.b at;
    private OverlayEditTextContainer au;
    private int av = -1;
    private int aw = -1;
    private String ax;
    private boolean ay = false;
    private String az = null;
    private long b;
    private Topic c;
    private Camera d;
    private int e;
    private CameraInfo f;
    private int g;
    private final Object h = new Object();
    private Runnable i;
    private boolean j;
    private HandlerThread k;
    private Handler l;
    private final Object m = new Object();
    private volatile boolean n;
    private final Object o = new Object();
    private volatile boolean p;
    private SurfaceTexture q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private CameraSurfaceView w;
    private byte[] x;
    private STMobileHumanActionNative y;
    private Accelerometer z;

    private class a extends Handler {
        final /* synthetic */ VideoRecordActivity a;

        private a(VideoRecordActivity videoRecordActivity) {
            this.a = videoRecordActivity;
        }

        public void handleMessage(Message message) {
            boolean z = true;
            if (message.what == 100) {
                if (message.arg1 != 1) {
                    z = false;
                }
                this.a.X.setSelected(false);
                this.a.X.setEnabled(z);
            } else if (message.what == 101) {
                int i = message.arg1;
            } else if (message.what == 102) {
                this.a.aq.setVisibility(8);
            }
        }
    }

    public static class b {
        public String a;
        public String b;

        public b(String str, String str2) {
            this.a = str;
            this.b = str2;
        }
    }

    static /* synthetic */ int E(VideoRecordActivity videoRecordActivity) {
        int i = videoRecordActivity.af - 1;
        videoRecordActivity.af = i;
        return i;
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static void a(Activity activity, int i, long j, String str) {
        if (j.a(true)) {
            Intent intent = new Intent(activity, VideoRecordActivity.class);
            intent.putExtra("key_owner_id", j);
            intent.putExtra("key_follow_from_src", str);
            activity.startActivityForResult(intent, i);
        }
    }

    public static void a(Activity activity, int i, Topic topic, String str) {
        if (j.a(true)) {
            Intent intent = new Intent(activity, VideoRecordActivity.class);
            intent.putExtra("key_topic", topic);
            intent.putExtra("key_follow_from_src", str);
            activity.startActivityForResult(intent, i);
        }
    }

    public static void a(Context context, cn.xiaochuankeji.tieba.ui.videomaker.a.a aVar) {
        if (j.a(true)) {
            Intent intent = new Intent(context, VideoRecordActivity.class);
            intent.putExtra("key_draft_video", aVar);
            context.startActivity(intent);
        }
    }

    public static void a(Context context, @NonNull h hVar) {
        if (j.a(true)) {
            Intent intent = new Intent(context, VideoRecordActivity.class);
            if (hVar != null) {
                intent.putExtra("key_video_workspace", hVar);
            }
            context.startActivity(intent);
        }
    }

    protected boolean i() {
        return false;
    }

    public boolean h() {
        return false;
    }

    protected int a() {
        return R.layout.activity_video_record;
    }

    protected void i_() {
        this.av = e.a(53.0f);
        this.aw = e.a(77.0f);
        this.as = new d();
        this.as.a((cn.xiaochuankeji.tieba.ui.videomaker.d.a) this);
        this.w = (CameraSurfaceView) findViewById(R.id.camera_surface);
        this.w.setCallback(this);
        this.w.setListener(this);
        this.Q = findViewById(R.id.layout_control_container);
        this.T = findViewById(R.id.layout_topbar);
        this.U = findViewById(R.id.btn_record_cancel);
        this.U.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.onBackPressed();
            }
        });
        this.V = findViewById(R.id.btn_face_beautify);
        this.V.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.f(!view.isSelected());
            }
        });
        this.W = (ImageView) findViewById(R.id.btn_pick_bgm);
        this.W.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                SelectVideoMusicActivity.a(this.a, 1002, this.a.I);
            }
        });
        this.X = findViewById(R.id.btn_camera_flash);
        this.X.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                boolean z;
                boolean z2 = true;
                boolean isSelected = view.isSelected();
                VideoRecordActivity videoRecordActivity = this.a;
                if (isSelected) {
                    z = false;
                } else {
                    z = true;
                }
                if (videoRecordActivity.d(z)) {
                    if (isSelected) {
                        z2 = false;
                    }
                    view.setSelected(z2);
                }
            }
        });
        this.X.setEnabled(false);
        this.Y = findViewById(R.id.btn_camera_switch);
        this.Y.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.j();
            }
        });
        findViewById(R.id.btn_option_more).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.an.a();
            }
        });
        this.ad = (TextSwitcher) findViewById(R.id.countdown_switcher);
        this.ad.setFactory(new ViewFactory(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public View makeView() {
                View textView = new TextView(this.a);
                textView.setTextColor(-1);
                textView.setTextSize(62.0f);
                textView.setGravity(17);
                textView.setPadding(0, 0, 0, e.a(5.0f));
                textView.setLayoutParams(new LayoutParams(-1, -1));
                return textView;
            }
        });
        this.ad.setInAnimation(this, R.anim.text_scale_in);
        this.ag = findViewById(R.id.layout_actionbar);
        this.ah = findViewById(R.id.btn_delete_record);
        this.ah.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                f.a("删除", "确认删除上一段视频？", this.a, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                    final /* synthetic */ AnonymousClass42 a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        if (z) {
                            this.a.a.I();
                        }
                    }
                }, true);
            }
        });
        this.ai = (ShutterButton) findViewById(R.id.btn_shutter);
        this.ai.setListener(new ShutterButton.c(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public boolean a() {
                if (this.a.ak.b() < 16000) {
                    return true;
                }
                g.a("已经录制" + String.valueOf(16) + "秒，请删除一段重新录制");
                return false;
            }

            public void b() {
                this.a.ae = this.a.an.c();
                if (this.a.ae > 0) {
                    this.a.ai.d();
                    this.a.an.e();
                    this.a.af = this.a.ae;
                    this.a.P.removeCallbacks(this.a.aA);
                    this.a.aA.run();
                    return;
                }
                PermissionItem permissionItem = new PermissionItem("android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE");
                permissionItem.runIgnorePermission = false;
                permissionItem.settingText = "设置";
                permissionItem.deniedMessage = "开启以下权限才能正常拍摄";
                permissionItem.needGotoSetting = true;
                cn.xiaochuankeji.aop.permission.a.a(this.a).a(permissionItem, new cn.xiaochuankeji.aop.permission.e(this) {
                    final /* synthetic */ AnonymousClass43 a;

                    {
                        this.a = r1;
                    }

                    public void permissionGranted() {
                        this.a.a.F();
                    }

                    public void permissionDenied() {
                        g.a("开启以下权限才能正常拍摄");
                    }
                });
            }

            public void c() {
                this.a.G();
            }
        });
        this.aj = findViewById(R.id.btn_finish_record);
        this.aj.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.G();
                this.a.J();
            }
        });
        this.ak = (VideoRecordProgressView) findViewById(R.id.progress_view);
        this.ak.a((Activity) this);
        this.ak.setMinDuration(3000);
        this.ak.setMaxDuration(16000);
        this.al = new cn.xiaochuankeji.tieba.ui.videomaker.VideoRecordProgressView.a(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.r();
            }

            public void b() {
                this.a.s();
            }

            public void c() {
                this.a.aj.setVisibility(0);
            }

            public void d() {
                this.a.G();
                this.a.J();
            }
        };
        this.ak.post(new Runnable(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (!this.a.H.isEmpty()) {
                    Iterator it = this.a.H.iterator();
                    while (it.hasNext()) {
                        k kVar = (k) it.next();
                        this.a.ak.a();
                        this.a.ak.setCurrentProgress(kVar.d);
                        this.a.ak.c();
                    }
                }
                this.a.ak.setListener(this.a.al);
                this.a.D();
                this.a.c(false);
            }
        });
        this.am = (RoundedVideoSurfaceView) findViewById(R.id.video_preview);
        this.am.setCallback(this);
        this.am.setCornerRadius((float) e.a(10.0f));
        this.am.setVideoAspectRatio(i.a());
        this.an = (VideoRecordOptionPanel) findViewById(R.id.option_panel);
        this.R = (FrameLayout) findViewById(R.id.rootView);
        findViewById(R.id.btn_video_filter).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.ao == null) {
                    this.a.ao = new f(this.a);
                    this.a.R.addView(this.a.ao.f_(), new LayoutParams(-1, -1));
                    this.a.ao.a(new cn.xiaochuankeji.tieba.ui.videomaker.f.a(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public void a(int i) {
                            this.a.a.ay = true;
                            this.a.a.ap.setCustomCurrentItem(i);
                        }

                        public void a() {
                            this.a.a.b(true);
                        }
                    });
                }
                this.a.ao.a(this.a.ar);
                this.a.b(false);
            }
        });
        this.Z = findViewById(R.id.open_sticker);
        this.Z.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.L();
            }
        });
        this.ab = findViewById(R.id.open_magic_emotion);
        this.ab.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.M();
            }
        });
        this.ac = (TextView) findViewById(R.id.tv_magic_emotion);
        this.aa = findViewById(R.id.open_text_sticker);
        this.aa.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.c(null);
            }
        });
        this.ap = (CustomFakeViewPager) findViewById(R.id.vFakeViewPager);
        this.aq = (TextView) findViewById(R.id.tvVideoFilterName);
        this.ap.setVideoFilterListener(new cn.xiaochuankeji.tieba.ui.videomaker.CustomFakeViewPager.b(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void a(int i, float f) {
                this.a.w.a(i, 1.0f - f);
            }

            public void a(int i) {
                this.a.w.setVideoFilter(i);
                this.a.ar = i % g.a.size();
                if (this.a.ay) {
                    this.a.ay = false;
                } else {
                    this.a.p();
                }
            }
        });
        this.S = (TouchProcessStickerLayout) findViewById(R.id.sticker_layout);
        this.S.a(this.ap, this.at);
        this.S.addView(this.at, new LayoutParams(-1, -1));
        this.at.h();
        this.at.a(-1, -1);
        this.at.setVideoAspectRatio(i.a());
        this.au = (OverlayEditTextContainer) findViewById(R.id.overlay_edit_text_container);
        this.au.setListener(this);
        P();
        this.e = 1;
        f(true);
    }

    private void p() {
        this.aq.setText(((g.c) g.a.get(this.ar)).a);
        if (this.aq.getVisibility() == 0) {
            this.P.removeMessages(102);
        } else {
            this.aq.setVisibility(0);
        }
        this.P.sendEmptyMessageDelayed(102, 3000);
    }

    private void b(boolean z) {
        if (z) {
            this.Q.setVisibility(0);
        } else {
            this.Q.setVisibility(8);
        }
    }

    protected boolean a(Bundle bundle) {
        boolean z = false;
        if (!cn.xiaochuankeji.tieba.ui.videomaker.c.a.a(this, "SenseME.lic")) {
            g.a("美颜等功能失效啦，快升级到新版本吧");
        }
        this.z = new Accelerometer(this);
        K();
        this.at = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.b(this);
        this.at.setDelegate(this);
        if (bundle != null) {
            this.a = bundle.getLong("key_owner_id", 0);
            this.c = (Topic) bundle.getSerializable("key_topic");
            this.ax = bundle.getString("key_follow_from_src", null);
            this.b = bundle.getLong("key_draft_id", 0);
            this.H = bundle.getParcelableArrayList("key_video_parts");
            this.J = (e) bundle.getParcelable("key_bgm_sound");
            this.I = (UgcVideoMusicJson) bundle.getSerializable("key_bgm_json");
            try {
                this.at.a(new JSONObject(bundle.getString("key_sticker_drawables")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Intent intent = getIntent();
            cn.xiaochuankeji.tieba.ui.videomaker.a.a aVar = (cn.xiaochuankeji.tieba.ui.videomaker.a.a) intent.getParcelableExtra("key_draft_video");
            if (aVar == null) {
                this.a = intent.getLongExtra("key_owner_id", 0);
                this.c = (Topic) intent.getSerializableExtra("key_topic");
                this.ax = intent.getStringExtra("key_follow_from_src");
                h hVar = (h) intent.getParcelableExtra("key_video_workspace");
                if (hVar != null) {
                    this.b = hVar.f;
                    this.a = hVar.g;
                    this.I = hVar.h;
                    this.J = hVar.c;
                    this.H = hVar.e;
                }
            } else if (!a(aVar)) {
                return false;
            }
        }
        this.k = new HandlerThread("CameraThread");
        this.k.start();
        this.l = new Handler(this.k.getLooper());
        this.P = new a();
        boolean z2 = !cn.xiaochuankeji.tieba.background.a.a().getBoolean("codec_switch", false);
        i b = j.b();
        if (z2 && b.d) {
            z = true;
        }
        m.a(z);
        this.C = new m(this, new i(b));
        this.C.a((cn.xiaochuankeji.tieba.common.medialib.m.b) this);
        this.C.a((cn.xiaochuankeji.tieba.common.medialib.m.a) this);
        this.C.b();
        this.D = new cn.xiaochuankeji.tieba.common.medialib.b(new cn.xiaochuankeji.tieba.common.medialib.a(44100, 2, 2));
        this.D.a(new cn.xiaochuankeji.tieba.common.medialib.b.b(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void a(Throwable th) {
                if (th instanceof SecurityException) {
                    g.a("无法录音，请检查权限");
                } else {
                    g.a(th.getMessage());
                }
            }
        });
        q();
        return true;
    }

    private void q() {
        final int i = ((cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.b) cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.a().get(0)).h;
        if (i != 0 && cn.xiaochuankeji.tieba.ui.videomaker.edittext.a.a(i) == null) {
            new cn.xiaochuankeji.tieba.api.ugcvideo.a().a(i).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new j<UgcTypefaceJson>(this) {
                final /* synthetic */ VideoRecordActivity b;

                public /* synthetic */ void onNext(Object obj) {
                    a((UgcTypefaceJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(UgcTypefaceJson ugcTypefaceJson) {
                    this.b.a(ugcTypefaceJson.typefaceUrl, i);
                }
            });
        }
    }

    private void a(String str, final int i) {
        c(i, 3);
        ((DownloadApi) cn.xiaochuankeji.tieba.network.c.a().a(DownloadApi.class)).download(str).b(rx.f.a.c()).d(new rx.b.g<ab, String>(this) {
            final /* synthetic */ VideoRecordActivity b;

            public /* synthetic */ Object call(Object obj) {
                return a((ab) obj);
            }

            public String a(ab abVar) {
                return this.b.a(new File(cn.xiaochuankeji.tieba.background.a.e().K().getAbsolutePath() + File.separator + i + ".zip"), abVar);
            }
        }).a(rx.a.b.a.a()).b(new j<String>(this) {
            final /* synthetic */ VideoRecordActivity b;

            public /* synthetic */ void onNext(Object obj) {
                a((String) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.b.c(i, 1);
            }

            public void a(String str) {
                if (TextUtils.isEmpty(str)) {
                    this.b.c(i, 1);
                    return;
                }
                cn.xiaochuankeji.tieba.ui.videomaker.edittext.a.a(i, str);
                this.b.c(i, 2);
                this.b.au.a();
            }
        });
    }

    private void c(int i, int i2) {
        Iterator it = cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.a().iterator();
        while (it.hasNext()) {
            cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.b bVar = (cn.xiaochuankeji.tieba.ui.videomaker.edittext.e.b) it.next();
            if (bVar.h == i) {
                bVar.m = i2;
            }
        }
    }

    private String a(File file, ab abVar) {
        InputStream byteStream;
        Exception e;
        Throwable th;
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file, false);
            try {
                byteStream = abVar.byteStream();
                try {
                    byte[] bArr = new byte[16384];
                    while (true) {
                        int read = byteStream.read(bArr);
                        if (-1 == read) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.getFD().sync();
                    fileOutputStream.close();
                    fileOutputStream = null;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (byteStream == null) {
                            return null;
                        }
                        try {
                            byteStream.close();
                            return null;
                        } catch (IOException e32) {
                            e32.printStackTrace();
                            return null;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (byteStream != null) {
                            try {
                                byteStream.close();
                            } catch (IOException e42) {
                                e42.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                byteStream = null;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (byteStream == null) {
                    return null;
                }
                byteStream.close();
                return null;
            } catch (Throwable th3) {
                th = th3;
                byteStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (byteStream != null) {
                    byteStream.close();
                }
                throw th;
            }
            try {
                byteStream.close();
                byteStream = null;
                String a = cn.htjyb.c.a.b.a(file.getPath(), file.getParent());
                if (null != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                if (null == null) {
                    return a;
                }
                try {
                    byteStream.close();
                    return a;
                } catch (IOException e422) {
                    e422.printStackTrace();
                    return a;
                }
            } catch (Exception e7) {
                e = e7;
                fileOutputStream = null;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (byteStream == null) {
                    return null;
                }
                byteStream.close();
                return null;
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (byteStream != null) {
                    byteStream.close();
                }
                throw th;
            }
        } catch (Exception e8) {
            e = e8;
            byteStream = null;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (byteStream == null) {
                return null;
            }
            byteStream.close();
            return null;
        } catch (Throwable th5) {
            th = th5;
            byteStream = null;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (byteStream != null) {
                byteStream.close();
            }
            throw th;
        }
    }

    private boolean a(cn.xiaochuankeji.tieba.ui.videomaker.a.a aVar) {
        try {
            this.b = aVar.a;
            this.a = aVar.b;
            h hVar = (h) new cn.xiaochuankeji.tieba.ui.videomaker.a.b().a(aVar).first;
            this.H.addAll(hVar.e);
            this.J = hVar.c;
            this.I = hVar.h;
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void a(h hVar) {
        this.b = hVar.f;
        this.a = hVar.g;
    }

    private void c(boolean z) {
        if (z) {
            this.ah.setVisibility(4);
            if (this.ak.b() < 3000) {
                this.aj.setVisibility(4);
            } else {
                this.aj.setVisibility(0);
            }
            getWindow().addFlags(128);
            P();
            this.Z.setVisibility(4);
            this.ab.setVisibility(4);
            this.aa.setVisibility(4);
            return;
        }
        if (this.H.isEmpty()) {
            this.ah.setVisibility(4);
            this.W.setImageResource(R.drawable.bgm_selector);
            this.W.setEnabled(true);
        } else {
            this.ah.setVisibility(0);
            this.W.setImageResource(R.drawable.bgm_disabled_selector);
            this.W.setEnabled(false);
        }
        if (this.ak.b() < 3000) {
            this.aj.setVisibility(4);
        } else {
            this.aj.setVisibility(0);
        }
        this.Z.setVisibility(0);
        this.ab.setVisibility(0);
        this.aa.setVisibility(0);
        getWindow().clearFlags(128);
        O();
    }

    private void r() {
        if (!this.O && !this.H.isEmpty()) {
            this.O = true;
            this.w.b(true);
            this.am.onResume();
            this.am.setVisibility(0);
            this.T.setVisibility(4);
            this.ag.setVisibility(4);
            this.at.setVisibility(4);
        }
    }

    private void s() {
        if (this.O) {
            this.O = false;
            this.w.b(false);
            this.am.setVisibility(4);
            this.am.onPause();
            this.T.setVisibility(0);
            this.ag.setVisibility(0);
            this.at.setVisibility(0);
        }
    }

    private k t() {
        String valueOf = String.valueOf(System.currentTimeMillis());
        File a = j.a(this.b, this.a);
        return new k(new File(a, valueOf + ".mp4").getAbsolutePath(), new File(a, valueOf + ".wav").getAbsolutePath(), 0);
    }

    private void u() {
        if (Thread.currentThread() != this.k) {
            throw new IllegalStateException("Wrong thread");
        }
    }

    private int v() {
        if (this.f.facing == 1) {
            return (360 - (this.f.orientation % com.umeng.analytics.a.p)) % com.umeng.analytics.a.p;
        }
        return (this.f.orientation + com.umeng.analytics.a.p) % com.umeng.analytics.a.p;
    }

    private int w() {
        if (this.f.facing != 1 && this.f.orientation == 90) {
            return 1;
        }
        return 3;
    }

    private int x() {
        int c = Accelerometer.c();
        int i = c - 1;
        if (i < 0) {
            return c ^ 3;
        }
        return i;
    }

    private int y() {
        int x = x();
        if (this.f.facing == 1) {
            if (x == 1) {
                return 3;
            }
            if (x == 3) {
                return 1;
            }
        }
        return x;
    }

    private boolean a(Parameters parameters) {
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        if (supportedFlashModes != null) {
            for (String equals : supportedFlashModes) {
                if (equals.equals("torch")) {
                    return true;
                }
            }
        }
        return false;
    }

    private void z() {
        final int i = j.b().b;
        final int a = i.a(i);
        this.l.post(new Runnable(this) {
            final /* synthetic */ VideoRecordActivity c;

            public void run() {
                this.c.a(a, i, 30);
            }
        });
    }

    private void a(final int i, final int i2, final int i3) {
        u();
        if (this.d != null) {
            throw new RuntimeException("Camera has already been started.");
        }
        try {
            synchronized (this.h) {
                Log.d("VideoRecordActivity", "Opening camera " + this.e);
                this.d = Camera.open(this.e);
                this.f = new CameraInfo();
                Camera.getCameraInfo(this.e, this.f);
            }
            try {
                this.d.setPreviewTexture(this.q);
                this.d.setPreviewCallback(this);
                Log.d("VideoRecordActivity", "Camera orientation: " + this.f.orientation);
                this.d.setErrorCallback(this.aB);
                b(i, i2, i3);
            } catch (Throwable e) {
                Log.e("VideoRecordActivity", "setPreviewTexture failed", null);
                throw new RuntimeException(e);
            } catch (Throwable e2) {
                Log.e("VideoRecordActivity", "startCapture failed", e2);
                A();
            }
        } catch (Throwable e22) {
            this.g++;
            if (this.g < 3) {
                Log.e("VideoRecordActivity", "Camera.open failed, retrying", e22);
                this.i = new Runnable(this) {
                    final /* synthetic */ VideoRecordActivity d;

                    public void run() {
                        this.d.a(i, i2, i3);
                    }
                };
                this.l.postDelayed(this.i, 500);
                return;
            }
            runOnUiThread(new Runnable(this) {
                final /* synthetic */ VideoRecordActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    g.a("相机无法打开，请检查权限");
                }
            });
            this.g = 0;
            throw e22;
        }
    }

    @TargetApi(15)
    private void b(int i, int i2, int i3) {
        int i4 = 1;
        u();
        Log.d("VideoRecordActivity", "startPreviewOnCameraThread");
        if (this.d == null) {
            Log.e("VideoRecordActivity", "Calling startPreviewOnCameraThread on stopped camera.");
            return;
        }
        this.r = i;
        this.s = i2;
        this.t = i3;
        Parameters parameters = this.d.getParameters();
        int[] a = cn.xiaochuankeji.tieba.d.c.a(parameters, i3 * 1000);
        Size a2 = cn.xiaochuankeji.tieba.d.c.a(parameters.getSupportedPreviewSizes(), i, i2);
        Log.d("VideoRecordActivity", "isVideoStabilizationSupported: " + parameters.isVideoStabilizationSupported());
        if (parameters.isVideoStabilizationSupported()) {
            parameters.setVideoStabilization(true);
        }
        if (a.length > 0) {
            parameters.setPreviewFpsRange(a[0], a[1]);
        }
        parameters.setPreviewSize(a2.width, a2.height);
        Size a3 = cn.xiaochuankeji.tieba.d.c.a(parameters.getSupportedPictureSizes(), i, i2);
        if (a3 != null) {
            parameters.setPictureSize(a3.width, a3.height);
        }
        if (this.j) {
            this.d.stopPreview();
        }
        this.j = true;
        List supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes != null && supportedFocusModes.contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        }
        int v = v();
        if (v % 180 == 0) {
            this.u = a2.width;
            this.v = a2.height;
        } else {
            this.u = a2.height;
            this.v = a2.width;
        }
        if (!a(parameters)) {
            i4 = 0;
        }
        this.P.sendMessage(this.P.obtainMessage(100, i4, 0));
        this.d.setParameters(parameters);
        this.d.setDisplayOrientation(v);
        this.d.startPreview();
    }

    void e() {
        Log.d("VideoRecordActivity", "stopCapture");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.l.post(new Runnable(this) {
            final /* synthetic */ VideoRecordActivity b;

            public void run() {
                this.b.A();
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("VideoRecordActivity", "stopCapture done");
    }

    private void A() {
        u();
        Log.d("VideoRecordActivity", "stopCaptureOnCameraThread");
        if (this.i != null) {
            this.l.removeCallbacks(this.i);
        }
        this.g = 0;
        if (this.d == null) {
            Log.e("VideoRecordActivity", "Calling stopCapture() for already stopped camera.");
            return;
        }
        try {
            this.d.setFaceDetectionListener(null);
            this.d.stopFaceDetection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("VideoRecordActivity", "Stop preview.");
        if (this.j) {
            this.d.setPreviewCallback(null);
            this.d.stopPreview();
        }
        this.j = false;
        this.u = 0;
        this.v = 0;
        Log.d("VideoRecordActivity", "Release camera.");
        this.d.release();
        this.d = null;
    }

    public void j() {
        if (Camera.getNumberOfCameras() >= 2) {
            synchronized (this.m) {
                if (this.n) {
                    Log.w("VideoRecordActivity", "Ignoring camera switch request.");
                    return;
                }
                this.n = true;
                this.l.post(new Runnable(this) {
                    final /* synthetic */ VideoRecordActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.d != null) {
                            this.a.B();
                            synchronized (this.a.m) {
                                this.a.n = false;
                            }
                        }
                    }
                });
            }
        }
    }

    private void B() {
        u();
        Log.d("VideoRecordActivity", "switchCameraOnCameraThread");
        A();
        synchronized (this.h) {
            this.e = (this.e + 1) % Camera.getNumberOfCameras();
        }
        this.P.sendMessage(this.P.obtainMessage(101, this.e, 0));
        a(this.r, this.s, this.t);
        Log.d("VideoRecordActivity", "switchCameraOnCameraThread done");
    }

    private boolean d(final boolean z) {
        boolean z2 = true;
        synchronized (this.o) {
            if (this.p) {
                Log.w("VideoRecordActivity", "Ignoring camera flash switch request.");
                z2 = false;
            } else {
                this.p = true;
                this.l.post(new Runnable(this) {
                    final /* synthetic */ VideoRecordActivity b;

                    public void run() {
                        if (this.b.d != null) {
                            this.b.e(z);
                            synchronized (this.b.o) {
                                this.b.p = false;
                            }
                        }
                    }
                });
            }
        }
        return z2;
    }

    private void e(boolean z) {
        u();
        Log.d("VideoRecordActivity", "switchCameraFlashOnCameraThread");
        if (this.d == null) {
            Log.e("VideoRecordActivity", "Calling switchCameraFlashOnCameraThread on stopped camera.");
            return;
        }
        Parameters parameters = this.d.getParameters();
        if (a(parameters)) {
            if (z) {
                parameters.setFlashMode("torch");
            } else {
                parameters.setFlashMode("off");
            }
            this.d.setParameters(parameters);
        }
        Log.d("VideoRecordActivity", "switchCameraFlashOnCameraThread done");
    }

    public void onResume() {
        super.onResume();
        this.z.a();
        this.w.onResume();
        if (!cn.xiaochuankeji.tieba.background.utils.c.a()) {
            g.a("无法录音，请检查权限");
        }
    }

    public void onPause() {
        super.onPause();
        if (this.F) {
            G();
        }
        if (this.O) {
            this.ak.f();
        }
        this.z.b();
        this.w.onPause();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (C() || (i != 25 && i != 24)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (C() || (i != 25 && i != 24)) {
            return super.onKeyUp(i, keyEvent);
        }
        if (this.F) {
            this.ai.a();
            return true;
        }
        this.ai.a(true);
        return true;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putLong("key_owner_id", this.a);
        if (this.c != null) {
            bundle.putParcelable("key_topic", this.c);
        }
        if (this.ax != null) {
            bundle.putString("key_follow_from_src", this.ax);
        }
        bundle.putLong("key_draft_id", this.b);
        bundle.putParcelableArrayList("key_video_parts", this.H);
        bundle.putParcelable("key_bgm_sound", this.J);
        if (this.I != null) {
            bundle.putSerializable("key_bgm_json", this.I);
        }
        JSONObject jSONObject = new JSONObject();
        this.at.b(jSONObject);
        bundle.putString("key_sticker_drawables", jSONObject.toString());
    }

    public void onBackPressed() {
        if (this.an.isShown()) {
            this.an.b();
        } else if (getSupportFragmentManager().popBackStackImmediate("V_S_F_sticker", 1)) {
            this.Q.setVisibility(0);
        } else if (getSupportFragmentManager().popBackStackImmediate("V_S_F_magic_emotion", 1)) {
            this.Q.setVisibility(0);
        } else if (this.ao != null && this.ao.c()) {
            b(true);
        } else if (!l() && !this.F) {
            if (this.O) {
                this.ak.f();
            } else if (this.H.isEmpty()) {
                super.onBackPressed();
            } else if (this.b > 0) {
                f.a("提示", "放弃编辑？", this, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                    final /* synthetic */ VideoRecordActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        if (z) {
                            j.d();
                            this.a.finish();
                        }
                    }
                }, true).setConfirmTip("放弃");
            } else {
                f a = f.a("提示", "是否放弃当前录制？", this, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                    final /* synthetic */ VideoRecordActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        if (z) {
                            j.d();
                            this.a.finish();
                        }
                    }
                }, true);
                a.setCancelTip("继续录制");
                a.setConfirmTip("放弃");
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        cn.xiaochuankeji.tieba.ui.videomaker.sticker.a.b.a();
        cn.xiaochuankeji.tieba.ui.videomaker.edittext.a.a();
        if (this.S != null) {
            this.S.removeAllViews();
        }
        if (this.at != null) {
            this.at.setDelegate(null);
            this.at = null;
        }
        if (this.as != null) {
            this.as.a(null);
            this.as = null;
        }
        this.P.removeCallbacksAndMessages(null);
        E();
        this.l.getLooper().quit();
        this.C.f();
        this.D.b();
    }

    private void f(boolean z) {
        this.V.setSelected(z);
        this.A = z;
        this.w.a(z);
    }

    private void a(String str) {
        this.w.a(str, new cn.xiaochuankeji.tieba.ui.videomaker.CameraSurfaceView.b(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void a(String str, long j) {
                this.a.B = j;
            }
        });
    }

    private boolean C() {
        if (!this.H.isEmpty() && !TextUtils.isEmpty(((k) this.H.get(0)).b)) {
            return false;
        }
        boolean z = this.J != null && new File(this.J.a).exists();
        return z;
    }

    private void D() {
        E();
        if (C()) {
            this.K = new cn.xiaochuankeji.tieba.common.d.a(this);
            this.K.a(false);
            this.K.a(this.J.a);
            Iterator it = this.H.iterator();
            int i = 0;
            while (it.hasNext()) {
                i = ((k) it.next()).d + i;
            }
            this.L = i;
            this.K.seekTo(this.L);
            this.W.setSelected(true);
            return;
        }
        this.W.setSelected(false);
    }

    private void E() {
        if (this.K != null) {
            this.K.g();
            this.K = null;
        }
    }

    private void F() {
        if (!this.F) {
            this.G = t();
            this.w.setVideoFilter(this.ar);
            Pair d = this.an.d();
            this.C.a(((Integer) d.first).intValue(), ((Integer) d.second).intValue());
            if (this.C.a(this.G.a)) {
                this.F = true;
                if (this.K != null) {
                    this.K.b((((float) ((Integer) d.second).intValue()) * 1.0f) / ((float) ((Integer) d.first).intValue()));
                    this.K.start();
                } else {
                    this.D.a(this.G.b, (1.0f * ((float) ((Integer) d.first).intValue())) / ((float) ((Integer) d.second).intValue()), new cn.xiaochuankeji.tieba.common.medialib.b.a(this) {
                        final /* synthetic */ VideoRecordActivity a;

                        {
                            this.a = r1;
                        }

                        public long a() {
                            return this.a.C.d();
                        }
                    });
                }
                this.ak.a();
                this.T.setVisibility(8);
                this.ap.b();
                this.ap.setVisibility(8);
                c(true);
                return;
            }
            this.ai.d();
            g.a("sorry，您的设备暂时无法录制视频");
        }
    }

    private void G() {
        if (this.F) {
            this.F = false;
            k e = this.C.e();
            if (this.D.a()) {
                this.D.a(e.a);
            }
            this.C.b();
            this.ak.setCurrentProgress(e.a);
            this.ak.c();
            if (e.a > 0) {
                k kVar = new k(this.G.a, C() ? null : this.G.b, e.a);
                kVar.c = this.at.getTrace();
                this.H.add(kVar);
            } else {
                new File(this.G.a).delete();
                new File(this.G.b).delete();
            }
            if (this.K != null) {
                this.K.pause();
                this.K.seekTo(this.ak.b());
            }
            this.ai.d();
            this.T.setVisibility(0);
            this.ap.a();
            this.ap.setVisibility(0);
            c(false);
            a(e.a());
            j.b(new cn.xiaochuankeji.tieba.ui.videomaker.h.a().b(this.J).a(this.H).a(this.b).b(this.a).a(this.I).a(this.c).a());
        }
    }

    private void a(float f) {
    }

    private void b(SurfaceTexture surfaceTexture) {
        int i = 0;
        if (!this.H.isEmpty()) {
            k kVar = (k) this.H.get(this.H.size() - 1);
            this.M = new cn.xiaochuankeji.tieba.common.d.a(this);
            this.M.a(surfaceTexture);
            this.M.a(kVar.a);
            this.M.a(new IMediaPlayer$OnCompletionListener(this) {
                final /* synthetic */ VideoRecordActivity a;

                {
                    this.a = r1;
                }

                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    if (this.a.M != null) {
                        this.a.M.seekTo(0);
                        this.a.M.start();
                        if (this.a.N != null) {
                            this.a.N.seekTo(0);
                            this.a.N.start();
                        }
                    }
                }
            });
            this.M.start();
            if (!TextUtils.isEmpty(kVar.b) || this.J != null) {
                this.N = new cn.xiaochuankeji.tieba.common.d.a(this);
                if (TextUtils.isEmpty(kVar.b)) {
                    this.N.a(this.J.a);
                    int i2 = 0;
                    while (i < this.H.size() - 1) {
                        i2 += ((k) this.H.get(i)).d;
                        i++;
                    }
                    this.N.seekTo(i2);
                } else {
                    this.N.a(kVar.b);
                }
                this.N.start();
            }
        }
    }

    private void H() {
        if (this.M != null) {
            this.M.f();
            this.M.g();
            this.M = null;
        }
        if (this.N != null) {
            this.N.f();
            this.N.g();
            this.N = null;
        }
    }

    private void I() {
        if (!this.H.isEmpty()) {
            this.H.remove(this.H.size() - 1);
            if (this.K != null) {
                int i = 0;
                for (int i2 = 0; i2 < this.H.size(); i2++) {
                    i += ((k) this.H.get(i2)).d;
                }
                this.L = i;
                this.K.seekTo(this.L);
            }
            this.ak.d();
            if (this.H.isEmpty()) {
                j.d();
            }
            c(false);
        }
    }

    private void J() {
        if (!this.H.isEmpty()) {
            this.aj.setEnabled(false);
            cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this, "正在合成中");
            d.a(Boolean.valueOf(true)).d(new rx.b.g<Boolean, h>(this) {
                final /* synthetic */ VideoRecordActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ Object call(Object obj) {
                    return a((Boolean) obj);
                }

                public h a(Boolean bool) {
                    return j.a(this.a.H, this.a.J, this.a.b, this.a.a, this.a.I, this.a.c);
                }
            }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.e<h>(this) {
                final /* synthetic */ VideoRecordActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((h) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    this.a.aj.setEnabled(true);
                    cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                }

                public void a(h hVar) {
                    this.a.aj.setEnabled(true);
                    cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                    VideoProcessActivity.a(this.a, hVar, 1001, this.a.ax);
                }
            });
        }
    }

    private void K() {
        this.y = new STMobileHumanActionNative();
        d.a(Boolean.valueOf(true)).d(new rx.b.g<Boolean, Boolean>(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((Boolean) obj);
            }

            public Boolean a(Boolean bool) {
                return Boolean.valueOf(this.a.y.createInstanceFromAssetFile("SenseME_action.model", STMobileHumanActionNative.ST_MOBILE_HUMAN_ACTION_DEFAULT_CONFIG_VIDEO, this.a.getAssets()) == 0);
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.e<Boolean>(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Boolean) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(Boolean bool) {
            }
        });
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (this.x == null || this.x.length != bArr.length) {
            this.x = new byte[bArr.length];
        }
        STCommon.stImageRotate(bArr, this.x, this.v, this.u, 3, w());
        long j = this.B;
        if (this.A) {
            j |= 1;
        }
        STHumanAction humanActionDetect = this.y.humanActionDetect(this.x, 3, j, y(), this.u, this.v);
        if (this.f.facing == 1) {
            humanActionDetect = this.y.humanActionMirror(this.u, humanActionDetect);
        }
        this.w.a(humanActionDetect, this.u, this.v);
    }

    public void a(EGLContext eGLContext, SurfaceTexture surfaceTexture) {
        this.q = surfaceTexture;
        z();
        this.C.a(eGLContext);
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ VideoRecordActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.w.setVideoFilter(this.a.ar);
                this.a.a(this.a.az);
            }
        });
    }

    public void a(int i, int i2) {
    }

    public void m_() {
        this.q = null;
        e();
        this.C.a();
    }

    public void a(int i, int i2, int i3, long j) {
        if (this.C.c()) {
            boolean z = false;
            if (this.K == null) {
                if (this.D.a()) {
                    z = true;
                }
            } else if (this.K.getCurrentPosition() > this.L) {
                z = true;
            }
            if (z && !this.E) {
                this.E = true;
                cn.xiaochuankeji.tieba.ui.videomaker.sticker.c andLockCurrentStickerFrame = this.at.getAndLockCurrentStickerFrame();
                if (andLockCurrentStickerFrame != null) {
                    this.C.a(i, j, andLockCurrentStickerFrame.a(), andLockCurrentStickerFrame.b(), andLockCurrentStickerFrame);
                    return;
                }
                this.C.a(i, j, null, 0, null);
            }
        }
    }

    public void a(int i) {
        if (this.F) {
            this.ak.setCurrentProgress(i);
        }
    }

    public void a(Object obj) {
        if (obj != null) {
            ((cn.xiaochuankeji.tieba.ui.videomaker.sticker.c) obj).f();
        }
        this.E = false;
    }

    public void a(SurfaceTexture surfaceTexture) {
        b(surfaceTexture);
    }

    public void b(int i, int i2) {
    }

    public void k() {
        H();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001) {
            if (i2 == -1) {
                setResult(i2, intent);
                finish();
            } else if (i2 == 1) {
                a((h) intent.getParcelableExtra("key_sync_video_info"));
            } else if (i2 == 2) {
                setResult(i2, intent);
                finish();
            }
        } else if (i == 1002 && i2 == -1) {
            String stringExtra = intent.getStringExtra("key_music_cache_path");
            this.J = TextUtils.isEmpty(stringExtra) ? null : new e(stringExtra);
            D();
            this.I = (UgcVideoMusicJson) intent.getSerializableExtra("key_music_info");
        }
    }

    private void L() {
        if (this.R != null) {
            VideoStickerFragment.a(getSupportFragmentManager(), R.id.rootView, this.aC);
        }
    }

    private void M() {
        if (this.R != null) {
            cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment.b.a(getSupportFragmentManager(), R.id.rootView, this.az, this.aD);
        }
    }

    public void a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar) {
        this.Q.setVisibility(4);
        this.an.b();
        boolean z = aVar instanceof TemplatedTextStickerDrawable;
        if ((aVar instanceof TextStickerDrawable) || z) {
            this.at.a(this.av, this.aw);
        } else {
            this.at.a(-1, -1);
        }
    }

    public void b(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar) {
        this.Q.setVisibility(0);
    }

    public void c(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar) {
        this.aC.a();
        this.as.a((Activity) this);
        this.au.setEnabled(true);
        this.au.setVisibility(0);
        if (aVar == null) {
            this.au.a(null);
        } else {
            this.au.a(aVar);
        }
    }

    public void a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar, Object obj) {
        this.Q.setVisibility(0);
        this.au.setVisibility(4);
        this.au.setEnabled(false);
        O();
        if (aVar != null) {
            int width;
            int height;
            int intrinsicWidth = aVar.getIntrinsicWidth();
            int intrinsicHeight = aVar.getIntrinsicHeight();
            if (obj == null) {
                width = (this.at.getWidth() - intrinsicWidth) / 2;
                height = (this.at.getHeight() - intrinsicHeight) / 2;
            } else {
                cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar2 = (cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a) obj;
                aVar.a(aVar2.e());
                aVar.b(aVar2.f());
                Rect bounds = aVar2.getBounds();
                width = bounds.centerX() - (intrinsicWidth / 2);
                height = bounds.centerY() - (intrinsicHeight / 2);
            }
            aVar.setBounds(width, height, intrinsicWidth + width, intrinsicHeight + height);
            this.at.a(aVar);
            N();
        }
        this.as.a();
    }

    private void N() {
        SharedPreferences a = cn.xiaochuankeji.tieba.background.a.a();
        if (!a.getBoolean("key_show_add_text_guide", false)) {
            a.edit().putBoolean("key_show_add_text_guide", true).apply();
            final View findViewById = findViewById(R.id.vTextAddGuideCover);
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ VideoRecordActivity b;

                public void onClick(View view) {
                    findViewById.setVisibility(8);
                }
            });
            LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.addTextAnimationView);
            lottieAnimationView.setImageAssetsFolder("ugc_textadd_anim/images");
            lottieAnimationView.setAnimation("ugc_textadd_anim/shoushisuofang.json");
            lottieAnimationView.b();
        }
    }

    public void a(boolean z, int i, int i2) {
        this.au.a(z, i, i2);
    }

    private void O() {
        this.at.setEnabled(true);
    }

    private void P() {
        this.au.setEnabled(false);
        this.at.setEnabled(false);
    }
}
