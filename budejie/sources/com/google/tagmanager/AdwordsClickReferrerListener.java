package com.google.tagmanager;

import android.content.Context;
import android.net.Uri;
import java.util.Map;

class AdwordsClickReferrerListener implements Listener {
    private final Context context;

    public AdwordsClickReferrerListener(Context context) {
        this.context = context;
    }

    public void changed(Map<Object, Object> map) {
        Object obj;
        Object obj2 = map.get("gtm.url");
        if (obj2 == null) {
            obj = map.get("gtm");
            if (obj != null && (obj instanceof Map)) {
                obj = ((Map) obj).get("url");
                if (obj != null && (obj instanceof String)) {
                    String queryParameter = Uri.parse((String) obj).getQueryParameter("referrer");
                    if (queryParameter != null) {
                        InstallReferrerUtil.addClickReferrer(this.context, queryParameter);
                        return;
                    }
                    return;
                }
            }
        }
        obj = obj2;
        if (obj != null) {
        }
    }
}
