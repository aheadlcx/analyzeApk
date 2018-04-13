package qsbk.app.slide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import qsbk.app.fragments.BaseFragment;
import qsbk.app.model.CircleTopicBanner;
import qsbk.app.widget.CircleTopicTextBgCell;
import qsbk.app.widget.qiuyoucircle.UnknownCell.EmptyView;

public class CircleTopicBannerFragment extends BaseFragment {
    CircleTopicBanner a;

    public static CircleTopicBannerFragment newInstance(CircleTopicBanner circleTopicBanner) {
        CircleTopicBannerFragment circleTopicBannerFragment = new CircleTopicBannerFragment();
        new Bundle().putSerializable("banner", circleTopicBanner);
        return circleTopicBannerFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = (CircleTopicBanner) getArguments().getSerializable("banner");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.a == null) {
            return new EmptyView(getContext());
        }
        CircleTopicTextBgCell circleTopicTextBgCell = new CircleTopicTextBgCell();
        circleTopicTextBgCell.performCreate(0, viewGroup, this.a);
        circleTopicTextBgCell.performUpdate(0, viewGroup, this.a);
        return circleTopicTextBgCell.getCellView();
    }
}
