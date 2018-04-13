package com.budejie.www.activity.video;

import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.budejie.www.util.ac;

class f$17 implements OnSeekBarChangeListener {
    final /* synthetic */ f a;

    f$17(f fVar) {
        this.a = fVar;
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        f.d(this.a, true);
        f.u(this.a).removeMessages(2);
        f.u(this.a).removeCallbacks(f.I(this.a));
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            f.d(this.a, true);
        }
        if (f.B(this.a) != null) {
            f.B(this.a).setText(ac.a((long) seekBar.getProgress()));
        }
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        if (f.s(this.a)) {
            f.d(this.a).a(seekBar.getProgress());
            f.d(this.a, false);
        }
        this.a.n();
        f.u(this.a).sendEmptyMessage(2);
        Log.d("MicroMediaController", "onStopTrackingTouch hideRunnable run()");
        f.u(this.a).postDelayed(f.I(this.a), 5000);
    }
}
