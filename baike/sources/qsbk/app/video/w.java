package qsbk.app.video;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class w implements OnCheckedChangeListener {
    final /* synthetic */ VideoEditActivity a;

    w(VideoEditActivity videoEditActivity) {
        this.a = videoEditActivity;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.a.s = z;
        if (this.a.u.endsWith("90") || this.a.u.endsWith("270")) {
            this.a.j.setView(this.a.d.getFilePath(this.a), this.a.d.height, this.a.d.width, this.a.m, this.a.s);
        } else {
            this.a.j.setView(this.a.d.getFilePath(this.a), this.a.d.width, this.a.d.height, this.a.m, this.a.s);
        }
    }
}
