package cn.xiaochuankeji.tieba.ui.danmaku;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.NotificationManagerCompat;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.danmaku.DanmakuItem;
import cn.xiaochuankeji.tieba.background.danmaku.e;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuLikeJson;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.updown.ViewUpDown;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;
import org.json.JSONObject;
import rx.j;

public class TopDanmakuView extends FrameLayout implements cn.xiaochuankeji.tieba.background.danmaku.e.a {
    private TreeSet<DanmakuItem> a;
    private a b;
    private int c;
    private cn.xiaochuankeji.tieba.common.d.a d;
    private boolean e;
    private e f;
    private c g;
    private boolean h;
    private cn.xiaochuankeji.tieba.background.danmaku.d i;
    private cn.xiaochuankeji.tieba.background.danmaku.c j;
    private View k;
    private a l;
    private AnimationDrawable m;
    private TextView n;
    private ViewUpDown o;
    private ImageView p;
    private int q = 0;

    private class a {
        public boolean a;
        public boolean b;
        public DanmakuItem c;
        public boolean d;
        public int e;
        public int f;
        public e g;
        final /* synthetic */ TopDanmakuView h;

        private a(TopDanmakuView topDanmakuView) {
            this.h = topDanmakuView;
        }
    }

    public static class b {
        public long a;
    }

    public interface c {
        void a(TopDanmakuView topDanmakuView, e eVar);
    }

    private class d implements Comparator<DanmakuItem> {
        final /* synthetic */ TopDanmakuView a;

        private d(TopDanmakuView topDanmakuView) {
            this.a = topDanmakuView;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((DanmakuItem) obj, (DanmakuItem) obj2);
        }

        public int a(DanmakuItem danmakuItem, DanmakuItem danmakuItem2) {
            if (danmakuItem == danmakuItem2) {
                return 0;
            }
            if (danmakuItem == null) {
                return -1;
            }
            if (danmakuItem2 == null) {
                return 1;
            }
            int i = danmakuItem.pos - danmakuItem2.pos;
            if (i > 0) {
                return 1;
            }
            if (i < 0) {
                return -1;
            }
            return 0;
        }
    }

    public TopDanmakuView(Context context) {
        super(context);
        c();
    }

    public TopDanmakuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c();
    }

    public TopDanmakuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c();
    }

    @TargetApi(21)
    public TopDanmakuView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        c();
    }

    private void c() {
        this.a = new TreeSet(new d());
        this.b = new a();
        LayoutInflater.from(getContext()).inflate(R.layout.top_danmaku_content, this);
        this.k = findViewById(R.id.container_view);
        this.n = (TextView) findViewById(R.id.danmaku_content);
        this.m = (AnimationDrawable) getResources().getDrawable(R.drawable.anim_danmaku_sound);
        this.m.setBounds(0, 0, this.m.getIntrinsicWidth(), this.m.getIntrinsicHeight());
        this.l = new a(this.n, this.m);
        this.o = (ViewUpDown) findViewById(R.id.like_bar);
        this.p = (ImageView) findViewById(R.id.btn_play_audio);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TopDanmakuView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                DanmakuItem danmakuItem = this.a.b.c;
                if (danmakuItem != null) {
                    final long j = danmakuItem.id;
                    if (this.a.getContext() == null) {
                        return;
                    }
                    if (this.a.getContext() instanceof MediaBrowseActivity) {
                        this.a.a(j, "danmaku");
                    } else if (this.a.getContext() instanceof UgcVideoActivity) {
                        new cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.d((Activity) this.a.getContext(), new cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.d.a(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void a(int i) {
                                if (1 == i) {
                                    b bVar = new b();
                                    bVar.a = j;
                                    org.greenrobot.eventbus.c.a().d(bVar);
                                } else if (2 == i) {
                                    this.b.a.a(j, "ugcvideo_danmaku");
                                }
                            }
                        }).b();
                    }
                }
            }
        });
        this.p.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TopDanmakuView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.p.setVisibility(8);
            }
        });
    }

    private void a(final long j, final String str) {
        f a = f.a("提示", "是否举报该弹幕?", (Activity) getContext(), new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
            final /* synthetic */ TopDanmakuView c;

            public void a(boolean z) {
                if (z) {
                    LinkedHashMap t = cn.xiaochuankeji.tieba.background.utils.c.a.c().t();
                    if (t.isEmpty()) {
                        this.c.a(j, str, 0);
                    }
                    SDCheckSheet sDCheckSheet = new SDCheckSheet((Activity) this.c.getContext(), new cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet.a(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void a(int i) {
                            if (i == -123) {
                                CustomReportReasonActivity.a(this.a.c.getContext(), j, this.a.c.q, str);
                            } else {
                                this.a.c.a(j, str, i);
                            }
                        }
                    });
                    int i = 0;
                    for (Entry entry : t.entrySet()) {
                        String str = (String) entry.getKey();
                        String str2 = (String) entry.getValue();
                        int parseInt = Integer.parseInt(str);
                        int i2 = i + 1;
                        if (str2.equals("其他")) {
                            this.c.q = parseInt;
                            i = -123;
                        } else {
                            i = parseInt;
                        }
                        if (i2 == t.size()) {
                            sDCheckSheet.a(str2, i, true);
                        } else {
                            sDCheckSheet.a(str2, i, false);
                        }
                        i = i2;
                    }
                    sDCheckSheet.b();
                }
            }
        });
        a.setConfirmTip("举报");
        a.b();
    }

    private void a(long j, String str, int i) {
        new cn.xiaochuankeji.tieba.background.b.b(j, str, i, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ TopDanmakuView a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                g.a("举报成功");
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ TopDanmakuView a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    private void d() {
        final DanmakuItem danmakuItem = this.b.c;
        if (danmakuItem != null && danmakuItem.liked == 0) {
            if (getContext() instanceof UgcVideoActivity) {
                new cn.xiaochuankeji.tieba.api.ugcvideo.a().f(danmakuItem.id).a(rx.a.b.a.a()).b(new j<UgcVideoDanmakuLikeJson>(this) {
                    final /* synthetic */ TopDanmakuView b;

                    public /* synthetic */ void onNext(Object obj) {
                        a((UgcVideoDanmakuLikeJson) obj);
                    }

                    public void onCompleted() {
                    }

                    public void onError(Throwable th) {
                        g.a(th.getMessage());
                    }

                    public void a(UgcVideoDanmakuLikeJson ugcVideoDanmakuLikeJson) {
                        danmakuItem.liked = 1;
                        DanmakuItem danmakuItem = danmakuItem;
                        danmakuItem.likes++;
                    }
                });
            } else if (this.i == null) {
                this.i = new cn.xiaochuankeji.tieba.background.danmaku.d(danmakuItem.id, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                    final /* synthetic */ TopDanmakuView b;

                    public /* synthetic */ void onResponse(Object obj, Object obj2) {
                        a((JSONObject) obj, obj2);
                    }

                    public void a(JSONObject jSONObject, Object obj) {
                        this.b.i = null;
                        danmakuItem.liked = 1;
                        DanmakuItem danmakuItem = danmakuItem;
                        danmakuItem.likes++;
                    }
                }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                    final /* synthetic */ TopDanmakuView a;

                    {
                        this.a = r1;
                    }

                    public void onErrorResponse(XCError xCError, Object obj) {
                        this.a.i = null;
                        g.a(xCError.getMessage());
                    }
                });
                this.i.execute();
            }
        }
    }

    private void e() {
        final DanmakuItem danmakuItem = this.b.c;
        if (danmakuItem != null && danmakuItem.liked == 0) {
            if (getContext() instanceof UgcVideoActivity) {
                new cn.xiaochuankeji.tieba.api.ugcvideo.a().g(danmakuItem.id).a(rx.a.b.a.a()).b(new j<UgcVideoDanmakuLikeJson>(this) {
                    final /* synthetic */ TopDanmakuView b;

                    public /* synthetic */ void onNext(Object obj) {
                        a((UgcVideoDanmakuLikeJson) obj);
                    }

                    public void onCompleted() {
                    }

                    public void onError(Throwable th) {
                        g.a(th.getMessage());
                    }

                    public void a(UgcVideoDanmakuLikeJson ugcVideoDanmakuLikeJson) {
                        danmakuItem.liked = -1;
                        DanmakuItem danmakuItem = danmakuItem;
                        danmakuItem.likes--;
                    }
                });
            } else if (this.j == null) {
                this.j = new cn.xiaochuankeji.tieba.background.danmaku.c(danmakuItem.id, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                    final /* synthetic */ TopDanmakuView b;

                    public /* synthetic */ void onResponse(Object obj, Object obj2) {
                        a((JSONObject) obj, obj2);
                    }

                    public void a(JSONObject jSONObject, Object obj) {
                        this.b.j = null;
                        danmakuItem.liked = -1;
                        DanmakuItem danmakuItem = danmakuItem;
                        danmakuItem.likes--;
                    }
                }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                    final /* synthetic */ TopDanmakuView a;

                    {
                        this.a = r1;
                    }

                    public void onErrorResponse(XCError xCError, Object obj) {
                        this.a.j = null;
                        g.a(xCError.getMessage());
                    }
                });
                this.j.execute();
            }
        }
    }

    public void setSoundDanmakuListener(c cVar) {
        this.g = cVar;
    }

    public void a() {
        if (!this.h) {
            this.h = true;
            if (this.e) {
                j();
            }
        }
    }

    public void b() {
        if (this.h) {
            this.h = false;
            if (this.e) {
                k();
            }
        }
    }

    public void a(boolean z) {
        this.b.a = false;
        this.b.b = false;
        g();
        l();
        if (z) {
            this.a.clear();
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!z) {
            setVisibility(4);
        }
    }

    public void a(int i) {
        if (d(i)) {
            setVisibility(4);
        }
        this.b.a = false;
        this.b.b = false;
        b(i);
    }

    public void b(int i) {
        this.c = i;
        if (!this.b.a) {
            if (!this.b.b) {
                e(i);
            }
            if (c(i) && isEnabled()) {
                setVisibility(0);
            }
            if (d(i)) {
                e(i);
                setVisibility(4);
            }
        }
    }

    public void a(ArrayList<DanmakuItem> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            DanmakuItem danmakuItem = (DanmakuItem) it.next();
            danmakuItem.pos = Math.max(0, danmakuItem.pos + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
        }
        this.a.addAll(arrayList);
        this.b.a = false;
    }

    public void a(DanmakuItem danmakuItem) {
        g();
        a(danmakuItem, this.c);
        f();
        if (getVisibility() != 0 && isEnabled()) {
            setVisibility(0);
        }
    }

    private boolean c(int i) {
        if (!this.b.b || this.b.d || i < this.b.e || i >= this.b.e + this.b.f) {
            return false;
        }
        f();
        return true;
    }

    private void f() {
        this.b.d = true;
        h();
        b(this.b.c);
    }

    private boolean d(int i) {
        if (!this.b.b) {
            return true;
        }
        if (!this.b.d || (i < this.b.e + this.b.f && i >= this.b.e)) {
            return false;
        }
        g();
        return true;
    }

    private void g() {
        this.b.d = false;
        i();
    }

    private void h() {
        if (this.b.g != null && this.b.g.b()) {
            this.p.setVisibility(8);
        }
    }

    private void i() {
        l();
        this.p.setVisibility(8);
    }

    private void e(int i) {
        DanmakuItem danmakuItem = new DanmakuItem();
        danmakuItem.pos = i;
        SortedSet tailSet = this.a.tailSet(danmakuItem);
        if (tailSet.isEmpty()) {
            a(null, -1);
            return;
        }
        danmakuItem = (DanmakuItem) tailSet.first();
        a(danmakuItem, danmakuItem.pos);
    }

    private void b(DanmakuItem danmakuItem) {
        if (danmakuItem.isTop) {
            this.k.setBackgroundResource(R.drawable.top_danmaku_bg);
        } else {
            this.k.setBackgroundResource(R.drawable.hot_danmaku_bg);
        }
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        if (danmakuItem.hasSound) {
            spannableStringBuilder.append("sound");
            spannableStringBuilder.setSpan(this.l, 0, spannableStringBuilder.length(), 17);
        }
        spannableStringBuilder.append(danmakuItem.text);
        this.n.setText(spannableStringBuilder);
        this.o.a(danmakuItem.liked, danmakuItem.likes, new cn.xiaochuankeji.tieba.ui.widget.updown.ViewUpDown.a(this) {
            final /* synthetic */ TopDanmakuView a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
            }

            public void a(int i, int i2, boolean z) {
                if (i == 1) {
                    this.a.d();
                } else if (i == -1) {
                    this.a.e();
                }
            }
        });
    }

    private void a(DanmakuItem danmakuItem, int i) {
        if (danmakuItem != null) {
            this.b.a = false;
            this.b.b = true;
        } else {
            this.b.a = true;
            this.b.b = false;
        }
        this.b.c = danmakuItem;
        this.b.d = false;
        this.b.e = i;
        this.b.f = 3000;
        if (this.b.g != null) {
            this.b.g.e();
            this.b.g = null;
        }
        if (danmakuItem != null && danmakuItem.hasSound) {
            e eVar = new e(danmakuItem.id, danmakuItem.soundUrl);
            eVar.a((cn.xiaochuankeji.tieba.background.danmaku.e.a) this);
            if (!eVar.b()) {
                eVar.d();
            }
            this.b.f = 1073741823;
            this.b.g = eVar;
        }
    }

    private void j() {
        if (this.e) {
            this.d.pause();
            this.m.stop();
        }
    }

    private void k() {
        if (this.e) {
            this.d.start();
            this.m.start();
        }
    }

    private void l() {
        if (this.d != null) {
            this.d.g();
            this.d = null;
            this.e = false;
            this.m.stop();
            if (this.g != null) {
                this.g.a(this, this.f);
            }
        }
    }

    public void a(e eVar, String str) {
        if (!this.b.b || this.b.c.id != eVar.a() || this.b.d) {
        }
    }

    public void b(e eVar, String str) {
        if (this.b.b && this.b.c.id == eVar.a()) {
            this.b.f = (this.c - this.b.e) + 2000;
        }
    }
}
