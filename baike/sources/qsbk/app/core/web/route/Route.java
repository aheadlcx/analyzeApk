package qsbk.app.core.web.route;

import java.util.HashMap;

public class Route {
    private HashMap<String, IWebResponse> a = new HashMap();
    private int b = 0;

    public String recordNode(IWebResponse iWebResponse) {
        String num = Integer.toString(a());
        this.a.put(num, iWebResponse);
        return num;
    }

    public IWebResponse removeNode(String str) {
        IWebResponse iWebResponse = (IWebResponse) this.a.get(str);
        this.a.remove(str);
        return iWebResponse;
    }

    private synchronized int a() {
        this.b++;
        if (this.b > 1000) {
            this.b = 0;
        }
        return this.b;
    }
}
