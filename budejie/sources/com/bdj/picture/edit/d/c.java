package com.bdj.picture.edit.d;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.alipay.sdk.util.h;
import com.bdj.picture.edit.bean.BVType;
import com.bdj.picture.edit.bean.b;
import com.bdj.picture.edit.bean.f;
import com.bdj.picture.edit.util.e;
import com.bdj.picture.edit.util.i;
import com.bdj.picture.edit.widget.HSuperImageView;
import com.umeng.analytics.MobclickAgent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class c extends b {
    private Map<Object, Map<Object, f>> b = new HashMap();
    private Canvas c = null;
    private String d = "{";
    private HashMap<String, String> e = new HashMap();

    public interface a {
        void a(String str, String str2);
    }

    public void a(b bVar, f fVar) {
        if (this.b.containsKey(bVar)) {
            ((Map) this.b.get(bVar)).put(Long.valueOf(fVar.a()), fVar);
            return;
        }
        Map hashMap = new HashMap();
        hashMap.put(Long.valueOf(fVar.a()), fVar);
        this.b.put(bVar, hashMap);
    }

    public void a(b bVar, Object obj) {
        Map a = a(bVar);
        if (a != null && a.containsKey(obj)) {
            a.remove(obj);
        }
    }

    public Map<Object, f> a(b bVar) {
        return (Map) this.b.get(bVar);
    }

    public int b(b bVar) {
        Map map = (Map) this.b.get(bVar);
        if (map != null) {
            return map.size();
        }
        return 0;
    }

    public int a(b bVar, BVType bVType) {
        Map map = (Map) this.b.get(bVar);
        int i = 0;
        if (map != null) {
            for (Entry value : map.entrySet()) {
                int i2;
                if (((f) value.getValue()).b().a() == bVType) {
                    i2 = i + 1;
                } else {
                    i2 = i;
                }
                i = i2;
            }
        }
        return i;
    }

    public f b(b bVar, Object obj) {
        Map a = a(bVar);
        if (a != null) {
            return (f) a.get(obj);
        }
        return null;
    }

    public void a(Activity activity, a aVar, Bitmap bitmap) {
        b(activity, aVar, bitmap);
    }

    private void b(final Activity activity, final a aVar, final Bitmap bitmap) {
        new AsyncTask<Void, Void, String>(this) {
            ProgressDialog a;
            boolean b = true;
            int c = 0;
            int d = 0;
            final /* synthetic */ c h;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                a((String) obj);
            }

            protected String a(Void... voidArr) {
                Iterator it = this.h.b.keySet().iterator();
                Bitmap bitmap = bitmap;
                File a = e.a(activity, "/budejie/report", System.currentTimeMillis() + ".png");
                if (it.hasNext()) {
                    if (this.h.c != null) {
                        this.h.c.restore();
                    }
                    for (Entry value : ((Map) this.h.b.get(it.next())).entrySet()) {
                        this.b = true;
                        f fVar = (f) value.getValue();
                        if (bitmap == null) {
                            Log.e("savePic", "bitmap == null");
                            bitmap = Bitmap.createBitmap(fVar.d(), fVar.e(), Config.ARGB_8888);
                            this.h.c = new Canvas(bitmap);
                            this.h.c.save();
                        } else {
                            Log.i("savePic", "bitmap != null");
                            this.h.c = new Canvas(bitmap);
                            this.h.c.save();
                        }
                        View hSuperImageView = new HSuperImageView(activity, fVar, true);
                        hSuperImageView.setLayoutParams(new LayoutParams(-2, -2));
                        hSuperImageView.a(false);
                        if (fVar.b().a() == BVType.IE_WATERMARK) {
                            this.c++;
                            MobclickAgent.onEvent(activity, "E06-A02", fVar.b().b().a());
                            hSuperImageView.a(i.a(activity, hSuperImageView.getmBitmap(), fVar.f(), fVar.b().b()), fVar.f());
                        } else {
                            this.d++;
                            MobclickAgent.onEvent(activity, "E06-A03", fVar.b().b().a());
                            this.h.e.put(fVar.b().b().a(), "1");
                        }
                        i.a(this.h.c, fVar, hSuperImageView);
                        this.b = false;
                    }
                    MobclickAgent.onEvent(activity, "E06-A04", this.c + "个");
                    MobclickAgent.onEvent(activity, "E06-A05", this.d + "个");
                    if (bitmap != null) {
                        try {
                            com.bdj.picture.edit.mosaic.a.a(a, bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (bitmap != null) {
                    try {
                        com.bdj.picture.edit.mosaic.a.a(a, bitmap);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                if (aVar == null) {
                    return null;
                }
                for (Entry value2 : this.h.e.entrySet()) {
                    String str = (String) value2.getKey();
                    this.h.d = this.h.d + str + ":" + ((String) value2.getValue()) + ",";
                }
                if (this.h.d.contains(",")) {
                    this.h.d = this.h.d.substring(0, this.h.d.length() - 1);
                    this.h.d = this.h.d + h.d;
                } else {
                    this.h.d = "";
                }
                return a.getAbsolutePath();
            }

            protected void a(String str) {
                if (this.a != null) {
                    this.a.dismiss();
                }
                aVar.a(str, this.h.d);
            }

            protected void onPreExecute() {
                super.onPreExecute();
                if (this.a == null) {
                    this.a = new ProgressDialog(activity);
                }
                this.a.show();
            }
        }.execute(new Void[0]);
        new Thread(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
            }
        }.start();
    }
}
