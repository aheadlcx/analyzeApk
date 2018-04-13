package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.util.LongSparseArray;
import com.iflytek.aiui.AIUIConstant;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ai {
    private final Map<String, List<Layer>> a;
    private final Map<String, aa> b;
    private final LongSparseArray<Layer> c;
    private final List<Layer> d;
    private final Rect e;
    private final long f;
    private final long g;
    private final int h;
    private final float i;

    public static class a {
        public static o a(Context context, String str, am amVar) {
            try {
                return a(context, context.getAssets().open(str), amVar);
            } catch (Throwable e) {
                throw new IllegalStateException("Unable to find file " + str, e);
            }
        }

        public static o a(Context context, InputStream inputStream, am amVar) {
            o vVar = new v(context.getResources(), amVar);
            vVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new InputStream[]{inputStream});
            return vVar;
        }

        public static o a(Resources resources, JSONObject jSONObject, am amVar) {
            o adVar = new ad(resources, amVar);
            adVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new JSONObject[]{jSONObject});
            return adVar;
        }

        static ai a(Resources resources, InputStream inputStream) {
            try {
                byte[] bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
                ai a = a(resources, new JSONObject(new String(bArr, "UTF-8")));
                bi.a(inputStream);
                return a;
            } catch (Throwable e) {
                throw new IllegalStateException("Unable to find file.", e);
            } catch (Throwable e2) {
                throw new IllegalStateException("Unable to load JSON.", e2);
            } catch (Throwable th) {
                bi.a(inputStream);
            }
        }

        static ai a(Resources resources, JSONObject jSONObject) {
            Rect rect;
            float f = resources.getDisplayMetrics().density;
            int optInt = jSONObject.optInt("w", -1);
            int optInt2 = jSONObject.optInt("h", -1);
            if (optInt == -1 || optInt2 == -1) {
                rect = null;
            } else {
                rect = new Rect(0, 0, (int) (((float) optInt) * f), (int) (((float) optInt2) * f));
            }
            ai aiVar = new ai(rect, jSONObject.optLong("ip", 0), jSONObject.optLong("op", 0), jSONObject.optInt("fr", 0), f);
            JSONArray optJSONArray = jSONObject.optJSONArray(AIUIConstant.RES_TYPE_ASSETS);
            b(optJSONArray, aiVar);
            a(optJSONArray, aiVar);
            a(jSONObject, aiVar);
            return aiVar;
        }

        private static void a(JSONObject jSONObject, ai aiVar) {
            JSONArray optJSONArray = jSONObject.optJSONArray("layers");
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                a(aiVar.d, aiVar.c, a.a(optJSONArray.optJSONObject(i), aiVar));
            }
        }

        private static void a(@Nullable JSONArray jSONArray, ai aiVar) {
            if (jSONArray != null) {
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    JSONArray optJSONArray = optJSONObject.optJSONArray("layers");
                    if (optJSONArray != null) {
                        List arrayList = new ArrayList(optJSONArray.length());
                        LongSparseArray longSparseArray = new LongSparseArray();
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            Layer a = a.a(optJSONArray.optJSONObject(i2), aiVar);
                            longSparseArray.put(a.c(), a);
                            arrayList.add(a);
                        }
                        aiVar.a.put(optJSONObject.optString("id"), arrayList);
                    }
                }
            }
        }

        private static void b(@Nullable JSONArray jSONArray, ai aiVar) {
            if (jSONArray != null) {
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject.has("p")) {
                        aa a = a.a(optJSONObject);
                        aiVar.b.put(a.a(), a);
                    }
                }
            }
        }

        private static void a(List<Layer> list, LongSparseArray<Layer> longSparseArray, Layer layer) {
            list.add(layer);
            longSparseArray.put(layer.c(), layer);
        }
    }

    private ai(Rect rect, long j, long j2, int i, float f) {
        this.a = new HashMap();
        this.b = new HashMap();
        this.c = new LongSparseArray();
        this.d = new ArrayList();
        this.e = rect;
        this.f = j;
        this.g = j2;
        this.h = i;
        this.i = f;
    }

    Layer a(long j) {
        return (Layer) this.c.get(j);
    }

    public Rect a() {
        return this.e;
    }

    public long b() {
        return (long) ((((float) (this.g - this.f)) / ((float) this.h)) * 1000.0f);
    }

    long c() {
        return this.g;
    }

    List<Layer> d() {
        return this.d;
    }

    @Nullable
    List<Layer> a(String str) {
        return (List) this.a.get(str);
    }

    boolean e() {
        return !this.b.isEmpty();
    }

    Map<String, aa> f() {
        return this.b;
    }

    float g() {
        return (((float) b()) * ((float) this.h)) / 1000.0f;
    }

    public float h() {
        return this.i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("LottieComposition:\n");
        for (Layer a : this.d) {
            stringBuilder.append(a.a("\t"));
        }
        return stringBuilder.toString();
    }
}
