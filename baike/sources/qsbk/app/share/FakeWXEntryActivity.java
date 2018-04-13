package qsbk.app.share;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.StatService;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage.IMediaObject;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.model.QiushiTopic;
import qsbk.app.model.Qsjx;
import qsbk.app.model.ShareMsgItem;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.Util;

public class FakeWXEntryActivity extends FragmentActivity implements OnClickListener {
    public static final int ARTICLE = 0;
    public static final int CIRCLE_ARTICLE = 3;
    public static final int CIRCLE_TOPIC = 2;
    public static final int MINI_PROGRAM_THUMB_LENGHT = 131072;
    public static final int QIUSHI_TOPIC = 5;
    public static final int QSJX = 4;
    public static final int WEB = 1;
    private IWXAPI a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g = 0;
    private ShareMsgItem h;
    private boolean i = false;
    private boolean j = false;
    private boolean k;
    private int l;
    private Qsjx m;
    private QiushiTopic n;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        this.b = getIntent().getStringExtra("content");
        this.d = getIntent().getStringExtra("articleId");
        this.c = getIntent().getStringExtra("image");
        this.e = getIntent().getStringExtra("url");
        this.l = getIntent().getIntExtra("where", 8);
        this.g = getIntent().getIntExtra("type", 0);
        this.m = (Qsjx) getIntent().getSerializableExtra(QYQShareInfo.TYPE_QSJX);
        this.n = (QiushiTopic) getIntent().getSerializableExtra(SplashAdItem.AD_QIUSHI_TOPIC);
        this.f = getIntent().getStringExtra("description");
        this.k = getIntent().getBooleanExtra("is_share_with_mini_program", false);
        if (this.g == 2) {
            if (!TextUtils.isEmpty(this.c)) {
                this.i = true;
            }
            if (this.l == 4) {
                b();
            } else if (this.l == 8) {
                c();
            } else {
                setContentView(R.layout.weixinshare);
                a();
            }
        } else if (this.g != 4 && this.g != 5) {
            if (this.g == 1) {
                this.h = (ShareMsgItem) getIntent().getSerializableExtra("share_item");
            }
            if (!TextUtils.isEmpty(this.e)) {
                this.j = true;
            } else if (!TextUtils.isEmpty(this.c)) {
                this.i = true;
            }
            if (this.l == 4) {
                b();
            } else if (this.l == 8) {
                c();
            } else {
                setContentView(R.layout.weixinshare);
                a();
            }
        } else if (this.l == 4) {
            b();
        } else if (this.l == 8) {
            c();
        } else {
            setContentView(R.layout.weixinshare);
            a();
        }
    }

    private void a() {
        findViewById(R.id.cancle).setOnClickListener(this);
        findViewById(R.id.share2firend).setOnClickListener(this);
        findViewById(R.id.share2firends).setOnClickListener(this);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        finish();
        return true;
    }

    private void b() {
        if (this.g == 2) {
            if (this.i) {
                b(0);
            } else {
                d(0);
            }
        } else if (this.g == 4) {
            a(0, this.m.getShareTitle(), this.m.detail, this.m.picUrl, this.m.link);
        } else if (this.g == 5) {
            a(0, this.n.content, "", this.n.icon, this.n.getWebUrl());
        } else {
            StatService.onEvent(this, "weixin_1", "pass", 1);
            if (this.g == 1) {
                c(0);
            } else if (this.k) {
                shareWithMiniProgram(0);
            } else if (this.j) {
                a(0);
            } else if (this.i) {
                b(0);
            } else {
                d(0);
            }
        }
        finish();
    }

    private void c() {
        StatService.onEvent(this, "weixin_2", "pass", 1);
        if (this.g == 2) {
            if (this.i) {
                b(1);
            } else {
                d(1);
            }
        } else if (this.g == 4) {
            a(1, this.m.getShareTitle(), this.m.detail, this.m.picUrl, this.m.link);
        } else if (this.g == 5) {
            a(1, this.n.content, "", this.n.icon, this.n.getWebUrl());
        } else if (this.g == 1) {
            c(1);
        } else if (this.j) {
            a(1);
        } else if (this.i) {
            b(1);
        } else {
            d(1);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share2firend:
                b();
                return;
            case R.id.share2firends:
                c();
                return;
            case R.id.cancle:
                finish();
                return;
            default:
                return;
        }
    }

    private void a(int i) {
        IMediaObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = this.e;
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXWebpageObject);
        if (this.l == 4) {
            wXMediaMessage.title = "天王盖地虎，小鸡炖蘑菇";
        } else {
            wXMediaMessage.title = this.b;
        }
        wXMediaMessage.description = this.b;
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(this.c), getApplicationContext()).subscribe(new a(this, wXMediaMessage, i), CallerThreadExecutor.getInstance());
    }

    private void b(int i) {
        String str;
        if (this.g == 2) {
            str = this.c;
        } else {
            str = QsbkApp.absoluteUrlOfMediumContentImage(this.d, this.c);
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        if (this.g == 2) {
            wXMediaMessage.title = this.b;
        } else {
            wXMediaMessage.title = "天王盖地虎，小鸡炖蘑菇";
        }
        wXMediaMessage.description = TextUtils.isEmpty(this.f) ? this.b : this.f;
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        if (this.g == 2) {
            wXWebpageObject.webpageUrl = String.format(Constants.CIRCLE_TOPIC_TOUCH + "&source=wx", new Object[]{this.d});
        } else if (this.g == 3) {
            wXWebpageObject.webpageUrl = String.format(Constants.CIRCLE_ARTICLE_TOUCH + "&source=wx", new Object[]{this.d});
        } else {
            wXWebpageObject.webpageUrl = String.format("https://www.qiushibaike.com/share/%1$s?source=wx", new Object[]{this.d});
        }
        wXMediaMessage.mediaObject = wXWebpageObject;
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(str), getApplicationContext()).subscribe(new b(this, wXMediaMessage, i), CallerThreadExecutor.getInstance());
    }

    private void c(int i) {
        String str;
        if (TextUtils.isEmpty(this.h.imageUrl)) {
            str = ShareUtils.defaultIconUrl;
        } else {
            str = this.h.imageUrl;
        }
        IMediaObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = this.h.link;
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXWebpageObject);
        if (this.h.shareFor != 2) {
            String str2 = this.h.title;
            if (TextUtils.isEmpty(str2)) {
                str2 = this.h.content;
            }
            String str3 = this.h.content;
            if (TextUtils.isEmpty(str3)) {
                str3 = str2;
            }
            wXMediaMessage.title = str2;
            wXMediaMessage.description = str3;
        } else {
            wXMediaMessage.title = this.h.content;
        }
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(str), getApplicationContext()).subscribe(new c(this, wXMediaMessage, i), CallerThreadExecutor.getInstance());
    }

    private void a(int i, String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str3)) {
            str3 = ShareUtils.defaultIconUrl;
        }
        IMediaObject wXWebpageObject = new WXWebpageObject();
        if (TextUtils.isEmpty(str4)) {
            str4 = "http://qiushibaike.com";
        }
        wXWebpageObject.webpageUrl = str4;
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXWebpageObject);
        if (TextUtils.isEmpty(str)) {
            str = str2;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = str;
        }
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(str3), getApplicationContext()).subscribe(new d(this, wXMediaMessage, i), CallerThreadExecutor.getInstance());
    }

    private void d(int i) {
        String str = ShareUtils.defaultIconUrl;
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        if (this.g == 2) {
            wXWebpageObject.webpageUrl = String.format(Constants.CIRCLE_TOPIC_TOUCH + "&source=wx", new Object[]{this.d});
        } else if (this.g == 3) {
            wXWebpageObject.webpageUrl = String.format(Constants.CIRCLE_ARTICLE_TOUCH + "&source=wx", new Object[]{this.d});
        } else {
            wXWebpageObject.webpageUrl = String.format("https://www.qiushibaike.com/share/%1$s?source=wx", new Object[]{this.d});
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXWebpageObject);
        if (this.g == 2) {
            wXMediaMessage.title = this.b;
            wXMediaMessage.description = TextUtils.isEmpty(this.f) ? this.b : this.f;
        } else {
            if (this.l == 4) {
                wXMediaMessage.title = "天王盖地虎，小鸡炖蘑菇";
            } else {
                wXMediaMessage.title = this.b;
            }
            wXMediaMessage.description = this.b;
        }
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(ShareUtils.defaultIconUrl), getApplicationContext()).subscribe(new e(this, wXMediaMessage, i), CallerThreadExecutor.getInstance());
    }

    public void shareWithMiniProgram(int i) {
        String format;
        Object obj = this.c;
        if (TextUtils.isEmpty(obj) && TextUtils.isEmpty(this.b)) {
            obj = ShareUtils.defaultIconUrl;
        }
        String str = "";
        if (this.g == 2) {
            format = String.format(Constants.CIRCLE_TOPIC_TOUCH + "&source=wx", new Object[]{this.d});
        } else if (this.g == 3) {
            format = String.format(Constants.CIRCLE_ARTICLE_TOUCH + "&source=wx", new Object[]{this.d});
        } else {
            format = String.format("https://www.qiushibaike.com/share/%1$s?source=wx", new Object[]{this.d});
            str = String.format("pages/qsDetail/qsDetail?id=%s", new Object[]{this.d});
        }
        IMediaObject wXMiniProgramObject = new WXMiniProgramObject();
        wXMiniProgramObject.webpageUrl = format;
        wXMiniProgramObject.userName = Constants.MINIPROGRAM_ID;
        wXMiniProgramObject.path = str;
        wXMiniProgramObject.withShareTicket = true;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXMiniProgramObject;
        if (this.g == 2) {
            wXMediaMessage.title = this.b;
        } else {
            wXMediaMessage.title = "天王盖地虎，小鸡炖蘑菇";
        }
        wXMediaMessage.description = TextUtils.isEmpty(this.f) ? this.b : this.f;
        if (TextUtils.isEmpty(obj)) {
            int sqrt = ((int) Math.sqrt(65536.0d)) - 1;
            Bitmap textAsBitmap = Util.textAsBitmap(this.b, (float) (sqrt / 10), Color.parseColor("#3b3d42"), -1, sqrt, sqrt);
            if (textAsBitmap != null) {
                wXMediaMessage.thumbData = Util.bitmap2ByteArray(textAsBitmap);
            }
            BaseReq req = new Req();
            req.transaction = System.currentTimeMillis() + "";
            req.message = wXMediaMessage;
            req.scene = i;
            this.a.sendReq(req);
            finish();
            return;
        }
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(obj), getApplicationContext()).subscribe(new f(this, wXMediaMessage, i), CallerThreadExecutor.getInstance());
    }
}
