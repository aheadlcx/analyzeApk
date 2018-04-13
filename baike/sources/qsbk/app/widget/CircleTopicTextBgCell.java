package qsbk.app.widget;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.UIHelper;

public class CircleTopicTextBgCell extends BaseCell {
    ImageView a;
    TextView b;
    TextView c;

    public void onCreate() {
        setCellView((int) R.layout.layout_article_item_type_circle_topic_singlte_title);
        this.a = (ImageView) findViewById(R.id.backgroud);
        this.b = (TextView) findViewById(R.id.topic_message);
        this.c = (TextView) findViewById(R.id.topic_btn);
    }

    public void onUpdate() {
        String str;
        CircleTopic circleTopic = (CircleTopic) getItem();
        Object picUrl = circleTopic.getPicUrl(0);
        if (TextUtils.isEmpty(picUrl)) {
            str = "android.resource://" + QsbkApp.mContext.getPackageName() + MqttTopic.TOPIC_LEVEL_SEPARATOR + R.drawable.circle_topic_default;
        } else {
            str = QsbkApp.absoluteUrlOfCircleWebpImage(picUrl, circleTopic.createAt);
        }
        displayImage(this.a, str);
        CharSequence charSequence = TextUtils.isEmpty(circleTopic.recommendContent) ? circleTopic.content : circleTopic.recommendContent;
        if (!TextUtils.isEmpty(charSequence)) {
            charSequence = charSequence.replace(MqttTopic.MULTI_LEVEL_WILDCARD, "");
        }
        this.b.setText(charSequence);
        this.c.setText("糗友圈已有 " + circleTopic.articleCount + "条内容");
        getCellView().setOnClickListener(new aw(this, circleTopic));
        if (UIHelper.isNightTheme()) {
            View findViewById = findViewById(R.id.divider_bellow);
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
        }
    }
}
