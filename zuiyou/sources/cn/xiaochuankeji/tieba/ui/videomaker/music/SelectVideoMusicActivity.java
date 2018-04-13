package cn.xiaochuankeji.tieba.ui.videomaker.music;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.danmaku.e;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicCategoryJson;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicHomeJson;
import cn.xiaochuankeji.tieba.json.UgcVideoMusicJson;
import cn.xiaochuankeji.tieba.ui.base.g;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.utils.f;
import cn.xiaochuankeji.tieba.ui.videomaker.music.a.b;
import com.android.a.a.c;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.Executors;
import rx.j;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnErrorListener;

public class SelectVideoMusicActivity extends h implements b {
    public static final HashMap<Long, Integer> d = new HashMap();
    private UltimateRecyclerView e;
    private a f;
    private ViewPager g;
    private a h;
    private f i;
    private View j;
    private d k;
    private ArrayList<UgcVideoMusicCategoryJson> l = new ArrayList();
    private ArrayList<UgcVideoMusicJson> m;
    private boolean n;
    private long o;
    private int p = -1;
    private UgcVideoMusicJson q = null;
    private cn.xiaochuankeji.tieba.common.d.a r;
    private cn.xiaochuankeji.tieba.common.d.a s;
    private e t;
    private long u = -1;

    class a extends g {
        final /* synthetic */ SelectVideoMusicActivity a;

        public a(SelectVideoMusicActivity selectVideoMusicActivity, FragmentManager fragmentManager) {
            this.a = selectVideoMusicActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            long j = ((UgcVideoMusicCategoryJson) this.a.l.get(i)).id;
            if (i == this.a.p) {
                return c.a(j, this.a.q, this.a.m, this.a.n, this.a.o);
            }
            return c.a(j);
        }

        public int getCount() {
            return this.a.l.size();
        }
    }

    public static void a(Activity activity, int i, UgcVideoMusicJson ugcVideoMusicJson) {
        Intent intent = new Intent(activity, SelectVideoMusicActivity.class);
        if (ugcVideoMusicJson != null) {
            intent.putExtra("key_music_info", ugcVideoMusicJson);
        }
        activity.startActivityForResult(intent, i);
    }

    protected boolean a(Bundle bundle) {
        this.q = (UgcVideoMusicJson) getIntent().getSerializableExtra("key_music_info");
        return true;
    }

    protected int a() {
        return R.layout.activity_select_video_music;
    }

    protected void c() {
        super.c();
        this.e = (UltimateRecyclerView) findViewById(R.id.id_stickynavlayout_topview);
        this.g = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
        this.j = findViewById(R.id.vClose);
        this.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SelectVideoMusicActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        findViewById(R.id.vSearch).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SelectVideoMusicActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.k();
                this.a.u = -1;
                this.a.a(-1, false);
                this.a.k.a();
            }
        });
        this.i = new f(this, new cn.xiaochuankeji.tieba.ui.utils.f.a(this) {
            final /* synthetic */ SelectVideoMusicActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.y();
            }
        });
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        ((ViewGroup) this.b).addView(this.i, 1, layoutParams);
        if (VERSION.SDK_INT >= 21) {
            c.a(this, getResources().getColor(R.color.video_music_pink_bg), false);
        }
        this.k = new d(this);
        if (-1 == ((FrameLayout) this.b).indexOfChild(this.k)) {
            ((ViewGroup) this.b).addView(this.k, layoutParams);
        }
    }

    public boolean h() {
        return false;
    }

    protected void i_() {
        w();
        x();
        y();
    }

    private void w() {
        this.e.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.e.a(new e());
        this.f = new a(this, this.l);
        this.f.a((b) this);
        this.e.setAdapter(this.f);
    }

    private void x() {
        this.g.setOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ SelectVideoMusicActivity a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                this.a.p = i;
                this.a.f.b(i);
                this.a.b(i);
                this.a.j();
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void b(int i) {
        View findViewByPosition = ((LinearLayoutManager) this.e.getLayoutManager()).findViewByPosition(i);
        if (findViewByPosition != null) {
            this.e.g.smoothScrollBy(((findViewByPosition.getWidth() / 2) + findViewByPosition.getLeft()) - (this.e.getWidth() / 2), 0);
        }
    }

    private void y() {
        cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
        new cn.xiaochuankeji.tieba.api.ugcvideo.a().a().a(rx.a.b.a.a()).b(new j<UgcVideoMusicHomeJson>(this) {
            final /* synthetic */ SelectVideoMusicActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((UgcVideoMusicHomeJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                cn.xiaochuankeji.tieba.background.utils.g.a(th.getMessage());
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                this.a.i.a(R.drawable.img_exception_network_error_in_musicselect, "小右加载不出列表啦");
            }

            public void a(UgcVideoMusicHomeJson ugcVideoMusicHomeJson) {
                boolean z = false;
                cn.xiaochuankeji.tieba.ui.widget.g.c(this.a);
                long j = ugcVideoMusicHomeJson.currentCid;
                Collection collection = ugcVideoMusicHomeJson.categoryList;
                if (collection == null || collection.size() <= 0) {
                    this.a.i.b(R.drawable.img_exception_musiclist_empty, "音乐去哪儿啦？");
                    return;
                }
                this.a.i.setVisibility(8);
                for (int i = 0; i < collection.size(); i++) {
                    if (((UgcVideoMusicCategoryJson) collection.get(i)).id == j) {
                        this.a.p = i;
                        break;
                    }
                }
                this.a.l.addAll(collection);
                this.a.f.notifyDataSetChanged();
                SelectVideoMusicActivity selectVideoMusicActivity = this.a;
                if (ugcVideoMusicHomeJson.more == 1) {
                    z = true;
                }
                selectVideoMusicActivity.n = z;
                this.a.o = ugcVideoMusicHomeJson.offset;
                this.a.m = ugcVideoMusicHomeJson.musicList;
                this.a.h = new a(this.a, this.a.getSupportFragmentManager());
                this.a.g.setAdapter(this.a.h);
                this.a.g.setCurrentItem(this.a.p);
            }
        });
    }

    public void a(int i) {
        this.g.setCurrentItem(i);
    }

    protected void onPause() {
        super.onPause();
        k();
        this.u = -1;
        a(-1, false);
    }

    public void onBackPressed() {
        if (q() || this.k == null || this.k.getVisibility() != 0) {
            super.onBackPressed();
        } else {
            this.k.b();
        }
    }

    public void j() {
        Object instantiateItem = this.h.instantiateItem(this.g, this.p);
        if (instantiateItem != null && (instantiateItem instanceof c)) {
            if (!((c) instantiateItem).b(((UgcVideoMusicCategoryJson) this.l.get(this.p)).id == -1)) {
                ((c) instantiateItem).b();
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        d.clear();
    }

    public void a(String str, final long j) {
        k();
        if (this.u == j) {
            this.u = -1;
            a(-1, false);
            return;
        }
        this.u = j;
        this.t = new e(0, str);
        if (this.t.b()) {
            a(j, false);
            a(this.t.c());
            return;
        }
        a(j, true);
        this.t.a(new cn.xiaochuankeji.tieba.background.danmaku.e.a(this) {
            final /* synthetic */ SelectVideoMusicActivity b;

            public void a(e eVar, String str) {
                this.b.a(j, false);
                this.b.a(str);
            }

            public void b(e eVar, String str) {
                cn.xiaochuankeji.tieba.background.utils.g.a(str);
            }
        });
        this.t.d();
    }

    private void a(long j, boolean z) {
        if (this.k.c()) {
            this.k.a(j, z);
        } else {
            org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.background.d.c(j, z));
        }
    }

    private void a(String str) {
        this.r = new cn.xiaochuankeji.tieba.common.d.a(BaseApplication.getAppContext());
        this.r.a(str);
        this.r.start();
        this.r.a(new IMediaPlayer$OnCompletionListener(this) {
            final /* synthetic */ SelectVideoMusicActivity a;

            {
                this.a = r1;
            }

            public void onCompletion(IMediaPlayer iMediaPlayer) {
                this.a.r.seekTo(0);
                this.a.r.start();
            }
        });
        this.r.a(new IMediaPlayer$OnErrorListener(this) {
            final /* synthetic */ SelectVideoMusicActivity a;

            {
                this.a = r1;
            }

            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                if (this.a.t != null) {
                    cn.htjyb.c.a.b.a(this.a.t.c());
                }
                return false;
            }
        });
    }

    protected void k() {
        if (this.r != null) {
            this.s = this.r;
            this.r = null;
            Executors.newSingleThreadExecutor().execute(new Runnable(this) {
                final /* synthetic */ SelectVideoMusicActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.s.g();
                }
            });
        }
        if (this.t != null) {
            this.t.e();
            this.t.a(null);
            this.t = null;
        }
    }

    public void a(UgcVideoMusicJson ugcVideoMusicJson) {
        e eVar = new e(0, ugcVideoMusicJson.url);
        if (eVar.b()) {
            k();
            Intent intent = new Intent();
            intent.putExtra("key_music_cache_path", eVar.c());
            intent.putExtra("key_music_info", ugcVideoMusicJson);
            setResult(-1, intent);
            finish();
        }
    }

    long v() {
        return this.u;
    }
}
