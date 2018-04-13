package com.budejie.www.bean;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;

public class UpdateResult {
    public BodyBean body;
    public UpdateResult$HeadBean head;

    public static class BodyBean {
        public String appstore_url;
        public String dialog_remark;
        public String dialog_title;
        public int down_id;
        public String download_url;
        public String first_btn;
        @SerializedName("package")
        public String packageX;
        public String second_btn;
        public int status;
        public String third_btn;
        public int version_code;
        public String version_name;
    }

    public boolean isAvailable() {
        if (this.head.code != 0 || TextUtils.isEmpty(this.body.dialog_title) || TextUtils.isEmpty(this.body.dialog_remark) || TextUtils.isEmpty(this.body.download_url)) {
            return false;
        }
        return true;
    }
}
