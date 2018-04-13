package com.qiushibaike.statsdk;

import android.content.Context;
import com.qiushibaike.statsdk.common.CommonParam;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class DataObj {
    long a;
    long b;
    int c;
    JSONObject d = new JSONObject();
    JSONArray e = new JSONArray();
    HeadObj f = new HeadObj();
    private int g = -1;

    public void setDataSize(int i) {
        this.g = i;
    }

    public int getDataSize() {
        return this.g;
    }

    public String generateAndGetCUID(Context context) {
        if (a(this.f.getCUID())) {
            this.f.setCUID(StoreTool.getInstance().getDeviceCUID(context));
            if (a(this.f.getCUID())) {
                String replaceAll = Pattern.compile("\\s*|\t|\r|\n").matcher(CommonParam.getCUID(context)).replaceAll("");
                this.f.setCUID(replaceAll);
                StoreTool.getInstance().setDeviceCUID(context, replaceAll);
            }
        }
        return this.f.getCUID();
    }

    static boolean a(String str) {
        return str == null || "".equals(str);
    }
}
