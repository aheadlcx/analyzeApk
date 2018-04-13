package com.bumptech.glide.g.b;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class b extends e<Bitmap> {
    public b(ImageView imageView) {
        super(imageView);
    }

    protected void a(Bitmap bitmap) {
        ((ImageView) this.b).setImageBitmap(bitmap);
    }
}
