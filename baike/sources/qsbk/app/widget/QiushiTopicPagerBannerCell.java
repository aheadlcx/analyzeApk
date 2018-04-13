package qsbk.app.widget;

import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import qsbk.app.R;
import qsbk.app.model.QiushiTopicBanner;

public class QiushiTopicPagerBannerCell extends LoopBannerCell<QiushiTopicBanner> {
    public QiushiTopicPagerBannerCell(List<QiushiTopicBanner> list) {
        super(list);
    }

    public int getLayoutId() {
        return R.layout.cell_loop_banner;
    }

    public View createView(ViewGroup viewGroup, QiushiTopicBanner qiushiTopicBanner, int i) {
        QiushiTopicBannerCell qiushiTopicBannerCell = new QiushiTopicBannerCell();
        qiushiTopicBannerCell.performCreate(i, viewGroup, qiushiTopicBanner);
        qiushiTopicBannerCell.performUpdate(i, viewGroup, qiushiTopicBanner);
        return qiushiTopicBannerCell.getCellView();
    }
}
