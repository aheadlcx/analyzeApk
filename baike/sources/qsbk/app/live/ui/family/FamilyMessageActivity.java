package qsbk.app.live.ui.family;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.r0adkll.slidr.Slidr;
import java.util.ArrayList;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.widget.EmptyPlaceholderView;
import qsbk.app.core.widget.EmptyPlaceholderView.OnEmptyClickListener;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth;
import qsbk.app.live.R;

public class FamilyMessageActivity extends BaseActivity implements OnEmptyClickListener {
    private RecyclerView a;
    private MessageAdapter b;
    private LinearLayoutManager c;
    private ArrayList<Message> d = new ArrayList();
    private boolean e = false;
    private boolean f = true;
    private SwipeRefreshLayoutBoth g;
    private int h = 1;
    private long i = 0;
    private EmptyPlaceholderView j;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!isTaskRoot()) {
            getWindow().setBackgroundDrawableResource(R.color.transparent);
            Slidr.attach(this, AppUtils.getEdgeSlidrConfig());
        }
    }

    protected int getLayoutId() {
        return R.layout.activity_family_message;
    }

    protected void initView() {
        this.g = (SwipeRefreshLayoutBoth) findViewById(R.id.refresher);
        this.a = (RecyclerView) findViewById(R.id.recyclerview);
        this.j = (EmptyPlaceholderView) findViewById(R.id.empty);
    }

    protected void initData() {
        setTitle(AppUtils.getInstance().getAppContext().getString(R.string.msg_drawer_message_family));
        setOnUpClickListener(new au(this));
        this.g.setOnRefreshListener(new av(this));
        this.c = new LinearLayoutManager(getActivity());
        this.c.setOrientation(1);
        this.a.setLayoutManager(this.c);
        this.b = new MessageAdapter(getActivity(), this.d);
        this.a.setAdapter(this.b);
        this.a.setItemAnimator(new DefaultItemAnimator());
        this.a.setHasFixedSize(true);
        this.a.addOnScrollListener(new aw(this));
        b();
    }

    private void a() {
        if (this.c.getChildCount() + this.c.findFirstVisibleItemPosition() >= this.c.getItemCount() - 3) {
            if (this.h == 1) {
                this.h++;
            }
            c();
            return;
        }
        this.g.setRefreshing(false);
    }

    private void b() {
        this.g.post(new ax(this));
    }

    private void c() {
        this.e = true;
        NetRequest.getInstance().get(UrlConstants.MESSAGE_FAMILY, new ay(this), "message_drawer");
    }

    public void onEmptyClick(View view) {
        this.j.hide();
        b();
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (isTaskRoot()) {
            AppUtils.getInstance().getUserInfoProvider().toMain(this);
        }
    }
}
