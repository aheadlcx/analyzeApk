package com.budejie.www.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import com.budejie.www.activity.video.k;
import com.budejie.www.f.b;
import com.budejie.www.util.aa;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MediaPlayerServer extends Service {
    public static MediaPlayer a;

    public class a extends Binder {
        String a = "";
        ExecutorService b = Executors.newSingleThreadExecutor();
        final /* synthetic */ MediaPlayerServer c;
        private Context d;
        private TelephonyManager e;
        private int f;
        private int g;
        private boolean h = false;
        private boolean i = false;
        private boolean j = false;
        private String k;
        private String l;
        private b m;
        private LocalTask<String, Void, Void> n;
        private Handler o;
        private a p;
        private Handler q = new MediaPlayerServer$a$4(this);

        public a(MediaPlayerServer mediaPlayerServer) {
            this.c = mediaPlayerServer;
        }

        public void a(Context context) {
            this.d = context;
            this.p = new b(this.q);
            this.o = new Handler(context.getMainLooper());
            this.e = (TelephonyManager) context.getSystemService("phone");
            this.e.listen(new MediaPlayerServer$a$b(this), 32);
            MediaPlayerServer.a.setAudioStreamType(3);
            MediaPlayerServer.a.setOnCompletionListener(new MediaPlayerServer$a$1(this));
            MediaPlayerServer.a.setOnErrorListener(new MediaPlayerServer$a$2(this));
        }

        public void a(String str) {
            b(str);
        }

        public void b(String str) {
            if (!this.a.equals(str)) {
                i();
                if (this.n != null) {
                    LocalTask localTask = this.n;
                    LocalTask.c();
                }
                this.n = new MediaPlayerServer$a$a(this, null);
                this.n.c(new String[]{str});
                aa.a("PlayerActionImpl", "新线程");
            }
        }

        private void e(String str) {
            try {
                this.j = false;
                this.i = false;
                this.a = str;
                aa.e("wuzhenlin", "initPlayPath  ");
                long currentTimeMillis = System.currentTimeMillis();
                MediaPlayerServer.a.reset();
                aa.e("wuzhenlin", "reset end - begin = " + (System.currentTimeMillis() - currentTimeMillis));
                if (str != null) {
                    c(str);
                    currentTimeMillis = System.currentTimeMillis();
                    MediaPlayerServer.a.setDataSource(str);
                    aa.e("wuzhenlin", "setDataSource end1 - begin1 = " + (System.currentTimeMillis() - currentTimeMillis));
                    MediaPlayerServer.a.setOnPreparedListener(new MediaPlayerServer$a$3(this));
                    currentTimeMillis = System.currentTimeMillis();
                    MediaPlayerServer.a.prepare();
                    aa.e("wuzhenlin", "prepareAsync end2 - begin2 = " + (System.currentTimeMillis() - currentTimeMillis));
                    return;
                }
                throw new Exception("Path to the address is wrong.");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e2) {
                e2.printStackTrace();
            } catch (IllegalStateException e3) {
                e3.printStackTrace();
            } catch (IOException e4) {
                e4.printStackTrace();
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }

        public void a(int i) {
            if (MediaPlayerServer.a != null) {
                MediaPlayerServer.a.seekTo(i);
            }
        }

        public boolean a() {
            if (MediaPlayerServer.a != null) {
                return MediaPlayerServer.a.isPlaying();
            }
            return false;
        }

        public void b() {
            if (MediaPlayerServer.a != null && !MediaPlayerServer.a.isPlaying() && p()) {
                k.a(this.d).k();
                MediaPlayerServer.a.start();
                this.h = false;
            }
        }

        public void c() {
            if (MediaPlayerServer.a != null && MediaPlayerServer.a.isPlaying()) {
                b(f());
                c(g());
                MediaPlayerServer.a.pause();
                q();
                this.h = true;
            }
        }

        public void d() {
            if (MediaPlayerServer.a != null) {
                if (this.m != null) {
                    this.m.a(1);
                }
                MediaPlayerServer.a.stop();
                q();
                h();
            }
            this.j = true;
            this.i = true;
        }

        public void e() {
            if (MediaPlayerServer.a != null) {
                MediaPlayerServer.a.stop();
                q();
            }
        }

        private boolean p() {
            if (this.p != null) {
                boolean a = this.p.a(this.d, 3);
                if (a) {
                    return a;
                }
            }
            return false;
        }

        private void q() {
            if (this.p != null) {
                this.p.b(this.d, 3);
            }
        }

        public int f() {
            if (MediaPlayerServer.a == null || !this.i) {
                return 0;
            }
            return MediaPlayerServer.a.getDuration();
        }

        public int g() {
            if (MediaPlayerServer.a != null) {
                return MediaPlayerServer.a.getCurrentPosition();
            }
            return 0;
        }

        public void h() {
            this.h = false;
        }

        public void i() {
            if (a()) {
                d();
            }
            if (j() != null) {
                j().b();
            }
        }

        public b j() {
            return this.m;
        }

        public void a(b bVar) {
            this.m = bVar;
        }

        public int k() {
            return this.g;
        }

        public void b(int i) {
            this.g = i;
        }

        public int l() {
            return this.f;
        }

        public void c(int i) {
            this.f = i;
        }

        public boolean m() {
            return this.h;
        }

        public void a(boolean z) {
            this.h = z;
        }

        public boolean n() {
            return this.i;
        }

        public void c(String str) {
            this.k = str;
        }

        public String o() {
            return this.l;
        }

        public void d(String str) {
            this.l = str;
        }
    }

    public void onCreate() {
        a = new MediaPlayer();
        super.onCreate();
    }

    public IBinder onBind(Intent intent) {
        return new a(this);
    }
}
