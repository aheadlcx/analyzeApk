package cn.xiaochuankeji.tieba.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import cn.htjyb.ui.widget.headfooterlistview.QueryListView;
import cn.xiaochuankeji.tieba.R;

public abstract class i extends f {
    protected QueryListView a;
    private FrameLayout b;

    protected abstract void b();

    protected abstract QueryListView c();

    protected abstract void d();

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_ugcvideo_recommend, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.b = (FrameLayout) view.findViewById(R.id.rootView);
        this.a = c();
        this.b.addView(this.a);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        d();
        if (bundle == null) {
            b();
        }
    }
}
