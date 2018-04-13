package qsbk.app.widget.qiuyoucircle;

import android.widget.ImageView;
import qsbk.app.R;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.BaseCell;

public class FollowWelcomeCell extends BaseCell {
    ImageView a;

    public void onCreate() {
        setCellView(R.layout.item_qb_follow_welcome);
        this.a = (ImageView) findViewById(R.id.illustration);
    }

    public void onUpdate() {
        this.a.setImageResource(UIHelper.isNightTheme() ? R.drawable.ic_follow_welcome_night : R.drawable.ic_follow_welcome);
    }
}
