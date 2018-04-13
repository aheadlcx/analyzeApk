package qsbk.app.utils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class bp implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ OnClickListener b;

    bp(int i, OnClickListener onClickListener) {
        this.a = i;
        this.b = onClickListener;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        VideoLoadConfig.setState(i);
        DebugUtil.debug("luolong", "Video Play Mode, getState, state=" + this.a + ",which=" + i);
        if (this.a != i) {
            VideoLoadConfig.setVideoPlayModeChanged();
        }
        dialogInterface.dismiss();
        if (this.b != null) {
            this.b.onClick(dialogInterface, i);
        }
    }
}
