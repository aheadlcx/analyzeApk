package cn.xiaochuankeji.tieba.background.post;

import android.text.TextUtils;
import cn.xiaochuankeji.tieba.background.post.PostRecommendQueryList.RecommendType;
import com.iflytek.cloud.SpeechConstant;
import java.util.HashMap;

public class r {
    public static String a = "post";
    private static HashMap<String, PostRecommendQueryList> b = new HashMap();
    private static PostRecommendQueryList c;
    private static PostRecommendQueryList d;
    private static PostRecommendQueryList e;
    private static String f = "index";

    public static PostRecommendQueryList a() {
        if (c == null) {
            c = new PostRecommendQueryList(RecommendType.INDEX_RECOMMEND, SpeechConstant.PLUS_LOCAL_ALL);
            b.put("index", c);
        }
        return c;
    }

    public static PostRecommendQueryList b() {
        if (e == null) {
            e = new PostRecommendQueryList(RecommendType.TAB_VIDEO, "video");
            b.put("index-video", e);
        }
        return e;
    }

    public static PostRecommendQueryList c() {
        if (d == null) {
            d = new PostRecommendQueryList(RecommendType.INDEX_IMGTXT, "imgtxt");
            b.put("index-imgtxt", d);
        }
        return d;
    }

    public static PostRecommendQueryList a(String str, String str2) {
        if (b.containsKey(str)) {
            return (PostRecommendQueryList) b.get(str);
        }
        PostRecommendQueryList postRecommendQueryList = new PostRecommendQueryList(str, str2);
        b.put(str, postRecommendQueryList);
        return postRecommendQueryList;
    }

    public static String d() {
        return f;
    }

    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            f = str;
        }
    }

    public static PostRecommendQueryList e() {
        return (PostRecommendQueryList) b.get(f);
    }
}
