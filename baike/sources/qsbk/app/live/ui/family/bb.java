package qsbk.app.live.ui.family;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;

class bb extends OnScrollListener {
    final /* synthetic */ FamilyRankFragment a;

    bb(FamilyRankFragment familyRankFragment) {
        this.a = familyRankFragment;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        float f = 1.0f;
        float f2 = 0.0f;
        super.onScrolled(recyclerView, i, i2);
        if (!this.a.i && this.a.j && i2 > 0) {
            this.a.b();
        }
        this.a.p = this.a.p + i2;
        if (this.a.p > 0) {
            float d = ((float) (this.a.p - WindowUtils.dp2Px(110))) / ((float) WindowUtils.dp2Px(64));
            if (d >= 1.0f) {
                this.a.l.setImageResource(R.drawable.live_family_rank_icon_add);
                this.a.m.setImageResource(R.drawable.live_family_rank_icon_about);
                this.a.c();
            } else {
                f = d;
            }
            if (f <= 0.0f) {
                this.a.l.setImageResource(R.drawable.live_family_rank_add_yellow);
                this.a.m.setImageResource(R.drawable.live_family_rank_about_yellow);
                this.a.d();
            } else {
                f2 = f;
            }
            this.a.o.setAlpha(f2);
            return;
        }
        this.a.o.setAlpha(0.0f);
        this.a.l.setImageResource(R.drawable.live_family_rank_add_yellow);
        this.a.m.setImageResource(R.drawable.live_family_rank_about_yellow);
        this.a.d();
    }
}
