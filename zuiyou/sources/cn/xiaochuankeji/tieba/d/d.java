package cn.xiaochuankeji.tieba.d;

import android.os.Environment;
import com.tencent.mm.opensdk.constants.ConstantsAPI.Token;
import java.util.HashMap;

public class d {
    public static String a = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/.xc/");
    public static final HashMap<Integer, String> b = new HashMap();

    static {
        b.put(Integer.valueOf(2), Token.WX_TOKEN_PLATFORMID_VALUE);
        b.put(Integer.valueOf(4), "wechatCircle");
        b.put(Integer.valueOf(3), "weibo");
        b.put(Integer.valueOf(5), "qqzone");
        b.put(Integer.valueOf(1), "qq");
    }

    public static String a(int i) {
        Object obj = b.get(Integer.valueOf(i));
        if (obj == null) {
            return "other";
        }
        return (String) obj;
    }
}
