package com.tencent.smtt.sdk;

final class j implements TbsListener {
    j() {
    }

    public void onDownloadFinish(int i) {
        TbsDownloader.a = false;
        if (QbSdk.c() != null) {
            QbSdk.c().onDownloadFinish(i);
        }
        if (QbSdk.d() != null) {
            QbSdk.d().onDownloadFinish(i);
        }
    }

    public void onDownloadProgress(int i) {
        if (QbSdk.d() != null) {
            QbSdk.d().onDownloadProgress(i);
        }
        if (QbSdk.c() != null) {
            QbSdk.c().onDownloadProgress(i);
        }
    }

    public void onInstallFinish(int i) {
        QbSdk.setTBSInstallingStatus(false);
        TbsDownloader.a = false;
        if (QbSdk.c() != null) {
            QbSdk.c().onInstallFinish(i);
        }
        if (QbSdk.d() != null) {
            QbSdk.d().onInstallFinish(i);
        }
    }
}
