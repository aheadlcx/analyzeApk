package cn.xiaochuankeji.tieba.ui.homepage.feed.holder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.json.MemberInfoBean;

public abstract class b<T> extends ViewHolder {
    public abstract void a(T t, int i, MemberInfoBean memberInfoBean);

    public b(View view) {
        super(view);
        ButterKnife.a(this, view);
    }
}
