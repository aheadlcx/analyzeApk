package com.iflytek.cloud.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.text.TextUtils;
import com.iflytek.cloud.thirdparty.cb;
import java.io.File;

public class ResourceUtil {
    public static final String ASR_RES_PATH = "asr_res_path";
    public static final String ENGINE_DESTROY = "engine_destroy";
    public static final String ENGINE_START = "engine_start";
    public static final String GRM_BUILD_PATH = "grm_build_path";
    public static final String IVW_RES_PATH = "ivw_res_path";
    public static final String TTS_RES_PATH = "tts_res_path";

    public enum RESOURCE_TYPE {
        assets,
        res,
        path
    }

    private static String a(Context context, RESOURCE_TYPE resource_type, String str) {
        Throwable e;
        Throwable th;
        String str2 = null;
        if (!(TextUtils.isEmpty(str) || context == null)) {
            String packageResourcePath = context.getPackageResourcePath();
            AssetFileDescriptor openFd;
            try {
                long startOffset;
                long length;
                if (resource_type == RESOURCE_TYPE.assets) {
                    openFd = context.getAssets().openFd(str);
                    try {
                        startOffset = openFd.getStartOffset();
                        length = openFd.getLength();
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            cb.a(e);
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
                e = e4;
                openFd = null;
                cb.a(e);
                if (openFd != null) {
                    openFd.close();
                }
                return str2;
            } catch (Throwable e5) {
                openFd = null;
                th = e5;
                if (openFd != null) {
                    openFd.close();
                }
                throw th;
            }
        }
        return str2;
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

    public static String generateResourcePath(Context context, RESOURCE_TYPE resource_type, String str) {
        return resource_type == RESOURCE_TYPE.path ? a(str) : a(context, resource_type, str);
    }
}
