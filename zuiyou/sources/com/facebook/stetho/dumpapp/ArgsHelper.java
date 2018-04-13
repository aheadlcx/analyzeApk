package com.facebook.stetho.dumpapp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArgsHelper {
    public static String nextOptionalArg(Iterator<String> it, String str) {
        return it.hasNext() ? (String) it.next() : str;
    }

    public static String nextArg(Iterator<String> it, String str) throws DumpUsageException {
        if (it.hasNext()) {
            return (String) it.next();
        }
        throw new DumpUsageException(str);
    }

    public static String[] drainToArray(Iterator<String> it) {
        List arrayList = new ArrayList();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
