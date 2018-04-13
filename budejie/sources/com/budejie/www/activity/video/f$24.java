package com.budejie.www.activity.video;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.util.an;
import com.umeng.analytics.MobclickAgent;

class f$24 implements OnClickListener {
    final /* synthetic */ f a;

    f$24(f fVar) {
        this.a = fVar;
    }

    public void onClick(View view) {
        MobclickAgent.onEvent(f.b(this.a), "E06-A10", "全屏视频id:" + f.o(this.a).getWid());
        MobclickAgent.onEvent(f.b(this.a), "E06_A11", "视频控制栏中下载");
        i.a(f.b(this.a).getString(R.string.track_event_fullscree_video_download), j.a(f.o(this.a)), j.b(f.b(this.a), f.o(this.a)));
        an.a(f.b(this.a), f.o(this.a));
    }
}
