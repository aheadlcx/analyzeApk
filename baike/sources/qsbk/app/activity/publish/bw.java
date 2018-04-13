package qsbk.app.activity.publish;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.util.UUID;
import qsbk.app.QsbkApp;
import qsbk.app.utils.FileUtils;

class bw implements OnClickListener {
    final /* synthetic */ Publish_Image a;

    bw(Publish_Image publish_Image) {
        this.a = publish_Image;
    }

    public void onClick(View view) {
        new File(Publish_Image.getSendImagePath(this.a.v)).delete();
        this.a.d = this.a.h();
        ((QsbkApp) this.a.getApplication()).setWaitSendBitmap(this.a.d);
        FileUtils.saveDrawable(this.a.d, this.a.v == null ? "send.jpg" : this.a.v, "qsbk/send/");
        String replaceAll = UUID.randomUUID().toString().replaceAll(Constants.ACCEPT_TIME_SEPARATOR_SERVER, "");
        FileUtils.saveDrawable(this.a.d, replaceAll + ".jpg", "qsbk/send/");
        Intent intent = new Intent();
        intent.putExtra("uuid", replaceAll);
        this.a.setResult(-1, intent);
        this.a.finish();
    }
}
