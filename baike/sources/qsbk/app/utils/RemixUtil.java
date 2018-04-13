package qsbk.app.utils;

import android.content.Context;
import qsbk.app.ad.feedsad.qbad.QbAdApkDownloader;
import qsbk.app.game.GamePlugin;

public class RemixUtil {
    public static final int APP_ARG_ERROR = 0;
    public static final int APP_DOWNLOADED = 2;
    public static final int APP_INSTALLED = 3;
    public static final int APP_UNDOWNLOAD = 1;
    public static final String REMIX_NAME = "热猫";
    public static final String REMIX_PACKAGE_NAME = "qsbk.app.remix";

    private RemixUtil() {
    }

    public static void downloadOrIntallOrOpenRemix(Context context, String str) {
        switch (getRemixApkStatus(str)) {
            case 1:
                QbAdApkDownloader.instance().downloadFile(context, str, REMIX_NAME);
                return;
            case 2:
                GamePlugin.installApp(QbAdApkDownloader.instance().getDownloadedFileByUrl(str).getAbsolutePath());
                return;
            case 3:
                GamePlugin.openAndroidAppByPackage(REMIX_PACKAGE_NAME);
                return;
            default:
                return;
        }
    }

    public static int getRemixApkStatus(String str) {
        if (str == null) {
            return 0;
        }
        if (QbAdApkDownloader.instance().isPackageInstalled(REMIX_PACKAGE_NAME)) {
            return 3;
        }
        if (QbAdApkDownloader.instance().isDownloadApkExist(str)) {
            return 2;
        }
        return 1;
    }

    public static boolean isRemixInstalled() {
        return QbAdApkDownloader.instance().isPackageInstalled(REMIX_PACKAGE_NAME);
    }

    public static void openRemix() {
        GamePlugin.openAndroidAppByPackage(REMIX_PACKAGE_NAME);
    }
}
