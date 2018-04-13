package qsbk.app.thirdparty;

import android.text.TextUtils;
import java.util.ArrayList;

public class ThirdPartyParameters {
    private ArrayList<String> a = new ArrayList();
    private ArrayList<String> b = new ArrayList();

    public void add(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.a.add(str);
            this.b.add(str2);
        }
    }

    public void add(String str, int i) {
        this.a.add(str);
        this.b.add(String.valueOf(i));
    }

    public void add(String str, long j) {
        this.a.add(str);
        this.b.add(String.valueOf(j));
    }

    public void remove(String str) {
        int indexOf = this.a.indexOf(str);
        if (indexOf >= 0) {
            this.a.remove(indexOf);
            this.b.remove(indexOf);
        }
    }

    public void remove(int i) {
        if (i < this.a.size()) {
            this.a.remove(i);
            this.b.remove(i);
        }
    }

    private int a(String str) {
        if (this.a.contains(str)) {
            return this.a.indexOf(str);
        }
        return -1;
    }

    public String getKey(int i) {
        if (i < 0 || i >= this.a.size()) {
            return "";
        }
        return (String) this.a.get(i);
    }

    public String getValue(String str) {
        int a = a(str);
        if (a < 0 || a >= this.a.size()) {
            return null;
        }
        return (String) this.b.get(a);
    }

    public String getValue(int i) {
        if (i < 0 || i >= this.a.size()) {
            return null;
        }
        return (String) this.b.get(i);
    }

    public int size() {
        return this.a.size();
    }

    public void addAll(ThirdPartyParameters thirdPartyParameters) {
        for (int i = 0; i < thirdPartyParameters.size(); i++) {
            add(thirdPartyParameters.getKey(i), thirdPartyParameters.getValue(i));
        }
    }

    public void clear() {
        this.a.clear();
        this.b.clear();
    }
}
