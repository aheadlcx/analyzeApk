package com.tencent.bugly.beta.upgrade;

public interface UpgradeStateListener {
    void onDownloadCompleted(boolean z);

    void onUpgradeFailed(boolean z);

    void onUpgradeNoVersion(boolean z);

    void onUpgradeSuccess(boolean z);

    void onUpgrading(boolean z);
}
