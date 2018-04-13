package qsbk.app.utils.audit;

import qsbk.app.utils.audit.RequestManager.RequestFilter;

class c implements RequestFilter {
    final /* synthetic */ SimpleImageLoader a;

    c(SimpleImageLoader simpleImageLoader) {
        this.a = simpleImageLoader;
    }

    public boolean apply(Request request) {
        return true;
    }
}
