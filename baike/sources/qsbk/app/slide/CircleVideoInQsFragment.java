package qsbk.app.slide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.QsbkApp;
import qsbk.app.fragments.BaseFragment;
import qsbk.app.fragments.IPageableFragment;
import qsbk.app.model.CircleArticle;
import qsbk.app.video.VideoPlayerView;
import qsbk.app.widget.CircleVideoCell;

public class CircleVideoInQsFragment extends BaseFragment implements IPageableFragment {
    private CircleArticle a;
    private int b;
    private VideoPlayerView c;

    public static CircleVideoInQsFragment newInstance(CircleArticle circleArticle, int i) {
        CircleVideoInQsFragment circleVideoInQsFragment = new CircleVideoInQsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(bo.CIRCLE_VIDEO.getTypeValue(), circleArticle);
        bundle.putInt("position", i);
        circleVideoInQsFragment.setArguments(bundle);
        return circleVideoInQsFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = (CircleArticle) getArguments().getSerializable(bo.CIRCLE_VIDEO.getTypeValue());
        this.b = getArguments().getInt("position");
        if (this.a == null) {
            return null;
        }
        CircleVideoCell circleVideoCell = new CircleVideoCell();
        circleVideoCell.performCreate(this.b, viewGroup, this.a);
        circleVideoCell.performUpdate(this.b, viewGroup, this.a);
        this.c = circleVideoCell.player;
        return circleVideoCell.getCellView();
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void doResume() {
        if (this.c != null && this.a != null && this.a.isVideoArticle() && !QsbkApp.getInstance().isAutoPlayConfiged()) {
        }
    }

    public void doStop() {
        if (this.c != null) {
            this.c.reset();
        }
    }

    public void doPause() {
    }
}
