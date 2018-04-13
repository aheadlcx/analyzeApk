package qsbk.app.live.share;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Window;
import android.widget.GridView;
import android.widget.Toast;
import com.alipay.sdk.sys.a;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import com.qiushibaike.statsdk.StatSDK;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrPosition;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage.IMediaObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.util.ArrayList;
import java.util.Random;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.publish.CirclePublishActivity;
import qsbk.app.core.adapter.ShareAdapter;
import qsbk.app.core.adapter.ShareAdapter.OnShareItemClickListener;
import qsbk.app.core.model.CommonVideo;
import qsbk.app.core.model.Share;
import qsbk.app.core.model.ShareItem;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.core.web.plugin.embed.SharePlugin;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.fragments.ShareToImType;
import qsbk.app.live.ui.share.ShareCallbackHelper;
import qsbk.app.model.EventWindow;
import qsbk.app.model.ShareMsgItem;
import qsbk.app.share.AuthorizeActivity;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.ShareUtils$SinaRequestLinstener;
import qsbk.app.share.WeiboShareActivity;
import qsbk.app.share.message.ShareToIMMessageActivity;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.ToastAndDialog;

public class LiveShareActivity extends Activity implements OnShareItemClickListener {
    public static final int SHARE_NO_LOGIN = 799;
    public static final String WX_STATE = "qiubai_share";
    private Handler a;
    protected ArrayList<ShareItem> b;
    protected CommonVideo c;
    protected Share d;
    protected Bitmap e;
    protected Tencent f;
    protected IWXAPI g;
    protected String h;
    private GridView i;
    private ShareAdapter j;
    private int k;
    private String l;
    private ShareUtils$SinaRequestLinstener m;
    private String n;
    private User o;
    private BroadcastReceiver p = new c(this);
    private BroadcastReceiver q = new g(this);
    private IUiListener r = new h(this);

    public interface ShareType {
        public static final int SHARE_CHAT_MSG = 9;
        public static final int SHARE_CIRCLE = 8;
        public static final int SHARE_COPY = 6;
        public static final int SHARE_MORE = 7;
        public static final int SHARE_QQ = 4;
        public static final int SHARE_QZONE = 5;
        public static final int SHARE_SINA = 3;
        public static final int SHARE_WECHAT = 1;
        public static final int SHARE_WECHAT_TIMELINE = 2;
    }

    public static int getShareItemMore() {
        return R.drawable.ic_share_more;
    }

    public static int getShareItemWechatTimeline() {
        return R.drawable.ic_share_wechat_timeline;
    }

    public static int getShareItemWechat() {
        return R.drawable.ic_share_wechat;
    }

    public static int getShareItemQQ() {
        return R.drawable.ic_share_qq;
    }

    public static int getShareItemSina() {
        return R.drawable.ic_share_sina_weibo;
    }

    public static int getShareItemQzone() {
        return R.drawable.ic_share_qzone;
    }

    public static int getShareItemCopy() {
        return R.drawable.ic_share_copy;
    }

    public static int getShareItemChatMsg() {
        return R.drawable.ic_share_chat_msg;
    }

    public static int getShareItemCircle() {
        return R.drawable.ic_share_circle;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(a());
        Slidr.attach(this, AppUtils.getSlidrConfigBuilder().position(SlidrPosition.TOP).sensitivity(1.0f).scrimColor(0).build());
        this.a = new Handler();
        a(getWindow());
        b();
        c();
        this.o = AppUtils.getInstance().getUserInfoProvider().getUser();
        if (this.o == null) {
            this.o = new User();
        }
        registerReceiver(this.p, new IntentFilter(WX_STATE));
        registerReceiver(this.q, new IntentFilter(WeiboShareActivity.WEIBO_SHARE));
    }

    protected void onDestroy() {
        try {
            unregisterReceiver(this.p);
            unregisterReceiver(this.q);
        } catch (Throwable th) {
        }
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(this.n)) {
            finish();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == SHARE_NO_LOGIN && i2 == -1) {
            setResult(-1);
            finish();
        }
        if (this.f != null) {
            Tencent tencent = this.f;
            Tencent.onActivityResultData(i, i2, intent, this.r);
        }
        if (i == 2) {
            weiboShare(new ShareUtils());
        }
    }

    protected void a(Window window) {
        if (VERSION.SDK_INT >= 21) {
            window.clearFlags(67108864);
            window.getDecorView().setSystemUiVisibility(1280);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(getResources().getColor(R.color.transparent));
            return;
        }
        window.addFlags(67108864);
        SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
        systemBarTintManager.setStatusBarTintEnabled(true);
        systemBarTintManager.setStatusBarTintResource(R.color.transparent);
    }

    protected void a(String str) {
        a(str, null);
    }

    protected void a(String str, Runnable runnable) {
        if (this.e == null || this.e.isRecycled()) {
            Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(str), getApplicationContext()).subscribe(new i(this, runnable), CallerThreadExecutor.getInstance());
            if (this.e == null) {
                this.e = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            }
        }
    }

    protected int a() {
        return R.layout.activity_share;
    }

    protected void b() {
        this.i = (GridView) findViewById(R.id.live_grid);
    }

    protected void c() {
        Intent intent = getIntent();
        if (intent != null) {
            this.c = (CommonVideo) intent.getSerializableExtra("video");
            this.k = intent.getIntExtra("sns", 0);
            this.h = intent.getStringExtra("from");
        }
        if (this.c == null || this.c.share == null) {
            finish();
            return;
        }
        this.d = this.c.share;
        m();
        l();
        if (this.k <= 0) {
            d();
            this.j = new ShareAdapter(this, this.b, true, this);
            this.i.setAdapter(this.j);
            findViewById(R.id.container).setOnClickListener(new j(this));
            return;
        }
        this.a.postDelayed(new k(this), 500);
    }

    private void l() {
        if (!"web".equals(this.h) && this.c != null) {
            NetRequest.getInstance().get(UrlConstants.GET_SHARE, new l(this), "getShare", true);
        }
    }

    private void m() {
        this.a.post(new n(this));
    }

    protected void d() {
        this.b = new ArrayList();
        this.b.add(new ShareItem(getShareItemWechatTimeline(), getString(R.string.share_friend_circle), 2));
        this.b.add(new ShareItem(getShareItemWechat(), getString(R.string.share_wechat_friend), 1));
        this.b.add(new ShareItem(getShareItemQQ(), getString(R.string.share_qq_friend), 4));
        this.b.add(new ShareItem(getShareItemQzone(), getString(R.string.share_qq_zone), 5));
        this.b.add(new ShareItem(getShareItemSina(), getString(R.string.share_sina_weibo), 3));
        this.b.add(new ShareItem(getShareItemChatMsg(), getString(R.string.share_chat_msg), 9));
        this.b.add(new ShareItem(getShareItemCircle(), getString(R.string.share_circle), 8));
        this.b.add(new ShareItem(getShareItemCopy(), getString(R.string.share_copy_link), 6));
    }

    public void onShareItemClicked(int i) {
        doShareItemClicked(((ShareItem) this.b.get(i)).resultCode);
    }

    public void doShareItemClicked(int i) {
        switch (i) {
            case 1:
                shareToWechat();
                return;
            case 2:
                shareToWechatTimeline();
                return;
            case 3:
                shareToSina();
                return;
            case 4:
                shareToQQ();
                return;
            case 5:
                shareToQzone();
                return;
            case 6:
                o();
                return;
            case 7:
                n();
                return;
            case 8:
                shareToCircle();
                return;
            case 9:
                shareToChatMessage();
                return;
            default:
                return;
        }
    }

    private void n() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(HTTP.PLAIN_TEXT_TYPE);
        intent.putExtra("android.intent.extra.TEXT", d("more"));
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        startActivity(Intent.createChooser(intent, getString(R.string.share_to)));
        toReportShare("more");
    }

    private void o() {
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("data", d("copy")));
        ToastUtil.Short((int) R.string.share_copy_success);
        toReportShare("copy");
        finish();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.still_when_down, R.anim.roll_down);
    }

    public void toReportShare(String str) {
        this.n = str;
        b("click");
    }

    protected void b(String str) {
        StatSDK.onEvent(this, f(), e(), this.n, str, this.o.getOrigin() + "_" + this.o.getOriginId());
    }

    protected String e() {
        if ("web".equals(this.h)) {
            return this.c.share.url;
        }
        return this.c.isLiveVideo() ? this.c.stream_id : Long.toString(this.c.id);
    }

    protected String f() {
        if ("web".equals(this.h)) {
            return SharePlugin.ACTION_SHARE_WEB;
        }
        return this.c.isLiveVideo() ? "share_live" : "share_video";
    }

    private void p() {
        ToastUtil.Short("分享成功");
        if ("live".equals(this.h)) {
            ShareCallbackHelper.getInstance().notifyShareSuccess(this.n);
        }
        b("success");
    }

    public void shareToWechat() {
        a(0);
        toReportShare(ThirdPartyConstants.THIRDPARTY_TYLE_WX);
    }

    public void shareToWechatTimeline() {
        a(1);
        toReportShare("wxtl");
    }

    protected String c(String str) {
        String str2 = "";
        if (this.d == null || TextUtils.isEmpty(this.d.url)) {
            return str2;
        }
        str2 = this.d.url;
        if (str2.indexOf("?") > 0) {
            str2 = str2 + a.b;
        } else {
            str2 = str2 + "?";
        }
        return str2 + "mFrom=" + str;
    }

    protected String g() {
        return this.c != null ? Long.toString(this.c.author.getOriginId()) : "";
    }

    protected long h() {
        return (this.c == null || this.c.author == null) ? 0 : this.c.author.origin;
    }

    protected String i() {
        return this.d != null ? this.d.caption : "";
    }

    protected String j() {
        return this.d != null ? this.d.wb_info : "";
    }

    protected String k() {
        return "web".equals(this.h) ? this.d != null ? this.d.imageUrl : "" : TextUtils.isEmpty(this.c.pic_url) ? this.c.thumbnail_url : this.c.pic_url;
    }

    protected String d(String str) {
        String str2 = "";
        if (this.d == null) {
            return str2;
        }
        if (TextUtils.isEmpty(this.d.wb_info)) {
            this.d.wb_info = "传送门";
        }
        return this.d.caption + "【" + this.d.wb_info + "：" + c(str) + "】";
    }

    private void a(int i) {
        this.g = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        this.g.registerApp(Constants.APP_ID);
        if (this.g.isWXAppInstalled()) {
            IMediaObject wXWebpageObject;
            int i2;
            Bitmap b = b(120);
            if ("web".equals(this.h)) {
                wXWebpageObject = new WXWebpageObject();
                ((WXWebpageObject) wXWebpageObject).webpageUrl = c(i == 1 ? "wxtl" : ThirdPartyConstants.THIRDPARTY_TYLE_WX);
            } else if ("image".equals(this.h)) {
                wXWebpageObject = new WXImageObject(this.e);
            } else {
                wXWebpageObject = new WXVideoObject();
                ((WXVideoObject) wXWebpageObject).videoUrl = c(i == 1 ? "wxtl" : ThirdPartyConstants.THIRDPARTY_TYLE_WX);
            }
            WXMediaMessage wXMediaMessage = new WXMediaMessage(wXWebpageObject);
            String i3 = "web".equals(this.h) ? i() : i == 1 ? i() : getString(R.string.app_name);
            wXMediaMessage.title = i3;
            wXMediaMessage.description = "web".equals(this.h) ? j() : i();
            wXMediaMessage.setThumbImage(b != null ? b : this.e);
            BaseReq req = new Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = wXMediaMessage;
            if (i == 0) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            req.scene = i2;
            this.g.sendReq(req);
            return;
        }
        ToastUtil.Short(getString(R.string.pay_wechat_not_installed));
    }

    private Bitmap b(int i) {
        if (this.e == null || this.e.isRecycled()) {
            return null;
        }
        Bitmap createBitmap;
        int width = this.e.getWidth();
        int height = this.e.getHeight();
        if (height > width) {
            createBitmap = Bitmap.createBitmap(this.e, 0, (height - width) / 2, width, width);
        } else {
            createBitmap = Bitmap.createBitmap(this.e, (width - height) / 2, 0, height, height);
        }
        return Bitmap.createScaledBitmap(createBitmap, i, i, false);
    }

    public void shareToQQ() {
        this.f = Tencent.createInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, this);
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        bundle.putString("title", "web".equals(this.h) ? i() : getString(R.string.app_name));
        bundle.putString("summary", "web".equals(this.h) ? j() : i());
        bundle.putString("imageUrl", k());
        bundle.putString("targetUrl", c(ThirdPartyConstants.THIRDPARTY_TYLE_QQ));
        bundle.putString("appName", getString(R.string.app_name));
        this.f.shareToQQ(this, bundle, this.r);
        toReportShare(ThirdPartyConstants.THIRDPARTY_TYLE_QQ);
    }

    public void shareToQzone() {
        this.f = Tencent.createInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, this);
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        bundle.putString("title", "web".equals(this.h) ? i() : getString(R.string.app_name));
        bundle.putString("summary", "web".equals(this.h) ? j() : i());
        ArrayList arrayList = new ArrayList();
        arrayList.add(k());
        bundle.putStringArrayList("imageUrl", arrayList);
        bundle.putString("targetUrl", c(com.tencent.connect.common.Constants.SOURCE_QZONE));
        bundle.putString("appName", getString(R.string.app_name));
        this.f.shareToQzone(this, bundle, this.r);
        toReportShare(com.tencent.connect.common.Constants.SOURCE_QZONE);
    }

    public void shareImageToQQ() {
        new Thread(new o(this)).start();
    }

    public void shareImageToQQZone() {
        new Thread(new d(this)).start();
    }

    public void shareToSina() {
        ShareUtils shareUtils = new ShareUtils();
        Integer checkAccessToken = shareUtils.checkAccessToken(1);
        Intent intent = new Intent(this, AuthorizeActivity.class);
        intent.putExtra(QsbkDatabase.TARGET, "sina");
        switch (checkAccessToken.intValue()) {
            case 1:
                shareUtils.buidBindUrl(Integer.valueOf(1));
                startActivityForResult(intent, 2);
                return;
            case 2:
                shareUtils.buidBindUrl(Integer.valueOf(1));
                ToastAndDialog.makeNegativeToast(this, "绑定信息过期，请重新绑定", Integer.valueOf(0)).show();
                startActivityForResult(intent, 2);
                return;
            case 3:
                weiboShare(shareUtils);
                return;
            default:
                return;
        }
    }

    public void weiboShare(ShareUtils shareUtils) {
        if (!TextUtils.isEmpty(ShareUtils.getWeiboAccessTokenKey()) || ShareUtils.isWeiboInstall()) {
            ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "分享中...", Integer.valueOf(1)).show();
            shareUtils.getClass();
            this.m = new f(this, shareUtils);
            this.l = new Random().nextInt() + "";
            if (this.e == null) {
                WeiboShareActivity.shareToWeiBo(this.l, null, new StringBuffer(d(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)).toString(), null, null);
            } else {
                WeiboShareActivity.shareToWeiBo(this.l, null, new StringBuffer(d(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)).toString(), null, k());
            }
        } else {
            Toast.makeText(QsbkApp.mContext, "绑定信息出错，请重新绑定", 1).show();
        }
        toReportShare(ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
    }

    public ShareMsgItem getShareMsgItem() {
        ShareMsgItem shareMsgItem = new ShareMsgItem();
        shareMsgItem.imageUrl = k();
        shareMsgItem.link = g();
        shareMsgItem.content = this.c.author.name + "正在直播，颜值爆表~快来一起看！" + this.c.content;
        shareMsgItem.title = null;
        shareMsgItem.live_origin = (int) h();
        return shareMsgItem;
    }

    public void shareToCircle() {
        if (AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            String str = "live";
            if (TextUtils.equals(this.h, "web")) {
                str = "web";
            }
            if (str == "live") {
                CirclePublishActivity.launch((Context) this, new QYQShareInfo(getShareMsgItem(), str));
            } else if (str == "web") {
                CirclePublishActivity.launch((Context) this, new QYQShareInfo(getImShareMsgItem(), str));
            }
            toReportShare(SplashAdItem.TAB_CIRCLE);
            return;
        }
        AppUtils.getInstance().getUserInfoProvider().toLogin(this, SHARE_NO_LOGIN);
    }

    public void shareToChatMessage() {
        if (AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            Bundle bundle = new Bundle();
            bundle.putInt("share_type", ShareToImType.TYPE_LIVE.getValue());
            if (TextUtils.equals(this.h, "web")) {
                bundle.putInt("share_type", ShareToImType.TYPE_LIVE_ACTIVITY.getValue());
                bundle.putSerializable("share_item", getImShareMsgItem());
            }
            bundle.putSerializable(EventWindow.JUMP_LIVE_ROOM, this.c);
            Intent intent = new Intent(this, ShareToIMMessageActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1002);
            toReportShare("chatmsg");
            return;
        }
        AppUtils.getInstance().getUserInfoProvider().toLogin(this, SHARE_NO_LOGIN);
    }

    public ShareMsgItem getImShareMsgItem() {
        ShareMsgItem shareMsgItem = new ShareMsgItem();
        shareMsgItem.imageUrl = k();
        shareMsgItem.link = c("im");
        shareMsgItem.content = j();
        shareMsgItem.title = i();
        shareMsgItem.shareFor = 4;
        return shareMsgItem;
    }
}
