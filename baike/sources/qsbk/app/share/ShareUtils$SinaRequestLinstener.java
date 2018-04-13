package qsbk.app.share;

import android.os.Message;
import com.umeng.analytics.pro.b;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.thirdparty.ThirdPartyException;
import qsbk.app.thirdparty.net.RequestListener;
import qsbk.app.utils.LogUtil;

public class ShareUtils$SinaRequestLinstener implements RequestListener {
    Message b = null;
    final /* synthetic */ ShareUtils c;

    public ShareUtils$SinaRequestLinstener(ShareUtils shareUtils) {
        this.c = shareUtils;
    }

    public void onComplete(String str) {
        LogUtil.e("分享邮箱的返回" + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("error_code") || jSONObject.has(b.J)) {
                this.b = this.c.a.obtainMessage(499, jSONObject.optString(b.J));
            } else if (jSONObject.has("id")) {
                this.b = this.c.a.obtainMessage(500, "分享成功");
            }
            this.b.sendToTarget();
        } catch (JSONException e) {
            this.b = this.c.a.obtainMessage(499, "数据解析失败");
            this.b.sendToTarget();
            e.printStackTrace();
        }
    }

    public void onIOException(IOException iOException) {
        this.b = this.c.a.obtainMessage(499, "分享失败");
        this.b.sendToTarget();
    }

    public void onError(ThirdPartyException thirdPartyException) {
        this.b = this.c.a.obtainMessage(499, "分享失败");
        this.b.sendToTarget();
    }
}
