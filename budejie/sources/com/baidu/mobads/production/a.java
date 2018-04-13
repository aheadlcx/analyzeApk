package com.baidu.mobads.production;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.alipay.sdk.util.e;
import com.baidu.mobads.g.g;
import com.baidu.mobads.g.q;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdContainerContext;
import com.baidu.mobads.interfaces.IXAdContainerFactory;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdInstanceInfo$CreativeType;
import com.baidu.mobads.interfaces.IXAdProdInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.download.activate.IXActivateListener;
import com.baidu.mobads.interfaces.download.activate.IXAppInfo;
import com.baidu.mobads.interfaces.error.XAdErrorCode;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.openad.c.c;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobads.utils.h;
import com.baidu.mobads.utils.l;
import com.baidu.mobads.utils.u;
import com.baidu.mobads.vo.XAdInstanceInfo;
import com.baidu.mobads.vo.d;
import com.umeng.update.UpdateConfig;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class a extends c implements IXNonLinearAdSlot {
    public static IXAdContainerFactory a;
    private static final String[] w = new String[]{"android.permission.READ_PHONE_STATE", "android.permission.ACCESS_COARSE_LOCATION", UpdateConfig.f};
    private Runnable A = null;
    private IOAdEventListener B = new b(this);
    protected Boolean b = Boolean.valueOf(false);
    public IXAdInstanceInfo d = null;
    protected RelativeLayout e;
    protected Context f;
    protected int g = 0;
    public IXAdContainer h;
    protected String i;
    protected u j;
    protected d k;
    protected SlotState l = SlotState.IDEL;
    protected int m = 5000;
    protected int n = 0;
    protected SlotType o;
    protected HashMap<String, String> p = new HashMap();
    protected AtomicBoolean q = new AtomicBoolean();
    protected String r = "";
    protected final IXAdLogger s = XAdSDKFoundationFacade.getInstance().getAdLogger();
    protected long t;
    protected long u;
    protected long v;
    private IXAdResponseInfo x;
    private String y;
    private Handler z = new Handler(Looper.getMainLooper());

    private static class a implements IXActivateListener {
        private Context a;

        public a(Context context) {
            this.a = context.getApplicationContext();
        }

        public void onAppActivation(IXAppInfo iXAppInfo) {
            com.baidu.mobads.c.a.a().b(this.a, iXAppInfo);
        }

        public void onAppInstalled(IXAppInfo iXAppInfo) {
            com.baidu.mobads.c.a.a().a(this.a, iXAppInfo);
        }
    }

    protected abstract void a(com.baidu.mobads.openad.d.c cVar, u uVar, int i);

    protected abstract void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap);

    public abstract void c(IXAdResponseInfo iXAdResponseInfo);

    protected abstract void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap);

    public abstract void g();

    protected abstract void h();

    private void q() {
        String str = "XAdMouldeLoader ad-server requesting success";
        com.baidu.mobads.a.a.n = System.currentTimeMillis();
        a(this.x);
        if (d()) {
            IXAdInstanceInfo primaryAdInstanceInfo = this.x.getPrimaryAdInstanceInfo();
            String b = b(primaryAdInstanceInfo);
            if (TextUtils.isEmpty(b)) {
                b(str);
                return;
            }
            boolean a = a(b, primaryAdInstanceInfo);
            a(a, primaryAdInstanceInfo);
            if (a) {
                if (e() && TextUtils.isEmpty(((XAdInstanceInfo) this.d).getSplash3DLocalUrl())) {
                    this.s.i("XAbstractAdProdTemplate", "背景图片没有缓存完成");
                    return;
                } else {
                    b("download the splash picture successfully");
                    return;
                }
            } else if (a(primaryAdInstanceInfo)) {
                d(this.x);
                return;
            } else {
                if (!e(primaryAdInstanceInfo)) {
                    b(str);
                }
                d(this.x);
                return;
            }
        }
        b(str);
    }

    boolean a(String str, IXAdInstanceInfo iXAdInstanceInfo) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            String b = h.b(getApplicationContext(), str);
            if (!new File(b).exists()) {
                return false;
            }
            XAdSDKFoundationFacade.getInstance().getAdCreativeCacheManager().c(b);
            if (e()) {
                b = h.a(getApplicationContext(), iXAdInstanceInfo.getMainPictureUrl());
                File file = new File(b);
                if (file == null || !file.exists()) {
                    return false;
                }
                XAdSDKFoundationFacade.getInstance().getAdCreativeCacheManager().c(b);
                c(iXAdInstanceInfo, h.a(getApplicationContext(), iXAdInstanceInfo.getMainPictureUrl()));
                return true;
            }
            iXAdInstanceInfo.setLocalCreativeURL(b);
            return true;
        } catch (Throwable e) {
            l.a().e(e);
            return false;
        }
    }

    protected void a() {
    }

    public a(Context context) {
        new Thread(new f(this, context)).start();
    }

    private void b(Context context) {
        new Handler(Looper.getMainLooper()).postDelayed(new g(this, context), 2000);
    }

    public SlotState getSlotState() {
        return this.l;
    }

    public void setActivity(Context context) {
        this.f = context;
        c();
        this.q.set(false);
        h();
        com.baidu.mobads.c.a.a().a(getApplicationContext());
        XAdSDKFoundationFacade.getInstance().initializeApplicationContext(getApplicationContext());
        this.A = new i(this);
        q.a(this.f).a();
    }

    public void b() {
    }

    public void setAdSlotBase(RelativeLayout relativeLayout) {
        this.e = relativeLayout;
    }

    public void setId(String str) {
        this.y = str;
    }

    protected void c() {
    }

    public String getId() {
        return this.y;
    }

    public SlotType getType() {
        return this.o;
    }

    protected void a(XAdErrorCode xAdErrorCode, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("msg", xAdErrorCode);
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_ERROR, hashMap));
        XAdSDKFoundationFacade.getInstance().getErrorCode().printErrorMessage(xAdErrorCode, str);
    }

    protected void a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("error_message", str);
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_ERROR, hashMap));
        XAdSDKFoundationFacade.getInstance().getErrorCode().printErrorMessage("", str, "");
    }

    protected void a(Context context) {
        if (BaiduXAdSDKContext.mApkLoader == null) {
            synchronized (g.class) {
                if (BaiduXAdSDKContext.mApkLoader == null) {
                    BaiduXAdSDKContext.mApkLoader = new g(context.getApplicationContext());
                }
            }
        }
        if (a != null) {
            r();
        } else {
            BaiduXAdSDKContext.mApkLoader.a(new j(this));
        }
    }

    private void r() {
        BaiduXAdSDKContext.isRemoteLoadSuccess = Boolean.valueOf(true);
        c("XAdMouldeLoader load success");
    }

    protected void b(String str) {
        this.b = Boolean.valueOf(true);
        c(str);
    }

    protected void c(String str) {
        this.s.i("XAbstractAdProdTemplate", "doubleCheck:" + str + ", bfp=" + this.b + ", apk=" + BaiduXAdSDKContext.isRemoteLoadSuccess);
        if (BaiduXAdSDKContext.isRemoteLoadSuccess.booleanValue()) {
            XAdSDKFoundationFacade.getInstance().initializeAdContainerFactory(getAdContainerFactory());
        }
        if (BaiduXAdSDKContext.isRemoteLoadSuccess.booleanValue() && this.b.booleanValue()) {
            IXAdResponseInfo adResponseInfo = getAdResponseInfo();
            if (adResponseInfo != null) {
                try {
                    b(adResponseInfo);
                    return;
                } catch (Throwable e) {
                    this.s.i("XAbstractAdProdTemplate", e);
                    return;
                }
            }
            this.s.i("XAbstractAdProdTemplate", "doubleCheck IXAdResponseInfo is null, but isBFP4APPRequestSuccess is true");
        }
    }

    public boolean d() {
        return false;
    }

    public boolean a(IXAdInstanceInfo iXAdInstanceInfo) {
        return false;
    }

    public String b(IXAdInstanceInfo iXAdInstanceInfo) {
        String str = "";
        if (iXAdInstanceInfo == null) {
            return str;
        }
        if (iXAdInstanceInfo.getCreativeType() == IXAdInstanceInfo$CreativeType.VIDEO) {
            return iXAdInstanceInfo.getVideoUrl();
        }
        if (iXAdInstanceInfo.getCreativeType() == IXAdInstanceInfo$CreativeType.RM) {
            return iXAdInstanceInfo.getMainPictureUrl();
        }
        return str;
    }

    public boolean c(IXAdInstanceInfo iXAdInstanceInfo) {
        return iXAdInstanceInfo.getCreativeType() == IXAdInstanceInfo$CreativeType.STATIC_IMAGE || iXAdInstanceInfo.getCreativeType() == IXAdInstanceInfo$CreativeType.GIF;
    }

    public boolean e() {
        if (TextUtils.isEmpty(this.r)) {
            return false;
        }
        return this.r.endsWith("3d");
    }

    public boolean d(IXAdInstanceInfo iXAdInstanceInfo) {
        return (iXAdInstanceInfo.getCreativeType() == IXAdInstanceInfo$CreativeType.RM && this.r.endsWith("vr")) || iXAdInstanceInfo.getCreativeType() == IXAdInstanceInfo$CreativeType.VIDEO;
    }

    private boolean a(IXAdInstanceInfo iXAdInstanceInfo, String str) {
        if (XAdSDKFoundationFacade.getInstance().getSystemUtils().isWifiConnected(getApplicationContext()).booleanValue() || c(iXAdInstanceInfo) || e()) {
            return true;
        }
        return false;
    }

    private void b(IXAdInstanceInfo iXAdInstanceInfo, String str) {
        if (d(iXAdInstanceInfo)) {
            com.baidu.mobads.c.a.a().a(this.f, "383", iXAdInstanceInfo, this.k.d(), "file_dl_failed_not_wifi", str);
        }
    }

    public boolean e(IXAdInstanceInfo iXAdInstanceInfo) {
        return false;
    }

    private void d(IXAdResponseInfo iXAdResponseInfo) {
        this.s.i("XAbstractAdProdTemplate", "cacheCreativeAsset");
        IXAdInstanceInfo primaryAdInstanceInfo = iXAdResponseInfo.getPrimaryAdInstanceInfo();
        String b = b(primaryAdInstanceInfo);
        if (!TextUtils.isEmpty(b)) {
            if (a(primaryAdInstanceInfo, b)) {
                primaryAdInstanceInfo.setLocalCreativeURL(null);
                String a = h.a(getApplicationContext());
                String b2 = h.b(b);
                h adCreativeCacheManager = XAdSDKFoundationFacade.getInstance().getAdCreativeCacheManager();
                adCreativeCacheManager.a(a);
                adCreativeCacheManager.a(getApplicationContext(), b, a, b2, new k(this, Looper.getMainLooper(), primaryAdInstanceInfo));
                return;
            }
            b(primaryAdInstanceInfo, b);
        }
    }

    private void c(IXAdInstanceInfo iXAdInstanceInfo, String str) {
        File[] listFiles = new File(str).listFiles();
        String[] strArr = new String[]{"jpg", "png", "jpeg"};
        if (listFiles != null && listFiles.length > 0) {
            for (int i = 0; i < listFiles.length; i++) {
                File[] listFiles2 = listFiles[i].listFiles();
                if (listFiles2 != null && listFiles2.length > 0) {
                    for (int i2 = 0; i2 < listFiles2.length; i2++) {
                        if (listFiles2[i2].getName().endsWith(".mp4")) {
                            iXAdInstanceInfo.setLocalCreativeURL(listFiles[i].getAbsolutePath());
                            return;
                        }
                        for (String endsWith : strArr) {
                            if (listFiles2[i2].getName().toLowerCase().endsWith(endsWith)) {
                                iXAdInstanceInfo.setLocalCreativeURL(listFiles[i].getAbsolutePath());
                                return;
                            }
                        }
                    }
                    continue;
                }
            }
        }
    }

    private void a(Message message, IXAdInstanceInfo iXAdInstanceInfo) {
        if (message.getData().getBoolean("caching_result")) {
            String string = message.getData().getString("local_creative_url");
            iXAdInstanceInfo.setLocalCreativeURL(string);
            if (iXAdInstanceInfo.getCreativeType() == IXAdInstanceInfo$CreativeType.RM) {
                if (e()) {
                    Log.e("XAbstractAdProdTemplate", "processDlResult: " + string);
                    try {
                        u.a(string, h.a(getApplicationContext(), iXAdInstanceInfo.getMainPictureUrl()));
                        c(iXAdInstanceInfo, h.a(getApplicationContext(), iXAdInstanceInfo.getMainPictureUrl()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (this.r.endsWith("vr")) {
                    a(XAdSDKFoundationFacade.getInstance().getCommonUtils().md5(iXAdInstanceInfo.getMainPictureUrl()), Uri.parse(string));
                }
            }
            if (e(iXAdInstanceInfo)) {
                if (e() && TextUtils.isEmpty(((XAdInstanceInfo) this.d).getSplash3DLocalUrl())) {
                    this.s.i("XAbstractAdProdTemplate", "背景图片没有缓存完成");
                } else {
                    b("download the splash picture successfully");
                }
            }
        } else {
            iXAdInstanceInfo.setLocalCreativeURL(null);
            if (e(iXAdInstanceInfo)) {
                b("download the splash picture successfully");
            }
        }
        b(message, iXAdInstanceInfo);
    }

    private void b(Message message, IXAdInstanceInfo iXAdInstanceInfo) {
        if (d(iXAdInstanceInfo)) {
            String str = "" + message.getData().getLong("caching_time_consume", 0);
            String b = b(iXAdInstanceInfo);
            String str2 = message.getData().getBoolean("caching_result") ? "success" : e.b;
            com.baidu.mobads.c.a.a().a(this.f, "383", iXAdInstanceInfo, this.k.d(), "file_dl_" + str2, b, str);
        }
    }

    private void a(String str, Uri uri) {
        new Thread(new l(this, str, uri)).start();
    }

    public void a(boolean z, IXAdInstanceInfo iXAdInstanceInfo) {
    }

    protected boolean a(d dVar) {
        this.s.i("XAbstractAdProdTemplate", "doRequest()");
        new Thread(new m(this, dVar)).start();
        return true;
    }

    protected void b(d dVar) {
        this.k = dVar;
        k();
        this.b = Boolean.valueOf(false);
        String b = this.i == null ? dVar.b() : this.i;
        this.j = new u();
        com.baidu.mobads.c.a.b = b;
        com.baidu.mobads.openad.d.c cVar = new com.baidu.mobads.openad.d.c(b, "");
        cVar.e = 1;
        this.j.addEventListener("URLLoader.Load.Complete", this.B);
        this.j.addEventListener("URLLoader.Load.Error", this.B);
        a(cVar, this.j, this.m);
    }

    protected void a(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        l();
        c(iXAdContainer, (HashMap) hashMap);
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_LOADED));
    }

    protected void b(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        this.l = SlotState.PLAYING;
        d(iXAdContainer, hashMap);
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_STARTED));
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
    }

    protected void a(IXAdResponseInfo iXAdResponseInfo) {
        c(iXAdResponseInfo);
    }

    protected void d(String str) {
        com.baidu.mobads.c.a.a().a(str);
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_ERROR));
    }

    protected void b(IXAdResponseInfo iXAdResponseInfo) {
        this.s.i("XAbstractAdProdTemplate", "handleAllReady");
        this.g++;
        this.d = iXAdResponseInfo.getPrimaryAdInstanceInfo();
        Context applicationContext = getApplicationContext();
        IXAdContainerContext oVar = new o(applicationContext, getActivity(), this.k.d(), this.e, new p(applicationContext, this), iXAdResponseInfo, null);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            a(oVar);
        } else {
            new Handler(Looper.getMainLooper()).post(new n(this, oVar));
        }
    }

    protected void a(IXAdResponseInfo iXAdResponseInfo, IXAdInstanceInfo iXAdInstanceInfo) {
        this.d = iXAdInstanceInfo;
    }

    public Context getApplicationContext() {
        Activity activity = getActivity();
        return activity == null ? this.f : activity.getApplicationContext();
    }

    public Activity getActivity() {
        if (this.f instanceof Activity) {
            return (Activity) this.f;
        }
        if (this.e == null || !(this.e.getContext() instanceof Activity)) {
            return null;
        }
        return (Activity) this.e.getContext();
    }

    public IXAdContainer getCurrentXAdContainer() {
        return this.h;
    }

    public IXAdContainerFactory getAdContainerFactory() {
        return a;
    }

    public static IXAdContainerFactory f() {
        return a;
    }

    protected void a(IXAdContainerContext iXAdContainerContext) {
        try {
            this.s.i("XAbstractAdProdTemplate", "processAllReadyOnUIThread()");
            this.u = System.currentTimeMillis();
            this.h = b(iXAdContainerContext);
            this.v = System.currentTimeMillis();
            if (this.h == null) {
                this.s.e(new Object[]{"XAbstractAdProdTemplate", "processAllReadyOnUIThread(), mAdContainer is null"});
                dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_STOPPED));
                return;
            }
            this.s.i("XAbstractAdProdTemplate", "processAllReadyOnUIThread(), mAdContainer be created");
            this.p.put("start", "" + this.t);
            this.p.put("container_before_created", "" + this.u);
            this.p.put("container_after_created", "" + this.v);
            this.h.setParameters(this.p);
            com.baidu.mobads.a.a.c = this.h.getRemoteVersion();
            this.s.i("XAbstractAdProdTemplate", "processAllReadyOnUIThread(), mAdContainer be created, hasCalledLoadAtAppSide=" + this.q.get());
            if (this.q.get()) {
                this.h.load();
            }
            g();
            b(this.f);
        } catch (Exception e) {
            e.printStackTrace();
            this.s.e(XAdSDKFoundationFacade.getInstance().getErrorCode().genCompleteErrorMessage(XAdErrorCode.PERMISSION_PROBLEM, e.getMessage()));
            com.baidu.mobads.c.a.a().a("process all ready on UI Thread exception: " + e.toString());
        }
    }

    private IXAdContainer b(IXAdContainerContext iXAdContainerContext) {
        IXAdContainer iXAdContainer = null;
        this.s.i("XAbstractAdProdTemplate", "createAdContainer");
        if (a != null) {
            iXAdContainer = a.createXAdContainer(iXAdContainerContext, null);
            if (iXAdContainer != null) {
                this.s.i("XAbstractAdProdTemplate", "createAdContainer() apk.version=" + a.getRemoteVersion());
            }
        }
        return iXAdContainer;
    }

    public IXAdProdInfo getProdInfo() {
        return this.k.d();
    }

    public void setParameter(HashMap<String, String> hashMap) {
        this.p = hashMap;
    }

    public HashMap<String, String> getParameter() {
        return this.p;
    }

    public int getDuration() {
        return -1;
    }

    public int getPlayheadTime() {
        return -1;
    }

    public IXAdInstanceInfo getCurrentAdInstance() {
        return this.d;
    }

    public ViewGroup getProdBase() {
        return this.e;
    }

    public void load() {
        if (this.h != null) {
            this.h.load();
        } else {
            this.q.set(true);
        }
    }

    public void resize() {
        if (this.h != null && getApplicationContext() != null) {
            new Handler(getApplicationContext().getMainLooper()).post(new c(this));
        }
    }

    public void pause() {
        i();
    }

    protected void i() {
        if (this.h != null && getApplicationContext() != null) {
            this.l = SlotState.PAUSED;
            new Handler(getApplicationContext().getMainLooper()).post(new d(this));
        }
    }

    public void start() {
        if (this.h != null) {
            this.h.start();
        }
    }

    public void resume() {
        j();
    }

    protected void j() {
        if (this.h != null && getApplicationContext() != null) {
            this.l = SlotState.PLAYING;
            new Handler(getApplicationContext().getMainLooper()).post(new e(this));
        }
    }

    public void stop() {
        XAdSDKFoundationFacade.getInstance().getAdLogger().i("XAbstractAdProdTemplate", "stop");
        if (this.h != null) {
            this.h.stop();
            this.h = null;
        }
    }

    protected void k() {
        if (this.j != null) {
            this.j.removeAllListeners();
            this.j.a();
        }
    }

    protected void l() {
        if (this.A != null) {
            this.z.removeCallbacks(this.A);
        }
        this.A = null;
    }

    protected void m() {
        if (this.A != null) {
            this.z.postDelayed(this.A, (long) this.m);
        }
    }

    public void n() {
        if (this.h != null) {
            this.h.onAttachedToWindow();
        }
    }

    @SuppressLint({"MissingSuperCall"})
    public void o() {
        if (this.h != null) {
            this.h.onDetachedFromWindow();
        }
    }

    public void a(int i) {
        if (this.h != null) {
            this.h.onWindowVisibilityChanged(i);
        }
    }

    public void a(boolean z) {
        if (this.h != null) {
            this.h.onWindowFocusChanged(z);
        }
    }

    public boolean a(int i, KeyEvent keyEvent) {
        if (this.h != null) {
            return this.h.processKeyEvent(i, keyEvent).booleanValue();
        }
        return false;
    }

    protected void e(String str) {
        if (TextUtils.isEmpty(str)) {
            this.s.e("代码位id(adPlaceId)不可以为空");
        }
    }

    public void p() {
        if (this.h != null) {
            this.h.destroy();
        }
        BaiduXAdSDKContext.exit();
    }

    public IXAdResponseInfo getAdResponseInfo() {
        return this.x;
    }

    public void setAdResponseInfo(IXAdResponseInfo iXAdResponseInfo) {
        this.x = iXAdResponseInfo;
    }

    public Boolean isAdServerRequestingSuccess() {
        return this.b;
    }
}
