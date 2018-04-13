package qsbk.app.cafe.plugin;

import android.content.Intent;
import android.os.Bundle;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.activity.group.SplashGroup;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;

public class ShortcutPlugin extends Plugin {
    public static final String MODEL = "shortcut";
    private static final String[] a = new String[]{"add"};
    private String c;
    private String d;
    private String e;

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        if (a[0].equalsIgnoreCase(str)) {
            this.c = jSONObject.optString("name");
            this.d = jSONObject.optString("avatarurl");
            this.e = jSONObject.optString("url");
            a();
            callback.sendResult(0, "", "");
            return;
        }
        callback.sendResult(1, str + " is not exist...", "");
    }

    private void a() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(1073741824);
        Bundle bundle = new Bundle();
        bundle.putString("url", this.e);
        intent.putExtras(bundle);
        intent.setClass(this.b.getCurActivity(), SplashGroup.class);
        Intent intent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent2.putExtra("duplicate", false);
        intent2.putExtra("android.intent.extra.shortcut.NAME", this.c);
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(this.d), this.b.getCurActivity().getApplicationContext()).subscribe(new e(this, intent2, intent), CallerThreadExecutor.getInstance());
    }

    public void onDestroy() {
    }
}
