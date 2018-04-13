package qsbk.app.live.ui;

import android.os.CountDownTimer;
import com.baidu.mobstat.Config;
import qsbk.app.live.R;

class bv extends CountDownTimer {
    final /* synthetic */ LiveBaseActivity a;

    bv(LiveBaseActivity liveBaseActivity, long j, long j2) {
        this.a = liveBaseActivity;
        super(j, j2);
    }

    public void onTick(long j) {
        if (j / 1000 < 60) {
            this.a.bl.setText((j / 1000) + "");
            return;
        }
        long j2 = (j / 1000) / 60;
        long j3 = (j / 1000) % 60;
        this.a.bl.setText(j2 + Config.TRACE_TODAY_VISIT_SPLIT + String.format("%02d", new Object[]{Long.valueOf(j3)}));
    }

    public void onFinish() {
        this.a.bl.setText("可领取");
        this.a.bl.setBackgroundResource(R.drawable.bg_freegift);
        this.a.bj.setOnClickListener(new bw(this));
    }
}
