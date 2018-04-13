package qsbk.app.widget.qiuyoucircle;

import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.BaseCell;

public class CommentCell extends BaseCell {
    TextView a;

    public void onCreate() {
        setCellView(R.layout.cell_topic_comment);
        this.a = (TextView) findViewById(R.id.comment);
    }

    public void onUpdate() {
        this.a.setBackgroundResource(UIHelper.isNightTheme() ? R.drawable.bg_weekly_comment_dark : R.drawable.bg_weekly_comment);
        this.a.setText((String) getItem());
        this.a.setTextColor(UIHelper.isNightTheme() ? -4276546 : -7368811);
    }
}
