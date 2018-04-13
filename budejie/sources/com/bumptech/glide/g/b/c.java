package com.bumptech.glide.g.b;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class c extends e<Drawable> {
    protected /* synthetic */ void a(Object obj) {
        b((Drawable) obj);
    }

    public c(ImageView imageView) {
        super(imageView);
    }

    protected void b(Drawable drawable) {
        ((ImageView) this.b).setImageDrawable(drawable);
    }
}
