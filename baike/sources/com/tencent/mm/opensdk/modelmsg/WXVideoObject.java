package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage.IMediaObject;
import com.tencent.mm.opensdk.utils.Log;

public class WXVideoObject implements IMediaObject {
    public String videoLowBandUrl;
    public String videoUrl;

    public boolean checkArgs() {
        if ((this.videoUrl == null || this.videoUrl.length() == 0) && (this.videoLowBandUrl == null || this.videoLowBandUrl.length() == 0)) {
            Log.e("MicroMsg.SDK.WXVideoObject", "both arguments are null");
            return false;
        } else if (this.videoUrl != null && this.videoUrl.length() > 10240) {
            Log.e("MicroMsg.SDK.WXVideoObject", "checkArgs fail, videoUrl is too long");
            return false;
        } else if (this.videoLowBandUrl == null || this.videoLowBandUrl.length() <= 10240) {
            return true;
        } else {
            Log.e("MicroMsg.SDK.WXVideoObject", "checkArgs fail, videoLowBandUrl is too long");
            return false;
        }
    }

    public void serialize(Bundle bundle) {
        bundle.putString("_wxvideoobject_videoUrl", this.videoUrl);
        bundle.putString("_wxvideoobject_videoLowBandUrl", this.videoLowBandUrl);
    }

    public int type() {
        return 4;
    }

    public void unserialize(Bundle bundle) {
        this.videoUrl = bundle.getString("_wxvideoobject_videoUrl");
        this.videoLowBandUrl = bundle.getString("_wxvideoobject_videoLowBandUrl");
    }
}