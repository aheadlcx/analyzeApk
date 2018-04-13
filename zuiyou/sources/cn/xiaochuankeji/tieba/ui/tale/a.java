package cn.xiaochuankeji.tieba.ui.tale;

import android.arch.lifecycle.q;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.tale.FollowPostThemeJson;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.homepage.PostLoadedTipsView;
import cn.xiaochuankeji.tieba.ui.recommend.b;
import cn.xiaochuankeji.tieba.ui.tale.viewmodel.FollowPostFeedModel;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.e.c;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class a extends f implements b, cn.xiaochuankeji.tieba.ui.tale.viewmodel.FollowPostFeedModel.a {
    private RecyclerView a;
    private FollowPostAdapter b;
    private SmartRefreshLayout c;
    private FollowPostFeedModel d;
    private LinearLayoutManager e;
    private CustomEmptyView f;
    private boolean g;
    private PostLoadedTipsView h;

    public static a a(NavigatorTag navigatorTag) {
        a aVar = new a();
        Bundle bundle = new Bundle();
        bundle.putParcelable("fragment_navigator", navigatorTag);
        aVar.setArguments(bundle);
        return aVar;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_tale_feed, null);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.a = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.c = (SmartRefreshLayout) view.findViewById(R.id.root);
        this.f = (CustomEmptyView) view.findViewById(R.id.custom_empty_view);
        this.h = (PostLoadedTipsView) view.findViewById(R.id.tips_view);
        this.e = new LinearLayoutManager(getActivity());
        this.e.setOrientation(1);
        this.a.setLayoutManager(this.e);
        this.d = (FollowPostFeedModel) q.a((Fragment) this).a(FollowPostFeedModel.class);
        this.d.a((b) this, (cn.xiaochuankeji.tieba.ui.tale.viewmodel.FollowPostFeedModel.a) this);
        if (this.b == null) {
            this.b = new FollowPostAdapter(getActivity());
            this.a.setAdapter(this.b);
            this.d.a(this.b);
            this.d.b();
        } else {
            this.a.setAdapter(this.b);
            this.d.a(this.b);
        }
        this.c.a(new c(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a_(h hVar) {
                this.a.d.c();
            }
        });
        this.c.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.d.d();
                cn.xiaochuankeji.tieba.ui.homepage.f fVar = new cn.xiaochuankeji.tieba.ui.homepage.f(true);
                fVar.b = true;
                org.greenrobot.eventbus.c.a().d(fVar);
            }
        });
        this.a.addOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ a a;
            private int b = -1;

            {
                this.a = r2;
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                boolean z = true;
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0) {
                    int i2;
                    final int findFirstVisibleItemPosition = this.a.e.findFirstVisibleItemPosition();
                    if (findFirstVisibleItemPosition > 4) {
                        i2 = 1;
                    } else {
                        i2 = 2;
                    }
                    if (this.b != i2) {
                        this.b = i2;
                        cn.xiaochuankeji.tieba.background.a.p().b().execute(new Runnable(this) {
                            final /* synthetic */ AnonymousClass3 b;

                            public void run() {
                                cn.xiaochuankeji.tieba.background.a.a().edit().putInt("key_tale_rec_visible_pos", findFirstVisibleItemPosition).apply();
                            }
                        });
                        org.greenrobot.eventbus.c a = org.greenrobot.eventbus.c.a();
                        if (findFirstVisibleItemPosition <= 4) {
                            z = false;
                        }
                        a.d(new cn.xiaochuankeji.tieba.ui.homepage.f(z));
                    }
                }
            }
        });
    }

    @l(a = ThreadMode.MAIN)
    public void navRefresh(cn.xiaochuankeji.tieba.b.a aVar) {
        CharSequence charSequence = aVar.a;
        NavigatorTag navigatorTag = aVar.b;
        if (!TextUtils.isEmpty(charSequence) && navigatorTag != null && this.a != null && this.c != null) {
            if (navigatorTag.id == ((NavigatorTag) getArguments().getParcelable("fragment_navigator")).id) {
                this.a.scrollToPosition(0);
                this.c.q();
            }
        }
    }

    public void a(boolean z, String str, int i, boolean z2) {
        if (!(this.c == null || isDetached())) {
            this.c.m();
            if (z2) {
                this.c.p();
                this.c.a(true);
            } else {
                this.c.a(false);
            }
        }
        cn.xiaochuankeji.tieba.background.a.a().edit().putInt("key_tale_rec_visible_pos", 0).apply();
        if (!z) {
            g.a(str);
        }
        if (z) {
            cn.xiaochuankeji.tieba.background.post.b.a().b(System.currentTimeMillis());
            if (this.g) {
                this.f.setVisibility(8);
                this.g = false;
            }
            a(null, i);
        }
        if (!z && this.g) {
            this.f.b();
            this.f.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.d.c();
                }
            });
        }
    }

    public void a(boolean z, String str, boolean z2) {
        if (!(this.c == null || isDetached())) {
            if (z2) {
                this.c.n();
            } else {
                this.c.o();
            }
        }
        if (!z) {
            g.a(str);
        }
        if (this.b.getItemCount() <= 4) {
            org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.homepage.f(false));
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        getActivity();
        if (-1 == i2 && 4 == i) {
            FollowPostThemeJson followPostThemeJson = (FollowPostThemeJson) intent.getParcelableExtra("create_data");
            if (this.b != null && this.a != null) {
                this.b.a(followPostThemeJson);
                this.a.scrollToPosition(0);
            }
        }
    }

    public void b() {
        int i = cn.xiaochuankeji.tieba.background.a.a().getInt("key_tale_rec_visible_pos", 0);
        this.a.scrollToPosition(i);
        if (!(i == 0 || cn.xiaochuankeji.tieba.background.post.b.a().f())) {
            a("上次您看到这里", 0);
            cn.xiaochuankeji.tieba.background.post.b.a().a(true);
        }
        if (cn.xiaochuankeji.tieba.background.post.b.a().b()) {
            this.c.q();
        }
        this.g = false;
    }

    public void c() {
        this.g = true;
    }

    private void a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            if (i > 0) {
                str = "为你选出" + i + "条好内容";
            } else {
                str = "暂无推荐，到话题里看看";
            }
        }
        this.h.setText(str);
        this.h.setVisibility(0);
        this.h.postDelayed(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.h.setVisibility(8);
            }
        }, 1500);
    }
}
