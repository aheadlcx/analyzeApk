package com.budejie.www.http;

import android.text.TextUtils;
import com.budejie.www.bean.DraftBean;
import com.budejie.www.util.aj;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class e {
    public static j a(String str, Map<String, String> map) {
        String str2;
        List<String> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        StringBuilder stringBuilder = new StringBuilder(str);
        for (String str22 : arrayList) {
            String str3 = (String) map.get(str22);
            StringBuilder append = stringBuilder.append(str22);
            if (str3 == null) {
                str3 = "";
            }
            append.append(str3);
        }
        stringBuilder.append("salt");
        j jVar = new j();
        for (Entry entry : map.entrySet()) {
            str3 = (String) entry.getValue();
            str22 = (String) entry.getKey();
            if (str3 == null) {
                str3 = "";
            }
            jVar.d(str22, str3);
        }
        str22 = aj.a(stringBuilder.toString());
        if (!TextUtils.isEmpty(str22)) {
            str3 = "hash";
            if (str22.length() > 4) {
                str22 = str22.substring(0, 4);
            }
            jVar.d(str3, str22);
        }
        return jVar;
    }

    public static j a(String str, Map<String, String> map, File file, File file2, File file3) {
        j a = a(str, map);
        if (file != null) {
            try {
                a.a(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY, file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (file2 != null) {
            a.a("voice", file2);
        }
        if (file3 != null) {
            a.a("video", file3);
        }
        return a;
    }

    public static j a(String str, Map<String, String> map, File file, File file2) {
        j a = a(str, map);
        try {
            a.a("bimage", file);
            a.a("voice", file2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return a;
    }

    public static j a(String str, Map<String, String> map, String str2) {
        j a = a(str, map);
        try {
            if (!TextUtils.isEmpty(str2)) {
                a.a(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY, new File(str2));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return a;
    }

    public static j b(String str, Map<String, String> map, File file, File file2) {
        j a = a(str, map);
        try {
            a.a("bimage", file);
            a.a("video", file2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return a;
    }

    public static j a(String str, Map<String, String> map, DraftBean draftBean) {
        if (TextUtils.isEmpty(draftBean.bimage)) {
            map.put("type", "29");
        } else if (!TextUtils.isEmpty(draftBean.voice)) {
            map.put("type", "31");
        } else if (TextUtils.isEmpty(draftBean.video)) {
            map.put("type", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
            if (!draftBean.isGif) {
                if (draftBean.isWatermark) {
                    map.put("waterprint", "1");
                } else {
                    map.put("waterprint", "0");
                }
            }
        } else {
            map.put("type", "41");
        }
        j a = a(str, map);
        try {
            if (!TextUtils.isEmpty(draftBean.bimage)) {
                if (!TextUtils.isEmpty(draftBean.voice)) {
                    a.a("bimage", new File(draftBean.bimage));
                } else if (TextUtils.isEmpty(draftBean.video)) {
                    a.a(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY, new File(draftBean.bimage));
                } else {
                    a.a("bimage", new File(draftBean.bimage));
                }
            }
            if (!TextUtils.isEmpty(draftBean.voice)) {
                a.a("voice", new File(draftBean.voice));
            } else if (!TextUtils.isEmpty(draftBean.video)) {
                a.a("video", new File(draftBean.video));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return a;
    }
}
