package qsbk.app.video;

import android.widget.PopupWindow.OnDismissListener;
import qsbk.app.utils.SharePreferenceUtils;

class aa implements OnDismissListener {
    final /* synthetic */ VideoEditActivity a;

    aa(VideoEditActivity videoEditActivity) {
        this.a = videoEditActivity;
    }

    public void onDismiss() {
        SharePreferenceUtils.setSharePreferencesValue("showed_video_edit_tip", true);
    }
}
