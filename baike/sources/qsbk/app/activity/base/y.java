package qsbk.app.activity.base;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;

class y implements OnClickListener {
    final /* synthetic */ BaseEmotionActivity a;

    y(BaseEmotionActivity baseEmotionActivity) {
        this.a = baseEmotionActivity;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendEmotion:
                this.a.a = true;
                this.a.n();
                return;
            case R.id.sendKeyboard:
            case R.id.addCmtEditText:
                if (this.a.t()) {
                    this.a.q();
                }
                this.a.showKeyboard();
                this.a.a = false;
                this.a.O.setVisibility(0);
                this.a.K.setVisibility(8);
                return;
            default:
                return;
        }
    }
}
