package cn.xiaochuankeji.tieba.ui.hollow.detail;

import android.arch.lifecycle.q;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController.MediaPlayerControl;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.common.d.a;
import cn.xiaochuankeji.tieba.ui.base.l;
import cn.xiaochuankeji.tieba.ui.hollow.recommend.TreeHoleHolder;
import cn.xiaochuankeji.tieba.ui.hollow.recommend.TreeHoleViewModel;
import cn.xiaochuankeji.tieba.ui.hollow.recommend.e;
import cn.xiaochuankeji.tieba.ui.hollow.recommend.f;
import cn.xiaochuankeji.tieba.ui.hollow.recommend.g;
import cn.xiaochuankeji.tieba.ui.hollow.recommend.i;
import cn.xiaochuankeji.tieba.ui.hollow.recommend.j;
import cn.xiaochuankeji.tieba.ui.hollow.report.ReportPlayHollowJson;
import cn.xiaochuankeji.tieba.ui.hollow.widget.HollowFooter;
import cn.xiaochuankeji.tieba.ui.homepage.PostLoadedTipsView;
import cn.xiaochuankeji.tieba.ui.recommend.RecommendViewModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.e.c;
import org.greenrobot.eventbus.ThreadMode;
import rx.d;
import rx.d$a;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnErrorListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnInfoListener;

public class FragmentDiscovery extends l implements j {
    static final /* synthetic */ boolean a = (!FragmentDiscovery.class.desiredAssertionStatus());
    private TreeHoleViewModel b;
    private i c;
    @BindView
    RecyclerView contentRecyclerView;
    private Unbinder d;
    private e e;
    private a f;
    private Handler g;
    @BindView
    SmartRefreshLayout refreshLayout;
    @BindView
    ImageView tipsEmpty;
    @BindView
    PostLoadedTipsView tipsView;

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_tree_hole, null);
        this.d = ButterKnife.a(this, inflate);
        j();
        return inflate;
    }

    private void j() {
        this.tipsView.setTipBackground(R.drawable.ic_recommend_tree_bg);
        this.contentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.c = new i(getContext());
        this.contentRecyclerView.setAdapter(this.c);
        this.refreshLayout.a(new c(this) {
            final /* synthetic */ FragmentDiscovery a;

            {
                this.a = r1;
            }

            public void a_(h hVar) {
                if (this.a.b != null) {
                    this.a.b.b();
                }
            }
        });
        this.refreshLayout.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ FragmentDiscovery a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                if (this.a.b != null) {
                    this.a.b.c();
                }
            }
        });
        this.refreshLayout.e(true);
        this.refreshLayout.b(0.0f);
        this.contentRecyclerView.addOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ FragmentDiscovery a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                int i3 = 0;
                while (i3 <= recyclerView.getChildCount()) {
                    View childAt = recyclerView.getChildAt(i3);
                    if (childAt != null) {
                        TreeHoleHolder treeHoleHolder = (TreeHoleHolder) recyclerView.findContainingViewHolder(childAt);
                        if (treeHoleHolder != null) {
                            treeHoleHolder.a();
                        }
                        i3++;
                    } else {
                        return;
                    }
                }
            }
        });
    }

    protected void e() {
        d.b(new d$a<Void>(this) {
            final /* synthetic */ FragmentDiscovery a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((rx.j) obj);
            }

            public void a(rx.j<? super Void> jVar) {
                if (this.a.b == null) {
                    this.a.b = (TreeHoleViewModel) q.a(this.a).a(TreeHoleViewModel.class);
                    this.a.b.a(this.a.c, this.a);
                }
                jVar.onNext(null);
                jVar.onCompleted();
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new rx.j<Void>(this) {
            final /* synthetic */ FragmentDiscovery a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Void) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(Void voidR) {
                this.a.b.a(new RecommendViewModel.a(this) {
                    final /* synthetic */ AnonymousClass6 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.a.g.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.a.refreshLayout.a(false);
                                this.a.a.a.refreshLayout.a(0, 300, 0.3f);
                            }
                        });
                    }

                    public void b() {
                        this.a.a.tipsEmpty.setVisibility(8);
                        if (this.a.a.c.a() > 4) {
                            this.a.a.refreshLayout.a(true);
                        } else {
                            this.a.a.refreshLayout.a(false);
                        }
                    }
                });
            }
        });
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.g == null) {
            this.g = new Handler(this, Looper.getMainLooper()) {
                final /* synthetic */ FragmentDiscovery a;

                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    switch (message.what) {
                        case 0:
                            this.a.l();
                            return;
                        default:
                            return;
                    }
                }
            };
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.f == null) {
            this.f = new a(context);
        }
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.b = (TreeHoleViewModel) q.a((Fragment) this).a(TreeHoleViewModel.class);
        this.b.a(this.c, (j) this);
    }

    public void onDetach() {
        super.onDetach();
        cn.xiaochuankeji.tieba.background.a.p().e().execute(new Runnable(this) {
            final /* synthetic */ FragmentDiscovery a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.f != null) {
                    this.a.f.b(true);
                    this.a.f = null;
                }
            }
        });
    }

    public void onPause() {
        super.onPause();
        this.tipsView.setVisibility(8);
        if (this.g != null) {
            this.g.removeCallbacksAndMessages(null);
        }
        if (this.e != null) {
            TreeHoleHolder treeHoleHolder = (TreeHoleHolder) this.contentRecyclerView.findViewHolderForAdapterPosition(this.e.a);
            if (treeHoleHolder != null) {
                treeHoleHolder.b(this.e.b);
            }
        }
        k();
        cn.xiaochuankeji.tieba.ui.hollow.report.a.a().a("navigator");
    }

    public void b(boolean z) {
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.d.a();
    }

    public void c() {
        super.c();
        k();
    }

    public void b() {
        super.b();
        this.tipsView.setVisibility(8);
    }

    public void a(String str) {
        if (this.tipsView != null) {
            this.tipsView.setVisibility(0);
            this.tipsView.setText(str);
            this.g.postDelayed(new Runnable(this) {
                final /* synthetic */ FragmentDiscovery a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.tipsView.setVisibility(8);
                }
            }, 3000);
        }
    }

    public void i() {
        if (this.refreshLayout != null) {
            this.refreshLayout.m();
        }
        if (this.tipsEmpty != null) {
            if (this.c == null || this.c.getItemCount() <= 0) {
                this.tipsEmpty.setVisibility(0);
                return;
            }
            this.tipsEmpty.setVisibility(8);
            if (this.c.a() > 4) {
                this.refreshLayout.a(true);
            } else {
                this.refreshLayout.a(false);
            }
        }
    }

    public void c(boolean z) {
        if (this.refreshLayout != null) {
            HollowFooter hollowFooter = (HollowFooter) this.refreshLayout.getRefreshFooter();
            if (a || hollowFooter != null) {
                hollowFooter.setNoDataTip(z);
                this.refreshLayout.n();
                if (this.c.getItemCount() > 0) {
                    this.tipsEmpty.setVisibility(8);
                    return;
                }
                return;
            }
            throw new AssertionError();
        }
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void onPlayHollowAudio(e eVar) {
        if (isVisible()) {
            TreeHoleHolder treeHoleHolder;
            if (this.e != null) {
                if (eVar.b.id != this.e.b.id) {
                    treeHoleHolder = (TreeHoleHolder) this.contentRecyclerView.findViewHolderForAdapterPosition(this.e.a);
                    if (treeHoleHolder != null) {
                        treeHoleHolder.b(this.e.b);
                    }
                    k();
                } else if (this.f.isPlaying()) {
                    k();
                    treeHoleHolder = (TreeHoleHolder) this.contentRecyclerView.findViewHolderForAdapterPosition(this.e.a);
                    if (treeHoleHolder != null) {
                        treeHoleHolder.b(this.e.b);
                        return;
                    }
                    return;
                } else {
                    b(this.e.b.audio.url);
                    treeHoleHolder = (TreeHoleHolder) this.contentRecyclerView.findViewHolderForAdapterPosition(this.e.a);
                    if (treeHoleHolder != null) {
                        treeHoleHolder.a(this.e.b);
                        return;
                    }
                    return;
                }
            }
            if (eVar.b != null && eVar.b.audio != null && this.f != null) {
                b(eVar.b.audio.url);
                treeHoleHolder = (TreeHoleHolder) this.contentRecyclerView.findViewHolderForAdapterPosition(eVar.a);
                if (treeHoleHolder != null) {
                    treeHoleHolder.a(eVar.b);
                    this.e = eVar;
                }
            }
        }
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void onPublishHollow(f fVar) {
        if (fVar.a != null) {
            this.c.a(fVar.a);
            this.contentRecyclerView.scrollToPosition(0);
            this.e = null;
            this.tipsEmpty.setVisibility(8);
        }
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void onDeleteHollow(cn.xiaochuankeji.tieba.ui.hollow.recommend.c cVar) {
        this.c.a(cVar.a);
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void onUpdateHollowDetail(g gVar) {
        this.b.b(gVar.a);
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            k();
            this.f.a(new IMediaPlayer$OnInfoListener(this) {
                final /* synthetic */ FragmentDiscovery a;

                {
                    this.a = r1;
                }

                public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                    switch (i) {
                    }
                    return true;
                }
            });
            this.f.a(new IMediaPlayer$OnCompletionListener(this) {
                final /* synthetic */ FragmentDiscovery a;

                {
                    this.a = r1;
                }

                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    if (this.a.e != null) {
                        this.a.g.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                TreeHoleHolder treeHoleHolder = (TreeHoleHolder) this.a.a.contentRecyclerView.findViewHolderForAdapterPosition(this.a.a.e.a);
                                if (treeHoleHolder != null) {
                                    treeHoleHolder.b(this.a.a.e.b);
                                    this.a.a.e = null;
                                }
                                this.a.a.g.removeMessages(0);
                            }
                        });
                        this.a.f.g();
                    }
                }
            });
            this.f.a(new IMediaPlayer$OnErrorListener(this) {
                final /* synthetic */ FragmentDiscovery a;

                {
                    this.a = r1;
                }

                public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                    return false;
                }
            });
            this.f.a(Uri.parse(str));
            this.f.start();
            l();
        }
    }

    private void k() {
        if (this.f != null) {
            this.f.g();
        }
        if (this.g != null) {
            this.g.removeMessages(0);
        }
    }

    private void l() {
        if (this.e == null || this.f == null) {
            this.g.sendEmptyMessageDelayed(0, 1000);
            return;
        }
        MediaPlayerControl a = this.f.a();
        if (a == null) {
            this.g.sendEmptyMessageDelayed(0, 1000);
            return;
        }
        long currentPosition = (long) (a.getCurrentPosition() / 1000);
        TreeHoleHolder treeHoleHolder = (TreeHoleHolder) this.contentRecyclerView.findViewHolderForAdapterPosition(this.e.a);
        if (treeHoleHolder != null) {
            treeHoleHolder.a(this.e.b, currentPosition);
        }
        this.g.sendEmptyMessageDelayed(0, 1000);
        try {
            ReportPlayHollowJson reportPlayHollowJson = new ReportPlayHollowJson();
            reportPlayHollowJson.ownerId = this.e.b.id;
            reportPlayHollowJson.owner = "flow_xroom";
            reportPlayHollowJson.audioDuration = this.e.b.audio.dur;
            reportPlayHollowJson.audioUri = this.e.b.audio.uri;
            reportPlayHollowJson.deviceType = 0;
            reportPlayHollowJson.playDur = currentPosition;
            reportPlayHollowJson.version = cn.xiaochuankeji.tieba.background.utils.d.a.f;
            cn.xiaochuankeji.tieba.ui.hollow.report.a.a().a(reportPlayHollowJson);
        } catch (Exception e) {
        }
    }
}
