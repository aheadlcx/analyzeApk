package qsbk.app.live.share;

import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.utils.StorageUtils;

class o implements Runnable {
    final /* synthetic */ LiveShareActivity a;

    o(LiveShareActivity liveShareActivity) {
        this.a = liveShareActivity;
    }

    public void run() {
        File file = new File(StorageUtils.getCacheDirectory(QsbkApp.getInstance()), "share.png");
        if (file.exists()) {
            file.delete();
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(file);
            this.a.e.compress(CompressFormat.PNG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            String path = file.getPath();
            Bundle bundle = new Bundle();
            bundle.putString("imageLocalUrl", path);
            bundle.putString("appName", this.a.getString(R.string.app_name));
            bundle.putInt("req_type", 5);
            this.a.a.post(new p(this, bundle));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
