package qsbk.app.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.model.CircleArticle;
import qsbk.app.widget.BaseCell;
import qsbk.app.widget.PtrLayout;

public class ReadCircle {
    private static final List<CircleArticle> a = new ArrayList();
    private static final SharedPreferences b = QsbkApp.mContext.getSharedPreferences("readcircle", 0);
    public static final HashMap<String, Boolean> mReadCircle = new HashMap();

    private static class a implements OnScrollListener {
        private final OnScrollListener a;

        a(OnScrollListener onScrollListener) {
            this.a = onScrollListener;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (this.a != null) {
                this.a.onScrollStateChanged(absListView, i);
            }
            if (i == 0) {
                int childCount = absListView.getChildCount();
                int top = absListView.getTop();
                int bottom = absListView.getBottom();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = absListView.getChildAt(i2);
                    if (childAt != null) {
                        int top2 = childAt.getTop();
                        int bottom2 = childAt.getBottom();
                        int i3 = bottom2 - top2;
                        if ((top2 <= top && bottom2 >= bottom) || (top2 >= top && bottom2 <= bottom)) {
                            a(childAt);
                        } else if (top2 >= top && bottom2 >= bottom) {
                            if ((((float) i3) / 3.0f) - ((float) (bottom2 - bottom)) >= 0.0f) {
                                a(childAt);
                            }
                        } else if (top2 <= top && bottom2 <= bottom && (((float) i3) / 3.0f) - ((float) (top - top2)) >= 0.0f) {
                            a(childAt);
                        }
                    }
                }
            }
        }

        private void a(View view) {
            if (view != null) {
                Object tag = view.getTag();
                if (tag != null && (tag instanceof BaseCell)) {
                    tag = ((BaseCell) tag).getItem();
                    if (tag instanceof CircleArticle) {
                        ReadCircle.setRead((CircleArticle) tag);
                    }
                }
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (this.a != null) {
                this.a.onScroll(absListView, i, i2, i3);
            }
        }
    }

    private ReadCircle() {
    }

    public static void setRead(CircleArticle circleArticle) {
        if (circleArticle != null) {
            CharSequence charSequence = circleArticle.id;
            if (!TextUtils.isEmpty(charSequence) && !mReadCircle.containsKey(charSequence)) {
                mReadCircle.put(charSequence, Boolean.TRUE);
            }
        }
    }

    public static boolean setFirstArticleRead(List<Object> list) {
        if (list != null && list.size() > 0) {
            Object obj = list.get(0);
            if (obj instanceof CircleArticle) {
                setRead((CircleArticle) obj);
                return true;
            }
        }
        return false;
    }

    public static void trackListView(PtrLayout ptrLayout) {
        ptrLayout.setOnScrollListener(new a(ptrLayout.getOnScrollListener()));
    }

    public static void init() {
        String string = b.getString("_read_circle_", "");
        if (!"".equalsIgnoreCase(string)) {
            try {
                JSONArray jSONArray = new JSONObject(string).getJSONArray("items");
                int length = jSONArray.length();
                long currentTimeMillis = System.currentTimeMillis();
                for (int i = 0; i < length; i++) {
                    Object parseJson = CircleArticle.parseJson(jSONArray.getJSONObject(i));
                    if (parseJson instanceof CircleArticle) {
                        CircleArticle circleArticle = (CircleArticle) parseJson;
                        if (Math.abs((((long) circleArticle.createAt) * 1000) - currentTimeMillis) < 259200000) {
                            a.add(circleArticle);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean filter() {
        Iterator it = a.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (mReadCircle.containsKey(((CircleArticle) it.next()).id)) {
                it.remove();
                z = true;
            }
        }
        a();
        return z;
    }

    private static void a() {
        while (a.size() > 30) {
            a.remove(0);
        }
    }

    public static void onLoadMore(List<Object> list) {
        for (Object next : list) {
            if (next instanceof CircleArticle) {
                CircleArticle circleArticle = (CircleArticle) next;
                if (!a.contains(circleArticle)) {
                    a.add(circleArticle);
                }
            }
        }
    }

    public static boolean onRefresh(List<Object> list) {
        filter();
        boolean z = false;
        for (CircleArticle circleArticle : a) {
            boolean z2;
            if (list.contains(circleArticle)) {
                z2 = z;
            } else {
                list.add(circleArticle);
                z2 = true;
            }
            z = z2;
        }
        a.clear();
        for (Object next : list) {
            if (next instanceof CircleArticle) {
                a.add((CircleArticle) next);
            }
        }
        return z;
    }

    public static String toJSONObjectString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("total", a.size());
            JSONArray jSONArray = new JSONArray();
            for (CircleArticle toJSONObject : a) {
                jSONArray.put(CircleArticle.toJSONObject(toJSONObject));
            }
            jSONObject.put("items", jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            return "{items:[],total:0}";
        }
    }

    public static void destroy() {
        save2Local();
        mReadCircle.clear();
        a.clear();
    }

    public static void save2Local() {
        filter();
        Editor edit = b.edit();
        edit.putString("_read_circle_", toJSONObjectString());
        edit.apply();
    }
}
