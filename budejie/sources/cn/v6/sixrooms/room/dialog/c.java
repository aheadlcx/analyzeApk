package cn.v6.sixrooms.room.dialog;

final class c implements Runnable {
    final /* synthetic */ InputSongDialog a;

    c(InputSongDialog inputSongDialog) {
        this.a = inputSongDialog;
    }

    public final void run() {
        this.a.openKeybord();
    }
}
