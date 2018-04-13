package qsbk.app.widget;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.QiushiTopic;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.QiushiTopicCell.OnSubcribeListener;
import qsbk.app.widget.qiushitopic.QiushiTopicRecommendController;

public class QiushiTopicRecommendCell extends BaseCell implements OnClickListener, OnSubcribeListener {
    public static final String TAG = QiushiTopicRecommendCell.class.getSimpleName();
    View a;
    TextView b;
    LinearLayout c;
    List<QiushiTopicCell> d = new ArrayList();

    public void onCreate() {
        setCellView((int) R.layout.cell_qiushi_topic_recommend);
        this.a = findViewById(R.id.refresh);
        this.a.setOnClickListener(this);
        this.c = (LinearLayout) findViewById(R.id.topic_container);
        this.b = (TextView) findViewById(R.id.topic_all);
        this.b.setText("全部爆社");
        this.b.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.live_arrow_night : R.drawable.live_arrow), null, null, null);
        this.b.setOnClickListener(new dt(this));
    }

    public void onUpdate() {
        List list = (List) getItem();
        int i = 0;
        while (i < list.size()) {
            QiushiTopicCell qiushiTopicCell;
            QiushiTopic qiushiTopic = (QiushiTopic) list.get(i);
            if (this.d.size() > i) {
                qiushiTopicCell = (QiushiTopicCell) this.d.get(i);
                qiushiTopicCell.performUpdate(i, this.c, qiushiTopic);
            } else {
                qiushiTopicCell = new QiushiTopicCell(true, this);
                qiushiTopicCell.performCreate(i, this.c, qiushiTopic);
                qiushiTopicCell.performUpdate(i, this.c, qiushiTopic);
                this.c.addView(qiushiTopicCell.getCellView());
                this.d.add(qiushiTopicCell);
            }
            qiushiTopicCell.getCellView().setOnClickListener(new du(this, qiushiTopic));
            qiushiTopicCell.g.setVisibility(i == list.size() + -1 ? 8 : 0);
            LayoutParams layoutParams = qiushiTopicCell.g.getLayoutParams();
            if (layoutParams != null && (layoutParams instanceof MarginLayoutParams)) {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
                marginLayoutParams.setMargins(UIHelper.dip2px(getContext(), 64.0f), 0, UIHelper.dip2px(getContext(), 15.0f), 0);
                qiushiTopicCell.g.setLayoutParams(marginLayoutParams);
            }
            i++;
        }
    }

    public void subcribe(QiushiTopic qiushiTopic, QiushiTopicCell qiushiTopicCell) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.QIUSHI_TOPIC_SUBCRIBE, new dv(this, qiushiTopic, qiushiTopicCell));
        Map hashMap = new HashMap();
        hashMap.put("topic_id", Integer.valueOf(qiushiTopic.id));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public void unSubcribe(QiushiTopic qiushiTopic, QiushiTopicCell qiushiTopicCell) {
        new Builder(getContext()).setCancelable(true).setMessage("是否取消订阅").setPositiveButton("不再订阅", new dx(this, qiushiTopic)).setNegativeButton("继续订阅", new dw(this, qiushiTopicCell)).create().show();
    }

    public void onClick(View view) {
        QiushiTopicRecommendController.load(new dz(this), 1, Constants.QIUSHI_TOPIC_RECOMMEND_REPLACE);
    }
}
