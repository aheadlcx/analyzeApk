package qsbk.app.core.net.OkHttp;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.Volley;
import java.io.File;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class OkhttpVolley extends Volley {
    public static RequestQueue newRequestQueue(Context context, @NonNull Network network) {
        File file = new File(context.getCacheDir(), "volley");
        String str = "volley/0";
        try {
            str = context.getPackageName();
            str + MqttTopic.TOPIC_LEVEL_SEPARATOR + context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (NameNotFoundException e) {
        }
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(file), network);
        requestQueue.start();
        return requestQueue;
    }
}
