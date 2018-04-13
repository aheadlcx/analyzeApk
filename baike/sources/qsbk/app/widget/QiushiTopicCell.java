package qsbk.app.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.facebook.drawee.view.SimpleDraweeView;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.UIHelper;

public class QiushiTopicCell extends BaseCell {
    SimpleDraweeView a;
    TextView b;
    ImageView c;
    TextView d;
    TextView e;
    ToggleButton f;
    View g;
    boolean h;
    OnSubcribeListener i;

    public interface OnSubcribeListener {
        void subcribe(QiushiTopic qiushiTopic, QiushiTopicCell qiushiTopicCell);

        void unSubcribe(QiushiTopic qiushiTopic, QiushiTopicCell qiushiTopicCell);
    }

    public QiushiTopicCell(boolean z) {
        this.h = z;
    }

    public QiushiTopicCell(boolean z, OnSubcribeListener onSubcribeListener) {
        this.h = z;
        this.i = onSubcribeListener;
    }

    public void onCreate() {
        setCellView((int) R.layout.cell_qiushi_topic);
        this.a = (SimpleDraweeView) findViewById(R.id.avatar);
        this.c = (ImageView) findViewById(R.id.event);
        this.b = (TextView) findViewById(R.id.title);
        this.d = (TextView) findViewById(R.id.article_count);
        this.e = (TextView) findViewById(R.id.view_count);
        this.f = (ToggleButton) findViewById(R.id.button);
        this.f.setVisibility(this.h ? 0 : 8);
        this.g = findViewById(R.id.topic_divider);
    }

    public void onUpdate() {
        QiushiTopic qiushiTopic = (QiushiTopic) getItem();
        if (qiushiTopic != null) {
            this.f.setClickable(true);
            this.f.setVisibility(this.h ? 0 : 8);
            this.f.setOnClickListener(new dr(this, qiushiTopic));
            this.f.setChecked(qiushiTopic.isSubscribed);
            int dip2px = UIHelper.dip2px(getContext(), 6.0f);
            FrescoImageloader.displayImage(this.a, qiushiTopic.icon, UIHelper.getDefaultAvatar(), UIHelper.getDefaultAvatar(), false, dip2px);
            if (qiushiTopic.hasEvent()) {
                this.c.setVisibility(0);
                FrescoImageloader.displayImage(this.c, qiushiTopic.eventWindow.iconUrl, UIHelper.getDefaultAvatar(), UIHelper.getDefaultAvatar(), false, dip2px);
            } else {
                this.c.setVisibility(8);
            }
            this.b.setText(qiushiTopic.content);
            this.d.setText("帖子 " + qiushiTopic.articleCount);
            this.e.setText("浏览 " + qiushiTopic.readCount);
        }
    }
}
