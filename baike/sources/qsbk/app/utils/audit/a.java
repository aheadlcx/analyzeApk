package qsbk.app.utils.audit;

import qsbk.app.utils.audit.RequestManager.RequestFilter;

class a implements RequestFilter {
    final /* synthetic */ Object a;
    final /* synthetic */ RequestManager b;

    a(RequestManager requestManager, Object obj) {
        this.b = requestManager;
        this.a = obj;
    }

    public boolean apply(Request request) {
        return request.getTag() == this.a;
    }
}
