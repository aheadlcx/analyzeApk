package qsbk.app.thirdparty;

public class ThirdPartyDialogError extends Throwable {
    private int a;
    private String b;

    public ThirdPartyDialogError(String str, int i, String str2) {
        super(str);
        this.a = i;
        this.b = str2;
    }

    String a() {
        return this.b;
    }
}
