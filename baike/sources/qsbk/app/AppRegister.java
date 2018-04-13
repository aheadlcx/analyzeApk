package qsbk.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class AppRegister extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        WXAPIFactory.createWXAPI(context, null).registerApp(Constants.APP_ID);
    }
}
