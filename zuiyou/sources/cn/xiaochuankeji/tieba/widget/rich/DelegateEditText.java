package cn.xiaochuankeji.tieba.widget.rich;

import android.content.Context;
import android.graphics.Rect;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import c.a.i.f;

public class DelegateEditText extends f implements b {
    private Rect a = new Rect();

    private class a extends InputConnectionWrapper {
        final /* synthetic */ DelegateEditText a;

        public a(DelegateEditText delegateEditText, InputConnection inputConnection, boolean z) {
            this.a = delegateEditText;
            super(inputConnection, z);
        }

        public boolean sendKeyEvent(KeyEvent keyEvent) {
            return super.sendKeyEvent(keyEvent);
        }

        public boolean deleteSurroundingText(int i, int i2) {
            if (i != 1 || i2 != 0) {
                return super.deleteSurroundingText(i, i2);
            }
            if (sendKeyEvent(new KeyEvent(0, 67)) && sendKeyEvent(new KeyEvent(1, 67))) {
                return true;
            }
            return false;
        }
    }

    public DelegateEditText(Context context) {
        super(context);
    }

    public DelegateEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DelegateEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return new a(this, super.onCreateInputConnection(editorInfo), true);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    public int getSpaceExtra() {
        return a();
    }

    public int a() {
        int lineCount = getLineCount() - 1;
        if (lineCount < 0) {
            return 0;
        }
        Layout layout = getLayout();
        lineCount = getLineBounds(lineCount, this.a);
        if (getMeasuredHeight() == getLayout().getHeight()) {
            return this.a.bottom - (lineCount + layout.getPaint().getFontMetricsInt().descent);
        }
        return 0;
    }
}
