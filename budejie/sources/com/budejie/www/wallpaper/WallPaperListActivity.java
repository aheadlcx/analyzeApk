package com.budejie.www.wallpaper;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.util.an;
import com.budejie.www.wallpaper.a.a;
import com.budejie.www.wallpaper.b.b;
import de.greenrobot.event.EventBus;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

public class WallPaperListActivity extends BaseActvityWithLoadDailog implements OnClickListener {
    private List<a> a;
    private List<a> b;
    private List<a> c;
    private RecyclerView d;
    private c e;
    private List<a> f;
    private WallPaperListActivity h;
    private TextView i;
    private TextView j;
    private TextView k;
    private View l;
    private View m;
    private boolean n;
    private ImageView o;
    private Disposable p;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wall_paper_list);
        EventBus.getDefault().register(this);
        b.a((Context) this);
        this.h = this;
        a();
        com.budejie.www.wallpaper.b.a.a((Context) this);
        a(true);
    }

    private void a() {
        this.n = b.e(this);
        h();
        g();
        this.b = new ArrayList();
        this.c = new ArrayList();
        b();
    }

    private void b() {
        if (this.a == null) {
            this.a = new ArrayList();
        }
        if (this.e == null) {
            this.e = new c(this, this.a, 1);
            this.d.setAdapter(this.e);
            return;
        }
        this.e.notifyDataSetChanged();
    }

    private void g() {
        this.d.setLayoutManager(new GridLayoutManager(this, 3));
        this.d.addItemDecoration(new b(an.a((Context) this, 1), an.a((Context) this, 1)));
        this.i.setText("动态桌面");
    }

    private void h() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.d = (RecyclerView) findViewById(R.id.wall_paper_recycler_view);
        this.i = (TextView) findViewById(R.id.title_center_txt);
        Button button = (Button) findViewById(R.id.title_left_btn);
        button.setVisibility(0);
        this.j = (TextView) findViewById(R.id.download_video_text_view);
        this.k = (TextView) findViewById(R.id.native_video_text_view);
        this.l = findViewById(R.id.download_video_tag_view);
        this.m = findViewById(R.id.native_video_tag_view);
        button.setOnClickListener(this);
        k();
        this.o = (ImageView) findViewById(R.id.voice_image_view);
        i();
        this.o.setOnClickListener(this);
        findViewById(R.id.download_video_layout).setOnClickListener(this);
        findViewById(R.id.native_video_layout).setOnClickListener(this);
    }

    public void a(final boolean z) {
        if (this.a != null) {
            this.a.clear();
        }
        f();
        Observable.just(Integer.valueOf(1)).map(new Function<Integer, Boolean>(this) {
            final /* synthetic */ WallPaperListActivity b;

            public /* synthetic */ Object apply(@NonNull Object obj) throws Exception {
                return a((Integer) obj);
            }

            public Boolean a(@NonNull Integer num) throws Exception {
                if (this.b.f != null) {
                    this.b.f.clear();
                }
                this.b.f = com.budejie.www.wallpaper.b.a.a(this.b.h, z);
                return Boolean.valueOf(!com.budejie.www.goddubbing.c.a.a(this.b.f));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>(this) {
            final /* synthetic */ WallPaperListActivity b;

            public /* synthetic */ void onNext(@NonNull Object obj) {
                a((Boolean) obj);
            }

            public void onSubscribe(@NonNull Disposable disposable) {
                this.b.p = disposable;
            }

            public void a(@NonNull Boolean bool) {
                if (z) {
                    this.b.b.addAll(this.b.f);
                } else {
                    this.b.c.addAll(this.b.f);
                }
                this.b.a.addAll(this.b.f);
                this.b.b();
            }

            public void onError(@NonNull Throwable th) {
                this.b.e();
                if (this.b.p != null) {
                    this.b.p.dispose();
                }
            }

            public void onComplete() {
                this.b.e();
                if (this.b.p != null) {
                    this.b.p.dispose();
                }
            }
        });
    }

    public void onEventMainThread(com.budejie.www.wallpaper.a.b bVar) {
        if (!com.budejie.www.goddubbing.c.a.a(this.a)) {
            Object a = bVar.a();
            if (!TextUtils.isEmpty(a)) {
                for (a aVar : this.a) {
                    if (a.equals(aVar.a())) {
                        this.a.remove(aVar);
                        break;
                    }
                }
                if (this.j.isSelected()) {
                    this.b.clear();
                    this.b.addAll(this.a);
                } else if (this.k.isSelected()) {
                    this.c.clear();
                    this.c.addAll(this.a);
                }
                b();
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_left_btn:
                finish();
                return;
            case R.id.download_video_layout:
                if (!this.j.isSelected()) {
                    j();
                    return;
                }
                return;
            case R.id.native_video_layout:
                if (!this.k.isSelected()) {
                    l();
                    return;
                }
                return;
            case R.id.voice_image_view:
                this.n = !this.n;
                i();
                b.a(this.h, this.n);
                if (an.p(this, "com.spriteapp.bdjwallpaperengine")) {
                    b.d(this.h);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void i() {
        this.o.setImageResource(this.n ? R.drawable.wall_paper_list_voice_image_selector : R.drawable.wall_paper_list_silence_image_selector);
    }

    private void j() {
        k();
        if (this.a == null) {
            this.a = new ArrayList();
        }
        this.a.clear();
        if (com.budejie.www.goddubbing.c.a.a(this.b)) {
            if (this.p != null) {
                this.p.dispose();
            }
            b();
            a(true);
            return;
        }
        this.a.addAll(this.b);
        b();
    }

    private void k() {
        this.j.setSelected(true);
        this.l.setVisibility(0);
        this.k.setSelected(false);
        this.m.setVisibility(8);
        this.j.getPaint().setFakeBoldText(true);
        this.k.getPaint().setFakeBoldText(false);
    }

    private void l() {
        this.j.setSelected(false);
        this.l.setVisibility(8);
        this.k.setSelected(true);
        this.m.setVisibility(0);
        this.j.getPaint().setFakeBoldText(false);
        this.k.getPaint().setFakeBoldText(true);
        if (this.a == null) {
            this.a = new ArrayList();
        }
        this.a.clear();
        if (com.budejie.www.goddubbing.c.a.a(this.c)) {
            if (this.p != null) {
                this.p.dispose();
            }
            b();
            a(false);
            return;
        }
        this.a.addAll(this.c);
        b();
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
