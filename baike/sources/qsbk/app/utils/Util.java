package qsbk.app.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.support.v4.util.LruCache;
import android.support.v4.util.Pair;
import android.text.ClipboardManager;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.baidu.mobstat.Config;
import com.facebook.common.util.UriUtil;
import cz.msebera.android.httpclient.message.TokenParser;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Pattern;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.QsbkApp;
import qsbk.app.ad.feedsad.FeedsAdUtils;
import qsbk.app.ad.feedsad.qbad.QbAdApkDownloader;
import qsbk.app.core.utils.Md5Utils;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.ReportAdForMedalUtil.AD_TYPE;

public class Util {
    static final Pattern a = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
    static boolean[] b = new boolean[2000];
    private static Random c;
    private static LruCache<String, StaticLayout> d = new LruCache(50);
    public static float density = 1.0f;
    public static Point displaySize = new Point();
    public static int statusBarHeight = 0;

    public static int dp(float f) {
        return (int) Math.ceil((double) (density * f));
    }

    public static int getViewInset(View view) {
        if (view == null || VERSION.SDK_INT < 21) {
            return 0;
        }
        try {
            Field declaredField = View.class.getDeclaredField("mAttachInfo");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(view);
            if (obj != null) {
                Field declaredField2 = obj.getClass().getDeclaredField("mStableInsets");
                declaredField2.setAccessible(true);
                return ((Rect) declaredField2.get(obj)).bottom;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    @SuppressLint({"NewApi"})
    public static void checkDisplaySize(Context context) {
        try {
            WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
            if (windowManager != null) {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                if (defaultDisplay == null) {
                    return;
                }
                if (VERSION.SDK_INT < 13) {
                    displaySize.set(defaultDisplay.getWidth(), defaultDisplay.getHeight());
                } else {
                    defaultDisplay.getSize(displaySize);
                }
            }
        } catch (Exception e) {
        }
    }

    public static boolean containsStamp(float f, float f2, View view) {
        Float valueOf = Float.valueOf(f);
        Float valueOf2 = Float.valueOf(f2);
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        if (rect.contains(valueOf.intValue(), valueOf2.intValue())) {
            return true;
        }
        return false;
    }

    public static int getStatusBarHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    public static int getRandom() {
        if (c == null) {
            c = new Random(System.currentTimeMillis());
        }
        return c.nextInt(2000);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int i = 0;
            for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                View view = adapter.getView(i2, null, listView);
                view.measure(0, 0);
                i += view.getMeasuredHeight();
            }
            LayoutParams layoutParams = listView.getLayoutParams();
            layoutParams.height = (listView.getDividerHeight() * (adapter.getCount() - 1)) + i;
            listView.setLayoutParams(layoutParams);
        }
    }

    public static boolean isLongImage(int i, int i2) {
        return (i == 0 || i2 == 0 || i2 <= (i << 1)) ? false : true;
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equals(obj2);
        }
    }

    public static Context getActivityOrContext(View view) {
        if (view == null) {
            return QsbkApp.getInstance();
        }
        Context context = view.getContext();
        while ((context instanceof ContextWrapper) && !(context instanceof Activity)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context == null) {
            return view.getContext();
        }
        return context;
    }

    public static boolean isValidPwd(String str) {
        return a.matcher(str).matches();
    }

    public static String formatMoney(double d) {
        return new BigDecimal(d).setScale(2, 5).toString();
    }

    public static String formatMoney(String str) {
        return new BigDecimal(str).setScale(2, 5).toString();
    }

    public static String formatMoneyWithCommas(double d) {
        return new DecimalFormat("##,##,##,##,##,##,##0.00").format(d);
    }

    public static String formatMoneyWithCommas(String str) {
        try {
            return formatMoneyWithCommas(Double.parseDouble(str));
        } catch (Exception e) {
            e.printStackTrace();
            return "...";
        }
    }

    public static String getRealPathFromUri(ContentResolver contentResolver, Uri uri) {
        String[] strArr;
        String str;
        Uri withAppendedId;
        Cursor query;
        int columnIndexOrThrow;
        if (VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(QsbkApp.getInstance(), uri)) {
            if (isExternalStorageDocument(uri)) {
                return Environment.getExternalStorageDirectory() + MqttTopic.TOPIC_LEVEL_SEPARATOR + DocumentsContract.getDocumentId(uri).split(Config.TRACE_TODAY_VISIT_SPLIT)[1];
            }
            if (isDownloadsDocument(uri)) {
                strArr = null;
                str = null;
                withAppendedId = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue());
            } else if (isMediaDocument(uri)) {
                Object obj = DocumentsContract.getDocumentId(uri).split(Config.TRACE_TODAY_VISIT_SPLIT)[0];
                if ("image".equals(obj)) {
                    uri = Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(obj)) {
                    uri = Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(obj)) {
                    uri = Audio.Media.EXTERNAL_CONTENT_URI;
                }
                str = "_id=?";
                strArr = new String[]{r0[1]};
                withAppendedId = uri;
            }
            if ("content".equalsIgnoreCase(withAppendedId.getScheme())) {
                try {
                    query = contentResolver.query(withAppendedId, new String[]{"_data"}, str, strArr, null);
                    columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
                    if (query.moveToFirst()) {
                        return null;
                    }
                    return query.getString(columnIndexOrThrow);
                } catch (Exception e) {
                    return null;
                }
            } else if (UriUtil.LOCAL_FILE_SCHEME.equalsIgnoreCase(withAppendedId.getScheme())) {
                return null;
            } else {
                return withAppendedId.getPath();
            }
        }
        strArr = null;
        str = null;
        withAppendedId = uri;
        if ("content".equalsIgnoreCase(withAppendedId.getScheme())) {
            query = contentResolver.query(withAppendedId, new String[]{"_data"}, str, strArr, null);
            columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
            if (query.moveToFirst()) {
                return null;
            }
            return query.getString(columnIndexOrThrow);
        } else if (UriUtil.LOCAL_FILE_SCHEME.equalsIgnoreCase(withAppendedId.getScheme())) {
            return null;
        } else {
            return withAppendedId.getPath();
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static final String emotionTrim(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length() - 1;
        char[] toCharArray = str.toCharArray();
        int i = 0;
        while (i <= length && toCharArray[i] <= TokenParser.SP && toCharArray[i] != '\u0001') {
            i++;
        }
        int i2 = length;
        while (i2 >= i && toCharArray[i2] <= TokenParser.SP) {
            i2--;
        }
        return (i == 0 && i2 == length) ? str : new String(toCharArray, i, (i2 - i) + 1);
    }

    public static boolean joinQQGroup(Context context, String str) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + str));
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            ToastAndDialog.makeText(context, "请安装qq后再试").show();
            return false;
        }
    }

    public static boolean checkSelfSign(Context context) {
        boolean z = false;
        if (context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
                if (packageInfo != null) {
                    Signature signature = packageInfo.signatures[0];
                    if (signature != null) {
                        z = "C7F96C2C4E6CCEC908F284CB37BB23E4".equalsIgnoreCase(Md5Utils.encryptMD5(signature.toByteArray()));
                    }
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    public static Pair<Boolean, String> ellipseText(String str, TextPaint textPaint, int i, int i2, String str2) {
        if (textPaint == null || i <= 0) {
            return new Pair(Boolean.valueOf(false), null);
        }
        String str3 = i;
        StaticLayout staticLayout = (StaticLayout) d.get(str3);
        if (staticLayout == null) {
            staticLayout = new StaticLayout(str, textPaint, i, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            d.put(str3, staticLayout);
        }
        if (staticLayout.getLineCount() <= i2) {
            return new Pair(Boolean.FALSE, str);
        }
        int offsetForHorizontal;
        int i3 = i2 - 1;
        float[] fArr = new float[1];
        textPaint.breakText(str2, true, (float) i, fArr);
        float f = fArr[0];
        int lineEnd = staticLayout.getLineEnd(i3) - 1;
        if (staticLayout.getLineWidth(i3) + f > ((float) i)) {
            offsetForHorizontal = staticLayout.getOffsetForHorizontal(i3, ((float) i) - f) - 1;
        } else {
            offsetForHorizontal = lineEnd;
        }
        return new Pair(Boolean.TRUE, str.substring(0, offsetForHorizontal) + str2);
    }

    public static Pair<Boolean, String> ellipseText(String str, TextPaint textPaint, Layout layout, int i, int i2, String str2) {
        if (textPaint == null || layout == null || i <= 0) {
            return new Pair(Boolean.valueOf(false), null);
        }
        if (layout.getLineCount() < i2) {
            return new Pair(Boolean.FALSE, str);
        }
        int i3 = i2 - 1;
        float[] fArr = new float[1];
        textPaint.breakText(str2, true, (float) i, fArr);
        float f = fArr[0];
        int lineEnd = layout.getLineEnd(i3) - 1;
        if (layout.getLineWidth(i3) + f > ((float) i)) {
            lineEnd = layout.getOffsetForHorizontal(i3, ((float) i) - f) - 1;
        }
        return new Pair(Boolean.TRUE, str.substring(0, lineEnd) + str2);
    }

    public static Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (!(drawable instanceof LayerDrawable)) {
            return null;
        }
        LayerDrawable layerDrawable = (LayerDrawable) drawable;
        return getBitmapFromDrawable(layerDrawable.getDrawable(layerDrawable.getNumberOfLayers() - 1));
    }

    public static void doDownloadAPK(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            String substring;
            ReportAdForMedalUtil.report(AD_PROVIDER.QIUBAI, AD_TYPE.DOWNLOAD);
            int indexOf = str.indexOf(63);
            if (indexOf != -1) {
                substring = str.substring(0, indexOf);
            } else {
                substring = str;
            }
            if (substring.endsWith(".apk")) {
                QbAdApkDownloader.instance().downloadFile(context, str, "应用");
                return;
            }
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }
    }

    public static void downloadAPKwithDialog(Activity activity, String str) {
        if (activity != null) {
            String network = HttpUtils.getNetwork(activity);
            if (FeedsAdUtils.needShowConfirm(network)) {
                new Builder(activity).setTitle("温馨提示").setCancelable(true).setMessage("当前为" + network + "网络，开始下载应用？").setNegativeButton("取消", new bo()).setPositiveButton("确认", new bn(activity, str)).create().show();
            } else {
                doDownloadAPK(activity, str);
            }
        }
    }

    public static void copyContent(Context context, String str) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
        if (clipboardManager != null) {
            clipboardManager.setText(str);
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已复制到剪贴板", Integer.valueOf(0)).show();
        }
    }

    public static Bitmap textAsBitmap(String str, float f, int i, int i2, int i3, int i4) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        TextPaint textPaint = new TextPaint(1);
        textPaint.setTextSize(f);
        textPaint.setColor(i);
        textPaint.setTextAlign(Align.LEFT);
        StaticLayout staticLayout = new StaticLayout(str, textPaint, i3, Alignment.ALIGN_NORMAL, 1.4f, 2.0f, false);
        Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(i2);
        staticLayout.draw(canvas);
        return createBitmap;
    }

    public static byte[] bitmap2ByteArray(Bitmap bitmap) {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, 95, byteArrayOutputStream);
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            return toByteArray;
        } finally {
            bitmap.recycle();
        }
    }
}
