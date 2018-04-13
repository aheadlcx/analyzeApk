package cn.v6.sixrooms.room.download;

final class a implements Runnable {
    final /* synthetic */ Downloader a;

    a(Downloader downloader) {
        this.a = downloader;
    }

    public final void run() {
        Downloader.b(this.a.a, this.a.b);
    }
}
