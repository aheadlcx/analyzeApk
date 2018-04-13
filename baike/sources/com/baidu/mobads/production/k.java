package com.baidu.mobads.production;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.alipay.sdk.util.e;
import com.baidu.mobads.c.a;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;

class k extends Handler {
    final /* synthetic */ IXAdInstanceInfo a;
    final /* synthetic */ a b;

    k(a aVar, Looper looper, IXAdInstanceInfo iXAdInstanceInfo) {
        this.b = aVar;
        this.a = iXAdInstanceInfo;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                String string;
                Object videoUrl;
                boolean z = message.getData().getBoolean("caching_result");
                String str = z ? "success" : e.b;
                if (z) {
                    string = message.getData().getString("local_creative_url");
                    this.a.setLocalCreativeURL(string);
                    if (this.a.getCreativeType() == CreativeType.RM) {
                        this.b.a(XAdSDKFoundationFacade.getInstance().getCommonUtils().md5(this.a.getMainPictureUrl()), Uri.parse(string));
                    }
                } else {
                    this.a.setLocalCreativeURL(null);
                }
                String str2 = "" + message.getData().getLong("caching_time_consume", 0);
                string = "";
                if (this.a.getCreativeType() == CreativeType.VIDEO) {
                    videoUrl = this.a.getVideoUrl();
                } else {
                    String mainPictureUrl = this.a.getCreativeType() == CreativeType.RM ? this.a.getMainPictureUrl() : string;
                }
                a.a().a(this.b.f, "383", this.a, this.b.k.d(), "file_dl_" + str, videoUrl, str2);
                return;
            default:
                return;
        }
    }
}
