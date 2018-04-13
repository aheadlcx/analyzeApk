package qsbk.app.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class CommonEditText extends EditText {
    private IKeycodeBackListener a;

    public interface IKeycodeBackListener {
        void onKeycodeBack();
    }

    public CommonEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CommonEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i == 4 && this.a != null) {
            this.a.onKeycodeBack();
        }
        return super.onKeyPreIme(i, keyEvent);
    }

    public void setKeycodeBackListener(IKeycodeBackListener iKeycodeBackListener) {
        this.a = iKeycodeBackListener;
    }
}
