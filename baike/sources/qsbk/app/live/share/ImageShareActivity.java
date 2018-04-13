package qsbk.app.live.share;

import android.content.Intent;
import qsbk.app.R;
import qsbk.app.core.model.Share;
import qsbk.app.widget.BlackProgressDialog;

public class ImageShareActivity extends LiveShareActivity {
    int a;
    private String i;

    protected int a() {
        return R.layout.activity_share_image;
    }

    protected void b() {
        BlackProgressDialog blackProgressDialog = new BlackProgressDialog(this);
        blackProgressDialog.setOnCancelListener(new a(this));
        blackProgressDialog.show();
    }

    protected void c() {
        Intent intent = getIntent();
        if (intent != null) {
            this.i = intent.getStringExtra("imgUrl");
            this.a = intent.getIntExtra("type", 0);
            this.d = new Share();
            this.h = "image";
            a(this.i, new b(this));
        }
    }
}
