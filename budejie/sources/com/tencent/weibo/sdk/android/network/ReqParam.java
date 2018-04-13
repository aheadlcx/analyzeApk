package com.tencent.weibo.sdk.android.network;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.util.Log;
import com.ali.auth.third.login.LoginConstants;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReqParam {
    public Bitmap mBitmap = null;
    private Map<String, String> mParams = new HashMap();

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public Map<String, String> getmParams() {
        return this.mParams;
    }

    public void setmParams(Map<String, String> map) {
        this.mParams = map;
    }

    public void addParam(String str, String str2) {
        this.mParams.put(str, str2);
    }

    public void addParam(String str, byte[] bArr) {
        double length = (double) (bArr.length / 1024);
        if (length > 400.0d) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            double d = length / 400.0d;
            zoomImage(this.mBitmap, ((double) this.mBitmap.getWidth()) / Math.sqrt(d), ((double) this.mBitmap.getHeight()) / Math.sqrt(d)).compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
            bArr = byteArrayOutputStream.toByteArray();
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append((char) b);
        }
        Log.d("buffer=======", stringBuffer.toString());
        this.mParams.put(str, stringBuffer.toString());
    }

    public void addParam(String str, Object obj) {
        this.mParams.put(str, obj.toString());
    }

    public String toString() {
        List<String> arrayList = new ArrayList();
        for (String add : this.mParams.keySet()) {
            arrayList.add(add);
        }
        Collections.sort(arrayList, new Comparator<String>() {
            public int compare(String str, String str2) {
                if (str.compareTo(str2) > 0) {
                    return 1;
                }
                if (str.compareTo(str2) < 0) {
                    return -1;
                }
                return 0;
            }
        });
        StringBuffer stringBuffer = new StringBuffer();
        for (String add2 : arrayList) {
            if (!add2.equals("pic")) {
                stringBuffer.append(add2);
                stringBuffer.append(LoginConstants.EQUAL);
                stringBuffer.append((String) this.mParams.get(add2));
                stringBuffer.append("&");
            }
        }
        Log.d("p-----", stringBuffer.toString());
        return stringBuffer.toString().replaceAll("\n", "").replaceAll("\r", "");
    }

    public Bitmap zoomImage(Bitmap bitmap, double d, double d2) {
        float width = (float) bitmap.getWidth();
        float height = (float) bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) d) / width, ((float) d2) / height);
        return Bitmap.createBitmap(bitmap, 0, 0, (int) width, (int) height, matrix, true);
    }
}
