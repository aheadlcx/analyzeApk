package com.flurry.android;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

final class s extends LinearLayout {
    public s(CatalogActivity catalogActivity, Context context) {
        super(context);
        setBackgroundColor(-1);
        AdImage m = catalogActivity.e.m();
        if (m != null) {
            View imageView = new ImageView(context);
            imageView.setId(10000);
            byte[] bArr = m.e;
            if (bArr != null) {
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
            }
            r.a(context, imageView, r.a(context, m.b), r.a(context, m.c));
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, 0, -3);
            setGravity(3);
            addView(imageView, layoutParams);
        }
    }
}
