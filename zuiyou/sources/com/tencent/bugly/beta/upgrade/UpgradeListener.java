package com.tencent.bugly.beta.upgrade;

import com.tencent.bugly.beta.UpgradeInfo;

public interface UpgradeListener {
    void onUpgrade(int i, UpgradeInfo upgradeInfo, boolean z, boolean z2);
}
