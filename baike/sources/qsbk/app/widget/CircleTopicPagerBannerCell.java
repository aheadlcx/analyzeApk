package qsbk.app.widget;

import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import qsbk.app.R;
import qsbk.app.model.CircleTopicBanner;

public class CircleTopicPagerBannerCell extends LoopBannerCell<CircleTopicBanner> {
    public CircleTopicPagerBannerCell(List<CircleTopicBanner> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.cell_loop_banner;
    }

    public View createView(ViewGroup viewGroup, CircleTopicBanner circleTopicBanner, int i) {
        CircleTopicBannerCell circleTopicBannerCell = new CircleTopicBannerCell();
        circleTopicBannerCell.performCreate(i, viewGroup, circleTopicBanner);
        circleTopicBannerCell.performUpdate(i, viewGroup, circleTopicBanner);
        return circleTopicBannerCell.getCellView();
    }
}
