package qsbk.app.core.utils;

import android.app.NotificationManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.liulishuo.filedownloader.FileDownloader;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrConfig.Builder;
import com.r0adkll.slidr.model.SlidrPosition;
import qsbk.app.ConfigManager;
import qsbk.app.core.R;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.OkHttp.OkhttpNetwork;
import qsbk.app.core.net.OkHttp.OkhttpVolley;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.net.ssl.SSLCertificateValidation;
import qsbk.app.core.net.ssl.SSLUtils;
import qsbk.app.core.provider.ImageProvider;
import qsbk.app.core.provider.UserInfoProvider;
import qsbk.app.core.widget.DialogFragment;
import qsbk.app.core.widget.SimpleDialog;

public class AppUtils {
    private static final String a = AppUtils.class.getSimpleName();
    private static Gson b;
    private static AppUtils c;
    private static long d;
    private static boolean m = false;
    private Handler e;
    private Context f;
    private String g;
    private RequestQueue h;
    private UserInfoProvider i;
    private ImageProvider j;
    private boolean k = false;
    private boolean l = false;

    private AppUtils(Context context, int i, String str, String str2) {
        this.f = context.getApplicationContext();
        Constants.APP_FLAG = i;
        Constants.SOURCE = i;
        if (i == 66) {
            Constants.SOURCE = 2;
        }
        NetRequest.setLiveSalt(str);
        this.g = str2;
        this.e = new Handler();
    }

    public static void init(Context context, int i, String str, String str2) {
        if (c == null) {
            c = new AppUtils(context, i, str, str2);
        }
        UrlConstants.initAllDomains();
        FileDownloader.init(context);
    }

    public static AppUtils getInstance() {
        if (c != null) {
            return c;
        }
        throw new RuntimeException("You didn't call the init method");
    }

    public Context getAppContext() {
        return this.f;
    }

    public String getChannel() {
        return this.g;
    }

    public boolean isTestOnlyChannel() {
        return (this.g != null && this.g.endsWith("dev")) || ConfigManager.TEST_ONLY_CHANNEL.equals(this.g);
    }

    public void loadConfig() {
        this.e.postDelayed(new c(this), 1200);
    }

    public void loadConfigInfo() {
        ConfigInfoUtil.instance().updateConfigInfo();
    }

    public boolean isLowSpecificationDevice() {
        return m;
    }

    public static Gson getGson() {
        if (b == null) {
            ExclusionStrategy dVar = new d();
            b = new GsonBuilder().addSerializationExclusionStrategy(dVar).addDeserializationExclusionStrategy(dVar).create();
        }
        return b;
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        T t = null;
        try {
            t = getGson().fromJson(str, (Class) cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> T fromJson(String str, TypeToken<T> typeToken) {
        T t = null;
        try {
            t = getGson().fromJson(str, typeToken.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static String toJson(Object obj) {
        String str = null;
        try {
            str = getGson().toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public synchronized RequestQueue getRequestQueue() {
        if (this.h == null) {
            this.h = OkhttpVolley.newRequestQueue(this.f, new OkhttpNetwork());
        }
        SSLCertificateValidation.trustAllCertificate();
        return this.h;
    }

    public synchronized RequestQueue getSSLRequestQueue(int i, String str, int i2) {
        return Volley.newRequestQueue(this.f, new HurlStack(null, SSLUtils.newSslSocketFactory(i, str)));
    }

    public <T> void addToRequestQueue(Request<T> request, String str) {
        if (TextUtils.isEmpty(str)) {
            str = a;
        }
        request.setTag(str);
        getRequestQueue().add(request);
    }

    public <T> void addToSSLRequestQueue(Request<T> request, String str, int i, String str2, int i2) {
        RequestQueue sSLRequestQueue = getSSLRequestQueue(i, str2, i2);
        if (TextUtils.isEmpty(str)) {
            str = a;
        }
        request.setTag(str);
        sSLRequestQueue.add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(a);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Object obj) {
        if (this.h != null) {
            this.h.cancelAll(obj);
        }
    }

    public void setUserInfoProvider(UserInfoProvider userInfoProvider) {
        this.i = userInfoProvider;
    }

    public UserInfoProvider getUserInfoProvider() {
        if (this.i != null) {
            return this.i;
        }
        throw new RuntimeException("You didn't set user info provider");
    }

    public void setImageProvider(ImageProvider imageProvider) {
        this.j = imageProvider;
    }

    public ImageProvider getImageProvider() {
        if (this.j != null) {
            return this.j;
        }
        throw new RuntimeException("You didn't set image provider");
    }

    public static Builder getSlidrConfigBuilder() {
        return new Builder().position(SlidrPosition.LEFT).sensitivity(0.2f).velocityThreshold(2400.0f).distanceThreshold(0.25f).edge(false);
    }

    public static SlidrConfig getEdgeSlidrConfig() {
        return getSlidrConfigBuilder().edge(true).build();
    }

    public static SlidrConfig getSlidrConfig() {
        return getSlidrConfigBuilder().build();
    }

    public boolean isLiving() {
        return this.k;
    }

    public void setLiving(boolean z) {
        this.k = z;
    }

    public void cancelNotification(int i) {
        ((NotificationManager) this.f.getSystemService("notification")).cancel(i);
    }

    public static boolean isFastDoubleClick() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - d;
        if (0 < j && j < 500) {
            return true;
        }
        d = currentTimeMillis;
        return false;
    }

    public static void showDialogFragment(FragmentActivity fragmentActivity, SimpleDialog.Builder builder) {
        if (fragmentActivity != null && !fragmentActivity.isFinishing()) {
            try {
                DialogFragment.newInstance(builder).show(fragmentActivity.getSupportFragmentManager(), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void addSupportForTransparentStatusBar(View view) {
        if (isSupportForTransparentStatusBar() && view != null) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + WindowUtils.getStatusBarHeight(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    public static void addSupportForTransparentStatusBarMargin(View view) {
        if (isSupportForTransparentStatusBar() && view != null) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.topMargin = ((MarginLayoutParams) view.getLayoutParams()).topMargin + WindowUtils.getStatusBarHeight();
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static boolean isSupportForTransparentStatusBar() {
        return VERSION.SDK_INT >= 19;
    }

    public static boolean isSupportForSetNavigationColor() {
        return VERSION.SDK_INT >= 21;
    }

    public static void weakenRecyclerViewAnimations(RecyclerView recyclerView) {
        ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        itemAnimator.setChangeDuration(0);
        itemAnimator.setAddDuration(0);
        itemAnimator.setMoveDuration(0);
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
    }

    public static void releaseImageBitmap(ImageView imageView) {
        releaseDrawable(imageView.getDrawable());
        imageView.setImageDrawable(null);
    }

    public static void releaseDrawable(Drawable drawable) {
        if (drawable != null) {
            if (drawable instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                if (!(bitmap == null || bitmap.isRecycled())) {
                    bitmap.recycle();
                }
            }
            drawable.setCallback(null);
        }
    }

    public static void copyToClipboard(Context context, String str, int i) {
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", str));
        ToastUtil.Short(i);
    }

    public static void copyToClipboard(Context context, String str) {
        copyToClipboard(context, str, R.string.share_copy_success);
    }

    public Handler getHandler() {
        return this.e;
    }

    public void setRestart(boolean z) {
        this.l = z;
    }

    public boolean isRestart() {
        return this.l;
    }
}
