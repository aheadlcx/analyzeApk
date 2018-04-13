package qsbk.app.fragments;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.cache.FileCache;
import qsbk.app.http.HttpCallBack;

class en implements HttpCallBack {
    final /* synthetic */ LaiseeVoiceSendFragment a;

    en(LaiseeVoiceSendFragment laiseeVoiceSendFragment) {
        this.a = laiseeVoiceSendFragment;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (this.a.isAdded() && !this.a.isDetached()) {
            this.a.a(jSONObject);
            FileCache.cacheTextToDisk(QsbkApp.getInstance().getApplicationContext(), LaiseeVoiceSendFragment.VOICE_WORDS, jSONObject.toString());
        }
    }

    public void onFailure(String str, String str2) {
        if (this.a.isAdded() && !this.a.isDetached() && this.a.b.size() == 0) {
            this.a.v.show((CharSequence) "加载失败，点击重试", true);
        }
    }
}
