package com.bumptech.glide.g.b;

import android.widget.ImageView;
import com.bumptech.glide.g.a.c;
import com.bumptech.glide.load.resource.a.b;

public class d extends e<b> {
    private int a;
    private b c;

    public /* synthetic */ void onResourceReady(Object obj, c cVar) {
        a((b) obj, cVar);
    }

    public d(ImageView imageView) {
        this(imageView, -1);
    }

    public d(ImageView imageView, int i) {
        super(imageView);
        this.a = i;
    }

    public void a(b bVar, c<? super b> cVar) {
        if (!bVar.a()) {
            float intrinsicWidth = ((float) bVar.getIntrinsicWidth()) / ((float) bVar.getIntrinsicHeight());
            if (Math.abs((((float) ((ImageView) this.b).getWidth()) / ((float) ((ImageView) this.b).getHeight())) - 1.0f) <= 0.05f && Math.abs(intrinsicWidth - 1.0f) <= 0.05f) {
                bVar = new i(bVar, ((ImageView) this.b).getWidth());
            }
        }
        super.onResourceReady(bVar, cVar);
        this.c = bVar;
        bVar.a(this.a);
        bVar.start();
    }

    protected void a(b bVar) {
        ((ImageView) this.b).setImageDrawable(bVar);
    }

    public void onStart() {
        if (this.c != null) {
            this.c.start();
        }
    }

    public void onStop() {
        if (this.c != null) {
            this.c.stop();
        }
    }
}
