package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;

class agk implements OnClickListener {
    final /* synthetic */ WebEmotionActivity a;

    agk(WebEmotionActivity webEmotionActivity) {
        this.a = webEmotionActivity;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendEmotion:
                this.a.p = true;
                this.a.i();
                return;
            case R.id.sendKeyboard:
            case R.id.addCmtEditText:
                if (this.a.n()) {
                    this.a.k();
                }
                this.a.j();
                this.a.p = false;
                this.a.g.setVisibility(0);
                this.a.f.setVisibility(8);
                return;
            default:
                return;
        }
    }
}
