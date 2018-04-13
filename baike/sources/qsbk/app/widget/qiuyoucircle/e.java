package qsbk.app.widget.qiuyoucircle;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.VideoImmersionCircleActivity;
import qsbk.app.widget.qiuyoucircle.BaseAdCell.GDTAdCell;

class e implements OnClickListener {
    final /* synthetic */ GDTAdCell a;

    e(GDTAdCell gDTAdCell) {
        this.a = gDTAdCell;
    }

    public void onClick(View view) {
        VideoImmersionCircleActivity.launch(this.a.getContext(), this.a.k, 0);
    }
}
