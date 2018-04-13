package qsbk.app.ad.feedsad.qbad;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.HashMap;
import qsbk.app.im.OfficialInfoActivity;

class i implements OnClickListener {
    final /* synthetic */ HashMap a;
    final /* synthetic */ QbAdItem b;

    i(QbAdItem qbAdItem, HashMap hashMap) {
        this.b = qbAdItem;
        this.a = hashMap;
    }

    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), OfficialInfoActivity.class);
        intent.putExtra("uid", (String) this.a.get("id"));
        intent.putExtra("name", this.b.user_name);
        intent.putExtra("avatar", this.b.user_icon);
        view.getContext().startActivity(intent);
    }
}
