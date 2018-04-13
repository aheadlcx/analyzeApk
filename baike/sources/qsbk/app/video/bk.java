package qsbk.app.video;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import qsbk.app.R;
import qsbk.app.utils.DebugUtil;

class bk implements OnTouchListener {
    final /* synthetic */ VideoRecordActivity a;

    bk(VideoRecordActivity videoRecordActivity) {
        this.a = videoRecordActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                boolean z;
                DebugUtil.debug(VideoRecordActivity.a, "ACTION_DOWN");
                this.a.e.setSelectedLast(false);
                this.a.h.setImageResource(R.drawable.video_delete_normal);
                this.a.h.setAlpha(this.a.s.size() > 0 ? 1.0f : 0.3f);
                ImageView h = this.a.h;
                if (this.a.s.size() > 0) {
                    z = true;
                } else {
                    z = false;
                }
                h.setEnabled(z);
                this.a.i.setImageResource(R.drawable.video_finish_select);
                if (this.a.s.size() == 0) {
                    this.a.i.setEnabled(false);
                    this.a.i.setAlpha(0.3f);
                    LayoutParams layoutParams = (LayoutParams) this.a.g.getLayoutParams();
                    layoutParams.bottomMargin = this.a.f.getBottom() + 2;
                    this.a.g.setLayoutParams(layoutParams);
                    this.a.g.setVisibility(0);
                    this.a.g.setText("按住拍摄视频需要超过3秒");
                    this.a.g.postDelayed(new bl(this), 1000);
                }
                this.a.d.setVisibility(4);
                this.a.i();
                break;
            case 1:
                DebugUtil.debug(VideoRecordActivity.a, "ACTION_UP");
                this.a.d.setVisibility(0);
                this.a.j();
                break;
        }
        return true;
    }
}
