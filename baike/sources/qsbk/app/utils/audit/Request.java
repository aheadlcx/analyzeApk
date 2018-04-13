package qsbk.app.utils.audit;

public class Request {
    private String a;
    private Object b;
    private boolean c;
    private RequestListener d;
    private boolean e = false;
    private boolean f = false;

    public Request(String str, RequestListener requestListener) {
        this.a = str;
        this.d = requestListener;
    }

    public boolean isCanceled() {
        return this.c;
    }

    public boolean isFinished() {
        return this.e;
    }

    public boolean isSuccess() {
        return this.f;
    }

    public void markSuccess() {
        markFinished();
        this.f = true;
    }

    public void markFinished() {
        this.e = true;
    }

    public void cancel() {
        if (!this.c) {
            this.c = true;
            if (this.d != null && !this.e) {
                this.d.onCanceled(this);
            }
        }
    }

    public String getUrl() {
        return this.a;
    }

    public Object getTag() {
        return this.b;
    }

    public void setTag(Object obj) {
        this.b = obj;
    }

    public RequestListener getRequestListener() {
        return this.d;
    }

    public int hashCode() {
        int i;
        int i2 = 1231;
        int i3 = 0;
        if (this.c) {
            i = 1231;
        } else {
            i = 1237;
        }
        i = (i + 31) * 31;
        if (!this.e) {
            i2 = 1237;
        }
        i = ((this.b == null ? 0 : this.b.hashCode()) + ((i + i2) * 31)) * 31;
        if (this.a != null) {
            i3 = this.a.hashCode();
        }
        return i + i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Request request = (Request) obj;
        if (this.b == null) {
            if (request.b != null) {
                return false;
            }
        } else if (!this.b.equals(request.b)) {
            return false;
        }
        if (this.a == null) {
            if (request.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(request.a)) {
            return true;
        } else {
            return false;
        }
    }
}
