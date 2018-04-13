package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;

public class d {
    public static final String a = "/data/data/";
    public static final String b = "/databases/cc/";
    public static final String c = "cc.db";
    public static final int d = 1;
    public static final String e = "Id";
    public static final String f = "INTEGER";

    public static class a {
        public static final String a = "aggregated";
        public static final String b = "aggregated_cache";

        public static class a {
            public static final String a = "key";
            public static final String b = "totalTimestamp";
            public static final String c = "value";
            public static final String d = "count";
            public static final String e = "label";
            public static final String f = "timeWindowNum";
        }

        public static class b {
            public static final String a = "TEXT";
            public static final String b = "TEXT";
            public static final String c = "INTEGER";
            public static final String d = "INTEGER";
            public static final String e = "TEXT";
            public static final String f = "TEXT";
        }
    }

    public static class b {
        public static final String a = "limitedck";

        public static class a {
            public static final String a = "ck";
        }

        public static class b {
            public static final String a = "TEXT";
        }
    }

    public static class c {
        public static final String a = "system";

        public static class a {
            public static final String a = "key";
            public static final String b = "timeStamp";
            public static final String c = "count";
            public static final String d = "label";
        }

        public static class b {
            public static final String a = "TEXT";
            public static final String b = "INTEGER";
            public static final String c = "INTEGER";
            public static final String d = "TEXT";
        }
    }

    public static String a(Context context) {
        return "/data/data/" + context.getPackageName() + b;
    }

    public static String a(List<String> list) {
        return TextUtils.join("!", list);
    }

    public static JSONArray a(String str) {
        String[] split = str.split("!");
        JSONArray jSONArray = new JSONArray();
        for (Object put : split) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    public static List<String> b(String str) {
        return new ArrayList(Arrays.asList(str.split("!")));
    }

    public static List<String> b(List<String> list) {
        List<String> arrayList = new ArrayList();
        try {
            for (String str : list) {
                if (Collections.frequency(arrayList, str) < 1) {
                    arrayList.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
