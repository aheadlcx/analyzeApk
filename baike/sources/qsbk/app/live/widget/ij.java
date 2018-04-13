package qsbk.app.live.widget;

import qsbk.app.live.R;

class ij implements Runnable {
    final /* synthetic */ RollTableView a;

    ij(RollTableView rollTableView) {
        this.a = rollTableView;
    }

    public void run() {
        boolean z = true;
        long j = 500;
        if (this.a.i == 1) {
            j = 100;
        }
        if (this.a.j) {
            this.a.c.setImageResource(R.drawable.game_rolltable_light1);
        } else if (this.a.i == 0) {
            this.a.c.setImageResource(R.drawable.game_rolltable_light2);
        } else {
            this.a.c.setImageResource(R.drawable.game_rolltable_light3);
        }
        RollTableView rollTableView = this.a;
        if (this.a.j) {
            z = false;
        }
        rollTableView.j = z;
        this.a.postDelayed(this.a.k, j);
    }
}
