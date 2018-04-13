package qsbk.app.utils;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

class BrightnessSetting$b implements OnSeekBarChangeListener {
    final /* synthetic */ BrightnessSetting a;

    BrightnessSetting$b(BrightnessSetting brightnessSetting) {
        this.a = brightnessSetting;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z || !BrightnessSetting.a(this.a).isChecked()) {
            BrightnessSetting.a(this.a, seekBar.getProgress());
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        this.a.cacheScreenBrightness(Math.max(((float) (seekBar.getProgress() + 1)) / 255.0f, 0.0588235f));
    }
}
