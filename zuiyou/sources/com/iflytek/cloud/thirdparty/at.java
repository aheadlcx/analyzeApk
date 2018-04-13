package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.text.TextUtils;
import java.io.File;

public class at {

    public enum a {
        assets,
        res,
        path
    }

    public static String a(Context context, a aVar, String str) {
        return aVar == a.path ? a(str) : b(context, aVar, str);
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        return "fo|" + str + "|" + 0 + "|" + file.length();
    }

    private static String b(Context context, a aVar, String str) {
        AssetFileDescriptor openFd;
        Exception e;
        Throwable th;
        String str2 = null;
        if (!(TextUtils.isEmpty(str) || context == null)) {
            String packageResourcePath = context.getPackageResourcePath();
            try {
                long startOffset;
                long length;
                if (aVar == a.assets) {
                    openFd = context.getAssets().openFd(str);
                    try {
                        startOffset = openFd.getStartOffset();
                        length = openFd.getLength();
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            if (openFd != null) {
                                try {
                                    openFd.close();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            return str2;
                        } catch (Throwable th2) {
                            th = th2;
                            if (openFd != null) {
                                try {
                                    openFd.close();
                                } catch (Exception e32) {
                                    e32.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                }
                openFd = context.getResources().openRawResourceFd(Integer.valueOf(str).intValue());
                startOffset = openFd.getStartOffset();
                length = openFd.getLength();
                str2 = "fo|" + packageResourcePath + "|" + startOffset + "|" + length;
                if (openFd != null) {
                    try {
                        openFd.close();
                    } catch (Exception e322) {
                        e322.printStackTrace();
                    }
                }
            } catch (Exception e4) {
                e322 = e4;
                openFd = null;
                e322.printStackTrace();
                if (openFd != null) {
                    openFd.close();
                }
                return str2;
            } catch (Throwable th3) {
                openFd = null;
                th = th3;
                if (openFd != null) {
                    openFd.close();
                }
                throw th;
            }
        }
        return str2;
    }
}
