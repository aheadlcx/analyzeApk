package cn.xiaochuankeji.tieba.ui.chat;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.i;
import cn.xiaochuankeji.tieba.push.c.g;
import cn.xiaochuankeji.tieba.push.c.h;
import cn.xiaochuankeji.tieba.push.d;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.chat.adapter.MessageAdapter;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class FlowSessionActivity extends a {
    private MessageAdapter a = new MessageAdapter();
    @BindView
    NavigationBar navBar;
    @BindView
    RecyclerView recycler;

    protected int a() {
        return R.layout.activity_flow_session;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        this.recycler.setHasFixedSize(false);
        i.a(this.recycler);
        this.recycler.setItemAnimator(new cn.xiaochuankeji.tieba.ui.widget.a.a());
        this.recycler.addItemDecoration(new cn.xiaochuankeji.tieba.widget.a());
        LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        linearLayoutManager.setInitialPrefetchItemCount(8);
        this.recycler.setLayoutManager(linearLayoutManager);
        this.recycler.setAdapter(this.a);
        this.navBar.setTitle("树洞消息");
        e();
        this.a.a(2);
    }

    private void e() {
        this.navBar.b();
        this.navBar.c();
        this.navBar.e();
        this.navBar.setListener(new NavigationBar.a(this) {
            final /* synthetic */ FlowSessionActivity a;

            {
                this.a = r1;
            }

            public void r() {
                this.a.onBackPressed();
            }

            public void s() {
            }

            public void t() {
            }

            public void u() {
            }
        });
    }

    protected void onResume() {
        super.onResume();
        d.a().a(2);
    }

    @l(a = ThreadMode.MAIN)
    public void toTopEvent(g gVar) {
        if (this.recycler != null) {
            this.recycler.scrollToPosition(0);
        }
    }

    @l(a = ThreadMode.MAIN)
    public void sessionUpdate(h hVar) {
        if (this.a != null) {
            this.a.a(2);
        }
    }
}
