package com.budejie.www.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.budejie.www.f.b;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ad {
    private static ad c = new ad();
    Context a;
    Handler b;
    private MediaPlayer d;
    private boolean e = false;
    private b f;
    private String g;
    private int h;
    private int i;
    private TelephonyManager j;

    private ad() {
    }

    public static ad a() {
        return new ad();
    }

    public void a(Context context) {
        this.a = context;
        this.j = (TelephonyManager) context.getSystemService("phone");
        this.j.listen(new ad$a(this), 32);
        this.b = new Handler(context.getMainLooper());
        if (this.d == null) {
            this.d = new MediaPlayer();
        }
        this.d.setAudioStreamType(3);
        this.d.setOnCompletionListener(new ad$1(this));
        this.d.setOnErrorListener(new ad$2(this));
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    public void a(String str) {
        try {
            if (this.d == null) {
                throw new Exception("it is called after init().");
            }
            this.d.reset();
            if (str != null) {
                b(str);
                this.d.setDataSource(str);
                this.d.setOnPreparedListener(new ad$3(this));
                this.d.prepare();
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
        if (this.d != null) {
            this.d.seekTo(i);
        }
    }

    public boolean b() {
        return this.e;
    }

    public boolean c() {
        if (this.d != null) {
            return this.d.isPlaying();
        }
        return false;
    }

    public void d() {
        if (this.d != null && !this.d.isPlaying()) {
            this.d.start();
            this.e = false;
        }
    }

    public void e() {
        if (this.d != null && this.d.isPlaying()) {
            c(j());
            b(i());
            this.d.pause();
            this.e = true;
        }
    }

    public void f() {
        if (this.d != null) {
            this.e = false;
        }
    }

    public void g() {
        if (this.d != null) {
            Log.i("play", "stop");
            if (this.f != null) {
                this.f.a(1);
            }
            this.d.stop();
            f();
        }
    }

    public void h() {
        if (this.d != null) {
            this.d.stop();
        }
    }

    public int i() {
        if (this.d != null) {
            return this.d.getCurrentPosition();
        }
        return 0;
    }

    public int j() {
        if (this.d != null) {
            return this.d.getDuration();
        }
        return 0;
    }

    public void b(String str) {
        this.g = str;
    }

    public int k() {
        return this.h;
    }

    public void b(int i) {
        this.h = i;
    }

    public void c(int i) {
        this.i = i;
    }

    public static String a(long j) {
        return new SimpleDateFormat("mm:ss").format(new Date(j));
    }

    public void a(String str, Handler handler) {
        try {
            if (this.d == null) {
                throw new Exception("it is called after init().");
            }
            this.d.reset();
            if (str != null) {
                b(str);
                this.d.setDataSource(str);
                this.d.setOnPreparedListener(new ad$4(this, handler));
                this.d.prepare();
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
            if ("Prepare failed.: status=0x1".equals(e4.getMessage())) {
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.what = 10;
                obtainMessage.obj = Integer.valueOf(0);
                handler.sendMessage(obtainMessage);
            }
            e4.printStackTrace();
        } catch (Exception e5) {
            e5.printStackTrace();
        }
    }
}
