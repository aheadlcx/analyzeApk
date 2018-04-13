package cn.xiaochuankeji.tieba.background.utils.share;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.utils.g;
import com.izuiyou.a.a.b;
import com.izuiyou.auth.a;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.opensdk.modelmsg.WXEmojiObject;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage.IMediaObject;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class c {
    private static c a;
    private IWXAPI b = WXAPIFactory.createWXAPI(a.a(), "wx16516ad81c31d872", true);

    public static c a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    private c() {
        this.b.registerApp("wx16516ad81c31d872");
    }

    public void a(boolean z, String str, String str2, String str3, String str4) {
        int i = 0;
        if (this.b.isWXAppInstalled()) {
            IMediaObject wXWebpageObject = new WXWebpageObject();
            wXWebpageObject.webpageUrl = str3;
            WXMediaMessage wXMediaMessage = new WXMediaMessage(wXWebpageObject);
            if (z) {
                str = str2;
            }
            wXMediaMessage.title = str;
            wXMediaMessage.description = str2;
            if (wXMediaMessage.title != null && wXMediaMessage.title.length() > 140) {
                wXMediaMessage.title = wXMediaMessage.title.substring(0, 140);
            }
            if (wXMediaMessage.description != null && wXMediaMessage.description.length() > 140) {
                wXMediaMessage.description = wXMediaMessage.description.substring(0, 140);
            }
            Bitmap a = a(str4);
            if (a != null) {
                wXMediaMessage.setThumbImage(a);
                if (!(a == null || a.isRecycled())) {
                    a.recycle();
                }
            }
            BaseReq req = new Req();
            req.transaction = b("webpage");
            req.message = wXMediaMessage;
            if (z) {
                i = 1;
            }
            req.scene = i;
            this.b.sendReq(req);
            return;
        }
        cn.htjyb.ui.widget.a.a(a.a(), (CharSequence) "您未安装微信或微信版本过低", 0).show();
    }

    private Bitmap a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = a.a();
            }
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            options.inSampleSize = a(options, 96, 96);
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Config.RGB_565;
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            int min = Math.min(Math.min(decodeFile.getWidth(), decodeFile.getHeight()), 96);
            Matrix matrix = new Matrix();
            matrix.setRectToRect(new RectF(0.0f, 0.0f, (float) decodeFile.getWidth(), (float) decodeFile.getHeight()), new RectF(0.0f, 0.0f, (float) min, (float) min), ScaleToFit.CENTER);
            return Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, true);
        } catch (Exception e) {
            b.e(e);
            return null;
        }
    }

    private Bitmap a(Bitmap bitmap) {
        if (bitmap == null) {
            return a(a.a());
        }
        Bitmap copy = bitmap.copy(Config.RGB_565, true);
        int min = Math.min(Math.min(copy.getWidth(), copy.getHeight()), 96);
        Matrix matrix = new Matrix();
        matrix.setRectToRect(new RectF(0.0f, 0.0f, (float) copy.getWidth(), (float) copy.getHeight()), new RectF(0.0f, 0.0f, (float) min, (float) min), ScaleToFit.CENTER);
        return Bitmap.createBitmap(copy, 0, 0, copy.getWidth(), copy.getHeight(), matrix, true);
    }

    public int a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            i5 = Math.round(((float) i3) / ((float) i2));
            int round = Math.round(((float) i4) / ((float) i));
            if (i5 >= round) {
                i5 = round;
            }
        }
        float f = (float) (i4 * i3);
        while (f / ((float) (i5 * i5)) > ((float) ((i * i2) * 2))) {
            i5++;
        }
        return i5;
    }

    public void a(boolean z, Bitmap bitmap, String str) {
        int i = 0;
        if (this.b.isWXAppInstalled()) {
            Bitmap a;
            IMediaObject wXImageObject = new WXImageObject();
            wXImageObject.setImagePath(str);
            WXMediaMessage wXMediaMessage = new WXMediaMessage(wXImageObject);
            if (bitmap == null || bitmap.isRecycled()) {
                a = a(str);
            } else {
                a = a(bitmap);
            }
            wXMediaMessage.setThumbImage(a);
            BaseReq req = new Req();
            req.transaction = b("img");
            req.message = wXMediaMessage;
            if (z) {
                i = 1;
            }
            req.scene = i;
            this.b.sendReq(req);
            if (a != null && !a.isRecycled()) {
                a.recycle();
                return;
            }
            return;
        }
        cn.htjyb.ui.widget.a.a(a.a(), (CharSequence) "您未安装微信", 0).show();
    }

    public void a(boolean z, String str, String str2, String str3, String str4, String str5) {
        int i = 0;
        if (this.b.isWXAppInstalled()) {
            if (str3.length() >= 512) {
                str3 = str3.substring(0, 500);
            }
            IMediaObject wXMusicObject = new WXMusicObject();
            wXMusicObject.musicUrl = str;
            wXMusicObject.musicDataUrl = str2;
            WXMediaMessage wXMediaMessage = new WXMediaMessage(wXMusicObject);
            wXMediaMessage.title = str3;
            wXMediaMessage.description = str4;
            wXMediaMessage.setThumbImage(a(str5));
            BaseReq req = new Req();
            req.transaction = b("music");
            req.message = wXMediaMessage;
            if (z) {
                i = 1;
            }
            req.scene = i;
            this.b.sendReq(req);
            return;
        }
        g.a("您未安装微信");
    }

    public void a(Bitmap bitmap, String str) {
        if (this.b.isWXAppInstalled()) {
            IMediaObject wXEmojiObject = new WXEmojiObject();
            wXEmojiObject.emojiPath = str;
            WXMediaMessage wXMediaMessage = new WXMediaMessage(wXEmojiObject);
            Bitmap a = a(bitmap);
            wXMediaMessage.setThumbImage(a);
            if (!(a == null || a.isRecycled())) {
                a.recycle();
            }
            BaseReq req = new Req();
            req.transaction = b("emoji");
            req.message = wXMediaMessage;
            req.scene = 0;
            this.b.sendReq(req);
            return;
        }
        cn.htjyb.ui.widget.a.a(a.a(), (CharSequence) "您未安装微信", 0).show();
    }

    public void b() {
        BaseReq req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wx16516ad81c31d872";
        this.b.sendReq(req);
    }

    public void a(Intent intent, IWXAPIEventHandler iWXAPIEventHandler) {
        this.b.handleIntent(intent, iWXAPIEventHandler);
    }

    private String b(String str) {
        if (str == null) {
            return String.valueOf(System.currentTimeMillis());
        }
        return str + System.currentTimeMillis();
    }
}
