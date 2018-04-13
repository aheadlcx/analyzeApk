package qsbk.app.widget;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.PicUrl;
import qsbk.app.utils.UIHelper;

public class CircleTopicMoreCell extends BaseCell {
    ImageView a;
    QBImageView b;
    TextView c;
    TextView d;
    View e;
    View f;
    ImageView g;

    public void onCreate() {
        setCellView((int) R.layout.layout_article_item_type_circle_topic_more);
        this.a = (ImageView) findViewById(R.id.image);
        this.b = (QBImageView) findViewById(R.id.cover);
        this.c = (TextView) findViewById(R.id.title);
        this.e = findViewById(R.id.content_container);
        this.d = (TextView) findViewById(R.id.content);
        this.f = findViewById(R.id.more);
        this.g = (ImageView) findViewById(R.id.arrow_double);
    }

    public void onUpdate() {
        String str;
        CircleTopic circleTopic = (CircleTopic) getItem();
        this.a.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_discuss_dark : R.drawable.ic_discuss);
        this.c.setText(circleTopic.content);
        FrescoImageloader.displayImage(this.b, circleTopic.getPicUrl(0));
        ArrayList arrayList = circleTopic.recomendCircleArticles;
        if (arrayList.size() > 0) {
            String imageUrl;
            CircleArticle circleArticle = (CircleArticle) arrayList.get(0);
            if (circleArticle.picUrls != null && circleArticle.picUrls.size() > 0) {
                imageUrl = ((PicUrl) circleArticle.picUrls.get(0)).getImageUrl();
            } else if (circleTopic != null) {
                imageUrl = circleTopic.icon.getImageUrl();
            } else {
                imageUrl = "android.resource://" + QsbkApp.mContext.getPackageName() + MqttTopic.TOPIC_LEVEL_SEPARATOR + R.drawable.circle_topic_default;
            }
            displayImage(this.b, imageUrl);
            imageUrl = circleArticle.id;
            CharSequence charSequence = circleArticle.content;
            if (!(TextUtils.isEmpty(charSequence) || TextUtils.isEmpty(circleTopic.content))) {
                charSequence = charSequence.replace(circleTopic.content, "").trim();
            }
            this.d.setText(charSequence);
            str = imageUrl;
        } else {
            str = null;
        }
        this.e.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.shape_gray_border_dark : R.drawable.shape_gray_border);
        this.g.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_arrow_double_dark : R.drawable.ic_arrow_double);
        this.f.setOnClickListener(new at(this));
        getCellView().setOnClickListener(new au(this, circleTopic, str));
    }
}
