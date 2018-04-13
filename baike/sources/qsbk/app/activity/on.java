package qsbk.app.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import qsbk.app.utils.LogUtil;

class on implements OnClickListener {
    final /* synthetic */ ImageClipActivity a;

    on(ImageClipActivity imageClipActivity) {
        this.a = imageClipActivity;
    }

    public void onClick(View view) {
        FileOutputStream fileOutputStream;
        IOException e;
        FileNotFoundException e2;
        Intent intent;
        Throwable th;
        Bitmap clip = this.a.c.clip();
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        clip.compress(CompressFormat.JPEG, 95, byteArrayOutputStream);
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        Parcelable b = this.a.a();
        File file = new File(b.getPath());
        LogUtil.d("ImageClipActivity:" + b.getPath());
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(toByteArray);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e4) {
                e2 = e4;
                try {
                    e2.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    intent = new Intent();
                    intent.putExtra("uri", b);
                    this.a.setResult(-1, intent);
                    this.a.finish();
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream2 = fileOutputStream;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e32 = e6;
                fileOutputStream2 = fileOutputStream;
                try {
                    e32.printStackTrace();
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    }
                    intent = new Intent();
                    intent.putExtra("uri", b);
                    this.a.setResult(-1, intent);
                    this.a.finish();
                } catch (Throwable th3) {
                    th = th3;
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
            }
        } catch (FileNotFoundException e7) {
            e2 = e7;
            fileOutputStream = null;
            e2.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            intent = new Intent();
            intent.putExtra("uri", b);
            this.a.setResult(-1, intent);
            this.a.finish();
        } catch (IOException e8) {
            e322 = e8;
            e322.printStackTrace();
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            intent = new Intent();
            intent.putExtra("uri", b);
            this.a.setResult(-1, intent);
            this.a.finish();
        }
        intent = new Intent();
        intent.putExtra("uri", b);
        this.a.setResult(-1, intent);
        this.a.finish();
    }
}
