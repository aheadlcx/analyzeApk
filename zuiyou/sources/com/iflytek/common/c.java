package com.iflytek.common;

import android.util.Log;
import com.iflytek.cloud.thirdparty.do;

final class c {
    static do a;

    protected static do a() {
        if (a != null) {
            return a;
        }
        try {
            do doVar = (do) Class.forName("com.iflytek.common.push.impl.PushImpl").newInstance();
            a = doVar;
            if (doVar != null) {
                return a;
            }
        } catch (Exception e) {
            Log.e("PushFactory", "getPushInstance not found push instance.");
        }
        return null;
    }
}
