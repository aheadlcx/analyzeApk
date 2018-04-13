package qsbk.app.live.share;

import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import qsbk.app.QsbkApp;
import qsbk.app.utils.StorageUtils;

class d implements Runnable {
    final /* synthetic */ LiveShareActivity a;

    d(LiveShareActivity liveShareActivity) {
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
            ArrayList arrayList = new ArrayList();
            arrayList.add(path);
            Bundle bundle = new Bundle();
            bundle.putInt("req_type", 3);
            bundle.putStringArrayList("imageUrl", arrayList);
            this.a.a.post(new e(this, bundle));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
