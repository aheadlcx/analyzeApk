package com.tencent.bugly.proguard;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.beta.global.a;
import com.tencent.bugly.beta.global.e;
import com.tencent.bugly.beta.tinker.TinkerManager;
import com.tencent.bugly.beta.upgrade.BetaGrayStrategy;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;

public class r {
    public static synchronized void a(Context context) {
        synchronized (r.class) {
            e eVar = e.E;
            String absolutePath = context.getDir("dex", 0).getAbsolutePath();
            String absolutePath2 = context.getDir("tmpPatch", 32768).getAbsolutePath();
            eVar.G = new File(absolutePath, TinkerManager.PATCH_NAME);
            if (TextUtils.isEmpty(a.b("PatchFile", ""))) {
                a.a("PatchFile", eVar.G.getAbsolutePath());
            }
            eVar.H = new File(absolutePath2, "tmpPatch.apk");
            if (eVar.G != null && TextUtils.isEmpty(a.b("PatchFile", ""))) {
                a.a("PatchFile", eVar.G.getAbsolutePath());
            }
            eVar.I = context.getDir("tmpPatch", 0);
            if (eVar.I != null && TextUtils.isEmpty(a.b("PatchTmpDir", ""))) {
                a.a("PatchTmpDir", eVar.G.getAbsolutePath());
            }
            BetaGrayStrategy betaGrayStrategy = (BetaGrayStrategy) a.a("st.bch", BetaGrayStrategy.CREATOR);
            if (betaGrayStrategy == null || betaGrayStrategy.a == null || betaGrayStrategy.a.p != 3) {
                if (!(TinkerManager.getInstance().getPatchDirectory(eVar.s) == null || TinkerManager.getInstance().getPatchDirectory(eVar.s).exists())) {
                    a.a("IS_PATCH_ROLL_BACK", false);
                }
                if (a.b("IS_PATCH_ROLL_BACK", false)) {
                    TinkerManager.getInstance().onPatchRollback(false);
                }
            } else {
                if (betaGrayStrategy.a.l != null) {
                    eVar.M = (String) betaGrayStrategy.a.l.get("H2");
                    if (!TextUtils.isEmpty(eVar.M)) {
                        ap.b("G15", eVar.M);
                    }
                }
                eVar.N = a.b("PatchResult", false);
                eVar.O = Integer.valueOf(a.b("PATCH_MAX_TIMES", "0")).intValue();
                if (eVar.N) {
                    an.a("[patch] inject success", new Object[0]);
                    if (!a.b("UPLOAD_PATCH_RESULT", false)) {
                        a.a("UPLOAD_PATCH_RESULT", true);
                        if (p.a.a(new w("active", System.currentTimeMillis(), (byte) 0, 0, null, betaGrayStrategy.a.m, betaGrayStrategy.a.p, null))) {
                            an.a("save patch success event success!", new Object[0]);
                        } else {
                            an.e("save patch success event failed!", new Object[0]);
                        }
                    }
                } else {
                    an.a("[patch] inject failure", new Object[0]);
                    if (!a.b("UPLOAD_PATCH_RESULT", false)) {
                        a.a("UPLOAD_PATCH_RESULT", true);
                        if (p.a.a(new w("active", System.currentTimeMillis(), (byte) 1, 0, null, betaGrayStrategy.a.m, betaGrayStrategy.a.p, null))) {
                            an.a("save patch failed event success!", new Object[0]);
                        } else {
                            an.e("save patch failed event failed!", new Object[0]);
                        }
                        if (e.E.G != null && e.E.G.exists() && e.E.G.delete()) {
                            an.a("[patch] delete patch.apk success", new Object[0]);
                        }
                    }
                }
            }
            File file = e.E.H;
            if (file != null && file.exists() && file.delete()) {
                an.a("[patch] delete tmpPatch.apk success", new Object[0]);
            }
            file = e.E.G;
            if (file != null && file.exists()) {
                absolutePath = ap.a(file, "SHA");
                if (absolutePath != null) {
                    e.E.L = absolutePath;
                }
            }
            if (TinkerManager.isTinkerManagerInstalled()) {
                if (TextUtils.isEmpty(eVar.J)) {
                    eVar.J = TinkerManager.getTinkerId();
                }
                an.a("TINKER_ID:" + eVar.J, new Object[0]);
                eVar.K = TinkerManager.getNewTinkerId();
                an.a("NEW_TINKER_ID:" + eVar.K, new Object[0]);
                TinkerManager.getInstance().setTinkerListener(new r$1(eVar));
            }
            if (TextUtils.isEmpty(a.b("BaseArchName", ""))) {
                absolutePath = eVar.s.getApplicationInfo().nativeLibraryDir;
                if (absolutePath != null) {
                    File[] listFiles = new File(absolutePath).listFiles();
                    Object obj = null;
                    if (listFiles != null && listFiles.length > 0) {
                        int length = listFiles.length;
                        int i = 0;
                        while (i < length) {
                            Object obj2;
                            File file2 = listFiles[i];
                            if (file2.getName().endsWith(".so")) {
                                String substring;
                                absolutePath = file2.getName().replace(".so", "");
                                if (absolutePath.startsWith(ShareConstants.SO_PATH)) {
                                    substring = absolutePath.substring(absolutePath.indexOf(ShareConstants.SO_PATH) + 3);
                                } else {
                                    substring = absolutePath;
                                }
                                an.a("libName:" + substring, new Object[0]);
                                String absolutePath3 = file2.getAbsolutePath();
                                an.a("soFilePath:" + absolutePath3, new Object[0]);
                                for (String absolutePath4 : eVar.Z) {
                                    if (substring.equals(absolutePath4)) {
                                        obj2 = 1;
                                        break;
                                    }
                                }
                                obj2 = obj;
                                if (obj2 == null) {
                                    obj2 = null;
                                    absolutePath2 = a.b(absolutePath3);
                                    an.a("archName:" + absolutePath2, new Object[0]);
                                    if (absolutePath2 != null && absolutePath2.equals("armeabi-v5te")) {
                                        absolutePath2 = "armeabi";
                                    }
                                    a.a(substring, absolutePath2);
                                    if (TextUtils.isEmpty(a.b("BaseArchName", ""))) {
                                        a.a("BaseArchName", absolutePath2);
                                    }
                                }
                            } else {
                                obj2 = obj;
                            }
                            i++;
                            obj = obj2;
                        }
                    }
                }
            }
        }
    }
}
