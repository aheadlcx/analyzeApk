package cn.xiaochuankeji.tieba.ui.recommend;

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
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.data.tag.NavigatorTag;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.homepage.PostLoadedTipsView;
import cn.xiaochuankeji.tieba.ui.post.LikedUsersActivity;
import cn.xiaochuankeji.tieba.ui.recommend.RecommendViewModel.a;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.e.c;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class NewRecommendFragment extends f implements b {
    private static boolean a = false;
    private Unbinder b;
    private a c;
    private RecommendViewModel d;
    private LinearLayoutManager e;
    private boolean f;
    private NavigatorTag g;
    @BindView
    RecyclerView recyclerView;
    @BindView
    SmartRefreshLayout refreshLayout;
    @BindView
    PostLoadedTipsView tipsView;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.g = (NavigatorTag) getArguments().getParcelable("fragment_navigator");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.d = (RecommendViewModel) q.a((Fragment) this).a(RecommendViewModel.class);
        this.d.a(this.c);
        this.d.a((b) this);
        this.d.a(new a(this) {
            final /* synthetic */ NewRecommendFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.refreshLayout.a(0, 300, 0.3f);
            }

            public void b() {
                if (!NewRecommendFragment.a) {
                    NewRecommendFragment.a = true;
                    this.a.a("上次看到这里", 0);
                }
            }
        }, this.g);
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_recommend_new, viewGroup, false);
        this.b = ButterKnife.a(this, inflate);
        c();
        return inflate;
    }

    private void c() {
        this.e = new LinearLayoutManager(getContext(), 1, false);
        this.recyclerView.setLayoutManager(this.e);
        this.c = new a(this, this.g);
        this.recyclerView.setAdapter(this.c);
        this.refreshLayout.a(new c(this) {
            final /* synthetic */ NewRecommendFragment a;

            {
                this.a = r1;
            }

            public void a_(h hVar) {
                this.a.d.a(false);
            }
        });
        this.refreshLayout.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ NewRecommendFragment a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.f = true;
                this.a.d.b(false);
            }
        });
        this.recyclerView.addOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ NewRecommendFragment a;
            private int b = -1;

            {
                this.a = r2;
            }

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                int i3;
                boolean z = true;
                super.onScrolled(recyclerView, i, i2);
                int findFirstVisibleItemPosition = this.a.e.findFirstVisibleItemPosition();
                if (findFirstVisibleItemPosition > 4) {
                    i3 = 1;
                } else {
                    i3 = 2;
                }
                if (this.b != i3) {
                    this.b = i3;
                    if (findFirstVisibleItemPosition <= 4) {
                        z = false;
                    }
                    cn.xiaochuankeji.tieba.ui.homepage.f fVar = new cn.xiaochuankeji.tieba.ui.homepage.f(z);
                    if (findFirstVisibleItemPosition > 4) {
                        fVar.b = this.a.f;
                    }
                    org.greenrobot.eventbus.c.a().d(fVar);
                }
                i3 = this.a.e.getItemCount();
                int findLastVisibleItemPosition = this.a.e.findLastVisibleItemPosition();
                if (i3 > 7 && findLastVisibleItemPosition + 4 > i3) {
                    this.a.refreshLayout.g(0);
                }
                if (i3 > 0 && this.a.e.getChildAt(0) != null) {
                    i3 = this.a.e.findLastVisibleItemPosition() - this.a.e.findLastVisibleItemPosition() > 2 ? findFirstVisibleItemPosition + 1 : this.a.e.getChildAt(0).getY() + ((float) this.a.e.getChildAt(0).getMeasuredHeight()) < ((float) (e.c() / 2)) ? findFirstVisibleItemPosition + 1 : findFirstVisibleItemPosition;
                    cn.xiaochuankeji.tieba.background.a.a().edit().putInt("key_all_rec_visible_pos", i3).apply();
                }
            }
        });
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.b.a();
    }

    public void a(boolean z, String str, int i, boolean z2) {
        if (!(this.refreshLayout == null || isDetached())) {
            this.refreshLayout.m();
        }
        if (z) {
            this.e.scrollToPosition(0);
            a(null, i);
            return;
        }
        g.a(str);
    }

    public void a(boolean z, String str, boolean z2) {
        this.f = true;
        if (!(this.refreshLayout == null || isDetached())) {
            this.refreshLayout.n();
        }
        if (!z) {
            g.a(str);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void cancelLikeState(LikedUsersActivity.a aVar) {
        a(aVar.a);
    }

    @l(a = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_POST_CANCEL_LIKE) {
            a(((Long) messageEvent.getData()).longValue());
        }
    }

    private void a(long j) {
        int a = this.c.a(j);
        if (a >= 0) {
            View findViewByPosition = this.e.findViewByPosition(a);
            if (findViewByPosition != null) {
                PostItemUpDownView postItemUpDownView = (PostItemUpDownView) findViewByPosition.findViewById(R.id.postItemUpDownView);
                if (postItemUpDownView != null) {
                    postItemUpDownView.b();
                }
            }
        }
    }

    private void a(String str, int i) {
        CharSequence replace;
        if (!TextUtils.isEmpty(str)) {
            replace = str.replace("${count}", String.valueOf(i));
        } else if (i > 0) {
            replace = "为你选出" + i + "条好帖";
        } else {
            replace = "暂无推荐，到话题里看看";
        }
        this.tipsView.setText(replace);
        this.tipsView.setVisibility(0);
        this.tipsView.postDelayed(new Runnable(this) {
            final /* synthetic */ NewRecommendFragment a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.tipsView != null) {
                    this.a.tipsView.setVisibility(8);
                }
            }
        }, 1500);
    }
}
