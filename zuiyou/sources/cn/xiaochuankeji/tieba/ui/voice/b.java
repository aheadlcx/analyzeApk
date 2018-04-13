package cn.xiaochuankeji.tieba.ui.voice;

import android.arch.lifecycle.q;
import android.content.Context;
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
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.homepage.PostLoadedTipsView;
import cn.xiaochuankeji.tieba.ui.topic.data.PostDataBean;
import cn.xiaochuankeji.tieba.ui.topic.holder.VoiceViewHolder;
import cn.xiaochuankeji.tieba.ui.voice.b.d;
import cn.xiaochuankeji.tieba.ui.voice.model.VoiceModel;
import cn.xiaochuankeji.tieba.ui.voice.model.VoiceModel.a;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.e.c;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class b extends f implements cn.xiaochuankeji.tieba.ui.recommend.b, cn.xiaochuankeji.tieba.ui.voice.b.b, a {
    private RecyclerView a;
    private a b;
    private SmartRefreshLayout c;
    private VoiceModel d;
    private LinearLayoutManager e;
    private CustomEmptyView f;
    private boolean g;
    private PostLoadedTipsView h;
    private NavigatorTag i;
    private cn.xiaochuankeji.tieba.ui.voice.model.VoiceModel.b j;

    public static b a(NavigatorTag navigatorTag) {
        b bVar = new b();
        Bundle bundle = new Bundle();
        bundle.putParcelable("fragment_navigator", navigatorTag);
        bVar.setArguments(bundle);
        return bVar;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.i = (NavigatorTag) getArguments().getParcelable("fragment_navigator");
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_voice_feed, null);
        this.j = null;
        return inflate;
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
        this.a.getRecycledViewPool().setMaxRecycledViews(1, 10);
        this.a.getRecycledViewPool().setMaxRecycledViews(2, 10);
        this.d = (VoiceModel) q.a((Fragment) this).a(VoiceModel.class);
        this.d.a((cn.xiaochuankeji.tieba.ui.recommend.b) this, (a) this);
        if (this.b == null) {
            this.b = new a(getActivity(), this.i);
            this.a.setAdapter(this.b);
            this.d.a(this.b);
            this.d.b();
        } else {
            this.a.setAdapter(this.b);
            this.d.a(this.b);
        }
        this.c.a(new c(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a_(h hVar) {
                if (this.a.j == null) {
                    this.a.d.a("down", false);
                    return;
                }
                this.a.d.a(this.a.j.a, this.a.j.b);
                this.a.j = null;
            }
        });
        this.c.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.d.c();
                cn.xiaochuankeji.tieba.ui.homepage.f fVar = new cn.xiaochuankeji.tieba.ui.homepage.f(true);
                fVar.b = true;
                org.greenrobot.eventbus.c.a().d(fVar);
            }
        });
        this.a.addOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ b a;
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
                        cn.xiaochuankeji.tieba.background.a.p().b().execute(new Runnable(this) {
                            final /* synthetic */ AnonymousClass3 b;

                            public void run() {
                                cn.xiaochuankeji.tieba.background.a.a().edit().putInt("key_tale_rec_visible_pos", findFirstVisibleItemPosition).apply();
                            }
                        });
                        this.b = i2;
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
    public void addPost(cn.xiaochuankeji.tieba.ui.voice.a.b bVar) {
        if (this.b != null && bVar != null && bVar.a != null) {
            this.b.a(bVar.a);
            this.e.scrollToPosition(0);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void deletePost(cn.xiaochuankeji.tieba.ui.my.mypost.a aVar) {
        if (this.b != null && aVar != null) {
            this.b.a(aVar.a);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void navRefresh(cn.xiaochuankeji.tieba.b.a aVar) {
        CharSequence charSequence = aVar.a;
        NavigatorTag navigatorTag = aVar.b;
        if (!TextUtils.isEmpty(charSequence) && navigatorTag != null && this.a != null && this.c != null) {
            if (navigatorTag.id == ((NavigatorTag) getArguments().getParcelable("fragment_navigator")).id) {
                this.a.scrollToPosition(0);
                this.j = new cn.xiaochuankeji.tieba.ui.voice.model.VoiceModel.b("homebutton", false);
                this.c.q();
            }
        }
    }

    public void onResume() {
        super.onResume();
        d.a().a((cn.xiaochuankeji.tieba.ui.voice.b.b) this);
    }

    public void onPause() {
        super.onPause();
        d.a().b((cn.xiaochuankeji.tieba.ui.voice.b.b) this);
    }

    public void b() {
        long j = d.a().b().b;
        if (this.b != null) {
            int i = 0;
            while (i < this.b.a().size()) {
                if (((PostDataBean) this.b.a().get(i)).getId() == j) {
                    break;
                }
                i++;
            }
            i = -1;
            if (i != -1) {
                VoiceViewHolder voiceViewHolder = (VoiceViewHolder) this.a.findViewHolderForAdapterPosition(i);
                if (voiceViewHolder != null) {
                    voiceViewHolder.a();
                    voiceViewHolder.b();
                }
            }
        }
    }

    public void a(cn.xiaochuankeji.tieba.ui.voice.b.c cVar) {
        if (this.b != null) {
            int i = 0;
            while (i < this.b.a().size()) {
                if (((PostDataBean) this.b.a().get(i)).getId() == d.a().b().a) {
                    break;
                }
                i++;
            }
            i = -1;
            if (i != -1) {
                VoiceViewHolder voiceViewHolder = (VoiceViewHolder) this.a.findViewHolderForAdapterPosition(i);
                if (voiceViewHolder != null) {
                    voiceViewHolder.a();
                }
            }
        }
        if (cVar.c == 3) {
            b();
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
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.d.a("down", false);
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

    public void c() {
        int i = cn.xiaochuankeji.tieba.background.a.a().getInt("key_tale_rec_visible_pos", 0);
        this.a.scrollToPosition(i);
        if (!(i == 0 || cn.xiaochuankeji.tieba.background.post.b.a().f())) {
            a("上次您看到这里", 0);
            cn.xiaochuankeji.tieba.background.post.b.a().a(true);
        }
        if (cn.xiaochuankeji.tieba.background.post.b.a().b()) {
            this.j = new cn.xiaochuankeji.tieba.ui.voice.model.VoiceModel.b("down", true);
            this.c.q();
        }
        this.g = false;
    }

    public void d() {
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
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.h.setVisibility(8);
            }
        }, 1500);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    @l(a = ThreadMode.MAIN)
    public void onFollowMember(cn.xiaochuankeji.tieba.ui.topic.data.b bVar) {
        if (bVar != null) {
            this.b.a(bVar.b, bVar.a);
        }
    }
}
