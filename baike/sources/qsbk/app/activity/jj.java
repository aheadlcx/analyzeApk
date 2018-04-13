package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.baidu.mobstat.StatService;
import qsbk.app.utils.VideoLoadConfig;

class jj implements OnClickListener {
    final /* synthetic */ ji a;

    jj(ji jiVar) {
        this.a = jiVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        StatService.onEvent(this.a.a, "video_play_cfg", VideoLoadConfig.getName(i));
        this.a.a.d.setSubTitle(VideoLoadConfig.getName(i));
    }
}
