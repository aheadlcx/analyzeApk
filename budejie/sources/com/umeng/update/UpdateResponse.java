package com.umeng.update;

import android.content.Context;
import com.tencent.open.GameAppOperation;
import java.io.Serializable;
import org.json.JSONObject;
import u.upd.c;
import u.upd.h;
import u.upd.m;

public class UpdateResponse extends h implements Serializable {
    private static final long a = -7768683594079202710L;
    public boolean delta = false;
    public boolean hasUpdate = false;
    public String new_md5;
    public String origin;
    public String patch_md5;
    public String path;
    public String proto_ver;
    public String size;
    public String target_size;
    public String updateLog = null;
    public String version = null;

    public UpdateResponse(JSONObject jSONObject) {
        super(jSONObject);
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        try {
            this.hasUpdate = "Yes".equalsIgnoreCase(jSONObject.optString(UpdateConfig.a));
            if (this.hasUpdate) {
                this.updateLog = jSONObject.getString("update_log");
                this.version = jSONObject.getString(GameAppOperation.QQFAV_DATALINE_VERSION);
                this.path = jSONObject.getString("path");
                this.target_size = jSONObject.optString("target_size");
                this.new_md5 = jSONObject.optString("new_md5");
                this.delta = jSONObject.optBoolean(a.l);
                if (this.delta) {
                    this.patch_md5 = jSONObject.optString("patch_md5");
                    this.size = jSONObject.optString("size");
                    this.origin = jSONObject.optString("origin");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String a(Context context, boolean z) {
        String string = context.getString(c.a(context).d("UMNewVersion"));
        String string2 = context.getString(c.a(context).d("UMTargetSize"));
        String string3 = context.getString(c.a(context).d("UMUpdateSize"));
        String string4 = context.getString(c.a(context).d("UMUpdateContent"));
        String string5 = context.getString(c.a(context).d("UMDialog_InstallAPK"));
        if (z) {
            return String.format("%s %s\n%s\n\n%s\n%s\n", new Object[]{string, this.version, string5, string4, this.updateLog});
        }
        if (this.delta) {
            string3 = String.format("\n%s %s", new Object[]{string3, m.c(this.size)});
        } else {
            string3 = "";
        }
        return String.format("%s %s\n%s %s%s\n\n%s\n%s\n", new Object[]{string, this.version, string2, m.c(this.target_size), string3, string4, this.updateLog});
    }
}
