package com.ut.device;

import android.content.Context;
import com.ta.utdid2.a.a;

public class UTDevice {
    public static String getUtdid(Context context) {
        return com.ta.utdid2.device.UTDevice.getUtdid(context);
    }

    public static String getAid(String str, String str2, Context context) {
        return a.a(context).a(str, str2, getUtdid(context));
    }

    public static void getAidAsync(String str, String str2, Context context, a aVar) {
        a.a(context).a(str, str2, getUtdid(context), aVar);
    }
}
