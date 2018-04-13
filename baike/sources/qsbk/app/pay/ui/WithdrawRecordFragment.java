package qsbk.app.pay.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.ui.base.BaseFragment;
import qsbk.app.core.widget.EmptyPlaceholderView;
import qsbk.app.core.widget.refresh.SwipeRefreshLayoutBoth;
import qsbk.app.pay.R;
import qsbk.app.pay.adapter.WithdrawRecordAdapter;
import qsbk.app.pay.model.WithRecordData;

public class WithdrawRecordFragment extends BaseFragment {
    private RecyclerView a;
    private SwipeRefreshLayoutBoth b;
    private WithdrawRecordAdapter c;
    private LinearLayoutManager d;
    private EmptyPlaceholderView e;
    private ArrayList<WithRecordData> f = new ArrayList();
    private String g = "0";
    private int h = 0;
    private boolean i = false;
    private boolean j = true;
    private int k = 0;

    public static Fragment newInstance(int i) {
        Fragment withdrawRecordFragment = new WithdrawRecordFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        withdrawRecordFragment.setArguments(bundle);
        return withdrawRecordFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.k = arguments.getInt("type", 0);
        }
    }

    protected int getLayoutId() {
        return R.layout.pay_withdraw_record_fragment;
    }

    protected void initView() {
        this.a = (RecyclerView) findViewById(R.id.recyclerview);
        this.e = (EmptyPlaceholderView) findViewById(R.id.empty);
        this.b = (SwipeRefreshLayoutBoth) findViewById(R.id.refresher);
    }

    protected void initData() {
        this.d = new LinearLayoutManager(getActivity());
        this.d.setOrientation(1);
        this.a.setLayoutManager(this.d);
        this.c = new WithdrawRecordAdapter(this.f, this.g, this.k, getActivity());
        this.a.setAdapter(this.c);
        this.a.setItemAnimator(new DefaultItemAnimator());
        this.a.setHasFixedSize(true);
        this.a.addOnScrollListener(new ai(this));
        this.b.setOnRefreshListener(new aj(this));
        a();
    }

    private void a() {
        this.b.post(new ak(this));
    }

    private void b() {
        this.i = true;
        NetRequest.getInstance().get(this.k == 0 ? UrlConstants.WITHDRAW_RECORD : UrlConstants.WITHDRAW_DIAMOND_RECORD, new al(this));
    }

    private void c() {
        if (this.d.getChildCount() + this.d.findFirstVisibleItemPosition() >= this.d.getItemCount() - 3) {
            if (this.h == 1) {
                this.h++;
            }
            b();
            return;
        }
        this.b.setRefreshing(false);
    }
}
