package qsbk.app.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class oo implements OnClickListener {
    final /* synthetic */ ImagePreviewActivity a;

    oo(ImagePreviewActivity imagePreviewActivity) {
        this.a = imagePreviewActivity;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("paths", this.a.b);
        this.a.setResult(999, intent);
        this.a.finish();
    }
}
