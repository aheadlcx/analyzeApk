package com.tencent.tinker.loader.shareutil;

import com.tencent.tinker.loader.TinkerRuntimeException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;

public class ShareResPatchInfo {
    public ArrayList<String> addRes = new ArrayList();
    public String arscBaseCrc = null;
    public ArrayList<String> deleteRes = new ArrayList();
    public HashMap<String, LargeModeInfo> largeModMap = new HashMap();
    public ArrayList<String> largeModRes = new ArrayList();
    public ArrayList<String> modRes = new ArrayList();
    public HashSet<Pattern> patterns = new HashSet();
    public String resArscMd5 = null;
    public HashMap<String, File> storeRes = new HashMap();

    public static class LargeModeInfo {
        public long crc;
        public File file = null;
        public String md5 = null;
    }

    public static void parseAllResPatchInfo(String str, ShareResPatchInfo shareResPatchInfo) {
        if (str != null && str.length() != 0) {
            String[] split = str.split("\n");
            int i = 0;
            while (i < split.length) {
                String str2 = split[i];
                if (str2 != null && str2.length() > 0) {
                    if (str2.startsWith(ShareConstants.RES_TITLE)) {
                        String[] split2 = str2.split(",", 3);
                        shareResPatchInfo.arscBaseCrc = split2[1];
                        shareResPatchInfo.resArscMd5 = split2[2];
                    } else if (str2.startsWith(ShareConstants.RES_PATTERN_TITLE)) {
                        r2 = Integer.parseInt(str2.split(":", 2)[1]);
                        while (r2 > 0) {
                            shareResPatchInfo.patterns.add(convertToPatternString(split[i + 1]));
                            r2--;
                            i++;
                        }
                    } else if (str2.startsWith(ShareConstants.RES_ADD_TITLE)) {
                        r2 = Integer.parseInt(str2.split(":", 2)[1]);
                        while (r2 > 0) {
                            shareResPatchInfo.addRes.add(split[i + 1]);
                            r2--;
                            i++;
                        }
                    } else if (str2.startsWith(ShareConstants.RES_MOD_TITLE)) {
                        r2 = Integer.parseInt(str2.split(":", 2)[1]);
                        while (r2 > 0) {
                            shareResPatchInfo.modRes.add(split[i + 1]);
                            r2--;
                            i++;
                        }
                    } else if (str2.startsWith(ShareConstants.RES_LARGE_MOD_TITLE)) {
                        r2 = Integer.parseInt(str2.split(":", 2)[1]);
                        while (r2 > 0) {
                            String[] split3 = split[i + 1].split(",", 3);
                            Object obj = split3[0];
                            LargeModeInfo largeModeInfo = new LargeModeInfo();
                            largeModeInfo.md5 = split3[1];
                            largeModeInfo.crc = Long.parseLong(split3[2]);
                            shareResPatchInfo.largeModRes.add(obj);
                            shareResPatchInfo.largeModMap.put(obj, largeModeInfo);
                            r2--;
                            i++;
                        }
                    } else if (str2.startsWith(ShareConstants.RES_DEL_TITLE)) {
                        r2 = Integer.parseInt(str2.split(":", 2)[1]);
                        while (r2 > 0) {
                            shareResPatchInfo.deleteRes.add(split[i + 1]);
                            r2--;
                            i++;
                        }
                    } else if (str2.startsWith(ShareConstants.RES_STORE_TITLE)) {
                        r2 = Integer.parseInt(str2.split(":", 2)[1]);
                        while (r2 > 0) {
                            shareResPatchInfo.storeRes.put(split[i + 1], null);
                            r2--;
                            i++;
                        }
                    }
                }
                i++;
            }
        }
    }

    public static boolean checkFileInPattern(HashSet<Pattern> hashSet, String str) {
        if (!hashSet.isEmpty()) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                if (((Pattern) it.next()).matcher(str).matches()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkResPatchInfo(ShareResPatchInfo shareResPatchInfo) {
        if (shareResPatchInfo == null) {
            return false;
        }
        String str = shareResPatchInfo.resArscMd5;
        if (str == null || str.length() != 32) {
            return false;
        }
        return true;
    }

    private static Pattern convertToPatternString(String str) {
        if (str.contains(".")) {
            str = str.replaceAll("\\.", "\\\\.");
        }
        if (str.contains("?")) {
            str = str.replaceAll("\\?", "\\.");
        }
        if (str.contains("*")) {
            str = str.replace("*", ".*");
        }
        return Pattern.compile(str);
    }

    public static void parseResPatchInfoFirstLine(String str, ShareResPatchInfo shareResPatchInfo) {
        if (str != null && str.length() != 0) {
            String str2 = str.split("\n")[0];
            if (str2 == null || str2.length() <= 0) {
                throw new TinkerRuntimeException("res meta Corrupted:" + str);
            }
            String[] split = str2.split(",", 3);
            shareResPatchInfo.arscBaseCrc = split[1];
            shareResPatchInfo.resArscMd5 = split[2];
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("resArscMd5:" + this.resArscMd5 + "\n");
        stringBuffer.append("arscBaseCrc:" + this.arscBaseCrc + "\n");
        Iterator it = this.patterns.iterator();
        while (it.hasNext()) {
            stringBuffer.append(ShareConstants.RES_PATTERN_TITLE + ((Pattern) it.next()) + "\n");
        }
        it = this.addRes.iterator();
        while (it.hasNext()) {
            stringBuffer.append("addedSet:" + ((String) it.next()) + "\n");
        }
        it = this.modRes.iterator();
        while (it.hasNext()) {
            stringBuffer.append("modifiedSet:" + ((String) it.next()) + "\n");
        }
        it = this.largeModRes.iterator();
        while (it.hasNext()) {
            stringBuffer.append("largeModifiedSet:" + ((String) it.next()) + "\n");
        }
        it = this.deleteRes.iterator();
        while (it.hasNext()) {
            stringBuffer.append("deletedSet:" + ((String) it.next()) + "\n");
        }
        for (String str : this.storeRes.keySet()) {
            stringBuffer.append("storeSet:" + str + "\n");
        }
        return stringBuffer.toString();
    }
}
