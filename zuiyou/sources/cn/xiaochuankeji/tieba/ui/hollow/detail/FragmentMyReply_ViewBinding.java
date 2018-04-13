package cn.xiaochuankeji.tieba.ui.hollow.detail;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class FragmentMyReply_ViewBinding implements Unbinder {
    private FragmentMyReply b;

    @UiThread
    public FragmentMyReply_ViewBinding(FragmentMyReply fragmentMyReply, View view) {
        this.b = fragmentMyReply;
        fragmentMyReply.refreshLayout = (SmartRefreshLayout) b.b(view, R.id.my_hollow_refresh_layout, "field 'refreshLayout'", SmartRefreshLayout.class);
        fragmentMyReply.emptyView = (RelativeLayout) b.b(view, R.id.my_hollow_empty_view, "field 'emptyView'", RelativeLayout.class);
        fragmentMyReply.recyclerView = (RecyclerView) b.b(view, R.id.my_hollow_list_view, "field 'recyclerView'", RecyclerView.class);
        fragmentMyReply.emptyImage = (ImageView) b.b(view, R.id.empty_hollow_img, "field 'emptyImage'", ImageView.class);
        fragmentMyReply.emptyText = (TextView) b.b(view, R.id.empty_hollow_text, "field 'emptyText'", TextView.class);
    }

    @CallSuper
    public void a() {
        FragmentMyReply fragmentMyReply = this.b;
        if (fragmentMyReply == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        fragmentMyReply.refreshLayout = null;
        fragmentMyReply.emptyView = null;
        fragmentMyReply.recyclerView = null;
        fragmentMyReply.emptyImage = null;
        fragmentMyReply.emptyText = null;
    }
}
