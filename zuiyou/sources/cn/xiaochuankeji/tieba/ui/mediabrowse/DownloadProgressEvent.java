package cn.xiaochuankeji.tieba.ui.mediabrowse;

public class DownloadProgressEvent {
    public int eventType = 0;
    public int eventValue = 0;
    public long videoId;

    public DownloadProgressEvent(long j, int i, int i2) {
        this.videoId = j;
        this.eventType = i;
        this.eventValue = i2;
    }
}
