package qsbk.app.utils.json;

import java.io.Serializable;
import org.json.JSONObject;
import qsbk.app.utils.LogUtil;

public class JSONAble implements Serializable, IJSONAble {

    public static class A extends JSONAble {
        public static int st;
        public String a;
        public String b;
        @JsonPrivate
        public String c;
        private String d = "should in a but should not in b";
    }

    public static class B extends A {
        public String d;
        @JsonKeyName("new_e")
        public String e;
    }

    public static void test1() {
        A a = new A();
        A.st = 2;
        a.a = "hello";
        a.b = "world";
        a.c = "should not out";
        LogUtil.d("test json:" + a.encodeToJsonObject().toString());
        A a2 = new A();
        a2.parseFromJSONObject(a.encodeToJsonObject());
        LogUtil.d("a.a " + a2.a);
        LogUtil.d("a.b " + a2.b);
        LogUtil.d("a.c " + a2.c);
        B b = new B();
        b.a = "hello";
        b.b = "world";
        b.d = "new property";
        b.e = "new key name";
        B b2 = new B();
        b2.parseFromJSONObject(b.encodeToJsonObject());
        LogUtil.d("b1.a " + b2.a);
        LogUtil.d("b1.b " + b2.b);
        LogUtil.d("b1.d " + b2.d);
        LogUtil.d("b1.e " + b2.e);
        LogUtil.d("test json:" + b.encodeToJsonObject().toString());
    }

    public void initJSONDefaultValue() {
    }

    public JSONObject encodeToJsonObject() {
        return JSONUtil.encodeToJsonObject(this);
    }

    public void parseFromJSONObject(JSONObject jSONObject) {
        JSONUtil.parseFromJSONObject(jSONObject, this);
    }
}
