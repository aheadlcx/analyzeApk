package cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.json.UgcVideoDanmakuJson;
import cn.xiaochuankeji.tieba.ui.base.j;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.c;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.g.a;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.b.d;
import cn.xiaochuankeji.tieba.ui.widget.g;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView.b;
import java.util.List;

public class e extends j {
    private UltimateRecyclerView a;
    private View b;
    private View c;
    private d d;
    private a e;
    private cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.e f;

    public e(@NonNull Context context, cn.xiaochuankeji.tieba.ui.ugcvideodetail.danmaku.a.e eVar) {
        super(context);
        this.f = eVar;
    }

    protected View a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.view_sub_danmakus_container, null);
    }

    protected void a(View view) {
        this.a = (UltimateRecyclerView) view.findViewById(R.id.recyclerView);
        this.b = view.findViewById(R.id.vEmpty);
        this.c = view.findViewById(R.id.vContainer);
        this.d = new d(new a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                if (z) {
                    g.a((Activity) this.a.e_());
                } else {
                    g.c((Activity) this.a.e_());
                }
            }

            public void b(boolean z) {
                this.a.b.setVisibility(z ? 0 : 8);
            }

            public void a() {
                this.a.a.a(0);
            }

            public void a(List<UgcVideoDanmakuJson> list, boolean z, boolean z2) {
                if (this.a.a.getVisibility() != 0) {
                    this.a.a.setVisibility(0);
                }
                this.a.e.a((List) list, z2);
                if (!z) {
                    this.a.a.h();
                } else if (!this.a.a.g()) {
                    this.a.a.f();
                }
            }

            public void a(long j) {
                this.a.f.a(j);
            }
        });
        f();
    }

    private void f() {
        this.e = new a(e_(), 2, new c(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(UgcVideoDanmakuJson ugcVideoDanmakuJson) {
                this.a.f.a(ugcVideoDanmakuJson);
            }

            public void b(UgcVideoDanmakuJson ugcVideoDanmakuJson) {
                this.a.f.b(ugcVideoDanmakuJson);
            }

            public void a() {
                this.a.d.c();
            }
        });
        this.a.setLayoutManager(new LinearLayoutManager(e_()));
        this.a.setOnLoadMoreListener(new b(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                this.a.d.a();
            }
        });
        this.a.a(R.layout.common_empty_view, UltimateRecyclerView.a, UltimateRecyclerView.a);
        this.a.setLoadMoreView(new cn.xiaochuankeji.tieba.ui.ugcvideodetail.j(e_()));
        this.a.setAdapter(this.e);
        this.a.h();
    }

    public void a(UgcVideoDanmakuJson ugcVideoDanmakuJson) {
        this.d.a(ugcVideoDanmakuJson);
    }

    public void a(final long j, final int i) {
        this.c.setVisibility(0);
        this.c.post(new Runnable(this) {
            final /* synthetic */ e c;

            public void run() {
                this.c.d.a(j, i);
            }
        });
    }

    public boolean c() {
        return this.c.getVisibility() == 0;
    }

    public RecyclerView d() {
        return this.a.g;
    }

    public void e() {
        this.d.b();
        this.c.setVisibility(8);
    }
}
