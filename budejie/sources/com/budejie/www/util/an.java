package com.budejie.www.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony.Sms;
import android.support.v4.content.PermissionChecker;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.utils.ManifestUtil;
import com.ali.auth.third.core.model.Constants;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.androidex.util.ImageUtil;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.CommendDetail;
import com.budejie.www.activity.CommendDetailOld;
import com.budejie.www.activity.OAuthWeiboActivity;
import com.budejie.www.activity.PersonalProfileActivity;
import com.budejie.www.activity.PublishCommentActivity;
import com.budejie.www.activity.detail.PostDetailActivity;
import com.budejie.www.activity.video.a;
import com.budejie.www.activity.view.BadgeView;
import com.budejie.www.bean.CacheItem;
import com.budejie.www.bean.DOCObject;
import com.budejie.www.bean.DraftBean;
import com.budejie.www.bean.Fans;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.TouGaoItem;
import com.budejie.www.bean.UserItem;
import com.budejie.www.bean.VideoOnlineBean;
import com.budejie.www.c.d;
import com.budejie.www.c.g;
import com.budejie.www.c.h;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.elves.update.DownloadServer;
import com.elves.update.b;
import com.elves.update.c;
import com.facebook.common.util.UriUtil;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import com.umeng.update.UpdateConfig;
import com.zxt.download2.f;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class an {
    public static ArrayList<String> a = new ArrayList();
    public static boolean b = false;
    private static final HashSet<String> c = new HashSet();
    private static RectF d = new RectF();
    private static Path e = new Path();
    private static long f;
    private static Toast g;
    private static Handler h = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 0) {
                Activity activity = (Activity) message.obj;
                an.g = an.a(activity, activity.getString(R.string.downloadvideo), -1);
                an.g.show();
            }
        }
    };

    static {
        c.add(UriUtil.HTTP_SCHEME);
        c.add("https");
    }

    public static boolean a(Context context) {
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    return activeNetworkInfo.isAvailable();
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public static String b(Context context) {
        try {
            if ("WIFI".equals(((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().getTypeName())) {
                return "1";
            }
        } catch (Exception e) {
        }
        return "0";
    }

    public static boolean c(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                int subtype = activeNetworkInfo.getSubtype();
                aa.a("is2GMobileNetwork", "current network type = :  " + subtype);
                if (subtype == 1 || subtype == 4 || subtype == 2) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static String d(Context context) {
        String subtypeName;
        String str = "";
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() != 1) {
                if (activeNetworkInfo.getType() == 0) {
                    subtypeName = activeNetworkInfo.getSubtypeName();
                    switch (activeNetworkInfo.getSubtype()) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            subtypeName = "2G";
                            break;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            subtypeName = "3G";
                            break;
                        case 13:
                            subtypeName = "4G";
                            break;
                        default:
                            if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                                subtypeName = "3G";
                                break;
                            }
                    }
                }
            }
            subtypeName = "WIFI";
            aa.b("SisterUtil", "Network Type : " + subtypeName);
            return subtypeName;
        }
        subtypeName = str;
        aa.b("SisterUtil", "Network Type : " + subtypeName);
        return subtypeName;
    }

    public static boolean a(long j) {
        return System.currentTimeMillis() < j;
    }

    public static void a(String str) {
        if (a.a()) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static String b(String str) {
        if (str.endsWith("_6.jpg")) {
            return str.replace("_6.jpg", ".jpg");
        }
        if (str.endsWith("_a_5.jpg")) {
            return str.replace("_a_5.jpg", "_2.gif");
        }
        if (str.endsWith("_2.gif")) {
            return str.replace("_2.gif", "_a_1.jpg");
        }
        return str;
    }

    public static String a(Context context, String str) {
        return new SimpleDateFormat(str).format(new Date());
    }

    public static Toast a(Activity activity, String str, int i) {
        Toast toast = new Toast(activity);
        toast.setDuration(1);
        toast.setGravity(17, 0, 0);
        RelativeLayout relativeLayout = (RelativeLayout) activity.getLayoutInflater().inflate(R.layout.toast, null);
        TextView textView = (TextView) relativeLayout.findViewById(R.id.toast);
        textView.setText(str);
        textView.setTextColor(i);
        toast.setView(relativeLayout);
        return toast;
    }

    public static Toast a(Activity activity, int i, int i2) {
        Toast toast = new Toast(activity);
        toast.setDuration(0);
        toast.setGravity(17, 0, 0);
        RelativeLayout relativeLayout = (RelativeLayout) activity.getLayoutInflater().inflate(R.layout.toast_new, null);
        ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.toast_img);
        ((TextView) relativeLayout.findViewById(R.id.toast)).setText(i);
        imageView.setImageResource(i2);
        toast.setView(relativeLayout);
        return toast;
    }

    public static void a(Activity activity) {
        Builder builder = new Builder(activity);
        builder.setTitle(R.string.sd_title);
        builder.setMessage(R.string.sd_message);
        builder.setNegativeButton(R.string.update_btn_cancel, null);
        builder.setPositiveButton(R.string.update_btn_sure, null);
        if (!activity.isFinishing()) {
            builder.show();
        }
    }

    public static String e(Context context) {
        String deviceId;
        try {
            deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            deviceId = null;
        }
        if (TextUtils.isEmpty(deviceId) || !h(deviceId)) {
            return "000000000000";
        }
        return deviceId;
    }

    public static String a() {
        String str = VERSION.RELEASE;
        if (str == null || !h(str)) {
            return "";
        }
        return str;
    }

    public static String f(Context context) {
        if (context == null) {
            return "";
        }
        return context.getSharedPreferences("weiboprefer", 0).getString("id", "");
    }

    public static UserItem g(Context context) {
        return new m(context).e(context.getSharedPreferences("weiboprefer", 0).getString("id", ""));
    }

    public static String h(Context context) {
        if (context == null) {
            return "";
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI);
        if (wifiManager == null) {
            return "";
        }
        try {
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null) {
                String macAddress = connectionInfo.getMacAddress();
                if (macAddress != null && h(macAddress)) {
                    return macAddress;
                }
            }
            return "";
        } catch (RuntimeException e) {
            return "";
        } catch (Exception e2) {
            return "";
        }
    }

    public static boolean a(SharedPreferences sharedPreferences) {
        CharSequence string = sharedPreferences.getString("locallogin", "");
        if (TextUtils.isEmpty(string) || !Constants.SERVICE_SCOPE_FLAG_VALUE.equals(string)) {
            return false;
        }
        return true;
    }

    public static void b(Activity activity) {
        try {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
        } catch (Exception e) {
        }
    }

    public static void a(final Activity activity, final View view) {
        new Timer().schedule(new TimerTask() {
            public void run() {
                ((InputMethodManager) activity.getSystemService("input_method")).showSoftInput(view, 0);
            }
        }, 0);
    }

    public static void a(Context context, c cVar) {
        b.a(context, Integer.parseInt("266"), "xiaomi", cVar);
    }

    public static Bitmap b(Context context, String str) {
        Bitmap decodeStream;
        try {
            File file;
            Options options = new Options();
            File imageFile = ImageUtil.getImageFile(str);
            if (imageFile == null || !imageFile.exists()) {
                imageFile = new File(str);
                if (imageFile.exists()) {
                    file = imageFile;
                } else {
                    String str2;
                    String str3 = "";
                    if (str == null || str.length() == 0) {
                        str2 = str3;
                    } else {
                        str2 = str.substring(7).replace("/", "-");
                    }
                    file = new File(context.getCacheDir(), str2);
                }
            } else {
                file = imageFile;
            }
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Config.ARGB_8888;
            options.inPurgeable = true;
            options.inInputShareable = true;
            if (file.exists()) {
                return BitmapFactory.decodeFile(file.getPath(), options);
            }
            try {
                decodeStream = BitmapFactory.decodeStream(new BufferedHttpEntity(new DefaultHttpClient().execute(new HttpGet(str)).getEntity()).getContent(), null, options);
                try {
                    h.a(decodeStream, file.getAbsolutePath(), CompressFormat.JPEG);
                    return decodeStream;
                } catch (OutOfMemoryError e) {
                } catch (Exception e2) {
                    aa.e("SisterUtil", "Exception ,path = " + str);
                    return decodeStream;
                }
            } catch (OutOfMemoryError e3) {
                decodeStream = null;
                aa.e("SisterUtil", "createBitmap-OutOfMemoryError ,path = " + str);
                return decodeStream;
            } catch (Exception e4) {
                decodeStream = null;
                aa.e("SisterUtil", "Exception ,path = " + str);
                return decodeStream;
            }
        } catch (OutOfMemoryError e5) {
            return null;
        } catch (Exception e6) {
            return null;
        }
    }

    public static Bitmap i(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.download_dialog_icon);
    }

    public static byte[] a(Context context, Bitmap bitmap, boolean z, boolean z2) {
        byte[] b;
        int i = 300;
        int width = bitmap.getWidth() > 300 ? 300 : bitmap.getWidth();
        if (bitmap.getHeight() <= 300) {
            i = bitmap.getHeight();
        }
        aa.b("SisterUtil", "compressImage width=" + width);
        aa.b("SisterUtil", "compressImage height=" + i);
        Bitmap createBitmap = Bitmap.createBitmap(width, i, Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        while (true) {
            canvas.drawBitmap(bitmap, null, new Rect(0, 0, width, i), null);
            if (z2) {
                Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), R.drawable.wxplayvideo);
                int width2 = decodeResource.getWidth();
                canvas.drawBitmap(decodeResource, (float) ((width - width2) / 2), (float) ((i - width2) / 2), null);
                decodeResource.recycle();
            }
            if (z) {
                bitmap.recycle();
            }
            try {
                b = b(createBitmap, 30);
                createBitmap.recycle();
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().scaledDensity * f) + 0.5f);
    }

    public static int a(Context context, int i) {
        return (int) ((context.getResources().getDisplayMetrics().density * ((float) i)) + 0.5f);
    }

    public static int b(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int a(Resources resources, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, resources.getDisplayMetrics());
    }

    public static void c(Activity activity) {
        File cacheDir = activity.getCacheDir();
        if (cacheDir != null && cacheDir.exists() && cacheDir.isDirectory()) {
            try {
                List arrayList = new ArrayList();
                File[] listFiles = cacheDir.listFiles(new FileFilter() {
                    public boolean accept(File file) {
                        if (file.getName().endsWith(".jpg") || file.getName().endsWith(".gif") || file.getName().endsWith(".png")) {
                            return true;
                        }
                        return false;
                    }
                });
                for (int i = 0; i < listFiles.length; i++) {
                    CacheItem cacheItem = new CacheItem();
                    cacheItem.setFile(listFiles[i]);
                    cacheItem.setTime(listFiles[i].lastModified());
                    arrayList.add(cacheItem);
                }
                if (!arrayList.isEmpty() && arrayList.size() > 100) {
                    Collections.sort(arrayList);
                    int size = arrayList.size() - 100;
                    for (int size2 = arrayList.size() - 1; size2 >= size; size2--) {
                        ((CacheItem) arrayList.get(size2)).getFile().delete();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] c(String str) {
        int i;
        int i2 = 1;
        if (str.startsWith("file://")) {
            str = str.substring(7);
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        if (((float) options.outWidth) > 640.0f) {
            i = (int) (((float) options.outWidth) / 640.0f);
        } else {
            i = 1;
        }
        if (i > 0) {
            i2 = i;
        }
        options.inSampleSize = i2;
        return b(BitmapFactory.decodeFile(str, options), 1000);
    }

    private static byte[] b(Bitmap bitmap, int i) {
        int i2 = 100;
        if (bitmap == null) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        while (byteArrayOutputStream.toByteArray().length / 1024 > i) {
            byteArrayOutputStream.reset();
            bitmap.compress(CompressFormat.JPEG, i2, byteArrayOutputStream);
            i2 -= 10;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] a(Bitmap bitmap, int i, boolean z) {
        int i2 = 100;
        if (bitmap == null) {
            return null;
        }
        Bitmap b;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        aa.b("ghb", "compressImage分享微信原图大小：" + ((bitmap.getRowBytes() * bitmap.getHeight()) / 1024) + "k   image.getHeight():" + bitmap.getHeight());
        if (z) {
            b = h.b(bitmap);
            b = h.a(b, (float) (b.getHeight() / 2));
            aa.b("ghb", "compressImage分享微信缩略图裁剪h>w：" + ((b.getRowBytes() * b.getHeight()) / 1024) + "k");
        } else {
            b = h.a(bitmap, (float) (bitmap.getHeight() / 3));
            aa.b("ghb", "compressImage分享微信缩略图裁剪减小9倍：" + ((b.getRowBytes() * b.getHeight()) / 1024) + "k");
        }
        b.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
        while (byteArrayOutputStream.toByteArray().length / 1024 > i && i2 >= 10) {
            aa.b("ghb", "compressImage baos.toByteArray().length：" + (byteArrayOutputStream.toByteArray().length / 1024) + "k     options：" + i2);
            aa.b("SisterUtil", "compressImage options=" + i2);
            byteArrayOutputStream.reset();
            b.compress(CompressFormat.JPEG, i2, byteArrayOutputStream);
            aa.b("ghb", "----compressImage baos.toByteArray().length 之后：" + (byteArrayOutputStream.toByteArray().length / 1024) + "k     options：" + i2);
            i2 -= 10;
        }
        aa.b("ghb", "compressImage 压缩后大小：" + (byteArrayOutputStream.size() / 1024) + "k     options：" + i2);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        aa.b("ghb", "compressImageSize 待处理原图大小：" + b(bitmap) + "k     maxSize：" + i);
        double d = 1.0d;
        Bitmap bitmap2 = bitmap;
        while (b(bitmap2) > i) {
            d += 0.5d;
            bitmap2 = h.a(bitmap, (float) (((double) bitmap.getHeight()) / d));
            aa.b("ghb", "compressImageSize 循环处理原图：" + b(bitmap2) + "k     stepSize：" + d);
        }
        return bitmap2;
    }

    private static int b(Bitmap bitmap) {
        if (bitmap != null) {
            return (bitmap.getRowBytes() * bitmap.getHeight()) / 1024;
        }
        return 0;
    }

    public static void a(Context context, boolean z) {
        Editor edit = context.getSharedPreferences("weiboprefer", 0).edit();
        edit.putBoolean("isRepostNoticeHasShown", z);
        edit.commit();
    }

    public static boolean j(Context context) {
        return false;
    }

    public static void c(Context context, String str) {
        Editor edit = context.getSharedPreferences("weiboprefer", 0).edit();
        edit.putBoolean(str, true);
        edit.commit();
    }

    public static boolean d(Context context, String str) {
        return context.getSharedPreferences("weiboprefer", 0).getBoolean(str, false);
    }

    public static boolean k(Context context) {
        return context.getSharedPreferences("weiboprefer", 0).getBoolean("isRepostNoticeHasShown", true);
    }

    public static void a(Context context, String str, String str2, String str3) {
        Editor edit = context.getSharedPreferences("weiboprefer", 0).edit();
        edit.putString(str + "_date", str2);
        edit.putString(str + "_state", str3);
        edit.commit();
    }

    public static String e(Context context, String str) {
        return context.getSharedPreferences("weiboprefer", 0).getString(str + "_state", "");
    }

    public static String f(Context context, String str) {
        return context.getSharedPreferences("weiboprefer", 0).getString(str + "_date", "");
    }

    public static void g(Context context, String str) {
        Editor edit = context.getSharedPreferences("weiboprefer", 0).edit();
        edit.putString("get_phonenum_date", str);
        edit.commit();
    }

    public static String l(Context context) {
        return context.getSharedPreferences("weiboprefer", 0).getString("get_phonenum_date", "");
    }

    public static void a(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences("weiboprefer", 0).edit();
        edit.putString(str + "_proxy", str2);
        edit.commit();
    }

    public static String h(Context context, String str) {
        return context.getSharedPreferences("weiboprefer", 0).getString(str + "_proxy", "");
    }

    public static void i(Context context, String str) {
        Editor edit = context.getSharedPreferences("weiboprefer", 0).edit();
        edit.putString("free_flow_phonenum", str);
        edit.commit();
    }

    public static String m(Context context) {
        return context.getSharedPreferences("weiboprefer", 0).getString("free_flow_phonenum", "");
    }

    public static void b(Activity activity, View view) {
        View inflate = activity.getLayoutInflater().inflate(R.layout.repost_notice_dailog, null);
        final PopupWindow popupWindow = new PopupWindow(inflate);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setTouchInterceptor(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 4) {
                    return false;
                }
                popupWindow.dismiss();
                return true;
            }
        });
        ((Button) inflate.findViewById(R.id.repost_notice_btn)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        popupWindow.showAtLocation(view, 17, 0, 0);
        popupWindow.update(0, 0, a((Context) activity, (int) R$styleable.Theme_Custom_last_refresh_item_text_theme), a((Context) activity, (int) R$styleable.Theme_Custom_label_subscribe_bg_gd_color));
    }

    public static boolean n(Context context) {
        return context.getSharedPreferences("weiboprefer", 0).getBoolean("noprofileInNonwifi", true);
    }

    public static String o(Context context) {
        String str = "";
        try {
            return (String) context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get(ManifestUtil.CHANNEL);
        } catch (Exception e) {
            aa.d("SisterUtil", "getApplicaitonMetaData, error");
            e.printStackTrace();
            return str;
        }
    }

    public static String p(Context context) {
        return context.getSharedPreferences("weiboprefer", 0).getString("theme_style", "light_colour");
    }

    public static void j(Context context, String str) {
        Editor edit = context.getSharedPreferences("weiboprefer", 0).edit();
        edit.putString("theme_style", str);
        edit.commit();
    }

    public static int q(Context context) {
        String r = r(context);
        String str = "";
        str = OnlineConfigAgent.getInstance().getConfigParams(context, "内容流-内容文字-字体");
        aa.a("SisterUtil", "umeng onlineParams = " + str);
        int a = z.a(r, str);
        aa.a("SisterUtil", "currentTextSizeType  = " + r + ", textSizeValue = " + a);
        if (a == 0) {
            return d(r);
        }
        return a;
    }

    public static void k(Context context, String str) {
        Editor edit = context.getSharedPreferences("weiboprefer", 0).edit();
        edit.putString("ListItemContentTextSizeType", str);
        edit.commit();
    }

    public static String r(Context context) {
        return context.getSharedPreferences("weiboprefer", 0).getString("ListItemContentTextSizeType", "medium");
    }

    public static int d(String str) {
        if (str.equals("large")) {
            return 22;
        }
        if (str.equals("medium") || !str.equals("small")) {
            return 19;
        }
        return 15;
    }

    public static Bitmap a(Bitmap bitmap) {
        return a(bitmap, 0.1f);
    }

    private static Bitmap a(Bitmap bitmap, float f) {
        if (bitmap == null || f <= 0.0f) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        e.reset();
        d.set(0.0f, 0.0f, (float) width, (float) height);
        e.addRoundRect(d, (float) ((int) (((float) width) * f)), (float) ((int) (((float) height) * f)), Direction.CW);
        canvas.clipPath(e);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(1));
        bitmap.recycle();
        return createBitmap;
    }

    public static boolean s(Context context) {
        if (Constants.SERVICE_SCOPE_FLAG_VALUE.equals(OnlineConfigAgent.getInstance().getConfigParams(context, "自动更新-启动时更新"))) {
            return true;
        }
        return false;
    }

    public static boolean l(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static int t(Context context) {
        int i = 0;
        try {
            Class cls = Class.forName("com.android.internal.R$dimen");
            i = context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void a(ArrayList<NameValuePair> arrayList, Context context) {
        arrayList.add(new BasicNameValuePair("ver", "6.9.2"));
        arrayList.add(new BasicNameValuePair("client", AlibcConstants.PF_ANDROID));
        arrayList.add(new BasicNameValuePair("market", "xiaomi"));
        arrayList.add(new BasicNameValuePair("udid", e(context)));
        arrayList.add(new BasicNameValuePair("mac", h(context)));
        arrayList.add(new BasicNameValuePair("os", a()));
    }

    public static void a(String str, ArrayList<NameValuePair> arrayList, Context context) {
        str + "&ver=6.9.2&client=android&marketxiaomi&udid=" + e(context) + "&mac=" + h(context) + "&appname=" + "baisibudejie";
        arrayList.add(new BasicNameValuePair("os", a()));
    }

    public static void a(List<ListItemObject> list, h hVar, d dVar, g gVar) {
        aa.b("SisterUtil", "editlistLocalData()");
        try {
            List a = hVar.a();
            List f = dVar.f();
            List<Fans> b = gVar.b();
            if (a.size() >= 0) {
                for (int i = 0; i < list.size(); i++) {
                    int i2;
                    ListItemObject listItemObject = (ListItemObject) list.get(i);
                    for (i2 = 0; i2 < a.size(); i2++) {
                        DOCObject dOCObject = (DOCObject) a.get(i2);
                        if (dOCObject.getData_id().equals(listItemObject.getWid())) {
                            listItemObject.setFlag(dOCObject.getFlag());
                            listItemObject.setCai_flag(dOCObject.getCai_flag());
                            break;
                        }
                    }
                    if (f.size() == 0) {
                        for (i2 = 0; i2 < list.size(); i2++) {
                            ((ListItemObject) list.get(i2)).setCollect(false);
                        }
                    }
                    i2 = 0;
                    while (i2 < f.size()) {
                        String str = (String) f.get(i2);
                        if (str != null && str.equals(listItemObject.getWid())) {
                            listItemObject.setCollect(true);
                            break;
                        } else {
                            listItemObject.setCollect(false);
                            i2++;
                        }
                    }
                    if (b == null || b.size() == 0) {
                        aa.b("SisterUtil", "itemObject.setShwFollow(true)");
                        listItemObject.setShwFollow(true);
                    } else {
                        for (Fans id : b) {
                            if (id.getId().equals(listItemObject.getUid())) {
                                aa.b("SisterUtil", "itemObject.setShwFollow(false)");
                                listItemObject.setShwFollow(false);
                                break;
                            }
                            aa.b("SisterUtil", "itemObject.setShwFollow(true)");
                            listItemObject.setShwFollow(true);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public static void a(List<TouGaoItem> list, h hVar, d dVar, g gVar, int i) {
        aa.b("SisterUtil", "editlistLocalData()");
        try {
            List a = hVar.a();
            List f = dVar.f();
            gVar.b();
            if (a.size() >= 0) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    int i3;
                    TouGaoItem touGaoItem = (TouGaoItem) list.get(i2);
                    for (i3 = 0; i3 < a.size(); i3++) {
                        DOCObject dOCObject = (DOCObject) a.get(i3);
                        if (dOCObject.getData_id().equals(touGaoItem.getDataId())) {
                            touGaoItem.setFlag(dOCObject.getFlag());
                            touGaoItem.setCai_flag(dOCObject.getCai_flag());
                            break;
                        }
                    }
                    if (f.size() == 0) {
                        for (i3 = 0; i3 < list.size(); i3++) {
                            ((TouGaoItem) list.get(i3)).setCollect(false);
                        }
                    }
                    i3 = 0;
                    while (i3 < f.size()) {
                        String str = (String) f.get(i3);
                        if (str != null && str.equals(touGaoItem.getDataId())) {
                            touGaoItem.setCollect(true);
                            break;
                        } else {
                            touGaoItem.setCollect(false);
                            i3++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(List<ListItemObject> list, h hVar, d dVar) {
        try {
            List a = hVar.a();
            List f = dVar.f();
            if (a.size() >= 0) {
                for (int i = 0; i < list.size(); i++) {
                    int i2;
                    ListItemObject listItemObject = (ListItemObject) list.get(i);
                    for (i2 = 0; i2 < a.size(); i2++) {
                        DOCObject dOCObject = (DOCObject) a.get(i2);
                        if (dOCObject.getData_id().equals(listItemObject.getWid())) {
                            listItemObject.setFlag(dOCObject.getFlag());
                            listItemObject.setCai_flag(dOCObject.getCai_flag());
                            break;
                        }
                    }
                    if (f.size() == 0) {
                        for (i2 = 0; i2 < list.size(); i2++) {
                            ((ListItemObject) list.get(i2)).setCollect(false);
                        }
                    }
                    i2 = 0;
                    while (i2 < f.size()) {
                        String str = (String) f.get(i2);
                        if (str != null && str.equals(listItemObject.getWid())) {
                            listItemObject.setCollect(true);
                            break;
                        } else {
                            listItemObject.setCollect(false);
                            i2++;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public static void b(List<com.budejie.www.activity.mycomment.d> list, h hVar, d dVar) {
        try {
            List a = hVar.a();
            List f = dVar.f();
            if (a.size() >= 0) {
                for (int i = 0; i < list.size(); i++) {
                    int i2;
                    ListItemObject listItemObject = ((com.budejie.www.activity.mycomment.d) list.get(i)).c;
                    for (i2 = 0; i2 < a.size(); i2++) {
                        DOCObject dOCObject = (DOCObject) a.get(i2);
                        if (dOCObject.getData_id().equals(listItemObject.getWid())) {
                            listItemObject.setFlag(dOCObject.getFlag());
                            listItemObject.setCai_flag(dOCObject.getCai_flag());
                            break;
                        }
                    }
                    if (f.size() == 0) {
                        for (i2 = 0; i2 < list.size(); i2++) {
                            ((com.budejie.www.activity.mycomment.d) list.get(i2)).c.setCollect(false);
                        }
                    }
                    i2 = 0;
                    while (i2 < f.size()) {
                        String str = (String) f.get(i2);
                        if (str != null && str.equals(listItemObject.getWid())) {
                            listItemObject.setCollect(true);
                            break;
                        } else {
                            listItemObject.setCollect(false);
                            i2++;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public static void c(List<ListItemObject> list, h hVar, d dVar) {
        List a = hVar.a();
        List f = dVar.f();
        if (!com.budejie.www.goddubbing.c.a.a(a) && !com.budejie.www.goddubbing.c.a.a(list)) {
            for (int i = 0; i < list.size(); i++) {
                int i2;
                ListItemObject listItemObject = (ListItemObject) list.get(i);
                for (i2 = 0; i2 < a.size(); i2++) {
                    DOCObject dOCObject = (DOCObject) a.get(i2);
                    Object data_id = dOCObject.getData_id();
                    if (!TextUtils.isEmpty(data_id) && data_id.equals(listItemObject.getWid())) {
                        listItemObject.setFlag(dOCObject.getFlag());
                        listItemObject.setCai_flag(dOCObject.getCai_flag());
                        listItemObject.setCai(dOCObject.getCai());
                        listItemObject.setLove(dOCObject.getDing());
                        break;
                    }
                }
                if (f.size() == 0) {
                    for (i2 = 0; i2 < list.size(); i2++) {
                        ((ListItemObject) list.get(i2)).setCollect(false);
                    }
                }
                i2 = 0;
                while (i2 < f.size()) {
                    String str = (String) f.get(i2);
                    if (str != null && str.equals(listItemObject.getWid())) {
                        listItemObject.setCollect(true);
                        break;
                    } else {
                        listItemObject.setCollect(false);
                        i2++;
                    }
                }
            }
        }
    }

    public static void a(ListItemObject listItemObject, h hVar, d dVar) {
        int i = 0;
        try {
            List a = hVar.a();
            List f = dVar.f();
            if (a.size() >= 0) {
                for (int i2 = 0; i2 < a.size(); i2++) {
                    DOCObject dOCObject = (DOCObject) a.get(i2);
                    if (dOCObject.getData_id().equals(listItemObject.getWid())) {
                        listItemObject.setFlag(dOCObject.getFlag());
                        listItemObject.setCai_flag(dOCObject.getCai_flag());
                        break;
                    }
                }
                if (f.size() == 0) {
                    listItemObject.setCollect(false);
                }
                while (i < f.size()) {
                    String str = (String) f.get(i);
                    if (str == null || !str.equals(listItemObject.getWid())) {
                        listItemObject.setCollect(false);
                        i++;
                    } else {
                        listItemObject.setCollect(true);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File a(Activity activity, String str) {
        File cacheDir = activity.getCacheDir();
        String str2 = "";
        if (str != null && str.length() > 7) {
            str2 = str.substring(7).replace("/", "-");
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "testXXXXXXX.jpg";
        }
        return new File(cacheDir, str2);
    }

    public static void a(String str, String str2) {
        int i = 0;
        try {
            File file = new File(str);
            File file2 = new File(str2);
            if (file.exists() && !file2.exists()) {
                file = new File(Environment.getExternalStorageDirectory().toString() + "/budejie");
                if (!file.exists()) {
                    file.mkdir();
                }
                InputStream fileInputStream = new FileInputStream(str);
                FileOutputStream fileOutputStream = new FileOutputStream(str2);
                byte[] bArr = new byte[1444];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        i += read;
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileInputStream.close();
                        aa.a("SisterUtil", "copyFile成功：老文件：" + str + "：新文件：" + str2);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            aa.a("SisterUtil", "复制单个文件操作出错");
            e.printStackTrace();
        }
    }

    public static void a(File file) {
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                file.delete();
                return;
            }
            for (File a : listFiles) {
                a(a);
            }
        }
    }

    public static long b(File file) throws Exception {
        long j = 0;
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    j += b(file2);
                } else {
                    j += file2.length();
                }
            }
        }
        return j;
    }

    public static String b(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        String str = "";
        String str2;
        if (j < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            str2 = decimalFormat.format((double) j) + "B";
            if ("0B".equals(str2)) {
                return "1B";
            }
            return str2;
        } else if (j < 1048576) {
            str2 = decimalFormat.format(((double) j) / 1024.0d) + "K";
            if ("0K".equals(str2)) {
                return "1K";
            }
            return str2;
        } else if (j < 1073741824) {
            str2 = decimalFormat.format(((double) j) / 1048576.0d) + "M";
            if ("0M".equals(str2)) {
                return "1M";
            }
            return str2;
        } else {
            str2 = decimalFormat.format(((double) j) / 1.073741824E9d) + "G";
            if ("0G".equals(str2)) {
                return "1G";
            }
            return str2;
        }
    }

    public static boolean m(Context context, String str) {
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(context, str);
        if (!TextUtils.isEmpty(configParams)) {
            String[] split = configParams.split(com.alipay.sdk.util.h.b);
            if (split.length != 0) {
                for (String split2 : split) {
                    String[] split3 = split2.split(":");
                    if (split3.length == 2 && "xiaomi".equals(split3[0]) && "6.9.2".equals(split3[1])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static ListItemObject a(DraftBean draftBean) {
        ListItemObject listItemObject = new ListItemObject();
        if (draftBean != null) {
            listItemObject.setIsDraftBean(true);
            listItemObject.setAddtime(draftBean.createTime);
            listItemObject.setUid(String.valueOf(draftBean.uid));
            listItemObject.setContent(draftBean.content);
            listItemObject.setImgUrl(draftBean.bimage);
            if (!TextUtils.isEmpty(draftBean.bimage)) {
                if (!TextUtils.isEmpty(draftBean.width)) {
                    listItemObject.setWidth(e(draftBean.width));
                }
                if (!TextUtils.isEmpty(draftBean.height)) {
                    listItemObject.setHeight(e(draftBean.height));
                }
            }
            listItemObject.setWid(draftBean.tid);
            listItemObject.setVoiceUri(draftBean.voice);
            listItemObject.setVoicetime(String.valueOf(draftBean.voicetime));
            listItemObject.setVideouri(draftBean.video);
            listItemObject.setVideotime(String.valueOf(draftBean.videotime));
            listItemObject.setIs_gif(draftBean.isGif ? "1" : "0");
            listItemObject.setState(draftBean.state);
            listItemObject.setTheme_id(draftBean.theme_id);
            listItemObject.setTheme_type(draftBean.theme_type);
            listItemObject.setTheme_name(draftBean.theme_name);
            listItemObject.setWeixin_url(draftBean.landuri);
            String str = "29";
            if (!TextUtils.isEmpty(draftBean.video)) {
                str = "41";
            } else if (!TextUtils.isEmpty(draftBean.voice)) {
                str = "31";
            } else if (!TextUtils.isEmpty(draftBean.bimage)) {
                str = com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
            } else if (!TextUtils.isEmpty(draftBean.linkurl)) {
                str = com.tencent.connect.common.Constants.VIA_ACT_TYPE_TWENTY_EIGHT;
            }
            listItemObject.setType(str);
            listItemObject.setVoteData(draftBean.voteData);
            listItemObject.setPlateDatasJson(draftBean.plateDataStr);
        }
        return listItemObject;
    }

    public static int e(String str) {
        int i = 0;
        try {
            i = Integer.valueOf(str).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static ListItemObject a(TouGaoItem touGaoItem) {
        ListItemObject listItemObject = new ListItemObject();
        if (touGaoItem != null) {
            listItemObject.setAddtime(touGaoItem.getTime());
            listItemObject.setUid(touGaoItem.getUid());
            listItemObject.setContent(touGaoItem.getContent());
            listItemObject.setImgUrl(touGaoItem.getImgUrl());
            listItemObject.setWidth(e(touGaoItem.getWidth() == null ? "0" : touGaoItem.getWidth()));
            listItemObject.setHeight(e(touGaoItem.getHeight() == null ? "0" : touGaoItem.getHeight()));
            listItemObject.setLove(e(touGaoItem.getDingCount() == null ? "0" : touGaoItem.getDingCount()));
            listItemObject.setCai(e(touGaoItem.getCaiCount() == null ? "0" : touGaoItem.getCaiCount()));
            listItemObject.setRepost(touGaoItem.getForwardCount());
            listItemObject.setComment(touGaoItem.getCommendCount());
            listItemObject.setWid(touGaoItem.getDataId());
            listItemObject.setMid(touGaoItem.getMid());
            listItemObject.setVoiceUri(touGaoItem.getVoiceuri());
            listItemObject.setVoicetime(touGaoItem.getVoicetime());
            listItemObject.setVideouri(touGaoItem.getVideouri());
            listItemObject.setPlaycount(touGaoItem.getPlaycount());
            listItemObject.setVideouriBackup(touGaoItem.getVideouriBackup());
            listItemObject.setVideotime(touGaoItem.getVideotime());
            listItemObject.setGifFistFrame(touGaoItem.getGifFistFrame());
            listItemObject.setIs_gif(touGaoItem.getIsGif() ? "1" : "0");
            listItemObject.setProfile(touGaoItem.getProfileImage());
            listItemObject.setName(touGaoItem.getScreenName());
            listItemObject.setStatus_text(touGaoItem.getStatus_text());
            listItemObject.setStatus(touGaoItem.getStatus());
            listItemObject.setCnd_img(touGaoItem.getCnd_img());
            listItemObject.setTheme_id(touGaoItem.getTheme_id());
            listItemObject.setTheme_type(touGaoItem.getTheme_type());
            listItemObject.setTheme_name(touGaoItem.getTheme_name());
            listItemObject.setWeixin_url(touGaoItem.getWeixin_url());
            listItemObject.setPlateDatas(touGaoItem.getPlateDatas());
            listItemObject.setPlateDatasJson(touGaoItem.getPlateDatasJson());
            listItemObject.setType(touGaoItem.getType());
            listItemObject.setOriginal_topic(touGaoItem.getOriginal_topic());
            listItemObject.setFlag(touGaoItem.getFlag());
            listItemObject.setCai_flag(touGaoItem.getCai_flag());
            listItemObject.setCollect(touGaoItem.isCollect());
            listItemObject.setDownloadVideoUris(touGaoItem.getDownloadVideoUris());
            listItemObject.setDownloadImageUris(touGaoItem.getDownloadImageUris());
            listItemObject.setRichObject(touGaoItem.getRichObject());
            listItemObject.setHeadPortraitItems(touGaoItem.getHeadPortraitItem());
            listItemObject.setVoteDataJson(touGaoItem.getVoteDataJson());
            listItemObject.setVoteData(touGaoItem.getVoteData());
        }
        return listItemObject;
    }

    public static ListItemObject a(com.budejie.www.activity.mycomment.d dVar) {
        if (dVar == null && dVar.c != null) {
            return null;
        }
        ListItemObject listItemObject = new ListItemObject();
        listItemObject = dVar.c;
        listItemObject.setHotcmt(dVar.a);
        listItemObject.setPrecmt(dVar.b);
        listItemObject.setPlaceholder(dVar.d);
        return listItemObject;
    }

    public static TouGaoItem a(ListItemObject listItemObject) {
        TouGaoItem touGaoItem = new TouGaoItem();
        if (listItemObject != null) {
            touGaoItem.setTime(listItemObject.getAddtime());
            touGaoItem.setUid(listItemObject.getUid());
            touGaoItem.setContent(listItemObject.getContent());
            touGaoItem.setImgUrl(listItemObject.getImgUrl());
            touGaoItem.setWidth(String.valueOf(listItemObject.getWidth()));
            touGaoItem.setHeight(String.valueOf(listItemObject.getHeight()));
            touGaoItem.setDingCount(String.valueOf(listItemObject.getLove()));
            touGaoItem.setCaiCount(String.valueOf(listItemObject.getCai()));
            touGaoItem.setForwardCount(listItemObject.getRepost());
            touGaoItem.setCommendCount(listItemObject.getComment());
            touGaoItem.setDataId(listItemObject.getWid());
            touGaoItem.setMid(listItemObject.getMid());
            touGaoItem.setVoiceuri(listItemObject.getVoiceUri());
            touGaoItem.setVoicetime(listItemObject.getVoicetime());
            touGaoItem.setVideouri(listItemObject.getVideouri());
            touGaoItem.setVideouriBackup(listItemObject.getVideouriBackup());
            touGaoItem.setVideotime(listItemObject.getVideotime());
            touGaoItem.setPlaycount(listItemObject.getPlaycount());
            touGaoItem.setGifFistFrame(listItemObject.getGifFistFrame());
            touGaoItem.setIsGif("1".equals(listItemObject.getIs_gif()));
            touGaoItem.setProfileImage(listItemObject.getProfile());
            touGaoItem.setScreenName(listItemObject.getName());
            touGaoItem.setStatus_text(listItemObject.getStatus_text());
            touGaoItem.setStatus(listItemObject.getStatus());
            touGaoItem.setTheme_id(listItemObject.getTheme_id());
            touGaoItem.setTheme_type(listItemObject.getTheme_type());
            touGaoItem.setTheme_name(listItemObject.getTheme_name());
            touGaoItem.setWeixin_url(listItemObject.getWeixin_url());
            touGaoItem.setPlateDatas(listItemObject.getPlateDatas());
            touGaoItem.setPlateDatasJson(listItemObject.getPlateDatasJson());
            touGaoItem.setType(listItemObject.getType());
            touGaoItem.setOriginal_topic(listItemObject.getOriginal_topic());
            touGaoItem.setDownloadVideoUris(listItemObject.getDownloadVideoUris());
            touGaoItem.setDownloadImageUris(listItemObject.getDownloadImageUris());
            touGaoItem.setRichObject(listItemObject.getRichObject());
            touGaoItem.setHeadPortraitItem(listItemObject.getHeadPortraitItems());
            touGaoItem.setVoteDataJson(listItemObject.getVoteDataJson());
            touGaoItem.setVoteData(listItemObject.getVoteData());
        }
        return touGaoItem;
    }

    public static DraftBean b(ListItemObject listItemObject) {
        DraftBean draftBean = new DraftBean();
        if (listItemObject != null) {
            draftBean.createTime = listItemObject.getAddtime();
            draftBean.uid = e(listItemObject.getUid());
            draftBean.content = listItemObject.getContent();
            String imgUrl = listItemObject.getImgUrl();
            if (!TextUtils.isEmpty(imgUrl) && Scheme.ofUri(imgUrl) == Scheme.FILE) {
                imgUrl = Scheme.FILE.crop(imgUrl);
            }
            draftBean.bimage = imgUrl;
            draftBean.tid = listItemObject.getWid();
            draftBean.voice = listItemObject.getVoiceUri();
            draftBean.voicetime = e(listItemObject.getVoicetime());
            draftBean.video = listItemObject.getVideouri();
            draftBean.videotime = e(listItemObject.getVideotime());
            draftBean.isGif = "1".equals(listItemObject.getIs_gif());
            draftBean.state = listItemObject.getState();
            draftBean.theme_id = listItemObject.getTheme_id();
            draftBean.theme_type = listItemObject.getTheme_type();
            draftBean.theme_name = listItemObject.getTheme_name();
            draftBean.plateDataStr = listItemObject.getPlateDatasJson();
            draftBean.plateDatas = listItemObject.getPlateDatas();
            draftBean.landuri = listItemObject.getWeixin_url();
        }
        return draftBean;
    }

    public static void u(Context context) {
        ai.i(context);
        ai.k(context);
        ai.m(context);
    }

    public static boolean v(Context context) {
        Object b = ai.b(context);
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(context, "topic-key");
        if (TextUtils.isEmpty(configParams) || TextUtils.isEmpty(b)) {
            return false;
        }
        for (Object equals : configParams.split(",")) {
            if (b.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    public static void a(Activity activity, int i, String str, String str2, int i2) {
        Intent intent = new Intent(activity, OAuthWeiboActivity.class);
        String configParams = OnlineConfigAgent.getInstance().getConfigParams(activity, "login_ui_switch");
        if (TextUtils.isEmpty(configParams)) {
            configParams = "new";
        }
        intent.putExtra("loginUI", configParams);
        if (i == 1) {
            intent.putExtra("source", str);
            intent.putExtra("type", str2);
            activity.startActivityForResult(intent, i2);
        } else if (i == 2) {
            intent.putExtra("source", str);
            intent.putExtra("type", str2);
            intent.putExtra("regist", true);
            activity.startActivityForResult(intent, i2);
        } else if (i == 0) {
            activity.startActivity(intent);
        }
        if ("new".equals(configParams)) {
            MobclickAgent.onEvent(activity, "newLoginUI_enter_count");
        }
    }

    public static void a(Activity activity, Intent intent) {
        String configParams = OnlineConfigAgent.getInstance().getConfigParams(activity, "login_ui_switch");
        if (TextUtils.isEmpty(configParams)) {
            configParams = "new";
        }
        Intent intent2 = new Intent(activity, OAuthWeiboActivity.class);
        intent2.putExtra("loginUI", configParams);
        if (intent != null) {
            intent2.putExtra("ok_taget_pending_intent", PendingIntent.getActivity(activity, 100, intent, 1073741824));
        }
        activity.startActivity(intent2);
        if ("new".equals(configParams)) {
            MobclickAgent.onEvent(activity, "newLoginUI_enter_count");
        }
    }

    public static void b(Activity activity, String str) {
        if (!a((Context) activity)) {
            a(activity, activity.getString(R.string.nonet), -1);
        } else if (TextUtils.isEmpty(str)) {
            a(activity, activity.getString(R.string.param_error), -1);
        } else {
            Intent intent = new Intent(activity, PersonalProfileActivity.class);
            intent.putExtra(PersonalProfileActivity.c, str);
            activity.startActivity(intent);
        }
    }

    public static void b(Activity activity, String str, int i) {
        if (!a((Context) activity)) {
            a(activity, activity.getString(R.string.nonet), -1);
        } else if (TextUtils.isEmpty(str)) {
            a(activity, activity.getString(R.string.param_error), -1);
        } else {
            Intent intent = new Intent(activity, PersonalProfileActivity.class);
            intent.putExtra(PersonalProfileActivity.c, str);
            activity.startActivityForResult(intent, i);
        }
    }

    public static int d(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        return (defaultDisplay.getHeight() - t(activity)) - (((int) activity.getResources().getDimension(R.dimen.navigation_height)) * 2);
    }

    @SuppressLint({"NewApi"})
    public static int w(Context context) {
        int width;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (VERSION.SDK_INT < 13) {
            width = defaultDisplay.getWidth();
        } else {
            Point point = new Point();
            defaultDisplay.getSize(point);
            width = point.x;
        }
        if (width <= R$styleable.Theme_Custom_last_refresh_item_text_theme) {
            return 0;
        }
        if (width >= 720) {
            return 2;
        }
        return 1;
    }

    public static int x(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public static int y(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
    }

    public static int z(Context context) {
        return (context.getResources().getDisplayMetrics().heightPixels - t(context)) - (((int) context.getResources().getDimension(R.dimen.navigation_height)) * 2);
    }

    public static BadgeView a(Context context, View view, boolean z, int i, int i2, int i3, float f) {
        return a(context, view, z, i, i2, i3, f, false);
    }

    public static BadgeView a(Context context, View view, boolean z, int i, int i2, int i3, float f, boolean z2) {
        BadgeView badgeView = new BadgeView(context, view);
        badgeView.setBadgePosition(i3);
        if (z) {
            badgeView.setBadgeBackgroundColor(R.color.main_red);
            badgeView.setTextSize(f);
        }
        if (z2) {
            badgeView.setBackgroundResource(R.drawable.red_count_red_packet);
        } else {
            badgeView.setBackgroundResource(com.budejie.www.h.c.a().b(R.attr.notice_red_point));
        }
        badgeView.b((int) (((float) i) * i.a().b(context)), (int) (((float) i2) * i.a().b(context)));
        return badgeView;
    }

    public static int A(Context context) {
        return (int) (10.0f * i.a().b(context));
    }

    public static String B(Context context) {
        if (ai.r(context) == 0) {
            return ai.n(context);
        }
        return ai.p(context);
    }

    public static String C(Context context) {
        UserItem e = new m(context).e(context.getSharedPreferences("weiboprefer", 0).getString("id", ""));
        String str = "";
        if (e != null) {
            String sex = e.getSex();
            if ("m".equals(sex)) {
                return "1";
            }
            if ("f".equals(sex)) {
                return "2";
            }
        }
        return "";
    }

    public static String D(Context context) {
        UserItem e = new m(context).e(context.getSharedPreferences("weiboprefer", 0).getString("id", ""));
        String str = "";
        if (e != null) {
            return e.getProfile();
        }
        return str;
    }

    public static void a(final Activity activity, boolean z) {
        if (!l(activity, "com.spriteapp.live")) {
            Builder builder = new Builder(activity);
            builder.setTitle(R.string.system_tip);
            builder.setMessage("“瞬间”现场直播尚未安装，是否下载观看直播？");
            builder.setPositiveButton(R.string.update_btn_sure, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    String configParams = OnlineConfigAgent.getInstance().getConfigParams(activity, "视频直播下载地址");
                    if (TextUtils.isEmpty(configParams)) {
                        configParams = "";
                    }
                    String str = Environment.getExternalStorageDirectory().getPath() + "/elves/apk";
                    String str2 = "";
                    if (!TextUtils.isEmpty(configParams)) {
                        str2 = configParams.substring(configParams.lastIndexOf("/") + 1);
                    }
                    Intent intent = new Intent(activity, DownloadServer.class);
                    intent.putExtra("apkPath", str);
                    intent.putExtra("url", configParams);
                    intent.putExtra("apkName", str2);
                    activity.startService(intent);
                }
            });
            builder.setNegativeButton(R.string.update_btn_cancel, null);
            builder.show();
        } else if (a(activity.getSharedPreferences("weiboprefer", 0))) {
            SharedPreferences sharedPreferences = activity.getSharedPreferences("weiboprefer", 0);
            m mVar = new m(activity);
            String string = sharedPreferences.getString("id", "");
            UserItem e = mVar.e(string);
            Object videoOnlineBean = new VideoOnlineBean();
            videoOnlineBean.setScheme(activity.getPackageName());
            videoOnlineBean.setCookie(NetWorkUtil.b((Context) activity));
            videoOnlineBean.setNikename(e.getName());
            videoOnlineBean.setUid(string);
            videoOnlineBean.setTelephone(e.getPhone());
            string = new Gson().toJson(videoOnlineBean);
            aa.a("SisterUtil", "转化后的json：" + string);
            Intent intent = new Intent();
            intent.setClassName("com.spriteapp.live", "com.spriteapp.live.IndexActivity");
            intent.putExtra(com.tencent.connect.common.Constants.LOGIN_INFO, string);
            if (z) {
                intent.putExtra("target_intent", "AnchorActivity");
            }
            activity.startActivity(intent);
        } else {
            activity.startActivity(new Intent(activity, OAuthWeiboActivity.class));
        }
    }

    public static boolean f(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.toLowerCase().endsWith("jpg") || str.toLowerCase().endsWith("png") || str.toLowerCase().endsWith("bmp") || str.toLowerCase().endsWith("gif")) {
            return true;
        }
        return false;
    }

    public static boolean b(DraftBean draftBean) {
        return !TextUtils.isEmpty(draftBean.bimage) && TextUtils.isEmpty(draftBean.voice) && TextUtils.isEmpty(draftBean.video);
    }

    public static boolean c(DraftBean draftBean) {
        return !TextUtils.isEmpty(draftBean.voice) && TextUtils.isEmpty(draftBean.video);
    }

    public static boolean d(DraftBean draftBean) {
        return !TextUtils.isEmpty(draftBean.video);
    }

    public static boolean e(Activity activity) {
        if ("1".equals(ai.t(activity))) {
            a(activity, "设备已被封禁，解禁后恢复", -1).show();
            return true;
        }
        UserItem e = new m(activity).e(ai.b(activity));
        if (e == null) {
            return false;
        }
        if ("1".equals(e.getIs_black_device())) {
            a(activity, "设备已被封禁，解禁后恢复", -1).show();
            return true;
        } else if (!"9".equals(e.getGrade()) && !"1".equals(e.getIs_black_user())) {
            return false;
        } else {
            a(activity, "账号已被封禁，解禁后恢复", -1).show();
            return true;
        }
    }

    public static boolean b() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - f;
        if (0 < j && j < 800) {
            return true;
        }
        f = currentTimeMillis;
        return false;
    }

    public static void a(Activity activity, ListItemObject listItemObject) {
        i.a(activity.getString(R.string.track_event_video_download), j.a(listItemObject), j.b((Context) activity, listItemObject));
        Map hashMap = new HashMap();
        hashMap.put("type", "视频");
        hashMap.put("title", listItemObject.getContent());
        hashMap.put("label", listItemObject.getPlateNames());
        a((Context) activity, hashMap, "E01_A05");
        if (!i(UpdateConfig.f)) {
            p.a((Context) activity);
        } else if (!com.elves.update.d.a()) {
            a(activity);
        } else if (a((Context) activity)) {
            String str = Environment.getExternalStorageDirectory().getPath() + "/-百思不得姐视频";
            String str2 = "";
            String str3 = "";
            if (listItemObject != null) {
                str2 = listItemObject.getDownloadVideoUri();
                if (TextUtils.isEmpty(str2)) {
                    str2 = listItemObject.getVideouri();
                }
                str3 = listItemObject.getContent();
            }
            if (TextUtils.isEmpty(str2)) {
                g = a(activity, activity.getString(R.string.data_invalid), -1);
                g.show();
                return;
            }
            String a = com.lt.a.a((Context) activity).a(str2);
            if (com.zxt.download2.g.a((Context) activity).c(a)) {
                g = a(activity, activity.getString(R.string.delvideo_downloaded_tip), -1);
                g.show();
                return;
            }
            f fVar = new f(a, str, "不得姐" + new Date().getTime() + ".mp4", str3, null);
            if (listItemObject != null) {
                fVar.a(listItemObject.getImgUrl());
            }
            com.zxt.download2.g.a((Context) activity).a(fVar, new com.zxt.download2.c(activity, fVar));
            com.zxt.download2.g.a((Context) activity).a(fVar);
            E(activity);
            Message message = new Message();
            message.what = 0;
            message.obj = activity;
            h.sendMessageDelayed(message, 2000);
        } else {
            g = a(activity, activity.getString(R.string.nonet), -1);
            g.show();
        }
    }

    public static void a(Context context, ListItemObject listItemObject) {
        if (!a(context)) {
            au.a((int) R.string.nonet);
        } else if (listItemObject == null) {
            au.a((int) R.string.save_failed);
        } else {
            String[] downloadImageUris = listItemObject.getDownloadImageUris();
            if (downloadImageUris == null || downloadImageUris.length == 0) {
                au.a((int) R.string.save_failed);
            } else {
                n(context, "1".equals(listItemObject.getIs_gif()) ? listItemObject.getImgUrl() : downloadImageUris[0]);
            }
        }
    }

    public static void n(Context context, String str) {
        if (a(context)) {
            BudejieApplication.a.a(context, str, new com.budejie.www.f.a() {
                public void a(int i, String str) {
                    au.a((int) R.string.save_successed);
                }

                public void a(int i) {
                    au.a((int) R.string.save_failed);
                }
            }, 100);
        } else {
            au.a((int) R.string.nonet);
        }
    }

    public static String o(Context context, String str) {
        try {
            if (!com.elves.update.d.a() || TextUtils.isEmpty(str) || !a(context)) {
                return str;
            }
            String str2 = Environment.getExternalStorageDirectory().getPath() + "/budejie/AdCache";
            str = com.lt.a.a(context).a(str);
            String a = com.budejie.www.download.d.a(ab.a(str) + ".mp4", str);
            File file = new File(str2 + "/" + a);
            if (file.exists()) {
                return file.getPath();
            }
            com.budejie.www.download.g.a().a(new com.budejie.www.download.f(str, str2, a));
            return str;
        } catch (Exception e) {
            return str;
        }
    }

    public static void E(Context context) {
        if (a(context) && !"1".equals(b(context)) && !com.lt.a.a(context).e() && !ai.u(context)) {
            ai.a(context, true);
            Toast makeText = Toast.makeText(context, context.getString(R.string.net_not_wifi), 0);
            makeText.setGravity(17, 0, 0);
            makeText.show();
        }
    }

    public static String c(Activity activity, String str) {
        String str2;
        Exception e;
        String str3 = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(activity.getResources().getAssets().open(str)));
            str2 = "";
            str2 = str3;
            while (true) {
                try {
                    str3 = bufferedReader.readLine();
                    if (str3 == null) {
                        break;
                    }
                    str2 = str2 + str3;
                } catch (Exception e2) {
                    e = e2;
                }
            }
        } catch (Exception e3) {
            Exception exception = e3;
            str2 = str3;
            e = exception;
            e.printStackTrace();
            return str2;
        }
        return str2;
    }

    public static int F(Context context) {
        int i = 0;
        long longValue = ai.f(context).longValue();
        if (longValue != 0) {
            Long valueOf = Long.valueOf(System.currentTimeMillis() - longValue);
            if (valueOf.longValue() >= 604800000) {
                i = 7;
            } else if (valueOf.longValue() >= 259200000) {
                i = 3;
            } else {
                i = 1;
            }
        }
        ai.g(context);
        return i;
    }

    public static void a(Context context, Map<String, String> map, String str) {
    }

    public static String g(String str) {
        String str2 = "";
        if ("29".equals(str)) {
            return "段子";
        }
        if (com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(str)) {
            return "图片";
        }
        if ("31".equals(str)) {
            return "声音";
        }
        if ("41".equals(str)) {
            return "视频";
        }
        if ("51".equals(str)) {
            return "长文";
        }
        if ("61".equals(str)) {
            return "转载贴";
        }
        return str2;
    }

    public static void a(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int i = 0;
            for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                View view = adapter.getView(i2, null, listView);
                try {
                    view.measure(0, 0);
                } catch (Exception e) {
                    i = 0;
                }
                i += view.getMeasuredHeight();
            }
            LayoutParams layoutParams = listView.getLayoutParams();
            layoutParams.height = (listView.getDividerHeight() * (adapter.getCount() - 1)) + i;
            listView.setLayoutParams(layoutParams);
        }
    }

    public static void f(Activity activity) {
        if (VERSION.SDK_INT >= 21 && !(activity instanceof CommendDetail) && !(activity instanceof CommendDetailOld) && !(activity instanceof PublishCommentActivity) && !(activity instanceof PostDetailActivity)) {
            activity.getWindow().addFlags(67108864);
        }
    }

    public static void a(LinearLayout linearLayout) {
        if (VERSION.SDK_INT >= 21) {
            linearLayout.setVisibility(0);
        } else {
            linearLayout.setVisibility(8);
        }
    }

    public static boolean G(Context context) {
        if (VERSION.SDK_INT < 19 || !TextUtils.isEmpty(Sms.getDefaultSmsPackage(context))) {
            return true;
        }
        return false;
    }

    public static boolean p(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        if (packageInfo != null) {
            return true;
        }
        return false;
    }

    public static boolean g(Activity activity) {
        return activity == null || activity.isFinishing() || (VERSION.SDK_INT >= 17 && activity.isDestroyed());
    }

    public static String c() {
        String str = "budejie/6.9.2 (" + Build.MODEL + ") android/" + VERSION.RELEASE;
        return h(str) ? str : "";
    }

    public static boolean h(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if ((charAt <= '\u001f' && charAt != '\t') || charAt >= '') {
                return false;
            }
        }
        return true;
    }

    public static boolean d() {
        if (BudejieApplication.g == null || TextUtils.isEmpty(BudejieApplication.g.getSharedPreferences("weiboprefer", 0).getString("id", ""))) {
            return false;
        }
        return true;
    }

    public static boolean H(Context context) {
        return context == null || ai.a(context) == 0;
    }

    public static boolean i(String str) {
        return VERSION.SDK_INT < 23 || PermissionChecker.checkSelfPermission(BudejieApplication.g, str) == 0;
    }
}
