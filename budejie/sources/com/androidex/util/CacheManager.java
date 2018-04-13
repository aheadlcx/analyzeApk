package com.androidex.util;

import android.os.StatFs;
import android.os.SystemClock;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

public class CacheManager {
    private CacheManager() {
    }

    public static void deleteOldestFile(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            Arrays.sort(listFiles, new Comparator<File>() {
                public int compare(File file, File file2) {
                    return Long.valueOf(file.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
                }
            });
            listFiles[0].delete();
            System.out.printf("delete catch file %s", new Object[]{listFiles[0].getName()});
        }
    }

    public static long getDirSize(File file) {
        long j = 0;
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    j += getDirSize(listFiles[i]);
                } else {
                    j += listFiles[i].length();
                }
            }
        }
        return j;
    }

    public static void pruneOldestFileIfNeeded(File file, long j) {
        long dirSize = getDirSize(file);
        System.out.printf("cache size %d, maxCacheSize %d", new Object[]{Long.valueOf(dirSize), Long.valueOf(j)});
        while (dirSize > j) {
            deleteOldestFile(file);
            dirSize = getDirSize(file);
        }
    }

    public static void pruneIfNeeded(File file, long j) {
        System.out.printf("pruneIfNeeded dirSize %d ", new Object[]{Long.valueOf(getDirSize(file))});
        if (getDirSize(file) > j) {
            deleteFiles(file);
        }
    }

    public static void deleteFiles(File file) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        File[] listFiles = file.listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return !str.endsWith("ing");
            }
        });
        long j = 0;
        for (File file2 : listFiles) {
            long length = file2.length();
            if (file2.delete()) {
                j += length;
            }
        }
        System.out.printf("pruned %d files, %d bytes, %d ms", new Object[]{Integer.valueOf(listFiles.length), Long.valueOf(j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime)});
    }

    public static boolean checkDirSpace(File file, long j, long j2) {
        if (file == null || !file.exists()) {
            return false;
        }
        StatFs statFs = new StatFs(file.getPath());
        if (((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks()) < (j - getDirSize(file)) + j2) {
            return true;
        }
        return false;
    }

    public static void removeTempFile(File file) {
        if (file != null && file.exists()) {
            File[] listFiles = file.listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return true;
                }
            });
            if (listFiles != null && listFiles.length > 0) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }
    }
}
