package u.upd;

import org.json.JSONObject;

public abstract class g {
    protected static String a = "POST";
    protected static String b = "GET";
    protected String c;

    public abstract JSONObject a();

    public abstract String b();

    protected String c() {
        return a;
    }

    public g(String str) {
        this.c = str;
    }

    public void a(String str) {
        this.c = str;
    }

    public String d() {
        return this.c;
    }
}
