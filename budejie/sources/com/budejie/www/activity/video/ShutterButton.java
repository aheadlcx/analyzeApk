package com.budejie.www.activity.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ShutterButton extends ImageView {
    private a a;
    private boolean b;

    public interface a {
        void a();

        void a(boolean z);
    }

    public ShutterButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnShutterButtonListener(a aVar) {
        this.a = aVar;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        final boolean isPressed = isPressed();
        if (isPressed != this.b) {
            if (isPressed) {
                a(isPressed);
            } else {
                post(new Runnable(this) {
                    final /* synthetic */ ShutterButton b;

                    public void run() {
                        this.b.a(isPressed);
                    }
                });
            }
            this.b = isPressed;
        }
    }

    private void a(boolean z) {
        if (this.a != null) {
            this.a.a(z);
        }
    }

    public boolean performClick() {
        boolean performClick = super.performClick();
        if (this.a != null) {
            this.a.a();
        }
        return performClick;
    }
}
