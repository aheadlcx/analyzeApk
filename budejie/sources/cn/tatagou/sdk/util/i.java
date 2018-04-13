package cn.tatagou.sdk.util;

import cn.tatagou.sdk.android.TtgSDK;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class i {
    private static String a = i.class.getSimpleName();
    private Map<String, String> b = new HashMap();
    private DateFormat c = new SimpleDateFormat("yyyy-MM-dd");

    static class a {
        private static final i a = new i();
    }

    public static i getInstance() {
        return a.a;
    }

    public String writeFile(String str) {
        String str2 = "ttg-log" + this.c.format(new Date()) + ".log";
        String canPath = p.canPath(TtgSDK.getContext(), "/ttg/data/log/");
        if (!p.isEmpty(canPath)) {
            File file = new File(canPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(canPath + str2, true);
                fileOutputStream.write(str.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return str2;
    }
}
