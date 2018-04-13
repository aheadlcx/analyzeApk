package cn.v6.sixrooms.room;

import java.io.File;

final class a implements Runnable {
    final /* synthetic */ BaseConfigPresenter a;

    a(BaseConfigPresenter baseConfigPresenter) {
        this.a = baseConfigPresenter;
    }

    public final void run() {
        File file = new File(this.a.mConfigInfo.targetPath);
        if (file.exists()) {
            System.out.println("exits");
        } else {
            file.mkdir();
        }
        if ("full".equals(this.a.mConfigInfo.type)) {
            this.a.saveBadgeFile(this.a.mConfigInfo.targetName);
        } else if ("patch".equals(this.a.mConfigInfo.type)) {
            this.a.saveBadgeFile(this.a.mConfigInfo.targetName);
        }
    }
}
