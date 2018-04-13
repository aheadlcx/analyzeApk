package com.bumptech.glide.g.b;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.g.a.c;
import com.bumptech.glide.g.a.c$a;

public abstract class e<Z> extends k<ImageView, Z> implements c$a {
    protected abstract void a(Z z);

    public e(ImageView imageView) {
        super(imageView);
    }

    public Drawable b() {
        return ((ImageView) this.b).getDrawable();
    }

    public void a(Drawable drawable) {
        ((ImageView) this.b).setImageDrawable(drawable);
    }

    public void onLoadStarted(Drawable drawable) {
        ((ImageView) this.b).setImageDrawable(drawable);
    }

    public void onLoadFailed(Exception exception, Drawable drawable) {
        ((ImageView) this.b).setImageDrawable(drawable);
    }

    public void onLoadCleared(Drawable drawable) {
        ((ImageView) this.b).setImageDrawable(drawable);
    }

    public void onResourceReady(Z z, c<? super Z> cVar) {
        if (cVar == null || !cVar.a(z, this)) {
            a((Object) z);
        }
    }
}
