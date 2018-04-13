package qsbk.app.utils.audit;

import qsbk.app.utils.audit.RequestManager.RequestFilter;

class i implements RequestFilter {
    final /* synthetic */ String a;
    final /* synthetic */ SimpleImageLoader b;

    i(SimpleImageLoader simpleImageLoader, String str) {
        this.b = simpleImageLoader;
        this.a = str;
    }

    public boolean apply(Request request) {
        return request.getUrl().equalsIgnoreCase(this.a);
    }
}
