package qsbk.app.im;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.commonsdk.proguard.g;

class gf implements OnClickListener {
    final /* synthetic */ IMChatingUrlContentDisplayActivity a;

    gf(IMChatingUrlContentDisplayActivity iMChatingUrlContentDisplayActivity) {
        this.a = iMChatingUrlContentDisplayActivity;
    }

    public void onClick(View view) {
        String[] strArr = new String[]{g.an, "politics", "abuse", "dirty", "others"};
        new Builder(this.a).setTitle("").setItems(new String[]{"广告/欺诈", "反动政治", "辱骂", "色情", "其它"}, new gh(this, strArr)).setNegativeButton("取消", new gg(this)).show();
    }
}
