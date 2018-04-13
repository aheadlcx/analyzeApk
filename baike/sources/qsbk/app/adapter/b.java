package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.ImageView;

class b implements OnClickListener {
    final /* synthetic */ ImageView a;
    final /* synthetic */ Animation b;
    final /* synthetic */ ArticleAdapter c;

    b(ArticleAdapter articleAdapter, ImageView imageView, Animation animation) {
        this.c = articleAdapter;
        this.a = imageView;
        this.b = animation;
    }

    public void onClick(View view) {
        this.a.startAnimation(this.b);
    }
}
