package cn.tatagou.sdk.view.pullview;

import android.os.Handler;
import android.os.Message;
import android.widget.AbsListView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.util.l;
import cn.tatagou.sdk.util.p;
import java.lang.ref.WeakReference;

public class c {

    static class a extends Handler {
        PullToRefreshLayout a;
        WeakReference<c> b;

        a(PullToRefreshLayout pullToRefreshLayout, c cVar) {
            this.a = pullToRefreshLayout;
            this.b = new WeakReference(cVar);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            c cVar = (c) this.b.get();
            if (cVar != null) {
                cVar.onRefreshNet(this.a);
            }
        }
    }

    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        if (pullToRefreshLayout != null) {
            pullToRefreshLayout.c = true;
            if (TtgSDK.getContext() == null || !p.isNetworkOpen(TtgSDK.getContext())) {
                if (TtgSDK.getContext() != null) {
                    l.showToastCenter(TtgSDK.getContext(), TtgSDK.getContext().getResources().getString(R.string.ttg_net_bad));
                }
                pullToRefreshLayout.refreshFinish(0);
                return;
            }
            new a(pullToRefreshLayout, this).sendEmptyMessageDelayed(0, 500);
        }
    }

    public void onAutoRefresh(AutoPullToRefreshLayout autoPullToRefreshLayout) {
        if (TtgSDK.getContext() == null || autoPullToRefreshLayout == null) {
            if (autoPullToRefreshLayout != null) {
                autoPullToRefreshLayout.refreshFinish(0);
            }
        } else if (p.isNetworkOpen(TtgSDK.getContext())) {
            onRefreshNet(autoPullToRefreshLayout);
        } else {
            l.showToastCenter(TtgSDK.getContext(), TtgSDK.getContext().getResources().getString(R.string.ttg_net_bad));
            autoPullToRefreshLayout.refreshFinish(0);
        }
    }

    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
    }

    public void onRefreshNet(PullToRefreshLayout pullToRefreshLayout) {
    }

    public void onRefreshNet(AutoPullToRefreshLayout autoPullToRefreshLayout) {
    }

    public void onScrollList(AbsListView absListView, int i, int i2, boolean z) {
    }

    public void onStopScroll(boolean z, int i, int i2) {
    }

    public void onInitView(PullToRefreshLayout pullToRefreshLayout) {
    }
}
