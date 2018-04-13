package qsbk.app.live.model;

import android.content.Context;
import android.text.TextUtils;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.ye.videotools.filter.Angel;
import qsbk.app.ye.videotools.filter.BlackWhite;
import qsbk.app.ye.videotools.filter.Brannan;
import qsbk.app.ye.videotools.filter.ClassicHDR;
import qsbk.app.ye.videotools.filter.Cool;
import qsbk.app.ye.videotools.filter.Earlybird;
import qsbk.app.ye.videotools.filter.ICESpirit;
import qsbk.app.ye.videotools.filter.Inkwell;
import qsbk.app.ye.videotools.filter.Lomo;
import qsbk.app.ye.videotools.filter.Nashville;
import qsbk.app.ye.videotools.filter.Nature;
import qsbk.app.ye.videotools.filter.Rainbow;
import qsbk.app.ye.videotools.filter.Sexy;
import qsbk.app.ye.videotools.filter.Sutro;
import qsbk.app.ye.videotools.filter.Sweet;
import qsbk.app.ye.videotools.filter.VideoFilter;
import qsbk.app.ye.videotools.filter.Whitening;
import qsbk.app.ye.videotools.filter.XProll;

public class VideoFilterData implements Serializable {
    public static final String LAST_CHOOSED_FILTER_ID = "lastChoosedFilter";
    public static final String LAST_CHOOSED_FILTER_NAME = "lastChoosedFilterName";
    public static final String NATURE = "Nature";
    public static final String ORIGIN = "None";
    private static ArrayList<VideoFilterData> a;
    public Class filterClass;
    public String name;
    public String remark;

    public VideoFilterData(String str) {
        this.remark = str;
    }

    public VideoFilterData(String str, Class cls, String str2) {
        this.name = str;
        this.filterClass = cls;
        this.remark = str2;
    }

    public static VideoFilterData newInstance(String str) {
        return new VideoFilterData(str);
    }

    public String toString() {
        return "VideoFilterData{name='" + this.name + '\'' + ", filterClass=" + this.filterClass + ", remark='" + this.remark + '\'' + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.remark.equals(((VideoFilterData) obj).remark);
    }

    public int hashCode() {
        return this.remark.hashCode();
    }

    public static List<VideoFilterData> getSupportFilterList() {
        if (a == null) {
            a = new ArrayList();
            a.add(new VideoFilterData("原图", VideoFilter.class, ORIGIN));
            a.add(new VideoFilterData("奶昔", Nashville.class, "Nashville"));
            a.add(new VideoFilterData("和谐", XProll.class, "XProll"));
            a.add(new VideoFilterData("橘彩", Sexy.class, "Sexy"));
            a.add(new VideoFilterData("清丽", ICESpirit.class, "ICESpirit"));
            a.add(new VideoFilterData("泉涌", Rainbow.class, "Rainbow"));
            a.add(new VideoFilterData("泼墨", Inkwell.class, "Inkwell"));
            a.add(new VideoFilterData("迷离", Brannan.class, "Brannan"));
            a.add(getNatureFilter());
            a.add(new VideoFilterData("遗忘", Sutro.class, "Sutro"));
            a.add(new VideoFilterData("粉系", Sweet.class, "Sweet"));
            a.add(new VideoFilterData("弥漫", ClassicHDR.class, "ClassicHDR"));
            a.add(new VideoFilterData("晨曦", Earlybird.class, "Earlybird"));
            a.add(new VideoFilterData("神秘", Lomo.class, "Lomo"));
            a.add(new VideoFilterData("亮白", Cool.class, "Cool"));
            a.add(new VideoFilterData("黑白", BlackWhite.class, "BlackWhite"));
            a.add(new VideoFilterData("素雅", Whitening.class, "Whitening"));
            a.add(new VideoFilterData("魅惑", Angel.class, "Angel"));
        }
        return a;
    }

    public static int getPositionOfNatureFilter() {
        return getSupportFilterList().indexOf(getNatureFilter());
    }

    public static VideoFilterData getNatureFilter() {
        return new VideoFilterData("自然", Nature.class, NATURE);
    }

    public static VideoFilter getFilter(Context context, String str) {
        return getFilter(context, getFilterId(str));
    }

    public static int getFilterId(String str) {
        int i;
        int positionOfNatureFilter = getPositionOfNatureFilter();
        List supportFilterList = getSupportFilterList();
        if (TextUtils.isEmpty(str)) {
            i = positionOfNatureFilter;
        } else {
            i = supportFilterList.indexOf(newInstance(str));
        }
        return i == -1 ? positionOfNatureFilter : i;
    }

    public static VideoFilter getFilter(Context context, int i) {
        VideoFilter videoFilter;
        Class cls = VideoFilter.class;
        List supportFilterList = getSupportFilterList();
        if (i >= 0 && i < supportFilterList.size()) {
            cls = ((VideoFilterData) supportFilterList.get(i)).filterClass;
        }
        if (cls != VideoFilter.class) {
            try {
                videoFilter = (VideoFilter) cls.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
            } catch (InstantiationException e) {
                e.printStackTrace();
                videoFilter = null;
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                videoFilter = null;
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
                videoFilter = null;
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            }
            if (videoFilter != null) {
                return new VideoFilter();
            }
            return videoFilter;
        }
        videoFilter = null;
        if (videoFilter != null) {
            return videoFilter;
        }
        return new VideoFilter();
    }

    public static String getFilterName(int i) {
        String str = NATURE;
        List supportFilterList = getSupportFilterList();
        if (i < 0 || i >= supportFilterList.size()) {
            return str;
        }
        return ((VideoFilterData) supportFilterList.get(i)).remark;
    }

    public static String getFilterRemark(int i) {
        String str = null;
        List supportFilterList = getSupportFilterList();
        if (i >= 0 && i < supportFilterList.size()) {
            str = ((VideoFilterData) supportFilterList.get(i)).remark;
        }
        if (TextUtils.isEmpty(str)) {
            return ORIGIN;
        }
        return str;
    }

    public static String getLastChoosedFilter() {
        String str = null;
        if (PreferenceUtils.instance().contains(LAST_CHOOSED_FILTER_ID)) {
            int i = PreferenceUtils.instance().getInt(LAST_CHOOSED_FILTER_ID, getPositionOfNatureFilter());
            PreferenceUtils.instance().removeKey(LAST_CHOOSED_FILTER_ID);
            str = getFilterRemark(i);
        }
        if (TextUtils.isEmpty(str)) {
            str = NATURE;
        }
        return PreferenceUtils.instance().getString(LAST_CHOOSED_FILTER_NAME, str);
    }

    public static void setLastChoosedFilter(String str) {
        PreferenceUtils.instance().putString(LAST_CHOOSED_FILTER_NAME, str);
    }
}
