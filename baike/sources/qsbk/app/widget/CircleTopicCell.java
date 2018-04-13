package qsbk.app.widget;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.UIHelper;

public class CircleTopicCell extends BaseCell {
    ImageView a;
    TextView b;
    TextView c;
    TextView d;
    TextView e;
    TextView f;
    TextView g;

    public void onCreate() {
        if (a() != 0) {
            setCellView(a());
        } else {
            setCellView((int) R.layout.cell_circle_topic_topic);
        }
        this.a = (ImageView) findViewById(R.id.icon);
        this.b = (TextView) findViewById(R.id.title);
        this.c = (TextView) findViewById(R.id.intro);
        this.d = (TextView) findViewById(R.id.article_count);
        this.e = (TextView) findViewById(R.id.create);
        this.f = (TextView) findViewById(R.id.ranking_num);
        this.g = (TextView) findViewById(R.id.topic_owner);
    }

    int a() {
        return R.layout.cell_circle_topic_topic;
    }

    public void onUpdate() {
        CircleTopic circleTopic = (CircleTopic) getItem();
        this.b.setText(circleTopic.content);
        if (circleTopic.isAnonymous) {
            this.b.setCompoundDrawablesWithIntrinsicBounds(0, 0, UIHelper.getTopicAnonymous(), 0);
        } else {
            this.b.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        if ((circleTopic.user == null || QsbkApp.currentUser == null || !TextUtils.equals(QsbkApp.currentUser.userId, circleTopic.user.userId)) && (circleTopic.master_id <= 0 || QsbkApp.currentUser == null || !TextUtils.equals(String.valueOf(circleTopic.master_id), QsbkApp.currentUser.userId))) {
            this.g.setVisibility(8);
        } else {
            this.g.setVisibility(0);
        }
        if (TextUtils.isEmpty(circleTopic.intro)) {
            this.c.setVisibility(8);
        } else {
            this.c.setText(circleTopic.intro);
            this.c.setVisibility(0);
        }
        displayImage(this.a, QsbkApp.absoluteUrlOfCircleWebpImage(circleTopic.icon == null ? "" : circleTopic.icon.url, circleTopic.createAt));
        this.d.setText(String.format("动态 %d  今日 %d", new Object[]{Integer.valueOf(circleTopic.articleCount), Integer.valueOf(circleTopic.todayCount)}));
        this.f.setVisibility(8);
        this.e.setVisibility(8);
    }
}
