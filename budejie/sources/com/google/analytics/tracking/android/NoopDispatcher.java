package com.google.analytics.tracking.android;

import android.text.TextUtils;
import java.util.List;

class NoopDispatcher implements Dispatcher {
    NoopDispatcher() {
    }

    public boolean okToDispatch() {
        return true;
    }

    public int dispatchHits(List<Hit> list) {
        if (list == null) {
            return 0;
        }
        int min = Math.min(list.size(), 40);
        if (Log.isVerbose()) {
            Log.v("Hits not actually being sent as dispatch is false...");
            for (int i = 0; i < min; i++) {
                String str;
                String postProcessHit = TextUtils.isEmpty(((Hit) list.get(i)).getHitParams()) ? "" : HitBuilder.postProcessHit((Hit) list.get(i), System.currentTimeMillis());
                if (TextUtils.isEmpty(postProcessHit)) {
                    str = "Hit couldn't be read, wouldn't be sent:";
                } else if (postProcessHit.length() <= 2036) {
                    str = "GET would be sent:";
                } else if (postProcessHit.length() > 8192) {
                    str = "Would be too big:";
                } else {
                    str = "POST would be sent:";
                }
                Log.v(str + postProcessHit);
            }
        }
        return min;
    }

    public void overrideHostUrl(String str) {
    }

    public void close() {
    }
}
