package qsbk.app.widget;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.PicUrl;

public class CircleTopicThreeImageCell extends BaseCell {
    ImageView a;
    ImageView b;
    ImageView c;
    TextView d;
    TextView e;

    public void onCreate() {
        setCellView((int) R.layout.layout_article_item_type_circle_topic_three_image);
        this.a = (ImageView) findViewById(R.id.imageview1);
        this.b = (ImageView) findViewById(R.id.imageview2);
        this.c = (ImageView) findViewById(R.id.imageview3);
        this.d = (TextView) findViewById(R.id.topic_title);
        this.e = (TextView) findViewById(R.id.topic_btn);
    }

    public void onUpdate() {
        CircleTopic circleTopic = (CircleTopic) getItem();
        ArrayList arrayList = circleTopic.recomendCircleArticles;
        String[] strArr = new String[3];
        if (arrayList.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                String str;
                CircleArticle circleArticle = (CircleArticle) arrayList.get(i);
                if (circleArticle.picUrls == null || circleArticle.picUrls.size() == 0) {
                    str = "android.resource://" + QsbkApp.mContext.getPackageName() + MqttTopic.TOPIC_LEVEL_SEPARATOR + R.drawable.circle_topic_default;
                } else {
                    str = QsbkApp.absoluteUrlOfCircleWebpImage(((PicUrl) circleArticle.picUrls.get(0)).url, circleArticle.createAt);
                }
                strArr[i] = circleArticle.id;
                if (i == 0) {
                    displayImage(this.a, str);
                } else if (i == 1) {
                    displayImage(this.b, str);
                } else {
                    displayImage(this.c, str);
                }
            }
            this.a.setOnClickListener(new ax(this, circleTopic, strArr));
            this.b.setOnClickListener(new ay(this, circleTopic, strArr));
            this.c.setOnClickListener(new az(this, circleTopic, strArr));
        }
        this.d.setText("[糗友圈] " + (TextUtils.isEmpty(circleTopic.recommendContent) ? circleTopic.content : circleTopic.recommendContent));
        this.e.setText("我有话说");
        this.e.setOnClickListener(new ba(this, circleTopic));
        getCellView().setOnClickListener(new bb(this, circleTopic));
    }
}
