package qsbk.app.slide;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.R;

class a implements OnClickListener {
    final /* synthetic */ BaseEmotionFragment a;

    a(BaseEmotionFragment baseEmotionFragment) {
        this.a = baseEmotionFragment;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendEmotion:
                this.a.j = true;
                this.a.b();
                return;
            case R.id.sendKeyboard:
            case R.id.addCmtEditText:
                if (this.a.g()) {
                    this.a.d();
                }
                this.a.c();
                this.a.j = false;
                this.a.f.setVisibility(0);
                this.a.e.setVisibility(8);
                return;
            default:
                return;
        }
    }
}
