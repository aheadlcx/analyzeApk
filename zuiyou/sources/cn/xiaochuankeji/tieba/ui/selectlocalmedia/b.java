package cn.xiaochuankeji.tieba.ui.selectlocalmedia;

import android.app.Activity;
import android.content.Intent;
import c.a.c;
import cn.xiaochuan.image.b.a;
import cn.xiaochuankeji.tieba.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.ResultItem;
import com.zhihu.matisse.filter.VideoSizeFilter;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class b {
    public static void a(Activity activity, int i) {
        a(activity, 9, i);
    }

    public static void a(Activity activity, int i, int i2) {
        c(activity, i, i2);
    }

    public static void b(Activity activity, int i) {
        c(activity, 1, i);
    }

    private static void c(Activity activity, int i, int i2) {
        Matisse.from(activity).choose(MimeType.ofNormal(), false).nightMode(c.e().c()).countable(true).capture(true).maxSelectable(i).addFilter(new VideoSizeFilter(104857600, 900000)).gridExpectedSize(activity.getResources().getDimensionPixelSize(R.dimen.grid_expected_size)).restrictOrientation(1).thumbnailScale(0.6f).imageEngine(new a()).forResult(i2);
    }

    public static void c(Activity activity, int i) {
        b(activity, 9, i);
    }

    public static void b(Activity activity, int i, int i2) {
        a(activity, i2, EnumSet.of(MimeType.JPEG, MimeType.PNG, MimeType.GIF, MimeType.BMP), Math.max(1, i));
    }

    public static void d(Activity activity, int i) {
        a(activity, i, EnumSet.of(MimeType.JPEG, MimeType.PNG, MimeType.GIF, MimeType.BMP), 1);
    }

    public static void e(Activity activity, int i) {
        a(activity, i, EnumSet.of(MimeType.JPEG, MimeType.PNG, MimeType.BMP), 9);
    }

    public static void f(Activity activity, int i) {
        a(activity, i, EnumSet.of(MimeType.JPEG, MimeType.PNG, MimeType.BMP), 1);
    }

    private static void a(Activity activity, int i, Set<MimeType> set, int i2) {
        Matisse.from(activity).choose(set, true).nightMode(c.e().c()).countable(true).capture(true).showSingleMediaType(true).maxSelectable(i2).addFilter(new VideoSizeFilter(104857600, 900000)).gridExpectedSize(activity.getResources().getDimensionPixelSize(R.dimen.grid_expected_size)).restrictOrientation(1).thumbnailScale(0.6f).imageEngine(new a()).forResult(i);
    }

    public static void a(Activity activity, int i, List<ResultItem> list) {
        Matisse.from(activity).choose(EnumSet.of(MimeType.JPEG, MimeType.PNG, MimeType.BMP), true).nightMode(c.e().c()).countable(true).capture(true).maxSelectable(9).addFilter(new VideoSizeFilter(104857600, 900000)).gridExpectedSize(activity.getResources().getDimensionPixelSize(R.dimen.grid_expected_size)).restrictOrientation(1).thumbnailScale(0.6f).withSelected(list).imageEngine(new a()).forResult(i);
    }

    public static void b(Activity activity, int i, List<ResultItem> list) {
        Matisse.from(activity).choose(MimeType.ofNormal(), false).nightMode(c.e().c()).countable(true).capture(true).maxSelectable(9).addFilter(new VideoSizeFilter(104857600, 900000)).gridExpectedSize(activity.getResources().getDimensionPixelSize(R.dimen.grid_expected_size)).restrictOrientation(1).thumbnailScale(0.6f).withSelected(list).imageEngine(new a()).forResult(i);
    }

    public static void c(Activity activity, int i, List<LocalMedia> list) {
        List arrayList = new ArrayList();
        for (LocalMedia localMedia : list) {
            arrayList.add(new ResultItem((long) localMedia.mediaID, localMedia.path, localMedia.mimeType, null, localMedia.width, localMedia.height));
        }
        b(activity, i, arrayList);
    }

    public static void d(Activity activity, int i, List<LocalMedia> list) {
        List arrayList = new ArrayList();
        for (LocalMedia localMedia : list) {
            arrayList.add(new ResultItem((long) localMedia.mediaID, localMedia.path, localMedia.mimeType, null, localMedia.width, localMedia.height));
        }
        a(activity, i, arrayList);
    }

    public static List<ResultItem> a(Intent intent) {
        return Matisse.obtainResult(intent);
    }

    public static List<LocalMedia> b(Intent intent) {
        List<ResultItem> obtainResult = Matisse.obtainResult(intent);
        List<LocalMedia> arrayList = new ArrayList();
        for (ResultItem resultItem : obtainResult) {
            LocalMedia localMedia = new LocalMedia();
            localMedia.mediaID = (int) resultItem.id;
            localMedia.path = resultItem.path;
            localMedia.mimeType = resultItem.mimeType;
            localMedia.width = resultItem.width;
            localMedia.height = resultItem.height;
            localMedia.type = 2;
            if (resultItem.mimeType != null && resultItem.mimeType.contains("video")) {
                localMedia.type = 1;
            }
            arrayList.add(localMedia);
        }
        return arrayList;
    }
}
