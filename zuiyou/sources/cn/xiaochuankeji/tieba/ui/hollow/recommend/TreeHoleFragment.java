package cn.xiaochuankeji.tieba.ui.hollow.recommend;

import android.arch.lifecycle.q;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
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
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.ui.base.l;
import cn.xiaochuankeji.tieba.ui.hollow.report.ReportPlayHollowJson;
import cn.xiaochuankeji.tieba.ui.hollow.widget.HollowFooter;
import cn.xiaochuankeji.tieba.ui.homepage.PostLoadedTipsView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.e.c;
import org.greenrobot.eventbus.ThreadMode;
import rx.d;
import rx.d$a;
import rx.j;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnCompletionListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnErrorListener;
import tv.danmaku.ijk.media.player.IMediaPlayer$OnInfoListener;

public class TreeHoleFragment extends l implements j {
    private static TreeHoleFragment j;
    private a a;
    private Unbinder b;
    private LinearLayoutManager c;
    @BindView
    RecyclerView contentRecyclerView;
    private i d;
    private TreeHoleViewModel e;
    private Handler f;
    private cn.xiaochuankeji.tieba.common.d.a g;
    private e h;
    private final int i = 0;
    @BindView
    SmartRefreshLayout refreshLayout;
    @BindView
    ImageView tipsEmpty;
    @BindView
    PostLoadedTipsView tipsView;

    public interface a {
    }

    public static TreeHoleFragment a(NavigatorTag navigatorTag) {
        if (j == null) {
            j = new TreeHoleFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("fragment_navigator", navigatorTag);
        j.setArguments(bundle);
        return j;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
        }
        if (this.f == null) {
            this.f = new Handler(this, Looper.getMainLooper()) {
                final /* synthetic */ TreeHoleFragment a;

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
        if (context instanceof a) {
            this.a = (a) context;
        }
        if (this.g == null) {
            this.g = new cn.xiaochuankeji.tieba.common.d.a(context);
        }
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.e = (TreeHoleViewModel) q.a((Fragment) this).a(TreeHoleViewModel.class);
        this.e.a(this.d, (j) this);
    }

    public void onDetach() {
        super.onDetach();
        this.a = null;
        cn.xiaochuankeji.tieba.background.a.p().e().execute(new Runnable(this) {
            final /* synthetic */ TreeHoleFragment a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.g != null) {
                    this.a.g.b(true);
                    this.a.g = null;
                }
            }
        });
    }

    public void onPause() {
        super.onPause();
        if (this.tipsView != null) {
            this.tipsView.setVisibility(8);
        }
        if (this.f != null) {
            this.f.removeCallbacksAndMessages(null);
        }
        if (this.h != null) {
            TreeHoleHolder treeHoleHolder = (TreeHoleHolder) this.contentRecyclerView.findViewHolderForAdapterPosition(this.h.a);
            if (treeHoleHolder != null) {
                treeHoleHolder.b(this.h.b);
            }
        }
        k();
        cn.xiaochuankeji.tieba.ui.hollow.report.a.a().a("navigator");
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.b.a();
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_tree_hole, viewGroup, false);
        this.b = ButterKnife.a(this, inflate);
        j();
        return inflate;
    }

    protected void e() {
        d.b(new d$a<Void>(this) {
            final /* synthetic */ TreeHoleFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super Void> jVar) {
                if (this.a.e == null) {
                    this.a.e = (TreeHoleViewModel) q.a(this.a).a(TreeHoleViewModel.class);
                    this.a.e.a(this.a.d, this.a);
                }
                jVar.onNext(null);
                jVar.onCompleted();
            }
        }).b(rx.f.a.c()).a(rx.a.b.a.a()).b(new j<Void>(this) {
            final /* synthetic */ TreeHoleFragment a;

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
                this.a.e.a(new cn.xiaochuankeji.tieba.ui.recommend.RecommendViewModel.a(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.a.f.post(new Runnable(this) {
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
                        if (this.a.a.d.a() > 4) {
                            this.a.a.refreshLayout.a(true);
                        } else {
                            this.a.a.refreshLayout.a(false);
                        }
                    }
                });
            }
        });
    }

    public void c() {
        super.c();
        k();
    }

    public void b() {
        super.b();
        if (this.tipsView != null) {
            this.tipsView.setVisibility(8);
        }
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void navRefresh(cn.xiaochuankeji.tieba.b.a aVar) {
        CharSequence charSequence = aVar.a;
        NavigatorTag navigatorTag = aVar.b;
        if (!TextUtils.isEmpty(charSequence) && navigatorTag != null && this.contentRecyclerView != null) {
            if (navigatorTag.id == ((NavigatorTag) getArguments().getParcelable("fragment_navigator")).id) {
                this.contentRecyclerView.scrollToPosition(0);
                this.refreshLayout.q();
            }
        }
    }

    private void j() {
        if (this.tipsView != null) {
            this.tipsView.setTipBackground(R.drawable.ic_recommend_tree_bg);
        }
        this.c = new LinearLayoutManager(getContext(), 1, false);
        this.contentRecyclerView.setLayoutManager(this.c);
        this.d = new i(getContext());
        this.contentRecyclerView.setAdapter(this.d);
        this.refreshLayout.a(new c(this) {
            final /* synthetic */ TreeHoleFragment a;

            {
                this.a = r1;
            }

            public void a_(h hVar) {
                if (this.a.e != null) {
                    this.a.e.b();
                }
            }
        });
        this.refreshLayout.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ TreeHoleFragment a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                if (this.a.e != null) {
                    this.a.e.c();
                }
            }
        });
        this.refreshLayout.e(true);
        this.refreshLayout.b(0.0f);
        this.contentRecyclerView.addOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ TreeHoleFragment a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                this.a.c.findFirstVisibleItemPosition();
                this.a.c.findLastVisibleItemPosition();
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

    public void a(String str) {
        if (this.tipsView != null) {
            this.tipsView.setVisibility(0);
            this.tipsView.setText(str);
            this.f.postDelayed(new Runnable(this) {
                final /* synthetic */ TreeHoleFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.tipsView != null) {
                        this.a.tipsView.setVisibility(8);
                    }
                }
            }, 3000);
        }
    }

    public void i() {
        if (this.refreshLayout != null) {
            this.refreshLayout.m();
        }
        if (this.d == null || this.d.getItemCount() <= 0) {
            if (this.tipsEmpty != null) {
                this.tipsEmpty.setVisibility(0);
            }
            if (this.refreshLayout != null) {
                this.refreshLayout.a(false);
                return;
            }
            return;
        }
        if (this.tipsEmpty != null) {
            this.tipsEmpty.setVisibility(8);
        }
        if (this.refreshLayout == null) {
            return;
        }
        if (this.d.a() > 4) {
            this.refreshLayout.a(true);
        } else {
            this.refreshLayout.a(false);
        }
    }

    @UiThread
    public void c(boolean z) {
        if (this.refreshLayout != null && isAdded()) {
            ((HollowFooter) this.refreshLayout.getRefreshFooter()).setNoDataTip(z);
            if (this.refreshLayout != null) {
                this.refreshLayout.n();
                this.refreshLayout.a(this.d.a() > 4);
            }
            if (this.d.getItemCount() > 0) {
                this.tipsEmpty.setVisibility(8);
            }
        }
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void onPlayHollowAudio(e eVar) {
        if (isVisible()) {
            TreeHoleHolder treeHoleHolder;
            if (this.h != null) {
                if (eVar.b.id != this.h.b.id) {
                    treeHoleHolder = (TreeHoleHolder) this.contentRecyclerView.findViewHolderForAdapterPosition(this.h.a);
                    if (treeHoleHolder != null) {
                        treeHoleHolder.b(this.h.b);
                    }
                    k();
                } else if (this.g.isPlaying()) {
                    k();
                    treeHoleHolder = (TreeHoleHolder) this.contentRecyclerView.findViewHolderForAdapterPosition(this.h.a);
                    if (treeHoleHolder != null) {
                        treeHoleHolder.b(this.h.b);
                        return;
                    }
                    return;
                } else {
                    b(this.h.b.audio.url);
                    treeHoleHolder = (TreeHoleHolder) this.contentRecyclerView.findViewHolderForAdapterPosition(this.h.a);
                    if (treeHoleHolder != null) {
                        treeHoleHolder.a(this.h.b);
                        return;
                    }
                    return;
                }
            }
            if (eVar.b != null && eVar.b.audio != null && this.g != null) {
                b(eVar.b.audio.url);
                treeHoleHolder = (TreeHoleHolder) this.contentRecyclerView.findViewHolderForAdapterPosition(eVar.a);
                if (treeHoleHolder != null) {
                    treeHoleHolder.a(eVar.b);
                    this.h = eVar;
                }
            }
        }
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void onPublishHollow(a aVar) {
        if (aVar.a != null) {
            if (this.d != null) {
                this.d.a(aVar.a);
            }
            if (this.e != null) {
                this.e.b(aVar.a);
            }
            if (this.contentRecyclerView != null) {
                this.contentRecyclerView.scrollToPosition(0);
            }
            if (this.tipsEmpty != null) {
                this.tipsEmpty.setVisibility(8);
            }
            this.h = null;
        }
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void onDeleteHollow(c cVar) {
        this.d.a(cVar.a);
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void onUpdateHollowDetail(g gVar) {
        this.e.b(gVar.a);
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            k();
            this.g.a(new IMediaPlayer$OnInfoListener(this) {
                final /* synthetic */ TreeHoleFragment a;

                {
                    this.a = r1;
                }

                public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                    switch (i) {
                        case 701:
                            if (iMediaPlayer.isPlaying()) {
                                break;
                            }
                            break;
                        case IMediaPlayer.MEDIA_INFO_BUFFERING_END /*702*/:
                            if (iMediaPlayer.isPlaying()) {
                                break;
                            }
                            break;
                    }
                    return true;
                }
            });
            this.g.a(new IMediaPlayer$OnCompletionListener(this) {
                final /* synthetic */ TreeHoleFragment a;

                {
                    this.a = r1;
                }

                public void onCompletion(IMediaPlayer iMediaPlayer) {
                    if (this.a.h != null) {
                        this.a.f.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                TreeHoleHolder treeHoleHolder = (TreeHoleHolder) this.a.a.contentRecyclerView.findViewHolderForAdapterPosition(this.a.a.h.a);
                                if (treeHoleHolder != null) {
                                    treeHoleHolder.b(this.a.a.h.b);
                                    this.a.a.h = null;
                                }
                                this.a.a.f.removeMessages(0);
                            }
                        });
                        this.a.g.g();
                    }
                }
            });
            this.g.a(new IMediaPlayer$OnErrorListener(this) {
                final /* synthetic */ TreeHoleFragment a;

                {
                    this.a = r1;
                }

                public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                    return false;
                }
            });
            this.g.a(Uri.parse(str));
            this.g.start();
            l();
        }
    }

    private void k() {
        if (this.g != null) {
            this.g.g();
        }
        if (this.f != null) {
            this.f.removeMessages(0);
        }
    }

    private void l() {
        if (this.h == null || this.g == null) {
            this.f.sendEmptyMessageDelayed(0, 1000);
            return;
        }
        MediaPlayerControl a = this.g.a();
        if (a == null) {
            this.f.sendEmptyMessageDelayed(0, 1000);
            return;
        }
        long currentPosition = (long) (a.getCurrentPosition() / 1000);
        TreeHoleHolder treeHoleHolder = (TreeHoleHolder) this.contentRecyclerView.findViewHolderForAdapterPosition(this.h.a);
        if (treeHoleHolder != null) {
            treeHoleHolder.a(this.h.b, currentPosition);
        }
        this.f.sendEmptyMessageDelayed(0, 1000);
        try {
            ReportPlayHollowJson reportPlayHollowJson = new ReportPlayHollowJson();
            reportPlayHollowJson.ownerId = this.h.b.id;
            reportPlayHollowJson.owner = "flow_xroom";
            reportPlayHollowJson.audioDuration = this.h.b.audio.dur;
            reportPlayHollowJson.audioUri = this.h.b.audio.uri;
            reportPlayHollowJson.deviceType = 0;
            reportPlayHollowJson.playDur = currentPosition;
            reportPlayHollowJson.version = cn.xiaochuankeji.tieba.background.utils.d.a.f;
            cn.xiaochuankeji.tieba.ui.hollow.report.a.a().a(reportPlayHollowJson);
        } catch (Exception e) {
        }
    }
}
