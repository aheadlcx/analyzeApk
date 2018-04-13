package bdj.uk.co.senab.photoview.a;

import android.content.Context;
import android.os.Build.VERSION;

public final class f {
    public static d a(Context context, e eVar) {
        d aVar;
        int i = VERSION.SDK_INT;
        if (i < 5) {
            aVar = new a(context);
        } else if (i < 8) {
            aVar = new b(context);
        } else {
            aVar = new c(context);
        }
        aVar.a(eVar);
        return aVar;
    }
}
