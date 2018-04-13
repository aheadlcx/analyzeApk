package com.iflytek.cloud.util;

import android.text.TextUtils;
import com.iflytek.cloud.thirdparty.cb;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class UserWords {
    private Hashtable<String, ArrayList<String>> a;

    public UserWords() {
        this.a = null;
        this.a = new Hashtable();
    }

    public UserWords(String str) {
        this.a = null;
        this.a = new Hashtable();
        a(str);
    }

    private String a() {
        try {
            if (this.a == null) {
                cb.a("mwords is null...");
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (Entry entry : this.a.entrySet()) {
                JSONObject jSONObject2 = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                Iterator it = ((ArrayList) entry.getValue()).iterator();
                while (it.hasNext()) {
                    jSONArray2.put((String) it.next());
                }
                jSONObject2.put("words", jSONArray2);
                jSONObject2.put("name", entry.getKey());
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("userword", jSONArray);
            return jSONObject.toString();
        } catch (Throwable e) {
            cb.a(e);
            return null;
        }
    }

    private void a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                cb.a("userword is null...");
                return;
            }
            JSONArray jSONArray = new JSONObject(new JSONTokener(str)).getJSONArray("userword");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                JSONArray jSONArray2 = jSONObject.getJSONArray("words");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    String obj = jSONArray2.get(i2).toString();
                    if (!(arrayList == null || arrayList.contains(obj))) {
                        arrayList.add(obj);
                    }
                }
                this.a.put(jSONObject.get("name").toString(), arrayList);
            }
        } catch (Throwable e) {
            cb.a(e);
        }
    }

    private boolean a(ArrayList<String> arrayList, String str) {
        if (arrayList == null || arrayList.contains(str)) {
            return false;
        }
        arrayList.add(str);
        return true;
    }

    private boolean a(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (arrayList == null || arrayList2 == null) {
            return false;
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            a((ArrayList) arrayList, (String) it.next());
        }
        return true;
    }

    public ArrayList<String> getKeys() {
        if (this.a == null || this.a.isEmpty()) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList();
        for (Object add : this.a.keySet()) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public ArrayList<String> getWords() {
        return getWords("default");
    }

    public ArrayList<String> getWords(String str) {
        return (ArrayList) this.a.get(str);
    }

    public boolean hasKey(String str) {
        return this.a.containsKey(str);
    }

    public boolean putWord(String str) {
        return putWord("default", str);
    }

    public boolean putWord(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        ArrayList words = getWords(str);
        if (words != null) {
            a(words, str2);
        } else {
            words = new ArrayList();
            a(words, str2);
            this.a.put(str, words);
        }
        return true;
    }

    public boolean putWords(String str, ArrayList<String> arrayList) {
        if (TextUtils.isEmpty(str) || arrayList == null) {
            return false;
        }
        ArrayList words = getWords(str);
        if (words != null) {
            a(words, (ArrayList) arrayList);
        } else {
            words = new ArrayList();
            a(words, (ArrayList) arrayList);
            this.a.put(str, words);
        }
        return true;
    }

    public boolean putWords(ArrayList<String> arrayList) {
        return putWords("default", arrayList);
    }

    public String toString() {
        return a();
    }
}
