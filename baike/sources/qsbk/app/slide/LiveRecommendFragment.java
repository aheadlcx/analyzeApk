package qsbk.app.slide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.fragments.BaseFragment;
import qsbk.app.model.LivePackage;
import qsbk.app.widget.LiveRecommendCell;

public class LiveRecommendFragment extends BaseFragment {
    private LivePackage a;
    private int b;

    public static LiveRecommendFragment newInstance(LivePackage livePackage, int i) {
        LiveRecommendFragment liveRecommendFragment = new LiveRecommendFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(bo.LIVE_RECOMMEND.getTypeValue(), livePackage);
        bundle.putInt("position", i);
        liveRecommendFragment.setArguments(bundle);
        return liveRecommendFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = (LivePackage) getArguments().getSerializable(bo.LIVE_RECOMMEND.getTypeValue());
        this.b = getArguments().getInt("position");
        if (this.a == null) {
            return null;
        }
        LiveRecommendCell liveRecommendCell = new LiveRecommendCell("liverecommentfragment", true);
        liveRecommendCell.performCreate(this.b, viewGroup, this.a);
        liveRecommendCell.performUpdate(this.b, viewGroup, this.a);
        return liveRecommendCell.getCellView();
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }
}
