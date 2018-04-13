package qsbk.app.im.group.vote;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import qsbk.app.im.group.vote.bean.GroupManagerCandidate;

class c implements OnPageChangeListener {
    final /* synthetic */ GroupManagerVoteActivity a;

    c(GroupManagerVoteActivity groupManagerVoteActivity) {
        this.a = groupManagerVoteActivity;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (i > this.a.o) {
            this.a.l.onKeyDown(22, null);
        } else if (i < this.a.o) {
            this.a.l.onKeyDown(21, null);
        }
        this.a.o = i;
        if (i == this.a.d.size() - 1) {
            this.a.setTitle("投票");
            this.a.a((GroupManagerCandidate) this.a.d.get(i), true);
            return;
        }
        this.a.setTitle(String.format("群大选举(%1$s/%2$s)", new Object[]{Integer.valueOf(i + 1), Integer.valueOf(this.a.d.size() - 1)}));
        this.a.a((GroupManagerCandidate) this.a.d.get(i), false);
    }

    public void onPageScrollStateChanged(int i) {
    }
}
