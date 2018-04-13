package qsbk.app.live.ui;

import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;

class c extends Callback {
    final /* synthetic */ LevelGiftPayActivity a;

    c(LevelGiftPayActivity levelGiftPayActivity) {
        this.a = levelGiftPayActivity;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("boxid", this.a.m + "");
        return hashMap;
    }
}
