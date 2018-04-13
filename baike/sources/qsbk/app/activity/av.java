package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class av implements OnClickListener {
    final /* synthetic */ AddQiuYouActivity a;

    av(AddQiuYouActivity addQiuYouActivity) {
        this.a = addQiuYouActivity;
    }

    public void onClick(View view) {
        this.a.c.setText("");
    }
}
