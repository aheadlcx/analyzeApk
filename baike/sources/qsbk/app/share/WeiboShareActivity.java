package qsbk.app.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.BaseMediaObject;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.QsbkApp;
import qsbk.app.thirdparty.ThirdPartyConstants;

public class WeiboShareActivity extends Activity implements WbShareCallback {
    public static final String WEIBO_SHARE = "weibo_share";
    DataSource<CloseableReference<CloseableImage>> a;
    private WbShareHandler b;
    private String c;

    public static void shareToWeiBo(String str, String str2, String str3, String str4) {
        shareToWeiBo(null, str, str2, str3, str4);
    }

    public static void shareToWeiBo(String str, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent(QsbkApp.getInstance(), WeiboShareActivity.class);
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        intent.putExtra("id", str);
        intent.putExtra("title", str2);
        intent.putExtra("message", str3);
        intent.putExtra("url", str4);
        intent.putExtra("bitmap", str5);
        QsbkApp.getInstance().startActivity(intent);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        WbSdk.install(QsbkApp.getInstance(), new AuthInfo(QsbkApp.getInstance(), ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.SINA_SCOPE));
        this.b = new WbShareHandler(this);
        this.b.registerApp();
        Intent intent = getIntent();
        if (intent != null) {
            this.c = intent.getStringExtra("id");
            String stringExtra = intent.getStringExtra("title");
            String stringExtra2 = intent.getStringExtra("message");
            CharSequence stringExtra3 = intent.getStringExtra("url");
            Object stringExtra4 = intent.getStringExtra("bitmap");
            WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
            BaseMediaObject webpageObject = new WebpageObject();
            TextObject textObject = new TextObject();
            textObject.text = stringExtra2;
            textObject.title = stringExtra;
            textObject.actionUrl = stringExtra3;
            weiboMultiMessage.textObject = textObject;
            if (!TextUtils.isEmpty(stringExtra3)) {
                webpageObject.identify = Utility.generateGUID();
                webpageObject.title = stringExtra;
                webpageObject.description = stringExtra2;
                webpageObject.actionUrl = stringExtra3;
                weiboMultiMessage.mediaObject = webpageObject;
            }
            if (TextUtils.isEmpty(stringExtra4) && TextUtils.isEmpty(stringExtra3)) {
                this.b.shareMessage(weiboMultiMessage, false);
                return;
            }
            ImageObject imageObject = new ImageObject();
            weiboMultiMessage.imageObject = imageObject;
            this.a = Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(stringExtra4), this);
            this.a.subscribe(new aj(this, imageObject, webpageObject, weiboMultiMessage), CallerThreadExecutor.getInstance());
            return;
        }
        finish();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.b.doResultIntent(intent, this);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.close();
        }
    }

    public void onWbShareSuccess() {
        sendBroadcast(a(this.c, "sucess"));
        finish();
    }

    public void onWbShareCancel() {
        sendBroadcast(a(this.c, "cancel"));
        finish();
    }

    public void onWbShareFail() {
        sendBroadcast(a(this.c, "fail"));
        finish();
    }

    private Intent a(String str, String str2) {
        Intent intent = new Intent(WEIBO_SHARE);
        intent.putExtra("share_id", str);
        intent.putExtra("share_result", str2);
        return intent;
    }
}
