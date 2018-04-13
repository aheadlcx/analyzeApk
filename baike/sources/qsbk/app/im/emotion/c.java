package qsbk.app.im.emotion;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.File;
import java.io.IOException;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.HttpClient;

class c extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ Context a;
    final /* synthetic */ EmotionManager b;

    c(EmotionManager emotionManager, Context context) {
        this.b = emotionManager;
        this.a = context;
    }

    protected Void a(Void... voidArr) {
        AssetManager assets = this.a.getAssets();
        try {
            for (String str : assets.list("emotion")) {
                if (!str.endsWith("_small")) {
                    EmotionManager$a a = EmotionManager.a(this.b, HttpClient.readStream(assets.open("emotion" + File.separator + str)), this.a);
                    if (a != null) {
                        EmotionManager.a().put(a.b, a.a);
                    }
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
        EmotionManager.a(this.b, true);
        EmotionManager.b(this.b, false);
    }
}
