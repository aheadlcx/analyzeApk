package com.budejie.www.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.budejie.www.R;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.goddubbing.c.d;
import com.budejie.www.goddubbing.c.e;
import com.budejie.www.goddubbing.c.f;
import com.budejie.www.goddubbing.c.g;
import com.budejie.www.goddubbing.wediget.RecordSeekBar;
import com.budejie.www.goddubbing.wediget.VideoLayout;
import com.budejie.www.util.aa;
import com.budejie.www.util.ac;
import com.budejie.www.util.an;
import com.budejie.www.util.h;
import com.budejie.www.widget.VoiceLineView;
import com.bumptech.glide.i;
import com.tencent.connect.common.Constants;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GodDubbingActivity extends Activity implements OnClickListener, com.budejie.www.goddubbing.a.a, com.budejie.www.goddubbing.wediget.RecordSeekBar.a {
    private int A;
    private int B = 0;
    private int C = 0;
    private int D;
    private String E;
    private String F;
    private boolean G = true;
    private boolean H;
    private boolean I = false;
    private boolean J;
    private b K;
    private Observer<Integer> L;
    private Observer<Integer> M;
    private com.budejie.www.goddubbing.a N;
    private ListItemObject O;
    private a P;
    private com.budejie.www.goddubbing.a.a Q;
    private c R;
    private com.budejie.www.goddubbing.c.b S;
    private Observer<Boolean> T;
    private Bitmap U;
    private ImageView a;
    private ImageView b;
    private ImageView c;
    private ImageView d;
    private ImageView e;
    private ImageView f;
    private ImageView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private VideoView l;
    private ProgressBar m;
    private RelativeLayout n;
    private RelativeLayout o;
    private VoiceLineView p;
    private VoiceLineView q;
    private RecordSeekBar r;
    private Dialog s;
    private Dialog t;
    private View u;
    private List<String> v;
    private List<Integer> w;
    private Map<Integer, String> x;
    private Map<Integer, Integer> y;
    private Map<Integer, Integer> z;

    private enum a {
        NO_INTERNET_CONNECT,
        DOWNLOAD_VIDEO,
        DO_ASYNC_TASK,
        DOWNLOAD_IMAGE,
        CANCEL_LOAD,
        PARSE_FAILED
    }

    private enum b {
        ON_RECORD,
        PAUSE_RECORD,
        FINISH_RECORD
    }

    public static class c extends Handler {
        WeakReference<GodDubbingActivity> a;

        c(GodDubbingActivity godDubbingActivity) {
            this.a = new WeakReference(godDubbingActivity);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            GodDubbingActivity godDubbingActivity = (GodDubbingActivity) this.a.get();
            if (godDubbingActivity != null) {
                switch (message.what) {
                    case 2:
                        godDubbingActivity.D = godDubbingActivity.D + 1;
                        if (godDubbingActivity.D >= 5) {
                            godDubbingActivity.e.setImageResource(R.drawable.god_dubbing_complete_selector);
                            godDubbingActivity.e.setClickable(true);
                        }
                        godDubbingActivity.G();
                        godDubbingActivity.R.sendEmptyMessageDelayed(2, 1000);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_god_dubbing);
        d.a((Context) this);
        k();
        i();
        j();
        if (d()) {
            this.r.setCallBack(this);
            this.K = b.PAUSE_RECORD;
            this.e.setClickable(false);
            this.R = new c(this);
            e();
            g.a().a(this.R);
            g.a().a(this.p, this.q);
            G();
            return;
        }
        a(getString(R.string.nonet));
        this.P = a.NO_INTERNET_CONNECT;
    }

    private boolean d() {
        if (!an.a((Context) this)) {
            return false;
        }
        an.E(this);
        return true;
    }

    private void e() {
        Intent intent = getIntent();
        if (intent != null) {
            this.O = (ListItemObject) intent.getSerializableExtra("dubbing_key");
            if (this.O != null) {
                String type = this.O.getType();
                if (!TextUtils.isEmpty(type)) {
                    Object obj = -1;
                    switch (type.hashCode()) {
                        case 1567:
                            if (type.equals(Constants.VIA_REPORT_TYPE_SHARE_TO_QQ)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 1661:
                            if (type.equals("41")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            if ("1".equals(this.O.getIs_gif())) {
                                g();
                                return;
                            } else {
                                f();
                                return;
                            }
                        case 1:
                            h();
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }

    private void f() {
        this.P = a.DOWNLOAD_IMAGE;
        this.m.setProgress(70);
        this.a.setVisibility(8);
        this.T = new Observer<Boolean>(this) {
            final /* synthetic */ GodDubbingActivity a;
            private Disposable b;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Boolean) obj);
            }

            public void onComplete() {
                this.b.dispose();
                this.a.T = null;
            }

            public void onError(Throwable th) {
                this.b.dispose();
                this.a.T = null;
                if (!TextUtils.isEmpty(th.getMessage())) {
                    aa.e("MainActivity", th.getMessage());
                }
            }

            public void onSubscribe(@NonNull Disposable disposable) {
                this.b = disposable;
            }

            public void a(Boolean bool) {
                if (a.CANCEL_LOAD == this.a.P) {
                    this.a.finish();
                } else if (bool.booleanValue() && this.a.U != null) {
                    this.a.F = d.o();
                    this.a.m.setProgress(100);
                    this.a.t.dismiss();
                    this.a.d.setImageBitmap(this.a.U);
                    this.a.A = 15000;
                    this.a.A();
                }
            }
        };
        Observable.just(Integer.valueOf(1)).map(new Function<Integer, Boolean>(this) {
            final /* synthetic */ GodDubbingActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object apply(Object obj) throws Exception {
                return a((Integer) obj);
            }

            public Boolean a(Integer num) {
                boolean a;
                try {
                    this.a.U = (Bitmap) i.a(this.a).a(com.lt.a.a(this.a).a(this.a.O.getImgUrl())).j().c(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
                    this.a.R.sendEmptyMessage(3);
                    a = h.a(this.a.U, new File(d.o()), CompressFormat.JPEG, 100);
                } catch (Throwable e) {
                    this.a.T.onError(e);
                    a = false;
                }
                return Boolean.valueOf(a);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this.T);
    }

    private void g() {
        i.a((Activity) this).a(this.O.getGifFistFrame()).a(this.d);
        this.Q = new com.budejie.www.goddubbing.a.a(this, this.O, new com.budejie.www.goddubbing.a.a.a(this) {
            final /* synthetic */ GodDubbingActivity a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                if (a.CANCEL_LOAD == this.a.P) {
                    this.a.m();
                } else if (i2 != 0) {
                    this.a.P = a.PARSE_FAILED;
                } else {
                    String c = d.c();
                    if (d.d(c)) {
                        this.a.E = c;
                        this.a.A = d.e(this.a.E);
                        if (this.a.A <= 0) {
                            this.a.P = a.PARSE_FAILED;
                            return;
                        }
                        this.a.A();
                        this.a.m.setProgress(100);
                        if (this.a.t != null) {
                            this.a.t.dismiss();
                            return;
                        }
                        return;
                    }
                    this.a.P = a.PARSE_FAILED;
                }
            }

            public void a(int i) {
                this.a.m.setProgress(i);
            }
        });
        this.P = a.DO_ASYNC_TASK;
        this.Q.execute(new Void[0]);
    }

    private void h() {
        this.P = a.DOWNLOAD_VIDEO;
        i.a((Activity) this).a(com.lt.a.a((Context) this).a(this.O.getImgUrl())).a(this.d);
        this.N = new com.budejie.www.goddubbing.a(this, this.m, this.O);
        this.N.a((com.budejie.www.goddubbing.a.a) this);
        this.N.f();
    }

    private void i() {
        if (this.u == null) {
            this.u = LayoutInflater.from(this).inflate(R.layout.god_dubbing_load_video_view, null);
        }
        this.m = (ProgressBar) this.u.findViewById(R.id.progress_bar);
        this.k = (TextView) this.u.findViewById(R.id.cancel_text_view);
        if (this.t == null) {
            this.t = new Dialog(this, R.style.dialog);
            this.t.setContentView(this.u);
        }
        this.m.setMax(100);
        this.t.setCanceledOnTouchOutside(false);
        this.t.show();
        this.t.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ GodDubbingActivity a;

            {
                this.a = r1;
            }

            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 4;
            }
        });
    }

    private void j() {
        this.l.setOnClickListener(this);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.b.setClickable(false);
        this.c.setOnClickListener(this);
        this.e.setOnClickListener(this);
        if (this.k != null) {
            this.k.setOnClickListener(this);
        }
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
    }

    private void k() {
        this.b = (ImageView) findViewById(R.id.undo_image_view);
        this.c = (ImageView) findViewById(R.id.record_image_view);
        this.e = (ImageView) findViewById(R.id.complete_image_view);
        this.g = (ImageView) findViewById(R.id.rerecord_image_view);
        this.h = (TextView) findViewById(R.id.record_text_view);
        this.r = (RecordSeekBar) findViewById(R.id.record_seek_bar);
        VideoLayout videoLayout = (VideoLayout) findViewById(R.id.video_layout);
        this.o = (RelativeLayout) findViewById(R.id.countdown_view);
        this.j = (TextView) findViewById(R.id.count_down_text_view);
        this.f = (ImageView) findViewById(R.id.back_image_view);
        this.l = videoLayout.getVideoView();
        this.d = videoLayout.getDefaultImageView();
        this.a = videoLayout.getPreviewImageView();
        this.p = (VoiceLineView) findViewById(R.id.left_voice_view);
        this.q = (VoiceLineView) findViewById(R.id.right_voice_view);
        this.n = (RelativeLayout) findViewById(R.id.voice_layout);
        this.i = (TextView) findViewById(R.id.time_text_view);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_image_view:
                finish();
                return;
            case R.id.complete_image_view:
                r();
                return;
            case R.id.undo_image_view:
                n();
                return;
            case R.id.rerecord_image_view:
                o();
                return;
            case R.id.record_image_view:
                if (!com.budejie.www.goddubbing.c.c.a()) {
                    t();
                    return;
                }
                return;
            case R.id.cancel_text_view:
                l();
                return;
            case R.id.preview_image_view:
                a(this.E, null, 0, this.A);
                return;
            default:
                return;
        }
    }

    private void a(String str) {
        an.a((Activity) this, str, -1).show();
    }

    private void l() {
        if (a.NO_INTERNET_CONNECT == this.P) {
            finish();
        } else if (a.DOWNLOAD_VIDEO == this.P) {
            if (this.N != null) {
                this.N.a();
            }
            m();
        } else if (a.PARSE_FAILED == this.P) {
            finish();
        } else if (a.DOWNLOAD_IMAGE == this.P) {
            finish();
        } else {
            this.P = a.CANCEL_LOAD;
            if (this.S == null) {
                this.S = new com.budejie.www.goddubbing.c.b();
            }
            new Thread(this.S).start();
        }
    }

    private void m() {
        if (this.N != null) {
            this.N.g();
        }
        if (this.t != null) {
            this.t.dismiss();
        }
        finish();
    }

    private void n() {
        if (e.a(this.x)) {
            this.r.d();
            this.r.setVisibility(8);
            this.d.setVisibility(0);
            this.n.setVisibility(4);
            this.b.setClickable(false);
            this.b.setImageResource(R.drawable.god_dubbing_undo_cannot_click);
        } else if (this.G) {
            this.r.b();
            this.G = false;
        } else {
            this.K = b.PAUSE_RECORD;
            this.h.setText(getString(R.string.press_to_record));
            this.c.setBackgroundResource(R.drawable.god_dubbing_record_image);
            this.c.setImageResource(R.drawable.god_dubbing_record_image);
            this.g.setVisibility(4);
            this.a.setClickable(true);
            this.d.setVisibility(0);
            B();
        }
    }

    private void o() {
        E();
        this.D = 0;
        this.i.setText(ac.a((long) (this.D * 1000)));
        this.e.setImageResource(R.drawable.god_dubbing_gray_complete);
        this.e.setClickable(false);
        this.c.setImageResource(R.drawable.god_dubbing_record_image);
        this.c.setBackgroundResource(R.drawable.god_dubbing_record_image);
        this.K = b.PAUSE_RECORD;
        this.g.setVisibility(4);
        this.b.setClickable(false);
        this.b.setImageResource(R.drawable.god_dubbing_undo_cannot_click);
        this.h.setText(R.string.press_to_record);
        this.r.d();
        this.r.setVisibility(8);
        this.n.setVisibility(4);
        this.d.setVisibility(0);
        if (!e.a(this.x)) {
            this.x.clear();
        }
        if (!e.a(this.z)) {
            this.z.clear();
        }
        if (!e.a(this.z)) {
            this.z.clear();
        }
        if (!e.a(this.y)) {
            this.y.clear();
        }
        this.C = 0;
        this.G = true;
        this.B = 0;
        this.I = false;
        A();
    }

    private void p() {
        this.H = false;
        this.L = new Observer<Integer>(this) {
            Disposable a;
            final /* synthetic */ GodDubbingActivity b;

            {
                this.b = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Integer) obj);
            }

            public void onComplete() {
                this.a.dispose();
                this.b.L = null;
            }

            public void onError(Throwable th) {
                if (this.b.s != null && this.b.s.isShowing()) {
                    this.b.s.dismiss();
                }
                this.b.a(this.b.getString(R.string.failed_to_mix_video));
                this.a.dispose();
                this.b.L = null;
                if (!TextUtils.isEmpty(th.getMessage())) {
                    aa.e("MainActivity", th.getMessage());
                }
            }

            public void onSubscribe(@NonNull Disposable disposable) {
                this.a = disposable;
            }

            public void a(Integer num) {
                if (this.b.s != null && this.b.s.isShowing()) {
                    this.b.s.dismiss();
                }
                if (!this.b.H) {
                    String h = d.h();
                    if (num.intValue() != 0 || d.e(h) <= 0) {
                        this.b.a(this.b.getString(R.string.failed_to_mix_video));
                    } else {
                        this.b.s();
                    }
                }
            }
        };
        Observable.just(Integer.valueOf(1)).map(new Function<Integer, Integer>(this) {
            final /* synthetic */ GodDubbingActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object apply(Object obj) throws Exception {
                return a((Integer) obj);
            }

            public Integer a(Integer num) {
                String str;
                String h;
                Integer valueOf;
                String str2 = "";
                if (!e.a(this.a.x)) {
                    if (this.a.x.size() == 1) {
                        str = (String) this.a.x.get(Integer.valueOf(this.a.C));
                        h = d.h();
                        if (d.d(h)) {
                            d.c(h);
                        }
                        valueOf = Integer.valueOf(-1);
                        if (a.DOWNLOAD_IMAGE != this.a.P && this.a.U != null) {
                            return (TextUtils.isEmpty(h) || !d.d(this.a.F) || d.e(str) <= 0) ? valueOf : Integer.valueOf(com.budejie.www.goddubbing.c.i.a(this.a.F, h, str));
                        } else {
                            if (!TextUtils.isEmpty(h) || d.e(this.a.E) <= 0 || d.e(str) <= 0 || e.a(this.a.y) || !this.a.y.containsKey(Integer.valueOf(this.a.C))) {
                                return valueOf;
                            }
                            return Integer.valueOf(com.budejie.www.goddubbing.c.i.a(this.a.E, str, h, 0.0f, (float) (((Integer) this.a.y.get(Integer.valueOf(this.a.C))).intValue() / 1000)));
                        }
                    }
                    str2 = d.l();
                    if (d.d(str2)) {
                        d.c(str2);
                    }
                    if (!d.a(this.a.x)) {
                        return num;
                    }
                    com.budejie.www.goddubbing.c.i.a(d.j(), str2);
                }
                str = str2;
                h = d.h();
                if (d.d(h)) {
                    d.c(h);
                }
                valueOf = Integer.valueOf(-1);
                if (a.DOWNLOAD_IMAGE != this.a.P) {
                }
                return !TextUtils.isEmpty(h) ? valueOf : valueOf;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this.L);
    }

    private void q() {
        if (this.s == null) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.send_comment_dialog_layout, null);
            ((TextView) inflate.findViewById(R.id.send_comment_dialog_content)).setText(R.string.mix_video_prompt);
            this.s = new Dialog(this, R.style.dialogTheme);
            this.s.setContentView(inflate);
        }
        this.s.setCanceledOnTouchOutside(false);
        this.s.show();
        this.s.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ GodDubbingActivity a;

            {
                this.a = r1;
            }

            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4) {
                    this.a.H = true;
                    if (this.a.S == null) {
                        this.a.S = new com.budejie.www.goddubbing.c.b();
                    }
                    new Thread(this.a.S).start();
                }
                return true;
            }
        });
    }

    public void a() {
        this.E = d.g();
        if (d.d(this.E)) {
            this.A = d.e(this.E);
            if (this.A > 0) {
                A();
                if (this.t != null) {
                    this.t.dismiss();
                }
            }
        }
    }

    private void r() {
        if (b.ON_RECORD == this.K) {
            z();
        }
        q();
        p();
    }

    private void s() {
        String h = d.h();
        if (d.e(h) > 0) {
            Intent intent = new Intent();
            intent.putExtra("VideoPathTag", h);
            if (!e.a(this.y) && this.y.containsKey(Integer.valueOf(this.C))) {
                intent.putExtra("VideoTimeTag", ((Integer) this.y.get(Integer.valueOf(this.C))).intValue() / 1000);
            }
            setResult(3, intent);
            finish();
        }
    }

    private void t() {
        switch (this.K) {
            case ON_RECORD:
                z();
                return;
            case PAUSE_RECORD:
                if (f.a()) {
                    x();
                    return;
                } else {
                    a(getString(R.string.please_open_audio_permission));
                    return;
                }
            case FINISH_RECORD:
                u();
                return;
            default:
                return;
        }
    }

    private void u() {
        if (!e.a(this.x)) {
            v();
            a(this.E, this.v, 0, this.A);
        }
    }

    private void v() {
        if (this.v == null) {
            this.v = new ArrayList();
        }
        this.v.clear();
        for (String str : this.x.values()) {
            if (d.d(str)) {
                this.v.add(str);
            }
        }
    }

    private void a(String str, List<String> list, int i, int i2) {
        if (a.DOWNLOAD_IMAGE != this.P) {
            if (!d.d(str)) {
                return;
            }
        } else if (!d.d(this.F)) {
            return;
        }
        Intent intent = new Intent(this, DubbingPreviewActivity.class);
        intent.putExtra("VideoPathTag", str);
        intent.putExtra("RecorderPathTag", (Serializable) list);
        intent.putExtra("ImagePathTag", this.F);
        intent.putExtra("VideoCutStartTime", i);
        intent.putExtra("VideoCutEndTime", i2);
        startActivity(intent);
    }

    private void w() {
        if (!e.a(this.z) && this.z.containsKey(Integer.valueOf(this.C))) {
            this.r.a(((Integer) this.z.get(Integer.valueOf(this.C))).intValue());
        }
        if (!e.a(this.y) && this.y.containsKey(Integer.valueOf(this.C))) {
            this.B = ((Integer) this.y.get(Integer.valueOf(this.C))).intValue();
        }
        this.C++;
        this.G = true;
        b();
        this.h.setText(getString(R.string.on_record));
        this.r.setVisibility(0);
        this.n.setVisibility(0);
        this.a.setClickable(false);
        this.b.setClickable(false);
        this.a.setVisibility(8);
        this.b.setImageResource(R.drawable.god_dubbing_undo_cannot_click);
        this.c.setImageResource(R.drawable.god_dubbing_record_first_frame);
        this.c.setBackgroundResource(R.drawable.god_dubbing_record_animation);
        ((AnimationDrawable) this.c.getBackground()).start();
        String a = d.a(com.budejie.www.goddubbing.b.a.d + this.C + ".mp4");
        g.a().a(a);
        if (this.x == null) {
            this.x = new LinkedHashMap();
        }
        this.x.put(Integer.valueOf(this.C), a);
        this.K = b.ON_RECORD;
    }

    private void x() {
        this.J = false;
        this.o.setVisibility(0);
        this.j.setText("3");
        y();
    }

    private void y() {
        this.M = new Observer<Integer>(this) {
            Disposable a;
            final /* synthetic */ GodDubbingActivity b;

            {
                this.b = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Integer) obj);
            }

            public void onComplete() {
                this.a.dispose();
                this.b.M = null;
            }

            public void onError(Throwable th) {
                this.a.dispose();
                this.b.M = null;
                if (!TextUtils.isEmpty(th.getMessage())) {
                    aa.e("MainActivity", th.getMessage());
                }
            }

            public void onSubscribe(@NonNull Disposable disposable) {
                this.a = disposable;
            }

            public void a(Integer num) {
                if (num.intValue() == 0) {
                    this.b.o.setVisibility(8);
                    if (!this.b.J) {
                        this.b.w();
                    } else {
                        return;
                    }
                }
                if (num.intValue() != 0) {
                    this.b.j.setText(String.valueOf(num));
                }
            }
        };
        if (com.budejie.www.goddubbing.c.a.a(this.w)) {
            this.w = new ArrayList();
            this.w.add(Integer.valueOf(2));
            this.w.add(Integer.valueOf(1));
            this.w.add(Integer.valueOf(0));
        }
        Observable.fromIterable(this.w).map(new Function<Integer, Integer>(this) {
            final /* synthetic */ GodDubbingActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object apply(Object obj) throws Exception {
                return a((Integer) obj);
            }

            public Integer a(Integer num) {
                try {
                    Thread.sleep(1000);
                } catch (Throwable e) {
                    this.a.M.onError(e);
                }
                return num;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this.M);
    }

    private void z() {
        if (a.DOWNLOAD_IMAGE == this.P) {
            this.B = this.D * 1000;
            A();
        } else if (this.l != null) {
            this.l.pause();
            this.B = this.l.getCurrentPosition();
            A();
        }
        this.r.a();
        this.h.setText(getString(R.string.press_to_record));
        g.a().b();
        this.b.setClickable(true);
        this.b.setImageResource(R.drawable.god_dubbing_undo_selector);
        this.a.setClickable(true);
        this.c.setImageResource(R.drawable.god_dubbing_record_image);
        this.c.setBackgroundResource(R.drawable.god_dubbing_record_image);
        this.K = b.PAUSE_RECORD;
    }

    private void A() {
        if (this.y == null) {
            this.y = new LinkedHashMap();
        }
        if (this.z == null) {
            this.z = new LinkedHashMap();
        }
        this.y.put(Integer.valueOf(this.C), Integer.valueOf(this.B));
        this.z.put(Integer.valueOf(this.C), Integer.valueOf(this.A - this.B));
    }

    protected void onPause() {
        super.onPause();
        this.J = true;
        if (b.ON_RECORD == this.K) {
            z();
        }
        if (b.FINISH_RECORD != this.K) {
            this.r.d();
            B();
        }
    }

    private void B() {
        this.G = true;
        if (a.DOWNLOAD_IMAGE != this.P) {
            this.a.setVisibility(0);
        }
        this.e.setClickable(false);
        this.e.setImageResource(R.drawable.god_dubbing_gray_complete);
        this.r.c();
        if (!e.a(this.y)) {
            this.y.clear();
        }
        if (!e.a(this.z)) {
            this.z.clear();
        }
        if (!e.a(this.x)) {
            this.x.clear();
        }
        this.C = 0;
        this.B = 0;
        A();
        E();
        this.D = 0;
        this.I = false;
        G();
    }

    protected void onDestroy() {
        super.onDestroy();
        F();
        D();
        if (this.N != null) {
            this.N.g();
        }
        C();
    }

    private void C() {
        if (this.Q != null) {
            this.Q.cancel(true);
            this.Q = null;
        }
        this.v = null;
        this.x = null;
        this.N = null;
        if (this.l != null) {
            if (this.l.isPlaying()) {
                this.l.stopPlayback();
            }
            this.l = null;
        }
        if (this.R != null) {
            this.R.removeCallbacksAndMessages(null);
        }
        g.a().c();
        if (this.L != null) {
            this.L.onComplete();
        }
        if (this.r != null) {
            this.r.e();
            this.r.f();
        }
    }

    private void D() {
        if (this.t != null && this.t.isShowing()) {
            this.t.dismiss();
            this.t = null;
        }
        if (this.s != null && this.s.isShowing()) {
            this.s.dismiss();
            this.s = null;
        }
    }

    private void E() {
        d.b(d.a());
        d.b(d.i());
        d.b(d.k());
    }

    private void F() {
        E();
        d.b(d.m());
        d.b(d.p());
        d.b(d.d());
        d.b(d.f());
        d.b(d.b());
        d.b(d.n());
    }

    protected void onResume() {
        super.onResume();
        if (this.J) {
            this.d.setVisibility(0);
            this.l.resume();
        }
    }

    public void b() {
        if (a.DOWNLOAD_IMAGE != this.P && d.d(this.E)) {
            if (this.I) {
                this.d.setVisibility(8);
                this.l.start();
            }
            if (!this.I) {
                this.l.setVideoURI(Uri.parse(this.E));
                this.I = true;
            }
            this.l.setOnPreparedListener(new OnPreparedListener(this) {
                final /* synthetic */ GodDubbingActivity a;

                {
                    this.a = r1;
                }

                public void onPrepared(MediaPlayer mediaPlayer) {
                    if (!this.a.J) {
                        this.a.l.seekTo(0);
                        mediaPlayer.setVolume(0.0f, 0.0f);
                        mediaPlayer.setOnSeekCompleteListener(new GodDubbingActivity$2$1(this));
                    }
                }
            });
        }
    }

    public void c() {
        if (a.DOWNLOAD_IMAGE == this.P) {
            this.B = 15000;
            A();
        } else if (this.l != null) {
            this.B = this.A;
            this.l.pause();
            A();
        }
        this.g.setVisibility(0);
        this.c.setImageResource(R.drawable.god_dubbing_listen_image);
        this.c.setBackgroundResource(R.drawable.god_dubbing_listen_image);
        this.e.setImageResource(R.drawable.god_dubbing_complete_selector);
        this.e.setClickable(true);
        this.h.setText(getString(R.string.pre_listen));
        this.K = b.FINISH_RECORD;
        g.a().b();
        this.b.setClickable(true);
        this.b.setImageResource(R.drawable.god_dubbing_undo_selector);
        this.a.setClickable(true);
        if (a.DOWNLOAD_IMAGE != this.P) {
            this.a.setVisibility(0);
        }
    }

    private void G() {
        if (this.D < 0) {
            this.D = 0;
        }
        CharSequence a = ac.a((long) (this.D * 1000));
        if (this.i != null) {
            this.i.setText(a);
        }
    }
}
