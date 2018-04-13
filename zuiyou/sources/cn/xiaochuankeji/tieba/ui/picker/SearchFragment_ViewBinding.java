package cn.xiaochuankeji.tieba.ui.picker;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class SearchFragment_ViewBinding implements Unbinder {
    private SearchFragment b;

    @UiThread
    public SearchFragment_ViewBinding(SearchFragment searchFragment, View view) {
        this.b = searchFragment;
        searchFragment.mRecyclerView = (RecyclerView) b.b(view, R.id.recycler, "field 'mRecyclerView'", RecyclerView.class);
        searchFragment.mTvNoResult = (TextView) b.b(view, R.id.tv_no_result, "field 'mTvNoResult'", TextView.class);
    }

    @CallSuper
    public void a() {
        SearchFragment searchFragment = this.b;
        if (searchFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        searchFragment.mRecyclerView = null;
        searchFragment.mTvNoResult = null;
    }
}
