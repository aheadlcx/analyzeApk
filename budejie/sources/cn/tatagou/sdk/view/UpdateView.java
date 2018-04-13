package cn.tatagou.sdk.view;

public class UpdateView {
    private String action;
    private IUpdateView iUpdateView;

    public UpdateView(String str, IUpdateView iUpdateView) {
        this.iUpdateView = iUpdateView;
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public IUpdateView getiUpdateView() {
        return this.iUpdateView;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.action.equals(((UpdateView) obj).action);
    }

    public int hashCode() {
        return this.action.hashCode();
    }
}
