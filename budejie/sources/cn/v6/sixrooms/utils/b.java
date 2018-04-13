package cn.v6.sixrooms.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import cn.v6.sixrooms.net.NetworkManager;
import cn.v6.sixrooms.utils.BitmapUtils.CallBack;
import java.io.IOException;
import java.io.InputStream;

final class b extends AsyncTask<Void, Void, Bitmap> {
    final /* synthetic */ String a;
    final /* synthetic */ CallBack b;

    b(String str, CallBack callBack) {
        this.a = str;
        this.b = callBack;
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        Bitmap bitmap = (Bitmap) obj;
        super.onPostExecute(bitmap);
        this.b.handler(bitmap);
    }

    private Bitmap a() {
        Bitmap bitmap = null;
        InputStream sendGetRequest = NetworkManager.getInstance().sendGetRequest(this.a);
        if (sendGetRequest != null) {
            Options options = new Options();
            options.inPreferredConfig = Config.RGB_565;
            options.inPurgeable = true;
            options.inInputShareable = true;
            bitmap = BitmapFactory.decodeStream(sendGetRequest, null, options);
        }
        if (sendGetRequest != null) {
            try {
                sendGetRequest.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    protected final void onPreExecute() {
        super.onPreExecute();
    }
}
