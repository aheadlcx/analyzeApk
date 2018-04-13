package qsbk.app.game;

public class GameDownloadManager {
    public static final int DOWNLOADED = 4;
    public static final int DOWNLOADING = 2;
    public static final int DOWNLOAD_PAUSED = 3;
    public static final int UNDOWNLOAD = 1;

    public int getDownloadState(DownloadUnit downloadUnit) {
        return 1;
    }

    public int getDownloadRate(DownloadUnit downloadUnit) {
        return 0;
    }
}
