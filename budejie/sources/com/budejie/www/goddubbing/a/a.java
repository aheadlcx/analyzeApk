package com.budejie.www.goddubbing.a;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.goddubbing.c.d;
import com.budejie.www.util.aa;
import com.bumptech.glide.i;
import com.bumptech.glide.load.resource.c.b;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class a extends AsyncTask<Void, Void, Void> {
    private Context a;
    private ListItemObject b;
    private a c;
    private int d = -1;
    private int e = 1;
    private int f;
    private int g;
    private int h;
    private int i;

    public interface a {
        void a(int i);

        void a(int i, int i2);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Void) obj);
    }

    protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
        b((Void[]) objArr);
    }

    public a(Context context, ListItemObject listItemObject, a aVar) {
        this.a = context;
        this.b = listItemObject;
        this.c = aVar;
    }

    protected Void a(Void... voidArr) {
        Exception e;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        FileOutputStream fileOutputStream2 = null;
        try {
            this.i = 70;
            publishProgress(new Void[0]);
            b bVar = (b) i.b(this.a).a(com.lt.a.a(this.a).a(this.b.getImgUrl())).k().c(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
            if (bVar != null) {
                File file = new File(d.e());
                if (!file.exists()) {
                    file.createNewFile();
                }
                fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileOutputStream2.write(bVar.e());
                    fileOutputStream2.flush();
                    a(bVar);
                    a(file.getPath());
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Exception e2) {
                            if (!TextUtils.isEmpty(e2.getMessage())) {
                                aa.e("GifAsyncTask", e2.getMessage());
                            }
                        }
                    }
                } catch (Exception e3) {
                    e2 = e3;
                    try {
                        if (!TextUtils.isEmpty(e2.getMessage())) {
                            aa.e("GifAsyncTask", e2.getMessage());
                        }
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (Exception e22) {
                                if (!TextUtils.isEmpty(e22.getMessage())) {
                                    aa.e("GifAsyncTask", e22.getMessage());
                                }
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e4) {
                                if (!TextUtils.isEmpty(e4.getMessage())) {
                                    aa.e("GifAsyncTask", e4.getMessage());
                                }
                            }
                        }
                        throw th;
                    }
                }
            } else if (null != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception e222) {
                    if (!TextUtils.isEmpty(e222.getMessage())) {
                        aa.e("GifAsyncTask", e222.getMessage());
                    }
                }
            }
        } catch (Exception e5) {
            e222 = e5;
            fileOutputStream2 = null;
            if (TextUtils.isEmpty(e222.getMessage())) {
                aa.e("GifAsyncTask", e222.getMessage());
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
        return null;
    }

    private void a(b bVar) {
        com.bumptech.glide.b.a c = bVar.c();
        for (int i = 0; i < bVar.f(); i++) {
            this.f += c.a(i);
        }
        this.g = c.a();
        this.h = c.b();
        if (this.g % 2 != 0) {
            this.g--;
        }
        if (this.h % 2 != 0) {
            this.h--;
        }
    }

    private void a(String str) {
        if (d.d(str) && this.f > 0 && this.g > 0 && this.h > 0) {
            if (this.f < 7500) {
                this.e = 15000 / this.f;
            } else {
                this.e = 1;
            }
            String c = d.c();
            if (d.d(c)) {
                d.c(c);
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.e; i++) {
                arrayList.add(str);
            }
            this.d = com.budejie.www.goddubbing.c.i.a(arrayList, c, this.g, this.h, new a$1(this));
        }
    }

    protected void b(Void... voidArr) {
        super.onProgressUpdate(voidArr);
        if (this.c != null) {
            this.c.a(this.i);
        }
    }

    protected void a(Void voidR) {
        super.onPostExecute(voidR);
        if (this.c != null) {
            this.c.a(this.e, this.d);
        }
    }
}
