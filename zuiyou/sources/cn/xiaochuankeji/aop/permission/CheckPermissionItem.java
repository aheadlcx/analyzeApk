package cn.xiaochuankeji.aop.permission;

import android.text.TextUtils;
import java.io.Serializable;

public class CheckPermissionItem implements Serializable {
    public String classPath;
    public PermissionItem permissionItem;

    public CheckPermissionItem(String str, String... strArr) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("classPath must not be null or empty");
        } else if (strArr == null || strArr.length <= 0) {
            throw new IllegalArgumentException("permissions must have one content at least");
        } else {
            this.permissionItem = new PermissionItem(strArr);
            this.classPath = str;
        }
    }

    public CheckPermissionItem rationalMessage(String str) {
        this.permissionItem.rationalMessage(str);
        return this;
    }

    public CheckPermissionItem rationalButton(String str) {
        this.permissionItem.rationalButton(str);
        return this;
    }

    public CheckPermissionItem deniedMessage(String str) {
        this.permissionItem.deniedMessage(str);
        return this;
    }

    public CheckPermissionItem deniedButton(String str) {
        this.permissionItem.deniedButton(str);
        return this;
    }

    public CheckPermissionItem needGotoSetting(boolean z) {
        this.permissionItem.needGotoSetting(z);
        return this;
    }

    public CheckPermissionItem runIgnorePermission(boolean z) {
        this.permissionItem.runIgnorePermission(z);
        return this;
    }

    public CheckPermissionItem settingText(String str) {
        this.permissionItem.settingText(str);
        return this;
    }
}
