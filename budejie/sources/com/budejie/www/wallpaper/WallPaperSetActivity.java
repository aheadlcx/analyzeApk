package com.budejie.www.wallpaper;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.VideoView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.OauthWeiboBaseAct;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.goddubbing.c.d;
import com.budejie.www.goddubbing.c.h;
import com.budejie.www.util.an;
import com.budejie.www.util.y;
import com.budejie.www.wallpaper.a.b;
import com.bumptech.glide.i;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.spriteapp.videoedit.spritefilters.SpriteFiltersClass.ProgressListener;
import de.greenrobot.event.EventBus;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class WallPaperSetActivity extends OauthWeiboBaseAct implements OnClickListener, b {
    private Disposable A;
    private Disposable B;
    private VideoView a;
    private ProgressBar b;
    private TextView c;
    private ListItemObject d;
    private a e;
    private a f;
    private String g;
    private String h;
    private String i;
    private AsyncImageView j;
    private ImageView k;
    private MediaPlayer l;
    private boolean m;
    private boolean n;
    private Context o;
    private Disposable p;
    private Disposable q;
    private String r;
    private boolean s;
    private BroadcastReceiver t;
    private String u;
    private int v = 0;
    private com.budejie.www.goddubbing.c.b w;
    private Handler x = new Handler(this) {
        final /* synthetic */ WallPaperSetActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.b.setProgress(100);
                    this.a.b.setVisibility(8);
                    this.a.c.setText(R.string.set_to_wall_paper);
                    this.a.k.setVisibility(0);
                    this.a.c.setBackgroundDrawable(this.a.getResources().getDrawable(R.drawable.wall_paper_download_text_selector));
                    return;
                case 2:
                    com.budejie.www.wallpaper.b.b.b(this.a);
                    return;
                case 3:
                    this.a.j.setVisibility(4);
                    return;
                case 4:
                    this.a.r();
                    this.a.i();
                    return;
                case 5:
                    this.a.b(message.arg1);
                    return;
                default:
                    return;
            }
        }
    };
    private boolean y;
    private ProgressBar z;

    private enum a {
        NEED_DOWNLOAD,
        ALREADY_DOWNLOAD
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wall_paper_set);
        this.o = this;
        h();
        c();
        b();
    }

    private void b() {
        BroadcastReceiver anonymousClass9 = new BroadcastReceiver(this) {
            final /* synthetic */ WallPaperSetActivity a;

            {
                this.a = r1;
            }

            public void onReceive(Context context, Intent intent) {
                if (intent != null) {
                    String action = intent.getAction();
                    if (!TextUtils.isEmpty(action)) {
                        Object obj = -1;
                        switch (action.hashCode()) {
                            case -1454123155:
                                if (action.equals("android.intent.action.SCREEN_ON")) {
                                    obj = null;
                                    break;
                                }
                                break;
                        }
                        switch (obj) {
                            case null:
                                this.a.j.setVisibility(8);
                                return;
                            default:
                                return;
                        }
                    }
                }
            }
        };
        this.t = anonymousClass9;
        registerReceiver(anonymousClass9, new IntentFilter("android.intent.action.SCREEN_ON"));
    }

    private void c() {
        com.budejie.www.wallpaper.b.b.a((Context) this);
        this.m = com.budejie.www.wallpaper.b.b.e(this);
        this.f = a.ALREADY_DOWNLOAD;
        g();
        d();
        ImageView imageView = this.k;
        int i = this.d == null ? R.drawable.wall_paper_set_delete_image_selector : this.m ? R.drawable.wall_paper_set_voice_image_selector : R.drawable.wall_paper_set_silence_image_selector;
        imageView.setImageResource(i);
        i();
        if (this.d == null) {
            e();
            a();
        }
    }

    private void d() {
        if (this.d != null) {
            this.i = com.lt.a.a((Context) this).a(this.d.getImgUrl());
            if (TextUtils.isEmpty(this.i)) {
                this.i = com.lt.a.a((Context) this).a(this.d.getImgPath());
            }
        }
        if (TextUtils.isEmpty(this.i)) {
            this.j.setVisibility(8);
        } else if (this.d != null) {
            this.j.setPostImage(this.i);
        } else {
            i.a((FragmentActivity) this).a(this.i).a(DiskCacheStrategy.SOURCE).a(this.j);
        }
    }

    public void setTopMargin(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = (int) getResources().getDimension(VERSION.SDK_INT >= 21 ? R.dimen.wall_paper_set_icon_top_margin : R.dimen.wall_paper_set_icon_api_below_21_top_margin);
        view.setLayoutParams(layoutParams);
    }

    private void e() {
        if (!TextUtils.isEmpty(this.g)) {
            this.a.setVideoURI(Uri.parse(this.g));
            this.a.start();
            this.a.setOnPreparedListener(new OnPreparedListener(this) {
                final /* synthetic */ WallPaperSetActivity a;

                {
                    this.a = r1;
                }

                public void onPrepared(MediaPlayer mediaPlayer) {
                    this.a.x.sendEmptyMessageDelayed(3, this.a.d == null ? 0 : 1000);
                    this.a.l = mediaPlayer;
                    this.a.l.setLooping(true);
                }
            });
            this.a.setOnErrorListener(new OnErrorListener(this) {
                final /* synthetic */ WallPaperSetActivity a;

                {
                    this.a = r1;
                }

                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    this.a.f();
                    this.a.finish();
                    return true;
                }
            });
        }
    }

    private void f() {
        String string;
        if (this.d == null) {
            string = getResources().getString(R.string.video_file_is_not_exist);
        } else {
            string = getResources().getString(R.string.can_not_play_this_video);
        }
        an.a((Activity) this, string, -1).show();
    }

    private void g() {
        Intent intent = getIntent();
        if (intent != null) {
            this.d = (ListItemObject) intent.getSerializableExtra("wallPaperTag");
            this.g = intent.getStringExtra("native_video_path_tag");
            this.i = intent.getStringExtra("native_video_image_tag");
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.a != null && !this.a.isPlaying() && !TextUtils.isEmpty(this.g)) {
            this.a.start();
        }
    }

    protected void onPause() {
        super.onPause();
        if (!(this.j == null || this.s)) {
            this.j.setVisibility(0);
        }
        if (this.a != null && this.a.isPlaying()) {
            this.a.pause();
        }
    }

    public void onBackPressed() {
        if (!o()) {
            super.onBackPressed();
            this.s = true;
        }
    }

    private void h() {
        this.a = (VideoView) findViewById(R.id.wall_paper_video_view);
        this.b = (ProgressBar) findViewById(R.id.wall_paper_progress_bar);
        this.c = (TextView) findViewById(R.id.progress_text_view);
        this.j = (AsyncImageView) findViewById(R.id.wall_paper_image_view);
        this.z = (ProgressBar) findViewById(R.id.copy_progress_bar);
        ImageView imageView = (ImageView) findViewById(R.id.finish_image_view);
        imageView.setOnClickListener(this);
        this.k = (ImageView) findViewById(R.id.voice_image_view);
        this.k.setOnClickListener(this);
        setTopMargin(imageView);
        setTopMargin(this.k);
        this.c.setText(String.format(getResources().getString(R.string.download_text), new Object[]{Integer.valueOf(0)}));
    }

    private void i() {
        if (this.d != null) {
            String a = com.lt.a.a((Context) this).a(this.d.getDownloadVideoUri());
            this.i = this.d.getImgUrl();
            if (!TextUtils.isEmpty(a)) {
                int lastIndexOf = a.lastIndexOf("_");
                if (lastIndexOf < 0 || lastIndexOf > a.length()) {
                    this.r = a;
                } else {
                    this.r = com.lt.a.a((Context) this).a(a.substring(0, lastIndexOf) + "_o4n" + ".mp4");
                }
                if (!TextUtils.isEmpty(this.r)) {
                    this.h = a(this.r);
                    if (!TextUtils.isEmpty(this.h)) {
                        if (this.h.contains("cut")) {
                            this.r = this.r.replace("_o4n", "");
                        }
                        Observable.just(Integer.valueOf(1)).map(new Function<Integer, Boolean>(this) {
                            final /* synthetic */ WallPaperSetActivity a;

                            {
                                this.a = r1;
                            }

                            public /* synthetic */ Object apply(@NonNull Object obj) throws Exception {
                                return a((Integer) obj);
                            }

                            public Boolean a(@NonNull Integer num) throws Exception {
                                return Boolean.valueOf(com.budejie.www.wallpaper.b.b.a(this.a.h));
                            }
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>(this) {
                            final /* synthetic */ WallPaperSetActivity a;

                            {
                                this.a = r1;
                            }

                            public /* synthetic */ void onNext(@NonNull Object obj) {
                                a((Boolean) obj);
                            }

                            public void onSubscribe(@NonNull Disposable disposable) {
                                this.a.p = disposable;
                            }

                            public void a(@NonNull Boolean bool) {
                                if (bool.booleanValue()) {
                                    this.a.j();
                                } else if (an.a(this.a.o)) {
                                    this.a.k();
                                } else {
                                    an.a(this.a, this.a.getResources().getString(R.string.nonet), -1).show();
                                }
                            }

                            public void onError(@NonNull Throwable th) {
                                if (this.a.p != null) {
                                    this.a.p.dispose();
                                }
                            }

                            public void onComplete() {
                                if (this.a.p != null) {
                                    this.a.p.dispose();
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    private void j() {
        this.g = com.budejie.www.wallpaper.b.b.a() + "/" + this.h;
        a();
        e();
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf("/") + 1;
        if (lastIndexOf < 0 || lastIndexOf > str.length()) {
            return "";
        }
        return str.substring(lastIndexOf);
    }

    private void k() {
        Observable.just(Integer.valueOf(1)).map(new Function<Integer, Boolean>(this) {
            final /* synthetic */ WallPaperSetActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object apply(@NonNull Object obj) throws Exception {
                return a((Integer) obj);
            }

            public Boolean a(@NonNull Integer num) throws Exception {
                this.a.y = y.a(this.a.r);
                if (!this.a.y) {
                    this.a.u = this.a.h;
                    this.a.r = com.lt.a.a(this.a.o).a(this.a.d.getDownloadVideoUri());
                    this.a.h = this.a.a(this.a.r);
                }
                return Boolean.valueOf(this.a.y);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>(this) {
            final /* synthetic */ WallPaperSetActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(@NonNull Object obj) {
                a((Boolean) obj);
            }

            public void onSubscribe(@NonNull Disposable disposable) {
                this.a.q = disposable;
            }

            public void a(@NonNull Boolean bool) {
                this.a.g = this.a.r;
                this.a.f = a.NEED_DOWNLOAD;
                this.a.e();
                this.a.e = new a$a().a(this.a.o).a(this.a).a(com.budejie.www.wallpaper.b.b.a()).b(this.a.h).d(com.lt.a.a(this.a.o).a(this.a.i)).c(this.a.r).a(bool.booleanValue() ? 100 : 70).a();
                this.a.e.f();
            }

            public void onError(@NonNull Throwable th) {
                if (this.a.q != null) {
                    this.a.q.dispose();
                }
            }

            public void onComplete() {
                if (this.a.q != null) {
                    this.a.q.dispose();
                }
            }
        });
    }

    public void a(int i) {
        if (!this.n) {
            b(i);
        }
    }

    private void b(int i) {
        if (this.b != null) {
            this.b.setProgress(i);
        }
        if (this.c != null) {
            this.c.setText(String.format(getResources().getString(R.string.download_text), new Object[]{Integer.valueOf(i)}));
        }
    }

    public void a() {
        this.n = true;
        if (this.f == a.NEED_DOWNLOAD) {
            this.f = a.ALREADY_DOWNLOAD;
            this.g = com.budejie.www.wallpaper.b.b.a() + "/" + this.h;
            if (!this.y) {
                Observable.just(Integer.valueOf(1)).map(new Function<Integer, Boolean>(this) {
                    final /* synthetic */ WallPaperSetActivity a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ Object apply(Object obj) throws Exception {
                        return a((Integer) obj);
                    }

                    public Boolean a(Integer num) throws Exception {
                        return Boolean.valueOf(this.a.m() == 0);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>(this) {
                    final /* synthetic */ WallPaperSetActivity a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onNext(Object obj) {
                        a((Boolean) obj);
                    }

                    public void onSubscribe(Disposable disposable) {
                        this.a.A = disposable;
                    }

                    public void a(Boolean bool) {
                        if (bool.booleanValue()) {
                            d.c(this.a.g);
                            this.a.g = com.budejie.www.wallpaper.b.b.a() + "/" + this.a.u;
                            d.a(this.a.o, this.a.g);
                        }
                        if (!an.g(this.a)) {
                            this.a.l();
                        }
                    }

                    public void onError(Throwable th) {
                        if (this.a.A != null) {
                            this.a.A.dispose();
                            this.a.A = null;
                        }
                    }

                    public void onComplete() {
                        if (this.a.A != null) {
                            this.a.A.dispose();
                            this.a.A = null;
                        }
                    }
                });
                return;
            }
        }
        l();
    }

    private void l() {
        this.x.sendEmptyMessage(1);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WallPaperSetActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.o()) {
                    this.a.n();
                }
            }
        });
    }

    private int m() {
        int e = d.e(this.g) / 1000;
        if (e <= 4) {
            return -1;
        }
        String str = com.budejie.www.wallpaper.b.b.a() + "/" + this.u;
        d.c(this.u);
        e -= 4;
        this.v = -1;
        this.v = com.budejie.www.goddubbing.c.i.a(this.g, str, 0.0f, (float) e, new ProgressListener(this) {
            final /* synthetic */ WallPaperSetActivity b;

            public void sendMessage(String str) {
                int a = h.a(str, (float) e);
                Message obtainMessage = this.b.x.obtainMessage();
                obtainMessage.arg1 = a;
                obtainMessage.what = 5;
                this.b.x.sendMessage(obtainMessage);
            }
        });
        if (an.g((Activity) this)) {
            this.v = -1;
            d.c(this.g);
            d.c(str);
        }
        return this.v;
    }

    private void n() {
        if (!an.p(this, "com.spriteapp.bdjwallpaperengine") || com.budejie.www.wallpaper.b.b.c(this)) {
            an.a((Activity) this, getResources().getString(R.string.install_wall_paper_engine_text), -1).show();
            this.x.sendEmptyMessageDelayed(2, 1000);
            return;
        }
        com.budejie.www.wallpaper.b.b.a(this.d, getResources().getString(R.string.wall_paper_set_live_wall_text), "");
        com.budejie.www.wallpaper.b.b.a((Context) this, this.m);
        final String str = com.budejie.www.wallpaper.b.b.a() + "/fb6b7cdf6d8nf65f5";
        this.z.setVisibility(0);
        Observable.just(Integer.valueOf(1)).map(new Function<Integer, Boolean>(this) {
            final /* synthetic */ WallPaperSetActivity b;

            public /* synthetic */ Object apply(Object obj) throws Exception {
                return a((Integer) obj);
            }

            public Boolean a(Integer num) throws Exception {
                return Boolean.valueOf(d.a(this.b.g, str));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>(this) {
            final /* synthetic */ WallPaperSetActivity b;

            public /* synthetic */ void onNext(Object obj) {
                a((Boolean) obj);
            }

            public void onSubscribe(Disposable disposable) {
                this.b.B = disposable;
            }

            public void a(Boolean bool) {
                com.budejie.www.wallpaper.b.b.a(this.b, bool.booleanValue() ? str : this.b.g);
            }

            public void onError(Throwable th) {
                this.b.z.setVisibility(8);
                if (this.b.B != null) {
                    this.b.B.dispose();
                    this.b.B = null;
                }
            }

            public void onComplete() {
                this.b.z.setVisibility(8);
                if (this.b.B != null) {
                    this.b.B.dispose();
                    this.b.B = null;
                }
            }
        });
    }

    public void onClick(View view) {
        if (!o()) {
            switch (view.getId()) {
                case R.id.voice_image_view:
                    if (this.d != null) {
                        q();
                        return;
                    } else {
                        p();
                        return;
                    }
                case R.id.finish_image_view:
                    this.s = true;
                    finish();
                    return;
                default:
                    return;
            }
        }
    }

    private boolean o() {
        return this.z != null && this.z.getVisibility() == 0;
    }

    private void p() {
        new Builder(this).setTitle(R.string.prompt).setMessage(R.string.delete_video_confirm_text).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ WallPaperSetActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ WallPaperSetActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                d.c(this.a.g);
                d.a(this.a, this.a.g);
                com.budejie.www.wallpaper.a.b bVar = new com.budejie.www.wallpaper.a.b();
                bVar.a(this.a.g);
                EventBus.getDefault().post(bVar);
                this.a.finish();
            }
        }).create().show();
    }

    private void q() {
        String string;
        this.m = !this.m;
        this.k.setImageResource(this.m ? R.drawable.wall_paper_set_voice_image_selector : R.drawable.wall_paper_set_silence_image_selector);
        if (this.m) {
            string = getResources().getString(R.string.press_set_to_open_volume);
        } else {
            string = getResources().getString(R.string.press_set_to_close_volume);
        }
        an.a((Activity) this, string, -1).show();
    }

    protected void onDestroy() {
        super.onDestroy();
        r();
        if (!(this.p == null || this.p.isDisposed())) {
            this.p.dispose();
            this.p = null;
        }
        if (!(this.q == null || this.q.isDisposed())) {
            this.q.dispose();
            this.q = null;
        }
        if (!(this.A == null || this.A.isDisposed())) {
            this.A.dispose();
            this.A = null;
        }
        if (!(this.B == null || this.B.isDisposed())) {
            this.B.dispose();
            this.B = null;
        }
        if (this.a != null) {
            this.a.stopPlayback();
        }
        if (this.t != null) {
            unregisterReceiver(this.t);
            this.t = null;
        }
        if (this.v != 0) {
            if (this.w == null) {
                this.w = new com.budejie.www.goddubbing.c.b();
            }
            new Thread(this.w).start();
        }
    }

    private void r() {
        if (this.e != null) {
            this.e.a();
            this.e.g();
        }
        if (this.f == a.NEED_DOWNLOAD) {
            d.c(com.budejie.www.wallpaper.b.b.a() + "/" + this.h);
        }
    }
}
