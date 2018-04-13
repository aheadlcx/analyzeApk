package org.greenrobot.eventbus;

import android.util.Log;
import java.util.logging.Level;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public interface f {

    public static class a implements f {
        static final boolean a;
        private final String b;

        static {
            boolean z = false;
            try {
                if (Class.forName("android.util.Log") != null) {
                    z = true;
                }
            } catch (ClassNotFoundException e) {
            }
            a = z;
        }

        public static boolean a() {
            return a;
        }

        public a(String str) {
            this.b = str;
        }

        public void a(Level level, String str) {
            if (level != Level.OFF) {
                Log.println(a(level), this.b, str);
            }
        }

        public void a(Level level, String str, Throwable th) {
            if (level != Level.OFF) {
                Log.println(a(level), this.b, str + "\n" + Log.getStackTraceString(th));
            }
        }

        protected int a(Level level) {
            int intValue = level.intValue();
            if (intValue < 800) {
                if (intValue < 500) {
                    return 2;
                }
                return 3;
            } else if (intValue < IMediaPlayer.MEDIA_INFO_TIMED_TEXT_ERROR) {
                return 4;
            } else {
                if (intValue < 1000) {
                    return 5;
                }
                return 6;
            }
        }
    }

    public static class b implements f {
        public void a(Level level, String str) {
            System.out.println("[" + level + "] " + str);
        }

        public void a(Level level, String str, Throwable th) {
            System.out.println("[" + level + "] " + str);
            th.printStackTrace(System.out);
        }
    }

    void a(Level level, String str);

    void a(Level level, String str, Throwable th);
}
