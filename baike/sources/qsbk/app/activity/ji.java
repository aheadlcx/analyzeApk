package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.VideoLoadConfig;

class ji implements OnClickListener {
    final /* synthetic */ CommonSettingActivity a;

    ji(CommonSettingActivity commonSettingActivity) {
        this.a = commonSettingActivity;
    }

    public void onClick(View view) {
        VideoLoadConfig.showSelectDialog(this.a, new jj(this));
    }
}
