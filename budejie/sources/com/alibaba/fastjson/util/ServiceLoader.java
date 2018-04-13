package com.alibaba.fastjson.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class ServiceLoader {
    private static final String PREFIX = "META-INF/services/";
    private static final Set<String> loadedUrls = new HashSet();

    public static <T> Set<T> load(Class<T> cls, ClassLoader classLoader) {
        if (classLoader == null) {
            return Collections.emptySet();
        }
        Set<T> hashSet = new HashSet();
        String str = PREFIX + cls.getName();
        Set<String> hashSet2 = new HashSet();
        try {
            Enumeration resources = classLoader.getResources(str);
            while (resources.hasMoreElements()) {
                URL url = (URL) resources.nextElement();
                if (!loadedUrls.contains(url.toString())) {
                    load(url, (Set) hashSet2);
                    loadedUrls.add(url.toString());
                }
            }
        } catch (IOException e) {
        }
        for (String str2 : hashSet2) {
            try {
                hashSet.add(classLoader.loadClass(str2).newInstance());
            } catch (Exception e2) {
            }
        }
        return hashSet;
    }

    public static void load(URL url, Set<String> set) throws IOException {
        Closeable bufferedReader;
        Throwable th;
        Closeable closeable = null;
        try {
            Closeable openStream = url.openStream();
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(openStream, "utf-8"));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        IOUtils.close(bufferedReader);
                        IOUtils.close(openStream);
                        return;
                    }
                    try {
                        int indexOf = readLine.indexOf(35);
                        if (indexOf >= 0) {
                            readLine = readLine.substring(0, indexOf);
                        }
                        readLine = readLine.trim();
                        if (readLine.length() != 0) {
                            set.add(readLine);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        closeable = openStream;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                closeable = openStream;
                IOUtils.close(bufferedReader);
                IOUtils.close(closeable);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            IOUtils.close(bufferedReader);
            IOUtils.close(closeable);
            throw th;
        }
    }
}
