package com.baidu.mobads.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Base64;
import com.baidu.mobads.interfaces.utils.IXAdBitmapUtils;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

class c implements IXAdBitmapUtils {
    c() {
    }

    @TargetApi(8)
    public String bitmap2tring(Bitmap bitmap) {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (Exception e) {
            return "";
        }
    }

    @TargetApi(8)
    public Bitmap string2bitmap(String str) {
        try {
            byte[] decode = Base64.decode(str, 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            return null;
        }
    }
}
