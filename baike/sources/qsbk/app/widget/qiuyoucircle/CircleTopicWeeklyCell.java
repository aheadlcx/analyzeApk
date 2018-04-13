package qsbk.app.widget.qiuyoucircle;

import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.BaseCell;

public class CircleTopicWeeklyCell extends BaseCell {
    ImageView a;
    TextView b;
    TextView c;
    TextView d;

    public void onCreate() {
        setCellView(a());
        this.a = (ImageView) findViewById(R.id.icon);
        this.b = (TextView) findViewById(R.id.title);
        this.c = (TextView) findViewById(R.id.article_count);
        this.d = (TextView) findViewById(R.id.ranking_num);
    }

    int a() {
        return R.layout.cell_circle_topic_weekly;
    }

    public void onUpdate() {
        CircleTopic circleTopic = (CircleTopic) getItem();
        this.b.setText(circleTopic.content);
        if (circleTopic.isAnonymous) {
            this.b.setCompoundDrawablesWithIntrinsicBounds(0, 0, UIHelper.getTopicAnonymous(), 0);
        } else {
            this.b.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        displayImage(this.a, QsbkApp.absoluteUrlOfCircleWebpImage(circleTopic.icon == null ? "" : circleTopic.icon.url, circleTopic.createAt));
        this.c.setText(String.format("动态 %d  今日 %d", new Object[]{Integer.valueOf(circleTopic.articleCount), Integer.valueOf(circleTopic.todayCount)}));
        this.d.setVisibility(8);
        getCellView().setOnClickListener(new ad(this, circleTopic));
    }
}
