package qsbk.app.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import qsbk.app.R;
import qsbk.app.model.CircleTopicCategory;
import qsbk.app.utils.UIHelper;

public class CircleCategoryFlowCell extends BaseCell {
    FlowLayout a;
    View b;
    ImageView c;

    public void onCreate() {
        setCellView((int) R.layout.cell_circle_topic_flow);
        this.a = (FlowLayout) findViewById(R.id.flow);
        this.b = findViewById(R.id.more);
        this.b.setOnClickListener(new ao(this));
        this.c = (ImageView) findViewById(R.id.more_arrow);
    }

    public void onUpdate() {
        List<CircleTopicCategory> list = (List) getItem();
        this.a.removeAllViews();
        for (CircleTopicCategory a : list) {
            this.a.addView(a(a));
        }
    }

    private View a(CircleTopicCategory circleTopicCategory) {
        View textView = new TextView(getContext());
        textView.setText(circleTopicCategory.name);
        textView.setPadding(UIHelper.dip2px(getContext(), 8.0f), UIHelper.dip2px(getContext(), 4.0f), UIHelper.dip2px(getContext(), 8.0f), UIHelper.dip2px(getContext(), 4.0f));
        textView.setTextColor(UIHelper.isNightTheme() ? -4424933 : -17664);
        textView.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.btn_circle_category_dark : R.drawable.btn_circle_category);
        textView.setOnClickListener(new ap(this, circleTopicCategory));
        this.c.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_right_arrow_yellow_dark : R.drawable.url_content_forward_active);
        return textView;
    }
}
