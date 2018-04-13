package com.liulishuo.filedownloader.exception;

import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FileDownloadHttpException extends RuntimeException {
    private final int a;
    private final Map<String, List<String>> b;
    private final Map<String, List<String>> c;

    public FileDownloadHttpException(int i, Map<String, List<String>> map, Map<String, List<String>> map2) {
        super(FileDownloadUtils.formatString("response code error: %d, \n request headers: %s \n response headers: %s", new Object[]{Integer.valueOf(i), map, map2}));
        this.a = i;
        this.b = a(map);
        this.c = a(map);
    }

    public Map<String, List<String>> getRequestHeader() {
        return this.b;
    }

    public Map<String, List<String>> getResponseHeader() {
        return this.c;
    }

    public int getCode() {
        return this.a;
    }

    private static Map<String, List<String>> a(Map<String, List<String>> map) {
        Map<String, List<String>> hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            hashMap.put((String) entry.getKey(), new ArrayList((Collection) entry.getValue()));
        }
        return hashMap;
    }
}
