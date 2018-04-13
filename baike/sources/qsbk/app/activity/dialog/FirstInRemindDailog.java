package qsbk.app.activity.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import qsbk.app.R;
import qsbk.app.utils.TimeDelta;
import qsbk.app.utils.Util;

public class FirstInRemindDailog extends Activity {
    public static final int REQUEST_CODE = 1776;
    private TimeDelta a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_first_in_guide);
        getWindow().setLayout(-1, -1);
        findViewById(R.id.refresh).setOnClickListener(new a(this));
        this.a = new TimeDelta();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 3 || motionEvent.getActionMasked() == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (x <= ((float) (displayMetrics.widthPixels / 5)) && (((float) Util.statusBarHeight) + y) + (displayMetrics.density * 48.0f) >= ((float) displayMetrics.heightPixels)) {
                setResult(-1);
                finish();
            } else if (this.a.getDelta() > 3000) {
                setResult(0);
                finish();
            }
        }
        return super.onTouchEvent(motionEvent);
    }
}
