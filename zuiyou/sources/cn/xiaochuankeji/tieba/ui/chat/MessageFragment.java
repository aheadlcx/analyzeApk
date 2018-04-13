package cn.xiaochuankeji.tieba.ui.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.i;
import cn.xiaochuankeji.tieba.push.a.d;
import cn.xiaochuankeji.tieba.push.c.g;
import cn.xiaochuankeji.tieba.push.c.h;
import cn.xiaochuankeji.tieba.ui.base.f;
import cn.xiaochuankeji.tieba.ui.chat.adapter.MessageAdapter;
import cn.xiaochuankeji.tieba.ui.widget.a.a;
import cn.xiaochuankeji.tieba.widget.CustomEmptyView;
import cn.xiaochuankeji.tieba.widget.StateLayout;
import com.izuiyou.a.a.b;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class MessageFragment extends f {
    private MessageAdapter a = new MessageAdapter();
    private d b = new d(this) {
        final /* synthetic */ MessageFragment a;

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
    private OnScrollListener c = new OnScrollListener(this) {
        final /* synthetic */ MessageFragment a;
        private boolean b = false;

        {
            this.a = r2;
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                boolean z = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() > 1;
                if (this.b != z) {
                    this.b = z;
                    b.b();
                    this.a.refreshLayout.m();
                }
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

    public static MessageFragment b() {
        MessageFragment messageFragment = new MessageFragment();
        messageFragment.setArguments(new Bundle());
        return messageFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_message, viewGroup, false);
        ButterKnife.a(this, inflate);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mStateLayout.a(R.id.recycler).b(R.id.custom_empty_view).setState(1);
        this.customEmptyView.b(R.drawable.ic_empty_chat, "若不寻人聊，只能待佳音");
        this.refreshLayout.b(false);
        this.refreshLayout.a(false);
        this.refreshLayout.setNestedScrollingEnabled(false);
        this.recycler.setHasFixedSize(true);
        i.a(this.recycler);
        this.recycler.setItemAnimator(new a());
        LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        linearLayoutManager.setInitialPrefetchItemCount(4);
        this.recycler.setLayoutManager(linearLayoutManager);
        this.recycler.setAdapter(this.a);
        this.a.registerAdapterDataObserver(this.b);
        this.a.a(1);
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        if (cn.xiaochuankeji.tieba.background.a.g().d()) {
            this.a.a();
            this.mStateLayout.setState(1);
            return;
        }
        cn.xiaochuankeji.tieba.push.d.a().a(1);
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        this.a.unregisterAdapterDataObserver(this.b);
        super.onDestroy();
    }

    @l(a = ThreadMode.MAIN)
    public void toTopEvent(g gVar) {
        Bundle arguments = getArguments();
        if (a()) {
            this.recycler.scrollToPosition(0);
            arguments.putBoolean("_need_to_top", false);
            return;
        }
        arguments.putBoolean("_need_to_top", true);
    }

    @l(a = ThreadMode.MAIN)
    public void sessionUpdate(h hVar) {
        Bundle arguments = getArguments();
        if (a()) {
            this.a.a(1);
        } else {
            arguments.putBoolean("_need_refresh", true);
        }
    }

    protected void a(String str, boolean z) {
        super.a(str, z);
        if (z) {
            Bundle arguments = getArguments();
            if (arguments != null && arguments.getBoolean("_need_refresh", false)) {
                arguments.putBoolean("_need_refresh", false);
                this.a.a(1);
            }
            if (arguments != null && arguments.getBoolean("_need_to_top", false)) {
                arguments.putBoolean("_need_to_top", false);
                this.recycler.scrollToPosition(0);
            }
        }
    }
}
