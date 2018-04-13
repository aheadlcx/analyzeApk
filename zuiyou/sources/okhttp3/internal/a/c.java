package okhttp3.internal.a;

import com.qq.e.comm.constants.ErrorCode$NetWorkError;
import com.tencent.bugly.beta.tinker.TinkerReport;
import javax.annotation.Nullable;
import okhttp3.aa;
import okhttp3.y;

public final class c {
    @Nullable
    public final y a;
    @Nullable
    public final aa b;

    c(y yVar, aa aaVar) {
        this.a = yVar;
        this.b = aaVar;
    }

    public static boolean a(aa aaVar, y yVar) {
        switch (aaVar.b()) {
            case 200:
            case 203:
            case 204:
            case 300:
            case 301:
            case TinkerReport.KEY_LOADED_MISSING_RES /*308*/:
            case 404:
            case ErrorCode$NetWorkError.RESOURCE_LOAD_FAIL_ERROR /*405*/:
            case 410:
            case 414:
            case 501:
                break;
            case 302:
            case TinkerReport.KEY_LOADED_MISSING_DEX_OPT /*307*/:
                if (aaVar.a("Expires") == null && aaVar.k().c() == -1 && !aaVar.k().e() && !aaVar.k().d()) {
                    return false;
                }
            default:
                return false;
        }
        return (aaVar.k().b() || yVar.g().b()) ? false : true;
    }
}
