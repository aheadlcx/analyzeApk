package qsbk.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class RecordButton extends Button {
    OnRecordListener a;

    public interface OnRecordListener {
        void onRecording(View view);

        void onStart(View view);

        void onStop(View view);
    }

    public RecordButton(Context context) {
        super(context);
    }

    public RecordButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RecordButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        switch (motionEvent.getAction()) {
            case 0:
                this.a.onStart(this);
                break;
            case 1:
            case 3:
                this.a.onStop(this);
                break;
            case 2:
                this.a.onRecording(this);
                break;
        }
        return true;
    }

    public void setOnRecordListener(OnRecordListener onRecordListener) {
        this.a = onRecordListener;
    }
}
