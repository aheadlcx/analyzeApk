package cn.v6.sixrooms.room.downloader;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.base.VLHttpClient;
import cn.v6.sixrooms.room.gift.Gift;
import cn.v6.sixrooms.room.gift.GiftResHelp;
import cn.v6.sixrooms.room.gift.ZipUtils;
import java.io.File;

public class GiftResDownloader {
    private Gift a;
    private String b;
    private String c;
    private String d = V6Coop.getInstance().getContext().getFilesDir().toString();
    private File e;
    private File f;
    private GiftResDownLoadCallback g;

    public interface GiftResDownLoadCallback {
        void onLoadingComplete(Gift gift);

        void onLoadingFailed(Gift gift);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GiftResDownloader(cn.v6.sixrooms.room.gift.Gift r7, cn.v6.sixrooms.room.downloader.GiftResDownloader.GiftResDownLoadCallback r8) {
        /*
        r6 = this;
        r3 = 1;
        r1 = 0;
        r6.<init>();
        r0 = cn.v6.sdk.sixrooms.coop.V6Coop.getInstance();
        r0 = r0.getContext();
        r0 = r0.getFilesDir();
        r0 = r0.toString();
        r6.d = r0;
        r6.g = r8;	 Catch:{ Exception -> 0x0145 }
        r6.a = r7;	 Catch:{ Exception -> 0x0145 }
        if (r7 == 0) goto L_0x0152;
    L_0x001d:
        r0 = "";
        r2 = r6.a;	 Catch:{ Exception -> 0x0145 }
        r2 = r2.getGtype();	 Catch:{ Exception -> 0x0145 }
        r4 = "2";
        r2 = r2.equals(r4);	 Catch:{ Exception -> 0x0145 }
        if (r2 == 0) goto L_0x004f;
    L_0x002d:
        r0 = r6.a;	 Catch:{ Exception -> 0x0145 }
        r0 = r0.getAnigift();	 Catch:{ Exception -> 0x0145 }
    L_0x0033:
        r6.b = r0;	 Catch:{ Exception -> 0x0145 }
        r0 = r6.b;	 Catch:{ Exception -> 0x0145 }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0145 }
        if (r0 != 0) goto L_0x0047;
    L_0x003d:
        r0 = r6.b;	 Catch:{ Exception -> 0x0145 }
        r1 = ".zip";
        r0 = r0.endsWith(r1);	 Catch:{ Exception -> 0x0145 }
        if (r0 != 0) goto L_0x00cf;
    L_0x0047:
        r0 = r6.g;	 Catch:{ Exception -> 0x0145 }
        r1 = r6.a;	 Catch:{ Exception -> 0x0145 }
        r0.onLoadingFailed(r1);	 Catch:{ Exception -> 0x0145 }
    L_0x004e:
        return;
    L_0x004f:
        r2 = cn.v6.sixrooms.constants.GiftIdStrs.fireworksIds;	 Catch:{ Exception -> 0x0145 }
        r4 = r6.a;	 Catch:{ Exception -> 0x0145 }
        r4 = r4.getId();	 Catch:{ Exception -> 0x0145 }
        r2 = r2.contains(r4);	 Catch:{ Exception -> 0x0145 }
        if (r2 == 0) goto L_0x00be;
    L_0x005d:
        r2 = r6.a;	 Catch:{ Exception -> 0x0145 }
        r4 = r2.getId();	 Catch:{ Exception -> 0x0145 }
        r2 = -1;
        r5 = r4.hashCode();	 Catch:{ Exception -> 0x0145 }
        switch(r5) {
            case 1823: goto L_0x0089;
            case 1824: goto L_0x0080;
            case 51601: goto L_0x0093;
            default: goto L_0x006b;
        };	 Catch:{ Exception -> 0x0145 }
    L_0x006b:
        r1 = r2;
    L_0x006c:
        switch(r1) {
            case 0: goto L_0x0070;
            case 1: goto L_0x009d;
            case 2: goto L_0x00ad;
            default: goto L_0x006f;
        };	 Catch:{ Exception -> 0x0145 }
    L_0x006f:
        goto L_0x0033;
    L_0x0070:
        r0 = "giftRes";
        r1 = 32768; // 0x8000 float:4.5918E-41 double:1.61895E-319;
        r2 = "99";
        r4 = "";
        r0 = cn.v6.sixrooms.utils.SharedPreferencesUtils.get(r0, r1, r2, r4);	 Catch:{ Exception -> 0x0145 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0145 }
        goto L_0x0033;
    L_0x0080:
        r5 = "99";
        r4 = r4.equals(r5);	 Catch:{ Exception -> 0x0145 }
        if (r4 == 0) goto L_0x006b;
    L_0x0088:
        goto L_0x006c;
    L_0x0089:
        r1 = "98";
        r1 = r4.equals(r1);	 Catch:{ Exception -> 0x0145 }
        if (r1 == 0) goto L_0x006b;
    L_0x0091:
        r1 = r3;
        goto L_0x006c;
    L_0x0093:
        r1 = "430";
        r1 = r4.equals(r1);	 Catch:{ Exception -> 0x0145 }
        if (r1 == 0) goto L_0x006b;
    L_0x009b:
        r1 = 2;
        goto L_0x006c;
    L_0x009d:
        r0 = "giftRes";
        r1 = 32768; // 0x8000 float:4.5918E-41 double:1.61895E-319;
        r2 = "98";
        r4 = "";
        r0 = cn.v6.sixrooms.utils.SharedPreferencesUtils.get(r0, r1, r2, r4);	 Catch:{ Exception -> 0x0145 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0145 }
        goto L_0x0033;
    L_0x00ad:
        r0 = "giftRes";
        r1 = 32768; // 0x8000 float:4.5918E-41 double:1.61895E-319;
        r2 = "430";
        r4 = "";
        r0 = cn.v6.sixrooms.utils.SharedPreferencesUtils.get(r0, r1, r2, r4);	 Catch:{ Exception -> 0x0145 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0145 }
        goto L_0x0033;
    L_0x00be:
        r0 = "giftRes";
        r1 = 32768; // 0x8000 float:4.5918E-41 double:1.61895E-319;
        r2 = "1";
        r4 = "";
        r0 = cn.v6.sixrooms.utils.SharedPreferencesUtils.get(r0, r1, r2, r4);	 Catch:{ Exception -> 0x0145 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0145 }
        goto L_0x0033;
    L_0x00cf:
        r0 = r6.b;	 Catch:{ Exception -> 0x0145 }
        r1 = "/";
        r0 = r0.split(r1);	 Catch:{ Exception -> 0x0145 }
        r1 = r0.length;	 Catch:{ Exception -> 0x0145 }
        if (r1 <= r3) goto L_0x00e1;
    L_0x00da:
        r1 = r0.length;	 Catch:{ Exception -> 0x0145 }
        r1 = r1 + -1;
        r0 = r0[r1];	 Catch:{ Exception -> 0x0145 }
        r6.c = r0;	 Catch:{ Exception -> 0x0145 }
    L_0x00e1:
        r0 = new java.io.File;	 Catch:{ Exception -> 0x0145 }
        r1 = r6.d;	 Catch:{ Exception -> 0x0145 }
        r2 = r6.c;	 Catch:{ Exception -> 0x0145 }
        r0.<init>(r1, r2);	 Catch:{ Exception -> 0x0145 }
        r6.e = r0;	 Catch:{ Exception -> 0x0145 }
        r0 = r6.c;	 Catch:{ Exception -> 0x0145 }
        r1 = 0;
        r2 = r6.c;	 Catch:{ Exception -> 0x0145 }
        r3 = ".zip";
        r2 = r2.indexOf(r3);	 Catch:{ Exception -> 0x0145 }
        r0 = r0.substring(r1, r2);	 Catch:{ Exception -> 0x0145 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0145 }
        r1.<init>();	 Catch:{ Exception -> 0x0145 }
        r2 = cn.v6.sdk.sixrooms.coop.V6Coop.getInstance();	 Catch:{ Exception -> 0x0145 }
        r2 = r2.getContext();	 Catch:{ Exception -> 0x0145 }
        r2 = r2.getFilesDir();	 Catch:{ Exception -> 0x0145 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0145 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0145 }
        r2 = java.io.File.separator;	 Catch:{ Exception -> 0x0145 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0145 }
        r2 = cn.v6.sixrooms.utils.ManifestUtil.getSDCradFilePathName();	 Catch:{ Exception -> 0x0145 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0145 }
        r2 = java.io.File.separator;	 Catch:{ Exception -> 0x0145 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0145 }
        r2 = "giftRes";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0145 }
        r2 = java.io.File.separator;	 Catch:{ Exception -> 0x0145 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0145 }
        r0 = r1.append(r0);	 Catch:{ Exception -> 0x0145 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0145 }
        r1 = new java.io.File;	 Catch:{ Exception -> 0x0145 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x0145 }
        r6.f = r1;	 Catch:{ Exception -> 0x0145 }
        goto L_0x004e;
    L_0x0145:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r6.g;
        r1 = r6.a;
        r0.onLoadingFailed(r1);
        goto L_0x004e;
    L_0x0152:
        r0 = r6.g;	 Catch:{ Exception -> 0x0145 }
        r1 = r6.a;	 Catch:{ Exception -> 0x0145 }
        r0.onLoadingFailed(r1);	 Catch:{ Exception -> 0x0145 }
        goto L_0x004e;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.v6.sixrooms.room.downloader.GiftResDownloader.<init>(cn.v6.sixrooms.room.gift.Gift, cn.v6.sixrooms.room.downloader.GiftResDownloader$GiftResDownLoadCallback):void");
    }

    public void downAsynFile() {
        if (this.f == null) {
            this.g.onLoadingFailed(this.a);
        } else if (GiftResHelp.checkGiftResMd5(this.f.getPath())) {
            this.a.setLocalResPath(this.f.getPath());
            this.g.onLoadingComplete(this.a);
        } else if (this.e.exists()) {
            a();
        } else {
            new VLHttpClient().httpFileDownloadTask(true, this.b, this.e.getPath(), 32768, null, new a(this));
        }
    }

    private void a() {
        try {
            String path = this.f.getPath();
            ZipUtils.UnZipFolder(this.e.getAbsolutePath(), path);
            if (GiftResHelp.checkGiftResMd5(path)) {
                this.a.setLocalResPath(path);
                this.g.onLoadingComplete(this.a);
                return;
            }
            this.g.onLoadingFailed(this.a);
        } catch (Exception e) {
            e.printStackTrace();
            this.g.onLoadingFailed(this.a);
        }
    }
}
