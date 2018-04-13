package qsbk.app.widget;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.QiushiTopicBanner;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;

public class QiushiTopicBannerCell extends BaseCell {
    ImageView a;
    TextView b;
    ImageView c;
    TextView d;
    View e;

    public void onCreate() {
        setCellView((int) R.layout.cell_circle_topic_banner);
        this.e = findViewById(R.id.vol_container);
        this.a = (ImageView) findViewById(R.id.weekly);
        this.b = (TextView) findViewById(R.id.title);
        this.c = (ImageView) findViewById(R.id.image);
        this.d = (TextView) findViewById(R.id.desc);
    }

    public void onUpdate() {
        int i;
        int i2 = 0;
        QiushiTopicBanner qiushiTopicBanner = (QiushiTopicBanner) getItem();
        this.b.setVisibility(TextUtils.isEmpty(qiushiTopicBanner.title) ? 8 : 0);
        this.b.setText(qiushiTopicBanner.title);
        View view = this.e;
        if (qiushiTopicBanner.type == 4) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        ImageView imageView = this.a;
        if (qiushiTopicBanner.type != 4) {
            i2 = 8;
        }
        imageView.setVisibility(i2);
        FrescoImageloader.displayImage(this.c, qiushiTopicBanner.imageUrl, TileBackground.getBackgroud(getContext(), BgImageType.ARTICLE), null);
        getCellView().setOnClickListener(new dq(this, qiushiTopicBanner));
    }
}
