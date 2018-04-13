package com.zxt.download2;

import android.content.Intent;
import android.net.Uri;
import com.tencent.stat.DeviceInfo;
import java.io.File;
import org.apache.commons.httpclient.methods.multipart.StringPart;

public class d {
    public static Intent a(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        String toLowerCase = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
        if (toLowerCase.equals("m4a") || toLowerCase.equals("mp3") || toLowerCase.equals(DeviceInfo.TAG_MID) || toLowerCase.equals("xmf") || toLowerCase.equals("ogg") || toLowerCase.equals("wav")) {
            return e(str);
        }
        if (toLowerCase.equals("3gp") || toLowerCase.equals("mp4") || toLowerCase.equals("flv") || toLowerCase.equals("mpg") || toLowerCase.equals("rm")) {
            return d(str);
        }
        if (toLowerCase.equals("jpg") || toLowerCase.equals("gif") || toLowerCase.equals("png") || toLowerCase.equals("jpeg") || toLowerCase.equals("bmp")) {
            return f(str);
        }
        if (toLowerCase.equals("apk")) {
            return c(str);
        }
        if (toLowerCase.equals("ppt")) {
            return g(str);
        }
        if (toLowerCase.equals("xls")) {
            return h(str);
        }
        if (toLowerCase.equals("doc")) {
            return i(str);
        }
        if (toLowerCase.equals("pdf")) {
            return k(str);
        }
        if (toLowerCase.equals("chm")) {
            return j(str);
        }
        if (toLowerCase.equals("txt")) {
            return a(str, false);
        }
        return b(str);
    }

    public static Intent b(String str) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(str)), "*/*");
        return intent;
    }

    public static Intent c(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(Uri.parse("file://" + str), "application/vnd.android.package-archive");
        return intent;
    }

    public static Intent d(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(67108864);
        intent.setDataAndType(Uri.fromFile(new File(str)), "video/*");
        return intent;
    }

    public static Intent e(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(67108864);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        intent.setDataAndType(Uri.fromFile(new File(str)), "audio/*");
        return intent;
    }

    public static Intent f(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(268435456);
        intent.setDataAndType(Uri.fromFile(new File(str)), "image/*");
        return intent;
    }

    public static Intent g(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(268435456);
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.ms-powerpoint");
        return intent;
    }

    public static Intent h(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(268435456);
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.ms-excel");
        return intent;
    }

    public static Intent i(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(268435456);
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/msword");
        return intent;
    }

    public static Intent j(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(268435456);
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/x-chm");
        return intent;
    }

    public static Intent a(String str, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(268435456);
        if (z) {
            intent.setDataAndType(Uri.parse(str), StringPart.DEFAULT_CONTENT_TYPE);
        } else {
            intent.setDataAndType(Uri.fromFile(new File(str)), StringPart.DEFAULT_CONTENT_TYPE);
        }
        return intent;
    }

    public static Intent k(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(268435456);
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/pdf");
        return intent;
    }
}
