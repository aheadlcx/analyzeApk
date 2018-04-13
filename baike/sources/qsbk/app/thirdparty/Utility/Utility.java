package qsbk.app.thirdparty.Utility;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import com.alipay.sdk.sys.a;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import qsbk.app.thirdparty.ThirdPartyParameters;

public class Utility {
    private static char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static byte[] b = new byte[256];

    public static Bundle parseUrl(String str) {
        try {
            URL url = new URL(str);
            Bundle decodeUrl = decodeUrl(url.getQuery());
            decodeUrl.putAll(decodeUrl(url.getRef()));
            return decodeUrl;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }

    public static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split(a.b)) {
                String[] split2 = split.split("=");
                bundle.putString(URLDecoder.decode(split2[0]), URLDecoder.decode(split2[1]));
            }
        }
        return bundle;
    }

    public static String encodeUrl(ThirdPartyParameters thirdPartyParameters) {
        if (thirdPartyParameters == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (int i = 0; i < thirdPartyParameters.size(); i++) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(a.b);
            }
            String key = thirdPartyParameters.getKey(i);
            if (thirdPartyParameters.getValue(key) == null) {
                Log.i("encodeUrl", "key:" + key + " 's value is null");
            } else {
                stringBuilder.append(URLEncoder.encode(thirdPartyParameters.getKey(i)) + "=" + URLEncoder.encode(thirdPartyParameters.getValue(i)));
            }
        }
        return stringBuilder.toString();
    }

    public static String encodeParameters(ThirdPartyParameters thirdPartyParameters) {
        int i = 0;
        if (thirdPartyParameters == null || a(thirdPartyParameters)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = 0;
        while (i < thirdPartyParameters.size()) {
            String key = thirdPartyParameters.getKey(i);
            if (i2 != 0) {
                stringBuilder.append(a.b);
            }
            try {
                stringBuilder.append(URLEncoder.encode(key, "UTF-8")).append("=").append(URLEncoder.encode(thirdPartyParameters.getValue(key), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
            }
            i2++;
            i++;
        }
        return stringBuilder.toString();
    }

    public static void showAlert(Context context, String str, String str2) {
        Builder builder = new Builder(context);
        builder.setTitle(str);
        builder.setMessage(str2);
        builder.create().show();
    }

    private static boolean a(ThirdPartyParameters thirdPartyParameters) {
        if (thirdPartyParameters == null || thirdPartyParameters.size() == 0) {
            return true;
        }
        return false;
    }

    public static String encodeBase62(byte[] bArr) {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        int i2 = 0;
        int i3 = 0;
        while (i < bArr.length) {
            i2 = (i2 << 8) | (bArr[i] & 255);
            i3 += 8;
            while (i3 > 5) {
                int i4 = i3 - 6;
                char c = a[i2 >> i4];
                Object valueOf = c == 'i' ? "ia" : c == '+' ? "ib" : c == '/' ? "ic" : Character.valueOf(c);
                stringBuffer.append(valueOf);
                i2 &= (1 << i4) - 1;
                i3 = i4;
            }
            i++;
        }
        if (i3 > 0) {
            char c2 = a[i2 << (6 - i3)];
            Object valueOf2 = c2 == 'i' ? "ia" : c2 == '+' ? "ib" : c2 == '/' ? "ic" : Character.valueOf(c2);
            stringBuffer.append(valueOf2);
        }
        return stringBuffer.toString();
    }

    public static byte[] decodeBase62(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        char[] toCharArray = str.toCharArray();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(str.toCharArray().length);
        int i2 = 0;
        int i3 = 0;
        while (i < toCharArray.length) {
            int i4;
            char c = toCharArray[i];
            char c2;
            if (c == 'i') {
                i4 = i + 1;
                c2 = toCharArray[i4];
                if (c2 == 'a') {
                    i = 105;
                } else if (c2 == 'b') {
                    i = 43;
                } else if (c2 == 'c') {
                    i = 47;
                } else {
                    i4--;
                    i = toCharArray[i4];
                }
            } else {
                char c3 = c;
                i4 = i;
                c2 = c3;
            }
            i2 = (i2 << 6) | b[i];
            i3 += 6;
            while (i3 > 7) {
                i3 -= 8;
                byteArrayOutputStream.write(i2 >> i3);
                i2 &= (1 << i3) - 1;
            }
            i = i4 + 1;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static boolean isWifi(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }
}
