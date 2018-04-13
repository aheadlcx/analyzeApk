package cn.xiaochuankeji.aop.permission;

import java.io.Serializable;

public class PermissionItem implements Serializable {
    public String deniedButton;
    public String deniedMessage;
    public boolean needGotoSetting;
    public String[] permissions;
    public String rationalButton;
    public String rationalMessage;
    public boolean runIgnorePermission;
    public String settingText;

    public PermissionItem(String... strArr) {
        if (strArr == null || strArr.length <= 0) {
            throw new IllegalArgumentException("permissions must have one content at least");
        }
        this.permissions = strArr;
    }

    public PermissionItem rationalMessage(String str) {
        this.rationalMessage = str;
        return this;
    }

    public PermissionItem rationalButton(String str) {
        this.rationalButton = str;
        return this;
    }

    public PermissionItem deniedMessage(String str) {
        this.deniedMessage = str;
        return this;
    }

    public PermissionItem deniedButton(String str) {
        this.deniedButton = str;
        return this;
    }

    public PermissionItem needGotoSetting(boolean z) {
        this.needGotoSetting = z;
        return this;
    }

    public PermissionItem runIgnorePermission(boolean z) {
        this.runIgnorePermission = z;
        return this;
    }

    public PermissionItem settingText(String str) {
        this.settingText = str;
        return this;
    }
}
