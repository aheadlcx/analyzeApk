package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.Random;

class vv implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    vv(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        int nextInt = 1 + (new Random().nextInt(5) % 5);
        if (nextInt == this.a.bE) {
            nextInt = (nextInt % 5) + 1;
        }
        this.a.bE = nextInt;
        this.a.bP = this.a.bE;
        this.a.setPersonalBgImage(String.valueOf(this.a.bE));
    }
}
