package cn.xiaochuankeji.tieba.ui.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.push.a.d;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.chat.adapter.b;
import cn.xiaochuankeji.tieba.ui.widget.a.a;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import cn.xiaochuankeji.tieba.widget.StateLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.e.c;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;
import rx.b.g;
import rx.e;

public class NotificationFragment extends f {
    private b a = new b();
    private d b = new d(this) {
        final /* synthetic */ NotificationFragment a;

        {
            this.a = r1;
        }

        public void a() {
            super.a();
            if (this.a.mStateLayout != null) {
                this.a.mStateLayout.setState(this.a.a.getItemCount() > 0 ? 0 : 1);
            }
        }
    };
    @BindView
    CustomEmptyView customEmptyView;
    @BindView
    StateLayout mStateLayout;
    @BindView
    RecyclerView recycler;
    @BindView
    SmartRefreshLayout refreshLayout;

    public static NotificationFragment b() {
        NotificationFragment notificationFragment = new NotificationFragment();
        notificationFragment.setArguments(new Bundle());
        return notificationFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_notify, viewGroup, false);
        ButterKnife.a(this, inflate);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.refreshLayout.b(true);
        this.refreshLayout.a(false);
        this.refreshLayout.a(new c(this) {
            final /* synthetic */ NotificationFragment a;

            {
                this.a = r1;
            }

            public void a_(h hVar) {
                this.a.c();
            }
        });
        this.mStateLayout.a(R.id.refresh).b(R.id.custom_empty_view).setState(1);
        this.customEmptyView.b(R.drawable.ic_empty_notify, "还没有消息提醒噢~");
        this.recycler.setHasFixedSize(true);
        this.recycler.setItemAnimator(new a());
        LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        linearLayoutManager.setInitialPrefetchItemCount(4);
        this.recycler.setLayoutManager(linearLayoutManager);
        this.recycler.setAdapter(this.a);
        this.a.a();
    }

    private void c() {
        rx.d.a(Long.valueOf(cn.xiaochuankeji.tieba.background.a.g().c())).d(new g<Long, Boolean>(this) {
            final /* synthetic */ NotificationFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((Long) obj);
            }

            public Boolean a(Long l) {
                cn.xiaochuankeji.tieba.background.h.d.a().c();
                return Boolean.valueOf(cn.xiaochuankeji.tieba.push.b.b.b(l.longValue()));
            }
        }).b(rx.f.a.c()).c(rx.f.a.c()).a(rx.a.b.a.a()).a(new e<Boolean>(this) {
            final /* synthetic */ NotificationFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Boolean) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                th.printStackTrace();
                if (this.a.refreshLayout != null) {
                    this.a.refreshLayout.m();
                }
            }

            public void a(Boolean bool) {
                if (this.a.refreshLayout != null) {
                    this.a.refreshLayout.m();
                }
                if (bool.booleanValue()) {
                    this.a.a.a();
                    cn.xiaochuankeji.tieba.background.h.d.a().d();
                }
            }
        });
    }

    public void onStart() {
        super.onStart();
        this.a.registerAdapterDataObserver(this.b);
    }

    public void onResume() {
        super.onResume();
        if (cn.xiaochuankeji.tieba.background.a.g().d()) {
            this.mStateLayout.setState(1);
        }
    }

    public void onStop() {
        super.onStop();
        this.a.unregisterAdapterDataObserver(this.b);
    }

    @l(a = ThreadMode.MAIN)
    public void toTopEvent(cn.xiaochuankeji.tieba.push.c.e eVar) {
        if (a()) {
            this.recycler.scrollToPosition(0);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void notifyEvent(cn.xiaochuankeji.tieba.push.c.f fVar) {
        if (a()) {
            this.a.a();
            return;
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.putBoolean("_need_refresh", true);
        }
    }

    protected void a(String str, boolean z) {
        super.a(str, z);
        if (z && a()) {
            cn.xiaochuankeji.tieba.background.h.d.a().c();
            cn.xiaochuankeji.tieba.background.h.d.a().d();
            Bundle arguments = getArguments();
            if (arguments != null && arguments.getBoolean("_need_refresh", false)) {
                arguments.putBoolean("_need_refresh", false);
                this.a.a();
            }
        }
    }
}
