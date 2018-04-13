package qsbk.app.live.ui.family;

import qsbk.app.live.R;

class v implements Runnable {
    final /* synthetic */ FamilyDetailActivity a;

    v(FamilyDetailActivity familyDetailActivity) {
        this.a = familyDetailActivity;
    }

    public void run() {
        int lineCount = this.a.g.getLineCount();
        this.a.g.setMaxLines(3);
        this.a.g.setText(this.a.I);
        this.a.a = 3;
        if (lineCount > 3) {
            this.a.l.setVisibility(0);
            this.a.l.setImageResource(R.drawable.ic_arrow_down);
            this.a.a(3);
            return;
        }
        this.a.l.setVisibility(4);
    }
}
