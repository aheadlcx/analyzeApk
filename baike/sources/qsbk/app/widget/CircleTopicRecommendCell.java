package qsbk.app.widget;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.CircleTopicBanner;
import qsbk.app.utils.UIHelper;

public class CircleTopicRecommendCell extends BaseCell {
    ImageView a;
    TextView b;
    ImageView c;
    TextView d;
    View e;

    public void onCreate() {
        setCellView((int) R.layout.cell_circle_topic_recommend);
        this.e = findViewById(R.id.vol_container);
        this.a = (ImageView) findViewById(R.id.weekly);
        this.b = (TextView) findViewById(R.id.title);
        this.c = (ImageView) findViewById(R.id.image);
        this.d = (TextView) findViewById(R.id.desc);
    }

    public void onUpdate() {
        int i;
        int i2 = 0;
        CircleTopicBanner circleTopicBanner = (CircleTopicBanner) getItem();
        this.b.setVisibility(TextUtils.isEmpty(circleTopicBanner.title) ? 8 : 0);
        this.b.setText(circleTopicBanner.title);
        TextView textView = this.d;
        if (TextUtils.isEmpty(circleTopicBanner.subTitle)) {
            i = 8;
        } else {
            i = 0;
        }
        textView.setVisibility(i);
        this.d.setText(circleTopicBanner.subTitle);
        View view = this.e;
        if (circleTopicBanner.type == 4) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        ImageView imageView = this.a;
        if (circleTopicBanner.type != 4) {
            i2 = 8;
        }
        imageView.setVisibility(i2);
        FrescoImageloader.displayImage(this.c, circleTopicBanner.bigPicUrl, UIHelper.getDefaultImageTileBackground());
        getCellView().setOnClickListener(new av(this, circleTopicBanner));
    }
}
