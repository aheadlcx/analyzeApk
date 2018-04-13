package cn.xiaochuankeji.tieba.ui.my.download;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class MyDownloadActivity_ViewBinding implements Unbinder {
    private MyDownloadActivity b;

    @UiThread
    public MyDownloadActivity_ViewBinding(MyDownloadActivity myDownloadActivity, View view) {
        this.b = myDownloadActivity;
        myDownloadActivity.mRecyclerView = (RecyclerView) b.a(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        myDownloadActivity.back = view.findViewById(R.id.back);
        myDownloadActivity.tv_setting = view.findViewById(R.id.tv_setting);
    }

    @CallSuper
    public void a() {
        MyDownloadActivity myDownloadActivity = this.b;
        if (myDownloadActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        myDownloadActivity.mRecyclerView = null;
        myDownloadActivity.back = null;
        myDownloadActivity.tv_setting = null;
    }
}
