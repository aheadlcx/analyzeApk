package qsbk.app.utils.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.io.InputStream;

public class GifView extends ImageView implements GifAction {
    private GifDecoder a;
    private Bitmap b;
    private boolean c;
    private boolean d;
    private a e;
    private Context f;
    private boolean g;
    private View h;
    private GifImageType i;
    private Handler j;

    public enum GifImageType {
        WAIT_FINISH(0),
        SYNC_DECODER(1),
        COVER(2);
        
        final int a;

        private GifImageType(int i) {
            this.a = i;
        }
    }

    private class a extends Thread {
        final /* synthetic */ GifView a;

        private a(GifView gifView) {
            this.a = gifView;
        }

        public void run() {
            if (this.a.a != null) {
                while (this.a.c) {
                    if (this.a.a.getFrameCount() == 1) {
                        this.a.b = this.a.a.next().image;
                        this.a.a.free();
                        this.a.a();
                        return;
                    } else if (this.a.d) {
                        SystemClock.sleep(50);
                    } else {
                        GifFrame next = this.a.a.next();
                        if (next == null) {
                            SystemClock.sleep(50);
                        } else {
                            if (next.image != null) {
                                this.a.b = next.image;
                            } else if (next.imageName != null) {
                                this.a.b = BitmapFactory.decodeFile(next.imageName);
                            }
                            long j = (long) next.delay;
                            if (this.a.j != null) {
                                this.a.a();
                                SystemClock.sleep(j);
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public GifView(Context context) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = true;
        this.d = false;
        this.e = null;
        this.f = null;
        this.g = false;
        this.h = null;
        this.i = GifImageType.SYNC_DECODER;
        this.j = new a(this);
        this.f = context;
        setScaleType(ScaleType.FIT_XY);
    }

    public GifView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GifView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
        this.b = null;
        this.c = true;
        this.d = false;
        this.e = null;
        this.f = null;
        this.g = false;
        this.h = null;
        this.i = GifImageType.SYNC_DECODER;
        this.j = new a(this);
        this.f = context;
        setScaleType(ScaleType.FIT_XY);
    }

    private void setGifDecoderImage(byte[] bArr) {
        if (this.a == null) {
            this.a = new GifDecoder(this);
        }
        this.a.setGifImage(bArr);
        this.a.start();
    }

    private void setGifDecoderImage(InputStream inputStream) {
        if (this.a == null) {
            this.a = new GifDecoder(this);
        }
        this.a.setGifImage(inputStream);
        this.a.start();
    }

    public void setAsBackground(View view) {
        this.h = view;
    }

    protected Parcelable onSaveInstanceState() {
        super.onSaveInstanceState();
        if (this.a != null) {
            this.a.free();
        }
        return null;
    }

    public void setGifImage(byte[] bArr) {
        setGifDecoderImage(bArr);
    }

    public void setGifImage(InputStream inputStream) {
        setGifDecoderImage(inputStream);
    }

    public void setGifImage(int i) {
        setGifDecoderImage(getResources().openRawResource(i));
    }

    public void destroy() {
        if (this.a != null) {
            this.a.free();
        }
    }

    public void showCover() {
        if (this.a != null) {
            this.d = true;
            this.b = this.a.getImage();
            invalidate();
        }
    }

    public void showAnimation() {
        if (this.d) {
            this.d = false;
        }
    }

    public void setGifImageType(GifImageType gifImageType) {
        if (this.a == null) {
            this.i = gifImageType;
        }
    }

    public void parseOk(boolean z, int i) {
        if (!z) {
            return;
        }
        if (this.a != null) {
            switch (b.a[this.i.ordinal()]) {
                case 1:
                    if (i != -1) {
                        return;
                    }
                    if (this.a.getFrameCount() > 1) {
                        new a().start();
                        return;
                    } else {
                        a();
                        return;
                    }
                case 2:
                    if (i == 1) {
                        this.b = this.a.getImage();
                        a();
                        return;
                    } else if (i != -1) {
                        return;
                    } else {
                        if (this.a.getFrameCount() <= 1) {
                            a();
                            return;
                        } else if (this.e == null) {
                            this.e = new a();
                            this.e.start();
                            return;
                        } else {
                            return;
                        }
                    }
                case 3:
                    if (i == 1) {
                        this.b = this.a.getImage();
                        a();
                        return;
                    } else if (i == -1) {
                        a();
                        return;
                    } else if (this.e == null) {
                        this.e = new a();
                        this.e.start();
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
        Log.e("gif", "parse error");
    }

    private void a() {
        if (this.j != null) {
            this.j.sendMessage(this.j.obtainMessage());
        }
    }

    private void b() {
        setImageBitmap(this.b);
        invalidate();
    }
}
