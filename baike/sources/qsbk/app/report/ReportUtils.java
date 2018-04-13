package qsbk.app.report;

import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import qsbk.app.model.ReportBean;
import qsbk.app.utils.SharePreferenceUtils;

public final class ReportUtils {
    public static final ReportUtils INSTANCE = new ReportUtils();
    public static List<ReportBean> RESOURCE;
    private static int[] a = new int[]{1, 2, 3, 4, 5};
    private static String[] b = new String[]{"广告欺诈", "淫秽色情", "骚扰谩骂", "反动政治", "其它（坟贴等）"};
    private static boolean c = false;

    static {
        a();
    }

    private ReportUtils() {
    }

    private static void a() {
        String sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("report-article");
        if (c || !a(sharePreferencesValue)) {
            int length = b.length;
            RESOURCE = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                RESOURCE.add(new ReportBean(b[i], a[i]));
            }
            return;
        }
        c = true;
        reset(sharePreferencesValue);
    }

    private static boolean a(String str) {
        return str != null && str.trim().length() > 2;
    }

    public static void reset(String str) {
        String str2 = "\\[.*?]";
        if (a(str)) {
            if (RESOURCE != null) {
                RESOURCE.clear();
            } else {
                RESOURCE = new ArrayList();
            }
            try {
                Matcher matcher = Pattern.compile(str2).matcher(str.substring(1, str.length() - 1));
                while (matcher.find()) {
                    String group = matcher.group(0);
                    String[] split = group.substring(1, group.length() - 1).replaceAll("\"", "").split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    RESOURCE.add(new ReportBean(split[0], Integer.parseInt(split[1])));
                }
            } catch (Exception e) {
                a();
            }
        }
    }
}
