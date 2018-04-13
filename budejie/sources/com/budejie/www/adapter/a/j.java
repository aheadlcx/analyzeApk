package com.budejie.www.adapter.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.bean.RecommendSubscription;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@SuppressLint({"NewApi"})
public class j extends ArrayAdapter<RecommendSubscription> {
    public static List<String> a = new LinkedList();
    public static List<String> b = new ArrayList();
    private GridView c;
    private LruCache<String, Bitmap> d;
    private HashSet<j$a> e;
    private int f;
    private int g;
    private boolean h = true;
    private List<RecommendSubscription> i;

    public j(Context context, List<RecommendSubscription> list, GridView gridView) {
        super(context, 0, list);
        this.c = gridView;
        this.c.setOnScrollListener(new j$b(this, null));
        this.i = list;
        a.clear();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        j$c j_c;
        RecommendSubscription recommendSubscription = (RecommendSubscription) getItem(i);
        if (view == null) {
            j$c j_c2 = new j$c(this);
            view = LayoutInflater.from(getContext()).inflate(R.layout.recommend_subscription_grid_item, null);
            j_c2.a = (AsyncImageView) view.findViewById(R.id.id_item_image);
            j_c2.b = (ImageView) view.findViewById(R.id.id_item_select);
            j_c2.c = (TextView) view.findViewById(R.id.label_name);
            view.setTag(j_c2);
            j_c = j_c2;
        } else {
            j_c = (j$c) view.getTag();
        }
        j_c.a.setAsyncCacheImage(recommendSubscription.getImage_list(), R.drawable.label_default_icon);
        j_c.c.setText(recommendSubscription.getTheme_name());
        j_c.a.setOnClickListener(new j$1(this, recommendSubscription, j_c));
        if (a.contains(recommendSubscription.getTheme_id())) {
            j_c.b.setImageResource(R.drawable.label_selector_icon);
        } else {
            j_c.b.setImageResource(R.drawable.label_no_selector_icon);
        }
        return view;
    }

    public void a(String str, Bitmap bitmap) {
        if (a(str) == null) {
            this.d.put(str, bitmap);
        }
    }

    public Bitmap a(String str) {
        return (Bitmap) this.d.get(str);
    }

    private void a(int i, int i2) {
        int i3 = i;
        while (i3 < i + i2) {
            try {
                String image_list = ((RecommendSubscription) this.i.get(i3)).getImage_list();
                Bitmap a = a(image_list);
                if (a == null) {
                    j$a j_a = new j$a(this);
                    this.e.add(j_a);
                    j_a.execute(new String[]{image_list});
                } else {
                    ImageView imageView = (ImageView) this.c.findViewWithTag(image_list);
                    if (!(imageView == null || a == null)) {
                        imageView.setImageBitmap(a);
                    }
                }
                i3++;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void a() {
        if (this.e != null) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                ((j$a) it.next()).cancel(false);
            }
        }
    }

    public static String b() {
        String str = "";
        for (int i = 0; i < b.size(); i++) {
            if (i == b.size() - 1) {
                str = str + ((String) b.get(i));
            } else {
                str = str + ((String) b.get(i)) + ",";
            }
        }
        return str;
    }

    private Bitmap b(String str) {
        HttpURLConnection httpURLConnection;
        Exception exception;
        Throwable th;
        HttpURLConnection httpURLConnection2 = null;
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection3.setConnectTimeout(5000);
                httpURLConnection3.setReadTimeout(10000);
                httpURLConnection3.setDoInput(true);
                httpURLConnection3.setDoOutput(true);
                Bitmap decodeStream = BitmapFactory.decodeStream(httpURLConnection3.getInputStream());
                if (httpURLConnection3 == null) {
                    return decodeStream;
                }
                httpURLConnection3.disconnect();
                return decodeStream;
            } catch (Exception e) {
                Exception exception2 = e;
                httpURLConnection = httpURLConnection3;
                exception = exception2;
                try {
                    exception.printStackTrace();
                    if (httpURLConnection != null) {
                        return httpURLConnection2;
                    }
                    httpURLConnection.disconnect();
                    return httpURLConnection2;
                } catch (Throwable th2) {
                    th = th2;
                    httpURLConnection2 = httpURLConnection;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                httpURLConnection2 = httpURLConnection3;
                th = th4;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        } catch (Exception e2) {
            exception = e2;
            httpURLConnection = httpURLConnection2;
            exception.printStackTrace();
            if (httpURLConnection != null) {
                return httpURLConnection2;
            }
            httpURLConnection.disconnect();
            return httpURLConnection2;
        } catch (Throwable th5) {
            th = th5;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }
}
