package com.facebook.drawee.drawable;

import android.graphics.drawable.Drawable;

class ArrayDrawable$1 implements DrawableParent {
    final /* synthetic */ ArrayDrawable this$0;
    final /* synthetic */ int val$index;

    ArrayDrawable$1(ArrayDrawable arrayDrawable, int i) {
        this.this$0 = arrayDrawable;
        this.val$index = i;
    }

    public Drawable setDrawable(Drawable drawable) {
        return this.this$0.setDrawable(this.val$index, drawable);
    }

    public Drawable getDrawable() {
        return this.this$0.getDrawable(this.val$index);
    }
}
