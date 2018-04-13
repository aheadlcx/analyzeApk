package tv.danmaku.ijk.media.player;

class IjkMediaPlayer$1 implements IjkLibLoader {
    IjkMediaPlayer$1() {
    }

    public void loadLibrary(String str) throws UnsatisfiedLinkError, SecurityException {
        System.loadLibrary(str);
    }
}
