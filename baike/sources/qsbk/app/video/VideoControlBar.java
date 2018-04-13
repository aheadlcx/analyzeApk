package qsbk.app.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import qsbk.app.R;

public class VideoControlBar extends LinearLayout {
    public VideoControlBar(Context context) {
        super(context);
        a();
    }

    public VideoControlBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public VideoControlBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        inflate(getContext(), R.layout.activity_video_controlbar, this);
    }
}
