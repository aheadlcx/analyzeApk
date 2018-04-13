package com.budejie.www.activity.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ControlPanelLayout extends RelativeLayout {
    private a a;
    private Context b;
    private boolean c;

    public interface a {
        void a(boolean z);
    }

    public ControlPanelLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
    }

    public void setOnPanelTouchListener(a aVar) {
        this.a = aVar;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        final boolean isPressed = isPressed();
        if (isPressed != this.c) {
            if (isPressed) {
                a(isPressed);
            } else {
                post(new Runnable(this) {
                    final /* synthetic */ ControlPanelLayout b;

                    public void run() {
                        this.b.a(isPressed);
                    }
                });
            }
            this.c = isPressed;
        }
    }

    public void a(boolean z) {
        if (this.a != null) {
            this.a.a(z);
        }
    }
}
