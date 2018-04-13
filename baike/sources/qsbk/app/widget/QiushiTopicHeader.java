package qsbk.app.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.UIHelper;

public class QiushiTopicHeader extends RelativeLayout {
    private TextView a;
    private TextView b;
    private ImageView c;
    private SimpleDraweeView d;
    private ImageView e;
    private TextView f;

    public QiushiTopicHeader(Context context) {
        super(context);
        a(context);
    }

    public QiushiTopicHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public QiushiTopicHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public ImageView getTopicSub() {
        return this.c;
    }

    public void setTopicSub(boolean z) {
        if (z) {
            if (UIHelper.isNightTheme()) {
                this.c.setImageDrawable(getResources().getDrawable(R.drawable.qiushi_topic_subscribed_night));
            } else {
                this.c.setImageDrawable(getResources().getDrawable(R.drawable.qiushi_topic_subscribed_day));
            }
        } else if (UIHelper.isNightTheme()) {
            this.c.setImageDrawable(getResources().getDrawable(R.drawable.qiushi_topic_subscribe_night));
        } else {
            this.c.setImageDrawable(getResources().getDrawable(R.drawable.qiushi_topic_subscribe_day));
        }
    }

    private void a(Context context) {
        if (this.a == null) {
            LayoutInflater.from(context).inflate(R.layout.activity_qiushi_topic_header, this, true);
            this.a = (TextView) findViewById(R.id.qiushi_topic_title);
            this.b = (TextView) findViewById(R.id.qiushi_topic_des);
            this.c = (ImageView) findViewById(R.id.qiushi_topic_sub);
            this.d = (SimpleDraweeView) findViewById(R.id.qiushi_topic_icon);
            this.e = (ImageView) findViewById(R.id.qiushi_topic_event);
            this.f = (TextView) findViewById(R.id.qiushit_topic_introduction);
        }
    }

    public void setQiushiTopic(QiushiTopic qiushiTopic) {
        int i = 0;
        setTopicName(qiushiTopic.content);
        setTopicDes(qiushiTopic.articleCount, qiushiTopic.subcribeCount);
        setTopicIcon(qiushiTopic.background);
        setTopicSub(qiushiTopic.isSubscribed);
        this.e.setVisibility(qiushiTopic.hasEvent() ? 0 : 4);
        if (qiushiTopic.hasEvent()) {
            FrescoImageloader.displayImage(this.e, qiushiTopic.eventWindow.iconUrl, 0, R.drawable.ic_get_laisee);
        }
        this.e.setOnClickListener(new ds(this, qiushiTopic));
        TextView textView = this.f;
        if (TextUtils.isEmpty(qiushiTopic.introduction)) {
            i = 8;
        }
        textView.setVisibility(i);
        this.f.setText(qiushiTopic.introduction);
    }

    public void setTopicName(String str) {
        this.a.setText(str);
    }

    public void setTopicDes(int i, int i2) {
        this.b.setText("帖子 " + i + "  " + "订阅 " + i2);
    }

    public void setTopicIcon(String str) {
        FrescoImageloader.displayImage(this.d, str, UIHelper.getDefaultImageTileBackground());
    }
}
