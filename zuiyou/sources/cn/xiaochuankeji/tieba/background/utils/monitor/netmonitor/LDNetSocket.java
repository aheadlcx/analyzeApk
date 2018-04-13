package cn.xiaochuankeji.tieba.background.utils.monitor.netmonitor;

public class LDNetSocket {
    static boolean b = true;
    private static LDNetSocket c = null;
    public boolean a = true;
    private int d = 6000;
    private final long[] e = new long[4];

    public native void startJNITelnet(String str, String str2);

    static {
        try {
            System.loadLibrary("tracepath");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private LDNetSocket() {
    }
}
