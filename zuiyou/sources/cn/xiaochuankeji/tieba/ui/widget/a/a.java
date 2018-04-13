package cn.xiaochuankeji.tieba.ui.widget.a;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;

public class a extends b.a.a.a.a {
    public a() {
        setAddDuration(200);
        setRemoveDuration(100);
    }

    protected void a(ViewHolder viewHolder) {
        ViewCompat.animate(viewHolder.itemView).translationY(((float) viewHolder.itemView.getHeight()) * 0.25f).alpha(0.0f).setDuration(getRemoveDuration()).setInterpolator(this.c).setListener(new c(this, viewHolder)).setStartDelay(e(viewHolder)).start();
    }

    protected void b(ViewHolder viewHolder) {
        ViewCompat.setAlpha(viewHolder.itemView, 0.0f);
    }

    protected void c(ViewHolder viewHolder) {
        ViewCompat.animate(viewHolder.itemView).alpha(1.0f).setDuration(getAddDuration()).setInterpolator(this.c).setListener(new b(this, viewHolder)).setStartDelay(f(viewHolder)).start();
    }

    public boolean animateChange(ViewHolder viewHolder, ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        return true;
    }
}
