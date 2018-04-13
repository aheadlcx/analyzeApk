package qsbk.app.widget;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.File;
import java.io.IOException;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.HttpClient;

class dl extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ Context a;
    final /* synthetic */ QiushiEmotionHandler b;

    dl(QiushiEmotionHandler qiushiEmotionHandler, Context context) {
        this.b = qiushiEmotionHandler;
        this.a = context;
    }

    protected Void a(Void... voidArr) {
        AssetManager assets = this.a.getAssets();
        try {
            for (String str : assets.list("emotion")) {
                if (str.endsWith("_small")) {
                    QiushiEmotionHandler.a(this.b, HttpClient.readStream(assets.open("emotion" + File.separator + str)), this.a);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    protected void a(Void voidR) {
        super.a(voidR);
        QiushiEmotionHandler.a(this.b, true);
        QiushiEmotionHandler.b(this.b, false);
    }
}
