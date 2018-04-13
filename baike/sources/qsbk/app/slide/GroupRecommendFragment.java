package qsbk.app.slide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.fragments.BaseFragment;
import qsbk.app.model.GroupRecommend;
import qsbk.app.model.GroupRecommend.GroupRecommendObserver;
import qsbk.app.widget.GroupRecommendQiushiCell;

public class GroupRecommendFragment extends BaseFragment implements GroupRecommendObserver {
    GroupRecommendQiushiCell a;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = new GroupRecommendQiushiCell();
        this.a.performCreate(0, viewGroup, GroupRecommend.getInstance());
        this.a.performUpdate(0, viewGroup, GroupRecommend.getInstance());
        return this.a.getCellView();
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        GroupRecommend.register(this);
    }

    public void onDestroyView() {
        super.onDestroyView();
        GroupRecommend.unregister(this);
    }

    public void onNewGroupRecommend(GroupRecommend groupRecommend) {
        this.a.onNewGroupRecommend(groupRecommend);
        this.a.onUpdate();
    }
}
