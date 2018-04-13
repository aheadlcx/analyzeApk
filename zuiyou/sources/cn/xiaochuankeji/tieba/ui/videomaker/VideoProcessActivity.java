package cn.xiaochuankeji.tieba.ui.videomaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.aop.permission.c;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.post.PostUgcVideo;
import cn.xiaochuankeji.tieba.background.upload.j;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.common.medialib.f;
import cn.xiaochuankeji.tieba.common.medialib.h;
import cn.xiaochuankeji.tieba.common.medialib.i;
import cn.xiaochuankeji.tieba.common.medialib.l;
import cn.xiaochuankeji.tieba.common.medialib.m;
import cn.xiaochuankeji.tieba.json.UgcVideoInfoBean;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicJson;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.mediabrowse.component.RoundProgressBar;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.videomaker.cover.SelectVideoCoverActivity;
import cn.xiaochuankeji.tieba.ui.videomaker.edittext.OverlayEditTextContainer;
import cn.xiaochuankeji.tieba.ui.videomaker.music.SelectVideoMusicActivity;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.b;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.TemplatedTextStickerDrawable;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.TextStickerDrawable;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.fragment.VideoStickerFragment;
import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.fastjson.JSON;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import rx.d;
import rx.d$a;
import tv.danmaku.ijk.media.player.FFmpegMainCaller;
import tv.danmaku.ijk.media.player.FFmpegMainCaller.FFMpegCallback;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;

public class VideoProcessActivity extends a implements Callback, PostUgcVideo.a, d.a, OverlayEditTextContainer.a, b.a {
    private static final org.aspectj.lang.a.a N = null;
    private RoundProgressBar A;
    private TextView B;
    private boolean C;
    private String D;
    private int E = -1;
    private int F = -1;
    private String G;
    private float H;
    private float I;
    private float J;
    private float K;
    private j L;
    private cn.xiaochuankeji.tieba.ui.videomaker.sticker.a M = new cn.xiaochuankeji.tieba.ui.videomaker.sticker.a<cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a>(this) {
        final /* synthetic */ VideoProcessActivity a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.o.setVisibility(8);
        }

        public void b() {
            this.a.o.setVisibility(0);
        }

        public void a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar) {
            if (aVar != null) {
                this.a.o.setVisibility(0);
                this.a.t.setVisibility(4);
                int intrinsicWidth = aVar.getIntrinsicWidth();
                int intrinsicHeight = aVar.getIntrinsicHeight();
                int width = (this.a.u.getWidth() - intrinsicWidth) / 2;
                int height = (this.a.u.getHeight() - intrinsicHeight) / 2;
                aVar.setBounds(width, height, intrinsicWidth + width, intrinsicHeight + height);
                this.a.u.a(aVar);
            }
            this.a.u.setEnabled(true);
        }
    };
    private h a;
    private int b;
    private boolean c;
    private File d;
    private File e;
    private File f;
    private File g;
    private cn.xiaochuankeji.tieba.common.d.a h;
    private cn.xiaochuankeji.tieba.common.d.a i;
    private l j;
    private d k;
    private SurfaceView l;
    private SurfaceHolder m;
    private Bitmap n;
    private View o;
    private View p;
    private View q;
    private View r;
    private ImageView s;
    private OverlayEditTextContainer t;
    private b u;
    private FrameLayout v;
    private FrameLayout w;
    private SoundMixPanel x;
    private volatile int y;
    private RelativeLayout z;

    static {
        B();
    }

    private static void B() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("VideoProcessActivity.java", VideoProcessActivity.class);
        N = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.videomaker.VideoProcessActivity", "android.os.Bundle", "savedInstance", "", "void"), 174);
    }

    public static void a(Activity activity, h hVar, int i, String str) {
        Intent intent = new Intent(activity, VideoProcessActivity.class);
        intent.putExtra("key_video_info", hVar);
        if (str != null) {
            intent.putExtra("key_follow_src", str);
        }
        activity.startActivityForResult(intent, i);
    }

    public static void a(Context context, cn.xiaochuankeji.tieba.ui.videomaker.a.a aVar) {
        Intent intent = new Intent(context, VideoProcessActivity.class);
        intent.putExtra("key_draft_video", aVar);
        context.startActivity(intent);
    }

    static final void a(VideoProcessActivity videoProcessActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        videoProcessActivity.getWindow().addFlags(128);
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(N, this, this, bundle);
        c.c().a(new l(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_video_process;
    }

    protected boolean i() {
        return false;
    }

    public boolean h() {
        return false;
    }

    protected boolean a(Bundle bundle) {
        this.E = e.a(53.0f);
        this.F = e.a(77.0f);
        this.u = new b(this);
        this.u.setDelegate(this);
        this.u.a(this.E, this.F);
        this.u.setVideoAspectRatio(i.a());
        if (bundle != null) {
            this.a = (h) bundle.getParcelable("key_video_info");
            this.D = bundle.getString("key_follow_src", null);
            try {
                this.u.a(new JSONObject(bundle.getString("key_sticker_drawables")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Intent intent = getIntent();
            this.a = (h) intent.getParcelableExtra("key_video_info");
            this.D = intent.getStringExtra("key_follow_src");
            cn.xiaochuankeji.tieba.ui.videomaker.a.a aVar = (cn.xiaochuankeji.tieba.ui.videomaker.a.a) intent.getParcelableExtra("key_draft_video");
            if ((this.a != null && this.a.f > 0) || aVar != null) {
                try {
                    a(aVar);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (this.a != null) {
            return true;
        }
        g.a("数据加载失败");
        return false;
    }

    protected void i_() {
        this.v = (FrameLayout) findViewById(R.id.rootView);
        this.k = new d();
        this.k.a((d.a) this);
        this.l = (SurfaceView) findViewById(R.id.preview_surface);
        this.l.getHolder().addCallback(this);
        LayoutParams layoutParams = this.l.getLayoutParams();
        float a = i.a();
        if (a >= (((float) e.b()) * 1.0f) / ((float) e.c())) {
            layoutParams.height = e.c();
            layoutParams.width = (int) (a * ((float) layoutParams.height));
        } else {
            layoutParams.width = e.b();
            layoutParams.height = (int) (((float) layoutParams.width) / a);
        }
        this.o = findViewById(R.id.layout_control_container);
        findViewById(R.id.btn_back).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.onBackPressed();
            }
        });
        this.p = findViewById(R.id.btn_pick_bgm);
        this.p.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                SelectVideoMusicActivity.a(this.a, 100, this.a.a.h);
            }
        });
        if (this.a.c != null) {
            this.p.setSelected(true);
        }
        findViewById(R.id.btn_pick_sticker).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.v != null) {
                    VideoStickerFragment.a(this.a.getSupportFragmentManager(), R.id.rootView, this.a.M);
                }
            }
        });
        this.q = findViewById(R.id.btn_add_text);
        this.q.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.c(null);
            }
        });
        findViewById(R.id.btn_select_cover).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                JSONObject jSONObject = new JSONObject();
                this.a.u.b(jSONObject);
                SelectVideoCoverActivity.a(this.a, this.a.a.a, jSONObject.toString(), this.a.b, 101);
            }
        });
        this.r = findViewById(R.id.btn_save);
        this.r.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.v();
            }
        });
        this.s = (ImageView) findViewById(R.id.btn_process);
        this.s.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (cn.xiaochuankeji.tieba.ui.auth.a.a(this.a, "ugc_process", 3)) {
                    this.a.w();
                    Map hashMap = new HashMap();
                    hashMap.put("owner", this.a.a.g > 0 ? "review" : "post");
                    cn.xiaochuankeji.tieba.background.utils.monitor.a.b.a().a("publish", "ugcvideo", hashMap);
                }
            }
        });
        if (this.a.b()) {
            this.s.setImageResource(R.drawable.record_next);
        } else {
            this.s.setImageResource(R.drawable.record_post);
        }
        FrameLayout frameLayout = (FrameLayout) this.l.getParent();
        frameLayout.addView(this.u, frameLayout.indexOfChild(this.l) + 1, new FrameLayout.LayoutParams(-1, -1));
        this.t = (OverlayEditTextContainer) findViewById(R.id.overlay_edit_text_container);
        this.t.setListener(this);
        this.z = (RelativeLayout) findViewById(R.id.ll_progress);
        this.A = (RoundProgressBar) findViewById(R.id.roundPBar);
        this.B = (TextView) findViewById(R.id.tv_progress);
        this.w = (FrameLayout) findViewById(R.id.vTextInputGuideCover);
        this.w.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.w.setVisibility(8);
                this.a.q.setVisibility(0);
            }
        });
        this.x = (SoundMixPanel) findViewById(R.id.sound_mix_panel);
        this.x.setListener(new a(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.j();
            }
        });
        findViewById(R.id.btn_mix_sound).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.e();
            }
        });
        if (this.a.b != null && this.a.c == null && this.a.b.b != 100) {
            p();
        } else if (this.a.c != null && this.a.b == null && this.a.c.b != 100) {
            p();
        } else if (!(this.a.b == null || this.a.c == null)) {
            p();
        }
        q();
    }

    private void e() {
        this.c = true;
        u();
        this.x.a(this.a.b, this.a.c);
        this.x.a();
    }

    private void j() {
        boolean z;
        int bgmVolume;
        if (this.a.b != null) {
            int recordVolume = this.x.getRecordVolume();
            if (recordVolume != this.a.b.b) {
                this.a.b.b = recordVolume;
                z = true;
                if (this.a.c != null) {
                    bgmVolume = this.x.getBgmVolume();
                    if (bgmVolume != this.a.c.b) {
                        this.a.c.b = bgmVolume;
                        z = true;
                    }
                }
                this.c = false;
                if (z) {
                    t();
                } else {
                    p();
                }
            }
        }
        z = false;
        if (this.a.c != null) {
            bgmVolume = this.x.getBgmVolume();
            if (bgmVolume != this.a.c.b) {
                this.a.c.b = bgmVolume;
                z = true;
            }
        }
        this.c = false;
        if (z) {
            t();
        } else {
            p();
        }
    }

    private int k() {
        if (this.y <= 0) {
            this.y = f.c(this.a.a);
        }
        return this.y;
    }

    private File a(String str, String str2) {
        return a(new String[]{str}, str2);
    }

    private File a(String[] strArr, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String file : strArr) {
            String file2 = new File(file2).getName();
            int lastIndexOf = file2.lastIndexOf(46);
            if (lastIndexOf >= 0) {
                file2 = file2.substring(0, lastIndexOf);
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.append('_');
            }
            stringBuilder.append(file2);
        }
        return new File(j.c(this.a), stringBuilder + str);
    }

    private void p() {
        this.c = true;
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this, "正在处理中");
        d.a(Boolean.valueOf(true)).d(new rx.b.g<Boolean, Void>(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((Boolean) obj);
            }

            public Void a(Boolean bool) {
                File o;
                e eVar = this.a.a.c;
                if (eVar != null && new File(eVar.a).exists()) {
                    int m = this.a.k();
                    File n = this.a.d;
                    this.a.d = this.a.a(eVar.a, "_d" + m + ".wav");
                    if (!this.a.d.exists() || this.a.d.length() <= 0) {
                        if (n != null) {
                            n.delete();
                        }
                        FFmpegMainCaller.aacToWav(eVar.a, this.a.d.getAbsolutePath(), ((float) m) / 1000.0f);
                    }
                    o = this.a.e;
                    this.a.e = this.a.a(this.a.d.getAbsolutePath(), "_v" + eVar.b + ".wav");
                    if (!this.a.e.exists() || this.a.e.length() <= 0) {
                        if (o != null) {
                            o.delete();
                        }
                        FFmpegMainCaller.changeVolume(this.a.d.getAbsolutePath(), this.a.e.getAbsolutePath(), ((float) eVar.b) / 100.0f);
                    }
                } else if (this.a.e != null) {
                    this.a.e.delete();
                    this.a.e = null;
                }
                eVar = this.a.a.b;
                if (eVar != null && new File(eVar.a).exists()) {
                    o = this.a.f;
                    this.a.f = this.a.a(eVar.a, "_v" + eVar.b + ".wav");
                    if (!this.a.f.exists() || this.a.f.length() <= 0) {
                        if (o != null) {
                            o.delete();
                        }
                        FFmpegMainCaller.changeVolume(eVar.a, this.a.f.getAbsolutePath(), ((float) eVar.b) / 100.0f);
                    }
                } else if (this.a.f != null) {
                    this.a.f.delete();
                    this.a.f = null;
                }
                if (this.a.f != null && this.a.e != null) {
                    File q = this.a.g;
                    this.a.g = this.a.a(new String[]{this.a.f.getAbsolutePath(), this.a.e.getAbsolutePath()}, "_merged.wav");
                    if (!this.a.g.exists() || this.a.g.length() <= 0) {
                        if (q != null) {
                            q.delete();
                        }
                        FFmpegMainCaller.mergeAudio(this.a.f.getAbsolutePath(), this.a.e.getAbsolutePath(), this.a.g.getAbsolutePath());
                    }
                } else if (this.a.g != null) {
                    this.a.g.delete();
                    this.a.g = null;
                }
                return null;
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).a(new rx.e<Void>(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Void) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.c = false;
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                this.a.t();
            }

            public void a(Void voidR) {
                this.a.c = false;
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                this.a.t();
            }
        });
    }

    private void q() {
        SharedPreferences a = cn.xiaochuankeji.tieba.background.a.a();
        if (!a.getBoolean("key_show_input_text_guide", false)) {
            a.edit().putBoolean("key_show_input_text_guide", true).apply();
            this.q.setVisibility(4);
            this.w.setVisibility(0);
            LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.textInputAnimationView);
            lottieAnimationView.setImageAssetsFolder("ugc_textinput_anim/images");
            lottieAnimationView.setAnimation("ugc_textinput_anim/data.json");
            lottieAnimationView.b();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("key_video_info", this.a);
        if (this.D != null) {
            bundle.putString("key_follow_src", this.D);
        }
        JSONObject jSONObject = new JSONObject();
        this.u.b(jSONObject);
        bundle.putString("key_sticker_drawables", jSONObject.toString());
    }

    public void onResume() {
        super.onResume();
        this.x.c();
        t();
    }

    protected void onPause() {
        super.onPause();
        this.x.b();
        u();
    }

    protected void onDestroy() {
        super.onDestroy();
        r();
        if (this.L != null) {
            this.L.a();
        }
        if (this.j != null) {
            this.j.b();
        }
        cn.xiaochuankeji.tieba.ui.videomaker.edittext.a.a();
    }

    private void r() {
        if (this.d != null) {
            this.d.delete();
            this.d = null;
        }
        if (this.e != null) {
            this.e.delete();
            this.e = null;
        }
        if (this.f != null) {
            this.f.delete();
            this.f = null;
        }
        if (this.g != null) {
            this.g.delete();
            this.g = null;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            if (i2 == -1) {
                a(intent.getStringExtra("key_music_cache_path"), (UgcVideoMusicJson) intent.getSerializableExtra("key_music_info"));
            }
        } else if (i == 101) {
            if (i2 == -1) {
                this.b = intent.getIntExtra("key_selected_time", 0);
            }
        } else if (i == 102 && i2 == -1) {
            setResult(i2, intent);
            finish();
        }
    }

    private void a(String str, UgcVideoMusicJson ugcVideoMusicJson) {
        boolean z;
        String str2 = this.a.c == null ? "" : this.a.c.a;
        this.a = this.a.a(ugcVideoMusicJson, str);
        Object obj = this.a.c == null ? "" : this.a.c.a;
        if (this.a.c != null) {
            this.p.setSelected(true);
        } else {
            this.p.setSelected(false);
        }
        if (str2.equals(obj)) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            p();
        }
    }

    public void onBackPressed() {
        if (this.x.isShown()) {
            this.x.d();
        } else if (this.C) {
            if (this.a.b()) {
                g.b("视频合成中，请勿退出");
            } else {
                g.b("视频上传中，请勿退出");
            }
        } else if (getSupportFragmentManager().popBackStackImmediate("V_S_F_sticker", 1)) {
            this.o.setVisibility(0);
        } else {
            Intent intent = new Intent();
            intent.putExtra("key_sync_video_info", this.a);
            setResult(1, intent);
            super.onBackPressed();
        }
    }

    private void b(boolean z) {
        this.q.setEnabled(z);
        findViewById(R.id.btn_save).setEnabled(z);
        this.s.setEnabled(z);
        findViewById(R.id.btn_back).setEnabled(z);
        findViewById(R.id.btn_pick_sticker).setEnabled(z);
        findViewById(R.id.btn_select_cover).setEnabled(z);
        this.t.setEnabled(z);
        this.p.setEnabled(z);
        this.u.setEnabled(z);
        this.C = !z;
        if (this.C) {
            u();
            this.u.f();
            return;
        }
        this.z.setVisibility(8);
        t();
        this.u.g();
    }

    private String s() {
        if (this.g != null) {
            return this.g.getAbsolutePath();
        }
        if (this.e != null) {
            return this.e.getAbsolutePath();
        }
        if (this.f != null) {
            return this.f.getAbsolutePath();
        }
        return null;
    }

    private void t() {
        if (this.m != null) {
            if (this.C || this.c) {
                a(this.m);
                return;
            }
            this.h = new cn.xiaochuankeji.tieba.common.d.a(this);
            this.h.a(this.m);
            this.h.a(this.a.a);
            this.h.a(new IMediaPlayer$OnCompletionListener(this) {
                final /* synthetic */ VideoProcessActivity a;

                {
                    this.a = r1;
                }

                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    if (this.a.h != null) {
                        this.a.h.seekTo(0);
                        this.a.h.start();
                        if (this.a.i != null) {
                            this.a.i.seekTo(0);
                            this.a.i.start();
                        }
                    }
                }
            });
            this.h.start();
            String s = s();
            if (TextUtils.isEmpty(s)) {
                s = this.a.c();
            }
            if (!TextUtils.isEmpty(s)) {
                this.i = new cn.xiaochuankeji.tieba.common.d.a(this);
                this.i.a(s);
                this.i.start();
            }
        }
    }

    private void a(SurfaceHolder surfaceHolder) {
        Canvas lockCanvas = surfaceHolder.lockCanvas();
        if (this.n == null) {
            this.n = BitmapFactory.decodeFile(this.a.d);
        }
        lockCanvas.drawBitmap(this.n, new Rect(0, 0, this.n.getWidth(), this.n.getHeight()), new Rect(0, 0, e.b(), e.c()), null);
        surfaceHolder.unlockCanvasAndPost(lockCanvas);
    }

    private void u() {
        if (this.h != null) {
            this.h.f();
            this.h.g();
            this.h = null;
        }
        if (this.i != null) {
            this.i.f();
            this.i.g();
            this.i = null;
        }
    }

    private void a(cn.xiaochuankeji.tieba.ui.videomaker.a.a aVar) throws JSONException {
        Pair pair;
        cn.xiaochuankeji.tieba.ui.videomaker.a.b bVar = new cn.xiaochuankeji.tieba.ui.videomaker.a.b();
        if (aVar != null) {
            Pair a = bVar.a(aVar);
            this.a = (h) a.first;
            pair = a;
        } else {
            pair = bVar.b(this.a.f, this.a.g);
        }
        this.u.a((JSONObject) pair.second);
    }

    private void v() {
        u();
        cn.xiaochuankeji.tieba.ui.videomaker.a.b bVar = new cn.xiaochuankeji.tieba.ui.videomaker.a.b();
        try {
            JSONObject jSONObject = new JSONObject();
            this.u.b(jSONObject);
            this.a = bVar.a(this.a, jSONObject);
            j.d();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setResult(2, null);
        finish();
        g.a("已保存至草稿箱");
    }

    private void w() {
        b(false);
        boolean z = this.u.a() > 0;
        if (this.a.b()) {
            if (z) {
                a("合成中", 0.8f, 0.2f, 0.0f, 0.0f);
            } else {
                a("合成中", 0.0f, 1.0f, 0.0f, 0.0f);
            }
        } else if (z) {
            a("上传中", 0.4f, 0.1f, 0.4f, 0.1f);
        } else {
            a("上传中", 0.0f, 0.1f, 0.7f, 0.2f);
        }
        if (z) {
            y();
        } else {
            c(this.a.a);
        }
    }

    private boolean x() {
        Iterator it = this.u.b().iterator();
        while (it.hasNext()) {
            if (((cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a) it.next()).a()) {
                return true;
            }
        }
        return false;
    }

    private void y() {
        String str = this.a.a.substring(0, this.a.a.length() - 4) + "_processed.mp4";
        new File(str).delete();
        if (j.b().f && (m.g() || x())) {
            a(str);
        } else {
            b(str);
        }
    }

    private void a(h hVar) {
        if (this.a.b()) {
            MainVideoPublishActivity.a(this, hVar, z(), 102);
            b(true);
            return;
        }
        b(hVar.a, hVar.d);
    }

    private void a(final String str) {
        if (this.j != null) {
            this.j.a();
        }
        this.j = new l(new i(j.b()));
        JSONObject jSONObject = new JSONObject();
        this.u.b(jSONObject);
        final cn.xiaochuankeji.tieba.ui.videomaker.sticker.d a = cn.xiaochuankeji.tieba.ui.videomaker.sticker.d.a(this, jSONObject);
        this.j.a(new h(this) {
            final /* synthetic */ VideoProcessActivity b;

            public void a(int i, cn.xiaochuankeji.tieba.common.medialib.g gVar) {
                cn.xiaochuankeji.tieba.ui.videomaker.sticker.c a = a.a(i);
                gVar.a = a.a();
                gVar.b = a.b();
            }
        });
        this.j.a(new l.a(this) {
            final /* synthetic */ VideoProcessActivity b;

            public void a(int i) {
                this.b.a(i, 0);
            }

            public void a(String str) {
                this.b.c(str);
            }

            public void a() {
                new File(str).delete();
                this.b.b(str);
            }
        });
        this.j.a(this.a.a, str);
    }

    private void b(final String str) {
        final cn.xiaochuankeji.tieba.ui.videomaker.sticker.c andLockCurrentStickerFrame = this.u.getAndLockCurrentStickerFrame();
        if (andLockCurrentStickerFrame == null) {
            c(this.a.a);
        } else {
            d.a(new d$a<Void>(this) {
                final /* synthetic */ VideoProcessActivity c;

                public /* synthetic */ void call(Object obj) {
                    a((rx.j) obj);
                }

                public void a(rx.j<? super Void> jVar) {
                    jVar.onStart();
                    String str = new File(this.c.a.a).getParent() + File.separator + "overlay.png";
                    new File(str).delete();
                    cn.xiaochuankeji.tieba.d.b.a(andLockCurrentStickerFrame.a(), str);
                    this.c.a(this.c.a.a, f.a(this.c.a.a), f.b(this.c.a.a), str, str);
                    jVar.onCompleted();
                }
            }).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new rx.j<Void>(this) {
                final /* synthetic */ VideoProcessActivity c;

                public /* synthetic */ void onNext(Object obj) {
                    a((Void) obj);
                }

                public void onCompleted() {
                    andLockCurrentStickerFrame.f();
                    this.c.c(str);
                }

                public void onError(Throwable th) {
                    this.c.b(true);
                }

                public void onStart() {
                }

                public void a(Void voidR) {
                }
            });
        }
    }

    private void a(String str, int i, int i2, String str2, String str3) {
        if (cn.htjyb.c.a.b.c(str) && cn.htjyb.c.a.b.c(str2)) {
            FFmpegMainCaller fFmpegMainCaller = new FFmpegMainCaller();
            fFmpegMainCaller.setFFMpegCallback(new FFMpegCallback(this) {
                final /* synthetic */ VideoProcessActivity a;

                {
                    this.a = r1;
                }

                public void onFrame(int i) {
                    int i2 = 0;
                    float m = ((float) this.a.k()) / 1000.0f;
                    if (m > 0.0f) {
                        i2 = (int) (((((float) i) * 1.0f) / (m * 30.0f)) * 100.0f);
                    }
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass11 b;

                        public void run() {
                            this.b.a.a(i2, 0);
                        }
                    });
                }
            });
            fFmpegMainCaller.overlayPng(str, i, i2, str2, str3);
        }
    }

    private void a(String str, int i, String str2) {
        new File(str2).delete();
        Bitmap copy = f.a(str, i).copy(Config.ARGB_8888, true);
        f.a(copy, CompressFormat.JPEG, 100, str2);
        copy.recycle();
    }

    private void c(final String str) {
        d.a(new d$a<Pair<Integer, h>>(this) {
            final /* synthetic */ VideoProcessActivity b;

            public /* synthetic */ void call(Object obj) {
                a((rx.j) obj);
            }

            public void a(rx.j<? super Pair<Integer, h>> jVar) {
                String str;
                jVar.onStart();
                jVar.onNext(new Pair(Integer.valueOf(0), null));
                String str2 = new File(this.b.a.d).getParent() + File.separator + "cover_final_" + System.currentTimeMillis() + ".jpg";
                this.b.a(str, this.b.b, str2);
                jVar.onNext(new Pair(Integer.valueOf(30), null));
                Object v = this.b.s();
                if (TextUtils.isEmpty(v)) {
                    v = this.b.a.c();
                }
                if (!TextUtils.isEmpty(v) && v.endsWith(".wav")) {
                    str = v.substring(0, v.length() - 4) + ".aac";
                    FFmpegMainCaller.wavToAac(v, str);
                    v = str;
                }
                jVar.onNext(new Pair(Integer.valueOf(60), null));
                str = str;
                if (!TextUtils.isEmpty(v)) {
                    str = str.substring(0, str.length() - 4) + "_final.mp4";
                    FFmpegMainCaller.mergeAVSource(str, v, str, ((float) this.b.k()) / 1000.0f, false);
                }
                jVar.onNext(new Pair(Integer.valueOf(100), this.b.a.a().a(str).b(str2).a()));
                jVar.onCompleted();
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new rx.j<Pair<Integer, h>>(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Pair) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.b(true);
            }

            public void onStart() {
            }

            public void a(Pair<Integer, h> pair) {
                this.a.a(((Integer) pair.first).intValue(), 1);
                if (((Integer) pair.first).intValue() == 100) {
                    this.a.a((h) pair.second);
                }
            }
        });
    }

    private String[] z() {
        ArrayList a = this.u.a(TextStickerDrawable.class);
        Collection a2 = this.u.a(TemplatedTextStickerDrawable.class);
        if (a2 != null && a2.size() > 0) {
            a.addAll(a2);
        }
        if (a == null || a.size() == 0) {
            return null;
        }
        Collections.sort(a, new Comparator<cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a>(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a) obj, (cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a) obj2);
            }

            public int a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar, cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar2) {
                long d = aVar.d();
                long d2 = aVar2.d();
                if (d == d2) {
                    return 0;
                }
                return d > d2 ? 1 : -1;
            }
        });
        String[] strArr = new String[a.size()];
        for (int i = 0; i < a.size(); i++) {
            cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar = (cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a) a.get(i);
            if (aVar instanceof TextStickerDrawable) {
                strArr[i] = ((TextStickerDrawable) aVar).k().toString();
            }
            if (aVar instanceof TemplatedTextStickerDrawable) {
                strArr[i] = ((TemplatedTextStickerDrawable) aVar).k().toString();
            }
        }
        return strArr;
    }

    private void b(String str, String str2) {
        this.L = new j();
        new PostUgcVideo(str, str2, z(), this.a.g, this.a.h, this.a.d(), this.D, this.L).a((PostUgcVideo.a) this, new cn.xiaochuankeji.tieba.background.upload.b(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public void a(long j, long j2, int i) {
                this.a.a((int) (((((float) j2) * 1.0f) / ((float) j)) * 100.0f), 2);
            }
        }, new cn.xiaochuankeji.tieba.background.upload.f(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public void a(List<Long> list, List<Long> list2, HashMap<String, LocalMedia> hashMap) {
            }

            public void a(String str) {
            }
        });
        this.L.a(new cn.xiaochuankeji.tieba.background.upload.b(this) {
            final /* synthetic */ VideoProcessActivity a;

            {
                this.a = r1;
            }

            public void a(long j, long j2, int i) {
                final long j3 = j2;
                final long j4 = j;
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass18 c;

                    public void run() {
                        this.c.a.a((int) (((((float) j3) * 1.0f) / ((float) j4)) * 100.0f), 3);
                    }
                });
            }
        });
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.m = surfaceHolder;
        t();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.m = null;
        u();
    }

    public void a(boolean z, int i, int i2) {
        this.t.a(z, i, i2);
    }

    public void a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar) {
        this.o.setVisibility(4);
        boolean z = aVar instanceof TemplatedTextStickerDrawable;
        if ((aVar instanceof TextStickerDrawable) || z) {
            this.u.a(this.E, this.F);
        } else {
            this.u.a(-1, -1);
        }
    }

    public void b(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar) {
        this.o.setVisibility(0);
    }

    public void c(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar) {
        this.k.a((Activity) this);
        this.o.setVisibility(4);
        this.t.setVisibility(0);
        if (aVar == null) {
            this.t.a(null);
        } else {
            this.t.a(aVar);
        }
    }

    public void a(cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar, Object obj) {
        this.o.setVisibility(0);
        this.t.setVisibility(4);
        this.u.setEnabled(true);
        if (aVar != null) {
            int width;
            int height;
            int intrinsicWidth = aVar.getIntrinsicWidth();
            int intrinsicHeight = aVar.getIntrinsicHeight();
            if (obj == null) {
                width = (this.u.getWidth() - intrinsicWidth) / 2;
                height = (this.u.getHeight() - intrinsicHeight) / 2;
            } else {
                cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a aVar2 = (cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a) obj;
                aVar.a(aVar2.e());
                aVar.b(aVar2.f());
                Rect bounds = aVar2.getBounds();
                width = bounds.centerX() - (intrinsicWidth / 2);
                height = bounds.centerY() - (intrinsicHeight / 2);
            }
            aVar.setBounds(width, height, intrinsicWidth + width, intrinsicHeight + height);
            this.u.a(aVar);
            A();
        }
        this.k.a();
    }

    private void A() {
        SharedPreferences a = cn.xiaochuankeji.tieba.background.a.a();
        if (!a.getBoolean("key_show_add_text_guide", false)) {
            a.edit().putBoolean("key_show_add_text_guide", true).apply();
            final View findViewById = findViewById(R.id.vTextAddGuideCover);
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ VideoProcessActivity b;

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

    public void a(boolean z, final String str, UgcVideoInfoBean ugcVideoInfoBean) {
        if (z) {
            j.d();
            j.a(this.a);
            g.a("发布成功");
            Intent intent = new Intent();
            intent.putExtra("key_published_video", JSON.toJSONString(ugcVideoInfoBean));
            setResult(-1, intent);
            if (this.a.f > 0) {
                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.background.d.i());
            }
            finish();
            return;
        }
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ VideoProcessActivity b;

            public void run() {
                g.a(str);
                this.b.b(true);
            }
        });
    }

    private void a(String str, float f, float f2, float f3, float f4) {
        this.G = str;
        this.H = f;
        this.I = f2;
        this.J = f3;
        this.K = f4;
        this.z.setVisibility(0);
        this.A.setMax(100);
        this.A.setProgress(0);
        this.B.setText(str + "0%");
    }

    private void a(int i, int i2) {
        int i3 = 0;
        if (i2 == 0) {
            i3 = (int) (((float) i) * this.H);
        } else if (i2 == 1) {
            i3 = (int) ((((float) i) * this.I) + (this.H * 100.0f));
        } else if (i2 == 2) {
            i3 = (int) ((((float) i) * this.J) + ((this.I + this.H) * 100.0f));
        } else if (i2 == 3) {
            i3 = (int) ((((float) i) * this.K) + (((this.J + this.I) + this.H) * 100.0f));
        }
        i3 = Math.min(i3, 100);
        if (i3 > this.A.getProgress()) {
            this.A.setProgress(i3);
            this.B.setText(this.G + i3 + "%");
        }
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void videoProgress(c cVar) {
        a(cVar.b, cVar.a);
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_UPLOAD_PROGRESS) {
            cn.xiaochuankeji.tieba.background.upload.g.b bVar = (cn.xiaochuankeji.tieba.background.upload.g.b) messageEvent.getData();
            int i = (int) (((((float) bVar.c) * 1.0f) / ((float) bVar.b)) * 100.0f);
            if (bVar.a == 0) {
                a(i, 2);
            } else if (bVar.a == 1) {
                a(i, 3);
            }
        }
    }
}
