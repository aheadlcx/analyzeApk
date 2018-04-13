package qsbk.app.im;

import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

class j extends Animation {
    final /* synthetic */ View a;
    final /* synthetic */ ChatListAdapter b;

    j(ChatListAdapter chatListAdapter, View view) {
        this.b = chatListAdapter;
        this.a = view;
    }

    protected void applyTransformation(float f, Transformation transformation) {
        super.applyTransformation(f, transformation);
        if (f > 0.9f) {
            this.a.setBackgroundColor(0);
        } else {
            this.a.setBackgroundColor(Color.argb((int) (255.0f * f), 84, 219, 170));
        }
    }

    public boolean willChangeTransformationMatrix() {
        return false;
    }

    public boolean willChangeBounds() {
        return false;
    }
}
