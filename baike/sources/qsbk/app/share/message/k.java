package qsbk.app.share.message;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class k implements OnClickListener {
    final /* synthetic */ ShareToIMMessageActivity a;

    k(ShareToIMMessageActivity shareToIMMessageActivity) {
        this.a = shareToIMMessageActivity;
    }

    public void onClick(View view) {
        Intent intent = new Intent(this.a, ChooseQiuyouActivity.class);
        intent.putExtras(this.a.c);
        this.a.startActivityForResult(intent, 1);
    }
}
