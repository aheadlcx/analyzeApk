package cn.xiaochuan.push.oppo;

import android.content.Context;
import android.support.annotation.Keep;
import com.coloros.mcssdk.PushService;
import com.coloros.mcssdk.e.a;
import com.coloros.mcssdk.e.b;
import com.coloros.mcssdk.e.d;

@Keep
public class OPPOService extends PushService {
    public void processMessage(Context context, a aVar) {
        super.processMessage(context, aVar);
    }

    public void processMessage(Context context, d dVar) {
        super.processMessage(context, dVar);
    }

    public void processMessage(Context context, b bVar) {
        super.processMessage(context, bVar);
        com.izuiyou.a.a.b.c(bVar);
    }
}
