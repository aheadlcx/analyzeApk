package cn.xiaochuankeji.tieba.ui.videomaker.sticker;

import android.content.Context;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.drawable.a;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e {
    public static ArrayList<a> a(Context context, JSONObject jSONObject) {
        ArrayList<a> arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("sticker_drawables");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    Constructor declaredConstructor = Class.forName(optJSONArray.getJSONObject(i).getString("class_name")).getDeclaredConstructor(new Class[]{Context.class, JSONObject.class});
                    declaredConstructor.setAccessible(true);
                    arrayList.add((a) declaredConstructor.newInstance(new Object[]{context, r0}));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public static void a(ArrayList<a> arrayList, JSONObject jSONObject) {
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("class_name", aVar.getClass().getName());
                aVar.a(jSONObject2);
                jSONArray.put(jSONObject2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            jSONObject.put("sticker_drawables", jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
