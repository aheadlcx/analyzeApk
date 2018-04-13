package com.tencent.tinker.lib.patch;

import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class BasePatchInternal {
    protected static final String DEX_META_FILE = "assets/dex_meta.txt";
    protected static final String DEX_OPTIMIZE_PATH = "odex";
    protected static final String DEX_PATH = "dex";
    protected static final int MAX_EXTRACT_ATTEMPTS = 2;
    protected static final String RES_META_FILE = "assets/res_meta.txt";
    protected static final String SO_META_FILE = "assets/so_meta.txt";
    protected static final String SO_PATH = "lib";
    protected static final String TAG = "Tinker.BasePatchInternal";
    protected static final int TYPE_CLASS_N_DEX = 7;
    protected static final int TYPE_DEX = 3;
    protected static final int TYPE_Library = 5;
    protected static final int TYPE_RESOURCE = 6;

    public static boolean extract(ZipFile zipFile, ZipEntry zipEntry, File file, String str, boolean z) throws IOException {
        boolean z2 = false;
        int i = 0;
        while (i < 2 && !z2) {
            i++;
            Closeable bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
            Closeable bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            TinkerLog.i(TAG, "try Extracting " + file.getPath(), new Object[0]);
            try {
                byte[] bArr = new byte[16384];
                for (int read = bufferedInputStream.read(bArr); read != -1; read = bufferedInputStream.read(bArr)) {
                    bufferedOutputStream.write(bArr, 0, read);
                }
                z2 = str != null ? z ? SharePatchFileUtil.verifyDexFileMd5(file, str) : SharePatchFileUtil.verifyFileMd5(file, str) : true;
                TinkerLog.i(TAG, "isExtractionSuccessful: %b", Boolean.valueOf(z2));
                if (!z2) {
                    file.delete();
                    if (file.exists()) {
                        TinkerLog.e(TAG, "Failed to delete corrupted dex " + file.getPath(), new Object[0]);
                    }
                }
            } finally {
                SharePatchFileUtil.closeQuietly(bufferedOutputStream);
                SharePatchFileUtil.closeQuietly(bufferedInputStream);
            }
        }
        return z2;
    }

    public static int getMetaCorruptedCode(int i) {
        if (i == 3) {
            return -3;
        }
        if (i == 5) {
            return -4;
        }
        if (i == 6) {
            return -8;
        }
        return 0;
    }
}
